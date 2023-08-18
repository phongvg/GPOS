package com.gg.gpos.menu.mapper;

import com.gg.gpos.menu.dto.RestaurantDataEditDto;
import com.gg.gpos.menu.entity.RestaurantDataEdit;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-01T17:40:57+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"
)
@Component
public class RestaurantDataEditMapperImpl implements RestaurantDataEditMapper {

    @Override
    public RestaurantDataEdit dtoToEntity(RestaurantDataEditDto restaurantDataEditDto) {
        if ( restaurantDataEditDto == null ) {
            return null;
        }

        RestaurantDataEdit restaurantDataEdit = new RestaurantDataEdit();

        restaurantDataEdit.setId( restaurantDataEditDto.getId() );
        restaurantDataEdit.setType( restaurantDataEditDto.getType() );
        restaurantDataEdit.setRestaurantCode( restaurantDataEditDto.getRestaurantCode() );

        return restaurantDataEdit;
    }

    @Override
    public RestaurantDataEditDto entityToDto(RestaurantDataEdit restaurantDataEdit) {
        if ( restaurantDataEdit == null ) {
            return null;
        }

        RestaurantDataEditDto restaurantDataEditDto = new RestaurantDataEditDto();

        restaurantDataEditDto.setId( restaurantDataEdit.getId() );
        restaurantDataEditDto.setType( restaurantDataEdit.getType() );
        restaurantDataEditDto.setRestaurantCode( restaurantDataEdit.getRestaurantCode() );

        return restaurantDataEditDto;
    }
}
