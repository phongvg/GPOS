package com.gg.gpos.menu.mapper;

import com.gg.gpos.menu.dto.FoodGroupDto;
import com.gg.gpos.menu.dto.FoodGroupItemDto;
import com.gg.gpos.menu.dto.FoodItemDto;
import com.gg.gpos.menu.dto.KdsDto;
import com.gg.gpos.menu.dto.MenuTypeDto;
import com.gg.gpos.menu.dto.PrintGroupDto;
import com.gg.gpos.menu.entity.FoodGroup;
import com.gg.gpos.menu.entity.FoodGroupItem;
import com.gg.gpos.menu.entity.FoodItem;
import com.gg.gpos.menu.entity.Kds;
import com.gg.gpos.menu.entity.MenuType;
import com.gg.gpos.menu.entity.PrintGroup;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-01T17:40:56+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"
)
@Component
public class PrintGroupMapperImpl implements PrintGroupMapper {

    @Override
    public PrintGroup dtoToEntity(PrintGroupDto printGroupDto) {
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

    @Override
    public PrintGroupDto entityToDto(PrintGroup printGroup) {
        if ( printGroup == null ) {
            return null;
        }

        PrintGroupDto printGroupDto = new PrintGroupDto();

        printGroupDto.setId( printGroup.getId() );
        printGroupDto.setRestaurantCode( printGroup.getRestaurantCode() );
        printGroupDto.setCode( printGroup.getCode() );
        printGroupDto.setKitchenType( printGroup.getKitchenType() );
        printGroupDto.setKds( kdsToKdsDto( printGroup.getKds() ) );
        printGroupDto.setFoodItems( foodItemListToFoodItemDtoList( printGroup.getFoodItems() ) );

        return printGroupDto;
    }

    @Override
    public KdsDto kdsToKdsDto(Kds kds) {
        if ( kds == null ) {
            return null;
        }

        KdsDto kdsDto = new KdsDto();

        kdsDto.setId( kds.getId() );
        kdsDto.setName( kds.getName() );
        kdsDto.setStatus( kds.isStatus() );
        kdsDto.setCreatedBy( kds.getCreatedBy() );
        kdsDto.setCreatedDate( kds.getCreatedDate() );
        kdsDto.setModifiedBy( kds.getModifiedBy() );
        kdsDto.setModifiedDate( kds.getModifiedDate() );
        kdsDto.setRestaurantCode( kds.getRestaurantCode() );

        kdsDto.setPrintGroups( null );

        return kdsDto;
    }

    @Override
    public FoodItemDto foodItemToFoodItemDto(FoodItem foodItem) {
        if ( foodItem == null ) {
            return null;
        }

        FoodItemDto foodItemDto = new FoodItemDto();

        foodItemDto.setId( foodItem.getId() );
        foodItemDto.setItemId( foodItem.getItemId() );
        foodItemDto.setParentId( foodItem.getParentId() );
        foodItemDto.setType( foodItem.getType() );
        foodItemDto.setCode( foodItem.getCode() );
        foodItemDto.setName( foodItem.getName() );
        foodItemDto.setUnsignedName( foodItem.getUnsignedName() );
        foodItemDto.setStatus( foodItem.getStatus() );
        foodItemDto.setModiScheme( foodItem.getModiScheme() );
        foodItemDto.setComboScheme( foodItem.getComboScheme() );
        foodItemDto.setComboJoinMode( foodItem.getComboJoinMode() );
        foodItemDto.setRightLvl( foodItem.getRightLvl() );
        foodItemDto.setObjectSifr( foodItem.getObjectSifr() );
        foodItemDto.setFlags( foodItem.getFlags() );
        foodItemDto.setSapCode( foodItem.getSapCode() );
        foodItemDto.setCookMins( foodItem.getCookMins() );

        foodItemDto.setFoodGroupItems( null );

        return foodItemDto;
    }

    protected List<PrintGroup> printGroupDtoListToPrintGroupList(List<PrintGroupDto> list) {
        if ( list == null ) {
            return null;
        }

        List<PrintGroup> list1 = new ArrayList<PrintGroup>( list.size() );
        for ( PrintGroupDto printGroupDto : list ) {
            list1.add( dtoToEntity( printGroupDto ) );
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

    protected List<FoodItemDto> foodItemListToFoodItemDtoList(List<FoodItem> list) {
        if ( list == null ) {
            return null;
        }

        List<FoodItemDto> list1 = new ArrayList<FoodItemDto>( list.size() );
        for ( FoodItem foodItem : list ) {
            list1.add( foodItemToFoodItemDto( foodItem ) );
        }

        return list1;
    }
}
