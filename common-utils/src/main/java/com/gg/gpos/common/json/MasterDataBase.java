package com.gg.gpos.common.json;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class MasterDataBase {
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
	@JsonProperty("object-sifr")
	private Integer objectSifr = 0;
	@JsonProperty("flags")
	private Integer flags = 0;
}
