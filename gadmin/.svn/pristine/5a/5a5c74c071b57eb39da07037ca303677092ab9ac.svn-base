package com.gg.gpos.menu.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gg.gpos.common.constant.ModelViewEnum;
import com.gg.gpos.common.util.UserContext;
import com.gg.gpos.controller.BaseController;
import com.gg.gpos.menu.dto.GroupParamDto;
import com.gg.gpos.menu.dto.ParamDto;
import com.gg.gpos.menu.manager.GroupParamManager;
import com.gg.gpos.menu.manager.ParamManager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ParamController extends BaseController {
	@Autowired
	private ParamManager paramManager;
	@Autowired
	private GroupParamManager groupParamManager;
	
	
	//============= Controller PARAM in CATALOG  ================//
	@GetMapping("/cag/group-param/param-list")
    public ModelAndView list(@RequestParam(value = "groupParamId",required = true)Long groupParamId,HttpServletRequest request){
		log.info("ENTERING 'LIST PARAM IN CATALOG' METHOD...");
		ModelAndView modelAndView = new ModelAndView("cag/group-param/param-list");
		if(groupParamId != null) {
			ParamDto criteria = new ParamDto();
			criteria.setPage(appProperties.getDefaultPage());
			criteria.setSize(appProperties.getDefaultPageSize());
			criteria.setGroupParamId(groupParamId);
			Page<ParamDto> pages = paramManager.gets(criteria);
			modelAndView.addObject("page", pages);
			modelAndView.addObject("criteria",criteria);
		} else {
			modelAndView.setViewName("/cag/group-param/param-list");
    		addError(request, getText("group.param.not.created", request.getLocale()));
		}
		
        return modelAndView;
    }
    
    @PostMapping("/cag/group-param/param-list")
    public ModelAndView search(@Valid ParamDto criteria) {
    	log.info("ENTERING 'SEARCH PARAM IN CATALOG' METHOD...");
		ModelAndView modelAndView = new ModelAndView("cag/group-param/param-list");
		if (criteria != null && criteria.getSize() == null) {
			criteria.setPage(appProperties.getDefaultPage());
			criteria.setSize(appProperties.getDefaultPageSize());
		}
		Page<ParamDto> pages = paramManager.gets(criteria);
		modelAndView.addObject("page", pages);
		modelAndView.addObject("criteria",criteria);
        return modelAndView;
    }
	
	
	@GetMapping("/cag/group-param/param-form")
    public ModelAndView show(@RequestParam(value = "id", required = false) Long id, @RequestParam(value = "groupParamId", required = false) Long groupParamId) {
		log.info("ENTERING 'SHOW PARAM IN CATALOG' METHOD...");
		ModelAndView modelAndView = new ModelAndView("cag/group-param/param-form");
		ParamDto param = new ParamDto(); 
		if(id != null) {
			param = paramManager.get(id);
		} else {
			GroupParamDto groupParamDto = new GroupParamDto();
			groupParamDto.setId(groupParamId);
			param.setGroupParam(groupParamDto);
		}
		param.setGroupParamId(groupParamId);
		modelAndView.addObject("paramDto",param);
        return modelAndView;
    }
	
	@PostMapping("/group-param/param-save")
	public String save(@Valid ParamDto param, BindingResult bindingResult, HttpServletRequest request) {
		log.info("ENTERING 'SAVE PARAM' METHOD...");
		String view = "redirect:/cag/group-param/param-list?groupParamId=" + param.getGroupParamId() ;
		Locale locale = request.getLocale();
		if(bindingResult.hasErrors()) {
			addError(request, bindingResult.getAllErrors().toString());
			return view;
		}
		String loginedUsername = UserContext.getUsername();
		try {
			if(param.getId() == null) {
				param.setCreatedBy(loginedUsername);
			}else {
				param.setCreatedBy(loginedUsername);
				param.setModifiedBy(loginedUsername);
			}
			paramManager.save(param);
			addMessage(request, getText("param.updated.success", locale));
		} catch (Exception e) {
			addError(request, e.getMessage());
			log.error("ERROR SAVE PARAM EXCEPTION: {}", e);
		}
		
		if(param.getRestaurantCode() != null) {
			view = "redirect:/param/list?rCode=" + param.getRestaurantCode();
		}
		return view;
	}
	//============= Controller PARAM in CATALOG  ================//
	
	
	
	
	@GetMapping("/param/list")
    public ModelAndView listParam(@RequestParam(name = "rCode", required = true) Integer rCode) {
    	log.debug("entering 'list' method...");
    	
		ModelAndView modelAndView = new ModelAndView("param-list");
		ParamDto criteria = new ParamDto();
		criteria.setPage(appProperties.getDefaultPage());
		criteria.setSize(appProperties.getDefaultPageSize());
		criteria.setRestaurantCode(rCode);
		modelAndView.addObject(ModelViewEnum.MODEL_PAGE.mav, paramManager.gets(criteria));
		modelAndView.addObject(ModelViewEnum.MODEL_CRITERIA.mav, criteria);
        return modelAndView;
    }
	
	@PostMapping("/param/list")
    public ModelAndView searchParamInRestaur(@Valid ParamDto criteria) {
    	log.debug("entering 'search' method...");
    	
		ModelAndView modelAndView = new ModelAndView("param-list");
		if(criteria != null && criteria.getSize() == null) {
			criteria.setPage(appProperties.getDefaultPage());
			criteria.setSize(appProperties.getDefaultPageSize());
		}
		modelAndView.addObject(ModelViewEnum.MODEL_PAGE.mav, paramManager.gets(criteria));
		modelAndView.addObject(ModelViewEnum.MODEL_CRITERIA.mav, criteria);

        return modelAndView;
    }
	
	@GetMapping("/param/form")
    public ModelAndView showParam(@RequestParam(value = "id",required = false)Long id, @RequestParam(name="rCode", required = true) Integer rCode) {
    	log.debug("entering 'showForm' method...");
    	
		ModelAndView modelAndView = new ModelAndView("param-form");
		ParamDto param = new ParamDto(); 
		if(!StringUtils.isEmpty(id)) {
			param = paramManager.get(id);
		}else {
			param.setRestaurantCode(rCode);
		}
		modelAndView.addObject("paramDto",param);
        return modelAndView;
    }
	
	//================== Catalog ======================//
	
	
	
	@GetMapping("/cag/group-param/sync-to-restaurant")
    public ModelAndView syncParamForm(@RequestParam (value="groupParamId") Long groupParamId, HttpServletRequest request) {
    	log.info("ENTERING 'SYNC GROUP-PARAM' METHOD...");
		ModelAndView modelAndView = new ModelAndView("catalog-param-sync");
		if(groupParamId != null) {
    		GroupParamDto groupParam = groupParamManager.get(groupParamId);
    		modelAndView.addObject("groupParam", groupParam);
    	}else {
    		modelAndView.setViewName("redirect:cag/group-param/param-list");
    		addError(request, getText("group.param.not.created", request.getLocale()));
    	}
        return modelAndView;
    }
}
