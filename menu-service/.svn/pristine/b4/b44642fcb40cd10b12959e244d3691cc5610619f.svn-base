package com.gg.gpos.menu.manager;

import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gg.gpos.menu.dto.CoCategoryDto;
import com.gg.gpos.menu.dto.CoFoodGroupDisplayDto;
import com.gg.gpos.menu.entity.CoCategory;
import com.gg.gpos.menu.entity.CoFoodGroupDisplay;
import com.gg.gpos.menu.mapper.CoCategoryMapper;
import com.gg.gpos.menu.mapper.CoFoodGroupDisplayMapper;
import com.gg.gpos.menu.repository.CoFoodGroupDisplayRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class CoFoodGroupDisplayManager {
	@Autowired
	private CoFoodGroupDisplayRepository coFoodGroupDisplayRepository;
	private CoFoodGroupDisplayMapper coFoodGroupDisplayMapper;
	
	@Autowired
	private CoCategoryMapper coCategoryMapper;
	@Autowired
	public void setCoFoodGroupDisplayMapper(CoFoodGroupDisplayMapper coFoodGroupDisplayMapper) {
		this.coFoodGroupDisplayMapper = coFoodGroupDisplayMapper;
	}	
	public CoFoodGroupDisplayDto save(CoFoodGroupDisplayDto coFoodGroupDisplayDto) {
		log.debug("Entering 'save' method...");
		CoFoodGroupDisplay coFoodGroupDisplay = Optional.ofNullable(coFoodGroupDisplayDto).map(coFoodGroupDisplayMapper::dtoToEntity).orElse(null);
		return Optional.ofNullable(coFoodGroupDisplayRepository.save(coFoodGroupDisplay)).map(coFoodGroupDisplayMapper::entityToDto).orElse(null);
	}
	public void deleteByCoCategory(CoCategoryDto coCategoryDto) {
		log.debug("Entering 'save' method...");
		CoCategory coCategory = Optional.ofNullable(coCategoryDto).map(coCategoryMapper :: dtoToEntity).orElse(null);
		if(coCategoryDto !=null) {
			coFoodGroupDisplayRepository.deleteByCoCategory(coCategory);
		}
		
	}
}
