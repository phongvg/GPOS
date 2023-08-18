package com.gg.gpos.reference.mapper;

import org.mapstruct.Mapper;

import com.gg.gpos.reference.dto.SystemParameterDto;
import com.gg.gpos.reference.entity.SystemParameter;

/**
 * Generated by Speed Generator
 * 
 * @author <a href="mailto:ngtrungkien@gmail.com">Kien Nguyen</a>
 */
@Mapper(componentModel = "spring")
public interface SystemParameterMapper {
	SystemParameter dtoToEntity(SystemParameterDto systemParameterDto);
	SystemParameterDto entityToDto(SystemParameter systemParameter);
}