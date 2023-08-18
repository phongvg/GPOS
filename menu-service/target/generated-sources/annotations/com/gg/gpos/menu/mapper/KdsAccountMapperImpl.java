package com.gg.gpos.menu.mapper;

import com.gg.gpos.menu.dto.FoodGroupDto;
import com.gg.gpos.menu.dto.FoodGroupItemDto;
import com.gg.gpos.menu.dto.FoodItemDto;
import com.gg.gpos.menu.dto.HallplanDto;
import com.gg.gpos.menu.dto.KdsAccountDto;
import com.gg.gpos.menu.dto.KdsConfigCookingDto;
import com.gg.gpos.menu.dto.KdsPlaceDto;
import com.gg.gpos.menu.dto.MenuTypeDto;
import com.gg.gpos.menu.entity.FoodGroup;
import com.gg.gpos.menu.entity.FoodGroupItem;
import com.gg.gpos.menu.entity.FoodItem;
import com.gg.gpos.menu.entity.Hallplan;
import com.gg.gpos.menu.entity.KdsAccount;
import com.gg.gpos.menu.entity.KdsConfigCooking;
import com.gg.gpos.menu.entity.KdsPlace;
import com.gg.gpos.menu.entity.MenuType;
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
public class KdsAccountMapperImpl implements KdsAccountMapper {

    @Override
    public KdsAccount dtoToEntity(KdsAccountDto kdsAccountDto) {
        if ( kdsAccountDto == null ) {
            return null;
        }

        KdsAccount kdsAccount = new KdsAccount();

        kdsAccount.setId( kdsAccountDto.getId() );
        kdsAccount.setRestaurantCode( kdsAccountDto.getRestaurantCode() );
        kdsAccount.setAccountName( kdsAccountDto.getAccountName() );
        kdsAccount.setPassword( kdsAccountDto.getPassword() );
        kdsAccount.setRole( kdsAccountDto.getRole() );
        kdsAccount.setKdsPlaces( kdsPlaceDtoListToKdsPlaceList( kdsAccountDto.getKdsPlaces() ) );
        kdsAccount.setParent( dtoToEntity( kdsAccountDto.getParent() ) );

        return kdsAccount;
    }

    @Override
    public KdsAccountDto entityToDto(KdsAccount kdsAccount) {
        if ( kdsAccount == null ) {
            return null;
        }

        KdsAccountDto kdsAccountDto = new KdsAccountDto();

        kdsAccountDto.setId( kdsAccount.getId() );
        kdsAccountDto.setRestaurantCode( kdsAccount.getRestaurantCode() );
        kdsAccountDto.setAccountName( kdsAccount.getAccountName() );
        kdsAccountDto.setPassword( kdsAccount.getPassword() );
        kdsAccountDto.setParent( entityToDto( kdsAccount.getParent() ) );
        kdsAccountDto.setRole( kdsAccount.getRole() );
        kdsAccountDto.setKdsPlaces( kdsPlaceListToKdsPlaceDtoList( kdsAccount.getKdsPlaces() ) );

        return kdsAccountDto;
    }

