package com.gg.gpos.common.constant;

public enum MailEnum {
	EMAIL_SUPPORT("keysoft.hotro@gmail.com"),
	EMAIL_PASSWORD("1234567a@"),
	EMAIL_SUBJECT("[GPOSADMIN] Password");
	
	public String val;
	
	private MailEnum(String val) {
		this.val = val;
	}
}
