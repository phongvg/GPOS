package com.gg.gpos.menu.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.gg.gpos.menu.entity.GroupParam;
import com.gg.gpos.menu.entity.Param;

@Repository
public interface ParamRepository extends JpaRepository<Param, Long>,JpaSpecificationExecutor<Param>{
	Page<Param> findByRestaurantCode(Integer restaurantCode,Pageable pageable);
	List<Param> findByRestaurantCode(Integer restaurantCode);
	List<Param> findByGroupParamAndRestaurantCodeIsNull(GroupParam groupParam);
	List<Param> findByRestaurantCodeAndGroupParamIsNotNull(Integer restaurantCode);
	void deleteByRestaurantCode(Integer restaurantCodes);
	List<Param> findByIdIn(List<Long> paramIds);
	List<Param> findByRestaurantCodeAndGroupParamIsNull(Integer restaurantCode);
	void deleteByRestaurantCodeAndParamCodeAndGroupParamIsNotNull(Integer restaurantCode, String paramCode);
}
