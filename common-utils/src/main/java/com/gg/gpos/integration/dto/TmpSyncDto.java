package com.gg.gpos.integration.dto;

import lombok.Data;

@Data
public class TmpSyncDto {
	private Long id;
	private Integer restaurantCode;
	private String type;
	private Long catalogId;
	private String value;
	private SyncDto sync;
}
