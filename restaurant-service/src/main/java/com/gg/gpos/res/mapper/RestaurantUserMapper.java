package com.gg.gpos.res.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.gg.gpos.res.dto.RestaurantDto;
import com.gg.gpos.res.dto.RestaurantUserDto;
import com.gg.gpos.res.entity.Restaurant;
import com.gg.gpos.res.entity.RestaurantUser;

@Mapper(componentModel = "spring")
public interface RestaurantUserMapper {
	RestaurantUser dtoToEntity(RestaurantUserDto restaurantUserDto);
	RestaurantUserDto entityToDto(RestaurantUser restaurantUser);
	
	@Mapping(target = "provinceBrand", expression = "java(null)")
	RestaurantDto restaurantToRestaurantDto(Restaurant restaurant);
}
