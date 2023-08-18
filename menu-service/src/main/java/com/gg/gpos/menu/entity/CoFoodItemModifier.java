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
@ToString(exclude = {"coFoodItem", "modifier"})
@Entity
@Table(name = "co_food_item_modifier")
@EntityListeners(AuditEntityListener.class)
public class CoFoodItemModifier {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "\"id\"", nullable = false)
	private Long id;
	@Column(name = "\"price\"", nullable = true)
	private Long price;
	@Column(name = "\"quantity\"", nullable = true)
	private Long quantity;
	@Column(name = "\"type\"", nullable = true)
	private Integer type;
	@Column(name = "\"number_of_chili\"", nullable = true)
	private Integer numberOfChili;
	  
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "\"co_food_item_id\"", nullable = false)
	private CoFoodItem coFoodItem;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "\"modifier_id\"", nullable = true)
	private Modifier modifier;  
	
}
