package com.gg.gpos.menu.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "config_qr_order")
public class ConfigQrOrder {
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(name = "\"id\"", nullable = false)
	  private Long id;
	  @Column(name = "\"co_id\"", nullable = true)
	  private Long coId;
	  @Column(name = "\"restaurant_code\"", nullable = true)
	  private String restaurantCode;
	  @Column(name = "\"ip\"", nullable = true)
	  private String ip;
	  @Column(name = "\"port\"", nullable = true)
	  private String port;
	  
	  @Column(name = "\"src_img_info\"", nullable = true)
	  private String srcImgInfo;
	  @Column(name = "\"url_img_info\"", nullable = true)
	  private String urlImgInfo;
	  @Column(name = "\"src_img_logo\"", nullable = true)
	  private String srcImgLogo;
	  @Column(name = "\"url_img_logo\"", nullable = true)
	  private String urlImgLogo;
	  @Column(name = "\"src_img_close_order\"", nullable = true)
	  private String srcImgCloseOrder;
	  @Column(name = "\"url_img_close_order\"", nullable = true)
	  private String urlImgCloseOrder;
	  @Column(name = "\"created_by\"", nullable = true)
	  private String createdBy;
	  @Column(name = "\"created_date\"", nullable = true)
	  private LocalDateTime createdDate;
	  @Column(name = "\"modified_by\"", nullable = true)
	  private String modifiedBy;
	  @Column(name = "\"modified_date\"", nullable = true)
	  private LocalDateTime modifiedDate;
	  
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
