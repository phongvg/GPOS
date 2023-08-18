package com.gg.gpos.menu.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gg.gpos.controller.BaseController;
import com.gg.gpos.menu.dto.DeviceDto;
import com.gg.gpos.menu.dto.TableKitchenDto;
import com.gg.gpos.menu.manager.DeviceManager;
import com.gg.gpos.menu.manager.HallplanManager;
import com.gg.gpos.menu.manager.TableKitchenManager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class DeviceController extends BaseController{
	
	@Autowired
	private DeviceManager deviceManager;
	@Autowired
	private HallplanManager hallplanManager;
	@Autowired
	private TableKitchenManager tableKitchenManager;
	
	@GetMapping("restaurant/device")
	public ModelAndView getDevice(@RequestParam(value="rCode",required = true)Integer rCode) {
		ModelAndView modelAndView = new ModelAndView("res-device-form");
		DeviceDto device = new DeviceDto();
		device.setRestaurantCode(rCode);
		modelAndView.addObject("hallplans", hallplanManager.gets(restaurantManager.get(rCode)));
		modelAndView.addObject("device", device);
		return modelAndView;
	}
	
	
	@PostMapping("/restaurant/device/save")
	public String save(@Valid DeviceDto deviceDto, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {
		log.debug("entering 'apply to restaurant' method...");
    	String view = "res-device-form";
    	Locale locale = request.getLocale();
    	if(bindingResult.hasErrors()) {
    		addError(request, bindingResult.getAllErrors().toString());
    		return view;
    	}
    	try {
    		deviceManager.save(deviceDto);
    		addMessage(request, getText("device.update.success", locale));
		} catch (Exception e) {
			addError(request, getText("device.update.fail", locale));
		}
    	view = "redirect:/restaurant/device?rCode=" + deviceDto.getRestaurantCode();
    	return view;
	}
	
	//============= api ====================//
	@GetMapping("/device/check-deviceId")
    @ResponseBody
	public DeviceDto checkDeviceId(@RequestParam("deviceId") Long deviceId,@RequestParam(value="tableId",required = false) Long tableId, HttpServletRequest request) {
		log.debug("...Entering checkDeviceId Transaction");
		DeviceDto device = new DeviceDto();
		try {
			device = deviceManager.get(deviceId,tableId);
			if(device != null) {
				device.setCheck(true);
			}
		} catch (Exception e) {
			return device;
		}
		return device;
	}
	
	@GetMapping("/api/device/get-existing-device-id")
    @ResponseBody
	public List<DeviceDto> loadDeviceExisting(@RequestParam("resCode") Integer resCode, HttpServletRequest request) {
		log.debug("...Entering loadDeviceExisting Transaction");
		List<DeviceDto> devices = null;
		try {
			if(resCode != null) {
				devices = deviceManager.gets(resCode);
			}
		} catch (Exception e) {
			return devices;
		}
		return devices;
	}
	
	@GetMapping("/api/device/load-table")
	@ResponseBody
	public List<TableKitchenDto> loadTableKitchen(@RequestParam("hallplanId") Long hallPlanId, HttpServletRequest request) {
		log.debug("...Entering loadTableKitchen Transaction");
		List<TableKitchenDto> tableKitchens = null;
		try {
			if(hallPlanId != null) {
				tableKitchens = tableKitchenManager.getByHallPlanId(hallPlanId.intValue());
			}
		} catch (Exception e) {
			return tableKitchens;
		}
		return tableKitchens;
	}
}
