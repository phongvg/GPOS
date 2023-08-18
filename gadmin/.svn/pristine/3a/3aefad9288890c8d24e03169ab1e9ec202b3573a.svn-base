package com.gg.gpos.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gg.gpos.api.DeviceRequest;
import com.gg.gpos.api.DeviceResponse;
import com.gg.gpos.api.ResultResponse;
import com.gg.gpos.common.constant.ModuleTypeEnum;
import com.gg.gpos.menu.dto.CoCategoryDto;
import com.gg.gpos.menu.dto.CoFoodItemDto;
import com.gg.gpos.menu.dto.FoodGroupDto;
import com.gg.gpos.menu.dto.RestaurantMasterDto;
import com.gg.gpos.menu.manager.CoCategoryManager;
import com.gg.gpos.menu.manager.CoFoodItemManager;
import com.gg.gpos.menu.manager.FoodGroupManager;
import com.gg.gpos.menu.manager.RestaurantMasterManager;
import com.gg.gpos.reference.dto.SurveyDto;
import com.gg.gpos.reference.dto.TaxDto;
import com.gg.gpos.reference.manager.AttachmentManager;
import com.gg.gpos.reference.manager.SurveyManager;
import com.gg.gpos.reference.manager.TaxManager;
import com.gg.gpos.res.dto.RestaurantDto;
import com.gg.gpos.res.manager.RestaurantManager;
import com.gg.gpos.user.dto.UserDetailsSecurity;
import com.gg.gpos.user.manager.AppUserManager;
import com.gg.gpos.user.manager.CustomUserDetailsService;
import com.speedfrwk.security.jwt.helper.TokenHelper;
import com.speedfrwk.security.jwt.model.AuthenticationRequest;
import com.speedfrwk.security.jwt.model.UserSecurity;
import com.speedfrwk.security.jwt.model.UserTokenState;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/v1")
public class APIController {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private TokenHelper tokenHelper;
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	@Autowired
	private RestaurantMasterManager restaurantMasterManager;
	@Autowired
	private AppUserManager appUserManager;
	@Autowired
	private SurveyManager surveyManager;

	@Autowired
	private TaxManager taxManager;
	private MessageSourceAccessor messages;
    @Autowired
    public void setMessages(MessageSource messageSource) {
        messages = new MessageSourceAccessor(messageSource);
    }
    
