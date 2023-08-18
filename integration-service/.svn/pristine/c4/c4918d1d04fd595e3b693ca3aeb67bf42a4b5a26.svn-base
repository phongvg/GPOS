package com.gg.gpos.integration.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.gg.gpos.integration.dto.SyncDto;
import com.gg.gpos.integration.entity.Sync;

@Component
public class SyncSpecification {
	public Specification<Sync> filter(final SyncDto criteria) {
		return (root, cq, cb) -> {
			List<Predicate> predicates = new ArrayList<>();
			if(StringUtils.isNotBlank(criteria.getRestaurantName())) {
				predicates.add(cb.like(root.get("restaurantName"), "%" + criteria.getRestaurantName().trim() + "%"));
			}
			if(criteria.getRestaurantCode() != null) {
				predicates.add(cb.equal(root.get("restaurantCode"), criteria.getRestaurantCode()));
			}
			if(StringUtils.isNotBlank(criteria.getStatus())) {
				predicates.add(cb.equal(root.get("status"), criteria.getStatus()));
			}
			return cb.and(predicates.stream().toArray(Predicate[]::new));
		};
	}
}
