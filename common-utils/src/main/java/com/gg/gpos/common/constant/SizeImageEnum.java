package com.gg.gpos.common.constant;

public enum SizeImageEnum {
	
	// size upload image less than 3MB 3145728
	SIZE(3145728),
	CO_FOODITEM_SIZE(1048756),
	CO_CATEGORY_SIZE(2097512),
	FILE_SIZE(10);
	


	/** The status. */
	public Integer sizeImage;

	private SizeImageEnum(Integer sizeImage) {
		this.sizeImage = sizeImage;
	}
	
}
