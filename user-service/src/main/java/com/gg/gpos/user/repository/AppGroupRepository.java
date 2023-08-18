package com.gg.gpos.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.gg.gpos.user.entity.AppGroup;

/**
 * Generated by Speed Generator
 * 
 * @author <a href="mailto:ngtrungkien@gmail.com">Kien Nguyen</a>
 */
@Repository
public interface AppGroupRepository extends JpaRepository<AppGroup, Long>, JpaSpecificationExecutor<AppGroup> {
	@Query("from AppGroup g join fetch g.roles where g.id=:id")
	AppGroup loadGroupById(@Param("id") Long id);
}