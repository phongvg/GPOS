package com.gg.gpos.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gg.gpos.common.util.UserContext;
import com.gg.gpos.user.manager.AppUserManager;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class PasswordController extends BaseController {
	@Autowired
	private AppUserManager userManager;

	@GetMapping("/passwordChange")
	public ModelAndView changePassForm(HttpServletRequest request, HttpServletResponse response) {
		log.info("ENTERING 'CHANGE PASSWORD FORM' METHOD...");
		ModelAndView modelAndView = new ModelAndView("system/change-password");
		if(userManager.checkChangeDate()) {
			modelAndView.addObject("check", "check");
		}
		return modelAndView;
	}

	@PostMapping("/save-change-pass")
	public String changePass(@RequestParam(value = "password") String password,@RequestParam(value = "passwordOld",required = false) String passwordOld,
			@RequestParam(value = "confirmPassword") String confirmPass, HttpServletRequest request,
			HttpServletResponse responses,RedirectAttributes redirectAttributes) throws Exception {
		log.info("ENTERING 'SAVED PASSWORD' METHOD...");
		Locale locale = request.getLocale();
		String view = "redirect:/passwordChange";
		boolean check = userManager.check();
		if(check) {
			if (userManager.changePass(UserContext.getUsername(), password) != null) {
				addMessage(request, getText("user.password.changed", locale));
				view = "redirect:/";
			} else {
				addMessage(request, getText("user.password.changefail", locale));
			}  
		}else {
			boolean checkPass = userManager.checkPass(passwordOld);
			if(checkPass) {
				if (userManager.changePass(UserContext.getUsername(), password) != null) {
					addMessage(request, getText("user.password.changed", locale));
					view = "redirect:/";
				} else {
					addMessage(request, getText("user.password.changefail", locale));
				}  
			}else {
				redirectAttributes.addFlashAttribute("error", "error");
			}
		}
		return view;
	}

}
