package com.gg.gpos.menu.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gg.gpos.common.constant.TypeRestaurantDataEditEnum;
import com.gg.gpos.menu.dto.CatalogDataEditDto;
import com.gg.gpos.menu.entity.CatalogDataEdit;
import com.gg.gpos.menu.mapper.CatalogDataEditMapper;
import com.gg.gpos.menu.repository.CatalogDataEditRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class CatalogDataEditManager {
	@Autowired
	private CatalogDataEditRepository catalogDataEditRepository;
	@Autowired
	private CatalogDataEditMapper catalogDataEditMapper;
	
	public void save(String type, Long catalogId, String value){
		log.debug("Entering 'save(String type, Long catalogId, String value)' method...");
		catalogDataEditRepository.deleteByCatalogIdAndTypeAndValue(catalogId,type, value);
		CatalogDataEdit catalogDataEdit = catalogDataEditRepository.findByCatalogIdAndTypeAndValue(catalogId,type, value);
		if(catalogDataEdit == null) {
			catalogDataEdit = new CatalogDataEdit();
		}
		catalogDataEdit.setCatalogId(catalogId);
		catalogDataEdit.setValue(value);
		catalogDataEdit.setType(type);
		catalogDataEditRepository.save(catalogDataEdit);
	}

	
	public CatalogDataEditDto save(CatalogDataEditDto catalogDataEditDto) {
		log.debug("Entering save(CatalogDataEditDto catalogDataEditDto) method...");
		CatalogDataEdit catalogDataEdit = Optional.ofNullable(catalogDataEditDto).map(catalogDataEditMapper::dtoToEntity).orElse(null);
		if (catalogDataEdit != null) {
			return Optional.ofNullable(catalogDataEditRepository.save(catalogDataEdit)).map(catalogDataEditMapper::entityToDto).orElse(null);
		} else {
			return null;
		}
	}

	public List<CatalogDataEditDto> getByTypeAndCatalogId(Long catalogId, String type) {
		log.debug("PROCESS FUNCTION: GET BY CATALOG_ID AND TYPE, CATALOG_ID: {}, TYPE: {}", catalogId, type);
		List<CatalogDataEditDto> catalogDataEditDtos = new ArrayList<>();
		try {
			switch (type) {
			case "CO_MENU":
				catalogDataEditDtos.addAll(catalogDataEditRepository.findByTypeAndCatalogId(TypeRestaurantDataEditEnum.CO_CATEGORY.val, catalogId).stream().map(catalogDataEditMapper::entityToDto).collect(Collectors.toList()));
				catalogDataEditDtos.addAll(catalogDataEditRepository.findByTypeAndCatalogId(TypeRestaurantDataEditEnum.DELETE_CO_CATEGORY.val, catalogId).stream().map(catalogDataEditMapper::entityToDto).collect(Collectors.toList()));
				catalogDataEditDtos.addAll(catalogDataEditRepository.findByTypeAndCatalogId(TypeRestaurantDataEditEnum.CO_FOODITEM.val, catalogId).stream().map(catalogDataEditMapper::entityToDto).collect(Collectors.toList()));
				catalogDataEditDtos.addAll(catalogDataEditRepository.findByTypeAndCatalogId(TypeRestaurantDataEditEnum.DELETE_CO_FOODITEM.val, catalogId).stream().map(catalogDataEditMapper::entityToDto).collect(Collectors.toList()));
				// xóa dữ liệu
				catalogDataEditRepository.deleteByCatalogIdAndType(catalogId, TypeRestaurantDataEditEnum.CO_CATEGORY.val);
				catalogDataEditRepository.deleteByCatalogIdAndType(catalogId, TypeRestaurantDataEditEnum.DELETE_CO_CATEGORY.val);
				catalogDataEditRepository.deleteByCatalogIdAndType(catalogId, TypeRestaurantDataEditEnum.CO_FOODITEM.val);
				catalogDataEditRepository.deleteByCatalogIdAndType(catalogId, TypeRestaurantDataEditEnum.DELETE_CO_FOODITEM.val);
				break;
			case "SO_MENU":
				catalogDataEditDtos.addAll(catalogDataEditRepository.findByTypeAndCatalogId(TypeRestaurantDataEditEnum.SO_CATEGORY.val, catalogId).stream().map(catalogDataEditMapper::entityToDto).collect(Collectors.toList()));
				catalogDataEditDtos.addAll(catalogDataEditRepository.findByTypeAndCatalogId(TypeRestaurantDataEditEnum.DELETE_SO_CATEGORY.val, catalogId).stream().map(catalogDataEditMapper::entityToDto).collect(Collectors.toList()));
				
				catalogDataEditRepository.deleteByCatalogIdAndType(catalogId, TypeRestaurantDataEditEnum.SO_CATEGORY.val);
				catalogDataEditRepository.deleteByCatalogIdAndType(catalogId, TypeRestaurantDataEditEnum.DELETE_SO_CATEGORY.val);
				break;
			default:
				catalogDataEditDtos.addAll(catalogDataEditRepository.findByTypeAndCatalogId(TypeRestaurantDataEditEnum.PARAM.val, catalogId).stream().map(catalogDataEditMapper::entityToDto).collect(Collectors.toList()));
				
				catalogDataEditRepository.deleteByCatalogIdAndType(catalogId, TypeRestaurantDataEditEnum.PARAM.val);
				break;
			}
		} catch (Exception e) {
			log.debug("ERROR PROCESS FUNCTION: GET BY CATALOG_ID AND TYPE EXCEPTION: {}", e);
		}
		return catalogDataEditDtos;
	}
}
