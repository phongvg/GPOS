package com.gg.gpos.reference.dto;

import lombok.Data;

@Data
public class TaxDto {
	private Long id;
	private String taxNo;
	private String representativeName;
	private String companyName;
	private String address;
	private String email;
	private String phone;
}
