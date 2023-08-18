package com.gg.gpos.menu.dto;

import com.gg.gpos.common.util.BaseDto;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper = true)
public class CatalogDataEditDto extends BaseDto{
	  private Long id;
	  private String type;
	  private Long catalogId;
	  private String value;
}
