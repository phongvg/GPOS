package com.gg.gpos.config;

import java.util.Locale;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.gg.gpos.filter.SiteMeshFilter;

@Configuration
public class WebConfig {
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.US);
        return slr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
    
	@Bean
	public FilterRegistrationBean<SiteMeshFilter> siteMeshFilter(){
		FilterRegistrationBean<SiteMeshFilter> fitler = new FilterRegistrationBean<>();
		SiteMeshFilter siteMeshFilter = new SiteMeshFilter();
		fitler.setFilter(siteMeshFilter);
		return fitler;
	}
    
}
