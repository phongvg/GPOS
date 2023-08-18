package com.gg.gpos.integration.dto;

import lombok.Data;

/**
 * Generated by Speed Generator
 * 
 * @author <a href="mailto:ngtrungkien@gmail.com">Kien Nguyen</a>
 */
@Data
public class Modifier {

  private Long id;
  private String type;
  private String code;
  private String nameEn;
  private String nameVn;
  private String descEn;
  private String descVn;
  private boolean status;
  
  private boolean selected;

}