package com.gg.gpos.io.dto;

import lombok.Data;

@Data
public class IOKdsPlaceDto {
	  private String code;
	  private String name;
	  private String type;
	  private String printer;
	  private String hallplanIds;
	  private Integer restaurantCode;
	  private String error;
	  private boolean status;
	
}
