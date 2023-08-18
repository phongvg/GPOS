package com.gg.gpos.menu.mapper;

import com.gg.gpos.menu.dto.CoDto;
import com.gg.gpos.menu.dto.CoFoodItemDto;
import com.gg.gpos.menu.dto.CofoodItemModifierDto;
import com.gg.gpos.menu.dto.FeatureDto;
import com.gg.gpos.menu.dto.FoodGroupDto;
import com.gg.gpos.menu.dto.FoodGroupItemDto;
import com.gg.gpos.menu.dto.FoodItemDto;
import com.gg.gpos.menu.dto.MenuTypeDto;
import com.gg.gpos.menu.dto.ModifierDto;
import com.gg.gpos.menu.dto.RelatedFoodItemDto;
import com.gg.gpos.menu.dto.ToppingFoodItemDto;
import com.gg.gpos.menu.entity.Co;
import com.gg.gpos.menu.entity.CoFoodItem;
import com.gg.gpos.menu.entity.CoFoodItemModifier;
import com.gg.gpos.menu.entity.Feature;
import com.gg.gpos.menu.entity.FoodGroup;
import com.gg.gpos.menu.entity.FoodGroupItem;
import com.gg.gpos.menu.entity.FoodItem;
import com.gg.gpos.menu.entity.MenuType;
import com.gg.gpos.menu.entity.Modifier;
import com.gg.gpos.menu.entity.RelatedFoodItem;
import com.gg.gpos.menu.entity.ToppingFoodItem;
import java.math.BigInteger;
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
public class CoFoodItemModifierMapperImpl implements CoFoodItemModifierMapper {

    @Override
    public CoFoodItemModifier dtoToEntity(CofoodItemModifierDto cofoodItemModifierDto) {
        if ( cofoodItemModifierDto == null ) {
            return null;
        }

        CoFoodItemModifier coFoodItemModifier = new CoFoodItemModifier();

        coFoodItemModifier.setId( cofoodItemModifierDto.getId() );
        coFoodItemModifier.setPrice( cofoodItemModifierDto.getPrice() );
        if ( cofoodItemModifierDto.getQuantity() != null ) {
            coFoodItemModifier.setQuantity( cofoodItemModifierDto.getQuantity().longValue() );
        }
        coFoodItemModifier.setType( cofoodItemModifierDto.getType() );
        coFoodItemModifier.setNumberOfChili( cofoodItemModifierDto.getNumberOfChili() );
        coFoodItemModifier.setCoFoodItem( coFoodItemDtoToCoFoodItem( cofoodItemModifierDto.getCoFoodItem() ) );
        coFoodItemModifier.setModifier( modifierDtoToModifier( cofoodItemModifierDto.getModifier() ) );

        return coFoodItemModifier;
    }

    @Override
    public CofoodItemModifierDto entityToDto(CoFoodItemModifier coFoodItemModifier) {
        if ( coFoodItemModifier == null ) {
            return null;
        }

        CofoodItemModifierDto cofoodItemModifierDto = new CofoodItemModifierDto();

        cofoodItemModifierDto.setId( coFoodItemModifier.getId() );
        cofoodItemModifierDto.setPrice( coFoodItemModifier.getPrice() );
        if ( coFoodItemModifier.getQuantity() != null ) {
            cofoodItemModifierDto.setQuantity( coFoodItemModifier.getQuantity().intValue() );
        }
        cofoodItemModifierDto.setNumberOfChili( coFoodItemModifier.getNumberOfChili() );
        cofoodItemModifierDto.setType( coFoodItemModifier.getType() );
        cofoodItemModifierDto.setCoFoodItem( coFoodItemToCoFoodItemDto( coFoodItemModifier.getCoFoodItem() ) );
        cofoodItemModifierDto.setModifier( modifierToModifierDto( coFoodItemModifier.getModifier() ) );

        return cofoodItemModifierDto;
    }

