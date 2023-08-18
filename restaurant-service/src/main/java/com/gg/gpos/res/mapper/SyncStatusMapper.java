package com.gg.gpos.res.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.gg.gpos.res.dto.RestaurantDto;
import com.gg.gpos.res.dto.SyncStatusDto;
import com.gg.gpos.res.entity.Restaurant;
import com.gg.gpos.res.entity.SyncStatus;

@Mapper(componentModel = "spring")
public interface SyncStatusMapper {
	SyncStatus dtoToEntity(SyncStatusDto syncStatusDto);
	SyncStatusDto entityToDto(SyncStatus syncStatus);
	
	@Mapping(target = "provinceBrand", expression = "java(null)")
	@Mapping(target = "syncStatus", expression = "java(null)")
	RestaurantDto restaurantToRestaurantDto(Restaurant restaurant);
}
