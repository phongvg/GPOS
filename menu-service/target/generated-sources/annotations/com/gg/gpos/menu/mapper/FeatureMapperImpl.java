package com.gg.gpos.menu.mapper;

import com.gg.gpos.menu.dto.CoFoodItemDto;
import com.gg.gpos.menu.dto.FeatureDto;
import com.gg.gpos.menu.entity.CoFoodItem;
import com.gg.gpos.menu.entity.Feature;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-01T17:40:56+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"
)
@Component
public class FeatureMapperImpl implements FeatureMapper {

    @Override
    public Feature dtoToEntity(FeatureDto featureDto) {
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

    @Override
    public FeatureDto entityToDto(Feature feature) {
        if ( feature == null ) {
            return null;
        }

        FeatureDto featureDto = new FeatureDto();

        featureDto.setId( feature.getId() );
        featureDto.setParentId( feature.getParentId() );
        featureDto.setRestaurantCode( feature.getRestaurantCode() );
        featureDto.setCode( feature.getCode() );
        featureDto.setNameEn( feature.getNameEn() );
        featureDto.setNameVn( feature.getNameVn() );
        featureDto.setStatus( feature.getStatus() );
        featureDto.setDescVn( feature.getDescVn() );
        featureDto.setDescEn( feature.getDescEn() );

        return featureDto;
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
}
