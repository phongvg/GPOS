package com.gg.gpos.reference.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.gg.gpos.reference.dto.SurveyDetailDto;
import com.gg.gpos.reference.dto.SurveyDto;
import com.gg.gpos.reference.entity.Survey;
import com.gg.gpos.reference.entity.SurveyDetail;

@Mapper(componentModel = "spring")
public interface SurveyMapper {
	Survey dtoToEntity(SurveyDto surveyDto);
	SurveyDto entityToDto(Survey survey);
	
	@Mapping(target = "survey", expression = "java(null)")
	SurveyDetailDto surveyDetailToSurveyDetailDto(SurveyDetail surveyDetail);
}
