package com.gg.gpos.reference.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.gg.gpos.reference.dto.SurveyDetailDto;
import com.gg.gpos.reference.dto.SurveyDto;
import com.gg.gpos.reference.entity.Survey;
import com.gg.gpos.reference.entity.SurveyDetail;

@Mapper(componentModel = "spring")
public interface SurveyDetailMapper {

	SurveyDetail dtoToEntity(SurveyDetailDto surveyDetailDto);
	SurveyDetailDto entityToDto(SurveyDetail surveyDetail);
	
	@Mapping(target = "surveyDetails", expression = "java(null)")
	SurveyDto surveyToSurveyDto(Survey survey);
}
