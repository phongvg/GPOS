package com.gg.gpos.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.util.StringUtils;
import org.springframework.web.context.ServletContextAware;

import com.gg.gpos.common.constant.SymbolEnum;
import com.gg.gpos.common.json.MasterDataSync;
import com.gg.gpos.common.json.SyncResponse;
import com.gg.gpos.config.AppProperties;
import com.gg.gpos.integration.manager.RestaurantSyncManager;
import com.gg.gpos.menu.manager.DataSyncManager;
import com.gg.gpos.reference.dto.SystemParameterDto;
import com.gg.gpos.reference.manager.SystemParameterManager;
import com.gg.gpos.res.manager.RestaurantManager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BaseController implements ServletContextAware {

	@Autowired
	protected AppProperties appProperties;
	@Autowired
	protected SystemParameterManager systemParameterManager;
	@Autowired
	protected DataSyncManager dataSyncManager;
	@Autowired
	protected RestaurantSyncManager restaurantSyncManager;
	@Autowired
	protected RestaurantManager restaurantManager;
    private ServletContext servletContext;
    private MessageSourceAccessor messages;
    public static final String ERRORS_SESSION = "errors";
    public static final String MESSAGES_SESSION = "messages";

    
	public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    protected ServletContext getServletContext() {
        return servletContext;
    }
    
    @Autowired
    public void setMessages(MessageSource messageSource) {
        messages = new MessageSourceAccessor(messageSource);
    }
    
    public String getText(String msgKey, Locale locale) {
        return messages.getMessage(msgKey, locale);
    }

    public String getText(String msgKey, String arg, Locale locale) {
        return getText(msgKey, new Object[] { arg }, locale);
    }

    public String getText(String msgKey, Object[] args, Locale locale) {
        return messages.getMessage(msgKey, args, locale);
    }    
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void addError(HttpServletRequest request, String error) {
    	List errors = (List) request.getSession().getAttribute(ERRORS_SESSION);
    	if (errors == null) {
    		errors = new ArrayList<>();
    	}
        errors.add(error);
        request.getSession().setAttribute(ERRORS_SESSION, errors);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void addMessage(HttpServletRequest request, String msg) {
    	List messages = (List) request.getSession().getAttribute(MESSAGES_SESSION);
    	if (messages == null) {
    		messages = new ArrayList<>();
    	}
    	messages.add(msg);
    	request.getSession().setAttribute(MESSAGES_SESSION, messages);
    }
    
    protected SyncResponse sendMasterData(String paramHost, String praramTypePattern, Integer version) {
    	log.info("Entering sendMasterData() ...");
    	log.debug("paramHost: " + paramHost + ", praramTypePattern: " + praramTypePattern);
    	
    	SystemParameterDto apiGatewayUrl = systemParameterManager.get(paramHost);
    	SystemParameterDto pattern = systemParameterManager.get(praramTypePattern);
    	String url = apiGatewayUrl.getParamValue() + pattern.getParamValue();

    	String resCodes = null;
		StringBuilder builder = new StringBuilder();
    	MasterDataSync masterDataSync = new MasterDataSync();
		List<Integer> codes = restaurantManager.gets().stream().map(r -> r.getCode()).collect(Collectors.toList());
		codes.stream().forEach(c -> builder.append(c).append(SymbolEnum.COMMA.val));
		resCodes = builder.deleteCharAt(builder.length() - 1).toString();
		masterDataSync = dataSyncManager.prepareMasterDataToSendToRestaurant(praramTypePattern, version);
    	return restaurantSyncManager.sendMasterDataToRestaurant(masterDataSync, praramTypePattern, url, resCodes);

    }
    

}
