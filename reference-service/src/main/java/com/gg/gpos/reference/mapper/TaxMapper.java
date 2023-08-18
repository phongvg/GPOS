package com.gg.gpos.reference.mapper;

import org.mapstruct.Mapper;

import com.gg.gpos.reference.dto.TaxDto;
import com.gg.gpos.reference.entity.Tax;

@Mapper(componentModel = "spring")
public interface TaxMapper {
	Tax dtoToEntity(TaxDto taxDto);
	TaxDto entityToDto(Tax tax);
}
