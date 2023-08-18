package com.gg.gpos.menu.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.gg.gpos.menu.dto.CoFoodItemDto;
import com.gg.gpos.menu.dto.CofoodItemModifierDto;
import com.gg.gpos.menu.entity.CoFoodItem;
import com.gg.gpos.menu.entity.CoFoodItemModifier;

@Mapper(componentModel = "spring")
public interface CoFoodItemModifierMapper {
	CoFoodItemModifier dtoToEntity(CofoodItemModifierDto cofoodItemModifierDto);
	CofoodItemModifierDto entityToDto(CoFoodItemModifier coFoodItemModifier);
	
	@Mapping(target ="foodItem" , expression = "java(null)")
	@Mapping(target ="modifiers" , expression = "java(null)")
	@Mapping(target ="relatedFoodItems" , expression = "java(null)")
	@Mapping(target ="toppingFoodItems" , expression = "java(null)")
	@Mapping(target ="features" , expression = "java(null)")
	@Mapping(target ="co" , expression = "java(null)")
	@Mapping(target ="coFoodItemModifiers" , expression = "java(null)")
	CoFoodItemDto coFoodItemToCoFoodItemDto(CoFoodItem coFoodItem);
}
