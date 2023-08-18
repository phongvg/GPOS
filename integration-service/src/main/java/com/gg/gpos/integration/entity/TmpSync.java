package com.gg.gpos.integration.entity;

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

@Data
@Entity
@Table(name = "tmp_sync")
public class TmpSync {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "\"id\"", nullable = false)
	private Long id;
	@Column(name = "\"restaurant_code\"", nullable = true)
	private Integer restaurantCode;
	@Column(name = "\"type\"", nullable = true)
	private String type;
	@Column(name = "\"catalog_id\"", nullable = true)
	private Long catalogId;
	@Column(name = "\"value\"", nullable = true)
	private String value;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "\"sync_id\"", nullable = false)
	private Sync sync;
}
