package com.gg.gpos.menu.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.gg.gpos.menu.dto.CoFoodItemDto;
import com.gg.gpos.menu.dto.RelatedFoodItemDto;
import com.gg.gpos.menu.entity.CoFoodItem;
import com.gg.gpos.menu.entity.RelatedFoodItem;

/**
 * Generated by Speed Generator
 * 
 * @author <a href="mailto:ngtrungkien@gmail.com">Kien Nguyen</a>
 */
@Mapper(componentModel = "spring")
public interface RelatedFoodItemMapper {
	RelatedFoodItem dtoToEntity(RelatedFoodItemDto relatedFoodItemDto);
	RelatedFoodItemDto entityToDto(RelatedFoodItem relatedFoodItem);
	
	@Mapping(target ="foodItem" , expression = "java(null)")
	@Mapping(target ="modifiers" , expression = "java(null)")
	@Mapping(target ="relatedFoodItems" , expression = "java(null)")
	@Mapping(target ="toppingFoodItems" , expression = "java(null)")
	@Mapping(target ="features" , expression = "java(null)")
	@Mapping(target ="co" , expression = "java(null)")
	@Mapping(target ="coFoodItemModifiers" , expression = "java(null)")
	CoFoodItemDto coFoodItemToCoFoodItemDto(CoFoodItem coFoodItem);
}