package com.gg.gpos.menu.mapper;

import com.gg.gpos.menu.dto.KaloGroupDto;
import com.gg.gpos.menu.entity.KaloGroup;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-01T17:40:57+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"
)
@Component
public class KaloGroupMapperImpl implements KaloGroupMapper {

    @Override
    public KaloGroup dtoToEntity(KaloGroupDto kaloGroupDto) {
        if ( kaloGroupDto == null ) {
            return null;
        }

        KaloGroup kaloGroup = new KaloGroup();

        kaloGroup.setId( kaloGroupDto.getId() );
        kaloGroup.setCode( kaloGroupDto.getCode() );
        kaloGroup.setName( kaloGroupDto.getName() );
        kaloGroup.setStatus( kaloGroupDto.getStatus() );
        kaloGroup.setMaxKalo( kaloGroupDto.getMaxKalo() );

        return kaloGroup;
    }

    @Override
    public KaloGroupDto entityToDto(KaloGroup kaloGroup) {
        if ( kaloGroup == null ) {
            return null;
        }

        KaloGroupDto kaloGroupDto = new KaloGroupDto();

        kaloGroupDto.setId( kaloGroup.getId() );
        kaloGroupDto.setCode( kaloGroup.getCode() );
        kaloGroupDto.setName( kaloGroup.getName() );
        kaloGroupDto.setStatus( kaloGroup.getStatus() );
        kaloGroupDto.setMaxKalo( kaloGroup.getMaxKalo() );

        return kaloGroupDto;
    }
}
