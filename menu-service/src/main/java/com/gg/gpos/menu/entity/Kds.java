package com.gg.gpos.menu.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.gg.gpos.common.util.UserContext;
import com.speedfrwk.data.jpa.audit.AuditEntityListener;

import lombok.Data;

/**
 * Generated by Speed Generator
 * 
 * @author <a href="mailto:ngtrungkien@gmail.com">Kien Nguyen</a>
 */
@Data
@Entity
@Table(name = "kds")
@EntityListeners(AuditEntityListener.class)
public class Kds {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "\"id\"", nullable = false)
  private Long id;
  @Column(name = "\"restaurant_code\"", nullable = false)
  private Integer restaurantCode;
  @Column(name = "\"name\"", nullable = false)
  private String name;
  @Column(name = "\"status\"", nullable = true)
  private boolean status;
  @Column(name = "\"created_by\"", nullable = true)
  private String createdBy;
  @Column(name = "\"created_date\"", nullable = true)
  private LocalDateTime createdDate;
  @Column(name = "\"modified_by\"", nullable = true)
  private String modifiedBy;
  @Column(name = "\"modified_date\"", nullable = true)
  private LocalDateTime modifiedDate;
  
  @OneToMany(mappedBy = "kds")
  private List<PrintGroup> printGroups;
  
  @PrePersist
  private void beforePersist() {
	  setCreatedDate(LocalDateTime.now());
	  setCreatedBy(UserContext.getUsername());
  }
  
  @PreUpdate
  private void beforeUpdate() {
	  setModifiedDate(LocalDateTime.now());
	  setModifiedBy(UserContext.getUsername());
  }
}