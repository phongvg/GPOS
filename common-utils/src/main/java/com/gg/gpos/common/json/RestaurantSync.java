package com.gg.gpos.common.json;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
@SuppressWarnings("serial")
@Data
public class RestaurantSync implements Serializable{
	private String resCodes;
	
	// For SO
	private List<ConfigMenuEngineering> menuEngineerings = new ArrayList<>();
	private List<JsonMenuGroupDetail> menuGroups = new ArrayList<>();
	
	// For CO
	private List<ConfigCOCategory> coCategories = new ArrayList<>();
	private List<ConfigCOMenuItem> coMenuItems = new ArrayList<>();
	private List<ConfigCOModifier> coModifiers = new ArrayList<>();
	private List<ConfigCOSpecialType> coSpecials = new ArrayList<>();
	
	// For KDS
	private List<ConfigPrintGroup> printGroups = new ArrayList<>();
	private List<ConfigPlace> places = new ArrayList<>();
	private List<ConfigHallplan> hallplans = new ArrayList<>();
	
	// For Param
	private List<ConfigParam> params = new ArrayList<>();
	
	// Delete CO
	private List<JsonDeleteCOMenuItem> deleteCOMenuItems = new ArrayList<>();
	private List<JsonDeleteCoCategory> deleteCoCategories = new ArrayList<>();
	private List<ConfigMenuEngineering> deleteSoCategoryItems = new ArrayList<>();
	
	// Delete Param
	private List<JsonDeleteParam> deleteParams = new ArrayList<>();
	
	// FileContet
	private List<FileContent> fileContents = new ArrayList<>();
	private JsonKaloGroup jsonKaloGroup;
	
}
