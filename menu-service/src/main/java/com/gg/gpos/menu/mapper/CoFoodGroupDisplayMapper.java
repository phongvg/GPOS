package com.gg.gpos.menu.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.gg.gpos.menu.dto.CoCategoryDto;
import com.gg.gpos.menu.dto.CoFoodGroupDisplayDto;
import com.gg.gpos.menu.entity.CoCategory;
import com.gg.gpos.menu.entity.CoFoodGroupDisplay;

@Mapper(componentModel = "spring")
public interface CoFoodGroupDisplayMapper {
	CoFoodGroupDisplay dtoToEntity(CoFoodGroupDisplayDto coFoodGroupDisplayDto);
	CoFoodGroupDisplayDto entityToDto(CoFoodGroupDisplay coFoodGroupDisplay);
	
	@Mapping(target ="soCategory" , expression = "java(null)")
	@Mapping(target ="coFoodGroupDisplays" , expression = "java(null)")
	@Mapping(target ="digitalMenus" , expression = "java(null)")
	CoCategoryDto coCategoryToCoCategoryDto(CoCategory coCategory);
}
