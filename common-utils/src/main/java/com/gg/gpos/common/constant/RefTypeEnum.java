package com.gg.gpos.common.constant;

public enum RefTypeEnum {
	CO("co"),
	KDS("kds"),
	PARAM("param"),
	SO("so"),
	USER("user"),
	RES("res");
	
	public String val;

	private RefTypeEnum(String val) {
		this.val = val;
	}
}
