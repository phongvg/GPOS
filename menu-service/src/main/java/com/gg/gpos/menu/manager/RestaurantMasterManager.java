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
import com.gg.gpos.menu.dto.RestaurantMasterDto;
import com.gg.gpos.menu.entity.Device;
import com.gg.gpos.menu.entity.RestaurantMaster;
import com.gg.gpos.menu.entity.TableKitchen;
import com.gg.gpos.menu.mapper.RestaurantMasterMapper;
import com.gg.gpos.menu.repository.DeviceRepository;
import com.gg.gpos.menu.repository.RestaurantMasterRepository;
import com.gg.gpos.menu.repository.TableKitchenRepository;
import com.gg.gpos.menu.specification.ReferenceSpecification;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class RestaurantMasterManager {
	@Autowired
	private RestaurantMasterMapper restaurantMasterMapper;
	@Autowired
	private RestaurantMasterRepository restaurantMasterRepository;
	@Autowired
	private ReferenceSpecification<RestaurantMaster> referenceSpecification;
	@Autowired
	private DeviceRepository deviceRepository;
	@Autowired
	private TableKitchenRepository tableKitchenRepository;

	public Page<RestaurantMasterDto> gets(RestaurantMasterDto criteria) {
		log.debug("PROCESS: GETS RESTAURANT_MASTER, RESTAURANT_MASTER_DTO: {}", criteria);
		Specification<RestaurantMaster> spec = Specification.where(referenceSpecification.search(criteria.getCode(), criteria.getName(), criteria.getStatus()));
		Page<RestaurantMaster> page = restaurantMasterRepository.findAll(spec, PageRequest.of(criteria.getPage(), criteria.getSize()));
		return new PageImpl<>(page.getContent().stream().map(restaurantMasterMapper::entityToDto).collect(Collectors.toList()), PageRequest.of(criteria.getPage(), criteria.getSize()), page.getTotalElements());
	}
	
	public void save(List<RestaurantMasterDto> restaurantMasterDtos) {
		log.debug("PROCESS: SAVE RESTAURANT_MASTERS, RESTAURANT_MASTER_DTOS: {}", restaurantMasterDtos);
		if (!restaurantMasterDtos.isEmpty()) {
			restaurantMasterDtos.stream().forEach(f -> {
				RestaurantMaster restaurantMaster = Optional.ofNullable(f).map(restaurantMasterMapper::dtoToEntity).orElse(null);
				if (restaurantMaster != null) {
					restaurantMasterRepository.save(restaurantMaster);
				}
			});
		}
	}

	public RestaurantMasterDto getByTransceiverDeviceIdAndCallingDeviceId(Long transceiverDeviceId, Long callingDeviceId) {
		RestaurantMasterDto restaurantMasterDto;
		
		Device transceiverDevice = deviceRepository.findByDeviceIdAndTableKitchenIdIsNull(transceiverDeviceId);
		Device callingDevice = deviceRepository.findByDeviceIdAndAndTableKitchenIdIsNotNull(callingDeviceId);
		if(transceiverDevice != null && callingDevice != null) {
			if(transceiverDevice.getRestaurantCode().equals(callingDevice.getRestaurantCode())) {
				TableKitchen tableKitchen = tableKitchenRepository.getOne(callingDevice.getTableKitchenId());
				restaurantMasterDto = Optional.ofNullable(restaurantMasterRepository.findByCode(transceiverDevice.getRestaurantCode().toString())).map(restaurantMasterMapper::entityToDto).orElse(null);
				if(restaurantMasterDto != null) {
					restaurantMasterDto.setTableKitchenCode(tableKitchen.getCode());
					restaurantMasterDto.setTableKitchenId(tableKitchen.getId());
				}else {
					restaurantMasterDto = null;
				}
			
			}else {
				restaurantMasterDto = null;
			}
		}else {
			restaurantMasterDto = null;
		}
		
		return restaurantMasterDto;
	}
	
	public RestaurantMasterDto get(String code) {
		return Optional.ofNullable(restaurantMasterRepository.findByCode(code)).map(restaurantMasterMapper::entityToDto).orElse(null);
	}
	
}
