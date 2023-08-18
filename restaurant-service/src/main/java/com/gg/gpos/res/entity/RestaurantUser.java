package com.gg.gpos.res.entity;

import java.io.Serializable;

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

/**
 * Generated by Speed Generator
 * 
 * @author <a href="mailto:ngtrungkien@gmail.com">Kien Nguyen</a>
 */
@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "restaurant_user")
public class RestaurantUser implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "\"id\"", nullable = false)
  private Long id;
  //@Column(name = "\"restaurant_code\"", nullable = false)
  //private Integer restaurantCode;
  @Column(name = "\"username\"", nullable = true)
  private String username;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "restaurant_code", referencedColumnName = "code", nullable = true)
  private Restaurant restaurant;
  
}