    @Override
    public CoFoodItemDto coFoodItemToCoFoodItemDto(CoFoodItem coFoodItem) {
        if ( coFoodItem == null ) {
            return null;
        }

        CoFoodItemDto coFoodItemDto = new CoFoodItemDto();

        coFoodItemDto.setId( coFoodItem.getId() );
        coFoodItemDto.setRestaurantCode( coFoodItem.getRestaurantCode() );
        coFoodItemDto.setMaxPerTransaction( coFoodItem.getMaxPerTransaction() );
        coFoodItemDto.setMaxForKitchen( coFoodItem.getMaxForKitchen() );
        coFoodItemDto.setKalo( coFoodItem.getKalo() );
        coFoodItemDto.setSoId( coFoodItem.getSoId() );
        coFoodItemDto.setNameEn( coFoodItem.getNameEn() );
        coFoodItemDto.setDescVn( coFoodItem.getDescVn() );
        coFoodItemDto.setDescEn( coFoodItem.getDescEn() );
        if ( coFoodItem.getPrice() != null ) {
            coFoodItemDto.setPrice( coFoodItem.getPrice().longValue() );
        }
        coFoodItemDto.setCombo( coFoodItem.isCombo() );
        coFoodItemDto.setCode( coFoodItem.getCode() );
        coFoodItemDto.setNameVn( coFoodItem.getNameVn() );
        coFoodItemDto.setPositionNumber( coFoodItem.getPositionNumber() );
        coFoodItemDto.setCoImageSize( coFoodItem.getCoImageSize() );
        coFoodItemDto.setBufferLabel( coFoodItem.getBufferLabel() );
        coFoodItemDto.setInfoFoodItem( coFoodItem.getInfoFoodItem() );
        coFoodItemDto.setViewType( coFoodItem.getViewType() );
        coFoodItemDto.setExtraFoodItem( coFoodItem.getExtraFoodItem() );
        coFoodItemDto.setKaloGroupId( coFoodItem.getKaloGroupId() );
        coFoodItemDto.setHideIcon( coFoodItem.getHideIcon() );
        coFoodItemDto.setNumberOfPeopleEat( coFoodItem.getNumberOfPeopleEat() );
        coFoodItemDto.setNote( coFoodItem.getNote() );
        coFoodItemDto.setNoteQuantitative( coFoodItem.getNoteQuantitative() );

        coFoodItemDto.setFeatures( null );
        coFoodItemDto.setCoFoodItemModifiers( null );
        coFoodItemDto.setRelatedFoodItems( null );
        coFoodItemDto.setFoodItem( null );
        coFoodItemDto.setToppingFoodItems( null );
        coFoodItemDto.setModifiers( null );
        coFoodItemDto.setCo( null );

        return coFoodItemDto;
    }

    protected List<CoFoodItem> coFoodItemDtoListToCoFoodItemList(List<CoFoodItemDto> list) {
        if ( list == null ) {
            return null;
        }

        List<CoFoodItem> list1 = new ArrayList<CoFoodItem>( list.size() );
        for ( CoFoodItemDto coFoodItemDto : list ) {
            list1.add( coFoodItemDtoToCoFoodItem( coFoodItemDto ) );
        }

        return list1;
    }

    protected Co coDtoToCo(CoDto coDto) {
        if ( coDto == null ) {
            return null;
        }

        Co co = new Co();

        co.setId( coDto.getId() );
        co.setSoId( coDto.getSoId() );
        co.setName( coDto.getName() );
        co.setStatus( coDto.isStatus() );
        co.setCreatedBy( coDto.getCreatedBy() );
        co.setCreatedDate( coDto.getCreatedDate() );
        co.setModifiedBy( coDto.getModifiedBy() );
        co.setModifiedDate( coDto.getModifiedDate() );
        co.setCoFoodItems( coFoodItemDtoListToCoFoodItemList( coDto.getCoFoodItems() ) );

        return co;
    }

    protected RelatedFoodItem relatedFoodItemDtoToRelatedFoodItem(RelatedFoodItemDto relatedFoodItemDto) {
        if ( relatedFoodItemDto == null ) {
            return null;
        }

        RelatedFoodItem relatedFoodItem = new RelatedFoodItem();

        relatedFoodItem.setId( relatedFoodItemDto.getId() );
        relatedFoodItem.setFoodItemCode( relatedFoodItemDto.getFoodItemCode() );
        relatedFoodItem.setFoodItemName( relatedFoodItemDto.getFoodItemName() );
        relatedFoodItem.setNameDisplay( relatedFoodItemDto.getNameDisplay() );
        relatedFoodItem.setCoFoodItem( coFoodItemDtoToCoFoodItem( relatedFoodItemDto.getCoFoodItem() ) );
        relatedFoodItem.setSapCode( relatedFoodItemDto.getSapCode() );
        relatedFoodItem.setType( relatedFoodItemDto.getType() );

        return relatedFoodItem;
    }

