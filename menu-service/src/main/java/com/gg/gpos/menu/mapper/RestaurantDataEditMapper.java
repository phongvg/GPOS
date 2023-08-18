package com.gg.gpos.menu.mapper;
import org.mapstruct.Mapper;

import com.gg.gpos.menu.dto.RestaurantDataEditDto;
import com.gg.gpos.menu.entity.RestaurantDataEdit;

@Mapper(componentModel = "spring")
public interface RestaurantDataEditMapper {
	RestaurantDataEdit dtoToEntity(RestaurantDataEditDto restaurantDataEditDto);
	RestaurantDataEditDto entityToDto(RestaurantDataEdit restaurantDataEdit);
}
