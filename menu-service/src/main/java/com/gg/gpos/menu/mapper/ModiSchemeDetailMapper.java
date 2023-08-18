package com.gg.gpos.menu.mapper;

import org.mapstruct.Mapper;

import com.gg.gpos.menu.dto.ModiSchemeDetailDto;
import com.gg.gpos.menu.entity.ModiSchemeDetail;

/**
 * Generated by Speed Generator
 * 
 * @author <a href="mailto:ngtrungkien@gmail.com">Kien Nguyen</a>
 */
@Mapper(componentModel = "spring")
public interface ModiSchemeDetailMapper {
	ModiSchemeDetail dtoToEntity(ModiSchemeDetailDto modiSchemeDetailDto);
	ModiSchemeDetailDto entityToDto(ModiSchemeDetail modiSchemeDetail);
}