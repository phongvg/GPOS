package com.gg.gpos.integration.dto;
import lombok.Data;

@Data
public class Mail {
	private String mailTo;
	private String subject;
	private String mailContent;
}
