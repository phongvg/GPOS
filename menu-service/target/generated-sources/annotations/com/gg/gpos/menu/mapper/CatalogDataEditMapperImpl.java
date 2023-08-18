package com.gg.gpos.menu.mapper;

import com.gg.gpos.menu.dto.CatalogDataEditDto;
import com.gg.gpos.menu.entity.CatalogDataEdit;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-01T17:40:57+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"
)
@Component
public class CatalogDataEditMapperImpl implements CatalogDataEditMapper {

    @Override
    public CatalogDataEdit dtoToEntity(CatalogDataEditDto catalogDataEditDto) {
        if ( catalogDataEditDto == null ) {
            return null;
        }

        CatalogDataEdit catalogDataEdit = new CatalogDataEdit();

        catalogDataEdit.setId( catalogDataEditDto.getId() );
        catalogDataEdit.setType( catalogDataEditDto.getType() );
        catalogDataEdit.setCatalogId( catalogDataEditDto.getCatalogId() );
        catalogDataEdit.setValue( catalogDataEditDto.getValue() );

        return catalogDataEdit;
    }

    @Override
    public CatalogDataEditDto entityToDto(CatalogDataEdit catalogDataEdit) {
        if ( catalogDataEdit == null ) {
            return null;
        }

        CatalogDataEditDto catalogDataEditDto = new CatalogDataEditDto();

        catalogDataEditDto.setId( catalogDataEdit.getId() );
        catalogDataEditDto.setType( catalogDataEdit.getType() );
        catalogDataEditDto.setCatalogId( catalogDataEdit.getCatalogId() );
        catalogDataEditDto.setValue( catalogDataEdit.getValue() );

        return catalogDataEditDto;
    }
}
