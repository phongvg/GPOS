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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.speedfrwk.data.jpa.audit.AuditEntityListener;

import lombok.Data;

@Data
@Entity
@Table(name = "kds_place")
@EntityListeners(AuditEntityListener.class)
public class KdsPlace {
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(name = "\"id\"", nullable = false)
	  private Long id;
	  @Column(name = "\"restaurant_code\"", nullable = true)
	  private Integer restaurantCode;
	  @Column(name = "\"code\"", nullable = true)
	  private String code;
	  @Column(name = "\"name\"", nullable = true)
	  private String name;
	  @Column(name = "\"type\"", nullable = true)
	  private String type;
	  @Column(name = "\"printer\"", nullable = true)
	  private String printer;
	  
	  @OneToMany(mappedBy = "kdsPlace", fetch = FetchType.LAZY)
	  private List<KdsConfigCooking> kdsConfigCookings;
	  
	  @ManyToMany()
	  @JoinTable(name="kds_place_hallplan", joinColumns = {
	      @JoinColumn(name="kds_place_id", nullable=false, updatable=false) }, 
	  	inverseJoinColumns = { @JoinColumn(name="hallplan_id", nullable=false, updatable=false) })
	  private List<Hallplan> hallplans;
}
