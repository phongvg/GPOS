package com.gg.gpos.menu.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "group_param")
public class GroupParam {
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(name = "\"id\"", nullable = false)
	  private Long id;
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
	  
	  @OneToMany(mappedBy = "groupParam", fetch = FetchType.LAZY)
	  private List<Param> params;
	  
	  @PrePersist
	  private void prePersist() {
		  setCreatedDate(LocalDateTime.now());
		  setModifiedDate(LocalDateTime.now());
	  }
	  
	  @PreUpdate
	  private void preUpdate() {
		  setModifiedDate(LocalDateTime.now());
	  }
}
