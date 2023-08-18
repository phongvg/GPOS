package com.gg.gpos.menu.controller;

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
import org.springframework.web.servlet.ModelAndView;

import com.gg.gpos.controller.BaseController;
import com.gg.gpos.menu.dto.KitchenTypeDto;
import com.gg.gpos.menu.manager.KitchenTypeManager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class KitchenTypeController  extends BaseController{
	@Autowired
	private KitchenTypeManager kitchenTypeManager;
	
	@GetMapping("/res/kitchenType/list")
    public ModelAndView list(@RequestParam(name="rCode", required = true) Integer rCode,HttpServletRequest request) throws Exception {
    	log.debug("entering 'list' method...");
    	
		ModelAndView modelAndView = new ModelAndView("res-kitchen-type-list");
		KitchenTypeDto criteria = new KitchenTypeDto();
		criteria.setPage(appProperties.getDefaultPage());
		criteria.setSize(appProperties.getDefaultPageSize());
		criteria.setRestaurantCode(rCode);
		Page<KitchenTypeDto> pages = kitchenTypeManager.gets(criteria);
		modelAndView.addObject("page", pages);
		modelAndView.addObject("criteria",criteria);

        return modelAndView;
    }

    @PostMapping("/res/kitchenType/list")
    public ModelAndView paging(Model model, @Valid KitchenTypeDto criteria, BindingResult bindingResult, HttpServletRequest request) throws Exception {
    	log.debug("entering 'paging' method...");
    	
		ModelAndView modelAndView = new ModelAndView("res-kitchen-type-list");
		if (criteria.getSize() == null) {
			criteria.setPage(appProperties.getDefaultPage());
			criteria.setSize(appProperties.getDefaultPageSize());
		}
		
		Page<KitchenTypeDto> pages = kitchenTypeManager.gets(criteria);
		modelAndView.addObject("page", pages);
		modelAndView.addObject("criteria", criteria);
		

        return modelAndView;
    }
    
    @GetMapping("/res/kitchenType/form")
    public ModelAndView show(@RequestParam(value="id", required=false) Long id,@RequestParam(value="rCode", required=true) Integer rCode) {
    	log.debug("entering 'show' method...");
    
    	ModelAndView modelAndView = new ModelAndView("res-kitchen-type-form");
    	KitchenTypeDto kitchenType = new KitchenTypeDto();
    	if(id != null) {
    		kitchenType = kitchenTypeManager.get(id);
    	}
    	kitchenType.setRestaurantCode(rCode);    	
    	modelAndView.addObject("kitchenType", kitchenType);

        return modelAndView;
    }

    @PostMapping("/res/kitchenType/save")
    public String save(@Valid KitchenTypeDto kitchenTypeDto, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("entering 'save' method...");

        Locale locale = request.getLocale();
        String view = "redirect:/res/kitchenType/form";        
        if (bindingResult.hasErrors()) {
        	addError(request, bindingResult.getAllErrors().toString());
            return view;
        }
        try {
        	 kitchenTypeManager.save(kitchenTypeDto);
             addMessage(request, getText("kitchenType.updated", locale));
		} catch (Exception e) {
			addError(request, e.getMessage());
		}
       
        view = "redirect:/res/kitchenType/list?rCode=" + kitchenTypeDto.getRestaurantCode();
        return view;
    }
    
    @GetMapping("/res/kitchenType/delete")
    public String delete(@RequestParam(name="id", required = true) Long id, HttpServletRequest request) {
    	log.debug("entering 'delete' method...");
    	Locale locale = request.getLocale();
    	String view;
    	KitchenTypeDto kitchenType = kitchenTypeManager.get(id);
    	view = "redirect:/res/kitchenType/list?rCode="+kitchenType.getRestaurantCode();

    	try {
    		kitchenTypeManager.delete(kitchenType);
        	addMessage(request, getText("area.deleted", locale));
		} catch (Exception e) {
			addError(request, e.getMessage());
		}
    	
    	
    	return view;
    }
}
