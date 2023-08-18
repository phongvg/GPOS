package com.gg.gpos.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gg.gpos.common.constant.FunctionTypeEnum;
import com.gg.gpos.common.constant.MailEnum;
import com.gg.gpos.common.constant.ModuleTypeEnum;
import com.gg.gpos.common.constant.RefTypeEnum;
import com.gg.gpos.common.util.FancyTree;
import com.gg.gpos.common.util.UserContext;
import com.gg.gpos.controller.BaseController;
import com.gg.gpos.integration.dto.Mail;
import com.gg.gpos.integration.manager.MailService;
import com.gg.gpos.menu.dto.FeatureDto;
import com.gg.gpos.menu.manager.CatalogApplyToRestaurantManager;
import com.gg.gpos.menu.manager.FeatureManager;
import com.gg.gpos.menu.manager.KaloGroupManager;
import com.gg.gpos.menu.manager.MenuTypeManager;
import com.gg.gpos.menu.manager.PrintGroupManager;
import com.gg.gpos.reference.manager.AttachmentManager;
import com.gg.gpos.user.dto.AppUserDto;
import com.gg.gpos.user.dto.RoleDto;
import com.gg.gpos.user.dto.StaffDto;
import com.gg.gpos.user.manager.AppUserManager;
import com.gg.gpos.user.manager.RoleManager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class AjaxController extends BaseController{
	@Autowired
	public JavaMailSender emailSender;
	@Autowired
    private AppUserManager appUserManager;
	@Autowired
	private RoleManager roleManager;
	@Autowired
	private MailService mailService;
	@Autowired
	private MenuTypeManager menuTypeManager;
	@Autowired
	private KaloGroupManager kaloGroupManager;
	@Autowired
	private FeatureManager featureManager;
	@Autowired
    private CatalogApplyToRestaurantManager catalogApplyToRestaurantManager;
	@Autowired
	private PrintGroupManager printGroupManager;
	@Autowired
	private AttachmentManager attachmentManager;
	
	//================== SYSTEM ====================//
	/*
	 * API call khi người dùng muốn set lại mật khẩu cho tài khoản
	 */
	@PostMapping(value = "/system/user/set-password")
    public @ResponseBody AppUserDto changePassword(@RequestParam(value="id")Long id, @RequestParam(value="password")String password) throws Exception {
    	log.info("ENTERING 'CHANGE PASSWORD USER' METHOD...");
    	AppUserDto appUserDto = appUserManager.getFull(id);
    	String logineduser = UserContext.getUsername();
    	if(appUserDto != null) {
    		appUserDto.setPassword(password);
    		appUserDto.setModifiedBy(logineduser);
    		appUserDto.setPasswordChangedDate(null);
    		appUserDto = appUserManager.saveUser(appUserDto);
    		appUserDto.setCheck(true);
    		if (appProperties.isSendEmailEnabled()) {
            	StaffDto staff = appUserDto.getStaff();
        		if (StringUtils.isNotBlank(staff.getEmail())) {
        			try {
                    	Mail mail = new Mail();
                    	String msgContent = "Your password from GPOSADMIN is reset. Login information - username : <b>" + appUserDto.getUsername()+ "</b>"+ " password : " +"<b>" + password + " </b> .Do not share with everyone!";
                    	mail.setMailTo(staff.getEmail());
                    	mail.setSubject(MailEnum.EMAIL_SUBJECT.val);
                    	mail.setMailContent(msgContent);
                    	mailService.sendMail(mail);
        			} catch(Exception e) {
        				log.error("ERROR SEND EMAIL: {}", e);
        			}
        		}
        	}
    	} 
		return appUserDto;
	}
	
	/*
	 * Lấy danh sách các role theo Group
	 */
	@GetMapping("/system/user/get-roles")
    @ResponseBody
    public List<RoleDto> getRole(@RequestParam(value ="appGroupId" ,required = true)Long appGroupId) {
    	log.info("ENTERING 'GET ROLES BY APP_GROUP_ID' METHOD...");
    	return roleManager.getRolesByAppGroupId(appGroupId);
    }
	
	/*
	 * Check username đã tồn tại chưa
	 */
	@GetMapping(value = "/system/user/checkUsername")
	public @ResponseBody boolean checkUsernameExisting(@RequestParam(value = "username", required = false) String username){
		log.info("ENTERING 'CHECK USER_NAME EXISTING' METHOD...");
    	return appUserManager.isExistedUsername(username);
	}
	//================== SYSTEM ====================//
	
	
	//================== REFERENCE ====================//
	/*
	 * Check xem code của MenuType đã tồn tại hay chưa
	 */
	@PostMapping(value = "/menuType/checkCode", produces = "application/json;charset=UTF-8")
	public @ResponseBody boolean checkMenuTypeCode(@RequestParam("code") Integer code, HttpServletRequest request) {
		log.info("ENTERING 'CHECK MENU_TYPE_CODE' METHOD...");
		return menuTypeManager.isUsedCode(code);
	}
	
	/*
	 * Check xem code của KaloGroup đã tồn tại hay chưa
	 */
	@PostMapping(value = "/kaloGroup/checkCode", produces = "application/json;charset=UTF-8")
	public @ResponseBody boolean checkKaloGroupCode(@RequestParam("code") String code, HttpServletRequest request) {
		log.debug("ENTERING 'CHECK KALO_GROUP_CODE' METHOD...");
		return kaloGroupManager.isUsedCode(code);
	}
	
	/*
	 * Check xem code của Feature đã tồn tại hay chưa
	 */
	@PostMapping(value = "/feature/checkCode", produces = "application/json;charset=UTF-8")
	public @ResponseBody boolean checkFeatureCode(@RequestParam("code") String code, HttpServletRequest request) {
		log.debug("ENTERING 'CHECK FEATURE_CODE' METHOD...");
		FeatureDto featureDto = new FeatureDto();
		featureDto.setCode(code);
		return featureManager.isUsedCode(featureDto);
	}
	//================== REFERENCE ====================//
	
	
	
	//================== RESTAURANT ====================//
	
	/*
	 * API lấy dữ liệu brand và nhà hàng 
	 */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/res/get-tree/{refType}/{refId}")
    @ResponseBody
    public FancyTree getTree(@PathVariable (value="refType", required=true) String refType, @PathVariable (value="refId", required=true) Long refId,
    		@RequestParam (value = "keyword", required = false) String keyword, @RequestParam (value = "selectedResCodes", required = false) String selectedResCodes, HttpServletRequest request) {
    	log.info("ENTERING 'GET FANCY_TREE' METHOD...");
		
    	FancyTree root = new FancyTree();
    	List comNodes = new ArrayList();
    	FancyTree comNode = new FancyTree();
    	comNode.setTitle(getText("tree.root.name", request.getLocale()));
    	List<Integer> restaurantCodeSelecteds = null;
    	if(StringUtils.isNotBlank(keyword) || StringUtils.isNotBlank(selectedResCodes)) {
    		if(selectedResCodes != null && !selectedResCodes.isEmpty()) {
    			Set<String> keys = org.springframework.util.StringUtils.commaDelimitedListToSet(selectedResCodes);
    	    	List<String> filterKey = keys.stream().filter(key -> !key.contains("_")).collect(Collectors.toList());
    	    	restaurantCodeSelecteds = filterKey.stream().map(f -> Integer.parseInt(f)).collect(Collectors.toList());
    		}
    		comNodes.add(restaurantManager.getFancyTree(comNode,restaurantCodeSelecteds, keyword));
    	} else {
    		// Tìm kiếm các nhà hàng đang áp dụng danh mục này theo
    		if (RefTypeEnum.CO.val.equals(refType)) {
    			restaurantCodeSelecteds = catalogApplyToRestaurantManager.getRestaurantCodeByCoId(refId);
    		} else if(RefTypeEnum.KDS.val.equals(refType)) {
    			restaurantCodeSelecteds = printGroupManager.getAppliedRestaurantCodes(refId);
    		} else if(RefTypeEnum.SO.val.equals(refType)) {
    			restaurantCodeSelecteds = catalogApplyToRestaurantManager.getRestaurantCodeBySoId(refId);
    		} else if(RefTypeEnum.USER.val.equals(refType)) {
    			AppUserDto appUser = appUserManager.get(refId);
    			restaurantCodeSelecteds = restaurantManager.getAppliedRestaurantCodes(appUser.getUsername());
    		} else if(RefTypeEnum.PARAM.val.equals(refType)) {
    			restaurantCodeSelecteds = catalogApplyToRestaurantManager.getRestaurantCodeByGroupParamId(refId);
    		} else if(RefTypeEnum.RES.val.equals(refType)){
    			restaurantCodeSelecteds = new ArrayList<>();
    		}else {
    			//notthing
    		}
    		comNodes.add(restaurantManager.getFancyTree(comNode, restaurantCodeSelecteds, null));
    	}
    	root.setChildren(comNodes);
    	
    	return root;
    }
    
    
    //================== CO-MENU ====================//
    @PostMapping("/del/infoPhoto")
    public ResponseEntity<String> delInfoPhoto(@RequestParam(value = "configQrOrderId", required = true) Long configQrOrderId, HttpServletRequest request) throws Exception{
    	log.info("ENTERING 'DELETE INFO PHOTO' METHOD...");
    	attachmentManager.delete(configQrOrderId, ModuleTypeEnum.CONFIG_QR_ORDER.val , FunctionTypeEnum.INFO_PHOTO.val);
    	return ResponseEntity.ok(HttpStatus.OK.toString());
    }
    
    @PostMapping("/del/logoPhoto")
    public ResponseEntity<String> delLogoPhoto(@RequestParam(value = "configQrOrderId", required = true) Long configQrOrderId, HttpServletRequest request) throws Exception{
    	log.info("ENTERING 'DELETE LOGO PHOTO' METHOD...");
    	attachmentManager.delete(configQrOrderId, ModuleTypeEnum.CONFIG_QR_ORDER.val , FunctionTypeEnum.LOGO_PHOTO.val);
    	return ResponseEntity.ok(HttpStatus.OK.toString());
    }
    
    @PostMapping("/del/closeOrderPhoto")
    public ResponseEntity<String> delCloseOrder(@RequestParam(value = "configQrOrderId", required = true) Long configQrOrderId, HttpServletRequest request) throws Exception{
    	log.info("ENTERING 'DELETE CLOSE ORDER PHOTO' METHOD...");
    	attachmentManager.delete(configQrOrderId, ModuleTypeEnum.CONFIG_QR_ORDER.val , FunctionTypeEnum.CLOSE_ORDER_PHOTO.val);
    	return ResponseEntity.ok(HttpStatus.OK.toString());
    }
    //================== CO-MENU ====================//
}
