package com.gg.gpos.menu.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gg.gpos.menu.entity.CatalogApplyToRestaurant;

@Repository
public interface CatalogApplyToRestaurantRepository extends JpaRepository<CatalogApplyToRestaurant, Long>{
	List<CatalogApplyToRestaurant> findByCoId(Long coId);
	List<CatalogApplyToRestaurant> findBySoId(Long soId);
	List<CatalogApplyToRestaurant> findByGroupParamId(Long gpId);
	CatalogApplyToRestaurant findByRestaurantCode(Integer resCode);
	CatalogApplyToRestaurant findByRestaurantCodeAndSoId(Integer resCode,Long soId);
}
