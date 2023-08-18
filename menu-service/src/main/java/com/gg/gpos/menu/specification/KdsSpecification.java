package com.gg.gpos.menu.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import com.gg.gpos.menu.entity.Kds;

@Component
public class KdsSpecification {
	public Specification<Kds> codeLike(final String name) {
		return (root, cq, cb) -> cb.like(root.get("name"), "%" + name + "%");
	}
}
