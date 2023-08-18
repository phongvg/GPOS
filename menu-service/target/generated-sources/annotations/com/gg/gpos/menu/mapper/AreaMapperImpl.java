package com.gg.gpos.menu.mapper;

import com.gg.gpos.menu.dto.AreaDto;
import com.gg.gpos.menu.dto.FoodGroupDto;
import com.gg.gpos.menu.dto.FoodGroupItemDto;
import com.gg.gpos.menu.dto.FoodItemDto;
import com.gg.gpos.menu.dto.HallplanDto;
import com.gg.gpos.menu.dto.KdsDto;
import com.gg.gpos.menu.dto.MenuTypeDto;
import com.gg.gpos.menu.dto.PrintGroupDto;
import com.gg.gpos.menu.entity.Area;
import com.gg.gpos.menu.entity.FoodGroup;
import com.gg.gpos.menu.entity.FoodGroupItem;
import com.gg.gpos.menu.entity.FoodItem;
import com.gg.gpos.menu.entity.Hallplan;
import com.gg.gpos.menu.entity.Kds;
import com.gg.gpos.menu.entity.MenuType;
import com.gg.gpos.menu.entity.PrintGroup;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-01T17:40:57+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"
)
@Component
public class AreaMapperImpl implements AreaMapper {

    @Override
    public Area dtoToEntity(AreaDto areaDto) {
        if ( areaDto == null ) {
            return null;
        }

        Area area = new Area();

        area.setId( areaDto.getId() );
        area.setRestaurantCode( areaDto.getRestaurantCode() );
        area.setCode( areaDto.getCode() );
        area.setName( areaDto.getName() );
        area.setPrinterName( areaDto.getPrinterName() );
        area.setPrintPosCode( areaDto.getPrintPosCode() );
        area.setStatus( areaDto.isStatus() );
        area.setType( areaDto.getType() );
        area.setKds( kdsDtoToKds( areaDto.getKds() ) );
        area.setHallplan( hallplanDtoToHallplan( areaDto.getHallplan() ) );

        return area;
    }

    @Override
    public AreaDto entityToDto(Area area) {
        if ( area == null ) {
            return null;
        }

        AreaDto areaDto = new AreaDto();

        areaDto.setId( area.getId() );
        areaDto.setRestaurantCode( area.getRestaurantCode() );
        areaDto.setCode( area.getCode() );
        areaDto.setName( area.getName() );
        areaDto.setPrinterName( area.getPrinterName() );
        areaDto.setPrintPosCode( area.getPrintPosCode() );
        areaDto.setStatus( area.isStatus() );
        areaDto.setType( area.getType() );
        areaDto.setHallplan( hallplanToHallplanDto( area.getHallplan() ) );

        areaDto.setKds( null );

        return areaDto;
    }

    protected MenuType menuTypeDtoToMenuType(MenuTypeDto menuTypeDto) {
        if ( menuTypeDto == null ) {
            return null;
        }

        MenuType menuType = new MenuType();

        menuType.setId( menuTypeDto.getId() );
        menuType.setCode( menuTypeDto.getCode() );
        menuType.setName( menuTypeDto.getName() );

        return menuType;
    }

    protected List<FoodGroupItem> foodGroupItemDtoListToFoodGroupItemList(List<FoodGroupItemDto> list) {
        if ( list == null ) {
            return null;
        }

        List<FoodGroupItem> list1 = new ArrayList<FoodGroupItem>( list.size() );
        for ( FoodGroupItemDto foodGroupItemDto : list ) {
            list1.add( foodGroupItemDtoToFoodGroupItem( foodGroupItemDto ) );
        }

        return list1;
    }

    protected FoodGroup foodGroupDtoToFoodGroup(FoodGroupDto foodGroupDto) {
        if ( foodGroupDto == null ) {
            return null;
        }

        FoodGroup foodGroup = new FoodGroup();

        foodGroup.setId( foodGroupDto.getId() );
        foodGroup.setRestaurantCode( foodGroupDto.getRestaurantCode() );
        foodGroup.setType( foodGroupDto.getType() );
        foodGroup.setCode( foodGroupDto.getCode() );
        foodGroup.setNameEn( foodGroupDto.getNameEn() );
        foodGroup.setNameVn( foodGroupDto.getNameVn() );
        foodGroup.setStatus( foodGroupDto.isStatus() );
        foodGroup.setCreatedBy( foodGroupDto.getCreatedBy() );
        foodGroup.setCreatedDate( foodGroupDto.getCreatedDate() );
        foodGroup.setModifiedBy( foodGroupDto.getModifiedBy() );
        foodGroup.setModifiedDate( foodGroupDto.getModifiedDate() );
        foodGroup.setLevel( foodGroupDto.getLevel() );
        foodGroup.setSrcImage( foodGroupDto.getSrcImage() );
        foodGroup.setUnsignedName( foodGroupDto.getUnsignedName() );
        foodGroup.setMenuType( menuTypeDtoToMenuType( foodGroupDto.getMenuType() ) );
        foodGroup.setParent( foodGroupDtoToFoodGroup( foodGroupDto.getParent() ) );
        foodGroup.setFoodGroupItems( foodGroupItemDtoListToFoodGroupItemList( foodGroupDto.getFoodGroupItems() ) );
        foodGroup.setChanged( foodGroupDto.getChanged() );

        return foodGroup;
    }

