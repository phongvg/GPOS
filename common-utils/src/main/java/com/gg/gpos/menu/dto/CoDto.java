package com.gg.gpos.menu.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

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
public class CoDto  extends BaseDto{
  private Long id;
  private Long soId;
  private String name;
  private boolean status;
  private String createdBy;
  private LocalDateTime createdDate;
  private String modifiedBy;
  private LocalDateTime modifiedDate;
  
  private Integer restaurantCode;
  private List<CoFoodItemDto> coFoodItems;
  private String selectedRestaurantCodes;
  private List<Integer> resCodes;
  private Integer codeToRes;
  private List<Long> newModuleIds;
  private List<Long> currentModuleIds;
  private List<Long> oldModuleIds;
  //========= coCategoryId ====//
  private List<Long> newCoCategoryIds;
  private List<Long> oldCoCategoryIds;
  private List<Long> currentCoCategoryIds;
  private MultipartFile fileImport;
  private boolean override;
  
  private String displayCreatedDate;
  public String getDisplayCreatedDate() {
	  if (createdDate != null) {
		  return DateTimeFormatter.ofPattern("dd-MM-yyyy").format(createdDate);  
	  } else {
		  return null;
	  }
  }
  
  private String displayModifiedDate;
  public String getDisplayModifiedDate() {
	  if (modifiedDate != null) {
		  return DateTimeFormatter.ofPattern("dd-MM-yyyy").format(modifiedDate);  
	  } else {
		  return null;
	  }
  }
  
  private List<MultipartFile> photos;
  
  private String moduleType;
}