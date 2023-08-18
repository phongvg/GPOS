package com.gg.gpos.menu.dto;

import com.gg.gpos.common.util.BaseDto;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper = true)
public class KitchenTypeDto extends BaseDto{
	  private Long id;
	  private Integer restaurantCode;
	  private String name;
}
