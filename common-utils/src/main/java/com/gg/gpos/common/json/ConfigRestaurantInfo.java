package com.gg.gpos.common.json;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class ConfigRestaurantInfo implements Serializable {
	@JsonProperty("code")
	private String restaurantCode;
	@JsonProperty("ip")
	private String ip;
	@JsonProperty("port")
	private String port;
	@JsonProperty("src-img-info")
	private String srcImgInfo;
	@JsonProperty("url-img-info")
	private String urlImgInfo;
	@JsonProperty("src-img-logo")
	private String srcImgLogo;
	@JsonProperty("url-img-logo")
	private String urlImgLogo;
	@JsonProperty("src-img-close-order")
	private String srcImgCloseOrder;
	@JsonProperty("url-img-close-order")
	private String urlImgCloseOrder;
}
