package com.gg.gpos.common.json;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@SuppressWarnings("serial")
@Data
public class ConfigMenuEngineering implements Serializable{
	@JsonProperty("order-category")
	private JsonOrderCategory jsonOrderCategory;
	@JsonProperty("menu-type")
	private String menuType = "";
	@JsonProperty("default-dish")
	private JsonDefaultDish jsonDefaultDish;
	@JsonProperty("child-dish")
	private JsonChildDish jsonChildDish;
	
	@JsonProperty("menu-groups")
	private List<JsonMenuGroupDetail> jsonMenuGroups = new ArrayList<>();
}
