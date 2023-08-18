package com.gg.gpos.menu.mapper;

import com.gg.gpos.menu.dto.MenuTypeDto;
import com.gg.gpos.menu.entity.MenuType;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-01T17:40:57+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"
)
@Component
public class MenuTypeMapperImpl implements MenuTypeMapper {

    @Override
    public MenuType dtoToEntity(MenuTypeDto menuTypeDto) {
        if ( menuTypeDto == null ) {
            return null;
        }

        MenuType menuType = new MenuType();

        menuType.setId( menuTypeDto.getId() );
        menuType.setCode( menuTypeDto.getCode() );
        menuType.setName( menuTypeDto.getName() );

        return menuType;
    }

    @Override
    public MenuTypeDto entityToDto(MenuType menuType) {
        if ( menuType == null ) {
            return null;
        }

        MenuTypeDto menuTypeDto = new MenuTypeDto();

        menuTypeDto.setId( menuType.getId() );
        menuTypeDto.setCode( menuType.getCode() );
        menuTypeDto.setName( menuType.getName() );

        return menuTypeDto;
    }
}
