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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gg.gpos.controller.BaseController;
import com.gg.gpos.menu.dto.KaloGroupDto;
import com.gg.gpos.menu.manager.KaloGroupManager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class KaloGroupController extends BaseController{
	@Autowired
	private KaloGroupManager kaloGroupManager;
	
	@GetMapping("/ref/kaloGroup/list")
    public ModelAndView list(HttpServletRequest request) throws Exception {
		log.info("ENTERING 'LIST KALO-GROUP' METHOD...");
		ModelAndView modelAndView = new ModelAndView("ref/kalo-group-list");
		KaloGroupDto criteria = new KaloGroupDto();
		criteria.setPage(appProperties.getDefaultPage());
		criteria.setSize(appProperties.getDefaultPageSize());
		Page<KaloGroupDto> pages = kaloGroupManager.gets(criteria);
		modelAndView.addObject("page", pages);
		modelAndView.addObject("criteria",criteria);
        return modelAndView;
    }

    @PostMapping("/ref/kaloGroup/list")
    public ModelAndView search(Model model, @Valid KaloGroupDto criteria, BindingResult bindingResult, HttpServletRequest request) throws Exception {
    	log.info("ENTERING 'SEARCH KALO-GROUP' METHOD...");
		ModelAndView modelAndView = new ModelAndView("ref/kalo-group-list");
		if (criteria.getSize() == null) {
			criteria.setPage(appProperties.getDefaultPage());
			criteria.setSize(appProperties.getDefaultPageSize());
		}
		Page<KaloGroupDto> pages = kaloGroupManager.gets(criteria);
		modelAndView.addObject("page", pages);
		modelAndView.addObject("criteria", criteria);
        return modelAndView;
    }
    
    @GetMapping("/ref/kaloGroup/form")
    public ModelAndView show(@RequestParam(value="id", required=false) Long id) {
    	log.info("ENTERING 'SHOW KALO-GROUP' METHOD...");
    	ModelAndView modelAndView = new ModelAndView("ref/kalo-group-form");
    	try {
    		if (id != null) {
        		modelAndView.addObject("kaloGroup", kaloGroupManager.get(id));	
        	} else {
        		modelAndView.addObject("kaloGroup", new KaloGroupDto());
        	}
		} catch (Exception e) {
			log.error("ERROR SHOW FORM KALO_GROUP: EXCEPTION: {}", e);
		}
        return modelAndView;
    }

    @PostMapping("/ref/kaloGroup/save")
    public String save(@Valid KaloGroupDto kaloGroupDto, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	log.info("ENTERING 'SAVE KALO-GROUP' METHOD...");
        Locale locale = request.getLocale();
        String view = "redirect:/ref/kaloGroup/form";  
        try {
        	if (!kaloGroupManager.isUsedCode(kaloGroupDto.getCode()) || kaloGroupDto.getId() != null) {
            	kaloGroupManager.save(kaloGroupDto);
                addMessage(request, getText("kaloGroup.updated", locale));
                view = "redirect:/ref/kaloGroup/list";
            }else {
            	addError(request, getText("kaloGroup.error", locale));
            }
		} catch (Exception e) {
			addError(request, e.getMessage());
			log.error("ERROR SAVE KALO_GROUP: EXCEPTION: {}", e);
		}
        return view;
    }

    @GetMapping("/ref/kaloGroup/del/{id}")
    public ModelAndView del(@PathVariable(value="id", required=true) Long id, HttpServletRequest request) {
    	log.info("ENTERING 'DELETE KALO-GROUP' METHOD...");
    	ModelAndView modelAndView = new ModelAndView("redirect:/ref/kaloGroup/list");
    	if (id != null) {
    		// Kiểm tra xem KaloGroup này đã được sử dụng trong CoFoodItem chưa
    		if (kaloGroupManager.isUsingInCoFoodItem(id)) {
    			addError(request, getText("kaloGroup.delete.fail", request.getLocale()));
    		} else {
    			kaloGroupManager.delete(id);
    			addMessage(request, getText("kaloGroup.delete.success", request.getLocale()));
    		}
    	} else {
    		addError(request, getText("kaloGroup.not.found", request.getLocale()));
    	}
        return modelAndView;
    }
}
