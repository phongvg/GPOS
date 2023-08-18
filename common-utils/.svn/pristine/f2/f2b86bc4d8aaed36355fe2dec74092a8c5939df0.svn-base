package com.gg.gpos.audit.dto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.gg.gpos.common.util.BaseDto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AuditLogDto extends BaseDto{
	  private Long id;
	  private String content;
	  private String modifiedBy;
	  private Date modifiedDate;
	  private String action;
	  private String keyword;
	  
	  private String displayModifiedDate;
	  public String getDisplayModifiedDate() {
		  if (modifiedDate != null) {
//			  return DateFormat.getTimeInstance().format(modifiedDate);
			  return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(modifiedDate);
		  } else {
			  return null;
		  }
	  }
	  
}
