package com.gg.gpos.integration.dto;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class FileContent implements Serializable{
	@JsonProperty("file-name")
	private String fileName;
	@JsonProperty("folder")
	private String folder;
	@JsonProperty("file")
	private byte[] file;
	
	@JsonIgnore
	private String absolutePath;
	@JsonIgnore
	private Integer resCode;
	@JsonIgnore
	private String apiUrl;
}
