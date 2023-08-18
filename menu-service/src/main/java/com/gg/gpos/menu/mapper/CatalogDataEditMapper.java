package com.gg.gpos.menu.mapper;

import org.mapstruct.Mapper;

import com.gg.gpos.menu.dto.CatalogDataEditDto;
import com.gg.gpos.menu.entity.CatalogDataEdit;
@Mapper(componentModel = "spring")
public interface CatalogDataEditMapper {
	CatalogDataEdit dtoToEntity(CatalogDataEditDto catalogDataEditDto);
	CatalogDataEditDto entityToDto(CatalogDataEdit catalogDataEdit);
}
