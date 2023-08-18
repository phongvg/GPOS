package com.gg.gpos.sync.controller;

import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.gg.gpos.common.constant.CatalogTypeEnum;
import com.gg.gpos.common.constant.SymbolEnum;
import com.gg.gpos.common.util.UserContext;
import com.gg.gpos.controller.BaseController;
import com.gg.gpos.integration.manager.SyncManager;
import com.gg.gpos.menu.dto.CatalogDataEditDto;
import com.gg.gpos.menu.dto.SoCategoryDto;
import com.gg.gpos.menu.dto.SoDto;
import com.gg.gpos.menu.manager.CatalogDataEditManager;
import com.gg.gpos.menu.manager.SoCategoryFoodGroupManager;
import com.gg.gpos.menu.manager.SyncSoMenuManager;
import com.gg.gpos.res.dto.RestaurantDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SyncSoMenuController extends BaseController{
	@Autowired
	private SyncSoMenuManager syncSoMenuManager;
	@Autowired
	private SoCategoryFoodGroupManager soCategoryFoodGroupManager;
	@Autowired
	private SyncManager syncManager;
	@Autowired
	private CatalogDataEditManager catalogDataEditManager;
	
	/*
	 * Áp dụng DANH MỤC xuống NHÀ HÀNG
	 */
	@PostMapping("/cag/so/apply-to-res")
    public String syncSoMenuFromCatalog(@Valid SoDto so, HttpServletRequest request, HttpServletResponse response) {
		log.info("ENTERING 'APPLY SO_MENU FROM CATALOG TO RESTAURANT' METHOD...");
        Locale locale = request.getLocale();
        String view = "redirect:/cag/so/apply?soId=" + so.getId(); 
        if(so.getSelectedRestaurantCodes() != null &&  !so.getSelectedRestaurantCodes().isEmpty()) {
        	try {
        		Set<String> keys = org.springframework.util.StringUtils.commaDelimitedListToSet(so.getSelectedRestaurantCodes());
            	// Danh sách các mã nhà hàng được chọn để đồng bộ dữ liệu
        		List<String> resCodes = keys.stream().filter(key -> {
            		return !key.contains(SymbolEnum.UNDERSCORE.val);
            	}).collect(Collectors.toList());
        		// lấy danh sách nhà hàng đã chọn
            	List<RestaurantDto> restaurantDtos = restaurantManager.getRestaurantByRestaurantCodes(resCodes);
            	
            	// Lấy và xóa thông tin dữ liệu đã chỉnh sửa ở danh mục
            	List<CatalogDataEditDto> catalogDataEditDtos = catalogDataEditManager.getByTypeAndCatalogId(so.getId(), CatalogTypeEnum.SO_MENU.val);
            	
            	// Lưu thông tin đánh dấu trạng thái "CHỜ ĐỒNG BỘ"
				syncManager.savedSyncSoMenuFromCatalogToRestaurant(so.getId(), so.getOverride(), restaurantDtos, catalogDataEditDtos);
				addMessage(request, getText("apply.to.restaurant.sucess", locale));
			} catch (Exception e) {
				addError(request, e.getMessage());
				log.error("ERROR: APPLY SO_MENU FROM CATALOG TO RESTAURANT EXCEPTION: {}", e);
			}
        }
        return view;
    }
	
	/*
	 * Đồng bộ từ DANH MỤC từ NHÀ HÀNG
	 */
	@PostMapping("/res/so/apply")
    public String applySoMenuFromRestaurant(@Valid SoCategoryDto criteria, HttpServletRequest request) {
    	log.info("ENTERING 'APPLY SO_MENU FROM RESTAURANT' METHOD...");
        Locale locale = request.getLocale();
        String view = "redirect:/res/so/menu/list?resCode=" + criteria.getRestaurantCode();
    	try {
    		// lấy danh sách nhà hàng đã chọn
    		RestaurantDto restaurantDto = restaurantManager.get(criteria.getRestaurantCode());
        	
        	// Lấy và xóa thông tin dữ liệu đã chỉnh sửa ở danh mục
        	List<CatalogDataEditDto> catalogDataEditDtos = catalogDataEditManager.getByTypeAndCatalogId(criteria.getSoId(), CatalogTypeEnum.SO_MENU.val);
        	
        	// Lưu thông tin đánh dấu trạng thái "CHỜ ĐỒNG BỘ"
			syncManager.savedSyncSoMenuFromRestaurant(criteria.getSoId(), criteria.isOverride(), restaurantDto, catalogDataEditDtos);
			addMessage(request, getText("apply.to.restaurant.sucess", locale));
		} catch (Exception e) {
			addError(request, e.getMessage());
			log.error("ERROR: APPLY SO_MENU FROM RESTAURANT EXCEPTION: {}", e);
		}
        return view;
    }
	
	/*
	 * Copy dữ liệu từ NHÀ HÀNG NÀY sang NHÀ HÀNG KHÁC
	 */
	@PostMapping("/res/so/copy-to-others")
    public String copySoMenuFromRestaurantToRestaurants(@Valid SoCategoryDto criteria, HttpServletRequest request) {
		log.info("ENTERING 'COPY SO_MENU FROM RESTAURANT TO RESTAURANTS' METHOD...");
    	try {
    		syncSoMenuManager.copyFromRestaurantToRestaurants(criteria, UserContext.getUsername());
    		// Xóa các bản ghi SO_CATEGORY_FOOD_GROUP mà có FOOD_GROUP NULL
    		soCategoryFoodGroupManager.deleteSoCategoryIfHasFoodGroupNull();
        	addMessage(request, getText("restaurant.copy.to.others.success", request.getLocale()));   
		} catch (Exception e) {
			addError(request, e.getMessage());
			log.error("ERROR COPY SO_MENU FROM RESTAURANT TO RESTAURANTS EXCEPTION: {}", e);
		}
        return "redirect:/res/so/menu/list?resCode=" + criteria.getRestaurantCode();
    }
}
	