package com.gg.gpos.menu.mapper;

import com.gg.gpos.menu.dto.CoCategoryDto;
import com.gg.gpos.menu.dto.DigitalMenuDto;
import com.gg.gpos.menu.entity.CoCategory;
import com.gg.gpos.menu.entity.DigitalMenu;
import org.mapstruct.Mapping;

public interface DigitalMenuMapper {

    DigitalMenu dtoToEntity(DigitalMenuDto digitalMenuDto);
    DigitalMenuDto entityToDto(DigitalMenu digitalMenu);

    @Mapping(target ="soCategory" , expression = "java(null)")
    @Mapping(target ="coFoodGroupDisplays" , expression = "java(null)")
    @Mapping(target ="digitalMenus" , expression = "java(null)")
    CoCategoryDto coCategoryToCoCategoryDto(CoCategory coCategory);

}
