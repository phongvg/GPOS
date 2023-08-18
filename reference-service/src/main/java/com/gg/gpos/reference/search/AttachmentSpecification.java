package com.gg.gpos.reference.search;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.gg.gpos.reference.dto.AttachmentDto;
import com.gg.gpos.reference.entity.Attachment;

@Component
public class AttachmentSpecification {
	
	public Specification<Attachment> search(final Integer restaurantCode,final String syncStatus){
		return (root, cq, cb) -> {
			List<Predicate> predicates = new ArrayList<>();
			if(restaurantCode != null) {
				predicates.add(cb.equal(root.get("restaurantCode"), restaurantCode));
			}
			if(StringUtils.isNotBlank(syncStatus)) {
				predicates.add(cb.equal(root.get("syncStatus"), syncStatus));
			}
			return cb.and(predicates.stream().toArray(Predicate[]::new));
		};
	}
}
