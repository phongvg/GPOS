package com.gg.gpos.res.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

/**
 * Generated by Speed Generator
 * 
 * @author <a href="mailto:ngtrungkien@gmail.com">Kien Nguyen</a>
 */
@Data
@Entity
@Table(name = "zone")
public class Zone {

  @Id
  @Column(name = "\"id\"", nullable = false)
  private Long id;
  @Column(name = "\"code\"", nullable = true)
  private String code;
  @Column(name = "\"name\"", nullable = true, unique = true)
  private String name;
  
  @OneToMany(mappedBy = "zone",fetch = FetchType.LAZY)
  private List<Province> provinces;
}