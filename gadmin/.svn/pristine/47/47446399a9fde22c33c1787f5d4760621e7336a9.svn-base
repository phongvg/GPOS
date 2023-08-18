package com.gg.gpos.controller;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.gg.gpos.common.constant.StatusEnum;
import com.gg.gpos.common.constant.SystemParamEnum;
import com.gg.gpos.integration.exception.IntegrationException;
import com.gg.gpos.integration.manager.MasterDataSyncManager;
import com.gg.gpos.integration.manager.RestaurantSyncManager;
import com.gg.gpos.menu.dto.CategoryDto;
import com.gg.gpos.menu.dto.CurrencyDto;
import com.gg.gpos.menu.dto.CurrencyRateDto;
import com.gg.gpos.menu.dto.EmployeeDto;
import com.gg.gpos.menu.dto.FoodItemDto;
import com.gg.gpos.menu.dto.GuestTypeDto;
import com.gg.gpos.menu.dto.HallplanDto;
import com.gg.gpos.menu.dto.ModiGroupDto;
import com.gg.gpos.menu.dto.ModiSchemeDetailDto;
import com.gg.gpos.menu.dto.ModiSchemeDto;
import com.gg.gpos.menu.dto.ModifierDto;
import com.gg.gpos.menu.dto.OrderCategoryDto;
import com.gg.gpos.menu.dto.OrderTypeDto;
import com.gg.gpos.menu.dto.OrderVoidDto;
import com.gg.gpos.menu.dto.RestaurantMasterDto;
import com.gg.gpos.menu.dto.TableKitchenDto;
import com.gg.gpos.menu.manager.CategoryManager;
import com.gg.gpos.menu.manager.CurrencyManager;
import com.gg.gpos.menu.manager.CurrencyRateManager;
import com.gg.gpos.menu.manager.EmployeeManager;
import com.gg.gpos.menu.manager.FoodItemManager;
import com.gg.gpos.menu.manager.GuestTypeManager;
import com.gg.gpos.menu.manager.HallplanManager;
import com.gg.gpos.menu.manager.ModiGroupManager;
import com.gg.gpos.menu.manager.ModiSchemeDetailManager;
import com.gg.gpos.menu.manager.ModiSchemeManager;
import com.gg.gpos.menu.manager.ModifierManager;
import com.gg.gpos.menu.manager.OrderCategoryManager;
import com.gg.gpos.menu.manager.OrderTypeManager;
import com.gg.gpos.menu.manager.OrderVoidManager;
import com.gg.gpos.menu.manager.RestaurantMasterManager;
import com.gg.gpos.menu.manager.TableKitchenManager;
import com.gg.gpos.reference.dto.SystemParameterDto;
import com.gg.gpos.reference.manager.SystemParameterManager;
import com.gg.gpos.res.dto.RestaurantDto;
import com.gg.gpos.res.manager.RestaurantManager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableScheduling
@Controller
public class HomeController extends BaseController{
	
	@Autowired
	private CategoryManager categoryManager;
	@Autowired
	private CurrencyManager currencyManager;
	@Autowired
	private CurrencyRateManager currencyRateManager;
	@Autowired
	private FoodItemManager foodItemManager;
	@Autowired
	private HallplanManager hallPlanManager;
	@Autowired
	private ModifierManager modifierManager;
	@Autowired
	private ModiGroupManager modiGroupManager;
	@Autowired
	private ModiSchemeManager modiSchemeManager;
	@Autowired
	private ModiSchemeDetailManager modiSchemeDetailManager;
	@Autowired
	private OrderCategoryManager orderCategoryManager;
	@Autowired
	private OrderTypeManager orderTypeManager;
	@Autowired
	private OrderVoidManager orderVoidManager;
	@Autowired
	private TableKitchenManager tableKitchenManager;
	@Autowired
	private GuestTypeManager guestTypeManager;
	@Autowired
	private EmployeeManager employeeManager;
	@Autowired
	private RestaurantMasterManager restaurantMasterManager;
	@Autowired
	private SystemParameterManager systemParameterManager;
	@Autowired
	private MasterDataSyncManager masterDataSyncManager;
	@Autowired
	private RestaurantSyncManager restaurantSyncManager;
	@Autowired
	private RestaurantManager restaurantManager;
	
	@GetMapping(value = {"/", "home"})
	public String home(Model model) {
		log.info("Entering home() ... ");
		String view = "home";
		model.addAttribute("countRestaurant", restaurantManager.countRestaurant());
		model.addAttribute("countRestaurantActive", restaurantManager.countRestaurantByOnOffline(StatusEnum.ACTIVE.status));
		model.addAttribute("countRestaurantInactive", restaurantManager.countRestaurantByOnOffline(StatusEnum.INACTIVE.status));
		return view;
	}
	
	@GetMapping("all/sync-to-res")
	public ModelAndView syncToRes() {
		ModelAndView modelAndView = new ModelAndView("ref-form-sync");
		modelAndView.addObject("restaurant", new RestaurantDto());
		return modelAndView;
	}
	
