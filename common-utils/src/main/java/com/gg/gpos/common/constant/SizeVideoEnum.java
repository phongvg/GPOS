package com.gg.gpos.common.constant;

public enum SizeVideoEnum {

	// size upload video less than 100MB (104857600) 
		SIZE_VIDEO(104857600);
		public Integer size;
	
		private SizeVideoEnum(Integer size) {
			this.size = size;
		}
}
