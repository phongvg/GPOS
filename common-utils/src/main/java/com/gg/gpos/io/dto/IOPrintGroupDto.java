package com.gg.gpos.io.dto;

import lombok.Data;

@Data
public class IOPrintGroupDto {
	private String printGroupCode;
	private String kitchenType;
	private String foodItemCode;
	private String foodItemName;
	private boolean status;
	private String error;
}