    @Autowired
	private CoFoodItemManager coFoodItemManager;
	@Autowired
	private CoCategoryManager coCategoryManager;
	@Autowired
	private FoodGroupManager foodGroupManager;
	@Autowired
	private RestaurantManager restaurantManager;
	@Autowired
	private AttachmentManager attachmentManager;

    
	@PostMapping("/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
		log.info("=====> API=login, request={}", authenticationRequest.toString());
		
		UserDetailsSecurity user = (UserDetailsSecurity)customUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		if (user != null && passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword())) {
			String accessToken = tokenHelper.generateToken(authenticationRequest.getUsername());
			return ResponseEntity.ok(new UserTokenState(accessToken, tokenHelper.getExpiration()));
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PostMapping("/password/change")
	public ResponseEntity<?> changePass(@RequestBody AuthenticationRequest authenticationRequest, HttpServletRequest request) {
		log.info("=====> API=password-change, request={}", authenticationRequest.toString());
		
		if (!isAuthenticated(request)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ResultResponse(HttpStatus.FORBIDDEN.value(), messages.getMessage("api.err.forbidden")));
		}

		if (StringUtils.isBlank(authenticationRequest.getUsername())) {
			return ResponseEntity.badRequest().build();
		} else {
			UserDetails userDetails = customUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
			if (userDetails != null) {
				boolean isPasswordMatched = passwordEncoder.matches(authenticationRequest.getOldPassword(), userDetails.getPassword());
				if (isPasswordMatched) {
					appUserManager.changePass(authenticationRequest.getUsername(), authenticationRequest.getPassword());
					return ResponseEntity.ok(new ResultResponse(HttpStatus.OK.value(), messages.getMessage("api.msg.password_change_success")));
				} else {
					return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResultResponse(HttpStatus.EXPECTATION_FAILED.value(), messages.getMessage("api.err.password_not_match")));
				}
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResultResponse(HttpStatus.NOT_FOUND.value(), messages.getMessage("api.err.user_not_found")));
			}
		}
	}
	
	@PostMapping("/device/check")
	public ResponseEntity<?> checkDevices(@RequestBody DeviceRequest deviceRequest, HttpServletRequest request) {
		log.info("=====> API=device-check, request={}", deviceRequest.toString());
		
		RestaurantMasterDto restaurantMaster = restaurantMasterManager.getByTransceiverDeviceIdAndCallingDeviceId(deviceRequest.getTransceiverDeviceId(), deviceRequest.getCallingDeviceId());
		if (restaurantMaster != null) {
			return ResponseEntity.ok(new DeviceResponse(restaurantMaster.getId(), restaurantMaster.getCode(), restaurantMaster.getTableKitchenId(), restaurantMaster.getTableKitchenCode()));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResultResponse(HttpStatus.NOT_FOUND.value(), messages.getMessage("api.err.notfound")));
		}
	}
	
	private boolean isAuthenticated(HttpServletRequest request) {
		boolean isAuthenticated = false;
		
		String authToken = tokenHelper.getToken(request);
        if (authToken != null) {
            String username = tokenHelper.getUsernameFromToken(authToken);
            if (username != null) {
            	UserDetailsSecurity userDetails = (UserDetailsSecurity)customUserDetailsService.loadUserByUsername(username);
            	UserSecurity userSecurity = new UserSecurity(userDetails.getUsername(), userDetails.getPassword(), userDetails.isEnabled(), userDetails.getAuthorityList());
                boolean isValidToken = tokenHelper.validateToken(authToken, userSecurity);
                if (isValidToken) {
                	isAuthenticated = true;
                }
            }
        }
		
        return isAuthenticated;
	}
	
	@GetMapping("/tax/{taxNo}")
	public ResponseEntity<?> getTaxs(@PathVariable String taxNo) {
		TaxDto taxDto = taxManager.get(taxNo);
		if(taxDto != null) {
			return ResponseEntity.ok(taxDto);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResultResponse(HttpStatus.NOT_FOUND.value(), messages.getMessage("tax.get.notfound")));
		}
	}
	
	@PostMapping("/tax")
	public ResponseEntity<?> saveTax(@RequestBody TaxDto taxDto) {
		List<String> errors = taxManager.save(taxDto);
		if(errors.isEmpty()) {
			return ResponseEntity.ok(new ResultResponse(HttpStatus.OK.value(), messages.getMessage("tax.save.success")));
			
		}else {
			StringBuilder sb = new StringBuilder();
			errors.stream().forEach(e -> {
				sb.append(e).append(",");
			});
			
			sb.deleteCharAt(sb.length() - 1);
			
			return ResponseEntity.ok(new ResultResponse(HttpStatus.BAD_REQUEST.value(), sb.toString()));
		}
	}
	
	@PostMapping("/survey/save")
	public ResponseEntity<?> saveSurvey(@RequestBody SurveyDto surveyDto) {
		log.info("=====> API=survey, request={}", surveyDto.toString());
		try {
			SurveyDto savedSurvey = surveyManager.save(surveyDto);
			if(savedSurvey != null) {
				return ResponseEntity.ok(new ResultResponse(HttpStatus.OK.value(), messages.getMessage("survey.save.success")));
			} else {
				return ResponseEntity.ok(new ResultResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), messages.getMessage("survey.save.fail")));
			}
		} catch (Exception e) {
			return ResponseEntity.ok(new ResultResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
		}
	}
	
	@PostMapping("/attachment/update")
	public ResponseEntity<?> UPDATE() {
		log.info("=====> API=UPDATE ATTACHMENT");
		try {
			List<RestaurantDto> restaurantDtos = restaurantManager.getAll();
			if(!CollectionUtils.isEmpty(restaurantDtos)) {
				restaurantDtos.stream().forEach(item -> {
					Integer restaurantCode = item.getCode();
					String restaurantName = item.getName();
					
					List<CoFoodItemDto> coFoodItemDtos = coFoodItemManager.getByRestaurantCodes(restaurantCode);
					if(!CollectionUtils.isEmpty(coFoodItemDtos)) {
						coFoodItemDtos.stream().forEach(coFoodItem ->{
							String moduleCode = coFoodItem.getFoodItem().getCode();
							attachmentManager.updateRestaurantCodeAndModoleCode(coFoodItem.getId(), ModuleTypeEnum.CO_FOOD_ITEM.val, moduleCode, restaurantCode, restaurantName);
						});
					}
					
					List<CoCategoryDto> categoryDtos = coCategoryManager.getByResCode(restaurantCode);
					if(!CollectionUtils.isEmpty(categoryDtos)) {
						categoryDtos.stream().forEach(categoryDto ->{
							String orderCategoryCode = categoryDto.getSoCategory().getOrderCategory().getCode();
							attachmentManager.updateRestaurantCodeAndModoleCode(categoryDto.getId(), ModuleTypeEnum.CO_CATEGORY.val, orderCategoryCode, restaurantCode, restaurantName);
						});
					}
					List<FoodGroupDto> foodGroupDtos = foodGroupManager.gets(restaurantCode);
					if(!CollectionUtils.isEmpty(foodGroupDtos)) {
						foodGroupDtos.stream().forEach(foodGroupDto ->{
							String moduleCode = foodGroupDto.getCode();
							attachmentManager.updateRestaurantCodeAndModoleCode(foodGroupDto.getId(), ModuleTypeEnum.FOOD_GROUP.val, moduleCode, restaurantCode, restaurantName);
						});
					}
				});
			}
			return ResponseEntity.ok(new ResultResponse(HttpStatus.OK.value(), "OK"));
		} catch (Exception e) {
			return ResponseEntity.ok(new ResultResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
		}
	}
}
