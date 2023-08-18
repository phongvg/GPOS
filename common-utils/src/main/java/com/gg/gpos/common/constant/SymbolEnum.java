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
 * The Enum SymbolEnum.
 * 
 * @author <a href="mailto:ngtrungkien@gmail.com">Kien Nguyen</a> 
 */
public enum SymbolEnum {
	AT_SIGN("@"),
	AMPERSAND("&"),
	COLON(":"),
	COMMA(","),
	DOT("."),
	EQUALS_SIGN("="),
	HYPHEN("-"),
	QUESTION_MARK("?"),
	SLASH("/"),
	BACKSLASH("\\"),
	HASH("#"),
	SPACE(" "),
	PERCENT("%"),
	BRACKETS_LEFT("("),
	BRACKETS_RIGHT(")"),
	UNDERSCORE("_");
	
	public String val;

	private SymbolEnum(String val) {
		this.val = val;
	}
}
