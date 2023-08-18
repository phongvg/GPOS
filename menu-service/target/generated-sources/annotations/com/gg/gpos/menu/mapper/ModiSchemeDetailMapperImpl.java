package com.gg.gpos.menu.mapper;

import com.gg.gpos.menu.dto.ModiSchemeDetailDto;
import com.gg.gpos.menu.entity.ModiSchemeDetail;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-01T17:40:56+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"
)
@Component
public class ModiSchemeDetailMapperImpl implements ModiSchemeDetailMapper {

    @Override
    public ModiSchemeDetail dtoToEntity(ModiSchemeDetailDto modiSchemeDetailDto) {
        if ( modiSchemeDetailDto == null ) {
            return null;
        }

        ModiSchemeDetail modiSchemeDetail = new ModiSchemeDetail();

        modiSchemeDetail.setId( modiSchemeDetailDto.getId() );
        modiSchemeDetail.setItemId( modiSchemeDetailDto.getItemId() );
        modiSchemeDetail.setParentId( modiSchemeDetailDto.getParentId() );
        modiSchemeDetail.setChangesPrice( modiSchemeDetailDto.getChangesPrice() );
        modiSchemeDetail.setUseDownLimit( modiSchemeDetailDto.getUseDownLimit() );
        modiSchemeDetail.setUpLimit( modiSchemeDetailDto.getUpLimit() );
        modiSchemeDetail.setFreeCount( modiSchemeDetailDto.getFreeCount() );
        modiSchemeDetail.setSourceIdent( modiSchemeDetailDto.getSourceIdent() );
        modiSchemeDetail.setSortNum( modiSchemeDetailDto.getSortNum() );
        modiSchemeDetail.setReadOnlyName( modiSchemeDetailDto.getReadOnlyName() );
        modiSchemeDetail.setUseUpLimit( modiSchemeDetailDto.getUseUpLimit() );
        modiSchemeDetail.setModiGroup( modiSchemeDetailDto.getModiGroup() );
        modiSchemeDetail.setShQuantity( modiSchemeDetailDto.getShQuantity() );
        modiSchemeDetail.setAssignChildsOnServer( modiSchemeDetailDto.getAssignChildsOnServer() );
        modiSchemeDetail.setReplaceDefModifier( modiSchemeDetailDto.getReplaceDefModifier() );
        modiSchemeDetail.setDownLimit( modiSchemeDetailDto.getDownLimit() );
        modiSchemeDetail.setDefaultModifier( modiSchemeDetailDto.getDefaultModifier() );
        modiSchemeDetail.setModiScheme( modiSchemeDetailDto.getModiScheme() );
        modiSchemeDetail.setObjectSifr( modiSchemeDetailDto.getObjectSifr() );
        modiSchemeDetail.setFlags( modiSchemeDetailDto.getFlags() );

        return modiSchemeDetail;
    }

    @Override
    public ModiSchemeDetailDto entityToDto(ModiSchemeDetail modiSchemeDetail) {
        if ( modiSchemeDetail == null ) {
            return null;
        }

        ModiSchemeDetailDto modiSchemeDetailDto = new ModiSchemeDetailDto();

        modiSchemeDetailDto.setId( modiSchemeDetail.getId() );
        modiSchemeDetailDto.setItemId( modiSchemeDetail.getItemId() );
        modiSchemeDetailDto.setParentId( modiSchemeDetail.getParentId() );
        modiSchemeDetailDto.setChangesPrice( modiSchemeDetail.getChangesPrice() );
        modiSchemeDetailDto.setUseDownLimit( modiSchemeDetail.getUseDownLimit() );
        modiSchemeDetailDto.setUpLimit( modiSchemeDetail.getUpLimit() );
        modiSchemeDetailDto.setFreeCount( modiSchemeDetail.getFreeCount() );
        modiSchemeDetailDto.setSourceIdent( modiSchemeDetail.getSourceIdent() );
        modiSchemeDetailDto.setSortNum( modiSchemeDetail.getSortNum() );
        modiSchemeDetailDto.setReadOnlyName( modiSchemeDetail.getReadOnlyName() );
        modiSchemeDetailDto.setUseUpLimit( modiSchemeDetail.getUseUpLimit() );
        modiSchemeDetailDto.setModiGroup( modiSchemeDetail.getModiGroup() );
        modiSchemeDetailDto.setShQuantity( modiSchemeDetail.getShQuantity() );
        modiSchemeDetailDto.setAssignChildsOnServer( modiSchemeDetail.getAssignChildsOnServer() );
        modiSchemeDetailDto.setReplaceDefModifier( modiSchemeDetail.getReplaceDefModifier() );
        modiSchemeDetailDto.setDownLimit( modiSchemeDetail.getDownLimit() );
        modiSchemeDetailDto.setDefaultModifier( modiSchemeDetail.getDefaultModifier() );
        modiSchemeDetailDto.setModiScheme( modiSchemeDetail.getModiScheme() );
        modiSchemeDetailDto.setObjectSifr( modiSchemeDetail.getObjectSifr() );
        modiSchemeDetailDto.setFlags( modiSchemeDetail.getFlags() );

        return modiSchemeDetailDto;
    }
}
