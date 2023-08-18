package com.gg.gpos.menu.mapper;

import com.gg.gpos.menu.dto.ModiSchemeDto;
import com.gg.gpos.menu.entity.ModiScheme;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-01T17:40:57+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"
)
@Component
public class ModiSchemeMapperImpl implements ModiSchemeMapper {

    @Override
    public ModiScheme dtoToEntity(ModiSchemeDto modiSchemeDto) {
        if ( modiSchemeDto == null ) {
            return null;
        }

        ModiScheme modiScheme = new ModiScheme();

        modiScheme.setId( modiSchemeDto.getId() );
        modiScheme.setItemId( modiSchemeDto.getItemId() );
        modiScheme.setParentId( modiSchemeDto.getParentId() );
        modiScheme.setCode( modiSchemeDto.getCode() );
        modiScheme.setName( modiSchemeDto.getName() );
        modiScheme.setStatus( modiSchemeDto.getStatus() );
        modiScheme.setIgnoreDefaultForKitchen( modiSchemeDto.getIgnoreDefaultForKitchen() );
        modiScheme.setSourceIdent( modiSchemeDto.getSourceIdent() );
        modiScheme.setModiSchemeType( modiSchemeDto.getModiSchemeType() );
        modiScheme.setEditRight( modiSchemeDto.getEditRight() );
        modiScheme.setActiveHierarchy( modiSchemeDto.getActiveHierarchy() );
        modiScheme.setAssignChildsOnServer( modiSchemeDto.getAssignChildsOnServer() );
        modiScheme.setAutoOpen( modiSchemeDto.getAutoOpen() );
        modiScheme.setObjectSifr( modiSchemeDto.getObjectSifr() );
        modiScheme.setFlags( modiSchemeDto.getFlags() );

        return modiScheme;
    }

    @Override
    public ModiSchemeDto entityToDto(ModiScheme modiScheme) {
        if ( modiScheme == null ) {
            return null;
        }

        ModiSchemeDto modiSchemeDto = new ModiSchemeDto();

        modiSchemeDto.setId( modiScheme.getId() );
        modiSchemeDto.setItemId( modiScheme.getItemId() );
        modiSchemeDto.setParentId( modiScheme.getParentId() );
        modiSchemeDto.setCode( modiScheme.getCode() );
        modiSchemeDto.setName( modiScheme.getName() );
        modiSchemeDto.setStatus( modiScheme.getStatus() );
        modiSchemeDto.setIgnoreDefaultForKitchen( modiScheme.getIgnoreDefaultForKitchen() );
        modiSchemeDto.setSourceIdent( modiScheme.getSourceIdent() );
        modiSchemeDto.setModiSchemeType( modiScheme.getModiSchemeType() );
        modiSchemeDto.setEditRight( modiScheme.getEditRight() );
        modiSchemeDto.setActiveHierarchy( modiScheme.getActiveHierarchy() );
        modiSchemeDto.setAssignChildsOnServer( modiScheme.getAssignChildsOnServer() );
        modiSchemeDto.setAutoOpen( modiScheme.getAutoOpen() );
        modiSchemeDto.setObjectSifr( modiScheme.getObjectSifr() );
        modiSchemeDto.setFlags( modiScheme.getFlags() );

        return modiSchemeDto;
    }
}
