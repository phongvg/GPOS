package com.gg.gpos.menu.mapper;

import org.mapstruct.Mapper;

import com.gg.gpos.menu.dto.ModifierDto;
import com.gg.gpos.menu.entity.Modifier;

/**
 * Generated by Speed Generator
 * 
 * @author <a href="mailto:ngtrungkien@gmail.com">Kien Nguyen</a>
 */
@Mapper(componentModel = "spring")
public interface ModifierMapper {
	Modifier dtoToEntity(ModifierDto modifierDto);
	ModifierDto entityToDto(Modifier modifier);
}