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

@Data
@ToString(exclude = {"parent"})
@Entity
@Table(name = "kds_account")
@EntityListeners(AuditEntityListener.class)
public class KdsAccount {
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(name = "\"id\"", nullable = false)
	  private Long id;
	  @Column(name = "\"restaurant_code\"", nullable = true)
	  private Integer restaurantCode;
	  @Column(name = "\"account_name\"", nullable = true)
	  private String accountName;
	  @Column(name = "\"password\"", nullable = true)
	  private String password;
	  @Column(name = "\"role\"", nullable = true)
	  private Integer role;
	  
	  @ManyToMany()
	  @JoinTable(name="kds_account_kds_place", joinColumns = {
	      @JoinColumn(name="kds_account_id", nullable=false, updatable=false) }, 
	  	inverseJoinColumns = { @JoinColumn(name="kds_place_id", nullable=false, updatable=false) })
	  private List<KdsPlace> kdsPlaces;
	  
	  @ManyToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name = "parent_id", nullable = true)
	  private KdsAccount parent;
}
