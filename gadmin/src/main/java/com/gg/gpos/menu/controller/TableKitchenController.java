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
import com.gg.gpos.menu.dto.TableKitchenDto;
import com.gg.gpos.menu.manager.TableKitchenManager;
import com.gg.gpos.reference.dto.SystemParameterDto;
import com.gg.gpos.res.dto.RestaurantDto;
import com.gg.gpos.res.manager.SyncStatusManager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TableKitchenController extends BaseController {
	@Autowired
	private  TableKitchenManager tableKitchenManager;
    @Autowired
    private MasterDataSyncManager masterDataSyncManager;
    @Autowired
	private SyncManager syncManager;
    @Autowired
    private SyncStatusManager syncStatusManager;
    
    @GetMapping("/ref/tableKitchen/list")
    public ModelAndView list() {
    	log.info("ENTERING 'LIST TABLE-KITCHEN' METHOD...");
    	ModelAndView modelAndView = new ModelAndView("ref/table-kitchen-list");
    	TableKitchenDto criteria = new TableKitchenDto();
    	criteria.setPage(appProperties.getDefaultPage());
    	criteria.setSize(appProperties.getDefaultPageSize());
    	Page<TableKitchenDto> pages = tableKitchenManager.gets(criteria);
    	modelAndView.addObject("page", pages);
    	modelAndView.addObject("criteria", criteria);
    	
    	return modelAndView;
    }
    
    @PostMapping("/ref/tableKitchen/list")
    public ModelAndView search(Model model, @Valid TableKitchenDto criteria, BindingResult bindingResult) {
    	log.info("ENTERING 'SEARCH TABLE-KITCHEN' METHOD...");
    	ModelAndView modelAndView = new ModelAndView("ref/table-kitchen-list");
    	if (criteria != null && criteria.getSize() == null) {
			criteria.setPage(appProperties.getDefaultPage());
			criteria.setSize(appProperties.getDefaultPageSize());
		}
    	Page<TableKitchenDto> pages = tableKitchenManager.gets(criteria);
    	modelAndView.addObject("page", pages);
    	modelAndView.addObject("criteria",criteria);
        return modelAndView;
    }
    
    /*
     * Đồng bộ thông tin từ RK7
     */
    @GetMapping("/tableKitchen/sync-from-rk7")
    public String syncFromRK7(Model model, HttpServletRequest request) {
    	log.info("ENTERING 'SYNC TABLE_KITCHEN FROM RK7' METHOD...");
    	try {
    		SystemParameterDto gatewayUrlParam = systemParameterManager.get(SystemParamEnum.API_GATEWAY_URL.param);
        	SystemParameterDto rk7PatternParam = systemParameterManager.get(SystemParamEnum.RK7_PATTERN.param);
        	SystemParameterDto rk7BasicAuthParam = systemParameterManager.get(SystemParamEnum.RK7_BASIC_AUTH.param);
        	String rk7Url = gatewayUrlParam.getParamValue() + rk7PatternParam.getParamValue();
        	
        	List<TableKitchenDto> tableKitchenDtos = masterDataSyncManager.syncTableFromRK7(rk7Url, rk7BasicAuthParam.getParamValue());
        	tableKitchenManager.save(tableKitchenDtos);
        	syncStatusManager.changeSttAfterSync();
        	addMessage(request, getText("hallplan.sync.from.rk7.success", request.getLocale()));
		} catch (Exception e) {
			addError(request, e.getMessage());
			log.error("SYNC TABLE_KITCHEN FROM RK7 EXCEPTION: {}", e);
		}
    	
    	return "redirect:/ref/tableKitchen/list";
    }    
    
    /*
     * Đồng bộ DỮ LIỆU MASTER_DATA từ NHÀ HÀNG xuống SERVER NHÀ HÀNG
     * Có thể chọn nhiều nhà hàng để đồng bộ
     */
    @GetMapping("/tableKitchen/sync-to-res")
    public String syncToRestaurant(Model model, HttpServletRequest request) {
    	log.info("ENTERING 'SYNC TABLE_KITCHEN TO RESTAURANT' METHOD...");
		// Lấy danh sách nhà hàng đã chọn để đồng bộ
		List<RestaurantDto> restaurantDtos = restaurantManager.gets();
		syncManager.savedSyncMasterData(restaurantDtos, ReferenceDataEnum.TABLE_KITCHEN.key);
    	addMessage(request, getText("apply.to.restaurant.sucess", request.getLocale()));
    	return "redirect:/ref/tableKitchen/list";
	}
}
