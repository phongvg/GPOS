package com.gg.gpos.integration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.gg.gpos.integration.entity.SyncArchive;

@Repository
public interface SyncArchiveRepository extends JpaRepository<SyncArchive, Long>, JpaSpecificationExecutor<SyncArchive> {
}
