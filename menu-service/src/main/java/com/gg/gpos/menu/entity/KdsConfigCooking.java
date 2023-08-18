package com.gg.gpos.menu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.speedfrwk.data.jpa.audit.AuditEntityListener;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude = {"kdsPlace", "foodItem"})
@Entity
@Table(name = "kds_config_cooking")
@EntityListeners(AuditEntityListener.class)
public class KdsConfigCooking {
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(name = "\"id\"", nullable = false)
	  private Long id;
	  @Column(name = "\"restaurant_code\"", nullable = true)
	  private Integer restaurantCode;
	  @Column(name = "\"single_cooking_time\"", nullable = true)
	  private Integer singleCookingTime;
	  @Column(name = "\"multi_cooking_time\"", nullable = true)
	  private Integer multiCookingTime;
	  @Column(name = "\"fasting\"", nullable = true)
	  private String fasting;
	  @Column(name = "\"group_type_kds_code\"", nullable = true)
	  private String groupTypeKdsCode;
	  @Column(name = "\"group_type_kds_name\"", nullable = true)
	  private String groupTypeKdsName;
	  
	  @ManyToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name = "\"kds_place_id\"", nullable = false)
	  private KdsPlace kdsPlace; 
	  
	  @ManyToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name = "\"food_item_id\"", nullable = false)
	  private FoodItem foodItem; 
}
