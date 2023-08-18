package com.gg.gpos.menu.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.gg.gpos.menu.entity.OrderType;

@Repository
public interface OrderTypeRepository extends JpaRepository<OrderType, Long>, JpaSpecificationExecutor<OrderType> {
	@Query("from OrderType ot where ot.status=3")
	List<OrderType> findActives();
}
