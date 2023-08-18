package com.gg.gpos.menu.mapper;

import com.gg.gpos.menu.dto.GuestTypeDto;
import com.gg.gpos.menu.entity.GuestType;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-01T17:40:56+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"
)
@Component
public class GuestTypeMapperImpl implements GuestTypeMapper {

    @Override
    public GuestType dtoToEntity(GuestTypeDto guestTypeDto) {
        if ( guestTypeDto == null ) {
            return null;
        }

        GuestType guestType = new GuestType();

        guestType.setId( guestTypeDto.getId() );
        guestType.setItemId( guestTypeDto.getItemId() );
        guestType.setParentId( guestTypeDto.getParentId() );
        guestType.setCode( guestTypeDto.getCode() );
        guestType.setName( guestTypeDto.getName() );
        guestType.setStatus( guestTypeDto.getStatus() );
        guestType.setRightLvl( guestTypeDto.getRightLvl() );
        guestType.setObjectSifr( guestTypeDto.getObjectSifr() );
        guestType.setFlags( guestTypeDto.getFlags() );

        return guestType;
    }

    @Override
    public GuestTypeDto entityToDto(GuestType guestType) {
        if ( guestType == null ) {
            return null;
        }

        GuestTypeDto guestTypeDto = new GuestTypeDto();

        guestTypeDto.setId( guestType.getId() );
        guestTypeDto.setItemId( guestType.getItemId() );
        guestTypeDto.setParentId( guestType.getParentId() );
        guestTypeDto.setCode( guestType.getCode() );
        guestTypeDto.setName( guestType.getName() );
        guestTypeDto.setStatus( guestType.getStatus() );
        guestTypeDto.setRightLvl( guestType.getRightLvl() );
        guestTypeDto.setObjectSifr( guestType.getObjectSifr() );
        guestTypeDto.setFlags( guestType.getFlags() );

        return guestTypeDto;
    }
}
