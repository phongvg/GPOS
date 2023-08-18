/*
 * Copyright 2018. All rights reserved.
 * This software is the confidential and proprietary information
 * of GG ("Confidential Information").
 * You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement
 * you entered into with GG.
 */
package com.gg.gpos.common.util;

/**
 * The Class BaseSearchCriteria.
 *
 * @author <a href="mailto:ngtrungkien@gmail.com">Kien Nguyen</a>
 */
public class BaseSearchCriteria {
	private Integer page = 0;
	private Integer size = 25; // DEFAULT_PAGE_SIZE
	/**
	 * @return the page
	 */
	public Integer getPage() {
		return page;
	}
	/**
	 * @param page the page to set
	 */
	public void setPage(Integer page) {
		this.page = page;
	}
	/**
	 * @return the size
	 */
	public Integer getSize() {
		return size;
	}
	/**
	 * @param size the size to set
	 */
	public void setSize(Integer size) {
		this.size = size;
	}
}
