package com.gg.gpos.common.json;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class ConfigLogoutJson implements Serializable {
	@JsonProperty("user-code")
	private String userCode;
}
