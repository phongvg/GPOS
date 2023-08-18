package com.gg.gpos.reference.search;

import com.gg.gpos.common.util.BaseSearchCriteria;

public class SystemParameterSearchCriteria extends BaseSearchCriteria {
    private String paramName;
    private String paramValue;
    private String description;

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
