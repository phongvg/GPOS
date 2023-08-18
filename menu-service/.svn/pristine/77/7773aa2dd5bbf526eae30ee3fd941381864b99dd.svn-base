package com.gg.gpos.menu.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.gg.gpos.menu.entity.ModiGroup;

@Repository
public interface ModiGroupRepository extends JpaRepository<ModiGroup, Long>,JpaSpecificationExecutor<ModiGroup> {
	@Query("from ModiGroup mg where mg.status=3")
	List<ModiGroup> findActives();
}
