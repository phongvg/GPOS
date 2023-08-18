package com.gg.gpos.menu.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.gg.gpos.menu.dto.CoCategoryDto;
import com.gg.gpos.menu.entity.CoCategory;

@Component
public class CoCategorySpecification {
	public Specification<CoCategory> filter(final CoCategoryDto criteria) {
		return (root, cq, cb) -> {
			List<Predicate> predicates = new ArrayList<>();
			
			if (criteria.getCoId() != null) {
				predicates.add(cb.equal(root.get("coId"), criteria.getCoId()));
			}			
			
			if (criteria.getRestaurantCode() != null) {
				predicates.add(cb.equal(root.get("restaurantCode"), criteria.getRestaurantCode()));
				if(criteria.getIsChanged()) {
					predicates.add(cb.isNull(root.get("coId")));
				}
			} else {
				predicates.add(cb.isNull(root.get("restaurantCode")));
			}
			
			return cb.and(predicates.stream().toArray(Predicate[]::new));
		};
	}
}
