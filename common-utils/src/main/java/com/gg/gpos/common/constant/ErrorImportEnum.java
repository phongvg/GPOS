package com.gg.gpos.common.constant;

public enum ErrorImportEnum {
	
	ERROR_FOODGROUP_NOT_EXIST("Mã nhóm món ăn này đã được sử dụng"),
	ERROR_FOODITEM_NOT_EXIST("Mã món ăn này không tồn tại"),
	ERROR_KDS_PLACE_NOT_EXIST("Mã KDS Place này không tồn tại"),
	ERROR_FOODITEM_NOT_EXIST_IN_SO("Mã món ăn này không tồn tại"),
	ERROR_USERNAME_IS_EXIST("Tên đăng nhập này đã được sử dụng"),
	ORDER_CATEGORY_NOT_EXIST("Mã loại menu không tồn tại trong danh mục này"),
	ERROR_FOODGROUP_IS_USE("Mã nhóm món này đã được sử dụng ở danh mục SO : "),
	ERROR_KDS_PLACE_EXIST("Mã code này đã được sử dụng ở nhà hàng :"),
	ERROR_FORMAT("Sai định dạng"),
	ERROR_FOODGROUP_FORMAT("Sai định dạng nhóm cha con trong FoodGroup"),
	ERROR_ATTACHMENT_FORMAT("Sai định dạng tên ảnh."),
	ERROR_FOODITEM_NOT_EXIST_OR_ERROR_FOODITEM_NOT_EXIST("Mã món ăn hoặc mã KDS Place này không tồn tại"),
	ERROR_PRINTGROUP_NOT_EXIST("Chưa nhập mã nhóm in");
	
	/** The val. */
	public String val;

	/**
	 * Instantiates a new function type enum.
	 *
	 * @param val the val
	 */
	private ErrorImportEnum(String val) {
		this.val = val;
	}
}
