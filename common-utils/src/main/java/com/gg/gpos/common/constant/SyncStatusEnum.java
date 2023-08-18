package com.gg.gpos.common.constant;

public enum SyncStatusEnum {
	WAITING("Waiting"),
	INPROCESSING("Inprocessing"),

	WAIT_FOR_SYNC_NOW("Wait_for_sync_now"),
	PROCESSING("Inprocessing"),
	SUCCESS("Success"),
	FAIL("Fail");
	
	public String val;
	
	private SyncStatusEnum(String val) {
		this.val = val;
	}
}
