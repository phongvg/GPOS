package com.gg.gpos.menu.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.gg.gpos.menu.entity.CoCategory;
import com.gg.gpos.menu.entity.CoFoodGroupDisplay;

@Repository
public interface CoFoodGroupDisplayRepository extends JpaRepository<CoFoodGroupDisplay, Long>, JpaSpecificationExecutor<CoFoodGroupDisplay>{
	List<CoFoodGroupDisplay> findByCoCategory(CoCategory coCategory);
	List<CoFoodGroupDisplay> findByCoCategoryAndFoodGroupCodeIn(CoCategory coCategory,List<String> foodGroupCode);
	void deleteByCoCategoryAndFoodGroupCodeIn(CoCategory coCategory,List<String> codes);
	void deleteByCoCategory(CoCategory coCategory);
}
