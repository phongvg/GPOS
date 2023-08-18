package com.gg.gpos.menu.manager;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import com.gg.gpos.common.constant.TypeRestaurantDataEditEnum;
import com.gg.gpos.menu.dto.GroupParamDto;
import com.gg.gpos.menu.entity.CatalogApplyToRestaurant;
import com.gg.gpos.menu.entity.GroupParam;
import com.gg.gpos.menu.mapper.GroupParamMapper;
import com.gg.gpos.menu.repository.CatalogApplyToRestaurantRepository;
import com.gg.gpos.menu.repository.CatalogDataEditRepository;
import com.gg.gpos.menu.repository.GroupParamRepository;
import com.gg.gpos.menu.repository.ParamRepository;
import com.gg.gpos.menu.repository.RestaurantDataEditRepository;
import com.gg.gpos.menu.specification.GroupParamSpecification;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class GroupParamManager {
	@Autowired
	private GroupParamRepository groupParamRepository;
	@Autowired
	private GroupParamMapper groupParamMapper;
	@Autowired 
	private GroupParamSpecification groupParamSpecification;
	@Autowired
	private CatalogApplyToRestaurantRepository catalogApplyToRestaurantRepository;
	@Autowired
	private CatalogDataEditRepository catalogDataEditRepository;
	@Autowired
	private RestaurantDataEditRepository restaurantDataEditRepository;
	@Autowired
	private ParamRepository paramRepository;
	
	public GroupParamDto get(Long id) {
		log.debug("PROCESS: GET GROUP-PARAM_DTO BY ID, GROUP-PARAM_ID: {}", id);
		return groupParamRepository.findById(id).map(groupParamMapper::entityToDto).orElse(null);
	}
	
	public List<GroupParamDto> gets(){
		log.info("Entering 'gets()' method...");
		return groupParamRepository.findAll().stream().map(groupParamMapper::entityToDto).collect(Collectors.toList());
	}

	public GroupParamDto save(GroupParamDto groupParamDto) {
		log.debug("PROCESS: SAVE GROUP-PARAM, GROUP-PARAM_DTO: {}", groupParamDto);
		GroupParam groupParam = Optional.ofNullable(groupParamDto).map(groupParamMapper::dtoToEntity).orElse(null);
		if(groupParam != null) {
			// trường hợp status = false (Off danh mục)
			if(groupParam.getId() != null && !groupParam.isStatus()) {
				// Trường hợp danh mục Group-Param đã áp dụng xuống nhà hàng thì tìm kiếm các nhà hàng đã áp dụng danh mục để xóa các dữ liệu cần thiết
				List<Integer> restaurantCodeExistings = catalogApplyToRestaurantRepository.findByGroupParamId(groupParam.getId()).stream().map(ap -> ap.getRestaurantCode()).collect(Collectors.toList());
		        if(!CollectionUtils.isEmpty(restaurantCodeExistings)) {
		        	restaurantCodeExistings.stream().forEach(restaurantCode ->{
		        		// Xóa các bản ghi lưu lại thông tin mà nhà hàng đã chỉnh sửa (Các bản ghi này phục vụ cho việc đồng bộ update từ nhà hàng xuống Server của nhà hàng)
		        		restaurantDataEditRepository.deleteByRestaurantCodeAndType(restaurantCode,TypeRestaurantDataEditEnum.PARAM.val);
		        		// set lại các nhà hàng đánh đấu là không còn áp dụng danh mục Group-Param này nữa
		        		saveCatalogApplyToRestaurant(restaurantCode, null);
		        		// Xóa các bản ghi đã đươc đồng bộ xuống nhà hàng
		        		paramRepository.deleteByRestaurantCode(restaurantCode);
		        	});
		        	// Xóa các bản ghi đánh dấu bản ghi này đã chỉnh sửa ở danh mục để phục vụ cho việc đồng bộ update từ danh mục xuống nhà hàng
		        	catalogDataEditRepository.deleteByCatalogIdAndType(groupParam.getId(),TypeRestaurantDataEditEnum.PARAM.val);
		        }
			}
			return Optional.ofNullable(groupParamRepository.save(groupParam)).map(groupParamMapper::entityToDto).orElse(null);
		} else {
			return null;
		}
	}
	
	
	
	public Page<GroupParamDto> gets(GroupParamDto criteria){
		log.debug("PROCESS: GETS GROUP-PARAM, GROUP-PARAM_DTO: {}", criteria);
		Specification<GroupParam> spec = Specification.where(groupParamSpecification.filter(criteria));
		Page<GroupParam> page = groupParamRepository.findAll(spec,PageRequest.of(criteria.getPage(), criteria.getSize(), Sort.by(Sort.Direction.DESC, "modifiedDate")));
		return new PageImpl<>(page.getContent().stream().map(groupParamMapper::entityToDto).collect(Collectors.toList()),PageRequest.of(criteria.getPage(), criteria.getSize()),page.getTotalElements());
	}

	/*
	 * Lưu dữ liệu vào bảng đánh dấu các nhà hàng đã áp dụng danh mục nào
	 */
	private void saveCatalogApplyToRestaurant(Integer resCode, Long groupParamId) {
		log.debug("PROCESS: SAVE CATALOG-APPLY-TO-RESTAURANT BY RESTAURANT_CODE AND GROUP_PARAM_ID, RESTAURANT_CODE: {}, GROUP_PARAM_ID: {}", resCode, groupParamId);
		try {
			CatalogApplyToRestaurant applyToRestaurant = catalogApplyToRestaurantRepository.findByRestaurantCode(resCode);
			if(applyToRestaurant == null) {
				applyToRestaurant = new CatalogApplyToRestaurant();
			}
			applyToRestaurant.setRestaurantCode(resCode);
			applyToRestaurant.setGroupParamId(groupParamId);
			catalogApplyToRestaurantRepository.save(applyToRestaurant);
		} catch (Exception e) {
			log.error("ERROR SAVE CATALOG-APPLY-TO-RESTAURANT EXCEPTION: {}", e);
		}
	}
	
}
