package com.gg.gpos.menu.mapper;

import com.gg.gpos.menu.dto.FoodGroupDto;
import com.gg.gpos.menu.dto.FoodGroupItemDto;
import com.gg.gpos.menu.dto.FoodItemDto;
import com.gg.gpos.menu.dto.MenuTypeDto;
import com.gg.gpos.menu.dto.OrderCategoryDto;
import com.gg.gpos.menu.dto.SoCategoryDto;
import com.gg.gpos.menu.dto.SoCategoryFoodGroupDto;
import com.gg.gpos.menu.dto.SoDto;
import com.gg.gpos.menu.entity.FoodGroup;
import com.gg.gpos.menu.entity.FoodGroupItem;
import com.gg.gpos.menu.entity.FoodItem;
import com.gg.gpos.menu.entity.MenuType;
import com.gg.gpos.menu.entity.OrderCategory;
import com.gg.gpos.menu.entity.So;
import com.gg.gpos.menu.entity.SoCategory;
import com.gg.gpos.menu.entity.SoCategoryFoodGroup;
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
public class SoCategoryFoodGroupMapperImpl implements SoCategoryFoodGroupMapper {

    @Override
    public SoCategoryFoodGroup dtoToEntity(SoCategoryFoodGroupDto soCategoryFoodGroupDto) {
        if ( soCategoryFoodGroupDto == null ) {
            return null;
        }

        SoCategoryFoodGroup soCategoryFoodGroup = new SoCategoryFoodGroup();

        soCategoryFoodGroup.setId( soCategoryFoodGroupDto.getId() );
        soCategoryFoodGroup.setRestaurantCode( soCategoryFoodGroupDto.getRestaurantCode() );
        soCategoryFoodGroup.setFoodGroupParentId( soCategoryFoodGroupDto.getFoodGroupParentId() );
        soCategoryFoodGroup.setGroupOrder( soCategoryFoodGroupDto.getGroupOrder() );
        soCategoryFoodGroup.setSoCategory( soCategoryDtoToSoCategory( soCategoryFoodGroupDto.getSoCategory() ) );
        soCategoryFoodGroup.setFoodGroup( foodGroupDtoToFoodGroup( soCategoryFoodGroupDto.getFoodGroup() ) );

        return soCategoryFoodGroup;
    }

    @Override
    public SoCategoryFoodGroupDto entityToDto(SoCategoryFoodGroup soCategoryFoodGroup) {
        if ( soCategoryFoodGroup == null ) {
            return null;
        }

        SoCategoryFoodGroupDto soCategoryFoodGroupDto = new SoCategoryFoodGroupDto();

        soCategoryFoodGroupDto.setId( soCategoryFoodGroup.getId() );
        soCategoryFoodGroupDto.setRestaurantCode( soCategoryFoodGroup.getRestaurantCode() );
        soCategoryFoodGroupDto.setFoodGroupParentId( soCategoryFoodGroup.getFoodGroupParentId() );
        soCategoryFoodGroupDto.setGroupOrder( soCategoryFoodGroup.getGroupOrder() );
        soCategoryFoodGroupDto.setSoCategory( soCategoryToSoCategoryDto( soCategoryFoodGroup.getSoCategory() ) );
        soCategoryFoodGroupDto.setFoodGroup( foodGroupToFoodGroupDto( soCategoryFoodGroup.getFoodGroup() ) );

        return soCategoryFoodGroupDto;
    }

    @Override
    public SoCategoryDto soCategoryToSoCategoryDto(SoCategory soCategory) {
        if ( soCategory == null ) {
            return null;
        }

        SoCategoryDto soCategoryDto = new SoCategoryDto();

        soCategoryDto.setId( soCategory.getId() );
        soCategoryDto.setRestaurantCode( soCategory.getRestaurantCode() );
        soCategoryDto.setOrderCategoryParentId( soCategory.getOrderCategoryParentId() );
        soCategoryDto.setAdultBufferTicket( soCategory.getAdultBufferTicket() );
        soCategoryDto.setKidBufferTicket( soCategory.getKidBufferTicket() );
        soCategoryDto.setType( soCategory.getType() );
        soCategoryDto.setStatus( soCategory.isStatus() );

        soCategoryDto.setSoCategoryFoodGroups( null );
        soCategoryDto.setSo( null );
        soCategoryDto.setOrderCategory( null );

        return soCategoryDto;
    }

