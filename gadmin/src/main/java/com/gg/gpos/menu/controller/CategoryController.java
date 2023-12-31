package com.gg.gpos.menu.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gg.gpos.common.constant.ReferenceDataEnum;
import com.gg.gpos.common.constant.SystemParamEnum;
import com.gg.gpos.controller.BaseController;
import com.gg.gpos.integration.manager.MasterDataSyncManager;
import com.gg.gpos.integration.manager.SyncManager;
import com.gg.gpos.menu.dto.CategoryDto;
import com.gg.gpos.menu.manager.CategoryManager;
import com.gg.gpos.reference.dto.SystemParameterDto;
import com.gg.gpos.res.dto.RestaurantDto;
import com.gg.gpos.res.manager.SyncStatusManager;

import lombok.extern.slf4j.Slf4j;

/**
 * Generated by Speed Generator
 * 
 * @author <a href="mailto:ngtrungkien@gmail.com">Kien Nguyen</a>
 */
 @Slf4j
@Controller
public class CategoryController extends BaseController {
	@Autowired
    private CategoryManager categoryManager;
    @Autowired
    private MasterDataSyncManager masterDataSyncManager;
    @Autowired
    private SyncStatusManager syncStatusManager;
    @Autowired
    private SyncManager syncManager;
  
    @GetMapping("/ref/category/list")
    public ModelAndView list() {
    	log.info("ENTERING 'LIST CATEGORY' METHOD...");
		ModelAndView modelAndView = new ModelAndView("ref/category-list");
		CategoryDto criteria = new CategoryDto();
		criteria.setPage(appProperties.getDefaultPage());
		criteria.setSize(appProperties.getDefaultPageSize());
		Page<CategoryDto> pages = categoryManager.gets(criteria);
		modelAndView.addObject("page", pages);
		modelAndView.addObject("criteria",criteria);
        return modelAndView;
    }
    
    @PostMapping("/ref/category/list")
    public ModelAndView search(Model model, @Valid CategoryDto criteria, BindingResult bindingResult){
    	log.info("ENTERING 'SEARCH CATEGORY' METHOD...");
		ModelAndView modelAndView = new ModelAndView("ref/category-list");
		if(criteria != null && criteria.getSize() == null) {
			criteria.setPage(appProperties.getDefaultPage());
			criteria.setSize(appProperties.getDefaultPageSize());
		}
		Page<CategoryDto> pages = categoryManager.gets(criteria);
		modelAndView.addObject("page", pages);
		modelAndView.addObject("criteria",criteria);
        return modelAndView;
    } 
    
    /*
     * Đồng bộ thông tin từ RK7
     */
    @GetMapping("/category/sync-from-rk7")
    public String syncFromRK7(Model model, HttpServletRequest request) {
    	log.info("ENTERING 'SYNC CATEGORY FROM RK7' METHOD...");
    	try {
    		SystemParameterDto rk7UrlParam = systemParameterManager.get(SystemParamEnum.RK7_URL.param);
        	SystemParameterDto rk7BasicAuthParam = systemParameterManager.get(SystemParamEnum.RK7_BASIC_AUTH.param);
    		List<CategoryDto> categoryDtos = masterDataSyncManager.syncCategoryFromRK7(rk7UrlParam.getParamValue(), rk7BasicAuthParam.getParamValue());
        	categoryManager.save(categoryDtos);
        	syncStatusManager.changeSttAfterSync();
        	addMessage(request, getText("category.sync.from.rk7.success", request.getLocale()));
		} catch (Exception e) {
			log.error("SYNC CATEGORY FROM RK7 EXCEPTION: {}", e);
			addError(request, e.getMessage());
		}
    	return "redirect:/ref/category/list";
    }    
        
    
    /*
     * Đồng bộ DỮ LIỆU MASTER_DATA từ NHÀ HÀNG xuống SERVER NHÀ HÀNG
     * Có thể chọn nhiều nhà hàng để đồng bộ
     */
    @GetMapping("/category/sync-to-res")
    public String syncToRestaurant(Model model, HttpServletRequest request) {
    	log.info("ENTERING 'SYNC CATEGORY TO RESTAURANT' METHOD...");
		// Lấy danh sách nhà hàng đã chọn để đồng bộ
		List<RestaurantDto> restaurantDtos = restaurantManager.gets();
		syncManager.savedSyncMasterData(restaurantDtos, ReferenceDataEnum.CATEGORY.key);
    	addMessage(request, getText("apply.to.restaurant.sucess", request.getLocale()));
    	return "redirect:/ref/category/list";
	}
 }