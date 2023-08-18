package com.gg.gpos.common.json;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class RestaurantMenuDataSync implements Serializable{
	private String resCode;
	
	// thông tin SO_MENU
	private List<ConfigMenuEngineering> menuEngineerings = new ArrayList<>();
	private List<JsonMenuGroupDetail> menuGroups = new ArrayList<>();
	private List<ConfigMenuEngineering> deleteSoCategoryItems = new ArrayList<>();
	
	// thông tin CO_MENU
	private List<ConfigCOCategory> coCategories = new ArrayList<>();
	private List<ConfigCOMenuItem> coMenuItems = new ArrayList<>();
	private List<ConfigCOModifier> coModifiers = new ArrayList<>();
	private List<ConfigCOSpecialType> coSpecials = new ArrayList<>();
	private JsonKaloGroup jsonKaloGroup;
	private List<JsonDeleteCOMenuItem> deleteCOMenuItems = new ArrayList<>();
	private List<JsonDeleteCoCategory> deleteCoCategories = new ArrayList<>();
	// Thông tin RESTAURANT_INFO (Tab cấu hình ảnh ở danh mục CO)
	private ConfigRestaurantInfo configRestaurantInfo;
	
	// thông tin cấu hình FAX bếp
	private List<ConfigPrintGroup> printGroups = new ArrayList<>();
	private List<ConfigPlace> places = new ArrayList<>();
	private List<ConfigHallplan> hallplans = new ArrayList<>();
	
	// thông tin GROUP_PARAM
	private List<ConfigParam> configParams = new ArrayList<>();
	private List<JsonDeleteParam> deleteParams = new ArrayList<>();
	
	// thông tin file ATTACHMENT
	private List<FileContent> fileContents = new ArrayList<>();
	private List<Long> coFoodItemIds = new ArrayList<>();
	private List<Long> coCategoryIds = new ArrayList<>();
	private List<Long> foodGroupIds = new ArrayList<>();
}