package com.gg.gpos.menu.dto;

import org.springframework.web.multipart.MultipartFile;

import com.gg.gpos.common.util.BaseDto;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper = true)
public class KdsConfigCookingDto extends BaseDto{
	  private Long id;
	  private Integer singleCookingTime;
	  private Integer restaurantCode;
	  private Integer multiCookingTime;
	  private String fasting;
	  private String groupTypeKdsCode;
	  private String groupTypeKdsName;
	  private KdsPlaceDto kdsPlace; 
	  private FoodItemDto foodItem; 
	  private MultipartFile multipartFile;
}
