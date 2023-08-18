package com.gg.gpos.reference.entity;

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
@Entity(name = "com.gg.gadmin.reference.entity.SurveyDetail")
@Table(name = "survey_detail")
public class SurveyDetail {

	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(name = "\"id\"", nullable = false)
	  private Long id;
	  @Column(name = "\"question_id\"", nullable = false)
	  private Long questionId;
 	  @Column(name = "\"question_name\"", nullable = false)
	  private String questionName;
	  @Column(name = "\"answer_id\"", nullable = true)
	  private Long answerId;
	  @Column(name = "\"answer_name\"", nullable = true)
	  private String answerName;
	  @ManyToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name = "\"survey_id\"", nullable = false)
	  private Survey survey;
}
