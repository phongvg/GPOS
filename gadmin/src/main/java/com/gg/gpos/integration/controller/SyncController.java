package com.gg.gpos.integration.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.gg.gpos.common.constant.TypeSyncEnum;
import com.gg.gpos.integration.manager.ThreadSyncFileManager;
import com.gg.gpos.integration.manager.ThreadSyncManager;
import com.gg.gpos.menu.dto.SyncResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gg.gpos.controller.BaseController;
import com.gg.gpos.integration.dto.SyncDto;
import com.gg.gpos.integration.manager.SyncManager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@EnableScheduling
public class SyncController extends BaseController{
		@Autowired 
		private SyncManager syncManager;
		@Autowired
		private ThreadSyncManager threadSyncManager;
		@Autowired
		private ThreadSyncFileManager threadSyncFileManager;
		
		@GetMapping("/sync-latest-data/list")
	    public ModelAndView list() {
			log.info("ENTERING 'LIST SYNC' METHOD...");
			ModelAndView modelAndView = new ModelAndView("sync/sync-list");
			SyncDto criteria = new SyncDto();
			criteria.setCheckAllItem(false);
			criteria.setPage(appProperties.getDefaultPage());
			criteria.setSize(appProperties.getDefaultPageSize());
			Page<SyncDto> pages = syncManager.gets(criteria);
			modelAndView.addObject("page", pages);
			modelAndView.addObject("criteria",criteria);
	        return modelAndView;
	    }
	    
	    @PostMapping("/sync-latest-data/list")
	    public ModelAndView search(Model model, @Valid SyncDto criteria, BindingResult bindingResult){
	    	log.info("ENTERING 'SEARCH SYNC' METHOD...");
	    	ModelAndView modelAndView = new ModelAndView("sync/sync-list");
			if(criteria != null && criteria.getSize() == null) {
				criteria.setPage(appProperties.getDefaultPage());
				criteria.setSize(appProperties.getDefaultPageSize());
			}
			Page<SyncDto> pages = syncManager.gets(criteria);
			modelAndView.addObject("page", pages);
			modelAndView.addObject("criteria",criteria);
	        return modelAndView;
	    }
	    
	    @PostMapping("/sync/reset-sync-fail")
	    public String reset( @Valid SyncDto syncDto, HttpServletRequest request){
	    	log.info("ENTERING 'RESET SYNC FAIL' METHOD...");
	    	try {
				syncManager.resetSyncHasStatusFail(syncDto);
				addMessage(request, getText("sync.update.sucess", request.getLocale()));
			} catch (Exception e) {
				log.error("ERROR: RESET SYNC FAIL EXCEPTION: {}", e);
			}
	        return "redirect:/sync-latest-data/list";
	    }

		@PostMapping("/sync/delete-all")
		public String delete( @Valid SyncDto syncDto, HttpServletRequest request){
			log.info("ENTERING 'DELETE ALL SYNC' METHOD...");
			try {
				syncManager.deleteSyncHasStatusFail(syncDto);
				addMessage(request, getText("sync.delete", request.getLocale()));
			} catch (Exception e) {
				log.error("ERROR: RESET SYNC FAIL EXCEPTION: {}", e);
			}
			return "redirect:/sync-latest-data/list";
		}

	    @GetMapping("/sync/del/{id}")
	    public String del(@PathVariable(value="id") Long id, HttpServletRequest request) {
	    	log.info("ENTERING 'DELETE SYNC' METHOD...");
	    	try {
	    		syncManager.deleteById(id);
	    		addMessage(request, getText("sync.delete", request.getLocale()));
			} catch (Exception e) {
				addMessage(request, e.getMessage());
				log.error("ERROR: DELETE SYNC EXCEPTION: {}", e);
			}
	    	String view = "redirect:/sync-latest-data/list";
	        return view;
	    }

		@GetMapping("/sync-now/{id}")
		public String syncNow(@PathVariable(value="id") Long id, HttpServletRequest request) {
			log.info("ENTERING 'SYNC NOW' METHOD...");
			try {
				SyncDto syncDto = syncManager.get(id);
				if(syncDto != null) {
					if(syncDto.getTypeSync().equals(TypeSyncEnum.SYNC_FILE_DATA_TO_SERVER_RESTAURANT.val)) {
						SyncResponseDto syncResponseDto = threadSyncFileManager.syncNow(syncDto);
						if(syncResponseDto.getStatus()) {
							addMessage(request, getText("sync.success", request.getLocale()));
						} else {
							addError(request, syncResponseDto.getMessage());
						}
					} else {
						SyncResponseDto syncResponseDto = threadSyncManager.syncNow(syncDto);
						if(syncResponseDto.getStatus()) {
							addMessage(request, getText("sync.success", request.getLocale()));
						} else {
							addError(request, syncResponseDto.getMessage());
						}
					}
				} else {
					addError(request, getText("sync.not.exist", request.getLocale()));
				}
			} catch (Exception e) {
				addError(request, e.getMessage());
				log.error("ERROR: SYNC NOW EXCEPTION: {}", e);
			}
			String view = "redirect:/sync-latest-data/list";
			return view;
		}
}
