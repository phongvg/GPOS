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
@Table(name = "restaurant_data_edit")
@EntityListeners(AuditEntityListener.class)
public class RestaurantDataEdit {
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(name = "\"id\"", nullable = false)
	  private Long id;
	  @Column(name = "\"type\"", nullable = false)
	  private String type;
	  
	  @Column(name = "\"restaurant_code\"", nullable = false)
	  private Integer restaurantCode;
	  
	  @Column(name = "\"value\"", nullable = false)
	  private String value;
}
