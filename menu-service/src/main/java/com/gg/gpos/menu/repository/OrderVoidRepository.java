package com.gg.gpos.menu.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.gg.gpos.menu.entity.OrderVoid;

@Repository
public interface OrderVoidRepository extends JpaRepository<OrderVoid, Long>,JpaSpecificationExecutor<OrderVoid> {
	@Query("from OrderVoid ov where ov.status=3")
	List<OrderVoid> findActives();
}
