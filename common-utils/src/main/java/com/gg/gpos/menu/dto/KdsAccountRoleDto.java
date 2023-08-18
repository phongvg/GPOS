package com.gg.gpos.menu.dto;

import com.gg.gpos.common.util.BaseDto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class KdsAccountRoleDto extends BaseDto{
	private Long id;
	private Integer value;
	private String name;
}
