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

/**
 * Generated by Speed Generator
 * 
 * @author <a href="mailto:ngtrungkien@gmail.com">Kien Nguyen</a>
 */
@Data
@Entity
@Table(name = "menu_type")
@EntityListeners(AuditEntityListener.class)
public class MenuType {
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(name = "\"id\"", nullable = false)
	  private Long id;
	  @Column(name = "\"code\"", nullable = true)
	  private Integer code;
	  @Column(name = "\"name\"", nullable = true)
	  private String name;
	  
}