package com.gg.gpos.menu.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.gg.gpos.menu.dto.FoodItemDto;
import com.gg.gpos.menu.dto.KdsConfigCookingDto;
import com.gg.gpos.menu.dto.KdsPlaceDto;
import com.gg.gpos.menu.entity.FoodItem;
import com.gg.gpos.menu.entity.KdsConfigCooking;
import com.gg.gpos.menu.entity.KdsPlace;

@Mapper(componentModel = "spring")
public interface KdsConfigCookingMapper {
	KdsConfigCooking dtoToEntity(KdsConfigCookingDto configCookingDto);
	KdsConfigCookingDto entityToDto(KdsConfigCooking kdsConfigCooking);
	
	@Mapping(target = "foodGroupItems", expression ="java(null)")
	FoodItemDto foodItemToFoodItemDto(FoodItem foodItem);
	
	@Mapping(target = "kdsConfigCookings", expression ="java(null)")
	KdsPlaceDto kdsPlaceToKdsPlaceDto(KdsPlace kdsPlace);
}
