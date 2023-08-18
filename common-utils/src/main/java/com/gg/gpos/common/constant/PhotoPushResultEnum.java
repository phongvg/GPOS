package com.gg.gpos.common.constant;

public enum PhotoPushResultEnum {
	PHOTO_PUSH_PROCESS("Tiến trình đẩy ảnh : "),
	PHOTO_PUSH_RESULT("Kết quả đẩy ảnh : "),
	CATEOGRY(" ảnh category - "),
	CO_FOODITEM(" ảnh món - "),
	FOOD_GROUP(" ảnh nhóm món ăn - "),
	HYPHEN(" - "),
	ZERO("0/");
	
	public String val;
	
	private PhotoPushResultEnum(String val) {
		this.val = val;
	}
}
