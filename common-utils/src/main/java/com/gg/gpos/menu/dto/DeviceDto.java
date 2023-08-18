package com.gg.gpos.menu.dto;

import com.gg.gpos.common.util.BaseDto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DeviceDto extends BaseDto {
	  private Long id;
	  private Integer restaurantCode;
	  private Long tableKitchenId;
	  private Long deviceId;
	  
	  private String restaurantDeviceSelected;
	  private String tableIdSelected;
	  private String deviceInTableSelected;
	  private boolean check;
	  
	  private String tableKitchenText;
}
