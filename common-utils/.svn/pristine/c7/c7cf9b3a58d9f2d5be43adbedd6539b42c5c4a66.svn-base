package com.gg.gpos.integration.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.gg.gpos.common.util.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SyncArchiveDto extends BaseDto{
	private Long id;
	  private Integer restaurantCode;
	  private String restaurantName;
	  private String typeSync;
	  private String typeData;
	  private LocalDateTime createdDate;
	  private String status;
	  private String result;
	  
	  private boolean checkSyncDataFail;
	  private boolean checkSyncDataSuccess;
	  
	  private String displayCreatedDate;
	  public String getDisplayCreatedDate() {
		  if (createdDate != null) {
			  return DateTimeFormatter.ofPattern("dd-MM-yyyy 'at' hh:mm:ss a").format(createdDate);  
		  } else {
			  return null;
		  }
	  }

	private LocalDateTime syncStartDate;
	private LocalDateTime syncEndDate;
	public String getDisplaySyncStartDate() {
		if (syncStartDate != null) {
			return DateTimeFormatter.ofPattern("dd-MM-yyyy 'at' hh:mm:ss a").format(syncStartDate);
		} else {
			return null;
		}
	}
	public String getDisplaySyncEndDate() {
		if (syncEndDate != null) {
			return DateTimeFormatter.ofPattern("dd-MM-yyyy 'at' hh:mm:ss a").format(syncEndDate);
		} else {
			return null;
		}
	}
}
