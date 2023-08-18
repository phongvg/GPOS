package com.gg.gpos.io.dto;

import lombok.Data;

@Data
public class IOSoDto {
	private String orderCategoryCode;
	private String orderCategoryName;
	private String foodGroupParentNameVn;
	private String foodGroupParentNameEn;
	private String foodGroupParentCode;
	private String foodGroupParentType;
	private Double foodGroupParentLevel;
	private Double groupOrder;
	private String foodGroupNameVn;
	private String foodGroupNameEn;
	private String foodGroupCode;
	private String foodGroupType;
	private Double itemOrder;
	private String foodItemCode;
	private String foodItemNameVn;
	private Double menuTypeCode;
	private String sapCode;
	private String srcImg;
	private boolean status;
	private String error;
}
