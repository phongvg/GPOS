package com.gg.gpos.menu.mapper;

import com.gg.gpos.menu.dto.CoCategoryDto;
import com.gg.gpos.menu.dto.CoFoodGroupDisplayDto;
import com.gg.gpos.menu.dto.DigitalMenuDto;
import com.gg.gpos.menu.dto.FoodGroupDto;
import com.gg.gpos.menu.dto.FoodGroupItemDto;
import com.gg.gpos.menu.dto.FoodItemDto;
import com.gg.gpos.menu.dto.MenuTypeDto;
import com.gg.gpos.menu.dto.OrderCategoryDto;
import com.gg.gpos.menu.dto.SoCategoryDto;
import com.gg.gpos.menu.dto.SoCategoryFoodGroupDto;
import com.gg.gpos.menu.dto.SoDto;
import com.gg.gpos.menu.entity.CoCategory;
import com.gg.gpos.menu.entity.CoFoodGroupDisplay;
import com.gg.gpos.menu.entity.DigitalMenu;
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
    date = "2023-08-01T17:40:56+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"
)
@Component
public class CoCategoryMapperImpl implements CoCategoryMapper {

    @Override
    public CoCategory dtoToEntity(CoCategoryDto coCategoryDto) {
        if ( coCategoryDto == null ) {
            return null;
        }

        CoCategory coCategory = new CoCategory();

        coCategory.setId( coCategoryDto.getId() );
        coCategory.setCoId( coCategoryDto.getCoId() );
        coCategory.setRestaurantCode( coCategoryDto.getRestaurantCode() );
        coCategory.setNameVn( coCategoryDto.getNameVn() );
        coCategory.setNameEn( coCategoryDto.getNameEn() );
        coCategory.setDescEn( coCategoryDto.getDescEn() );
        coCategory.setDescVn( coCategoryDto.getDescVn() );
        coCategory.setDescJp( coCategoryDto.getDescJp() );
        coCategory.setDescKr( coCategoryDto.getDescKr() );
        coCategory.setDescCn( coCategoryDto.getDescCn() );
        coCategory.setType( coCategoryDto.getType() );
        coCategory.setSrcImg( coCategoryDto.getSrcImg() );
        coCategory.setSrcImgIntros( coCategoryDto.getSrcImgIntros() );
        coCategory.setAlacarteLabel( coCategoryDto.getAlacarteLabel() );
        coCategory.setBuffetLabel( coCategoryDto.getBuffetLabel() );
        coCategory.setDrinksLabel( coCategoryDto.getDrinksLabel() );
        coCategory.setAlacarteLabelEn( coCategoryDto.getAlacarteLabelEn() );
        coCategory.setBuffetLabelEn( coCategoryDto.getBuffetLabelEn() );
        coCategory.setDrinksLabelEn( coCategoryDto.getDrinksLabelEn() );
        coCategory.setPhotoDisplayPosition( coCategoryDto.getPhotoDisplayPosition() );
        coCategory.setAverageAmount( coCategoryDto.getAverageAmount() );
        coCategory.setNote( coCategoryDto.getNote() );
        coCategory.setStatus( coCategoryDto.isStatus() );
        coCategory.setPositionNumber( coCategoryDto.getPositionNumber() );
        coCategory.setSoCategory( soCategoryDtoToSoCategory( coCategoryDto.getSoCategory() ) );
        coCategory.setCoFoodGroupDisplays( coFoodGroupDisplayDtoListToCoFoodGroupDisplayList( coCategoryDto.getCoFoodGroupDisplays() ) );
        coCategory.setDigitalMenus( digitalMenuDtoListToDigitalMenuList( coCategoryDto.getDigitalMenus() ) );

        return coCategory;
    }

