package com.gg.gpos.menu.mapper;

import com.gg.gpos.menu.dto.KitchenTypeDto;
import com.gg.gpos.menu.entity.KitchenType;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-01T17:40:55+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"
)
@Component
public class KitchenTypeMapperImpl implements KitchenTypeMapper {

    @Override
    public KitchenType dtoToEntity(KitchenTypeDto kitchenTypeDto) {
        if ( kitchenTypeDto == null ) {
            return null;
        }

        KitchenType kitchenType = new KitchenType();

        kitchenType.setId( kitchenTypeDto.getId() );
        kitchenType.setRestaurantCode( kitchenTypeDto.getRestaurantCode() );
        kitchenType.setName( kitchenTypeDto.getName() );

        return kitchenType;
    }

    @Override
    public KitchenTypeDto entityToDto(KitchenType kitchenType) {
        if ( kitchenType == null ) {
            return null;
        }

        KitchenTypeDto kitchenTypeDto = new KitchenTypeDto();

        kitchenTypeDto.setId( kitchenType.getId() );
        kitchenTypeDto.setRestaurantCode( kitchenType.getRestaurantCode() );
        kitchenTypeDto.setName( kitchenType.getName() );

        return kitchenTypeDto;
    }
}
