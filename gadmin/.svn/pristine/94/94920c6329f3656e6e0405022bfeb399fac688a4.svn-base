package com.gg.gpos.menu.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gg.gpos.controller.BaseController;
import com.gg.gpos.menu.dto.HallplanDto;
import com.gg.gpos.menu.dto.KdsPlaceDto;
import com.gg.gpos.menu.manager.HallplanManager;
import com.gg.gpos.menu.manager.KdsPlaceManager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class KdsPlaceController extends BaseController{
	@Autowired
	private KdsPlaceManager kdsPlaceManager;
	@Autowired
	private HallplanManager hallplanManager;
	
	@GetMapping("/res/kdsPlace/list")
    public ModelAndView list(@RequestParam(name="rCode", required = true) Integer rCode) {
    	log.debug("entering 'show kds place' method...");
    	ModelAndView modelAndView = new ModelAndView("res-kds-place-list");
    	KdsPlaceDto criteria = new KdsPlaceDto();
		criteria.setPage(appProperties.getDefaultPage());
		criteria.setSize(appProperties.getDefaultPageSize());
		criteria.setRestaurantCode(rCode);
		Page<KdsPlaceDto> pages = kdsPlaceManager.gets(criteria);
		modelAndView.addObject("page", pages);
		modelAndView.addObject("criteria",criteria);
		return modelAndView;
    }
	
	@PostMapping("/res/kdsPlace/list")
    public ModelAndView search(Model model, @Valid KdsPlaceDto criteria, BindingResult bindingResult){
    	log.debug("entering 'search' method...");
		ModelAndView modelAndView = new ModelAndView("res-kds-place-list");
		if(criteria.getSize() == null) {
			criteria.setPage(appProperties.getDefaultPage());
			criteria.setSize(appProperties.getDefaultPageSize());
		}
		Page<KdsPlaceDto> pages = kdsPlaceManager.gets(criteria);
		modelAndView.addObject("page", pages);
		modelAndView.addObject("criteria",criteria);
        return modelAndView;
    }
	
	@GetMapping("/res/kdsPlace/form")
    public ModelAndView show(@RequestParam(value="id", required=false) Long id,@RequestParam(value="rCode", required=true) Integer rCode) {
    	log.debug("entering 'show' method...");
    	ModelAndView modelAndView = new ModelAndView("res-kds-place-form");
    	KdsPlaceDto kdsPlace = new KdsPlaceDto();
    	if(id != null) {
    		kdsPlace = kdsPlaceManager.get(id);
    	}
    	kdsPlace.setRestaurantCode(rCode);
    	
    	modelAndView.addObject("kdsPlace", kdsPlace);		
        return modelAndView;
    }
	
	@PostMapping("/res/kdsPlace/save")
    public String save(@Valid KdsPlaceDto kdsPlace,BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {
    	log.debug("entering 'save ' method...");
        Locale locale = request.getLocale();
        String view = "res-kds-place-form";
        if (bindingResult.hasErrors()) {
        	addError(request, bindingResult.getAllErrors().toString());
        } 
        try {
        	KdsPlaceDto kdsNew = kdsPlaceManager.save(kdsPlace);
 	    	addMessage(request, getText("kds.place.updated", locale));
 	        view = "redirect:/res/kdsPlace/list?rCode=" + kdsNew.getRestaurantCode();
		} catch (Exception e) {
			addError(request, e.getMessage());
		}
	       
        
        return view;
    }
	
	//============= api =============//
	@GetMapping("/res/kdsPlace/get-hallplans")
    @ResponseBody
    public List<HallplanDto> getHallplans(@RequestParam(value="id", required = false)Long kdsPlaceId,@RequestParam(value="resCode", required = true) Integer resCode) {
    	log.debug("entering 'getHallplans' method...");
    	return hallplanManager.getByKdsPlaceIdAndResCodes(kdsPlaceId,resCode);
    }
	
	@PostMapping(value = "/kdsPlace/checkCode", produces = "application/json;charset=UTF-8")
	public @ResponseBody boolean checkCode(@RequestParam("code") String code,@RequestParam("resCode") Integer resCode, HttpServletRequest request) {
		log.debug("...Entering checkCode Transaction");
		Boolean isExistCode = false;
		try {
			if(code != null) {
				KdsPlaceDto kdsPlaceDto = kdsPlaceManager.getByCodeAndResCode(code, resCode);
				if(kdsPlaceDto != null) {
					isExistCode = true;
				}
			}
		} catch (Exception e) {
			return isExistCode;
		}
		return isExistCode;
	}
}
