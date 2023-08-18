package com.gg.gpos.menu.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gg.gpos.common.util.UserContext;
import com.gg.gpos.controller.BaseController;
import com.gg.gpos.menu.dto.CatalogApplyToRestaurantDto;
import com.gg.gpos.menu.dto.GroupParamDto;
import com.gg.gpos.menu.manager.CatalogApplyToRestaurantManager;
import com.gg.gpos.menu.manager.GroupParamManager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class GroupParamController extends BaseController {
	@Autowired
	private GroupParamManager groupParamManager;
    @Autowired
    private CatalogApplyToRestaurantManager catalogApplyToRestaurantManager;
	
    
    //============= Controller GROUP-PARAM in CATALOG  ================//
    @GetMapping("/cag/group-param/list")
    public ModelAndView list(){
    	log.info("ENTERING 'LIST GROUP-PARAM' METHOD...");
		ModelAndView modelAndView = new ModelAndView("cag/group-param/group-param-list");
		GroupParamDto criteria = new GroupParamDto();
		criteria.setPage(appProperties.getDefaultPage());
		criteria.setSize(appProperties.getDefaultPageSize());
		Page<GroupParamDto> pages = groupParamManager.gets(criteria);
		modelAndView.addObject("page", pages);
		modelAndView.addObject("criteria",criteria);
        return modelAndView;
    }
    
    @PostMapping("/cag/group-param/list")
    public ModelAndView search (@Valid GroupParamDto criteria) {
    	log.info("ENTERING 'SEARCH GROUP-PARAM' METHOD...");
		ModelAndView modelAndView = new ModelAndView("cag/group-param/group-param-list");
		if (criteria != null && criteria.getSize() == null) {
			criteria.setPage(appProperties.getDefaultPage());
			criteria.setSize(appProperties.getDefaultPageSize());
		}
		Page<GroupParamDto> pages = groupParamManager.gets(criteria);
		modelAndView.addObject("page", pages);
		modelAndView.addObject("criteria",criteria);
        return modelAndView;
    }
    
    @GetMapping("/cag/group-param/form")
    public ModelAndView show(@RequestParam (value = "id", required = false) Long id) {
    	log.info("ENTERING 'FORM GROUP-PARAM' METHOD...");
    	ModelAndView modelAndView = new ModelAndView("cag/group-param/group-param-form");
    	if (id != null) {
            modelAndView.addObject("groupParam", groupParamManager.get(id));
    	} else {
    		modelAndView.addObject("groupParam", new GroupParamDto());
    	}
        return modelAndView;
    }
    
    @PostMapping("/cag/group-param/save")
    public String save(@Valid GroupParamDto groupParamDto, BindingResult bindingResult, HttpServletRequest request) {
    	log.info("ENTERING 'SAVE GROUP-PARAM' METHOD...");
        Locale locale = request.getLocale();
        String view = "catalog-group-param-form";
        if (bindingResult.hasErrors()) {
        	addError(request, bindingResult.getAllErrors().toString());
            return view;
        }
        String username = UserContext.getUsername();
        if (groupParamDto.getId() != null) {
        	groupParamDto.setModifiedBy(username);
        } else {
        	groupParamDto.setCreatedBy(username);
        	groupParamDto.setModifiedBy(username);
        }
        try {
        	GroupParamDto groupParam = groupParamManager.save(groupParamDto);
            addMessage(request, getText("group.param.updated", locale));
            view = "redirect:/cag/group-param/form?id=" + groupParam.getId(); 
		} catch (Exception e) {
			log.error("ERROR SAVE GROUP-PARAM EXCEPTION: {}", e);
			addError(request, e.getMessage());
		}
        return view;
    }
    //============= Controller GROUP-PARAM in CATALOG  ================//
    
    
    //============= Controller GROUP-PARAM in RESTAURANT  ================//
    @GetMapping("/group-param/form")
    public ModelAndView show(@RequestParam(value="rCode",required = true)Integer rCode) {
    	log.debug("entering 'show' method...");
    	
    	ModelAndView modelAndView = new ModelAndView("res-group-param");
    	CatalogApplyToRestaurantDto applyToRestaurantDto = catalogApplyToRestaurantManager.getByResCode(rCode);
    	GroupParamDto groupParam = new GroupParamDto();	
    	if(applyToRestaurantDto != null && applyToRestaurantDto.getGroupParamId() != null) {
    		groupParam.setId(applyToRestaurantDto.getGroupParamId());
    	} 
    	groupParam.setRestaurantCode(rCode);
		modelAndView.addObject("groupParam", groupParam);
		modelAndView.addObject("groupParams",groupParamManager.gets());
		modelAndView.addObject("restaurants", restaurantManager.getRelatedRestaurants(rCode));
        return modelAndView;
    }

}
