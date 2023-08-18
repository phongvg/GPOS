package com.gg.gpos.common.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class JsonDigitalMenu {
    @JsonProperty("src-img")
    private String srcImg;
    @JsonProperty("url-img")
    private String urlImg;
    @JsonProperty("order-number")
    private Integer orderNumber;
}
