package com.gg.gpos.menu.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.gg.gpos.menu.dto.KdsDto;
import com.gg.gpos.menu.dto.PrintGroupDto;
import com.gg.gpos.menu.entity.Kds;
import com.gg.gpos.menu.entity.PrintGroup;

/**
 * Generated by Speed Generator
 * 
 * @author <a href="mailto:ngtrungkien@gmail.com">Kien Nguyen</a>
 */
@Mapper(componentModel = "spring")
public interface KdsMapper {
	Kds dtoToEntity(KdsDto kdsDto);
	KdsDto entityToDto(Kds kds);
	
	@Mapping(target = "foodItems", expression = "java(null)")
	@Mapping(target = "kds", expression = "java(null)")
	PrintGroupDto printGroupToPrintGroupDto(PrintGroup printGroup);
}