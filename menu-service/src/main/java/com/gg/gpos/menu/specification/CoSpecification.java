package com.gg.gpos.menu.specification;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import com.gg.gpos.menu.dto.CoDto;
import com.gg.gpos.menu.entity.Co;

@Component
public class CoSpecification {
	public Specification<Co> filter(final CoDto criteria){
		return (root, cq, cb) -> {
			List<Predicate> predicates = new ArrayList<>();
			if (StringUtils.isNotBlank(criteria.getName())) {
				predicates.add(cb.like(root.get("name"), "%" + criteria.getName().trim() + "%"));
			}
			return cb.and(predicates.stream().toArray(Predicate[]::new));
		};
	}
}
