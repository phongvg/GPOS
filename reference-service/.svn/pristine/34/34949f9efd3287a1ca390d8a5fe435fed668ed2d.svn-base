package com.gg.gpos.reference.manager;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gg.gpos.reference.dto.SurveyDetailDto;
import com.gg.gpos.reference.entity.SurveyDetail;
import com.gg.gpos.reference.mapper.SurveyDetailMapper;
import com.gg.gpos.reference.repository.SurveyDetailRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class SurveyDetailManager {

	@Autowired
	private SurveyDetailRepository surveyDetailRepository;
	
	@Autowired
	private SurveyDetailMapper surveyDetailMapper;
	
	public SurveyDetailDto save(SurveyDetailDto surveyDetailDto) {
		log.debug("Entering 'save' method...");
		SurveyDetail surveyDetail = Optional.ofNullable(surveyDetailDto).map(surveyDetailMapper::dtoToEntity).orElse(null);
		return Optional.ofNullable(surveyDetailRepository.save(surveyDetail)).map(surveyDetailMapper::entityToDto).orElse(null);
	}
}
