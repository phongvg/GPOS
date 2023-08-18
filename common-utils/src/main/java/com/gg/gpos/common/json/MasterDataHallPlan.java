package com.gg.gpos.common.json;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class MasterDataHallPlan extends MasterDataBase {
	@JsonProperty("is-current-res")
	private boolean isCurrentRes;
	
}
