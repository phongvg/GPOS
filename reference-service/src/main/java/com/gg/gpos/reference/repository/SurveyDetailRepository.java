package com.gg.gpos.reference.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gg.gpos.reference.entity.Survey;
import com.gg.gpos.reference.entity.SurveyDetail;

@Repository
public interface SurveyDetailRepository extends JpaRepository<SurveyDetail, Long>{
	void deleteBySurvey(Survey survey);

}
