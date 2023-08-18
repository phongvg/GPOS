package com.gg.gpos.common.json;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class MasterDataMenuItem extends MasterDataBase {
	@JsonProperty("right-lvl")
	private String rightLvl = "0";
	@JsonProperty("modi-scheme")
	private Integer modiScheme = 0;
	@JsonProperty("combo-scheme")
	private Integer comboScheme = 0;
	@JsonProperty("combo-join-mode")
	private Integer comboJoinMode = 0;
	@JsonProperty("sap-code")
	private String sapCode;
	@JsonProperty("cook-mins")
	private Integer cookMins;
	
}
