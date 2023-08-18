package com.gg.gpos.menu.mapper;

import com.gg.gpos.menu.dto.OrderCategoryDto;
import com.gg.gpos.menu.entity.OrderCategory;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-01T17:40:56+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"
)
@Component
public class OrderCategoryMapperImpl implements OrderCategoryMapper {

    @Override
    public OrderCategory dtoToEntity(OrderCategoryDto orderCategoryDto) {
        if ( orderCategoryDto == null ) {
            return null;
        }

        OrderCategory orderCategory = new OrderCategory();

        orderCategory.setId( orderCategoryDto.getId() );
        orderCategory.setItemId( orderCategoryDto.getItemId() );
        orderCategory.setParentId( orderCategoryDto.getParentId() );
        orderCategory.setCode( orderCategoryDto.getCode() );
        orderCategory.setName( orderCategoryDto.getName() );
        orderCategory.setStatus( orderCategoryDto.getStatus() );
        orderCategory.setRightLvl( orderCategoryDto.getRightLvl() );
        orderCategory.setObjectSifr( orderCategoryDto.getObjectSifr() );
        orderCategory.setFlags( orderCategoryDto.getFlags() );

        return orderCategory;
    }

    @Override
    public OrderCategoryDto entityToDto(OrderCategory orderCategory) {
        if ( orderCategory == null ) {
            return null;
        }

        OrderCategoryDto orderCategoryDto = new OrderCategoryDto();

        orderCategoryDto.setId( orderCategory.getId() );
        orderCategoryDto.setItemId( orderCategory.getItemId() );
        orderCategoryDto.setParentId( orderCategory.getParentId() );
        orderCategoryDto.setCode( orderCategory.getCode() );
        orderCategoryDto.setName( orderCategory.getName() );
        orderCategoryDto.setStatus( orderCategory.getStatus() );
        orderCategoryDto.setRightLvl( orderCategory.getRightLvl() );
        orderCategoryDto.setObjectSifr( orderCategory.getObjectSifr() );
        orderCategoryDto.setFlags( orderCategory.getFlags() );

        return orderCategoryDto;
    }
}
