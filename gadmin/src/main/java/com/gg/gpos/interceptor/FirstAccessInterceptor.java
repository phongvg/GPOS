package com.gg.gpos.interceptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


import com.gg.gpos.user.manager.CustomUserDetailsService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FirstAccessInterceptor extends HandlerInterceptorAdapter{
	@Autowired
	public CustomUserDetailsService userDetailsService;

	private static final String CHANGE_PASS_URL = "/passwordChange";
	private static final String LOGIN_URL = "/login";
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		if (currentUser != null) {
			log.debug("checking the first time login....");
			if (isFirstTimeLogin(currentUser.getName())) {
				if(!(request.getRequestURI().equals(request.getContextPath()+ CHANGE_PASS_URL))){
				    redirect(request, response, CHANGE_PASS_URL);
					return false;
				}
			}
			
			if(isCheckEnable(currentUser.getName())) {
				log.debug("checking account enable....");
				if(!(request.getRequestURI().equals(request.getContextPath()+ LOGIN_URL))){
				    redirect(request, response, LOGIN_URL);
					return false;
				}
			}
			
		}
		
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)	throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}

	// check is the first time login
	private boolean isFirstTimeLogin(String username) {
		return userDetailsService.isFirstTimeAccess(username);
	}
	
	
	// check is the first time login
		private boolean isCheckEnable(String username) {
			return userDetailsService.isCheckEnable(username);
		}
		
	// redirect path
	private void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException {
		try {
			response.sendRedirect(request.getContextPath() + path);
		} catch (java.io.IOException e) {
			throw new ServletException(e);
		}
	}

}
