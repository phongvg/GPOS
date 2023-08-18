package com.gg.gpos.reference.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gg.gpos.reference.entity.Survey;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long>{
	Survey findByOrderCode(String orderCode);

}