    @Override
    public FoodGroupDto foodGroupToFoodGroupDto(FoodGroup foodGroup) {
        if ( foodGroup == null ) {
            return null;
        }

        FoodGroupDto foodGroupDto = new FoodGroupDto();

        foodGroupDto.setId( foodGroup.getId() );
        foodGroupDto.setRestaurantCode( foodGroup.getRestaurantCode() );
        foodGroupDto.setType( foodGroup.getType() );
        foodGroupDto.setCode( foodGroup.getCode() );
        foodGroupDto.setNameEn( foodGroup.getNameEn() );
        foodGroupDto.setNameVn( foodGroup.getNameVn() );
        foodGroupDto.setStatus( foodGroup.isStatus() );
        foodGroupDto.setCreatedBy( foodGroup.getCreatedBy() );
        foodGroupDto.setCreatedDate( foodGroup.getCreatedDate() );
        foodGroupDto.setModifiedBy( foodGroup.getModifiedBy() );
        foodGroupDto.setModifiedDate( foodGroup.getModifiedDate() );
        foodGroupDto.setLevel( foodGroup.getLevel() );
        foodGroupDto.setParent( foodGroupToFoodGroupDto( foodGroup.getParent() ) );
        foodGroupDto.setSrcImage( foodGroup.getSrcImage() );
        foodGroupDto.setChanged( foodGroup.getChanged() );
        foodGroupDto.setUnsignedName( foodGroup.getUnsignedName() );

        foodGroupDto.setFoodGroupItems( null );
        foodGroupDto.setMenuType( null );
        foodGroupDto.setFoodItems( null );

        return foodGroupDto;
    }

    protected OrderCategory orderCategoryDtoToOrderCategory(OrderCategoryDto orderCategoryDto) {
        if ( orderCategoryDto == null ) {
            return null;
        }

        OrderCategory orderCategory = new OrderCategory();

        orderCategory.setId( orderCategoryDto.getId() );
        orderCategory.setItemId( orderCategoryDto.getItemId() );
        orderCategory.setParentId( orderCategoryDto.getParentId() );
        orderCategory.setCode( orderCategoryDto.getCode() );
        orderCategory.setName( orderCategoryDto.getName() );
        orderCategory.setStatus( orderCategoryDto.getStatus() );
        orderCategory.setRightLvl( orderCategoryDto.getRightLvl() );
        orderCategory.setObjectSifr( orderCategoryDto.getObjectSifr() );
        orderCategory.setFlags( orderCategoryDto.getFlags() );

        return orderCategory;
    }

    protected List<SoCategory> soCategoryDtoListToSoCategoryList(List<SoCategoryDto> list) {
        if ( list == null ) {
            return null;
        }

        List<SoCategory> list1 = new ArrayList<SoCategory>( list.size() );
        for ( SoCategoryDto soCategoryDto : list ) {
            list1.add( soCategoryDtoToSoCategory( soCategoryDto ) );
        }

        return list1;
    }

    protected So soDtoToSo(SoDto soDto) {
        if ( soDto == null ) {
            return null;
        }

        So so = new So();

        so.setId( soDto.getId() );
        so.setName( soDto.getName() );
        so.setStatus( soDto.isStatus() );
        so.setCreatedBy( soDto.getCreatedBy() );
        so.setCreatedDate( soDto.getCreatedDate() );
        so.setModifiedBy( soDto.getModifiedBy() );
        so.setModifiedDate( soDto.getModifiedDate() );
        so.setSoCategories( soCategoryDtoListToSoCategoryList( soDto.getSoCategories() ) );

        return so;
    }

    protected List<SoCategoryFoodGroup> soCategoryFoodGroupDtoListToSoCategoryFoodGroupList(List<SoCategoryFoodGroupDto> list) {
        if ( list == null ) {
            return null;
        }

        List<SoCategoryFoodGroup> list1 = new ArrayList<SoCategoryFoodGroup>( list.size() );
        for ( SoCategoryFoodGroupDto soCategoryFoodGroupDto : list ) {
            list1.add( dtoToEntity( soCategoryFoodGroupDto ) );
        }

        return list1;
    }

    protected SoCategory soCategoryDtoToSoCategory(SoCategoryDto soCategoryDto) {
        if ( soCategoryDto == null ) {
            return null;
        }

        SoCategory soCategory = new SoCategory();

        soCategory.setId( soCategoryDto.getId() );
        soCategory.setRestaurantCode( soCategoryDto.getRestaurantCode() );
        soCategory.setOrderCategoryParentId( soCategoryDto.getOrderCategoryParentId() );
        soCategory.setAdultBufferTicket( soCategoryDto.getAdultBufferTicket() );
        soCategory.setKidBufferTicket( soCategoryDto.getKidBufferTicket() );
        soCategory.setType( soCategoryDto.getType() );
        soCategory.setStatus( soCategoryDto.isStatus() );
        soCategory.setOrderCategory( orderCategoryDtoToOrderCategory( soCategoryDto.getOrderCategory() ) );
        soCategory.setSo( soDtoToSo( soCategoryDto.getSo() ) );
        soCategory.setSoCategoryFoodGroups( soCategoryFoodGroupDtoListToSoCategoryFoodGroupList( soCategoryDto.getSoCategoryFoodGroups() ) );

        return soCategory;
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
}
