package com.gg.gpos.menu.manager;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.gg.gpos.menu.dto.OrderVoidDto;
import com.gg.gpos.menu.entity.OrderVoid;
import com.gg.gpos.menu.mapper.OrderVoidMapper;
import com.gg.gpos.menu.repository.OrderVoidRepository;
import com.gg.gpos.menu.specification.ReferenceObjectSpecification;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class OrderVoidManager {
	@Autowired
	private OrderVoidRepository orderVoidRepository;
	@Autowired
	private OrderVoidMapper orderVoidMapper;
	@Autowired
	private ReferenceObjectSpecification<OrderVoid> referenceObjectSpecification;
	
	public OrderVoidDto get(Long id) {
		return orderVoidRepository.findById(id).map(orderVoidMapper::entityToDto).orElse(null);
	}
	
	public void save(List<OrderVoidDto> orderVoidDtos) {
		log.debug("PROCESS: SAVE ORDER_VOIDS, ORDER_VOID_DTOS: {}", orderVoidDtos);
		if (!orderVoidDtos.isEmpty()) {
			orderVoidDtos.stream().forEach(f -> {
				OrderVoid orderVoid = Optional.ofNullable(f).map(orderVoidMapper::dtoToEntity).orElse(null);
				if (orderVoid != null) {
					orderVoidRepository.save(orderVoid);
				}
			});
		}
	}
	
	public Page<OrderVoidDto> gets(OrderVoidDto criteria){
		log.debug("PROCESS: GETS ORDER_VOID, ORDER_VOID_DTO: {}", criteria);
		Specification<OrderVoid> spec = Specification.where(referenceObjectSpecification.search(criteria.getCode(), criteria.getName(), criteria.getStatus()));
		Page<OrderVoid> page = orderVoidRepository.findAll(spec, PageRequest.of(criteria.getPage(), criteria.getSize()));
		return new PageImpl<>(page.getContent().stream().map(orderVoidMapper::entityToDto).collect(Collectors.toList()),PageRequest.of(criteria.getPage(), criteria.getSize()), page.getTotalElements());
	}
}
