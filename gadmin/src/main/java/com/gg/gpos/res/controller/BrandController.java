package com.gg.gpos.res.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gg.gpos.controller.BaseController;
import com.gg.gpos.res.manager.BrandManager;

import lombok.extern.slf4j.Slf4j;

/**
 * Generated by Speed Generator
 * 
 * @author <a href="mailto:ngtrungkien@gmail.com">Kien Nguyen</a>
 */
 @Slf4j
@Controller
public class BrandController extends BaseController {

    private BrandManager brandManager;

    @Autowired
    public void setBrandManager(BrandManager brandManager) {
        this.brandManager = brandManager;
    }

    @GetMapping("/brand/list")
    public ModelAndView list(HttpServletRequest request) throws Exception {
    	log.debug("entering 'list' method...");
    	
		ModelAndView modelAndView = new ModelAndView("brand-list");
		modelAndView.addObject("brands", brandManager.gets());

        return modelAndView;
    }
}