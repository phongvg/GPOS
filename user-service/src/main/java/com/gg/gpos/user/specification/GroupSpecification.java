package com.gg.gpos.user.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.gg.gpos.user.entity.AppGroup;

@Component
public class GroupSpecification {
	public Specification<AppGroup> nameLike(final String name) {
		return (group, cq, cb) -> cb.like(group.get("name"), "%" + name + "%");
	}
}
