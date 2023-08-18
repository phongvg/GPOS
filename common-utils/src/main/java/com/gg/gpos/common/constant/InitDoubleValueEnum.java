package com.gg.gpos.common.constant;

public enum InitDoubleValueEnum {
	ZERO(0D),
	ONE(-1D);

	public Double val;

	private InitDoubleValueEnum(Double val) {
		this.val = val;
	}
}
