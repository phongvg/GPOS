package com.gg.gpos.menu.mapper;

import org.mapstruct.Mapper;

import com.gg.gpos.menu.dto.KaloGroupDto;
import com.gg.gpos.menu.entity.KaloGroup;

@Mapper(componentModel = "spring")
public interface KaloGroupMapper {
	KaloGroup dtoToEntity(KaloGroupDto kaloGroupDto);
	KaloGroupDto entityToDto(KaloGroup kaloGroup);
}
