package com.gg.gpos.menu.mapper;

import com.gg.gpos.menu.dto.TableKitchenDto;
import com.gg.gpos.menu.entity.TableKitchen;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-01T17:40:56+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"
)
@Component
public class TableKitchenMapperImpl implements TableKitchenMapper {

    @Override
    public TableKitchen dtoToEntity(TableKitchenDto tableKitchenDto) {
        if ( tableKitchenDto == null ) {
            return null;
        }

        TableKitchen tableKitchen = new TableKitchen();

        tableKitchen.setId( tableKitchenDto.getId() );
        tableKitchen.setItemId( tableKitchenDto.getItemId() );
        tableKitchen.setParentId( tableKitchenDto.getParentId() );
        tableKitchen.setCode( tableKitchenDto.getCode() );
        tableKitchen.setName( tableKitchenDto.getName() );
        tableKitchen.setStatus( tableKitchenDto.getStatus() );
        tableKitchen.setHallPlanId( tableKitchenDto.getHallPlanId() );
        tableKitchen.setCurrentRes( tableKitchenDto.isCurrentRes() );
        tableKitchen.setRightLvl( tableKitchenDto.getRightLvl() );
        tableKitchen.setObjectSifr( tableKitchenDto.getObjectSifr() );
        tableKitchen.setFlags( tableKitchenDto.getFlags() );

        return tableKitchen;
    }

    @Override
    public TableKitchenDto entityToDto(TableKitchen tableKitchen) {
        if ( tableKitchen == null ) {
            return null;
        }

        TableKitchenDto tableKitchenDto = new TableKitchenDto();

        tableKitchenDto.setId( tableKitchen.getId() );
        tableKitchenDto.setItemId( tableKitchen.getItemId() );
        tableKitchenDto.setParentId( tableKitchen.getParentId() );
        tableKitchenDto.setCode( tableKitchen.getCode() );
        tableKitchenDto.setName( tableKitchen.getName() );
        tableKitchenDto.setStatus( tableKitchen.getStatus() );
        tableKitchenDto.setHallPlanId( tableKitchen.getHallPlanId() );
        tableKitchenDto.setCurrentRes( tableKitchen.isCurrentRes() );
        tableKitchenDto.setRightLvl( tableKitchen.getRightLvl() );
        tableKitchenDto.setObjectSifr( tableKitchen.getObjectSifr() );
        tableKitchenDto.setFlags( tableKitchen.getFlags() );

        return tableKitchenDto;
    }
}