    protected FoodGroupItem foodGroupItemDtoToFoodGroupItem(FoodGroupItemDto foodGroupItemDto) {
        if ( foodGroupItemDto == null ) {
            return null;
        }

        FoodGroupItem foodGroupItem = new FoodGroupItem();

        foodGroupItem.setId( foodGroupItemDto.getId() );
        foodGroupItem.setRestaurantCode( foodGroupItemDto.getRestaurantCode() );
        foodGroupItem.setItemOrder( foodGroupItemDto.getItemOrder() );
        foodGroupItem.setFoodGroup( foodGroupDtoToFoodGroup( foodGroupItemDto.getFoodGroup() ) );
        foodGroupItem.setFoodItem( foodItemDtoToFoodItem( foodGroupItemDto.getFoodItem() ) );

        return foodGroupItem;
    }

    protected FoodItem foodItemDtoToFoodItem(FoodItemDto foodItemDto) {
        if ( foodItemDto == null ) {
            return null;
        }

        FoodItem foodItem = new FoodItem();

        foodItem.setId( foodItemDto.getId() );
        foodItem.setItemId( foodItemDto.getItemId() );
        foodItem.setParentId( foodItemDto.getParentId() );
        foodItem.setType( foodItemDto.getType() );
        foodItem.setCode( foodItemDto.getCode() );
        foodItem.setName( foodItemDto.getName() );
        foodItem.setUnsignedName( foodItemDto.getUnsignedName() );
        foodItem.setStatus( foodItemDto.getStatus() );
        foodItem.setModiScheme( foodItemDto.getModiScheme() );
        foodItem.setComboScheme( foodItemDto.getComboScheme() );
        foodItem.setComboJoinMode( foodItemDto.getComboJoinMode() );
        foodItem.setRightLvl( foodItemDto.getRightLvl() );
        foodItem.setObjectSifr( foodItemDto.getObjectSifr() );
        foodItem.setFlags( foodItemDto.getFlags() );
        foodItem.setSapCode( foodItemDto.getSapCode() );
        foodItem.setCookMins( foodItemDto.getCookMins() );
        foodItem.setFoodGroupItems( foodGroupItemDtoListToFoodGroupItemList( foodItemDto.getFoodGroupItems() ) );

        return foodItem;
    }

    protected List<FoodItem> foodItemDtoListToFoodItemList(List<FoodItemDto> list) {
        if ( list == null ) {
            return null;
        }

        List<FoodItem> list1 = new ArrayList<FoodItem>( list.size() );
        for ( FoodItemDto foodItemDto : list ) {
            list1.add( foodItemDtoToFoodItem( foodItemDto ) );
        }

        return list1;
    }

    protected PrintGroup printGroupDtoToPrintGroup(PrintGroupDto printGroupDto) {
        if ( printGroupDto == null ) {
            return null;
        }

        PrintGroup printGroup = new PrintGroup();

        printGroup.setId( printGroupDto.getId() );
        printGroup.setRestaurantCode( printGroupDto.getRestaurantCode() );
        printGroup.setCode( printGroupDto.getCode() );
        printGroup.setKitchenType( printGroupDto.getKitchenType() );
        printGroup.setKds( kdsDtoToKds( printGroupDto.getKds() ) );
        printGroup.setFoodItems( foodItemDtoListToFoodItemList( printGroupDto.getFoodItems() ) );

        return printGroup;
    }

    protected List<PrintGroup> printGroupDtoListToPrintGroupList(List<PrintGroupDto> list) {
        if ( list == null ) {
            return null;
        }

        List<PrintGroup> list1 = new ArrayList<PrintGroup>( list.size() );
        for ( PrintGroupDto printGroupDto : list ) {
            list1.add( printGroupDtoToPrintGroup( printGroupDto ) );
        }

        return list1;
    }

    protected Kds kdsDtoToKds(KdsDto kdsDto) {
        if ( kdsDto == null ) {
            return null;
        }

        Kds kds = new Kds();

        kds.setId( kdsDto.getId() );
        kds.setRestaurantCode( kdsDto.getRestaurantCode() );
        kds.setName( kdsDto.getName() );
        kds.setStatus( kdsDto.isStatus() );
        kds.setCreatedBy( kdsDto.getCreatedBy() );
        kds.setCreatedDate( kdsDto.getCreatedDate() );
        kds.setModifiedBy( kdsDto.getModifiedBy() );
        kds.setModifiedDate( kdsDto.getModifiedDate() );
        kds.setPrintGroups( printGroupDtoListToPrintGroupList( kdsDto.getPrintGroups() ) );

        return kds;
    }

    protected Hallplan hallplanDtoToHallplan(HallplanDto hallplanDto) {
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

    protected HallplanDto hallplanToHallplanDto(Hallplan hallplan) {
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
