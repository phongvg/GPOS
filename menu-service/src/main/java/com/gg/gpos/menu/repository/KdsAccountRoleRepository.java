package com.gg.gpos.menu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.gg.gpos.menu.entity.KdsAccountRole;
@Repository
public interface KdsAccountRoleRepository  extends JpaRepository<KdsAccountRole, Long>, JpaSpecificationExecutor<KdsAccountRole>{

}
