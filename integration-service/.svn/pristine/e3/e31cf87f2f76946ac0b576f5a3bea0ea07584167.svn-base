package com.gg.gpos.integration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.gg.gpos.integration.entity.Sync;
@Repository
public interface SyncRepository extends JpaRepository<Sync, Long>, JpaSpecificationExecutor<Sync>{
	List<Sync> findTop5ByStatusAndTypeSyncNotOrderByIdAsc(String status, String typeSync);
	List<Sync> findTop5ByStatusAndTypeSyncOrderByIdAsc(String status, String typeSync);
	List<Sync> findByStatus(String status);
	List<Sync> findByIdIn(List<Long> ids);
}
