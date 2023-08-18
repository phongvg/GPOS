package com.gg.gpos.menu.mapper;

import org.mapstruct.Mapper;
import com.gg.gpos.menu.entity.MenuType;
import com.gg.gpos.menu.dto.MenuTypeDto;

/**
 * Generated by Speed Generator
 * 
 * @author <a href="mailto:ngtrungkien@gmail.com">Kien Nguyen</a>
 */
@Mapper(componentModel = "spring")
public interface MenuTypeMapper {
	MenuType dtoToEntity(MenuTypeDto menuTypeDto);
	MenuTypeDto entityToDto(MenuType menuType);
}