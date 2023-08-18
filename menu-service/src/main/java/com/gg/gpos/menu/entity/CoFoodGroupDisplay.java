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
@ToString(exclude = {"coCategory"})
@Entity
@Table(name = "co_food_group_display")
@EntityListeners(AuditEntityListener.class)
public class CoFoodGroupDisplay {
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(name = "\"id\"", nullable = false)
	  private Long id;
	
	  @Column(name = "\"food_group_code\"", nullable = false)
	  private String foodGroupCode;
	  
	  @ManyToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name = "\"co_category_id\"", nullable = false)
	  private CoCategory coCategory;  
}
