package com.gg.gpos.menu.specification;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import com.gg.gpos.menu.dto.ParamDto;
import com.gg.gpos.menu.entity.GroupParam;
import com.gg.gpos.menu.entity.Param;

@Component
public class ParamSpecification {
	public Specification<Param> filter(final ParamDto criteria) {
		return (root, cq, cb) -> {
			List<Predicate> predicates = new ArrayList<>();
			
			if (criteria.getGroupParamId() != null) {
				Join<Param, GroupParam> prRoot = root.join("groupParam");
				predicates.add(cb.equal(prRoot.get("id"), criteria.getGroupParamId()));
			}
			
			if (StringUtils.isNotBlank(criteria.getParamCode())) {
				predicates.add(cb.like(root.get("paramCode"), "%" + criteria.getParamCode().trim() + "%"));
			}
			
			if (criteria.getRestaurantCode() != null) {
				predicates.add(cb.equal(root.get("restaurantCode"), criteria.getRestaurantCode()));
			} else {
				predicates.add(cb.isNull(root.get("restaurantCode")));
			}
			
			return cb.and(predicates.stream().toArray(Predicate[]::new));
		};
	}
}
