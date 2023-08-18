package com.gg.gpos.menu.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.gg.gpos.menu.entity.RestaurantDataEdit;

@Repository
public interface RestaurantDataEditRepository extends JpaRepository<RestaurantDataEdit, Long> {
	RestaurantDataEdit findByValueAndRestaurantCodeAndType(String value, Integer resCode, String type);
	void deleteByRestaurantCode(Integer resCode);
	void deleteByRestaurantCodeAndType(Integer resCode,String type);
	List<RestaurantDataEdit> findByRestaurantCodeAndType(Integer resCode, String type);
	void deleteByValueAndRestaurantCodeAndType(String value, Integer resCode, String type);
	@Query("select rd.value from RestaurantDataEdit rd where rd.restaurantCode = :restaurantCode and rd.type =:type")
	List<String> findValues(@Param("restaurantCode") Integer resCode,@Param("type") String type);
}
