package com.gg.gpos.menu.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.gg.gpos.menu.dto.KdsPlaceDto;
import com.gg.gpos.menu.entity.KdsPlace;

@Mapper(componentModel = "spring")
public interface KdsPlaceMapper {
	KdsPlace dtoToEntity(KdsPlaceDto kdsPlaceDto);
	@Mapping(target = "kdsConfigCookings", expression ="java(null)")
	KdsPlaceDto entityToDto(KdsPlace kdsPlace);
}
