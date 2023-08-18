package com.gg.gpos.res.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "district")
public class District {

  @Id
  @Column(name = "\"id\"", nullable = false)
  private Long id;
  @Column(name = "\"name\"", nullable = false)
  private String name;
  @Column(name = "\"display_order\"", nullable = true)
  private Integer displayOrder;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "province_id", nullable = true)
  private Province province;
  
  @OneToMany(mappedBy = "district",fetch = FetchType.LAZY)
  private List<DistrictBrand> districtBrands;
}