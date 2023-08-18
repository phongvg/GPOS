package com.gg.gpos.menu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
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
@Table(name = "modi_scheme")
@EntityListeners(AuditEntityListener.class)
public class ModiScheme {
	  @Id
	  @Column(name = "\"id\"", nullable = false)
	  private Long id;
	  @Column(name = "\"item_id\"", nullable = true)
	  private Long itemId;
	  @Column(name = "\"parent_id\"", nullable = true)
	  private Long parentId;
	  @Column(name = "\"code\"", nullable = true)
	  private String code;
	  @Column(name = "\"name\"", nullable = true)
	  private String name;
	  @Column(name = "\"status\"", nullable = true)
	  private Integer status;
	  @Column(name = "\"ignore_default_for_kitchen\"", nullable = true)
	  private Integer ignoreDefaultForKitchen;
	  @Column(name = "\"source_ident\"", nullable = true)
	  private Integer sourceIdent;
	  @Column(name = "\"modi_scheme_type\"", nullable = true)
	  private Integer modiSchemeType;
	  @Column(name = "\"edit_right\"", nullable = true)
	  private Integer editRight;
	  @Column(name = "\"active_hierarchy\"", nullable = true)
	  private Integer activeHierarchy;
	  @Column(name = "\"assign_childs_on_server\"", nullable = true)
	  private Integer assignChildsOnServer;
	  @Column(name = "\"auto_open\"", nullable = true)
	  private Integer autoOpen;
	  @Column(name = "\"object_sifr\"", nullable = true)
	  private Integer objectSifr;
	  @Column(name = "\"flags\"", nullable = true)
	  private Integer flags;
}