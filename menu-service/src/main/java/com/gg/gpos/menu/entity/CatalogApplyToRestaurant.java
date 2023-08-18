package com.gg.gpos.menu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.speedfrwk.data.jpa.audit.AuditEntityListener;

import lombok.Data;

@Data
@Entity
@Table(name = "catalog_apply_to_restaurant")
@EntityListeners(AuditEntityListener.class)
public class CatalogApplyToRestaurant {
	 @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(name = "\"id\"", nullable = false)
	  private Long id;
	  @Column(name = "\"restaurant_code\"", nullable = true)
	  private Integer restaurantCode;
	  @Column(name = "\"co_id\"", nullable = true)
	  private Long coId;
	  @Column(name = "\"so_id\"", nullable = true)
	  private Long soId;
	  @Column(name = "\"group_param_id\"", nullable = true)
	  private Long groupParamId;
}
