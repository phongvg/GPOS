package com.gg.gpos.common.json;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class MasterDataModiSchemeDetail {
	@JsonProperty("id")
	private Long id;
	@JsonProperty("item-id")
	private Long itemId;
	@JsonProperty("parent-id")
	private Long parentId = 0L;
	@JsonProperty("object-sifr")
	private Integer objectSifr = 0;
	@JsonProperty("flags")
	private Integer flags = 0;
	@JsonProperty("changes-price")
	private Integer changesPrice;
	@JsonProperty("use-down-limit")
	private Integer useDownLimit;
	@JsonProperty("up-limit")
	private Integer upLimit;
	@JsonProperty("free-count")
	private Integer freeCount;
	@JsonProperty("source-ident")
	private Integer sourceIdent;
	@JsonProperty("sort-num")
	private Integer sortNum;
	@JsonProperty("read-only-name")
	private String readOnlyName;
	@JsonProperty("use-up-limit")
	private Integer useUpLimit;
	@JsonProperty("modi-group")
	private Integer modiGroup;
	@JsonProperty("sh-quantity")
	private Integer shQuantity;
	@JsonProperty("assign-childs-on-server")
	private Integer assignChildsOnServer;
	@JsonProperty("replace-def-modifier")
	private Integer replaceDefModifier;
	@JsonProperty("down-limit")
	private Integer downLimit;
	@JsonProperty("default-modifier")
	private Integer defaultModifier;
	@JsonProperty("modi-scheme")
	private Integer modiScheme;
	
}
