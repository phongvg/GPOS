package com.gg.gpos.reference.mapper;

import com.gg.gpos.reference.dto.SurveyDetailDto;
import com.gg.gpos.reference.dto.SurveyDto;
import com.gg.gpos.reference.entity.Survey;
import com.gg.gpos.reference.entity.SurveyDetail;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-01T17:40:19+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"
)
@Component
public class SurveyMapperImpl implements SurveyMapper {

    @Override
    public Survey dtoToEntity(SurveyDto surveyDto) {
        if ( surveyDto == null ) {
            return null;
        }

        Survey survey = new Survey();

        survey.setId( surveyDto.getId() );
        survey.setRestaurantCode( surveyDto.getRestaurantCode() );
        survey.setRestaurantName( surveyDto.getRestaurantName() );
        survey.setOrderCode( surveyDto.getOrderCode() );
        survey.setCreatedTime( surveyDto.getCreatedTime() );
        survey.setStaffCode( surveyDto.getStaffCode() );
        survey.setIsSurvey( surveyDto.getIsSurvey() );
        survey.setSurveyDetails( surveyDetailDtoListToSurveyDetailList( surveyDto.getSurveyDetails() ) );

        return survey;
    }

    @Override
    public SurveyDto entityToDto(Survey survey) {
        if ( survey == null ) {
            return null;
        }

        SurveyDto surveyDto = new SurveyDto();

        surveyDto.setId( survey.getId() );
        surveyDto.setRestaurantCode( survey.getRestaurantCode() );
        surveyDto.setRestaurantName( survey.getRestaurantName() );
        surveyDto.setOrderCode( survey.getOrderCode() );
        surveyDto.setCreatedTime( survey.getCreatedTime() );
        surveyDto.setStaffCode( survey.getStaffCode() );
        surveyDto.setIsSurvey( survey.getIsSurvey() );
        surveyDto.setSurveyDetails( surveyDetailListToSurveyDetailDtoList( survey.getSurveyDetails() ) );

        return surveyDto;
    }

    @Override
    public SurveyDetailDto surveyDetailToSurveyDetailDto(SurveyDetail surveyDetail) {
        if ( surveyDetail == null ) {
            return null;
        }

        SurveyDetailDto surveyDetailDto = new SurveyDetailDto();

        surveyDetailDto.setId( surveyDetail.getId() );
        surveyDetailDto.setQuestionId( surveyDetail.getQuestionId() );
        surveyDetailDto.setQuestionName( surveyDetail.getQuestionName() );
        surveyDetailDto.setAnswerId( surveyDetail.getAnswerId() );
        surveyDetailDto.setAnswerName( surveyDetail.getAnswerName() );

        surveyDetailDto.setSurvey( null );

        return surveyDetailDto;
    }

    protected SurveyDetail surveyDetailDtoToSurveyDetail(SurveyDetailDto surveyDetailDto) {
        if ( surveyDetailDto == null ) {
            return null;
        }

        SurveyDetail surveyDetail = new SurveyDetail();

        surveyDetail.setId( surveyDetailDto.getId() );
        surveyDetail.setQuestionId( surveyDetailDto.getQuestionId() );
        surveyDetail.setQuestionName( surveyDetailDto.getQuestionName() );
        surveyDetail.setAnswerId( surveyDetailDto.getAnswerId() );
        surveyDetail.setAnswerName( surveyDetailDto.getAnswerName() );
        surveyDetail.setSurvey( dtoToEntity( surveyDetailDto.getSurvey() ) );

        return surveyDetail;
    }

    protected List<SurveyDetail> surveyDetailDtoListToSurveyDetailList(List<SurveyDetailDto> list) {
        if ( list == null ) {
            return null;
        }

        List<SurveyDetail> list1 = new ArrayList<SurveyDetail>( list.size() );
        for ( SurveyDetailDto surveyDetailDto : list ) {
            list1.add( surveyDetailDtoToSurveyDetail( surveyDetailDto ) );
        }

        return list1;
    }

    protected List<SurveyDetailDto> surveyDetailListToSurveyDetailDtoList(List<SurveyDetail> list) {
        if ( list == null ) {
            return null;
        }

        List<SurveyDetailDto> list1 = new ArrayList<SurveyDetailDto>( list.size() );
        for ( SurveyDetail surveyDetail : list ) {
            list1.add( surveyDetailToSurveyDetailDto( surveyDetail ) );
        }

        return list1;
    }
}
