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
@Table(name = "catalog_data_edit")
@EntityListeners(AuditEntityListener.class)
public class CatalogDataEdit {
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(name = "\"id\"", nullable = false)
	  private Long id;
	  @Column(name = "\"type\"", nullable = true)
	  private String type;
	  @Column(name = "\"catalog_id\"", nullable = true)
	  private Long catalogId;
	  @Column(name = "\"value\"", nullable = true)
	  private String value;
}
