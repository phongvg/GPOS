package com.gg.gpos.res.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.gg.gpos.res.entity.RestaurantUser;

@Repository
public interface RestaurantUserRepository extends JpaRepository<RestaurantUser, Long>, JpaSpecificationExecutor<RestaurantUser> {
	@Query("from RestaurantUser ru join fetch ru.restaurant r where ru.username = :username")
	List<RestaurantUser> findByUsername(@Param("username") String username);
	List<RestaurantUser> findByRestaurantCode(Integer code);
	RestaurantUser findByUsernameAndRestaurantCode(String username, Integer restaurantCode);
	void deleteByUsername(String username);
}