    @Override
    public KdsPlaceDto kdsPlaceToKdsPlaceDto(KdsPlace kdsPlace) {
        if ( kdsPlace == null ) {
            return null;
        }

        KdsPlaceDto kdsPlaceDto = new KdsPlaceDto();

        kdsPlaceDto.setId( kdsPlace.getId() );
        kdsPlaceDto.setRestaurantCode( kdsPlace.getRestaurantCode() );
        kdsPlaceDto.setCode( kdsPlace.getCode() );
        kdsPlaceDto.setName( kdsPlace.getName() );
        kdsPlaceDto.setType( kdsPlace.getType() );
        kdsPlaceDto.setPrinter( kdsPlace.getPrinter() );
        kdsPlaceDto.setHallplans( hallplanListToHallplanDtoList( kdsPlace.getHallplans() ) );

        kdsPlaceDto.setKdsConfigCookings( null );

        return kdsPlaceDto;
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

    protected KdsConfigCooking kdsConfigCookingDtoToKdsConfigCooking(KdsConfigCookingDto kdsConfigCookingDto) {
        if ( kdsConfigCookingDto == null ) {
            return null;
        }

        KdsConfigCooking kdsConfigCooking = new KdsConfigCooking();

        kdsConfigCooking.setId( kdsConfigCookingDto.getId() );
        kdsConfigCooking.setRestaurantCode( kdsConfigCookingDto.getRestaurantCode() );
        kdsConfigCooking.setSingleCookingTime( kdsConfigCookingDto.getSingleCookingTime() );
        kdsConfigCooking.setMultiCookingTime( kdsConfigCookingDto.getMultiCookingTime() );
        kdsConfigCooking.setFasting( kdsConfigCookingDto.getFasting() );
        kdsConfigCooking.setGroupTypeKdsCode( kdsConfigCookingDto.getGroupTypeKdsCode() );
        kdsConfigCooking.setGroupTypeKdsName( kdsConfigCookingDto.getGroupTypeKdsName() );
        kdsConfigCooking.setKdsPlace( kdsPlaceDtoToKdsPlace( kdsConfigCookingDto.getKdsPlace() ) );
        kdsConfigCooking.setFoodItem( foodItemDtoToFoodItem( kdsConfigCookingDto.getFoodItem() ) );

        return kdsConfigCooking;
    }

    protected List<KdsConfigCooking> kdsConfigCookingDtoListToKdsConfigCookingList(List<KdsConfigCookingDto> list) {
        if ( list == null ) {
            return null;
        }

        List<KdsConfigCooking> list1 = new ArrayList<KdsConfigCooking>( list.size() );
        for ( KdsConfigCookingDto kdsConfigCookingDto : list ) {
            list1.add( kdsConfigCookingDtoToKdsConfigCooking( kdsConfigCookingDto ) );
        }

        return list1;
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

    protected List<Hallplan> hallplanDtoListToHallplanList(List<HallplanDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Hallplan> list1 = new ArrayList<Hallplan>( list.size() );
        for ( HallplanDto hallplanDto : list ) {
            list1.add( hallplanDtoToHallplan( hallplanDto ) );
        }

        return list1;
    }

    protected KdsPlace kdsPlaceDtoToKdsPlace(KdsPlaceDto kdsPlaceDto) {
        if ( kdsPlaceDto == null ) {
            return null;
        }

        KdsPlace kdsPlace = new KdsPlace();

        kdsPlace.setId( kdsPlaceDto.getId() );
        kdsPlace.setRestaurantCode( kdsPlaceDto.getRestaurantCode() );
        kdsPlace.setCode( kdsPlaceDto.getCode() );
        kdsPlace.setName( kdsPlaceDto.getName() );
        kdsPlace.setType( kdsPlaceDto.getType() );
        kdsPlace.setPrinter( kdsPlaceDto.getPrinter() );
        kdsPlace.setKdsConfigCookings( kdsConfigCookingDtoListToKdsConfigCookingList( kdsPlaceDto.getKdsConfigCookings() ) );
        kdsPlace.setHallplans( hallplanDtoListToHallplanList( kdsPlaceDto.getHallplans() ) );

        return kdsPlace;
    }

    protected List<KdsPlace> kdsPlaceDtoListToKdsPlaceList(List<KdsPlaceDto> list) {
        if ( list == null ) {
            return null;
        }

        List<KdsPlace> list1 = new ArrayList<KdsPlace>( list.size() );
        for ( KdsPlaceDto kdsPlaceDto : list ) {
            list1.add( kdsPlaceDtoToKdsPlace( kdsPlaceDto ) );
        }

        return list1;
    }

    protected List<KdsPlaceDto> kdsPlaceListToKdsPlaceDtoList(List<KdsPlace> list) {
        if ( list == null ) {
            return null;
        }

        List<KdsPlaceDto> list1 = new ArrayList<KdsPlaceDto>( list.size() );
        for ( KdsPlace kdsPlace : list ) {
            list1.add( kdsPlaceToKdsPlaceDto( kdsPlace ) );
        }

        return list1;
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

    protected List<HallplanDto> hallplanListToHallplanDtoList(List<Hallplan> list) {
        if ( list == null ) {
            return null;
        }

        List<HallplanDto> list1 = new ArrayList<HallplanDto>( list.size() );
        for ( Hallplan hallplan : list ) {
            list1.add( hallplanToHallplanDto( hallplan ) );
        }

        return list1;
    }
}
