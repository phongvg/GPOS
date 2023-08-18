package com.gg.gpos.common.constant;

public enum ReferenceDataEnum {
	CATEGORY(1, "api-master-categlist-pattern","CATEGORY","API_MASTER_CATEGLIST_PATTERN"),
	CURRENCY(2, "api-master-currency-pattern","CURRENCY","API_MASTER_CURRENCY_PATTERN"),
	FOOD_ITEM(3, "api-master-menuitem-pattern","FOOD_ITEM","API_MASTER_MENUITEM_PATTERN"),
	MODI_GROUP(4, "api-master-modigroup-pattern","MODI_GROUP","API_MASTER_MODIGROUPS_PATTERN"),
	MODI_SCHEME(5, "api-master-modischeme-pattern","MODI_SCHEME","API_MASTER_MODISCHEME_PATTERN"),
	MODI_SCHEME_DETAIL(6, "api-master-modischemedetail-pattern","MODI_SCHEME_DETAIL","API_MASTER_MODISCHEMEDETAIL_PATTERN"),
	MODIFIER(7, "api-master-modifier-pattern","MODIFIER","API_MASTER_MODIFIER_PATTERN"),
	ORDER_CATEGORY(8, "api-master-category-pattern","ORDER_CATEGORY","API_MASTER_CATEGORY_PATTERN"),
	ORDER_TYPE(9, "api-master-ordertype-pattern","ORDER_TYPE","API_MASTER_ORDERTYPE_PATTERN"),
	ORDER_VOID(10, "api-master-ordervoid-pattern","ORDER_VOID","API_MASTER_ORDERVOID_PATTERN"),
	CURRENCY_RATE(11, "api-master-currencyrate-pattern","CURRENCY_RATE","API_MASTER_CURRENCYRATE_PATTERN"),
	HALL_PLAN(12, "api-master-hallplan-pattern","HALL_PLAN","API_MASTER_HALLPLAN_PATTERN"),
	TABLE_KITCHEN(13, "api-master-table-pattern","TABLE_KITCHEN","API_MASTER_TABLE_PATTERN"),
	GUEST_TYPE(14, "api-master-guesttype-pattern","GUEST_TYPE","API_MASTER_GUESTTYPE_PATTERN"),
	EMPLOYEE(15, "api-master-employee-pattern","EMPLOYEE","API_MASTER_EMPLOYEE_PATTERN"),
	RESTAURANT(16, "api-master-restaurant-pattern","RESTAURANT","API_MASTER_RESTAURANT_PATTERN");
	
	
	public Integer key;
	public String valSysParam;
	public String valTableName;
	public String enumSysParam;
	
	ReferenceDataEnum(Integer key, String valSysParam, String valTableName,String enumSysParam){
		this.key = key;
		this.valSysParam = valSysParam;
		this.valTableName = valTableName;
		this.enumSysParam = enumSysParam;
	}
}