    @Override
    public CoCategoryDto entityToDto(CoCategory coCategory) {
        if ( coCategory == null ) {
            return null;
        }

        CoCategoryDto coCategoryDto = new CoCategoryDto();

        coCategoryDto.setId( coCategory.getId() );
        coCategoryDto.setCoId( coCategory.getCoId() );
        coCategoryDto.setRestaurantCode( coCategory.getRestaurantCode() );
        coCategoryDto.setNameVn( coCategory.getNameVn() );
        coCategoryDto.setNameEn( coCategory.getNameEn() );
        coCategoryDto.setDescEn( coCategory.getDescEn() );
        coCategoryDto.setDescVn( coCategory.getDescVn() );
        coCategoryDto.setDescJp( coCategory.getDescJp() );
        coCategoryDto.setDescKr( coCategory.getDescKr() );
        coCategoryDto.setDescCn( coCategory.getDescCn() );
        coCategoryDto.setType( coCategory.getType() );
        coCategoryDto.setSrcImg( coCategory.getSrcImg() );
        coCategoryDto.setSrcImgIntros( coCategory.getSrcImgIntros() );
        coCategoryDto.setAlacarteLabel( coCategory.getAlacarteLabel() );
        coCategoryDto.setBuffetLabel( coCategory.getBuffetLabel() );
        coCategoryDto.setDrinksLabel( coCategory.getDrinksLabel() );
        coCategoryDto.setAlacarteLabelEn( coCategory.getAlacarteLabelEn() );
        coCategoryDto.setBuffetLabelEn( coCategory.getBuffetLabelEn() );
        coCategoryDto.setDrinksLabelEn( coCategory.getDrinksLabelEn() );
        coCategoryDto.setPhotoDisplayPosition( coCategory.getPhotoDisplayPosition() );
        coCategoryDto.setAverageAmount( coCategory.getAverageAmount() );
        coCategoryDto.setNote( coCategory.getNote() );
        coCategoryDto.setPositionNumber( coCategory.getPositionNumber() );
        coCategoryDto.setStatus( coCategory.isStatus() );
        coCategoryDto.setCoFoodGroupDisplays( coFoodGroupDisplayListToCoFoodGroupDisplayDtoList( coCategory.getCoFoodGroupDisplays() ) );
        coCategoryDto.setSoCategory( soCategoryToSoCategoryDto( coCategory.getSoCategory() ) );
        coCategoryDto.setDigitalMenus( digitalMenuListToDigitalMenuDtoList( coCategory.getDigitalMenus() ) );

        return coCategoryDto;
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
        soCategoryDto.setOrderCategory( orderCategoryToOrderCategoryDto( soCategory.getOrderCategory() ) );

        soCategoryDto.setSo( null );
        soCategoryDto.setSoCategoryFoodGroups( null );

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
    public CoFoodGroupDisplayDto coFoodGroupDisplayToCoFoodGroupDisplayDto(CoFoodGroupDisplay coFoodGroupDisplay) {
        if ( coFoodGroupDisplay == null ) {
            return null;
        }

        CoFoodGroupDisplayDto coFoodGroupDisplayDto = new CoFoodGroupDisplayDto();

        coFoodGroupDisplayDto.setId( coFoodGroupDisplay.getId() );
        coFoodGroupDisplayDto.setFoodGroupCode( coFoodGroupDisplay.getFoodGroupCode() );

        coFoodGroupDisplayDto.setCoCategory( null );

        return coFoodGroupDisplayDto;
    }

    @Override
    public DigitalMenuDto digitalMenuToDigitalMenuDto(DigitalMenu digitalMenu) {
        if ( digitalMenu == null ) {
            return null;
        }

        DigitalMenuDto digitalMenuDto = new DigitalMenuDto();

        digitalMenuDto.setId( digitalMenu.getId() );
        digitalMenuDto.setSrcImg( digitalMenu.getSrcImg() );
        digitalMenuDto.setUrlImg( digitalMenu.getUrlImg() );
        digitalMenuDto.setOrderNumber( digitalMenu.getOrderNumber() );
        digitalMenuDto.setName( digitalMenu.getName() );
        digitalMenuDto.setUrl( digitalMenu.getUrl() );

        digitalMenuDto.setCoCategory( null );

        return digitalMenuDto;
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
        soCategoryFoodGroup.setSoCategory( soCategoryDtoToSoCategory( soCategoryFoodGroupDto.getSoCategory() ) );
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

    protected CoFoodGroupDisplay coFoodGroupDisplayDtoToCoFoodGroupDisplay(CoFoodGroupDisplayDto coFoodGroupDisplayDto) {
        if ( coFoodGroupDisplayDto == null ) {
            return null;
        }

        CoFoodGroupDisplay coFoodGroupDisplay = new CoFoodGroupDisplay();

        coFoodGroupDisplay.setId( coFoodGroupDisplayDto.getId() );
        coFoodGroupDisplay.setFoodGroupCode( coFoodGroupDisplayDto.getFoodGroupCode() );
        coFoodGroupDisplay.setCoCategory( dtoToEntity( coFoodGroupDisplayDto.getCoCategory() ) );

        return coFoodGroupDisplay;
    }

    protected List<CoFoodGroupDisplay> coFoodGroupDisplayDtoListToCoFoodGroupDisplayList(List<CoFoodGroupDisplayDto> list) {
        if ( list == null ) {
            return null;
        }

        List<CoFoodGroupDisplay> list1 = new ArrayList<CoFoodGroupDisplay>( list.size() );
        for ( CoFoodGroupDisplayDto coFoodGroupDisplayDto : list ) {
            list1.add( coFoodGroupDisplayDtoToCoFoodGroupDisplay( coFoodGroupDisplayDto ) );
        }

        return list1;
    }

    protected DigitalMenu digitalMenuDtoToDigitalMenu(DigitalMenuDto digitalMenuDto) {
        if ( digitalMenuDto == null ) {
            return null;
        }

        DigitalMenu digitalMenu = new DigitalMenu();

        digitalMenu.setId( digitalMenuDto.getId() );
        digitalMenu.setCoCategory( dtoToEntity( digitalMenuDto.getCoCategory() ) );
        digitalMenu.setSrcImg( digitalMenuDto.getSrcImg() );
        digitalMenu.setUrlImg( digitalMenuDto.getUrlImg() );
        digitalMenu.setOrderNumber( digitalMenuDto.getOrderNumber() );
        digitalMenu.setName( digitalMenuDto.getName() );
        digitalMenu.setUrl( digitalMenuDto.getUrl() );

        return digitalMenu;
    }

    protected List<DigitalMenu> digitalMenuDtoListToDigitalMenuList(List<DigitalMenuDto> list) {
        if ( list == null ) {
            return null;
        }

        List<DigitalMenu> list1 = new ArrayList<DigitalMenu>( list.size() );
        for ( DigitalMenuDto digitalMenuDto : list ) {
            list1.add( digitalMenuDtoToDigitalMenu( digitalMenuDto ) );
        }

        return list1;
    }

    protected List<CoFoodGroupDisplayDto> coFoodGroupDisplayListToCoFoodGroupDisplayDtoList(List<CoFoodGroupDisplay> list) {
        if ( list == null ) {
            return null;
        }

        List<CoFoodGroupDisplayDto> list1 = new ArrayList<CoFoodGroupDisplayDto>( list.size() );
        for ( CoFoodGroupDisplay coFoodGroupDisplay : list ) {
            list1.add( coFoodGroupDisplayToCoFoodGroupDisplayDto( coFoodGroupDisplay ) );
        }

        return list1;
    }

    protected List<DigitalMenuDto> digitalMenuListToDigitalMenuDtoList(List<DigitalMenu> list) {
        if ( list == null ) {
            return null;
        }

        List<DigitalMenuDto> list1 = new ArrayList<DigitalMenuDto>( list.size() );
        for ( DigitalMenu digitalMenu : list ) {
            list1.add( digitalMenuToDigitalMenuDto( digitalMenu ) );
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
}
