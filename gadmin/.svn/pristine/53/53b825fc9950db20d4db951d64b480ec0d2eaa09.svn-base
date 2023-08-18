package com.gg.gpos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.gg.gpos.interceptor.FirstAccessInterceptor;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
	@Autowired
	protected AppProperties appProperties;
	
    @Autowired
    Environment env;
    
    @Autowired
    FirstAccessInterceptor firstAccessInterceptor;

    public MvcConfig() {
        super();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	registry.addResourceHandler(appProperties.getAttachmentContextPath() + "**").addResourceLocations("file:" + appProperties.getAttachmentPath());
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    	registry.addInterceptor(firstAccessInterceptor).excludePathPatterns("/login", "/save-change-pass");
    }
}