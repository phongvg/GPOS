package com.gg.gpos.res.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gg.gpos.controller.BaseController;
import com.gg.gpos.res.dto.ZoneDto;
import com.gg.gpos.res.manager.ZoneManager;

import lombok.extern.slf4j.Slf4j;

/**
 * Generated by Speed Generator
 * 
 * @author <a href="mailto:ngtrungkien@gmail.com">Kien Nguyen</a>
 */
 @Slf4j
@Controller
public class ZoneController extends BaseController {

    private ZoneManager zoneManager;

    @Autowired
    public void setZoneManager(ZoneManager zoneManager) {
        this.zoneManager = zoneManager;
    }

    @GetMapping("/zone/list")
    public ModelAndView list(HttpServletRequest request) throws Exception {
    	log.debug("entering 'list' method...");
    	
		ModelAndView modelAndView = new ModelAndView("zone-list");
		modelAndView.addObject("zones", zoneManager.gets());

        return modelAndView;
    }
    
    @ModelAttribute
    @GetMapping("/zone/edit/{id}")
    public ModelAndView show(@PathVariable ("id") @NotNull Long id) throws Exception {
    	log.debug("entering 'show' method...");
    
    	ModelAndView modelAndView = new ModelAndView("zone-form");
        modelAndView.addObject(zoneManager.get(id));

        return modelAndView;
    }

    @PostMapping("/zone/save")
    public String save(@Valid ZoneDto zoneDto, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("entering 'save' method...");

        Locale locale = request.getLocale();
        String view = "zone-form";
        
        if (bindingResult.hasErrors()) {
        	addError(request, bindingResult.getAllErrors().toString());
            return view;
        }

        zoneManager.save(zoneDto);
        addMessage(request, getText("zone.updated", locale));
        view = "redirect:/zone/list";

        return view;
    }
    
}