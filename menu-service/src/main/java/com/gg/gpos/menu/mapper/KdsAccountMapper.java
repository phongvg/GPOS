package com.gg.gpos.menu.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.gg.gpos.menu.dto.KdsAccountDto;
import com.gg.gpos.menu.dto.KdsPlaceDto;
import com.gg.gpos.menu.entity.KdsAccount;
import com.gg.gpos.menu.entity.KdsPlace;

@Mapper(componentModel = "spring")
public interface KdsAccountMapper {
	KdsAccount dtoToEntity(KdsAccountDto kdsAccountDto);
	KdsAccountDto entityToDto(KdsAccount kdsAccount);
	
	@Mapping(target = "kdsConfigCookings", expression ="java(null)")
	KdsPlaceDto kdsPlaceToKdsPlaceDto(KdsPlace kdsPlace);
}
