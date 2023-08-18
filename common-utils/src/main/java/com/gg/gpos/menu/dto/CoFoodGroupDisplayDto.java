package com.gg.gpos.menu.dto;

import lombok.Data;

@Data
public class CoFoodGroupDisplayDto {
	private Long id;
	private Long coCategoryIds;
	private String foodGroupCode;
	  
	private boolean selected;
	private CoCategoryDto coCategory;
}
