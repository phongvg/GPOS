package com.gg.gpos.common.json;

import java.io.Serializable;
import java.util.List;
import lombok.Data;
@SuppressWarnings("serial")
@Data
public class RestaurantMasterDataSync implements Serializable{
	private String code;
	private List<String> valParam;
}
