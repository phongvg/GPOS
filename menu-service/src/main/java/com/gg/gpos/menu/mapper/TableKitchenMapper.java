package com.gg.gpos.menu.mapper;

import org.mapstruct.Mapper;

import com.gg.gpos.menu.dto.TableKitchenDto;
import com.gg.gpos.menu.entity.TableKitchen;


/**
 * Generated by Speed Generator
 * 
 * @author <a href="mailto:ngtrungkien@gmail.com">Kien Nguyen</a>
 */
@Mapper(componentModel = "spring")
public interface TableKitchenMapper {
	TableKitchen dtoToEntity(TableKitchenDto tableKitchenDto);
	TableKitchenDto entityToDto(TableKitchen tableKitchen);
}