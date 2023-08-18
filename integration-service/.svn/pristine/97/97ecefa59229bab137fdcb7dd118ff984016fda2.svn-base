package com.gg.gpos.integration.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "sync")
public class Sync {
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(name = "\"id\"", nullable = false)
	  private Long id;
	  @Column(name = "\"restaurant_code\"", nullable = true)
	  private Integer restaurantCode;
	  @Column(name = "\"restaurant_name\"", nullable = true)
	  private String restaurantName;
	  @Column(name = "\"type_sync\"", nullable = true)
	  private String typeSync;
	  @Column(name = "\"type_data\"", nullable = true)
	  private String typeData;
	  @Column(name = "\"created_date\"", nullable = true)
	  private LocalDateTime createdDate;
	  @Column(name = "\"status\"", nullable = true)
	  private String status;
	  @Column(name = "\"result\"", nullable = true)
	  private String result;
	  @Column(name = "\"catalog_id\"", nullable = true)
	  private Long catalogId;
	  @Column(name = "\"override\"", nullable = false)
	  private Boolean override;
	  @OneToMany(mappedBy = "sync", fetch = FetchType.LAZY)
	  private List<TmpSync> tmpSyncs;
	  @Column(name = "\"sync_start_date\"", nullable = true)
	  private LocalDateTime syncStartDate;
	  @Column(name = "\"sync_end_date\"", nullable = true)
	  private LocalDateTime syncEndDate;
	  
	  @PrePersist
	  private void prePersist() {
		  setCreatedDate(LocalDateTime.now());
	  }

	  @PreUpdate
	  private void preUpdate() {setSyncStartDate(LocalDateTime.now());}
}
