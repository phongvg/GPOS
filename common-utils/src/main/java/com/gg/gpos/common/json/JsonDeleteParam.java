package com.gg.gpos.common.json;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class JsonDeleteParam implements Serializable{
	@JsonProperty("code")
	private String code;
}
