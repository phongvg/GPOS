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
@Table(name = "kitchen_type")
@EntityListeners(AuditEntityListener.class)
public class KitchenType {
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(name = "\"id\"", nullable = false)
	  private Long id;
	  @Column(name = "\"restaurant_code\"", nullable = false)
	  private Integer restaurantCode;
	  @Column(name = "\"name\"", nullable = false)
	  private String name;
}
