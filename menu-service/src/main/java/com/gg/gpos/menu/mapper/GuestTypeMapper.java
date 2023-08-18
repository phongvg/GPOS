package com.gg.gpos.menu.mapper;

import org.mapstruct.Mapper;

import com.gg.gpos.menu.dto.GuestTypeDto;
import com.gg.gpos.menu.entity.GuestType;


/**
 * Generated by Speed Generator
 * 
 * @author <a href="mailto:ngtrungkien@gmail.com">Kien Nguyen</a>
 */
@Mapper(componentModel = "spring")
public interface GuestTypeMapper {
	GuestType dtoToEntity(GuestTypeDto guestTypeDto);
	GuestTypeDto entityToDto(GuestType guestType);
}