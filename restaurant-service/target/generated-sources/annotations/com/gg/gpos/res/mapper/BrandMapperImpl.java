package com.gg.gpos.res.mapper;

import com.gg.gpos.res.dto.BrandDto;
import com.gg.gpos.res.entity.Brand;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-01T17:40:07+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"
)
@Component
public class BrandMapperImpl implements BrandMapper {

    @Override
    public Brand dtoToEntity(BrandDto brandDto) {
        if ( brandDto == null ) {
            return null;
        }

        Brand brand = new Brand();

        brand.setId( brandDto.getId() );
        brand.setName( brandDto.getName() );
        brand.setLongName( brandDto.getLongName() );
        brand.setDisplayOrder( brandDto.getDisplayOrder() );

        return brand;
    }

    @Override
    public BrandDto entityToDto(Brand brand) {
        if ( brand == null ) {
            return null;
        }

        BrandDto brandDto = new BrandDto();

        brandDto.setId( brand.getId() );
        brandDto.setName( brand.getName() );
        brandDto.setLongName( brand.getLongName() );
        brandDto.setDisplayOrder( brand.getDisplayOrder() );

        return brandDto;
    }
}
