package com.gg.gpos.common.constant;

public enum FoodGroupLevelEnum {
	MENU_NORMAL(-1),
	MENU_DRINK(0);

	public Integer val;

	private FoodGroupLevelEnum(Integer val) {
		this.val = val;
	}
}
