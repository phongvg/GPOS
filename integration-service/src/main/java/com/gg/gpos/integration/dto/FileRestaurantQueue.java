package com.gg.gpos.integration.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
@SuppressWarnings("serial")
@Data
public class FileRestaurantQueue implements Serializable{
	private Integer resCode;
	private String resName;
	private List<Integer> referenceDatas;
	private boolean checkedRef;
}
