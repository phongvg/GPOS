package com.gg.gpos.common.json;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@SuppressWarnings("serial")
@Data
public class JsonCOMenuItem implements Serializable{
	@JsonProperty("code")
	private String code;
	@JsonProperty("name-vn")
	private String nameVn;
	@JsonProperty("name-en")
	private String nameEn;
	@JsonProperty("price")
	private Double price;
	@JsonProperty("kalo")
	private Integer kalo;
}
