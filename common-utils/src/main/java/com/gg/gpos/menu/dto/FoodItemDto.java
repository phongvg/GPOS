package com.gg.gpos.menu.dto;

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
public class FoodItemDto extends BaseDto {
	  private Long id;
	  private Long itemId;
	  private Long parentId;
	  private String type;
	  private String code;
	  private String name;
	  private String unsignedName;
	  private Integer status;
	  private Integer modiScheme;
	  private Integer comboScheme;
	  private Integer comboJoinMode;
	  private String rightLvl;
	  private Integer objectSifr;
	  private Integer flags;
	  private String sapCode;
	  private Integer cookMins;
	  
	  //private FoodItemDto parent;
	  
	  public List<FeatureDto> features;
	  public List<FoodGroupItemDto> foodGroupItems;
	  
	  public List<Long> printGroupIds;
	  public List<PrintGroupDto> printGroups;
	  
	  // For processing in form
	  private boolean selected;
	  private List<Long> featureIds;
	  private List<MultipartFile> photos;
	  private List<String> photoUrls;
	  private MultipartFile avatar;
	  private String avatarUrl;
	  private Long soId;
	  private Integer restaurantCode;
	  private String nameDisplay;
}