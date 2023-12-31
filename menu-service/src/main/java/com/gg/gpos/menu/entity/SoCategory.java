package com.gg.gpos.menu.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.speedfrwk.data.jpa.audit.AuditEntityListener;

import lombok.Data;
import lombok.ToString;

/**
 * Generated by Speed Generator
 * 
 * @author <a href="mailto:ngtrungkien@gmail.com">Kien Nguyen</a>
 */
@Data
@ToString(exclude = {"so", "orderCategory"})
@Entity
@Table(name = "so_category")
@EntityListeners(AuditEntityListener.class)
public class SoCategory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "\"id\"", nullable = false)
  private Long id;
  @Column(name = "\"restaurant_code\"", nullable = true)
  private Integer restaurantCode;
  @Column(name = "\"order_category_parent_id\"", nullable = true)
  private Long orderCategoryParentId;
  @Column(name = "\"adult_buffer_ticket\"", nullable = true)
  private String adultBufferTicket;
  @Column(name = "\"kid_buffer_ticket\"", nullable = true)
  private String kidBufferTicket;
  @Column(name = "\"type\"", nullable = true)
  private Integer type;
  @Column(name = "\"status\"", nullable = true)
  private boolean status;
  
  @ManyToOne()
  @JoinColumn(name = "\"order_category_id\"", nullable = false)
  private OrderCategory orderCategory;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "\"so_id\"", nullable = false)
  private So so;

  @OneToMany(mappedBy = "soCategory",fetch = FetchType.LAZY)
  private List<SoCategoryFoodGroup> soCategoryFoodGroups;
}