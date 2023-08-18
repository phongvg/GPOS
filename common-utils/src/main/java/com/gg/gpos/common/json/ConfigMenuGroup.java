package com.gg.gpos.common.json;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;
@SuppressWarnings("serial")
@Data
public class ConfigMenuGroup implements Serializable{
	@JsonView(Views.Normal.class)
	@JsonProperty("id")
	private Long id;
	@JsonView(Views.Normal.class)
	@JsonProperty("code")
	private String code;
	@JsonView(Views.Normal.class)
	@JsonProperty("name")
	private String name;
	
	/*@JsonView(Views.Normal.class)*/
	@JsonProperty("name-en")
	private String nameEn;
	
	@JsonView(Views.Normal.class)
	@JsonProperty("level")
	/*private Integer level = -1;*/
	private Integer level;

	@JsonView(Views.OrderCategory.class)
	@JsonProperty("order-no")
	private Integer orderNo = 0;
	
	//@JsonView(Views.MenuGroup.class)
	@JsonView(Views.Normal.class)
	@JsonProperty("group-type")
	private Integer groupType = 0;
	
	@JsonView(Views.Normal.class)
	@JsonProperty("src-image")
	private String srcImage;
}
