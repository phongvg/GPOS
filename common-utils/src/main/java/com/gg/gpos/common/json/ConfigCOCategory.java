package com.gg.gpos.common.json;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class ConfigCOCategory implements Serializable{
	@JsonIgnore
	private Long id;
	@JsonProperty("code")
	private String orderCategoryCode;
	@JsonProperty("name-vn")
	private String nameVn;
	@JsonProperty("name-en")
	private String nameEn;
	@JsonProperty("desc-vn")
	private String descVn;
	@JsonProperty("desc-en")
	private String descEn;
	@JsonProperty("type")
	private Integer type;
	@JsonProperty("alacarte")
	private String alacarteLabel;
	@JsonProperty("buffet")
	private String buffetLabel;
	@JsonProperty("drinks")
	private String drinksLabel;
	@JsonProperty("time-modify")
	private Long timeModify;
	@JsonProperty("alacarte-en")
	private String alacarteLabelEn;
	@JsonProperty("buffet-en")
	private String buffetLabelEn;
	@JsonProperty("drinks-en")
	private String drinksLabelEn;
	@JsonProperty("restaurant-intro-location")
	private Integer photoDisplayPosition;
	@JsonProperty("average-amount")
	private Long averageAmount;
	@JsonProperty("note")
	private String note;
	@JsonProperty("position-number")
	private Integer positionNumber;
	
	@JsonProperty("menu-group-codes")
	private List<String> menuGroupCodes = new ArrayList<>();
	
	@JsonProperty("src-img")
	private String srcImg;
	@JsonProperty("src-img-intros")
	private List<String> srcImgIntros = new ArrayList<>();
	@JsonProperty("src-video-intros")
	private List<String> srcVideoIntros = new ArrayList<>();
	@JsonProperty("restaurant-lst-video")
	private List<String> restaurantLstVideo = new ArrayList<>();
	@JsonProperty("restaurant-lst-img")
	private List<String> restaurantLstImg = new ArrayList<>();
	@JsonProperty("src-img-multi-category")
	private String srcImgMultiCategory;
	@JsonProperty("src-img-single-category")
	private String srcImgSingleCategory;
	@JsonProperty("lst-image-digital")
	private List<JsonDigitalMenu> digitalMenus = new ArrayList<>();
}
