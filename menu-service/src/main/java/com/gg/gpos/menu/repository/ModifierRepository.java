package com.gg.gpos.menu.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.gg.gpos.menu.entity.Modifier;

/**
 * Generated by Speed Generator
 * 
 * @author <a href="mailto:ngtrungkien@gmail.com">Kien Nguyen</a>
 */
@Repository
public interface ModifierRepository extends JpaRepository<Modifier, Long>, JpaSpecificationExecutor<Modifier> {
	@Query("from Modifier m where m.status=3 and m.code=:code")
	List<Modifier> findByCode(String code);
	@Query("from Modifier m where m.status=3")
	List<Modifier> findActives();
	@Query("from Modifier m where (m.status=3 and m.unsignedName like :name%) or (m.status=3 and m.code like :code%)")
	List<Modifier> findByUnsignedNameStartingWithOrCodeStartingWith(@Param("name") String name, @Param("code") String code);
}