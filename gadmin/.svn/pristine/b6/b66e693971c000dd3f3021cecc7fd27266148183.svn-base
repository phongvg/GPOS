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
import com.gg.gpos.menu.dto.KdsAccountDto;
import com.gg.gpos.menu.dto.KdsPlaceDto;
import com.gg.gpos.menu.manager.KdsAccountManager;
import com.gg.gpos.menu.manager.KdsAccountRoleManager;
import com.gg.gpos.menu.manager.KdsPlaceManager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class KdsAccountController extends BaseController {
	
	@Autowired
	private KdsAccountManager kdsAccountManager;
	@Autowired
	private KdsPlaceManager kdsPlaceManager;
	@Autowired 
	private KdsAccountRoleManager kdsAccountRoleManager;
	
	@GetMapping("/res/kdsAccount/list")
    public ModelAndView list(@RequestParam(name="rCode", required = true) Integer rCode) {
    	log.debug("entering 'show kds place' method...");
    	ModelAndView modelAndView = new ModelAndView("res-kds-account-list");
    	KdsAccountDto criteria = new KdsAccountDto();
		criteria.setPage(appProperties.getDefaultPage());
		criteria.setSize(appProperties.getDefaultPageSize());
		criteria.setRestaurantCode(rCode);
		Page<KdsAccountDto> pages = kdsAccountManager.gets(criteria);
		modelAndView.addObject("page", pages);
		modelAndView.addObject("criteria",criteria);
		return modelAndView;
    }
	
	@PostMapping("/res/kdsAccount/list")
    public ModelAndView search(Model model, @Valid KdsAccountDto criteria, BindingResult bindingResult){
    	log.debug("entering 'search' method...");
		ModelAndView modelAndView = new ModelAndView("res-kds-account-list");
		if(criteria.getSize() == null) {
			criteria.setPage(appProperties.getDefaultPage());
			criteria.setSize(appProperties.getDefaultPageSize());
		}
		Page<KdsAccountDto> pages = kdsAccountManager.gets(criteria);
		modelAndView.addObject("page", pages);
		modelAndView.addObject("criteria",criteria);
        return modelAndView;
    }
	
	@GetMapping("/res/kdsAccount/form")
    public ModelAndView show(@RequestParam(value="id", required=false) Long id,@RequestParam(value="rCode", required=true) Integer rCode) {
    	log.debug("entering 'show' method...");
    	ModelAndView modelAndView = new ModelAndView("res-kds-account-form");
    	KdsAccountDto kdsAccount = new KdsAccountDto();
    	if(id != null) {
    		kdsAccount = kdsAccountManager.get(id);
    	}
    	kdsAccount.setRestaurantCode(rCode);
    	
    	modelAndView.addObject("kdsAccount", kdsAccount);
    	modelAndView.addObject("parents", kdsAccountManager.gets(rCode));
    	modelAndView.addObject("roles", kdsAccountRoleManager.gets());
        return modelAndView;
    }
	
	@PostMapping("/res/kdsAccount/save")
    public String save(@Valid KdsAccountDto kdsAccount,BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {
    	log.debug("entering 'save ' method...");
        Locale locale = request.getLocale();
        String view = "res-kds-account-form";
        if (bindingResult.hasErrors()) {
        	addError(request, bindingResult.getAllErrors().toString());
        } 
        try {
        	KdsAccountDto kdsNew = kdsAccountManager.save(kdsAccount);
	    	addMessage(request, getText("kds.place.updated", locale));
	        view = "redirect:/res/kdsAccount/list?rCode=" + kdsNew.getRestaurantCode();
		} catch (Exception e) {
			addError(request, e.getMessage());
		}
        return view;
    }
	
	//============= api =============//
	@GetMapping("/res/kdsAccount/get-kdsPlaces")
    @ResponseBody
    public List<KdsPlaceDto> getKdsPlaces(@RequestParam(value="id", required = false)Long kdsAccountId,@RequestParam(value="resCode", required = true) Integer resCode) {
    	log.debug("entering 'getKdsPlaces' method...");
    	return kdsPlaceManager.getByKdsAccountIdAndResCodes(kdsAccountId,resCode);
    }
	
	@PostMapping(value = "/kdsAccount/checkAccountName", produces = "application/json;charset=UTF-8")
	public @ResponseBody boolean checkCode(@RequestParam("accountName") String accountName,@RequestParam("resCode") Integer resCode, HttpServletRequest request) {
		log.debug("...Entering checkCode Transaction");
		Boolean isExistCode = false;
		try {
			if(accountName != null) {
				KdsAccountDto kdsAccountDto = kdsAccountManager.getByResCodeAndAccountName(resCode, accountName);
				if(kdsAccountDto != null) {
					isExistCode = true;
				}
			}
		} catch (Exception e) {
			return isExistCode;
		}
		return isExistCode;
	}
}
