package com.gg.gpos.common.json;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class JsonToppingFoodItem implements Serializable{
	@JsonProperty("code")
	private String foodItemCode;
	@JsonProperty("name")
	private String foodItemName;
	@JsonProperty("modifier-code")
	private String modifierCode;
	@JsonProperty("modifier-name")
	private String modifierName;
	@JsonProperty("name-vn")
	private String foodItemNameVn;
	@JsonProperty("name-en")
	private String foodItemNameEn;
}
