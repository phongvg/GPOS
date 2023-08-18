package com.gg.gpos.common.json;

import java.io.Serializable;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class FileContent implements Serializable {
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
	private String resName;
	@JsonIgnore
	private String apiUrl;
	
	@JsonIgnore
	private String code;
	@JsonIgnore
	private String type;
	@JsonIgnore
	private Long attachmentId;
	@JsonIgnore
	private Boolean stauts;
	
	public String toJsonString() {
		return "{\"file-name\":\""+ fileName + "\",\"folder\":\"" + folder + "\",\"file\":" + Arrays.toString(file) + "}";
	}
	
	public String jsonLog() {
		return "{\"file-name\":\""+ fileName + "\",\"folder\":\"" + folder + "\",\"resCode\":" + resCode + "}";
	}
}
