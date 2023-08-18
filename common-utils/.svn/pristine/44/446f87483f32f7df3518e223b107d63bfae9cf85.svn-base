package com.gg.gpos.common.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class MasterDataRestaurant extends MasterDataBase {
	@JsonView(Views.MasterDataRestaurant.class)
	@JsonProperty("gen-gggrname")
	private String genGggRname;
	
	@JsonView(Views.MasterDataRestaurant.class)
	@JsonProperty("gen-gggtel")
	private String genGggTel;
	
	@JsonView(Views.MasterDataRestaurant.class)
	@JsonProperty("gen-gggadd")
	private String genGggAdd;
	
	@JsonView(Views.MasterDataRestaurant.class)
	@JsonProperty("gen-gggweb")
	private String genGggWeb;
	
	@JsonView(Views.MasterDataRestaurant.class)
	@JsonProperty("gen-gggrco")
	private String genGggRco;
	
	@JsonView(Views.MasterDataRestaurant.class)
	@JsonProperty("gen-sapcode")
	private String genSapCode;
	
	@JsonView(Views.MasterDataRestaurant.class)
	@JsonProperty("gen-gggbrand")
	private String genGggBrand;
	
	@JsonView(Views.MasterDataRestaurant.class)
	@JsonProperty("gen-sapbankterminalid")
	private String genSapBankTerminalId;
}
