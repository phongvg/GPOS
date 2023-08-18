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
 * The Enum FunctionTypeEnum.
 *
 * @author <a href="mailto:ngtrungkien@gmail.com">Kien Nguyen</a>
 */
public enum FunctionTypeEnum {

	/** The avatar. */
	AVATAR("avatar"),

	/** The images. */
	IMAGES("images"),

	/** The avatar home. */
	AVATAR_HOME("avatar_home"),

	/** The avatar intro. */
	AVATAR_ABOUTUS("avatar_aboutus"),

	/** The Half image */
	HALF_PHOTO("half_image"),

	/** The Topping image */
	TOPPING_PHOTO("topping_image"),

	/** The Group image */
	GROUP("group_image"),

	/** The Group hidden image */
	GROUP_HIDDEN("group_hidden_image"),
	
	LOGO("logo"),
	
	/** The avatar intro restaurant. */
	AVATAR_ABOUTUS_RES("avatar_aboutus_res"),
	
	/** The avatar intro menu. */
	AVATAR_ABOUTUS_MENU("avatar_aboutus_menu"),
	
	/** The video intro menu. */
	VIDEO_ABOUTUS_MENU("video_aboutus_menu"),
	
	/** The video intro restaurant. */
	VIDEO_ABOUTUS_RES("video_aboutus_res"),
	
	/** The horizontal image. */
	HORIZONTAL_PHOTO("horizontal_image"),
	
	/** The vertical image. */
	VERTICAL_PHOTO("vertical_image"),
	
	/** The quarter image. */
	QUARTER_PHOTO("quarter_image"),
	
	/** The drink image. */
	DRINK_PHOTO("drink_image"),
	
	/** The singleCategory image. */
	SINGLE_CATEGORY("single_category"),
	
	/** The mutilCategory image. */
	MULTI_CATEGORY("multi_category"),
	
	/** The QR-order image. */
	QR_ORDER_PHOTO("qr_order_image"),
	
	/** The intro image. */
	INFO_PHOTO("info_image"),
	
	/** The logo image. */
	LOGO_PHOTO("logo_image"),
	
	/** The close ORDER image. */
	CLOSE_ORDER_PHOTO("close_order_image"),
	
	/** The excel. */
	EXCEL("excel");

	/** The val. */
	public String val;

	/**
	 * Instantiates a new function type enum.
	 *
	 * @param val the val
	 */
	private FunctionTypeEnum(String val) {
		this.val = val;
	}
}
