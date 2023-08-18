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
 * The Enum MapKeyEnum.
 *
 * @author <a href="mailto:ngtrungkien@gmail.com">Kien Nguyen</a>
 */
public enum MapKeyEnum {
	CURRENT_CO_FOOD_ITEM_IDS("current-module-id"),
	NEW_CO_FOOD_ITEM_IDS("new-module-id"),
	OLD_CO_FOOD_ITEM_IDS("old-module-id"),
	CURRENT_COCATEGORY_IDS("current-co-category-id"),
	NEW_COCATEGORY_IDS("new-co-category-id"),
	OLD_COCATEGORY_IDS("old-co-category-id"),
	CURRENT_FOODGROUP_IDS("current-food-group-id"),
	NEW_FOODGROUP_IDS("new-food-group-id"),
	OLD_FOODGROUP_IDS("old-food-group-id"),
	RESTAURANT_CODE("restaurant-code"),
	SELECTED_RESTAURANT_CODES("selected-restaurant-codes"),
	ZONE("zone"),
	PROVINCE("province"),
	DISTRICT("district"),
	BRAND("brand"),
	RES("restaurant"),
	PROVINCE_BRAND("province-brand"),
	DISTRICT_BRAND("district-brand"),
	SO_CATEGORY("so-category"),
	URL_DOWNLOAD("url-download"),
	ATTACHMENTS("attachments"),
	FILE_CONTENT("file-content"),
	CO_CATEGORY("co-category"),
	CO_FOOD_ITEM("co-food-item"),
	FOOD_GROUP("food-group"),
	ERROR("error"),
	FOODGROUP("food-group"),
	SO_ID("so-id"),
	REMOVED_CO_FOOD_ITEM_IDS("removed-co-food-item-ids"),
	
	//Key liên quan đến phần đồng bộ
	SO_CATEGORY_IN_CATALOG("so-category-in-catalog"),
	SO_CATEGORY_EDIT_IN_CATALOG("so-category-edit-in-catalog"),
	DELETE_SO_CATEGORY_CODE_IN_CATALOG("delete-so-category-code-in-catalog"),
	
	CO_CATEGORY_IN_CATALOG("co-category-in-catalog"),
	CO_CATEGORY_EDIT_IN_CATALOG("co-category-edit-in-catalog"),
	DELETE_CO_CATEGORY_CODE_IN_CATALOG("delete-co-category-code-in-catalog"),
	
	CO_FOODITEM_IN_CATALOG("co-fooditem-in-catalog"),
	CO_FOODITEM_EDIT_IN_CATALOG("co-fooditem-edit-in-catalog"),
	DELETE_CO_FOODITEM_CODE_IN_CATALOG("delete-co-fooditem-code-in-catalog"),
	
	CONFIG_QR_ORDER_IN_CATALOG("config-qr-order-in-catalog"),
	
	VERSIONS("versions"),
	RESTAURANT("restaurant"),
	EMPLOYEE("employee"),
	GUEST_TYPE("guest_type"),
	TABLE_KITCHEN("table_kitchen"),
	HALL_PLAN("hall_plan"),
	CURRENCY_RATE("currency_rate"),
	ORDER_VOID("order_void"),
	ORDER_TYPE("order_type"),
	ORDER_CATEGORY("order_category"),
	MODIFIER("modifier"),
	MODI_SCHEME_DETAIL("modi_scheme_detail"),
	MODI_SCHEME("modi_scheme"),
	MODI_GROUP("modi_group"),
	FOOD_ITEM("food_item"),
	CURRENCY("currency"),
	CATEGORY("category"),
	
	KALO_GROUP("kalo-group"),
	
	;

	public String key;

	private MapKeyEnum(String key) {
		this.key = key;
	}
}
