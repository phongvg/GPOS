package com.gg.gpos.menu.mapper;

import com.gg.gpos.menu.dto.ModifierDto;
import com.gg.gpos.menu.entity.Modifier;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-01T17:40:57+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"
)
@Component
public class ModifierMapperImpl implements ModifierMapper {

    @Override
    public Modifier dtoToEntity(ModifierDto modifierDto) {
        if ( modifierDto == null ) {
            return null;
        }

        Modifier modifier = new Modifier();

        modifier.setId( modifierDto.getId() );
        modifier.setItemId( modifierDto.getItemId() );
        modifier.setParentId( modifierDto.getParentId() );
        modifier.setCode( modifierDto.getCode() );
        modifier.setName( modifierDto.getName() );
        modifier.setUnsignedName( modifierDto.getUnsignedName() );
        modifier.setStatus( modifierDto.getStatus() );
        modifier.setDish( modifierDto.getDish() );
        modifier.setMaxOneDish( modifierDto.getMaxOneDish() );
        modifier.setRightLvl( modifierDto.getRightLvl() );
        modifier.setObjectSifr( modifierDto.getObjectSifr() );
        modifier.setFlags( modifierDto.getFlags() );

        return modifier;
    }

    @Override
    public ModifierDto entityToDto(Modifier modifier) {
        if ( modifier == null ) {
            return null;
        }

        ModifierDto modifierDto = new ModifierDto();

        modifierDto.setId( modifier.getId() );
        modifierDto.setItemId( modifier.getItemId() );
        modifierDto.setParentId( modifier.getParentId() );
        modifierDto.setCode( modifier.getCode() );
        modifierDto.setName( modifier.getName() );
        modifierDto.setUnsignedName( modifier.getUnsignedName() );
        modifierDto.setStatus( modifier.getStatus() );
        modifierDto.setDish( modifier.getDish() );
        modifierDto.setMaxOneDish( modifier.getMaxOneDish() );
        modifierDto.setRightLvl( modifier.getRightLvl() );
        modifierDto.setObjectSifr( modifier.getObjectSifr() );
        modifierDto.setFlags( modifier.getFlags() );

        return modifierDto;
    }
}
