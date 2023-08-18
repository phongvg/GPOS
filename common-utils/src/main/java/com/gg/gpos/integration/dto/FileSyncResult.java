package com.gg.gpos.integration.dto;

import java.io.Serializable;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class FileSyncResult implements Serializable{
	private String code;
	private String result;
	private boolean checkSync;
}
