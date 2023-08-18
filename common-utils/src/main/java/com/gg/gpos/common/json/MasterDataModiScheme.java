package com.gg.gpos.common.json;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class MasterDataModiScheme extends MasterDataBase {
	@JsonProperty("ignore-default-for-kitchen")
	private Integer ignoreDefaultForKitchen;
	@JsonProperty("source-ident")
	private Integer sourceIdent;
	@JsonProperty("modi-scheme-type")
	private Integer modiSchemeType;
	@JsonProperty("edit-right")
	private Integer editRight;
	@JsonProperty("active-hierarchy")
	private Integer activeHierarchy;
	@JsonProperty("assign-childs-on-server")
	private Integer assignChildsOnServer;
	@JsonProperty("auto-open")
	private Integer autoOpen;
	
}
