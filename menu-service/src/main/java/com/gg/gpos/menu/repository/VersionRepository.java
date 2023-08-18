package com.gg.gpos.menu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.gg.gpos.menu.entity.Version;

@Repository
public interface VersionRepository extends JpaRepository<Version, Long>, JpaSpecificationExecutor<Version>{
	Version findByTableName(String tableName);

}
