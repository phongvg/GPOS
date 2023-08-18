package com.gg.gpos.menu.mapper;

import com.gg.gpos.menu.dto.HallplanDto;
import com.gg.gpos.menu.entity.Hallplan;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-01T17:40:55+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"
)
@Component
public class HallplanMapperImpl implements HallplanMapper {

    @Override
    public Hallplan dtoToEntity(HallplanDto hallplanDto) {
        if ( hallplanDto == null ) {
            return null;
        }

        Hallplan hallplan = new Hallplan();

        hallplan.setId( hallplanDto.getId() );
        hallplan.setItemId( hallplanDto.getItemId() );
        hallplan.setParentId( hallplanDto.getParentId() );
        hallplan.setCode( hallplanDto.getCode() );
        hallplan.setName( hallplanDto.getName() );
        hallplan.setStatus( hallplanDto.getStatus() );
        hallplan.setCurrentRes( hallplanDto.isCurrentRes() );
        hallplan.setObjectSifr( hallplanDto.getObjectSifr() );
        hallplan.setFlags( hallplanDto.getFlags() );

        return hallplan;
    }

    @Override
    public HallplanDto entityToDto(Hallplan hallplan) {
        if ( hallplan == null ) {
            return null;
        }

        HallplanDto hallplanDto = new HallplanDto();

        hallplanDto.setId( hallplan.getId() );
        hallplanDto.setItemId( hallplan.getItemId() );
        hallplanDto.setParentId( hallplan.getParentId() );
        hallplanDto.setCode( hallplan.getCode() );
        hallplanDto.setName( hallplan.getName() );
        hallplanDto.setStatus( hallplan.getStatus() );
        hallplanDto.setCurrentRes( hallplan.isCurrentRes() );
        hallplanDto.setObjectSifr( hallplan.getObjectSifr() );
        hallplanDto.setFlags( hallplan.getFlags() );

        return hallplanDto;
    }
}
