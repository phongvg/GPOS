package com.gg.gpos.menu.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.speedfrwk.data.jpa.audit.AuditEntityListener;

import lombok.Data;
import lombok.ToString;

/**
 * Generated by Speed Generator
 * 
 * @author <a href="mailto:ngtrungkien@gmail.com">Kien Nguyen</a>
 */
@Data
@ToString(exclude = {"kds"})
@Entity
@Table(name = "kitchen")
@EntityListeners(AuditEntityListener.class)
public class Kitchen {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "\"id\"", nullable = false)
  private Long id;
  @Column(name = "\"restaurant_code\"", nullable = true)
  private Integer restaurantCode;
  @Column(name = "\"kitchen_code\"", nullable = false)
  private String kitchenCode;
  @Column(name = "\"kitchen_type\"", nullable = false)
  private String kitchenType;
  @Column(name = "\"print_name\"", nullable = true)
  private String printName;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "\"kds_id\"", nullable = true)
  private Kds kds;
  
  @ManyToMany()
  @JoinTable(name="kitchen_hallplan", joinColumns = {
      @JoinColumn(name="kitchen_id", nullable=false, updatable=false) }, 
  	inverseJoinColumns = { @JoinColumn(name="hallplan_id", nullable=false, updatable=false) })
  private List<Hallplan> hallplans;
}