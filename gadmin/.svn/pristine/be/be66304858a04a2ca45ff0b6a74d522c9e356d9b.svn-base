package com.gg.gpos.sync.controller;

import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.gg.gpos.common.constant.CatalogTypeEnum;
import com.gg.gpos.common.constant.SymbolEnum;
import com.gg.gpos.controller.BaseController;
import com.gg.gpos.integration.manager.SyncManager;
import com.gg.gpos.menu.dto.CatalogDataEditDto;
import com.gg.gpos.menu.dto.GroupParamDto;
import com.gg.gpos.menu.manager.CatalogDataEditManager;
import com.gg.gpos.menu.manager.SyncGroupParamManager;
import com.gg.gpos.res.dto.RestaurantDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SyncGroupParamController extends BaseController {
	@Autowired
	private SyncGroupParamManager syncGroupParamManager;
	@Autowired
	private SyncManager syncManager;
	@Autowired
	private CatalogDataEditManager catalogDataEditManager;
	
	/*
	 * Áp dụng DANH MỤC xuống NHÀ HÀNG
	 */
	@PostMapping("/param/sync-full")
	public String syncGroupParamFromCatalog(HttpServletRequest request, @Valid GroupParamDto groupParamDto) {
		log.info("ENTERING 'APPLY GROUP_PARAM FROM CATALOG TO RESTAURANT' METHOD...");
		Locale locale = request.getLocale();
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
			syncManager.savedSyncGroupParamMenuFromCatalogToRestaurant(groupParamDto.getId(), groupParamDto.getOverride(), restaurantDtos, catalogDataEditDtos);
			addMessage(request, getText("apply.to.restaurant.sucess", locale));
		} catch (Exception e) {
			addError(request, e.getMessage());
			log.error("ERROR: APPLY GROUP_PARAM FROM CATALOG TO RESTAURANT EXCEPTION: {}", e);
		}
		return "redirect:/cag/group-param/sync-to-restaurant?groupParamId=" + groupParamDto.getId();
	}
    
    /*
	 * Áp dụng DANH MỤC từ NHÀ HÀNG
	 */
    @PostMapping("/groupParam/applyToRes")
	public String applyGroupParamFromRestaurant(HttpServletRequest request, @Valid GroupParamDto groupParamDto) {
    	log.info("ENTERING 'APPLY GROUP_PARAM FROM RESTAURANT' METHOD...");
		Locale locale = request.getLocale();
		try {
    		RestaurantDto restaurantDto = restaurantManager.get(groupParamDto.getRestaurantCode());
        	// Lấy và xóa thông tin dữ liệu đã chỉnh sửa ở danh mục
        	List<CatalogDataEditDto> catalogDataEditDtos = catalogDataEditManager.getByTypeAndCatalogId(groupParamDto.getId(), CatalogTypeEnum.PARAM.val);
        	// Lưu thông tin đánh dấu trạng thái "CHỜ ĐỒNG BỘ"
			syncManager.savedSyncGroupParamMenuFromRestaurant(groupParamDto.getId(), groupParamDto.getOverride(), restaurantDto, catalogDataEditDtos);
			addMessage(request, getText("apply.to.restaurant.sucess", locale));
		} catch (Exception e) {
			addError(request, e.getMessage());
			log.error("ERROR APPLY GROUP_PARAM FROM RESTAURANT EXCEPTION: {}", e);
		}
		return "redirect:/group-param/form?rCode=" + groupParamDto.getRestaurantCode();
	}
    
    /*
	 * Copy dữ liệu từ NHÀ HÀNG NÀY sang NHÀ HÀNG KHÁC
	 */
    @PostMapping("/groupParam/copyToRes")
	public String copyGroupParamFromRestaurantToRestaurants(HttpServletRequest request, @Valid GroupParamDto groupParam) {
    	log.info("ENTERING 'COPY GROUP_PARAM FROM RESTAURANT TO RESTAURANTS' METHOD...");
		Locale locale = request.getLocale();
		try {
			syncGroupParamManager.copyFromRestaurantToRestaurants(groupParam);
			addMessage(request, getText("param.sync.success", locale));
		} catch (Exception e) {
			addError(request, e.getMessage());
			log.error("ERROR COPY GROUP_PARAM FROM RESTAURANT TO RESTAURANTS EXCEPTION: {}", e);
		}
		return "redirect:/group-param/form?rCode=" + groupParam.getRestaurantCode();
	}
}
