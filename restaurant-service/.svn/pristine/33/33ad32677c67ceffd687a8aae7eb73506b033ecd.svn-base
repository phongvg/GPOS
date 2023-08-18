package com.gg.gpos.res.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.gg.gpos.common.constant.StatusEnum;
import com.gg.gpos.common.constant.StatusSyncEnum;
import com.gg.gpos.res.dto.RestaurantDto;
import com.gg.gpos.res.entity.Restaurant;
import com.gg.gpos.res.entity.SyncStatus;
import com.gg.gpos.res.entity.RestaurantUser;

@Component
public class RestaurantSpecification {
	public Specification<Restaurant> filter(final RestaurantDto criteria) {
		return (restaurant, cq, cb) -> {
			List<Predicate> predicates = new ArrayList<>();
			predicates.add(cb.equal(restaurant.get("status"), StatusEnum.ACTIVE.status));
			if(criteria.getRestaurantCodes() != null && !criteria.getRestaurantCodes().isEmpty()) {
				predicates.add(restaurant.get("code").in(criteria.getRestaurantCodes()));
			}
			if (criteria.getCode() != null) {
				predicates.add(cb.equal(restaurant.get("code"), criteria.getCode()));
			}
			if (!StringUtils.isEmpty(criteria.getName())) {
				predicates.add(cb.like(restaurant.get("name"), "%" + criteria.getName() + "%"));
			}
			if (criteria.getOnline() != null) {
				predicates.add(cb.equal(restaurant.get("online"), criteria.getOnline()));
			}
			if(criteria.isCheckSyncMasterDataSuccess()) {
				Join<Restaurant, SyncStatus> syncStatus = restaurant.join("syncStatus");
				predicates.add(cb.like(syncStatus.get("masterDataSyncStatus"), StatusSyncEnum.SYNC_SUCCCESS.val));
			}
			if(criteria.isCheckSyncMasterData()) {
				Join<Restaurant, SyncStatus> syncStatus = restaurant.join("syncStatus");
				predicates.add(cb.or(cb.like(syncStatus.get("masterDataSyncStatus"), StatusSyncEnum.SYNC_FAIL.val),cb.like(syncStatus.get("masterDataSyncStatus"), StatusSyncEnum.NOT_SYNC.val)));
			}
			if(criteria.isCheckSyncMenuSuccess()) {
				Join<Restaurant, SyncStatus> syncStatus = restaurant.join("syncStatus");
				predicates.add(cb.like(syncStatus.get("businessSyncStatus"), StatusSyncEnum.SYNC_SUCCCESS.val));
			}
			if(criteria.isCheckSyncMenu()) {
				Join<Restaurant, SyncStatus> syncStatus = restaurant.join("syncStatus");
				predicates.add(cb.or(cb.like(syncStatus.get("businessSyncStatus"), StatusSyncEnum.SYNC_FAIL.val),cb.like(syncStatus.get("businessSyncStatus"), StatusSyncEnum.NOT_SYNC.val)));
			}
			if (!StringUtils.isEmpty(criteria.getUsername())) {
				Join<Restaurant, RestaurantUser> restaurantUser = restaurant.join("restaurantUsers");
				predicates.add(cb.equal(restaurantUser.get("username"), criteria.getUsername()));
			}
			if(!StringUtils.isEmpty(criteria.getIp())) {
				predicates.add(cb.like(restaurant.get("ip"), "%" + criteria.getIp() + "%"));
			}
			return cb.and(predicates.stream().toArray(Predicate[]::new));
		};
	}
}
