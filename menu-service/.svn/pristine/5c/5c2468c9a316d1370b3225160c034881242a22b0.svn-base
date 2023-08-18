package com.gg.gpos.menu.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gg.gpos.menu.entity.CatalogDataEdit;

@Repository
public interface CatalogDataEditRepository extends JpaRepository<CatalogDataEdit, Long>{
	List<CatalogDataEdit> findByTypeAndCatalogId(String type, Long catalogId);
	CatalogDataEdit findByCatalogIdAndTypeAndValue(Long catalogId,String type,String value);
	void deleteByCatalogIdAndType(Long catalogId, String type);
	void deleteByCatalogIdAndTypeAndValue(Long catalogId,String type,String value);
}
