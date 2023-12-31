package com.gg.gpos.res.dto;

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
public class RestaurantDto extends BaseDto {
  private Long id;
  private Integer code;
  private String name;
  private String address;
  private String phone;
  private Integer status;
  private String latitude;
  private String longitude;
  private String email;
  private String ip;
  private String port;
  private Integer online;
  private String version;
  private String unsignedName;
  
  private ProvinceBrandDto provinceBrand;
  private DistrictBrandDto districtBrand;
  private List<SyncStatusDto> syncStatus;
  // For processing in form
  private boolean selected;
  private String username;
  private String selectedRestaurantCodes;
  private List<Integer> restaurantCodes;
  private List<Integer> referenceDatas;
  private boolean checkSyncMasterData;
  private boolean checkSyncMenu;
  private boolean checkSyncMasterDataSuccess;
  private boolean checkSyncMenuSuccess;
  
  private Boolean override;
}