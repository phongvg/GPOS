package com.gg.gpos.common.json;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@SuppressWarnings("serial")
@Data
public class ConfigCOMenuItem implements Serializable{
	@JsonIgnore
	private Long id;
	@JsonProperty("code")
	private String code;
	@JsonProperty("name-vn")
	private String nameVn;
	@JsonProperty("name-en")
	private String nameEn;
	@JsonProperty("desc-vn")
	private String descVn;
	@JsonProperty("desc-en")
	private String descEn;
	@JsonProperty("position-number")
	private Integer positionNumber;
	@JsonProperty("max-dish-on-session")
	private Integer maxPerTransaction;
	@JsonProperty("max-dish-to-kit")
	private Integer maxForKitchen;
	@JsonProperty("src-img-thumbnail")
	private String srcImgThumbnail;
	@JsonProperty("src-img")
	private String srcImg;
	
	@JsonProperty("view-type")
	private Integer viewType;
	@JsonProperty("other-name")
	private String bufferLabel;
	@JsonProperty("img-size")
	private Integer coImageSize;
	@JsonProperty("src-img-half")
	private String srcImgHalf;
	@JsonProperty("src-img-topping")
	private String srcImgTopping;
	@JsonProperty("src-img-group")
	private String srcImgGroup;
	@JsonProperty("src-img-group-hidden")
	private String srcImgGroupHidden;
	@JsonProperty("src-img2x-horizontal")
	private String srcImg2xHorizontal;
	@JsonProperty("src-img2x-vertical")
	private String srcImg2xVertical;
	@JsonProperty("src-img-quarter")
	private String srcImgQuarter;
	@JsonProperty("time-modify")
	private Long timeModify;
	@JsonProperty("lst-note")
	private List<String> infoFoodItem;

	@JsonProperty("related-items")
	private List<String> relatedItems = new ArrayList<>();
	@JsonProperty("lst-item-size")
	private List<JsonItemSize> itemSizes = new ArrayList<>();
	@JsonProperty("modifiers")
	private List<ConfigCOModifier> modifiers = new ArrayList<>();
	@JsonProperty("item-special-types")
	private List<ConfigCOSpecialType> itemSpecialTypes = new ArrayList<>();
	@JsonProperty("co-menu-item-details")
	private List<JsonCOMenuItem> coMenuItemDetails = new ArrayList<>();
	@JsonProperty("lst-topping")
	private List<JsonToppingFoodItem> itemToppings = new ArrayList<>();
	@JsonProperty("extra-item")
	private JsonExtraItem extraItem;
	
	@JsonProperty("kalo-group-code")
	private String kaloGroupCode;
	@JsonProperty("kalo-group-name")
	private String kaloGroupName;
	@JsonProperty("sap-code")
	private String sapCode;
	
	@JsonProperty("number-of-people-eat")
	private Integer numberOfPeopleEat;
	@JsonProperty("src-img-square")
	private String srcImgSquare;
	@JsonProperty("hidden-icon-choose-item")
	private Integer hideIcon;
	@JsonProperty("note")
	private String note;
	@JsonProperty("note-quantitative")
	private String noteQuantitative;
	
	@JsonProperty("src-img-qr")
	private String srcImgQr;
	@JsonProperty("url-img-qr")
	private String urlImgQr;
}
