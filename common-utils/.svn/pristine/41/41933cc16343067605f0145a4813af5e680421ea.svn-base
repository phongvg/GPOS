package com.gg.gpos.common.json;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper=false)
public class JsonMenuGroupDetail extends ConfigMenuGroup implements Serializable {
	@JsonProperty("details")
	private List<JsonMenuItem> jsonMenuItems = new ArrayList<>();
}
