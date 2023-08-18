package com.gg.gpos.common.constant;

public enum TmpSyncTypeEnum {
	MASTER_DATA("master-data"),
	CO_CATEGORY("co-category"),
	CO_FOOD_ITEM("co-food-item"),
	FOOD_GROUP("food-group"),
	ORDER_CATEGORY_CODE("order-category-code"),
	;
	public String val;

	private TmpSyncTypeEnum(String val) {
		this.val = val;
	}
}
