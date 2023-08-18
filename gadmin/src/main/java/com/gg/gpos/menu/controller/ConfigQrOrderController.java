package com.gg.gpos.menu.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gg.gpos.common.constant.FunctionTypeEnum;
import com.gg.gpos.common.constant.ModuleTypeEnum;
import com.gg.gpos.common.util.UserContext;
import com.gg.gpos.controller.BaseController;
import com.gg.gpos.menu.dto.ConfigQrOrderDto;
import com.gg.gpos.menu.manager.CoManager;
import com.gg.gpos.menu.manager.ConfigQrOrderManager;
import com.gg.gpos.reference.manager.AttachmentManager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ConfigQrOrderController extends BaseController {
	@Autowired
	private ConfigQrOrderManager configQrOrderManager;
	@Autowired
	private CoManager coManager;
	@Autowired
	private AttachmentManager attachmentManager;
	
	@GetMapping("/cag/co-menu/configQrOrder/form")
    public ModelAndView showFormInCatalog(@RequestParam(value = "coId", required = true) Long coId, HttpServletRequest request) {
    	log.info("ENTERING 'SHOW FORM CONFIG_QR_ORDER IN CATALOG' METHOD...");
    	ModelAndView modelAndView = new ModelAndView("cag/co-menu/config-qr-order-form");
    	String username = UserContext.getUsername();
    	ConfigQrOrderDto configQrOrderDto = new ConfigQrOrderDto();
       	if(coId != null) {
       		configQrOrderDto = configQrOrderManager.getByCoIdAndRestaurantCodeNull(coId);
    	}
       	configQrOrderDto.setCoId(coId);
       	configQrOrderDto.setCreatedBy(username);
       	configQrOrderDto.setModifiedBy(username);
    	modelAndView.addObject("configQrOrderDto",configQrOrderDto);
    	return modelAndView;
    }
	
	@PostMapping("/cag/co-menu/configQrOrder/save")
    public String save(@Valid ConfigQrOrderDto configQrOrderDto, BindingResult bindingResult, HttpServletRequest request) {
		log.info("ENTERING 'SAVE CONFIG_QR_ORDER IN CATALOG' METHOD...");
        Locale locale = request.getLocale();
        Long coId = configQrOrderDto.getCoId();
        String view = "redirect:/cag/co-menu/configQrOrder/form?coId=" + coId;
        if (bindingResult.hasErrors()) {
        	addError(request, bindingResult.getAllErrors().toString());
        }
    	try {
    		ConfigQrOrderDto savedConfigQrOrderDto = configQrOrderManager.save(configQrOrderDto);
    		if(configQrOrderDto.getInfoPhoto() != null) {
            	attachmentManager.saveAttachment(configQrOrderDto.getInfoPhoto(), appProperties.getAttachmentPath(), appProperties.getAttachmentContextPath(), savedConfigQrOrderDto.getId(), ModuleTypeEnum.CONFIG_QR_ORDER.val, FunctionTypeEnum.INFO_PHOTO.val, null, null);
            }
        	if(configQrOrderDto.getLogoPhoto() != null) {
            	attachmentManager.saveAttachment(configQrOrderDto.getLogoPhoto(), appProperties.getAttachmentPath(), appProperties.getAttachmentContextPath(), savedConfigQrOrderDto.getId(), ModuleTypeEnum.CONFIG_QR_ORDER.val, FunctionTypeEnum.LOGO_PHOTO.val, null, null);
            }
        	if(configQrOrderDto.getCloseOrderPhoto() != null) {
            	attachmentManager.saveAttachment(configQrOrderDto.getCloseOrderPhoto(), appProperties.getAttachmentPath(), appProperties.getAttachmentContextPath(), savedConfigQrOrderDto.getId(), ModuleTypeEnum.CONFIG_QR_ORDER.val, FunctionTypeEnum.CLOSE_ORDER_PHOTO.val, null, null);
            }
        	configQrOrderManager.saveInfoSrcImage(savedConfigQrOrderDto);
        	coManager.saveAfterChangeCatalog(coId);
            addMessage(request, getText("config.qr.order.update.sucess", locale));
		} catch (Exception e) {
			addError(request, e.getMessage());
			log.error("ERROR SAVE CONFIG_QR_ORDER IN CATALOG EXCEPTION: {}", e);
		}
        return view;
    }
	
	@GetMapping("/restaurant/co-menu/configQrOrder/form")
    public ModelAndView showFormInRestaurant(@RequestParam(value = "restaurantCode", required = true) String restaurantCode) {
    	log.info("ENTERING 'SHOW FORM CONFIG_QR_ORDER IN RESTAURANT' METHOD...");
    	ModelAndView modelAndView = new ModelAndView("restaurant/co-menu/config-qr-order-form");
    	String username = UserContext.getUsername();
    	ConfigQrOrderDto configQrOrderDto = new ConfigQrOrderDto();
       	if(StringUtils.isNotBlank(restaurantCode)) {
       		configQrOrderDto = configQrOrderManager.getByRestaurantCode(restaurantCode);
    	}
       	configQrOrderDto.setRestaurantCode(restaurantCode);
       	configQrOrderDto.setCreatedBy(username);
       	configQrOrderDto.setModifiedBy(username);
    	modelAndView.addObject("configQrOrderDto",configQrOrderDto);
    	return modelAndView;
    }
	
	@PostMapping("/restaurant/co-menu/configQrOrder/save")
    public String saveConfigQrOrderInRestaurant(@Valid ConfigQrOrderDto configQrOrderDto, BindingResult bindingResult, HttpServletRequest request) {
		log.info("ENTERING 'SAVE CONFIG_QR_ORDER IN RESTAURANT' METHOD...");
        Locale locale = request.getLocale();
        String restaurantCode = configQrOrderDto.getRestaurantCode();
        String view = "redirect:/restaurant/co-menu/configQrOrder/form?restaurantCode=" + restaurantCode;
        if (bindingResult.hasErrors()) {
        	addError(request, bindingResult.getAllErrors().toString());
        }
    	try {
    		ConfigQrOrderDto savedConfigQrOrderDto = configQrOrderManager.save(configQrOrderDto);
    		if(configQrOrderDto.getInfoPhoto() != null) {
            	attachmentManager.saveAttachment(configQrOrderDto.getInfoPhoto(), appProperties.getAttachmentPath(), appProperties.getAttachmentContextPath(), savedConfigQrOrderDto.getId(), ModuleTypeEnum.CONFIG_QR_ORDER.val, FunctionTypeEnum.INFO_PHOTO.val, null, null);
            }
        	if(configQrOrderDto.getLogoPhoto() != null) {
            	attachmentManager.saveAttachment(configQrOrderDto.getLogoPhoto(), appProperties.getAttachmentPath(), appProperties.getAttachmentContextPath(), savedConfigQrOrderDto.getId(), ModuleTypeEnum.CONFIG_QR_ORDER.val, FunctionTypeEnum.LOGO_PHOTO.val, null, null);
            }
        	if(configQrOrderDto.getCloseOrderPhoto() != null) {
            	attachmentManager.saveAttachment(configQrOrderDto.getCloseOrderPhoto(), appProperties.getAttachmentPath(), appProperties.getAttachmentContextPath(), savedConfigQrOrderDto.getId(), ModuleTypeEnum.CONFIG_QR_ORDER.val, FunctionTypeEnum.CLOSE_ORDER_PHOTO.val, null, null);
            }
        	configQrOrderManager.saveInfoSrcImage(savedConfigQrOrderDto);
            addMessage(request, getText("config.qr.order.update.sucess", locale));
		} catch (Exception e) {
			addError(request, e.getMessage());
			log.error("ERROR SAVE CONFIG_QR_ORDER IN RESTAURANT EXCEPTION: {}", e);
		}
        return view;
    }
}
