package com.gg.gpos.integration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.gg.gpos.integration.entity.Sync;
import com.gg.gpos.integration.entity.TmpSync;

@Repository
public interface TmpSyncRepository extends JpaRepository<TmpSync, Long>, JpaSpecificationExecutor<TmpSync>{
	void deleteBySync(Sync sync);
}
