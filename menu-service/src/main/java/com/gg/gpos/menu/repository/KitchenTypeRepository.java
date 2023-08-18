package com.gg.gpos.menu.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gg.gpos.menu.entity.KitchenType;
@Repository
public interface KitchenTypeRepository extends JpaRepository<KitchenType, Long>{
	Page<KitchenType> findByRestaurantCode(Integer restaurantCode,Pageable pageable);
	List<KitchenType> findByRestaurantCode(Integer resCode);
	List<KitchenType> findByRestaurantCodeAndNameIn(Integer resCode,List<String> names);
	void deleteByRestaurantCode(Integer resCode);

}
