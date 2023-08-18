package com.gg.gpos.menu.specification;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import com.gg.gpos.menu.dto.GroupParamDto;
import com.gg.gpos.menu.entity.GroupParam;

@Component
public class GroupParamSpecification {
	public Specification<GroupParam> filter(final GroupParamDto criteria) {
		return (root, cq, cb) -> {
			List<Predicate> predicates = new ArrayList<>();
			if (StringUtils.isNotBlank(criteria.getName())) {
				predicates.add(cb.like(root.get("name"), "%" + criteria.getName().trim() + "%"));
			}
			return cb.and(predicates.stream().toArray(Predicate[]::new));
		};
	}
}
