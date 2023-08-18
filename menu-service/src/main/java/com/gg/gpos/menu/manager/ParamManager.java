package com.gg.gpos.menu.manager;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.gg.gpos.common.constant.TypeRestaurantDataEditEnum;
import com.gg.gpos.menu.dto.GroupParamDto;
import com.gg.gpos.menu.dto.ParamDto;
import com.gg.gpos.menu.entity.CatalogDataEdit;
import com.gg.gpos.menu.entity.GroupParam;
import com.gg.gpos.menu.entity.Param;
import com.gg.gpos.menu.entity.RestaurantDataEdit;
import com.gg.gpos.menu.mapper.GroupParamMapper;
import com.gg.gpos.menu.mapper.ParamMapper;
import com.gg.gpos.menu.repository.CatalogDataEditRepository;
import com.gg.gpos.menu.repository.GroupParamRepository;
import com.gg.gpos.menu.repository.ParamRepository;
import com.gg.gpos.menu.repository.RestaurantDataEditRepository;
import com.gg.gpos.menu.specification.ParamSpecification;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class ParamManager {
	@Autowired
	private ParamRepository paramRepository;
	@Autowired
	private ParamMapper paramMapper;
	@Autowired
	private ParamSpecification paramSpecification;
	@Autowired
	private GroupParamRepository groupParamRepository;
	@Autowired
	private CatalogDataEditRepository catalogDataEditRepository;
	@Autowired
	private RestaurantDataEditRepository restaurantDataEditRepository;
	@Autowired
	private GroupParamMapper groupParamMapper;

	public ParamDto get(Long id) {
		log.debug("PROCESS: GET PARAM_DTO BY ID, PARAM_ID: {}", id);
		return paramRepository.findById(id).map(paramMapper::entityToDto).orElse(null);
	}
	
	public List<ParamDto> gets(){
		return paramRepository.findAll().stream().map(paramMapper::entityToDto).collect(Collectors.toList());
	}
	
	/*
	 * Ghi lại thông tin param đã chỉnh sửa để phục vụ cho việc đồng bộ update thông tin xuống nhà hàng (Từ danh mục xuống nhà hàng)
	 */
	private void saveCatalogDataEdit(Long catalogId,String value,String type) {
		log.debug("PROCESS: SAVE CATALOG_DATA_EDIT, CATALOG_ID: {}, TYPE: {}, VALUE: {}", catalogId, type, value);
		catalogDataEditRepository.deleteByCatalogIdAndTypeAndValue(catalogId,type,value);
		CatalogDataEdit catalogDataEdit = catalogDataEditRepository.findByCatalogIdAndTypeAndValue(catalogId,type,value);
		if(catalogDataEdit == null) {
			catalogDataEdit = new CatalogDataEdit();
		}
		catalogDataEdit.setValue(value);
		catalogDataEdit.setCatalogId(catalogId);
		catalogDataEdit.setType(type);
		catalogDataEditRepository.save(catalogDataEdit);
	}
	
	/*
	 * Ghi lại thông tin param đã chỉnh sửa để phục vụ cho việc đồng bộ update thông tin về server nhà hàng (Gửi từ GPOS xuống SERVER nhà hàng)
	 */
	private void saveRestaurantDataEdit(String value, String type,Integer resCode) {
		log.debug("PROCESS: SAVE RESTAURANT_DATA_EDIT, RESTAURANT_CODE: {}, TYPE: {}, VALUE: {}", resCode, type, value);
		restaurantDataEditRepository.deleteByValueAndRestaurantCodeAndType(value, resCode, type);
		RestaurantDataEdit restaurantDataEdit = restaurantDataEditRepository.findByValueAndRestaurantCodeAndType(value, resCode, type);
		if(restaurantDataEdit == null) {
			restaurantDataEdit = new RestaurantDataEdit();
		}
		restaurantDataEdit.setValue(value);
		restaurantDataEdit.setType(type);
		restaurantDataEdit.setRestaurantCode(resCode);
		restaurantDataEditRepository.save(restaurantDataEdit);
	}
	
	public ParamDto save(ParamDto paramDto) {
		log.debug("PROCESS: SAVE PARAM, PARAM_DTO: {}", paramDto);
		Param param = Optional.ofNullable(paramDto).map(paramMapper::dtoToEntity).orElse(null);
		if(param != null) {
			ParamDto newParamDto = Optional.ofNullable(paramRepository.save(param)).map(paramMapper::entityToDto).orElse(null);
			Long paramId = newParamDto.getId();
			// trường hợp mà chỉnh sửa param ở dưới danh sách nhà hàng thì cần lưu lại thông tin param đã chỉnh sửa để phục vụ cho việc đồng bộ update thông tin về server nhà hàng (Gửi từ GPOS xuống SERVER nhà hàng)
			if(newParamDto.getRestaurantCode() != null) {
				saveRestaurantDataEdit(paramId.toString(),TypeRestaurantDataEditEnum.PARAM.val, param.getRestaurantCode());
			} else {
				// Set lại trường ngày chỉnh sửa để hiển thị phần danh mục Group-Param theo thời gian chỉnh sửa
				GroupParamDto groupParamDto = groupParamRepository.findById(newParamDto.getGroupParam().getId()).map(groupParamMapper::entityToDto).orElse(null);
				groupParamDto.setModifiedDate(LocalDateTime.now());
				GroupParam groupParam = Optional.ofNullable(groupParamDto).map(groupParamMapper::dtoToEntity).orElse(null);
				groupParamRepository.save(groupParam);
				// trường hợp mà chỉnh sửa param ở danh mục thì cần lưu lại thông tin param đã chỉnh sửa để phục vụ cho việc đồng bộ update thông tin xuống nhà hàng (Từ danh mục xuống nhà hàng)
		    	saveCatalogDataEdit(groupParamDto.getId(), paramId.toString(), TypeRestaurantDataEditEnum.PARAM.val);
			}
			return newParamDto;
		} else {
			return null;
		}
	}
	
	public Page<ParamDto> gets(ParamDto criteria){
		log.debug("PROCESS: GETS PARAM, PARAM_DTO: {}", criteria);
		Page<Param> page = paramRepository.findAll(paramSpecification.filter(criteria), PageRequest.of(criteria.getPage(), criteria.getSize()));
		return new PageImpl<>(page.getContent().stream().map(paramMapper::entityToDto).collect(Collectors.toList()),PageRequest.of(criteria.getPage(), criteria.getSize()), page.getTotalElements());
	}
	
	public Page<ParamDto> getByResCode(ParamDto criteria){
		Page<Param> page = paramRepository.findByRestaurantCode(criteria.getRestaurantCode(), PageRequest.of(criteria.getPage(), criteria.getSize()));
		return new PageImpl<>(page.getContent().stream().map(paramMapper::entityToDto).collect(Collectors.toList()),PageRequest.of(criteria.getPage(),criteria.getSize()),page.getTotalElements());
	}
	
	public void delete(List<Param> params) {
		params.stream().forEach(f -> paramRepository.delete(f));
	}
	
	public List<ParamDto> getByResCode(Integer resCode) {
		return paramRepository.findByRestaurantCode(resCode).stream().map(paramMapper::entityToDto).collect(Collectors.toList());
	}
	
	public List<ParamDto> gets(Integer resCode){
		log.debug("Entering 'gets(resCode)' method...");
		return paramRepository.findByRestaurantCodeAndGroupParamIsNotNull(resCode).stream().map(paramMapper::entityToDto).collect(Collectors.toList());
	}
}
