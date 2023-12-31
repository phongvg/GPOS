package com.gg.gpos.res.mapper;

import org.mapstruct.Mapper;

import com.gg.gpos.res.dto.DistrictDto;
import com.gg.gpos.res.entity.District;

/**
 * Generated by Speed Generator
 * 
 * @author <a href="mailto:ngtrungkien@gmail.com">Kien Nguyen</a>
 */
@Mapper(componentModel = "spring")
public interface DistrictMapper {
	District dtoToEntity(DistrictDto districtDto);
	DistrictDto entityToDto(District district);
}