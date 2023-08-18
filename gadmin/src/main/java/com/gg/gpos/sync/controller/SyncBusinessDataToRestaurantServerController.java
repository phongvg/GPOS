package com.gg.gpos.sync.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.jms.JMSException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gg.gpos.common.constant.CatalogTypeEnum;
import com.gg.gpos.common.constant.SymbolEnum;
import com.gg.gpos.common.constant.SystemParamEnum;
import com.gg.gpos.common.json.ConfigLogoutJson;
import com.gg.gpos.controller.BaseController;
import com.gg.gpos.integration.manager.MenuDataSyncManager;
import com.gg.gpos.integration.manager.SyncManager;
import com.gg.gpos.menu.dto.CatalogDataEditDto;
import com.gg.gpos.menu.dto.CoDto;
import com.gg.gpos.menu.dto.GroupParamDto;
import com.gg.gpos.menu.dto.SoDto;
import com.gg.gpos.menu.manager.CatalogDataEditManager;
import com.gg.gpos.reference.dto.AttachmentDto;
import com.gg.gpos.reference.manager.AttachmentManager;
import com.gg.gpos.res.dto.RestaurantDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SyncBusinessDataToRestaurantServerController extends BaseController {
    @Autowired
	private SyncManager syncManager;
    @Autowired
	private MenuDataSyncManager menuDataSyncManager;
	@Autowired
	private CatalogDataEditManager catalogDataEditManager;
	@Autowired
	private AttachmentManager attachmentManager;
    
	/*
     * Đồng bộ DỮ LIỆU từ DANH MỤC GROUP_PARAM xuống SERVER NHÀ HÀNG
     */
    @PostMapping("/sync/catalog/param")
    public String syncGroupParamFromCatalogToRestaurantServer(@Valid GroupParamDto groupParamDto, HttpServletRequest request) {
    	log.info("ENTERING 'SYNC GROUP_PARAM FROM CATALOG TO RESTAURANT SERVER' METHOD...");
    	try {
    		Set<String> keys = org.springframework.util.StringUtils.commaDelimitedListToSet(groupParamDto.getSelectedRestaurantCodes());
        	// Danh sách các mã nhà hàng được chọn để đồng bộ dữ liệu
    		List<String> resCodes = keys.stream().filter(key -> {
        		return !key.contains(SymbolEnum.UNDERSCORE.val);
        	}).collect(Collectors.toList());
    		// lấy danh sách nhà hàng đã chọn
        	List<RestaurantDto> restaurantDtos = restaurantManager.getRestaurantByRestaurantCodes(resCodes);
        	// Lấy và xóa thông tin dữ liệu đã chỉnh sửa ở danh mục
        	List<CatalogDataEditDto> catalogDataEditDtos = catalogDataEditManager.getByTypeAndCatalogId(groupParamDto.getId(), CatalogTypeEnum.PARAM.val);
        	// Lưu thông tin đánh dấu trạng thái "CHỜ ĐỒNG BỘ"
			syncManager.savedSyncGroupParamMenuFromCatalogToRestaurantServer(groupParamDto.getId(), groupParamDto.getOverride(), restaurantDtos, catalogDataEditDtos);
			addMessage(request, getText("apply.to.restaurant.sucess", request.getLocale()));
		} catch (Exception e) {
			addError(request, e.getMessage());
			log.error("ERROR SYNC GROUP_PARAM FROM CATALOG TO RESTAURANT SERVER EXCEPTION: {}", e);
		}
    	String view = "redirect:/cag/group-param/sync-to-restaurant?groupParamId=" + groupParamDto.getId(); 
    	return view;
    }
    
    /*
     * Đồng bộ DỮ LIỆU từ DANH MỤC CO_MENU xuống SERVER NHÀ HÀNG
     */
    @PostMapping("/sync/catalog/co")
    public String syncCoMenuFromCatalogToRestaurantServer(@Valid CoDto coDto, HttpServletRequest request) {
    	log.info("ENTERING 'SYNC CO_MENU FROM CATALOG TO RESTAURANT SERVER' METHOD...");
    	try {
    		Set<String> keys = org.springframework.util.StringUtils.commaDelimitedListToSet(coDto.getSelectedRestaurantCodes());
        	// Danh sách các mã nhà hàng được chọn để đồng bộ dữ liệu
    		List<String> resCodes = keys.stream().filter(key -> {
        		return !key.contains(SymbolEnum.UNDERSCORE.val);
        	}).collect(Collectors.toList());
    		// lấy danh sách nhà hàng đã chọn
        	List<RestaurantDto> restaurantDtos = restaurantManager.getRestaurantByRestaurantCodes(resCodes);
        	
        	// Lấy và xóa thông tin dữ liệu đã chỉnh sửa ở danh mục
        	List<CatalogDataEditDto> catalogDataEditDtos = catalogDataEditManager.getByTypeAndCatalogId(coDto.getId(), CatalogTypeEnum.CO_MENU.val);
        	
        	// Lưu thông tin đánh dấu trạng thái "CHỜ ĐỒNG BỘ"
			syncManager.savedSyncCoMenuFromCatalogToRestaurantServer(coDto.getId(), coDto.isOverride(), restaurantDtos, catalogDataEditDtos);
			addMessage(request, getText("apply.to.restaurant.sucess", request.getLocale()));
		} catch (Exception e) {
			addError(request, e.getMessage());
			log.error("ERROR SYNC CO_MENU FROM CATALOG TO RESTAURANT SERVER EXCEPTION: {}", e);
		}
    	String view = "redirect:/co/sync-to-restaurant?cId=" + coDto.getId(); 
    	return view;
    }
    
    /*
     * Đồng bộ DỮ LIỆU từ DANH MỤC SO_MENU xuống SERVER NHÀ HÀNG
     */
    @PostMapping("/sync/catalog/so")
    public String syncSoMenuFromCatalogToRestaurantServer(@Valid SoDto soDto, HttpServletRequest request) {
    	log.info("ENTERING 'SYNC SO_MENU FROM CATALOG TO RESTAURANT SERVER' METHOD...");
    	try {
    		Set<String> keys = org.springframework.util.StringUtils.commaDelimitedListToSet(soDto.getSelectedRestaurantCodes());
        	// Danh sách các mã nhà hàng được chọn để đồng bộ dữ liệu
    		List<String> resCodes = keys.stream().filter(key -> {
        		return !key.contains(SymbolEnum.UNDERSCORE.val);
        	}).collect(Collectors.toList());
    		// lấy danh sách nhà hàng đã chọn
        	List<RestaurantDto> restaurantDtos = restaurantManager.getRestaurantByRestaurantCodes(resCodes);
        	
        	// Lấy và xóa thông tin dữ liệu đã chỉnh sửa ở danh mục
        	List<CatalogDataEditDto> catalogDataEditDtos = catalogDataEditManager.getByTypeAndCatalogId(soDto.getId(), CatalogTypeEnum.SO_MENU.val);
        	
        	// Lưu thông tin đánh dấu trạng thái "CHỜ ĐỒNG BỘ"
			syncManager.savedSyncSoMenuFromCatalogToRestaurantServer(soDto.getId(), soDto.getOverride(), restaurantDtos, catalogDataEditDtos);
			addMessage(request, getText("apply.to.restaurant.sucess", request.getLocale()));
		} catch (Exception e) {
			addError(request, e.getMessage());
			log.error("ERROR SYNC SO_MENU FROM CATALOG TO RESTAURANT SERVER EXCEPTION: {}", e);
		}
    	String view = "redirect:/cag/so/apply?soId=" + soDto.getId(); 
    	return view;
    }
   
    /*
     * Đồng bộ DỮ LIỆU từ DANH MỤC FILE_ATTACHMENT xuống SERVER NHÀ HÀNG
     */
    @PostMapping("/res/sync-file")
    public String syncFileAttachmentToRestaurantServer(@Valid RestaurantDto restaurantDto, HttpServletRequest request) throws MessageConversionException, JMSException {
    	log.info("ENTERING 'SYNC FILE_ATTACHMENT FROM CATALOG TO RESTAURANT SERVER' METHOD... : {}", restaurantDto);
    	try {
    		Set<String> keys = org.springframework.util.StringUtils.commaDelimitedListToSet(restaurantDto.getSelectedRestaurantCodes());
        	// Danh sách các mã nhà hàng được chọn để đồng bộ dữ liệu
    		List<String> resCodes = keys.stream().filter(key -> {
        		return !key.contains(SymbolEnum.UNDERSCORE.val);
        	}).collect(Collectors.toList());
    		// Lấy danh sách nhà hàng đã chọn để đồng bộ
    		List<RestaurantDto> restaurantDtos = restaurantManager.getRestaurantByRestaurantCodes(resCodes);
    		// Lưu thông tin đánh dấu trạng thái "CHỜ ĐỒNG BỘ"
			syncManager.savedSyncAllFileToRestaurantServer(restaurantDtos);
			addMessage(request, getText("apply.to.restaurant.sucess", request.getLocale()));
		} catch (Exception e) {
			log.error("ERROR: SYNC FILE_ATTACHMENT FROM CATALOG TO RESTAURANT SERVER EXCEPTION: {}", e);
		}
		String view = "redirect:/sync/attachment"; 
		return view;
   }
    
    
    /*
     * Đồng bộ tất cả FILE IMAGE bị lỗi "Không đồng bộ thành công" xuống SERVER NHÀ HÀNG
     */
    @PostMapping("/res/sync-all-file")
    public String syncFileAttachmentToRestaurantServer(HttpServletRequest request) {
    	log.info("ENTERING 'SYNC ALL_FILE ATTACHMENT TO RESTAURANT SERVER' METHOD...");
    	try {
    		Set<String> restaurantCodes = new HashSet<>();
        	List<AttachmentDto> attachmentDtos = attachmentManager.getsByStatusFail();
        	
        	if (!CollectionUtils.isEmpty(attachmentDtos)) {
        		attachmentDtos.stream().forEach(item ->{
        			restaurantCodes.add(item.getRestaurantCode().toString());
        		});
        		// Lấy danh sách nhà hàng đã chọn để đồng bộ
        		List<RestaurantDto> restaurantDtos = restaurantManager.getRestaurantByRestaurantCodes(new ArrayList<>(restaurantCodes));
        		if(!CollectionUtils.isEmpty(restaurantDtos)) {
        			restaurantDtos.stream().forEach(restaurantDto ->{
        				Set<Long> coFoodItemIds = new HashSet<>();
        		    	Set<Long> coCategoryIds = new HashSet<>();
        		    	Set<Long> foodGroupIds = new HashSet<>();
        		    	List<AttachmentDto> attDtos = attachmentDtos.stream().filter(a -> a.getRestaurantCode().equals(restaurantDto.getCode())).collect(Collectors.toList());
        		    	if(!CollectionUtils.isEmpty(attDtos)) {
        		    		attDtos.stream().forEach(a ->{
        		    			String moduleType = a.getModuleType();
        		    			Long moduleId = a.getModuleId();
        		    			switch (moduleType) {
    							case "co-category":
    								coCategoryIds.add(moduleId);
    								break;
    							case "co-food-item":
    								coFoodItemIds.add(moduleId);
    								break;
    							case "food-group":
    								foodGroupIds.add(moduleId);
    								break;
    							default:
    								// Notthing
    								break;
    							}
        		    		});
        		    	}
        		    	if(!CollectionUtils.isEmpty(coFoodItemIds) || !CollectionUtils.isEmpty(coCategoryIds) || !CollectionUtils.isEmpty(foodGroupIds)) {
							// Lưu vào bảng chờ đồng bộ
        		    		syncManager.savedSyncFileToRestaurantServer(restaurantDto, new ArrayList<>(coFoodItemIds), new ArrayList<>(coCategoryIds), new ArrayList<>(foodGroupIds));
						}
        			});
        		}
    		}
        	addMessage(request, getText("apply.to.restaurant.sucess", request.getLocale()));
		} catch (Exception e) {
			log.error("ERROR: SYNC ALL_FILE ATTACHMENT TO RESTAURANT SERVER EXCEPTION: {}", e);
		}
    	String view = "redirect:/sync/attchment-history"; 
    	return view;
    }
    
    /*
     * Đồng bộ DỮ LIỆU từ NHÀ HÀNG xuống SERVER NHÀ HÀNG (Ở form thông tin nhà hàng)
     */
    @PostMapping("res/sync-to-restaurant")
    public String syncBusinessDataToRestaurantServer(@Valid RestaurantDto restaurantDto, @RequestParam(value = "override") Boolean isOverride, HttpServletRequest request) {
    	log.info("ENTERING 'SYNC BUSINESS_DATA FROM RESTAURANT TO RESTAURANT SERVER' METHOD...");                  
    	try {
    		// Lấy danh sách nhà hàng đã chọn để đồng bộ
    		List<RestaurantDto> restaurantDtos = new ArrayList<>();
    		restaurantDtos.add(restaurantDto);
        	// Lưu thông tin đánh dấu trạng thái "CHỜ ĐỒNG BỘ"
			syncManager.savedSyncDataToRestaurantServer(restaurantDto.getOverride(), restaurantDtos);
			addMessage(request, getText("apply.to.restaurant.sucess", request.getLocale()));
		} catch (Exception e) {
			addError(request, e.getMessage());
			log.error("ERROR SYNC BUSINESS_DATA TO RESTAURANTS SERVER EXCEPTION: {}", e);
		}
    	return "redirect:/restaurant/form?rCode=" + restaurantDto.getCode();
    }
    
    /*
     * Đồng bộ DỮ LIỆU BUSINESS_DATA từ NHÀ HÀNG xuống SERVER NHÀ HÀNG
     * Có thể chọn nhiều nhà hàng để đồng bộ
     */
    @PostMapping("restaurant/sync-data-to-res")
    public String syncBusinessDataToRestaurantsServers(@Valid RestaurantDto restaurantDto, HttpServletRequest request) {
    	log.info("ENTERING 'SYNC BUSINESS_DATA TO RESTAURANTS SERVER' METHOD...");      
    	try {
    		// Lấy danh sách nhà hàng đã chọn để đồng bộ
    		List<RestaurantDto> restaurantDtos = new ArrayList<>();
    		
    		// TRƯỜNG HỢP ĐỒNG BỘ CÁC NHÀ HÀNG CÓ TRẠNG THÁI "ĐỒNG BỘ MENU" BỊ LỖI
    		if(restaurantDto.isCheckSyncMenu()) {
    			restaurantDtos.addAll(restaurantManager.getsRestaurantDtoWithSyncStatusBusinessDataFailOrNotSync());
    		} else {
    			Set<String> keys = org.springframework.util.StringUtils.commaDelimitedListToSet(restaurantDto.getSelectedRestaurantCodes());
            	// Danh sách các mã nhà hàng được chọn để đồng bộ dữ liệu
        		List<String> resCodes = keys.stream().filter(key -> {
            		return !key.contains(SymbolEnum.UNDERSCORE.val);
            	}).collect(Collectors.toList());
        		restaurantDtos.addAll(restaurantManager.getRestaurantByRestaurantCodes(resCodes));
    		}
        	
        	// Lưu thông tin đánh dấu trạng thái "CHỜ ĐỒNG BỘ"
			syncManager.savedSyncDataToRestaurantServer(restaurantDto.getOverride(), restaurantDtos);
			addMessage(request, getText("apply.to.restaurant.sucess", request.getLocale()));
		} catch (Exception e) {
			addError(request, e.getMessage());
			log.error("ERROR SYNC BUSINESS_DATA TO RESTAURANTS SERVER EXCEPTION: {}", e);
		}
    	return "redirect:/restaurant/sync-to-res";
    }
    
    /*
     * Unlock USER nhà hàng
     */
    @PostMapping("/sync/restaurant/logout-user")
    public String unlockUserInRestaurantServer(@Valid RestaurantDto restaurantDto, HttpServletRequest request) {
    	log.info("ENTERING 'UNLOCK USER IN RESTAURANT SERVER' METHOD...");                  
    	Integer restaurantCode = restaurantDto.getCode();
    	if(StringUtils.isNotBlank(restaurantDto.getUsername())) {
    		try {
        		// Lấy thông tin một số API_URL
            	Map<String, String> urls = systemParameterManager.gets(SystemParamEnum.API_BUSINESS_PREFIX.param); 
        		String gatewayUrl = systemParameterManager.get(SystemParamEnum.API_GATEWAY_URL.param).getParamValue();
            	String checkOffOnlineRestaurantUrl = gatewayUrl + urls.get(SystemParamEnum.API_BUSINESS_CHECK_STATUS_RESTAURANT_PATTERN.param); 
            	String apiUnlockUser = gatewayUrl + urls.get(SystemParamEnum.API_BUSINESS_UNLOCK_USER_PATTERN.param); 
            	 
            	// Kiểm tra xem thông tin nhà hàng có thông tin IP/PORT không nếu không thì báo lỗi luôn
            	if(StringUtils.isNotBlank(restaurantDto.getIp()) && StringUtils.isNotBlank(restaurantDto.getPort())) {
            		// Kiểm tra xem SERVER NHÀ HÀNG này có online không
                	if(checkOnlineRestaurant(checkOffOnlineRestaurantUrl, restaurantCode)) {
                		ConfigLogoutJson configLogoutJson = new ConfigLogoutJson();
                		configLogoutJson.setUserCode(restaurantDto.getUsername());
    					// Gửi thông tin user cần logout
    					String errors = menuDataSyncManager.sendDataLogoutUserToRestaurant(configLogoutJson, apiUnlockUser, restaurantCode);
    					if (StringUtils.isNotBlank(errors)) {
    						addError(request, errors); 
    					} else {
    						addMessage(request, getText("unlock.user.success", request.getLocale())); 
    					}
                	} else {
                		addError(request, getText("restaurant.server.offline", request.getLocale())); 
                	}
            	} else {
            		addError(request, getText("restaurant.not.ip.port", request.getLocale())); 
            	}
    		} catch (Exception e) {
    			log.error("ERROR UNLOCK USER IN RESTAURANT SERVER EXCEPTION: {}", e);
    			addError(request, e.getMessage()); 
    		}
    	} else {
    		addError(request, getText("restaurant.username.is.empty", request.getLocale())); 
    	}
    	
    	return "redirect:/restaurant/form?rCode=" + restaurantCode;
    }
    
    /*
     * Check trạng thái của server nhà hàng
     */
    private boolean checkOnlineRestaurant(String apiUrl, Integer resCode) {
    	log.debug("PROCESS FUNCTION: CHECK OFF/ONLINE RESTAURANT");    
    	boolean check;
    	try {
    		check = restaurantSyncManager.check(apiUrl,resCode);
		} catch (JsonProcessingException e) {
			check = false;
			e.printStackTrace();
		}
    	return check;
    }
}
