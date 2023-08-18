package com.gg.gpos.reference.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.gg.gpos.reference.dto.SystemParameterDto;
import com.gg.gpos.reference.entity.SystemParameter;

@Component
public class SystemParameterSpecification {
	public Specification<SystemParameter> search(final SystemParameterDto criteria){
		return (root,cq,cb) -> {
			List<Predicate> predicates = new ArrayList<>();
			if (StringUtils.isNotBlank(criteria.getParamName())) {
				predicates.add(cb.like(root.get("paramName"), "%" + criteria.getParamName() +"%"));
			}
			return cb.and(predicates.stream().toArray(Predicate[]::new));
		};
	}
}