	@GetMapping("/all/sync-from-rk7")
	public String synncFromRK7(Model model, HttpServletRequest request) throws IntegrationException {
		
		try {
			SystemParameterDto systemParameter = systemParameterManager.get(SystemParamEnum.REFERENCE_DATA_URL.param);
			Map<String, Object> map = restaurantSyncManager.syncRestaurantFromServer(systemParameter.getParamValue());		
			restaurantManager.syncFromServer(map);
			
			SystemParameterDto rk7UrlParam = systemParameterManager.get(SystemParamEnum.RK7_URL.param);
			SystemParameterDto rk7BasicAuthParam = systemParameterManager.get(SystemParamEnum.RK7_BASIC_AUTH.param);
			
			List<CategoryDto> categoryDtos = masterDataSyncManager.syncCategoryFromRK7(rk7UrlParam.getParamValue(), rk7BasicAuthParam.getParamValue());
			List<CurrencyDto> currencyDtos = masterDataSyncManager.syncCurrencyFromRK7(rk7UrlParam.getParamValue(), rk7BasicAuthParam.getParamValue());
			List<CurrencyRateDto> currencyRateDtos = masterDataSyncManager.syncCurrencyRateFromRK7(rk7UrlParam.getParamValue(), rk7BasicAuthParam.getParamValue());		
			List<FoodItemDto> foodItemDtos = masterDataSyncManager.syncMenuItemFromRK7(rk7UrlParam.getParamValue(), rk7BasicAuthParam.getParamValue());
			List<HallplanDto> hallplanDtos = masterDataSyncManager.syncHallplanFromRK7(rk7UrlParam.getParamValue(), rk7BasicAuthParam.getParamValue());
			List<ModifierDto> modifierDtos = masterDataSyncManager.syncModifierFromRK7(rk7UrlParam.getParamValue(), rk7BasicAuthParam.getParamValue());
			List<ModiGroupDto> modiGroupDtos = masterDataSyncManager.syncModiGroupFromRK7(rk7UrlParam.getParamValue(), rk7BasicAuthParam.getParamValue());
			List<ModiSchemeDto> modiSchemeDtos = masterDataSyncManager.syncModiSchemeFromRK7(rk7UrlParam.getParamValue(), rk7BasicAuthParam.getParamValue());
			List<ModiSchemeDetailDto> modiSchemeDetailDtos = masterDataSyncManager.syncModiSchemeDetailFromRK7(rk7UrlParam.getParamValue(), rk7BasicAuthParam.getParamValue());
			List<OrderCategoryDto> orderCategoryDtos = masterDataSyncManager.syncOrderCategoryFromRK7(rk7UrlParam.getParamValue(), rk7BasicAuthParam.getParamValue()) ;
			List<OrderTypeDto> orderTypeDtos = masterDataSyncManager.syncOrderTypeFromRK7(rk7UrlParam.getParamValue(), rk7BasicAuthParam.getParamValue());
			List<OrderVoidDto> orderVoidDtos = masterDataSyncManager.syncOrderVoidFromRK7(rk7UrlParam.getParamValue(), rk7BasicAuthParam.getParamValue());
			List<TableKitchenDto> tableKitchenDtos = masterDataSyncManager.syncTableFromRK7(rk7UrlParam.getParamValue(), rk7BasicAuthParam.getParamValue());
			List<GuestTypeDto> guestTypeDtos = masterDataSyncManager.syncGuestTypeFromRK7(rk7UrlParam.getParamValue(), rk7BasicAuthParam.getParamValue());	
			List<EmployeeDto> employeeDtos = masterDataSyncManager.syncEmployeeFromRK7(rk7UrlParam.getParamValue(), rk7BasicAuthParam.getParamValue());
			List<RestaurantMasterDto> restaurantMasterDtos = masterDataSyncManager.syncRestaurantMasterFromRK7(rk7UrlParam.getParamValue(), rk7BasicAuthParam.getParamValue());
			
			categoryManager.save(categoryDtos);
			currencyManager.save(currencyDtos);
			currencyRateManager.save(currencyRateDtos);
			foodItemManager.save(foodItemDtos);
			hallPlanManager.save(hallplanDtos);
			modifierManager.save(modifierDtos);
			modiGroupManager.save(modiGroupDtos);
			modiSchemeManager.save(modiSchemeDtos);
			modiSchemeDetailManager.save(modiSchemeDetailDtos);
			orderCategoryManager.save(orderCategoryDtos);
			orderTypeManager.save(orderTypeDtos);
			orderVoidManager.save(orderVoidDtos);
			tableKitchenManager.save(tableKitchenDtos);
			guestTypeManager.save(guestTypeDtos);
			employeeManager.save(employeeDtos);
			restaurantMasterManager.save(restaurantMasterDtos);
			addMessage(request, getText("all.sync.from.rk7.success",request.getLocale()));
		} catch (Exception e) {
			addError(request, e.getMessage());
		}
		
		return "redirect:/";
		
	}
    
}
