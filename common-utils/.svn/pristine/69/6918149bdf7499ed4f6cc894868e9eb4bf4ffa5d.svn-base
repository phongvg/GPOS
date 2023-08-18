package com.gg.gpos.common.json;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@SuppressWarnings("serial")
@Data
public class ConfigHallplan implements Serializable{
	@JsonProperty("id")
	private Long id;
	@JsonProperty("item-id")
	private Long itemId;
	@JsonProperty("parent-id")
	private Long parentId = 0L;
	@JsonProperty("code")
	private String code;
	@JsonProperty("name")
	private String name;
	@JsonProperty("status")
	private Integer status;
	@JsonProperty("station-code")
	private String stationCode;
	@JsonProperty("printer")
	private String printer;
	@JsonProperty("is-current-res")
	private boolean isCurrentRes;
	@JsonProperty("object-sifr")
	private Integer objectSifr = 0;
	@JsonProperty("flags")
	private Integer flags = 0;
	
}
