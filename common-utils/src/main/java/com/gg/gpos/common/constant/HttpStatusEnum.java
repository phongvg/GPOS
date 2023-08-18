/*
 * Copyright 2018. All rights reserved.
 * This software is the confidential and proprietary information
 * of GG ("Confidential Information").
 * You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement
 * you entered into with GG.
 */
package com.gg.gpos.common.constant;

/**
 * The Enum HttpStatusEnum.
 * 
 * @author <a href="mailto:ngtrungkien@gmail.com">Kien Nguyen</a> 
 */
public enum HttpStatusEnum {
	STATUS_200("200", "Success"),
	STATUS_201("201", "Created"),
	STATUS_202("202", "Accepted"),
	STATUS_400("400", "Bad Request"),
	STATUS_403("403", "Forbidden"),
	STATUS_404("404", "Not Found"),
	STATUS_498("498", "Token Expired"),
	STATUS_500("500", "Internal Server Error"),
	STATUS_502("502", "Bad Response From ICC Service"),
	STATUS_504("504", "Connection Timeout"),
	STATUS_560("560", "Network Connection Error");
	
	/** The code. */
	public String code;
	
	/** The message. */
	public String message;
	
	/**
	 * Instantiates a new status enum.
	 *
	 * @param code the code
	 * @param message the message
	 */
	private HttpStatusEnum(String code, String message) {
		this.code = code;
		this.message = message;
	}
}
