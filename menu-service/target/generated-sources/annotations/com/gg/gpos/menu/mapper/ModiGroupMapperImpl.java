package com.gg.gpos.menu.mapper;

import com.gg.gpos.menu.dto.ModiGroupDto;
import com.gg.gpos.menu.entity.ModiGroup;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-01T17:40:57+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"
)
@Component
public class ModiGroupMapperImpl implements ModiGroupMapper {

    @Override
    public ModiGroup dtoToEntity(ModiGroupDto modiGroupDto) {
        if ( modiGroupDto == null ) {
            return null;
        }

        ModiGroup modiGroup = new ModiGroup();

        modiGroup.setId( modiGroupDto.getId() );
        modiGroup.setItemId( modiGroupDto.getItemId() );
        modiGroup.setParentId( modiGroupDto.getParentId() );
        modiGroup.setCode( modiGroupDto.getCode() );
        modiGroup.setName( modiGroupDto.getName() );
        modiGroup.setStatus( modiGroupDto.getStatus() );
        modiGroup.setObjectSifr( modiGroupDto.getObjectSifr() );
        modiGroup.setFlags( modiGroupDto.getFlags() );

        return modiGroup;
    }

    @Override
    public ModiGroupDto entityToDto(ModiGroup modiGroup) {
        if ( modiGroup == null ) {
            return null;
        }

        ModiGroupDto modiGroupDto = new ModiGroupDto();

        modiGroupDto.setId( modiGroup.getId() );
        modiGroupDto.setItemId( modiGroup.getItemId() );
        modiGroupDto.setParentId( modiGroup.getParentId() );
        modiGroupDto.setCode( modiGroup.getCode() );
        modiGroupDto.setName( modiGroup.getName() );
        modiGroupDto.setStatus( modiGroup.getStatus() );
        modiGroupDto.setObjectSifr( modiGroup.getObjectSifr() );
        modiGroupDto.setFlags( modiGroup.getFlags() );

        return modiGroupDto;
    }
}
