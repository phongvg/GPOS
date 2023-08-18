package com.gg.gpos.menu.specification;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import com.gg.gpos.menu.dto.CoFoodItemDto;

@Component
public class ReferenceObjectSpecification<T> {

	public Specification<T> search(final String code, final String name, final Integer status) {
		return (root, cq, cb) -> {
			List<Predicate> predicates = new ArrayList<>();
			if (StringUtils.isNotBlank(code)) {
				predicates.add(cb.equal(root.get("code"), code));
			}
			if (StringUtils.isNotBlank(name)) {
				predicates.add(cb.like(root.get("name"), "%" + name + "%"));
			}
			if (status != null) {
				predicates.add(cb.equal(root.get("status"), status));
			}
			return cb.and(predicates.stream().toArray(Predicate[]::new));
		};
	}

	public Specification<T> search(final String name) {
		return (root, cq, cb) -> {
			List<Predicate> predicates = new ArrayList<>();
			if (StringUtils.isNotBlank(name)) {
				predicates.add(cb.like(root.get("readOnlyName"), "%" + name + "%"));
			}
			return cb.and(predicates.stream().toArray(Predicate[]::new));
		};
	}

	public Specification<T> searchCoFoodItem(final CoFoodItemDto criteria) {
		return (root, cq, cb) -> {
			List<Predicate> predicates = new ArrayList<>();
			if(criteria.getRestaurantCode() != null) {
				predicates.add(cb.equal(root.get("restaurantCode"), criteria.getRestaurantCode()));
			}else {
				predicates.add(cb.isNull(root.get("restaurantCode")));
				predicates.add(cb.equal(root.get("co").get("id"), criteria.getCo().getId()));
			}
			if (StringUtils.isNotBlank(criteria.getFoodItem().getCode())) {
				predicates.add(cb.like(root.get("foodItem").get("code"), "%" + criteria.getFoodItem().getCode() + "%"));
			}
			if (StringUtils.isNotBlank(criteria.getFoodItem().getSapCode())) {
				predicates.add(cb.like(root.get("foodItem").get("sapCode"), "%" + criteria.getFoodItem().getSapCode() + "%"));
			}
			if (StringUtils.isNotBlank(criteria.getNameVn())) {
				predicates.add(cb.like(root.get("foodItem").get("unsignedName"), "%" + StringUtils.stripAccents(criteria.getNameVn()) + "%"));
			}
			if (criteria.isChanged()) {
				predicates.add(cb.isNull(root.get("soId")));
			}
			return cb.and(predicates.stream().toArray(Predicate[]::new));
		};
	}
}
