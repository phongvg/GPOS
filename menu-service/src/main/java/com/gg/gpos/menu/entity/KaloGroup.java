package com.gg.gpos.menu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "kalo_group")
public class KaloGroup {
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(name = "\"id\"", nullable = false)
	  private Long id;
	  @Column(name = "\"code\"", nullable = true)
	  private String code;
	  @Column(name = "\"name\"", nullable = true)
	  private String name;
	  @Column(name = "\"status\"", nullable = true)
	  private Integer status;
	  @Column(name = "\"max-kalo\"", nullable = true)
	  private Integer maxKalo;
}
