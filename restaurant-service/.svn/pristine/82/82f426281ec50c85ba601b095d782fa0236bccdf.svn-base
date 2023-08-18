package com.gg.gpos.res.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "sync_status")
public class SyncStatus implements Serializable{
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(name = "\"id\"", nullable = true)
	  private Long id;
	  
//	  @Column(name = "\"restaurant_code\"", nullable = true)
//	  private Integer restaurantCode;
	  @ManyToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name = "restaurant_code", referencedColumnName = "code", nullable = true)
	  private Restaurant restaur;

	  @Column(name = "\"master_data_sync_status\"", nullable = true)
	  private String masterDataSyncStatus;
	  
	  @Column(name = "\"business_sync_status\"", nullable = true)
	  private String businessSyncStatus;
}
