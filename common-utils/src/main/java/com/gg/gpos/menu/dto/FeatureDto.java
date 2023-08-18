package com.gg.gpos.menu.dto;

import java.util.List;

import com.gg.gpos.common.util.BaseDto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Generated by Speed Generator
 * 
 * @author <a href="mailto:ngtrungkien@gmail.com">Kien Nguyen</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FeatureDto extends BaseDto {
  private Long id;
  private Long parentId;
  private Integer restaurantCode;
  private String code;
  private String nameEn;
  private String nameVn;
  private Integer status;
  private String descVn;
  private String descEn;
  
  private boolean selected;
  private boolean checkCode;
  private List<CoFoodItemDto> coFoodItems;
}