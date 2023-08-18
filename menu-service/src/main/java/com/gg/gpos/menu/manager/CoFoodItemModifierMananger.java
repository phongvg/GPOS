package com.gg.gpos.menu.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gg.gpos.menu.dto.CoFoodItemDto;
import com.gg.gpos.menu.dto.CofoodItemModifierDto;
import com.gg.gpos.menu.entity.CoFoodItem;
import com.gg.gpos.menu.entity.CoFoodItemModifier;
import com.gg.gpos.menu.mapper.CoFoodItemMapper;
import com.gg.gpos.menu.mapper.CoFoodItemModifierMapper;
import com.gg.gpos.menu.repository.CoFoodItemModifierRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class CoFoodItemModifierMananger {
	@Autowired
	private CoFoodItemModifierRepository coFoodItemModifierRepository;
	@Autowired
	private CoFoodItemModifierMapper coFoodItemModifierMapper;
	@Autowired
	private CoFoodItemMapper coFoodItemMapper;
	
	public List<CofoodItemModifierDto> getByCoFoodItem (CoFoodItemDto coFoodItemDto) {
		CoFoodItem coFoodItem = Optional.ofNullable(coFoodItemDto).map(coFoodItemMapper::dtoToEntity).orElse(null);
		if(coFoodItem != null) {
			return coFoodItemModifierRepository.findByCoFoodItem(coFoodItem).stream().map(coFoodItemModifierMapper::entityToDto).collect(Collectors.toList());
		} else {
			return new ArrayList<>();
		}
	}
	
	public void deleteByCoFoodItem (CoFoodItemDto coFoodItemDto){
		CoFoodItem coFoodItem = Optional.ofNullable(coFoodItemDto).map(coFoodItemMapper::dtoToEntity).orElse(null);
		if(coFoodItem != null) {
			coFoodItemModifierRepository.deleteByCoFoodItem(coFoodItem);
		}
	}
	
	public void save (CofoodItemModifierDto cofoodItemModifierDto) {
		CoFoodItemModifier coFoodItemModifier = Optional.ofNullable(cofoodItemModifierDto).map(coFoodItemModifierMapper::dtoToEntity).orElse(null);
		if(coFoodItemModifier != null) {
			coFoodItemModifierRepository.save(coFoodItemModifier);
		}
	}
}
