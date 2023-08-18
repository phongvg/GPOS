package com.gg.gpos.common.json;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class JsonKaloGroup implements Serializable{
	@JsonProperty("version")
	private String version;
	@JsonProperty("lst-group-kalo")
	private List<JsonKaloGroupDetail> jsonKaloGroupDetails = new ArrayList<>();
}
