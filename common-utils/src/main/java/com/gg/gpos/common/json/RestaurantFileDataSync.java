package com.gg.gpos.common.json;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class RestaurantFileDataSync implements Serializable{
	private String resCode;
	private List<FileContent> fileContents = new ArrayList<>();
	private List<String> categoryCodeFails = new ArrayList<>();
	private List<String> foodItemCodeFails = new ArrayList<>();
	private List<String> foodGroupCodeFails = new ArrayList<>(); 
}
