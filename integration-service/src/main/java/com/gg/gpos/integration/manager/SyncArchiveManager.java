package com.gg.gpos.integration.manager;

import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gg.gpos.integration.dto.SyncArchiveDto;
import com.gg.gpos.integration.entity.SyncArchive;
import com.gg.gpos.integration.mapper.SyncArchiveMapper;
import com.gg.gpos.integration.repository.SyncArchiveRepository;
import com.gg.gpos.integration.specification.SyncArchiveSpecification;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class SyncArchiveManager {
	@Autowired
	private SyncArchiveRepository syncArchiveRepository;
	@Autowired
	private SyncArchiveMapper syncArchiveMapper;
	@Autowired
	private SyncArchiveSpecification syncArchiveSpecification;
	
	
	public Page<SyncArchiveDto> gets(SyncArchiveDto criteria){
		log.debug("PROCESS: GETS SYNC_ARRCHIVE, SYNC_ARRCHIVE_DTO: {}", criteria);
		Page<SyncArchive> page = syncArchiveRepository.findAll(syncArchiveSpecification.filter(criteria),PageRequest.of(criteria.getPage(), criteria.getSize(),Sort.by(Sort.Direction.DESC, "createdDate")));
		return new PageImpl<>(page.getContent().stream().map(syncArchiveMapper::entityToDto).collect(Collectors.toList()),PageRequest.of(criteria.getPage(), criteria.getSize()), page.getTotalElements());
	}
	
	
}
