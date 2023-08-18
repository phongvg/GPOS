package com.gg.gpos.menu.dto;

import com.gg.gpos.common.util.BaseDto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Generated by Speed Generator
 * 
 * @author <a href="mailto:ngtrungkien@gmail.com">Kien Nguyen</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ModiSchemeDetailDto extends BaseDto{
	  private Long id;
	  private Long itemId;
	  private Long parentId;
	  private Integer changesPrice;
	  private Integer useDownLimit;
	  private Integer upLimit;
	  private Integer freeCount;
	  private Integer sourceIdent;
	  private Integer sortNum;
	  private String readOnlyName;
	  private Integer useUpLimit;
	  private Integer modiGroup;
	  private Integer shQuantity;
	  private Integer assignChildsOnServer;
	  private Integer replaceDefModifier;
	  private Integer downLimit;
	  private Integer defaultModifier;
	  private Integer modiScheme;
	  private Integer objectSifr;
	  private Integer flags;
}