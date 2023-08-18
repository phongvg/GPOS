package com.gg.gpos.res.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "restaurant")
public class Restaurant implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "\"id\"", nullable = false)
  private Long id;
  @Column(name = "\"code\"", nullable = true)
  private Integer code;
  @Column(name = "\"name\"", nullable = true)
  private String name;
  @Column(name = "\"address\"", nullable = true)
  private String address;
  @Column(name = "\"phone\"", nullable = true)
  private String phone;
  @Column(name = "\"status\"", nullable = true)
  private Integer status;
  @Column(name = "\"latitude\"", nullable = true)
  private String latitude;
  @Column(name = "\"longitude\"", nullable = true)
  private String longitude;
  @Column(name = "\"email\"", nullable = true)
  private String email;
  @Column(name = "\"ip\"", nullable = true)
  private String ip;
  @Column(name = "\"port\"", nullable = true)
  private String port;
  @Column(name = "\"online_offline\"", nullable = true)
  private Integer online;
  @Column(name = "\"version\"", nullable = true)
  private String version;
  @Column(name = "\"unsigned_name\"", nullable = true)
  private String unsignedName;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "district_brand_id", nullable = true)
  private DistrictBrand districtBrand;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "province_brand_id", nullable = false)
  private ProvinceBrand provinceBrand;

  @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
  private List<RestaurantUser> restaurantUsers;
  
  @OneToMany(mappedBy = "restaur", fetch = FetchType.LAZY)
  private List<SyncStatus> syncStatus;
}