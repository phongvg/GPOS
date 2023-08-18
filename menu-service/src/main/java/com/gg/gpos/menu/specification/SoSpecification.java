package com.gg.gpos.menu.specification;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import com.gg.gpos.menu.dto.SoDto;
import com.gg.gpos.menu.entity.So;

@Component
public class SoSpecification {
	public Specification<So> filter(final SoDto criteria){
		return (so,cq,cb) -> {
			List<Predicate> predicates = new ArrayList<>();
			if (StringUtils.isNotBlank(criteria.getName())) {
				predicates.add(cb.like(so.get("name"), "%" + criteria.getName() +"%"));
			}
			return cb.and(predicates.stream().toArray(Predicate[]::new));
		};
	}
}
