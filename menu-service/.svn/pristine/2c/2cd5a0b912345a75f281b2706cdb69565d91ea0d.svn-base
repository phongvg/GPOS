package com.gg.gpos.menu.repository;
import com.gg.gpos.menu.entity.CoCategory;
import com.gg.gpos.menu.entity.DigitalMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DigitalMenuRepository extends JpaRepository<DigitalMenu, Long>, JpaSpecificationExecutor<DigitalMenu>{
    void deleteByCoCategory(CoCategory coCategory);
}
