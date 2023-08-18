package com.gg.gpos.menu.mapper;

import com.gg.gpos.menu.dto.CatalogApplyToRestaurantDto;
import com.gg.gpos.menu.entity.CatalogApplyToRestaurant;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-01T17:40:57+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"
)
@Component
public class CatalogApplyToRestaurantMapperImpl implements CatalogApplyToRestaurantMapper {

    @Override
    public CatalogApplyToRestaurant dtoToEntity(CatalogApplyToRestaurantDto catalogApplyToRestaurantDto) {
        if ( catalogApplyToRestaurantDto == null ) {
            return null;
        }

        CatalogApplyToRestaurant catalogApplyToRestaurant = new CatalogApplyToRestaurant();

        catalogApplyToRestaurant.setId( catalogApplyToRestaurantDto.getId() );
        catalogApplyToRestaurant.setRestaurantCode( catalogApplyToRestaurantDto.getRestaurantCode() );
        catalogApplyToRestaurant.setCoId( catalogApplyToRestaurantDto.getCoId() );
        catalogApplyToRestaurant.setSoId( catalogApplyToRestaurantDto.getSoId() );
        catalogApplyToRestaurant.setGroupParamId( catalogApplyToRestaurantDto.getGroupParamId() );

        return catalogApplyToRestaurant;
    }

    @Override
    public CatalogApplyToRestaurantDto entityToDto(CatalogApplyToRestaurant catalogApplyToRestaurant) {
        if ( catalogApplyToRestaurant == null ) {
            return null;
        }

        CatalogApplyToRestaurantDto catalogApplyToRestaurantDto = new CatalogApplyToRestaurantDto();

        catalogApplyToRestaurantDto.setId( catalogApplyToRestaurant.getId() );
        catalogApplyToRestaurantDto.setRestaurantCode( catalogApplyToRestaurant.getRestaurantCode() );
        catalogApplyToRestaurantDto.setCoId( catalogApplyToRestaurant.getCoId() );
        catalogApplyToRestaurantDto.setSoId( catalogApplyToRestaurant.getSoId() );
        catalogApplyToRestaurantDto.setGroupParamId( catalogApplyToRestaurant.getGroupParamId() );

        return catalogApplyToRestaurantDto;
    }
}
