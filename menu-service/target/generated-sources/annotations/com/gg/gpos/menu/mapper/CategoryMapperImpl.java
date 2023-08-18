package com.gg.gpos.menu.mapper;

import com.gg.gpos.menu.dto.CategoryDto;
import com.gg.gpos.menu.entity.Category;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-01T17:40:56+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public Category dtoToEntity(CategoryDto categoryDto) {
        if ( categoryDto == null ) {
            return null;
        }

        Category category = new Category();

        category.setId( categoryDto.getId() );
        category.setItemId( categoryDto.getItemId() );
        category.setParentId( categoryDto.getParentId() );
        category.setCode( categoryDto.getCode() );
        category.setName( categoryDto.getName() );
        category.setStatus( categoryDto.getStatus() );
        category.setRightLvl( categoryDto.getRightLvl() );
        category.setObjectSifr( categoryDto.getObjectSifr() );
        category.setFlags( categoryDto.getFlags() );

        return category;
    }

    @Override
    public CategoryDto entityToDto(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setId( category.getId() );
        categoryDto.setItemId( category.getItemId() );
        categoryDto.setParentId( category.getParentId() );
        categoryDto.setCode( category.getCode() );
        categoryDto.setName( category.getName() );
        categoryDto.setStatus( category.getStatus() );
        categoryDto.setRightLvl( category.getRightLvl() );
        categoryDto.setObjectSifr( category.getObjectSifr() );
        categoryDto.setFlags( category.getFlags() );

        return categoryDto;
    }
}
