package com.gg.gpos.menu.dto;

import com.gg.gpos.common.util.BaseDto;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper = true)
public class CatalogApplyToRestaurantDto extends BaseDto{
	  private Long id;
	  private Integer restaurantCode;
	  private Long coId;
	  private Long soId;
	  private Long groupParamId;
}
