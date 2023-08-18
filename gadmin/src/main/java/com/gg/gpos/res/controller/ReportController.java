package com.gg.gpos.res.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gg.gpos.controller.BaseController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ReportController extends BaseController {
	
	 @GetMapping("/reportMenu/list")
	    public ModelAndView listReport(HttpServletRequest request, @RequestParam(name = "rCode", required = true) Integer rCode) {
	    	log.debug("entering 'list' method...");
	    	
			ModelAndView modelAndView = new ModelAndView("report-menu-list");
			/*modelAndView.addObject("page", deviceDisplayManager.gets());*/
			modelAndView.addObject("rCode", rCode);
			
	        return modelAndView;
	    }
	 
	 @GetMapping("/reportOrder/list")
	    public ModelAndView listReportOrder(HttpServletRequest request, @RequestParam(name = "rCode", required = true) Integer rCode) {
	    	log.debug("entering 'list' method...");
	    	
			ModelAndView modelAndView = new ModelAndView("report-order-list");
			/*modelAndView.addObject("page", deviceDisplayManager.gets());*/
			modelAndView.addObject("rCode", rCode);

	        return modelAndView;
	    }
}
