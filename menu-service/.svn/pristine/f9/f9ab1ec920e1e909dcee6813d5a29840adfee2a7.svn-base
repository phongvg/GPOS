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
@ToString(exclude = {"coFoodItem"})
@Entity
@Table(name = "topping_food_item")
@EntityListeners(AuditEntityListener.class)
public class ToppingFoodItem {
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(name = "\"id\"", nullable = false)
	  private Long id;
	  
	  @Column(name = "\"food_item_code\"", nullable = false)
	  private String foodItemCode;
	  
	  @Column(name = "\"food_item_name\"", nullable = false)
	  private String foodItemName;
	  
	  @Column(name = "\"modifier_name\"", nullable = false)
	  private String modifierName;
	  
	  @Column(name = "\"modifier_code\"", nullable = false)
	  private String modifierCode;
	  
	  @ManyToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name = "\"co_food_item_id\"", nullable = false)
	  private CoFoodItem coFoodItem;  
	  
	  @Column(name = "\"sap_code\"", nullable = true)
	  private String sapCode;
}
