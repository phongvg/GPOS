package com.gg.gpos.menu.mapper;

import org.mapstruct.Mapper;

import com.gg.gpos.menu.dto.CatalogApplyToRestaurantDto;
import com.gg.gpos.menu.entity.CatalogApplyToRestaurant;
@Mapper(componentModel = "spring")
public interface CatalogApplyToRestaurantMapper {
	CatalogApplyToRestaurant dtoToEntity(CatalogApplyToRestaurantDto catalogApplyToRestaurantDto);
	CatalogApplyToRestaurantDto entityToDto(CatalogApplyToRestaurant catalogApplyToRestaurant);
}
