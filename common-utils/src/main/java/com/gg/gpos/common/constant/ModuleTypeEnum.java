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
 * The Enum ModeEnum: data mode in Detail mode or Average mode.
 *
 * @author <a href="mailto:ngtrungkien@gmail.com">Kien Nguyen</a>
 */
public enum ModuleTypeEnum {
	DEL_ATTACHMENT("del-attachment"),
	FOOD_GROUP("food-group"),
	FOOD_ITEM("food-item"),
	CO_FOOD_ITEM("co-food-item"),
	SO_CATEGORY("so-category"),
	ORDER_CATEGORY("order-category"),
	CO_CATEGORY("co-category"),
	CONFIG_QR_ORDER("config-qr-order"),
	EXPORT_SO("export-so"),
	EXPORT_CO("export-co"),
	EXPORT_CO_CATEGORY("export-co-category"),
	EXPORT_KDS_PLACE("kds-place"),
	EXPORT_KDS_CONFIG_COOKING("kds-config-cooking"),
	EXPORT_KDS_ACCOUNT("kds-account"),
	EXPORT_APPUSER("export-user"),
	EXPORT_PRINT_GROUP("export-print-group"),
	DIGITAL_MENU("digital-menu/"),

	MENU("/menu/"),
	VIDEO("/video/");

	public String val;

	private ModuleTypeEnum(String val) {
		this.val = val;
	}
}
