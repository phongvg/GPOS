package com.gg.gpos.menu.mapper;

import com.gg.gpos.menu.dto.RestaurantMasterDto;
import com.gg.gpos.menu.entity.RestaurantMaster;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-01T17:40:57+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"
)
@Component
public class RestaurantMasterMapperImpl implements RestaurantMasterMapper {

    @Override
    public RestaurantMaster dtoToEntity(RestaurantMasterDto restaurantMasterDto) {
        if ( restaurantMasterDto == null ) {
            return null;
        }

        RestaurantMaster restaurantMaster = new RestaurantMaster();

        restaurantMaster.setId( restaurantMasterDto.getId() );
        restaurantMaster.setItemId( restaurantMasterDto.getItemId() );
        restaurantMaster.setParentId( restaurantMasterDto.getParentId() );
        restaurantMaster.setCode( restaurantMasterDto.getCode() );
        restaurantMaster.setName( restaurantMasterDto.getName() );
        restaurantMaster.setStatus( restaurantMasterDto.getStatus() );
        restaurantMaster.setObjectSifr( restaurantMasterDto.getObjectSifr() );
        restaurantMaster.setFlags( restaurantMasterDto.getFlags() );
        restaurantMaster.setGenGggRname( restaurantMasterDto.getGenGggRname() );
        restaurantMaster.setGenGggTel( restaurantMasterDto.getGenGggTel() );
        restaurantMaster.setGenGggAdd( restaurantMasterDto.getGenGggAdd() );
        restaurantMaster.setGenGggWeb( restaurantMasterDto.getGenGggWeb() );
        restaurantMaster.setGenGggRco( restaurantMasterDto.getGenGggRco() );
        restaurantMaster.setGenSapCode( restaurantMasterDto.getGenSapCode() );
        restaurantMaster.setGenGggBrand( restaurantMasterDto.getGenGggBrand() );
        restaurantMaster.setGenSapBankTerminalId( restaurantMasterDto.getGenSapBankTerminalId() );

        return restaurantMaster;
    }

    @Override
    public RestaurantMasterDto entityToDto(RestaurantMaster restaurantMaster) {
        if ( restaurantMaster == null ) {
            return null;
        }

        RestaurantMasterDto restaurantMasterDto = new RestaurantMasterDto();

        restaurantMasterDto.setId( restaurantMaster.getId() );
        restaurantMasterDto.setItemId( restaurantMaster.getItemId() );
        restaurantMasterDto.setParentId( restaurantMaster.getParentId() );
        restaurantMasterDto.setCode( restaurantMaster.getCode() );
        restaurantMasterDto.setName( restaurantMaster.getName() );
        restaurantMasterDto.setStatus( restaurantMaster.getStatus() );
        restaurantMasterDto.setObjectSifr( restaurantMaster.getObjectSifr() );
        restaurantMasterDto.setFlags( restaurantMaster.getFlags() );
        restaurantMasterDto.setGenGggRname( restaurantMaster.getGenGggRname() );
        restaurantMasterDto.setGenGggTel( restaurantMaster.getGenGggTel() );
        restaurantMasterDto.setGenGggAdd( restaurantMaster.getGenGggAdd() );
        restaurantMasterDto.setGenGggWeb( restaurantMaster.getGenGggWeb() );
        restaurantMasterDto.setGenGggRco( restaurantMaster.getGenGggRco() );
        restaurantMasterDto.setGenSapCode( restaurantMaster.getGenSapCode() );
        restaurantMasterDto.setGenGggBrand( restaurantMaster.getGenGggBrand() );
        restaurantMasterDto.setGenSapBankTerminalId( restaurantMaster.getGenSapBankTerminalId() );

        return restaurantMasterDto;
    }
}
