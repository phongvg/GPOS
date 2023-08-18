package com.gg.gpos.common.json;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class MasterDataTable extends MasterDataBase {
	@JsonProperty("right-lvl")
	private String rightLvl = "0";
	@JsonProperty("hall-plan-id")
	private Integer hallPlanId;
	@JsonProperty("is-current-res")
	private boolean isCurrentRes;
	
}
