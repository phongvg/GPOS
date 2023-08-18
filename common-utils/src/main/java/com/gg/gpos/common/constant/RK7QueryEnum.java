package com.gg.gpos.common.constant;

public enum RK7QueryEnum {
	CATEGORY("<?xml version=\"1.0\" encoding=\"utf-8\"?><RK7Query><RK7Command CMD=\"GetRefData\" RefName=\"CategList\" WithChildItems=\"0\" WithMacroProp=\"1\" IgnoreEnums=\"1\"/></RK7Query>"),
	CURRENCY("<?xml version=\"1.0\" encoding=\"utf-8\"?><RK7Query><RK7CMD CMD=\"GetRefData\" RefName=\"Currencies\"/></RK7Query>"),
	CURRENCY_RATE("<?xml version=\"1.0\" encoding=\"utf-8\"?><RK7Query><RK7CMD CMD=\"GetRefData\" RefName=\"RateClasses\"/></RK7Query>"),
	HALL_PLAN("<?xml version=\"1.0\" encoding=\"utf-8\"?><RK7Query><RK7Command CMD=\"GetRefData\" RefName=\"HallPlans\"  WithChildItems=\"0\" WithMacroProp=\"1\" IgnoreEnums=\"1\"  PropMask=\"items.(Ident,MainParentIdent,Name,Code,Status,Items.(*))\"/></RK7Query>"),
	MENU_ITEM("<?xml version=\"1.0\" encoding=\"utf-8\"?><RK7Query><RK7Command CMD=\"GetRefData\" RefName=\"MenuItems\" IgnoreEnums=\"1\"/></RK7Query>"),
	MODIFIER("<?xml version=\"1.0\" encoding=\"utf-8\"?><RK7Query><RK7Command CMD=\"GetRefData\" RefName=\"Modifiers\" IgnoreEnums=\"1\"/></RK7Query>"),
	MODI_GROUP("<?xml version=\"1.0\" encoding=\"utf-8\"?><RK7Query><RK7Command CMD=\"GetRefData\" RefName=\"MODIGROUPS\" IgnoreEnums=\"1\"/></RK7Query>"),
	MODI_SCHEME("<?xml version=\"1.0\" encoding=\"utf-8\"?><RK7Query><RK7Command CMD=\"GetRefData\" RefName=\"MODISCHEMES\" WithChildItems=\"0\" WithMacroProp=\"1\" IgnoreEnums=\"1\"/></RK7Query>"),
	MODI_SCHEME_DETAIL("<?xml version=\"1.0\" encoding=\"utf-8\"?><RK7Query><RK7Command CMD=\"GetRefData\" RefName=\"MODISCHEMEDETAILS\" WithChildItems=\"0\" WithMacroProp=\"1\" IgnoreEnums=\"1\"/></RK7Query>"),
	ORDER_CATEGORY("<?xml version=\"1.0\" encoding=\"utf-8\"?><RK7Query><RK7Command CMD=\"GetRefData\" RefName=\"UnchangeableOrderTypes\" IgnoreEnums=\"1\" WithMacroProp=\"1\" IgnoreEnums=\"1\" OnlyActive = \"1\"/></RK7Query>"),
	ORDER_TYPE("<?xml version=\"1.0\" encoding=\"utf-8\"?><RK7Query><RK7Command CMD=\"GetRefData\" RefName=\"ChangeableOrderTypes\" IgnoreEnums=\"1\" WithMacroProp=\"1\" IgnoreEnums=\"1\" OnlyActive = \"1\"/></RK7Query>"),
	ORDER_VOID("<?xml version=\"1.0\" encoding=\"utf-8\"?><RK7Query><RK7Command CMD=\"GetRefData\" RefName=\"OrderVoids\" IgnoreEnums=\"1\" WithMacroProp=\"1\" OnlyActive = \"1\"/></RK7Query>"),
	EMPLOYEE("<?xml version=\"1.0\" encoding=\"utf-8\"?><RK7Query><RK7Command CMD=\"GetRefData\" RefName=\"Employees\"  WithChildItems=\"0\" WithMacroProp=\"1\" IgnoreEnums=\"1\"  PropMask=\"items.(Ident,MainParentIdent,Name,Code,Status)\"/></RK7Query>"),
	GUEST_TYPE("<?xml version=\"1.0\" encoding=\"utf-8\"?><RK7Query><RK7Command CMD=\"GetRefData\" RefName=\"GuestTypes\" WithChildItems=\"0\" WithMacroProp=\"1\" IgnoreEnums=\"1\"/></RK7Query>"),
	TABLE("<?xml version=\"1.0\" encoding=\"utf-8\"?><RK7Query><RK7Command CMD=\"GetRefData\" RefName=\"Tables\" WithChildItems=\"0\" WithMacroProp=\"1\" IgnoreEnums=\"1\"/></RK7Query>"),
	RESTAURANT("<?xml version=\"1.0\" encoding=\"utf-8\"?><RK7Query><RK7Command CMD=\"GetRefData\" RefName=\"Restaurants\" WithChildItems=\"0\" WithMacroProp=\"1\" IgnoreEnums=\"1\" /></RK7Query>"),
	SAP_INFO("<?xml version=\"1.0\" encoding=\"utf-8\"?><RK7Query><RK7CMD CMD=\"GetRefData\" RefName=\"GENERATEDPROPDATAS\"/></RK7Query>");
	
	private String query;
	
	RK7QueryEnum(String query) {
		this.query = query;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

}
