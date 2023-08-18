package com.gg.gpos.user.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.gg.gpos.user.entity.Position;

@Component
public class PositionSpecification {
	public Specification<Position> nameLike(final String name){
		return (group,cq,cb) -> cb.like(group.get("name"), "%" + name  + "%");
	}
}
