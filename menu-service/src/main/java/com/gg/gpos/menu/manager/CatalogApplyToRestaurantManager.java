package com.gg.gpos.menu.manager;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gg.gpos.menu.dto.CatalogApplyToRestaurantDto;
import com.gg.gpos.menu.entity.CatalogApplyToRestaurant;
import com.gg.gpos.menu.mapper.CatalogApplyToRestaurantMapper;
import com.gg.gpos.menu.repository.CatalogApplyToRestaurantRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class CatalogApplyToRestaurantManager {
	@Autowired
	private CatalogApplyToRestaurantRepository catalogApplyToRestaurantRepository;
	@Autowired
	private CatalogApplyToRestaurantMapper catalogApplyToRestaurantMapper;
	
	public CatalogApplyToRestaurantDto getByResCode(Integer resCode){
		log.debug("entering 'getByResCode("+resCode+")' method...");
		CatalogApplyToRestaurant applyToRestaurant = catalogApplyToRestaurantRepository.findByRestaurantCode(resCode);
		if(applyToRestaurant != null) {
			return Optional.ofNullable(applyToRestaurant).map(catalogApplyToRestaurantMapper::entityToDto).orElse(null);
		} else {
			return null;
		}
	}
	
	public List<Integer> getRestaurantCodeByCoId(Long coId){
		log.debug("PROCESS: GET RESTAURANT_CODE BY CO_ID, CO_ID: {}", coId);
		return catalogApplyToRestaurantRepository.findByCoId(coId).stream().map(item -> item.getRestaurantCode()).collect(Collectors.toList());
	}
	
	public List<Integer> getRestaurantCodeBySoId(Long soId){
		log.debug("PROCESS: GET RESTAURANT_CODE BY SO_ID, SO_ID: {}", soId);
		return catalogApplyToRestaurantRepository.findBySoId(soId).stream().map(item -> item.getRestaurantCode()).collect(Collectors.toList());
	}
	
	public List<Integer> getRestaurantCodeByGroupParamId(Long gpId){
		log.debug("PROCESS: GET RESTAURANT_CODE BY GROUP_PARAM_ID, GROUP_PARAM_ID: {}", gpId);
		return catalogApplyToRestaurantRepository.findByGroupParamId(gpId).stream().map(item -> item.getRestaurantCode()).collect(Collectors.toList());
	}
}
