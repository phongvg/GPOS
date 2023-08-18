package com.gg.gpos.common.constant;

public enum MenuTypeEnum {
	ALC(1, "ALC"),
	BUFFET(2, "BUF"),
	SET(3, "SET"),;
	
	public Integer key;
	public String val;
	
	MenuTypeEnum(Integer key, String val){
		this.key = key;
		this.val = val;
	}
}
