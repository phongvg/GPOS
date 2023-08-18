package com.gg.gpos.reference.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "tax")
public class Tax {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "\"id\"", nullable = false)
	private Long id;
	
	@Column(name = "\"tax_no\"", nullable = false)
	private String taxNo;
	
	@Column(name = "\"representative_name\"")
	private String representativeName;
	
	@Column(name = "\"company_name\"", nullable = false)
	private String companyName;
	
	@Column(name = "\"address\"", nullable = false)
	private String address;
	
	@Column(name = "\"email\"")
	private String email;
	
	@Column(name = "\"phone\"")
	private String phone;
}
