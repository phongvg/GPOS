package com.gg.gpos.common.json;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class MasterDataOrderCategory extends MasterDataBase {
	@JsonProperty("right-lvl")
	private String rightLvl = "0";
}
