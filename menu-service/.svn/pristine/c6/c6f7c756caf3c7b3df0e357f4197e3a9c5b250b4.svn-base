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
import com.gg.gpos.menu.dto.ModiSchemeDto;
import com.gg.gpos.menu.entity.ModiScheme;
import com.gg.gpos.menu.mapper.ModiSchemeMapper;
import com.gg.gpos.menu.repository.ModiSchemeRepository;
import com.gg.gpos.menu.specification.ReferenceObjectSpecification;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class ModiSchemeManager {
	@Autowired
	private ModiSchemeRepository modiSchemeRepository;
	@Autowired
	private ModiSchemeMapper modiSchemeMapper;
	@Autowired
	private ReferenceObjectSpecification<ModiScheme> referenceObjectSpecification;
	
	public ModiSchemeDto get(Long id) {
		return modiSchemeRepository.findById(id).map(modiSchemeMapper::entityToDto).orElse(null);
	}
	
	public void save(List<ModiSchemeDto> modiSchemeDtos) {
		log.debug("PROCESS: SAVE MODI_SCHEMES, MODI_SCHEME_DTOS: {}", modiSchemeDtos);
		if (!modiSchemeDtos.isEmpty()) {
			modiSchemeDtos.stream().forEach(f -> {
				ModiScheme modiScheme = Optional.ofNullable(f).map(modiSchemeMapper::dtoToEntity).orElse(null);
				if (modiScheme != null) {
					modiSchemeRepository.save(modiScheme);
				}
			});
		}
	}
	
	public Page<ModiSchemeDto> gets(ModiSchemeDto criteria){
		log.debug("PROCESS: GETS MODI_SCHEME, MODI_SCHEME_DTO: {}", criteria);
		Specification<ModiScheme> spec = Specification.where(referenceObjectSpecification.search(criteria.getCode(), criteria.getName(), criteria.getStatus()));
		Page<ModiScheme> page = modiSchemeRepository.findAll(spec, PageRequest.of(criteria.getPage(), criteria.getSize()));
		return new PageImpl<>(page.getContent().stream().map(modiSchemeMapper::entityToDto).collect(Collectors.toList()),PageRequest.of(criteria.getPage(), criteria.getSize()), page.getTotalElements());
	}
}
