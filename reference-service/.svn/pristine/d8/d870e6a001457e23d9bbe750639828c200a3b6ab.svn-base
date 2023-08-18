package com.gg.gpos.reference.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "system_parameter")
public class SystemParameter {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "\"id\"", nullable = false)
  private Long id;
  @Column(name = "\"param_name\"", nullable = false)
  private String paramName;
  @Column(name = "\"param_value\"", nullable = true)
  private String paramValue;
  @Column(name = "\"description\"", nullable = true)
  private String description;
  @Column(name = "\"version\"", nullable = false)
  private Integer version;
}