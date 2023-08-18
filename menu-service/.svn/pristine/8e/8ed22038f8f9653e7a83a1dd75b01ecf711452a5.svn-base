package com.gg.gpos.menu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.gg.gpos.menu.entity.KaloGroup;
@Repository
public interface KaloGroupRepository extends JpaRepository<KaloGroup, Long>, JpaSpecificationExecutor<KaloGroup>{
	KaloGroup findByCode(String code);
}
