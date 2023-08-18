package com.gg.gpos.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	private static final String EXPORT_SO_CATALOG = "exportSoCatalog";
	private static final String EXPORT_CO_CATALOG = "exportCoCatalog";
	
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_OK);
        
        RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
        
        //since we have created our custom success handler, its up to us, to where
        //we will redirect the user after successfully login
        String savedRequestUrl = "/";
        SavedRequest savedRequest = new HttpSessionRequestCache().getRequest(request, response);
        if (savedRequest != null) {
        	if (StringUtils.isNotBlank(savedRequest.getRedirectUrl()) && !savedRequest.getRedirectUrl().contains(EXPORT_SO_CATALOG) && !savedRequest.getRedirectUrl().contains(EXPORT_CO_CATALOG)) {
        		savedRequestUrl = savedRequest.getRedirectUrl();
        	}
        }
        //response.sendRedirect(savedRequest.getRedirectUrl().isEmpty() ? "/" : savedRequest.getRedirectUrl()); //requestUrl!=null?requestUrl:   
        redirectStrategy.sendRedirect(request, response, savedRequestUrl);
        
//        for (GrantedAuthority auth : authentication.getAuthorities()) {
//            if ("ADMIN".equals(auth.getAuthority())) {
//                response.sendRedirect("/admin");
//            }
//            if ("USER".equals(auth.getAuthority())) {
//                response.sendRedirect("/user");
//            }
//        }
    }

}
