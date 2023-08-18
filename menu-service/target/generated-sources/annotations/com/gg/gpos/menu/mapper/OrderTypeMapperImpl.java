package com.gg.gpos.menu.mapper;

import com.gg.gpos.menu.dto.OrderTypeDto;
import com.gg.gpos.menu.entity.OrderType;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-01T17:40:56+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"
)
@Component
public class OrderTypeMapperImpl implements OrderTypeMapper {

    @Override
    public OrderType dtoToEntity(OrderTypeDto orderTypeDto) {
        if ( orderTypeDto == null ) {
            return null;
        }

        OrderType orderType = new OrderType();

        orderType.setId( orderTypeDto.getId() );
        orderType.setItemId( orderTypeDto.getItemId() );
        orderType.setParentId( orderTypeDto.getParentId() );
        orderType.setCode( orderTypeDto.getCode() );
        orderType.setName( orderTypeDto.getName() );
        orderType.setStatus( orderTypeDto.getStatus() );
        orderType.setRightLvl( orderTypeDto.getRightLvl() );
        orderType.setObjectSifr( orderTypeDto.getObjectSifr() );
        orderType.setFlags( orderTypeDto.getFlags() );

        return orderType;
    }

    @Override
    public OrderTypeDto entityToDto(OrderType orderType) {
        if ( orderType == null ) {
            return null;
        }

        OrderTypeDto orderTypeDto = new OrderTypeDto();

        orderTypeDto.setId( orderType.getId() );
        orderTypeDto.setItemId( orderType.getItemId() );
        orderTypeDto.setParentId( orderType.getParentId() );
        orderTypeDto.setCode( orderType.getCode() );
        orderTypeDto.setName( orderType.getName() );
        orderTypeDto.setStatus( orderType.getStatus() );
        orderTypeDto.setRightLvl( orderType.getRightLvl() );
        orderTypeDto.setObjectSifr( orderType.getObjectSifr() );
        orderTypeDto.setFlags( orderType.getFlags() );

        return orderTypeDto;
    }
}
