package com.gg.gpos.sync.controller;

import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.gg.gpos.common.constant.CatalogTypeEnum;
import com.gg.gpos.common.constant.SymbolEnum;
import com.gg.gpos.controller.BaseController;
import com.gg.gpos.integration.manager.SyncManager;
import com.gg.gpos.menu.dto.CatalogDataEditDto;
import com.gg.gpos.menu.dto.CoDto;
import com.gg.gpos.menu.manager.CatalogDataEditManager;
import com.gg.gpos.menu.manager.SyncCoMenuManager;
import com.gg.gpos.res.dto.RestaurantDto;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class SyncCoMenuController extends BaseController {
	@Autowired
	private SyncCoMenuManager syncCoMenuManager;
	@Autowired
	private SyncManager syncManager;
	@Autowired
	private CatalogDataEditManager catalogDataEditManager;
	
	/*
	 * Áp dụng DANH MỤC xuống NHÀ HÀNG
	 */
	@PostMapping("/co/apply-to-res")
    public String syncCoMenuFromCatalog(@Valid CoDto coDto, HttpServletRequest request) {
		log.info("ENTERING 'APPLY CO_MENU FROM CATALOG TO RESTAURANT' METHOD...");
    	Locale locale = request.getLocale();
    	String view = "redirect:/co/sync-to-restaurant?cId=" + coDto.getId();
    	if(StringUtils.isBlank(coDto.getSelectedRestaurantCodes())) {
    		addError(request, getText("restaurant.not.selected", locale));
    		return view;
    	}
    	try {
    		if(syncCoMenuManager.checkCatalogHasCoFoodItem(coDto)){
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
				syncManager.savedSyncCoMenuFromCatalogToRestaurant(coDto.getId(), coDto.isOverride(), restaurantDtos, catalogDataEditDtos);
				addMessage(request, getText("apply.to.restaurant.sucess", request.getLocale()));
    		}else {
    			addError(request, getText("co.apply.emptyCoFoodItem", locale));
    		}
		} catch (Exception e) {
			addError(request, e.getMessage());
			log.error("ERROR: APPLY CO_MENU FROM CATALOG TO RESTAURANT EXCEPTION: {}", e);
		}
    	return view;
    }
	
	/*
	 * Áp dụng DANH MỤC từ NHÀ HÀNG
	 */
	@PostMapping("/co/applyRes")
    public String applyCoMenuFromRestaurant(@Valid CoDto coDto, HttpServletRequest request){
		log.info("ENTERING 'APPLY CO_MENU FROM RESTAURANT' METHOD...");
    	String view = "redirect:/co/form?rCode=" + coDto.getRestaurantCode();
    	Locale locale = request.getLocale();	
    	try {
    		RestaurantDto restaurantDto = restaurantManager.get(coDto.getRestaurantCode());
    		// Lấy và xóa thông tin dữ liệu đã chỉnh sửa ở danh mục
        	List<CatalogDataEditDto> catalogDataEditDtos = catalogDataEditManager.getByTypeAndCatalogId(coDto.getId(), CatalogTypeEnum.CO_MENU.val);
        	// Lưu thông tin đánh dấu trạng thái "CHỜ ĐỒNG BỘ"
			syncManager.savedSyncCoMenuFromRestaurant(coDto.getId(), coDto.isOverride(), restaurantDto, catalogDataEditDtos);
			addMessage(request, getText("apply.to.restaurant.sucess", locale));
		} catch (Exception e) {
			addError(request, e.getMessage());
			log.error("ERROR APPLY CO_MENU FROM RESTAURANT EXCEPTION: {}", e);
		}
    	return view;
    }
	
	 /*
	 * Copy dữ liệu từ NHÀ HÀNG NÀY sang NHÀ HÀNG KHÁC
	 */
	@PostMapping("/co/copyToRes")
    public String copyCoMenuFromRestaurantToRestaurants(@Valid CoDto coDto, HttpServletRequest request){
		log.info("ENTERING 'COPY CO_MENU FROM RESTAURANT TO RESTAURANTS' METHOD...");
    	String view = "redirect:/co/form?rCode=" + coDto.getRestaurantCode();
    	Locale locale = request.getLocale();
    	try {
			syncCoMenuManager.copyFromRestaurantToRestaurants(coDto);
    		addMessage(request, getText("co.copy.to.res.succes", locale));
		} catch (Exception e) {
			addError(request, e.getMessage());
			log.error("ERROR COPY CO_MENU FROM RESTAURANT TO RESTAURANTS EXCEPTION: {}", e);
		}
    	return view;
    }
}
