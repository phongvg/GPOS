package com.speedfrwk.data.jpa.audit.specification;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.speedfrwk.data.jpa.audit.AuditLog;

@Component
public class AuditLogSpecification {
	public Specification<AuditLog> filter(final Date startDate, final Date endDate){
		return (root, cq, cb) -> {
			List<Predicate> predicates = new ArrayList<>();
			if (startDate != null && endDate != null) {
				predicates.add(cb.between(root.get("modifiedDate"), startDate, endDate));
			}
			return cb.and(predicates.stream().toArray(Predicate[]::new));
		};
	}
}
