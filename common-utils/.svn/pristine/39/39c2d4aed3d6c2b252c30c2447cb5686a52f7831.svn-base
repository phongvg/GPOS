package com.gg.gpos.common.json;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@SuppressWarnings("serial")
@Data
public class JsonOrderCategory implements Serializable{
	@JsonProperty("id")
	private Long id;
	@JsonProperty("item-id")
	private Long itemId;
	@JsonProperty("code")
	private String code;
	@JsonProperty("name")
	private String name;
	@JsonProperty("status")
	private Integer status;
	@JsonProperty("parent-id")
	private Long orderCategoryParentId = 0L;
	@JsonProperty("right-lvl")
	private String rightLvl = "0";
	@JsonProperty("object-sifr")
	private Integer objectSifr = 0;
	@JsonProperty("flags")
	private Integer flags = 0;
}
