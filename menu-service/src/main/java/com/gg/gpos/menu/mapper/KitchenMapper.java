package com.gg.gpos.menu.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.gg.gpos.menu.dto.KitchenDto;
import com.gg.gpos.menu.entity.Kitchen;

/**
 * Generated by Speed Generator
 * 
 * @author <a href="mailto:ngtrungkien@gmail.com">Kien Nguyen</a>
 */
@Mapper(componentModel = "spring")
public interface KitchenMapper {
	Kitchen dtoToEntity(KitchenDto kitchenDto);
	@Mapping(target = "kds", expression = "java(null)")
	KitchenDto entityToDto(Kitchen kitchen);
}