    protected List<RelatedFoodItem> relatedFoodItemDtoListToRelatedFoodItemList(List<RelatedFoodItemDto> list) {
        if ( list == null ) {
            return null;
        }

        List<RelatedFoodItem> list1 = new ArrayList<RelatedFoodItem>( list.size() );
        for ( RelatedFoodItemDto relatedFoodItemDto : list ) {
            list1.add( relatedFoodItemDtoToRelatedFoodItem( relatedFoodItemDto ) );
        }

        return list1;
    }

    protected ToppingFoodItem toppingFoodItemDtoToToppingFoodItem(ToppingFoodItemDto toppingFoodItemDto) {
        if ( toppingFoodItemDto == null ) {
            return null;
        }

        ToppingFoodItem toppingFoodItem = new ToppingFoodItem();

        toppingFoodItem.setId( toppingFoodItemDto.getId() );
        toppingFoodItem.setFoodItemCode( toppingFoodItemDto.getFoodItemCode() );
        toppingFoodItem.setFoodItemName( toppingFoodItemDto.getFoodItemName() );
        toppingFoodItem.setModifierName( toppingFoodItemDto.getModifierName() );
        toppingFoodItem.setModifierCode( toppingFoodItemDto.getModifierCode() );
        toppingFoodItem.setCoFoodItem( coFoodItemDtoToCoFoodItem( toppingFoodItemDto.getCoFoodItem() ) );
        toppingFoodItem.setSapCode( toppingFoodItemDto.getSapCode() );

        return toppingFoodItem;
    }

