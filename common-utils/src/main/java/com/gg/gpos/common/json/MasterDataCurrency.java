package com.gg.gpos.common.json;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class MasterDataCurrency {
	@JsonProperty("ident")
	private Long id;
	@JsonProperty("item-ident")
	private Long itemIdent;
	@JsonProperty("status")
	private String status;
	@JsonProperty("rate-class")
	private String rateClass;
	@JsonProperty("code")
	private String code;
}
