package com.gg.gpos.common.json;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@SuppressWarnings("serial")
@Data
public class ConfigParam implements Serializable{
	@JsonProperty("param-code")
	private String paramCode;
	@JsonProperty("param-name")
	private String name;
	@JsonProperty("status")
	private Integer status = 0;
	@JsonProperty("value")
	private String paramValue;
	@JsonProperty("param-type")
	private String type;
	@JsonProperty("note")
	private String description;
	@JsonProperty("check-time-range")
	private Integer checkTimeRange = 0;
}
