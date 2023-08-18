package com.gg.gpos.menu.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.gg.gpos.menu.entity.CoFoodItem;
import com.gg.gpos.menu.entity.CoFoodItemModifier;

@Repository
public interface CoFoodItemModifierRepository extends JpaRepository<CoFoodItemModifier, Long>, JpaSpecificationExecutor<CoFoodItemModifier> {
	void deleteByCoFoodItem(CoFoodItem coFoodItem);
	List<CoFoodItemModifier> findByCoFoodItem(CoFoodItem coFoodItem);
	void deleteByCoFoodItemIn(List<CoFoodItem> coFoodItems);
} 
