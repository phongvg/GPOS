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

import com.gg.gpos.menu.dto.GuestTypeDto;
import com.gg.gpos.menu.entity.GuestType;
import com.gg.gpos.menu.mapper.GuestTypeMapper;
import com.gg.gpos.menu.repository.GuestTypeRepository;
import com.gg.gpos.menu.specification.ReferenceSpecification;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class GuestTypeManager {
	@Autowired
	private GuestTypeMapper guestTypeMapper;
	@Autowired
	private GuestTypeRepository guestTypeRepository;
	@Autowired
	private ReferenceSpecification<GuestType> referenceSpecification;

	public List<GuestTypeDto> gets() {
		return guestTypeRepository.findAll().stream().map(guestTypeMapper::entityToDto).collect(Collectors.toList());
	}
	
	public Page<GuestTypeDto> gets(GuestTypeDto criteria) {
		log.debug("PROCESS: GETS GUEST_TYPE, GUEST_TYPE_DTO: {}", criteria);
		Specification<GuestType> spec = Specification.where(referenceSpecification.search(criteria.getCode(), criteria.getName(), criteria.getStatus()));
		Page<GuestType> page = guestTypeRepository.findAll(spec, PageRequest.of(criteria.getPage(), criteria.getSize()));
		return new PageImpl<>(page.getContent().stream().map(guestTypeMapper::entityToDto).collect(Collectors.toList()), PageRequest.of(criteria.getPage(), criteria.getSize()), page.getTotalElements());
	}
	
	public void save(List<GuestTypeDto> guestTypeDtos) {
		log.debug("PROCESS: SAVE GUEST_TYPES, GUEST_TYPE_DTOS: {}", guestTypeDtos);
		if (!guestTypeDtos.isEmpty()) {
			guestTypeDtos.stream().forEach(f -> {
				GuestType guestType = Optional.ofNullable(f).map(guestTypeMapper::dtoToEntity).orElse(null);
				if (guestType != null) {
					guestTypeRepository.save(guestType);
				}
			});
		}
	}
}
