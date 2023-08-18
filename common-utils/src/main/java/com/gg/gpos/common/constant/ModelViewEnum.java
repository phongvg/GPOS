package com.gg.gpos.common.constant;

public enum ModelViewEnum {
	MODEL_PAGE("page"),
	MODEL_SYSTEM_PARAMETER("systemParameter"),
	MODEL_SYSTEM_PARAMETERS("systemParameters"),
	VIEW_SYSTEM_PARAMETER_LIST("systemParameter-list"),
	VIEW_SYSTEM_PARAMETER_FORM("systemParameter-form"),
	
	MODEL_USER("user"),
	MODEL_USERS("users"),
	MODEL_GROUP("group"),
	MODEL_GROUPS("groups"),
	MODEL_ORGANIZATION("organization"),
	MODEL_ORGANIZATIONS("organizations"),
	MODEL_POSITION("position"),
	MODEL_POSITIONS("positions"),
	MODEL_STAFF("staff"),
	MODEL_STAFFS("staffs"),
	MODEL_ROLE("role"),
	MODEL_ROLES("roles"),
	
	MODEL_CRITERIA("criteria"),
	MODEL_MENUS("menus"),
	MODEL_MENU("menu"),
	MODEL_DEVICE_DISPLAY("deviceDisplay"),
	MODEL_DEVICE_DISPLAYS("deviceDisplays"),
	
	VIEW_USER_LIST("user-list"),
	VIEW_USER_FORM("user-form"),
	VIEW_GROUP_LIST("group-list"),
	VIEW_GROUP_FORM("group-form"),
	VIEW_STAFF_LIST("staff-list"),
	VIEW_STAFF_FORM("staff-form"),
	VIEW_ORGANIZATION_LIST("organization-list"),
	VIEW_ORGANIZATION_FORM("organization-form"),
	
	VIEW_CO_LIST("co-list"),
	VIEW_CO_FORM("co-form"),
	VIEW_SO_LIST("so-list"),
	
	VIEW_CHANGE_PASS("change-pass"),
	REDIRECT_USER_LIST("redirect:/user/list"),
	REDIRECT_GROUP_LIST("redirect:/group/list"),
	REDIRECT_STAFF_LIST("redirect:/staff/list"),
	REDIRECT_ORGANIZATION_LIST("redirect:/organization/list"),
	REDIRECT_CO_LIST("redirect:/co/list"),
	REDIRECT_SO_LIST("redirect:/so/list");

	public String mav;

	private ModelViewEnum(final String view) {
		mav = view;
	}
}
