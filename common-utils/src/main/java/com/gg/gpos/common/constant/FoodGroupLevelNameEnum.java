package com.gg.gpos.common.constant;

public enum FoodGroupLevelNameEnum {
	MENU_NORMAL("Menu thường"),
	MENU_DRINK("Menu đồ uống");
	
	public String val;
	private FoodGroupLevelNameEnum(String val) {
		this.val = val;
	}
}
