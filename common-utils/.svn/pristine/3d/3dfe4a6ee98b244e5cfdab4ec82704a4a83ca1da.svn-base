package com.gg.gpos.common.json;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;

@Data
public class MasterDataSync {
	@JsonView(Views.Normal.class)
	@JsonProperty("version")
	private Integer version;
	
	@JsonView(Views.MasterDataMenuItem.class)
	@JsonProperty("lst-menu-item")
	private List<MasterDataMenuItem> masterDataMenuItems = new ArrayList<>();
	
	@JsonView(Views.MasterDataCategList.class)
	@JsonProperty("lst-categ-list")
	private List<MasterDataCategList> masterDataCategLists = new ArrayList<>();;
	
	@JsonView(Views.MasterDataOrderType.class)
	@JsonProperty("lst-order-type")
	private List<MasterDataOrderType> masterDataOrderTypes = new ArrayList<>();;
	
	@JsonView(Views.MasterDataOrderCategory.class)
	@JsonProperty("lst-order-category")
	private List<MasterDataOrderCategory> masterDataOrderCategories = new ArrayList<>();;
	
	@JsonView(Views.MasterDataModifier.class)
	@JsonProperty("lst-modifier")
	private List<MasterDataModifier> masterDataModifiers = new ArrayList<>();;
	
	@JsonView(Views.MasterDataModiGroups.class)
	@JsonProperty("lst-modi-groups")
	private List<MasterDataModiGroups> masterDataModiGroups = new ArrayList<>();;
	
	@JsonView(Views.MasterDataModiScheme.class)
	@JsonProperty("lst-modi-scheme")
	private List<MasterDataModiScheme> masterDataModiSchemes = new ArrayList<>();;

	@JsonView(Views.MasterDataModiSchemeDetail.class)
	@JsonProperty("lst-modi-scheme-detail")
	private List<MasterDataModiSchemeDetail> masterDataModiSchemeDetails = new ArrayList<>();;
	
	@JsonView(Views.MasterDataOrderVoid.class)
	@JsonProperty("lst-order-void")
	private List<MasterDataOrderVoid> masterDataOrderVoids = new ArrayList<>();;

	@JsonView(Views.MasterDataCurrency.class)
	@JsonProperty("lst-currency")
	private List<MasterDataCurrency> masterDataCurrencies = new ArrayList<>();;

	@JsonView(Views.MasterDataCurrencyRate.class)
	@JsonProperty("lst-currency-rate")
	private List<MasterDataCurrencyRate> masterDataCurrencyRates = new ArrayList<>();;
	
	@JsonView(Views.MasterDataTable.class)
	@JsonProperty("lst-table")
	private List<MasterDataTable> masterDataTables = new ArrayList<>();;
	
	@JsonView(Views.MasterDataHallPlan.class)
	@JsonProperty("lst-hall-plan")
	private List<MasterDataHallPlan> masterDataHallPlans = new ArrayList<>();;
	
	@JsonView(Views.MasterDataRestaurant.class)
	@JsonProperty("lst-restaurant")
	private List<MasterDataRestaurant> masterDataRestaurants = new ArrayList<>();;
	
	@JsonView(Views.MasterDataEmployee.class)
	@JsonProperty("lst-employee")
	private List<MasterDataEmployee> masterDataEmployees = new ArrayList<>();;
	
	@JsonView(Views.MasterDataGuestType.class)
	@JsonProperty("lst-guest-type")
	private List<MasterDataGuestType> masterDataGuestTypes = new ArrayList<>();;
	
}
