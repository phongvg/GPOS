package com.gg.gpos.menu.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.gg.gpos.common.util.BaseDto;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper = true)
public class GroupParamDto extends BaseDto {
	private Long id;
	  private String name;
	  private boolean status;
	  private String createdBy;
	  private LocalDateTime createdDate;
	  private String modifiedBy;
	  private LocalDateTime modifiedDate;
	  private List<ParamDto> params;
	  public String getDisplayCreatedDate() {
		  if (createdDate != null) {
			  return DateTimeFormatter.ofPattern("dd-MM-yyyy").format(createdDate);  
		  } else {
			  return null;
		  }
	  }
	  public String getDisplayModifiedDate() {
		  if (modifiedDate != null) {
			  return DateTimeFormatter.ofPattern("dd-MM-yyyy").format(modifiedDate);  
		  } else {
			  return null;
		  }
	  }
	  
	  private String selectedRestaurantCodes;
	  private Integer restaurantCode;
	  private List<Integer> resCodes;
	  private Boolean override;
}
