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
import com.gg.gpos.menu.dto.MenuTypeDto;
import com.gg.gpos.menu.manager.MenuTypeManager;

import lombok.extern.slf4j.Slf4j;

/**
 * Generated by Speed Generator
 * 
 * @author <a href="mailto:ngtrungkien@gmail.com">Kien Nguyen</a>
 */
 @Slf4j
@Controller
public class MenuTypeController extends BaseController {
	 @Autowired
    private MenuTypeManager menuTypeManager;

    @GetMapping("/ref/menuType/list")
    public ModelAndView list(HttpServletRequest request) throws Exception {
    	log.info("ENTERING 'LIST MENU-TYPE' METHOD...");
		ModelAndView modelAndView = new ModelAndView("ref/menu-type-list");
		MenuTypeDto criteria = new MenuTypeDto();
		criteria.setPage(appProperties.getDefaultPage());
		criteria.setSize(appProperties.getDefaultPageSize());
		Page<MenuTypeDto> pages = menuTypeManager.gets(criteria);
		modelAndView.addObject("page", pages);
		modelAndView.addObject("criteria",criteria);

        return modelAndView;
    }

    @PostMapping("/ref/menuType/list")
    public ModelAndView search(Model model, @Valid MenuTypeDto criteria, BindingResult bindingResult, HttpServletRequest request) throws Exception {
    	log.info("ENTERING 'SEARCH MENU-TYPE' METHOD...");
		ModelAndView modelAndView = new ModelAndView("ref/menu-type-list");
		if (criteria.getSize() == null) {
			criteria.setPage(appProperties.getDefaultPage());
			criteria.setSize(appProperties.getDefaultPageSize());
		}
		Page<MenuTypeDto> pages = menuTypeManager.gets(criteria);
		modelAndView.addObject("page", pages);
		modelAndView.addObject("criteria", criteria);
        return modelAndView;
    }
    
    @GetMapping("/ref/menuType/form")
    public ModelAndView show(@RequestParam(value="id", required=false) Long id) {
    	log.info("ENTERING 'SHOW MENU-TYPE' METHOD...");
    	ModelAndView modelAndView = new ModelAndView("ref/menu-type-form");
    	try {
    		if (id != null) {
        		modelAndView.addObject("menuType", menuTypeManager.get(id));	
        	} else {
        		modelAndView.addObject("menuType", new MenuTypeDto());
        	}
		} catch (Exception e) {
			log.error("ERROR SHOW FORM MENU_TYPE: EXCEPTION: {}", e);
		}	
        return modelAndView;
    }

    @PostMapping("/ref/menuType/save")
    public String save(@Valid MenuTypeDto menuTypeDto, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	log.info("ENTERING 'SAVE MENU-TYPE' METHOD...");
        Locale locale = request.getLocale();
        String view = "redirect:/ref/menuType/form";        
        try {
        	if (!menuTypeManager.isUsedCode(menuTypeDto.getCode()) || menuTypeDto.getId() != null) {
        		 menuTypeManager.save(menuTypeDto);
                 addMessage(request, getText("menuType.updated", locale));
                view = "redirect:/ref/menuType/list";
            }else {
            	addError(request, getText("menuType.error", locale));
            }
		} catch (Exception e) {
			addError(request, e.getMessage());
			log.error("ERROR SAVE MENU_TYPE: EXCEPTION: {}", e);
		}
        return view;
    }

    @GetMapping("/ref/menuType/del/{id}")
    public ModelAndView del(@PathVariable(value="id", required=true) Long id, HttpServletRequest request) {
    	log.info("ENTERING 'DELETE MENU-TYPE' METHOD...");
    	ModelAndView modelAndView = new ModelAndView("redirect:/ref/menuType/list");
    	if (id != null) {
    		// Kiểm tra xem MenuType này đã được sử dụng trong FoodGroup chưa
    		if (menuTypeManager.isUsingInFoodGroup(id)) {
    			addError(request, getText("menuType.delete.fail", request.getLocale()));
    		} else {
    			menuTypeManager.delete(id);
    			addMessage(request, getText("menuType.delete.success", request.getLocale()));
    		}
    	} else {
    		addError(request, getText("menuType.not.found", request.getLocale()));
    	}
        return modelAndView;
    }
}