package com.gg.gpos.menu.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.gg.gpos.menu.entity.TableKitchen;

@Repository
public interface TableKitchenRepository extends JpaRepository<TableKitchen, Long>, JpaSpecificationExecutor<TableKitchen> {
	@Query("from TableKitchen tk where tk.status=3")
	List<TableKitchen> findActives();
	@Query("from TableKitchen tk where tk.status=3 and tk.hallPlanId = :hallPlanId")
	List<TableKitchen> findByHallplanId(@Param("hallPlanId")Integer hallPlanId);

}
