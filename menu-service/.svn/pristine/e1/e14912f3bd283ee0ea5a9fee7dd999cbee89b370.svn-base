package com.gg.gpos.menu.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.gg.gpos.menu.dto.GroupParamDto;
import com.gg.gpos.menu.dto.ParamDto;
import com.gg.gpos.menu.entity.GroupParam;
import com.gg.gpos.menu.entity.Param;

@Mapper(componentModel = "spring")
public interface GroupParamMapper {
	GroupParam dtoToEntity(GroupParamDto groupParamDto);
	GroupParamDto entityToDto(GroupParam groupParam);
	
	@Mapping(target = "groupParam", expression = "java(null)")
	ParamDto paramToParamDto(Param param);
}
