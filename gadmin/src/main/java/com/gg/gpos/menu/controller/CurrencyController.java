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
import com.gg.gpos.menu.dto.CurrencyDto;
import com.gg.gpos.menu.manager.CurrencyManager;
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
public class CurrencyController extends BaseController {
	@Autowired
    private CurrencyManager currencyManager;
    @Autowired
    private MasterDataSyncManager masterDataSyncManager;
    @Autowired
    private SyncStatusManager syncStatusManager;
    @Autowired
    private SyncManager syncManager;
  
    @GetMapping("/ref/currency/list")
    public ModelAndView list() {
    	log.info("ENTERING 'LIST CURRENCY' METHOD...");
		ModelAndView modelAndView = new ModelAndView("ref/currency-list");
		CurrencyDto criteria = new CurrencyDto();
		criteria.setPage(appProperties.getDefaultPage());
		criteria.setSize(appProperties.getDefaultPageSize());
		Page<CurrencyDto> pages = currencyManager.gets(criteria);
		modelAndView.addObject("page", pages);
		modelAndView.addObject("criteria",criteria);
        return modelAndView;
    }
    
    @PostMapping("/ref/currency/list")
    public ModelAndView search(Model model, @Valid CurrencyDto criteria, BindingResult bindingResult){
    	log.info("ENTERING 'SEARCH CURRENCY' METHOD...");
		ModelAndView modelAndView = new ModelAndView("ref/currency-list");
		if(criteria != null && criteria.getSize() == null) {
			criteria.setPage(appProperties.getDefaultPage());
			criteria.setSize(appProperties.getDefaultPageSize());
		}
		Page<CurrencyDto> pages = currencyManager.gets(criteria);
		modelAndView.addObject("page", pages);
		modelAndView.addObject("criteria",criteria);
        return modelAndView;
    }
    
    /*
     * Đồng bộ thông tin từ RK7
     */
	@GetMapping("/currency/sync-from-rk7")
    public String syncFromRK7(Model model, HttpServletRequest request) {
		log.info("ENTERING 'SYNC CURRENCY FROM RK7' METHOD...");
		try {
			SystemParameterDto rk7UrlParam = systemParameterManager.get(SystemParamEnum.RK7_URL.param);
	    	SystemParameterDto rk7BasicAuthParam = systemParameterManager.get(SystemParamEnum.RK7_BASIC_AUTH.param);
	    	List<CurrencyDto> currencyDtos = masterDataSyncManager.syncCurrencyFromRK7(rk7UrlParam.getParamValue(), rk7BasicAuthParam.getParamValue());
	    	currencyManager.save(currencyDtos);
	    	syncStatusManager.changeSttAfterSync();
	    	addMessage(request, getText("currency.sync.from.rk7.success", request.getLocale()));
		} catch (Exception e) {
			addError(request, e.getMessage());
			log.error("SYNC CURRENCY FROM RK7 EXCEPTION: {}", e);
		}
    	return "redirect:/ref/currency/list";
    }   
	
	/*
     * Đồng bộ DỮ LIỆU MASTER_DATA từ NHÀ HÀNG xuống SERVER NHÀ HÀNG
     * Có thể chọn nhiều nhà hàng để đồng bộ
     */
    @GetMapping("/currency/sync-to-res")
    public String syncToRestaurant(Model model, HttpServletRequest request) {
    	log.info("ENTERING 'SYNC CURRENCY TO RESTAURANT' METHOD...");
		// Lấy danh sách nhà hàng đã chọn để đồng bộ
		List<RestaurantDto> restaurantDtos = restaurantManager.gets();
		syncManager.savedSyncMasterData(restaurantDtos, ReferenceDataEnum.CURRENCY.key);
    	addMessage(request, getText("apply.to.restaurant.sucess", request.getLocale()));
    	return "redirect:/ref/currency/list";
	}
}