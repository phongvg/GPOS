package com.gg.gpos.menu.specification;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import com.gg.gpos.menu.entity.Feature;

@Component
public class FeatureSpecification {
	public Specification<Feature> search(final String code, final String name, final Integer status) {
		return (root, cq, cb) -> {
			List<Predicate> predicates = new ArrayList<>();
			predicates.add(cb.notEqual(root.get("status"), 0));
			if (StringUtils.isNotBlank(code)) {
				predicates.add(cb.equal(root.get("code"), code));
			}
			if (StringUtils.isNotBlank(name)) {
				predicates.add(cb.like(root.get("nameVn"), "%" + name + "%"));
			}
			if (status != null) {
				predicates.add(cb.equal(root.get("status"), status));
			}
			return cb.and(predicates.stream().toArray(Predicate[]::new));
		};
	}
}
