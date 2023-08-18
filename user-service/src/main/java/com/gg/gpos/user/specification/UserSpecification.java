package com.gg.gpos.user.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.gg.gpos.user.dto.AppUserDto;
import com.gg.gpos.user.entity.AppUser;

@Component
public class UserSpecification {
	public Specification<AppUser> filter(final AppUserDto criteria) {
		return (root, query, cb) -> {
			List<Predicate> predicates = new ArrayList<>();
			
        	if (StringUtils.isNotBlank(criteria.getUsername())) {
        		predicates.add(cb.like(root.get("username"), "%"+ criteria.getUsername().trim() + "%"));
        	}
        	return cb.and(predicates.stream().toArray(Predicate[]::new));
		};
	}


}
