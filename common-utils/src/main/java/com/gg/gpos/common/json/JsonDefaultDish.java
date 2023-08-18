package com.gg.gpos.common.json;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@SuppressWarnings("serial")
@Data
public class JsonDefaultDish implements Serializable{
	@JsonProperty("menu-item")
	private JsonDishItem jsonDishItem;
}
