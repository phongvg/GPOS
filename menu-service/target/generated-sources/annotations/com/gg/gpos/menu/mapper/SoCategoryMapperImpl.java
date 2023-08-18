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
public class SoCategoryMapperImpl implements SoCategoryMapper {

    @Override
    public SoCategory dtoToEntity(SoCategoryDto soCategoryDto) {
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

    @Override
    public SoCategoryDto entityToDto(SoCategory soCategory) {
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
        soCategoryDto.setOrderCategory( orderCategoryToOrderCategoryDto( soCategory.getOrderCategory() ) );
        soCategoryDto.setSo( soToSoDto( soCategory.getSo() ) );
        soCategoryDto.setSoCategoryFoodGroups( soCategoryFoodGroupListToSoCategoryFoodGroupDtoList( soCategory.getSoCategoryFoodGroups() ) );

        return soCategoryDto;
    }

    @Override
    public SoDto soToSoDto(So so) {
        if ( so == null ) {
            return null;
        }

        SoDto soDto = new SoDto();

        soDto.setId( so.getId() );
        soDto.setName( so.getName() );
        soDto.setStatus( so.isStatus() );
        soDto.setCreatedBy( so.getCreatedBy() );
        soDto.setCreatedDate( so.getCreatedDate() );
        soDto.setModifiedBy( so.getModifiedBy() );
        soDto.setModifiedDate( so.getModifiedDate() );

        soDto.setSoCategories( null );

        return soDto;
    }

    @Override
    public SoCategoryFoodGroupDto soCategoryFoodGroupToSoCategoryFoodGroupDto(SoCategoryFoodGroup soCategoryFoodGroup) {
        if ( soCategoryFoodGroup == null ) {
            return null;
        }

        SoCategoryFoodGroupDto soCategoryFoodGroupDto = new SoCategoryFoodGroupDto();

        soCategoryFoodGroupDto.setId( soCategoryFoodGroup.getId() );
        soCategoryFoodGroupDto.setRestaurantCode( soCategoryFoodGroup.getRestaurantCode() );
        soCategoryFoodGroupDto.setFoodGroupParentId( soCategoryFoodGroup.getFoodGroupParentId() );
        soCategoryFoodGroupDto.setGroupOrder( soCategoryFoodGroup.getGroupOrder() );

        soCategoryFoodGroupDto.setFoodGroup( null );
        soCategoryFoodGroupDto.setSoCategory( null );

        return soCategoryFoodGroupDto;
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
            list1.add( dtoToEntity( soCategoryDto ) );
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

    protected SoCategoryFoodGroup soCategoryFoodGroupDtoToSoCategoryFoodGroup(SoCategoryFoodGroupDto soCategoryFoodGroupDto) {
        if ( soCategoryFoodGroupDto == null ) {
            return null;
        }

        SoCategoryFoodGroup soCategoryFoodGroup = new SoCategoryFoodGroup();

        soCategoryFoodGroup.setId( soCategoryFoodGroupDto.getId() );
        soCategoryFoodGroup.setRestaurantCode( soCategoryFoodGroupDto.getRestaurantCode() );
        soCategoryFoodGroup.setFoodGroupParentId( soCategoryFoodGroupDto.getFoodGroupParentId() );
        soCategoryFoodGroup.setGroupOrder( soCategoryFoodGroupDto.getGroupOrder() );
        soCategoryFoodGroup.setSoCategory( dtoToEntity( soCategoryFoodGroupDto.getSoCategory() ) );
        soCategoryFoodGroup.setFoodGroup( foodGroupDtoToFoodGroup( soCategoryFoodGroupDto.getFoodGroup() ) );

        return soCategoryFoodGroup;
    }

    protected List<SoCategoryFoodGroup> soCategoryFoodGroupDtoListToSoCategoryFoodGroupList(List<SoCategoryFoodGroupDto> list) {
        if ( list == null ) {
            return null;
        }

        List<SoCategoryFoodGroup> list1 = new ArrayList<SoCategoryFoodGroup>( list.size() );
        for ( SoCategoryFoodGroupDto soCategoryFoodGroupDto : list ) {
            list1.add( soCategoryFoodGroupDtoToSoCategoryFoodGroup( soCategoryFoodGroupDto ) );
        }

        return list1;
    }

    protected OrderCategoryDto orderCategoryToOrderCategoryDto(OrderCategory orderCategory) {
        if ( orderCategory == null ) {
            return null;
        }

        OrderCategoryDto orderCategoryDto = new OrderCategoryDto();

        orderCategoryDto.setId( orderCategory.getId() );
        orderCategoryDto.setItemId( orderCategory.getItemId() );
        orderCategoryDto.setParentId( orderCategory.getParentId() );
        orderCategoryDto.setCode( orderCategory.getCode() );
        orderCategoryDto.setName( orderCategory.getName() );
        orderCategoryDto.setStatus( orderCategory.getStatus() );
        orderCategoryDto.setRightLvl( orderCategory.getRightLvl() );
        orderCategoryDto.setObjectSifr( orderCategory.getObjectSifr() );
        orderCategoryDto.setFlags( orderCategory.getFlags() );

        return orderCategoryDto;
    }

    protected List<SoCategoryFoodGroupDto> soCategoryFoodGroupListToSoCategoryFoodGroupDtoList(List<SoCategoryFoodGroup> list) {
        if ( list == null ) {
            return null;
        }

        List<SoCategoryFoodGroupDto> list1 = new ArrayList<SoCategoryFoodGroupDto>( list.size() );
        for ( SoCategoryFoodGroup soCategoryFoodGroup : list ) {
            list1.add( soCategoryFoodGroupToSoCategoryFoodGroupDto( soCategoryFoodGroup ) );
        }

        return list1;
    }
}
