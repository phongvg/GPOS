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
public class SurveyDetailMapperImpl implements SurveyDetailMapper {

    @Override
    public SurveyDetail dtoToEntity(SurveyDetailDto surveyDetailDto) {
        if ( surveyDetailDto == null ) {
            return null;
        }

        SurveyDetail surveyDetail = new SurveyDetail();

        surveyDetail.setId( surveyDetailDto.getId() );
        surveyDetail.setQuestionId( surveyDetailDto.getQuestionId() );
        surveyDetail.setQuestionName( surveyDetailDto.getQuestionName() );
        surveyDetail.setAnswerId( surveyDetailDto.getAnswerId() );
        surveyDetail.setAnswerName( surveyDetailDto.getAnswerName() );
        surveyDetail.setSurvey( surveyDtoToSurvey( surveyDetailDto.getSurvey() ) );

        return surveyDetail;
    }

    @Override
    public SurveyDetailDto entityToDto(SurveyDetail surveyDetail) {
        if ( surveyDetail == null ) {
            return null;
        }

        SurveyDetailDto surveyDetailDto = new SurveyDetailDto();

        surveyDetailDto.setId( surveyDetail.getId() );
        surveyDetailDto.setQuestionId( surveyDetail.getQuestionId() );
        surveyDetailDto.setQuestionName( surveyDetail.getQuestionName() );
        surveyDetailDto.setAnswerId( surveyDetail.getAnswerId() );
        surveyDetailDto.setAnswerName( surveyDetail.getAnswerName() );
        surveyDetailDto.setSurvey( surveyToSurveyDto( surveyDetail.getSurvey() ) );

        return surveyDetailDto;
    }

    @Override
    public SurveyDto surveyToSurveyDto(Survey survey) {
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

        surveyDto.setSurveyDetails( null );

        return surveyDto;
    }

    protected List<SurveyDetail> surveyDetailDtoListToSurveyDetailList(List<SurveyDetailDto> list) {
        if ( list == null ) {
            return null;
        }

        List<SurveyDetail> list1 = new ArrayList<SurveyDetail>( list.size() );
        for ( SurveyDetailDto surveyDetailDto : list ) {
            list1.add( dtoToEntity( surveyDetailDto ) );
        }

        return list1;
    }

    protected Survey surveyDtoToSurvey(SurveyDto surveyDto) {
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
}
