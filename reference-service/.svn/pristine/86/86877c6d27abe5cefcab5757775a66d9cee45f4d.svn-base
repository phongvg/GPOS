package com.gg.gpos.reference.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.Data;

@Data
@Entity(name = "com.gg.gadmin.reference.entity.Attachment")
@Table(name = "attachment")
public class Attachment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "\"id\"", nullable = false)
  private Long id;
  /**
   * ID c�?�a ?��?�i t�?�?�ng c?? attachment
   */
  @Column(name = "\"module_id\"", nullable = false)
  private Long moduleId;
  /**
   * module g?�
   */
  @Column(name = "\"module_type\"", nullable = false)
  private String moduleType;
  /**
   * Lo�?�i ch�?�c n?�ng c?? attachment trong t�?�ng module
   */
  @Column(name = "\"function_type\"", nullable = true)
  private String functionType;
  @Column(name = "\"name\"", nullable = true)
  private String name;
  @Column(name = "\"url\"", nullable = true)
  private String url;
  @Column(name = "\"absolute_path\"", nullable = true)
  private String absolutePath;
  @Column(name = "\"file_name\"", nullable = true)
  private String fileName;
  @Column(name = "\"file_type\"", nullable = true)
  private String fileType;
  @Version
  @Column(name = "\"version\"", nullable = false)
  private Integer version;
  
  @Column(name = "\"restaurant_code\"", nullable = true)
  private Integer restaurantCode;
  @Column(name = "\"restaurant_name\"", nullable = true)
  private String restaurantName;
  @Column(name = "\"sync_status\"", nullable = true)
  private String syncStatus;
  @Column(name = "\"module_code\"", nullable = false)
  private String moduleCode;
}