package com.gg.gpos.integration.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gg.gpos.controller.BaseController;
import com.gg.gpos.integration.dto.SyncArchiveDto;
import com.gg.gpos.integration.manager.SyncArchiveManager;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class SyncArchiveController extends BaseController{

	@Autowired
	private SyncArchiveManager syncArchiveManager;
	
	@GetMapping("/sync-history/list")
    public ModelAndView list() {
    	log.info("ENTERING 'LIST SYNC_HISTORY' METHOD...");
		ModelAndView modelAndView = new ModelAndView("sync-history-list");
		SyncArchiveDto criteria = new SyncArchiveDto();
		criteria.setPage(appProperties.getDefaultPage());
		criteria.setSize(appProperties.getDefaultPageSize());
		Page<SyncArchiveDto> pages = syncArchiveManager.gets(criteria);
		modelAndView.addObject("page", pages);
		modelAndView.addObject("criteria",criteria);
        return modelAndView;
    }
    
    @PostMapping("/sync-history/list")
    public ModelAndView search(Model model, @Valid SyncArchiveDto criteria, BindingResult bindingResult){
    	log.info("ENTERING 'SEARCH SYNC_HISTORY' METHOD...");
		ModelAndView modelAndView = new ModelAndView("sync-history-list");
		if(criteria != null && criteria.getSize() == null) {
			criteria.setPage(appProperties.getDefaultPage());
			criteria.setSize(appProperties.getDefaultPageSize());
		}
		Page<SyncArchiveDto> pages = syncArchiveManager.gets(criteria);
		modelAndView.addObject("page", pages);
		modelAndView.addObject("criteria",criteria);
        return modelAndView;
    }
}
