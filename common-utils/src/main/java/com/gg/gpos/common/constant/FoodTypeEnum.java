package com.gg.gpos.common.constant;

public enum FoodTypeEnum {
	MENU_ITEM("MENU_ITEM"),
	MENU_GROUP("MENU_GROUP"),
	SIZE_FOODITEM("SIZE_FOODITEM"),
	RELATED_FOODITEM("RELATED_FOODITEM");

	public String val;

	private FoodTypeEnum(String val) {
		this.val = val;
	}
}
