package com.gg.gpos.menu.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.gg.gpos.menu.entity.KdsConfigCooking;

@Repository
public interface KdsConfigCookingRepository  extends JpaRepository<KdsConfigCooking, Long>, JpaSpecificationExecutor<KdsConfigCooking>{
	List<KdsConfigCooking> findByRestaurantCode(Integer restaurantCode);
	Page<KdsConfigCooking> findByRestaurantCode(Integer restaurantCode, Pageable pageable);
	KdsConfigCooking findByFoodItem_CodeAndKdsPlace_Code(String foodItemCode, String kdsPlaceCode);
}