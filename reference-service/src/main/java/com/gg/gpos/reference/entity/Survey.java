package com.gg.gpos.reference.entity;

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
@Entity(name = "com.gg.gadmin.reference.entity.Survey")
@Table(name = "survey")
public class Survey {

	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(name = "\"id\"", nullable = false)
	  private Long id;
	  @Column(name = "\"restaurant_code\"", nullable = false)
	  private Integer restaurantCode;
  	  @Column(name = "\"restaurant_name\"", nullable = false)
	  private String restaurantName;
	  @Column(name = "\"order_code\"", nullable = true)
	  private String orderCode;
	  @Column(name = "\"created_time\"", nullable = true)
	  private LocalDateTime createdTime;
	  @Column(name = "\"staff_code\"", nullable = true)
	  private String staffCode;
	  @Column(name = "\"is_survey\"", nullable = true)
	  private Boolean isSurvey;
	  
	  @OneToMany(mappedBy = "survey", fetch = FetchType.LAZY)
	  private List<SurveyDetail> surveyDetails;
	  
	  @PrePersist
	  private void prePersist() {
		  setCreatedTime(LocalDateTime.now());
	  }
	  
	  @PreUpdate
	  private void preUpdate() {
		  setCreatedTime(LocalDateTime.now());
	  }
}
