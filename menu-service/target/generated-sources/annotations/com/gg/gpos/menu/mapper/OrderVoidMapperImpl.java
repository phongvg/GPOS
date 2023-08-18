package com.gg.gpos.menu.mapper;

import com.gg.gpos.menu.dto.OrderVoidDto;
import com.gg.gpos.menu.entity.OrderVoid;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-01T17:40:57+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"
)
@Component
public class OrderVoidMapperImpl implements OrderVoidMapper {

    @Override
    public OrderVoid dtoToEntity(OrderVoidDto orderVoidDto) {
        if ( orderVoidDto == null ) {
            return null;
        }

        OrderVoid orderVoid = new OrderVoid();

        orderVoid.setId( orderVoidDto.getId() );
        orderVoid.setItemId( orderVoidDto.getItemId() );
        orderVoid.setParentId( orderVoidDto.getParentId() );
        orderVoid.setCode( orderVoidDto.getCode() );
        orderVoid.setName( orderVoidDto.getName() );
        orderVoid.setStatus( orderVoidDto.getStatus() );
        orderVoid.setInputName( orderVoidDto.getInputName() );
        orderVoid.setImplOnDishVoid( orderVoidDto.getImplOnDishVoid() );
        orderVoid.setImplOnUnprintedDish( orderVoidDto.getImplOnUnprintedDish() );
        orderVoid.setRightLvl( orderVoidDto.getRightLvl() );
        orderVoid.setObjectSifr( orderVoidDto.getObjectSifr() );
        orderVoid.setFlags( orderVoidDto.getFlags() );

        return orderVoid;
    }

    @Override
    public OrderVoidDto entityToDto(OrderVoid orderVoid) {
        if ( orderVoid == null ) {
            return null;
        }

        OrderVoidDto orderVoidDto = new OrderVoidDto();

        orderVoidDto.setId( orderVoid.getId() );
        orderVoidDto.setItemId( orderVoid.getItemId() );
        orderVoidDto.setParentId( orderVoid.getParentId() );
        orderVoidDto.setCode( orderVoid.getCode() );
        orderVoidDto.setName( orderVoid.getName() );
        orderVoidDto.setStatus( orderVoid.getStatus() );
        orderVoidDto.setInputName( orderVoid.getInputName() );
        orderVoidDto.setImplOnDishVoid( orderVoid.getImplOnDishVoid() );
        orderVoidDto.setImplOnUnprintedDish( orderVoid.getImplOnUnprintedDish() );
        orderVoidDto.setRightLvl( orderVoid.getRightLvl() );
        orderVoidDto.setObjectSifr( orderVoid.getObjectSifr() );
        orderVoidDto.setFlags( orderVoid.getFlags() );

        return orderVoidDto;
    }
}
