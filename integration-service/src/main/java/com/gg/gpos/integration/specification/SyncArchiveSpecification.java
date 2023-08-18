package com.gg.gpos.integration.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.gg.gpos.common.constant.SyncStatusEnum;
import com.gg.gpos.integration.dto.SyncArchiveDto;
import com.gg.gpos.integration.entity.SyncArchive;

@Component
public class SyncArchiveSpecification {
	public Specification<SyncArchive> filter(final SyncArchiveDto criteria) {
		return (root, cq, cb) -> {
			List<Predicate> predicates = new ArrayList<>();
			if(criteria.isCheckSyncDataFail()) {
				predicates.add(cb.like(root.get("status"), SyncStatusEnum.FAIL.val));
			}
			if(criteria.isCheckSyncDataSuccess()) {
				predicates.add(cb.like(root.get("status"), SyncStatusEnum.SUCCESS.val));
			}
			if(criteria.getRestaurantCode() != null) {
				predicates.add(cb.equal(root.get("restaurantCode"), criteria.getRestaurantCode()));
			}
			return cb.and(predicates.stream().toArray(Predicate[]::new));
		};
	}
}
