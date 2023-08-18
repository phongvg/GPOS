package com.gg.gpos.menu.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.gg.gpos.menu.dto.FoodGroupItemDto;
import com.gg.gpos.menu.dto.FoodItemDto;
import com.gg.gpos.menu.entity.FoodGroupItem;
import com.gg.gpos.menu.entity.FoodItem;

@Mapper(componentModel = "spring")
public interface FoodGroupItemMapper {
	FoodGroupItem dtoToEntity(FoodGroupItemDto foodGroupItemDto);
	
	@Mapping(target = "foodGroup", expression = "java(null)")
	FoodGroupItemDto entityToDto(FoodGroupItem foodGroupItem);
	
	@Mapping(target = "foodGroupItems", expression = "java(null)")
	FoodItemDto foodItemToFoodItemDto(FoodItem foodItem);
}