    protected List<ToppingFoodItem> toppingFoodItemDtoListToToppingFoodItemList(List<ToppingFoodItemDto> list) {
        if ( list == null ) {
            return null;
        }

        List<ToppingFoodItem> list1 = new ArrayList<ToppingFoodItem>( list.size() );
        for ( ToppingFoodItemDto toppingFoodItemDto : list ) {
            list1.add( toppingFoodItemDtoToToppingFoodItem( toppingFoodItemDto ) );
        }

        return list1;
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

    protected Feature featureDtoToFeature(FeatureDto featureDto) {
        if ( featureDto == null ) {
            return null;
        }

        Feature feature = new Feature();

        feature.setId( featureDto.getId() );
        feature.setParentId( featureDto.getParentId() );
        feature.setRestaurantCode( featureDto.getRestaurantCode() );
        feature.setCode( featureDto.getCode() );
        feature.setNameEn( featureDto.getNameEn() );
        feature.setNameVn( featureDto.getNameVn() );
        feature.setStatus( featureDto.getStatus() );
        feature.setDescEn( featureDto.getDescEn() );
        feature.setDescVn( featureDto.getDescVn() );

        return feature;
    }

    protected List<Feature> featureDtoListToFeatureList(List<FeatureDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Feature> list1 = new ArrayList<Feature>( list.size() );
        for ( FeatureDto featureDto : list ) {
            list1.add( featureDtoToFeature( featureDto ) );
        }

        return list1;
    }

    protected List<CoFoodItemModifier> cofoodItemModifierDtoListToCoFoodItemModifierList(List<CofoodItemModifierDto> list) {
        if ( list == null ) {
            return null;
        }

        List<CoFoodItemModifier> list1 = new ArrayList<CoFoodItemModifier>( list.size() );
        for ( CofoodItemModifierDto cofoodItemModifierDto : list ) {
            list1.add( dtoToEntity( cofoodItemModifierDto ) );
        }

        return list1;
    }

    protected CoFoodItem coFoodItemDtoToCoFoodItem(CoFoodItemDto coFoodItemDto) {
        if ( coFoodItemDto == null ) {
            return null;
        }

        CoFoodItem coFoodItem = new CoFoodItem();

        coFoodItem.setId( coFoodItemDto.getId() );
        coFoodItem.setRestaurantCode( coFoodItemDto.getRestaurantCode() );
        coFoodItem.setMaxPerTransaction( coFoodItemDto.getMaxPerTransaction() );
        coFoodItem.setMaxForKitchen( coFoodItemDto.getMaxForKitchen() );
        coFoodItem.setKalo( coFoodItemDto.getKalo() );
        coFoodItem.setSoId( coFoodItemDto.getSoId() );
        coFoodItem.setNameEn( coFoodItemDto.getNameEn() );
        coFoodItem.setDescVn( coFoodItemDto.getDescVn() );
        coFoodItem.setDescEn( coFoodItemDto.getDescEn() );
        if ( coFoodItemDto.getPrice() != null ) {
            coFoodItem.setPrice( BigInteger.valueOf( coFoodItemDto.getPrice() ) );
        }
        coFoodItem.setCode( coFoodItemDto.getCode() );
        coFoodItem.setCombo( coFoodItemDto.isCombo() );
        coFoodItem.setNameVn( coFoodItemDto.getNameVn() );
        coFoodItem.setPositionNumber( coFoodItemDto.getPositionNumber() );
        coFoodItem.setCoImageSize( coFoodItemDto.getCoImageSize() );
        coFoodItem.setBufferLabel( coFoodItemDto.getBufferLabel() );
        coFoodItem.setInfoFoodItem( coFoodItemDto.getInfoFoodItem() );
        coFoodItem.setViewType( coFoodItemDto.getViewType() );
        coFoodItem.setExtraFoodItem( coFoodItemDto.getExtraFoodItem() );
        coFoodItem.setKaloGroupId( coFoodItemDto.getKaloGroupId() );
        coFoodItem.setHideIcon( coFoodItemDto.getHideIcon() );
        coFoodItem.setNumberOfPeopleEat( coFoodItemDto.getNumberOfPeopleEat() );
        coFoodItem.setNote( coFoodItemDto.getNote() );
        coFoodItem.setNoteQuantitative( coFoodItemDto.getNoteQuantitative() );
        coFoodItem.setCo( coDtoToCo( coFoodItemDto.getCo() ) );
        coFoodItem.setRelatedFoodItems( relatedFoodItemDtoListToRelatedFoodItemList( coFoodItemDto.getRelatedFoodItems() ) );
        coFoodItem.setToppingFoodItems( toppingFoodItemDtoListToToppingFoodItemList( coFoodItemDto.getToppingFoodItems() ) );
        coFoodItem.setFoodItem( foodItemDtoToFoodItem( coFoodItemDto.getFoodItem() ) );
        coFoodItem.setFeatures( featureDtoListToFeatureList( coFoodItemDto.getFeatures() ) );
        coFoodItem.setCoFoodItemModifiers( cofoodItemModifierDtoListToCoFoodItemModifierList( coFoodItemDto.getCoFoodItemModifiers() ) );

        return coFoodItem;
    }

    protected Modifier modifierDtoToModifier(ModifierDto modifierDto) {
        if ( modifierDto == null ) {
            return null;
        }

        Modifier modifier = new Modifier();

        modifier.setId( modifierDto.getId() );
        modifier.setItemId( modifierDto.getItemId() );
        modifier.setParentId( modifierDto.getParentId() );
        modifier.setCode( modifierDto.getCode() );
        modifier.setName( modifierDto.getName() );
        modifier.setUnsignedName( modifierDto.getUnsignedName() );
        modifier.setStatus( modifierDto.getStatus() );
        modifier.setDish( modifierDto.getDish() );
        modifier.setMaxOneDish( modifierDto.getMaxOneDish() );
        modifier.setRightLvl( modifierDto.getRightLvl() );
        modifier.setObjectSifr( modifierDto.getObjectSifr() );
        modifier.setFlags( modifierDto.getFlags() );

        return modifier;
    }

    protected ModifierDto modifierToModifierDto(Modifier modifier) {
        if ( modifier == null ) {
            return null;
        }

        ModifierDto modifierDto = new ModifierDto();

        modifierDto.setId( modifier.getId() );
        modifierDto.setItemId( modifier.getItemId() );
        modifierDto.setParentId( modifier.getParentId() );
        modifierDto.setCode( modifier.getCode() );
        modifierDto.setName( modifier.getName() );
        modifierDto.setUnsignedName( modifier.getUnsignedName() );
        modifierDto.setStatus( modifier.getStatus() );
        modifierDto.setDish( modifier.getDish() );
        modifierDto.setMaxOneDish( modifier.getMaxOneDish() );
        modifierDto.setRightLvl( modifier.getRightLvl() );
        modifierDto.setObjectSifr( modifier.getObjectSifr() );
        modifierDto.setFlags( modifier.getFlags() );

        return modifierDto;
    }
}
