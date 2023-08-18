package com.gg.gpos.menu.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import com.gg.gpos.common.constant.ErrorImportEnum;
import com.gg.gpos.io.dto.IOKdsConfigCookingDto;
import com.gg.gpos.menu.dto.FoodItemDto;
import com.gg.gpos.menu.dto.KdsConfigCookingDto;
import com.gg.gpos.menu.dto.KdsPlaceDto;
import com.gg.gpos.menu.entity.KdsConfigCooking;
import com.gg.gpos.menu.mapper.FoodItemMapper;
import com.gg.gpos.menu.mapper.KdsConfigCookingMapper;
import com.gg.gpos.menu.mapper.KdsPlaceMapper;
import com.gg.gpos.menu.repository.FoodItemRepository;
import com.gg.gpos.menu.repository.KdsConfigCookingRepository;
import com.gg.gpos.menu.repository.KdsPlaceRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class KdsConfigCookingManager {
	@Autowired
	private KdsConfigCookingRepository kdsConfigCookingRepository;
	@Autowired
	private KdsConfigCookingMapper configCookingMapper;
	@Autowired
	private FoodItemRepository foodItemRepository;
	@Autowired
	private FoodItemMapper foodItemMapper;
	@Autowired
	private KdsPlaceRepository kdsPlaceRepository;
	@Autowired
	private KdsPlaceMapper kdsPlaceMapper;
	
	
	public Page<KdsConfigCookingDto> gets(KdsConfigCookingDto criteria){
		log.debug("Entering 'gets(criteria)' method...");
		Page<KdsConfigCooking> page = kdsConfigCookingRepository.findByRestaurantCode(criteria.getRestaurantCode(), PageRequest.of(criteria.getPage(), criteria.getSize()));
		return new PageImpl<>(page.getContent().stream().map(configCookingMapper::entityToDto).collect(Collectors.toList()),PageRequest.of(criteria.getPage(), criteria.getSize()), page.getTotalElements());
	}
	
	public KdsConfigCookingDto get(Long id) {
		return kdsConfigCookingRepository.findById(id).map(configCookingMapper::entityToDto).orElse(null);
	}
	
	public List<KdsConfigCookingDto> gets(Integer resCode) {
		return kdsConfigCookingRepository.findByRestaurantCode(resCode).stream().map(configCookingMapper::entityToDto).collect(Collectors.toList());
	}
	
	public FoodItemDto getFoodItem(Long id) {
		FoodItemDto foodItemDto = new FoodItemDto();
		KdsConfigCookingDto kdsConfigCookingDto =  kdsConfigCookingRepository.findById(id).map(configCookingMapper::entityToDto).orElse(null);
		if(kdsConfigCookingDto != null) {
			foodItemDto = kdsConfigCookingDto.getFoodItem();
		} 
		return foodItemDto;
	}
	
	public KdsConfigCookingDto save(KdsConfigCookingDto kdsConfigCookingDto) {
		log.debug("Entering 'save' method...");
		KdsConfigCooking kdsConfigCooking = Optional.ofNullable(kdsConfigCookingDto).map(configCookingMapper::dtoToEntity).orElse(null);
		if (kdsConfigCooking != null) {
			return Optional.ofNullable(kdsConfigCookingRepository.save(kdsConfigCooking)).map(configCookingMapper::entityToDto).orElse(null);
		} else {
			return null;
		}
	}
	
	/*
	 * lấy dữ liệu KDS_CONFIG_COOKING theo RESTAURANT_CODE để export
	 */
	public List<IOKdsConfigCookingDto> getIOKdsConfigCookingsByRestaurantCode (Integer restaurantCode) {
		log.debug("PROCESS: GET IO_KDS_CONFIG_COOKING BY RESTAURANT_CODE, RESTAURANT_CODE: {}", restaurantCode);
		List<IOKdsConfigCookingDto> ioKdsConfigCookingDtos = new ArrayList<>();
		List<KdsConfigCookingDto> kdsConfigCookingDtos = kdsConfigCookingRepository.findByRestaurantCode(restaurantCode).stream().map(configCookingMapper::entityToDto).collect(Collectors.toList());
		if(!CollectionUtils.isEmpty(kdsConfigCookingDtos)) {
			kdsConfigCookingDtos.stream().forEach(item ->{
				IOKdsConfigCookingDto ioKdsConfigCookingDto = new IOKdsConfigCookingDto();
				BeanUtils.copyProperties(item, ioKdsConfigCookingDto);
				FoodItemDto foodItemDto = item.getFoodItem();
				ioKdsConfigCookingDto.setStatus(true);
				ioKdsConfigCookingDto.setFoodItemCode(foodItemDto.getCode());
				ioKdsConfigCookingDto.setFoodItemName(foodItemDto.getName());
				ioKdsConfigCookingDto.setSingleCookingTime(new Double(item.getSingleCookingTime()));
				ioKdsConfigCookingDto.setMultiCookingTime(new Double(item.getMultiCookingTime()));
				ioKdsConfigCookingDto.setKdsPlaceCode(item.getKdsPlace().getCode());
				ioKdsConfigCookingDtos.add(ioKdsConfigCookingDto);
			});
		}
		return ioKdsConfigCookingDtos;
	}
	
	public List<IOKdsConfigCookingDto> importKdsConfigCooking (List<IOKdsConfigCookingDto> ioKdsConfigCookingDtos, Integer restaurantCode) {
		log.debug("PROCESS: IMPORT KDS_CONFIG_COOKING, IO_KDS_CONFIG_COOKING: {}, RESTAURANT_CODE: {}",ioKdsConfigCookingDtos, restaurantCode);
		List<IOKdsConfigCookingDto> ioKdsConfigCookingErrs = new ArrayList<>();
		if(!CollectionUtils.isEmpty(ioKdsConfigCookingDtos)) {
			ioKdsConfigCookingDtos.stream().forEach(item ->{
				// Loại bỏ các bản ghi bị lỗi không đọc được từ file excel
				if(item.getError() != null && !item.getError().isEmpty()) {
					item.setStatus(false);
					ioKdsConfigCookingErrs.add(item);
				} else {
				// Các trường hợp không bị lỗi khi đọc file excel
					try {
						
						String foodItemCode = item.getFoodItemCode();
						String kdsPlaceCode = item.getKdsPlaceCode();
						// Kiểm tra xem FoodItemCode có giá trị không và Kiểm tra xem KdsPlaceCode có giá trị không
						if(StringUtils.isNotBlank(foodItemCode) && StringUtils.isNotBlank(kdsPlaceCode)) {
							FoodItemDto foodItemExistingDto = Optional.ofNullable(foodItemRepository.findByCode(foodItemCode)).map(foodItemMapper::entityToDto).orElse(null);
							// Kiểm tra xem có bản ghi nào tồn tại với mã FoodItemCode và status = 3 không
							if(foodItemExistingDto != null) {
								KdsPlaceDto kdsPlaceExistingDto = Optional.ofNullable(kdsPlaceRepository.findByCodeAndRestaurantCode(kdsPlaceCode, restaurantCode)).map(kdsPlaceMapper::entityToDto).orElse(null);
								// Kiểm tra xem có bản ghi nào tồn tại với mã KdsPlaceCode và RestaurantCode không
								if(kdsPlaceExistingDto != null) {
									// Kiểm tra xem có bản ghi KdsConfigCooking nào tồn tại với mã KdsPlaceCode và FoodItemCode không
									KdsConfigCookingDto kdsConfigCookingExistingDto = Optional.ofNullable(kdsConfigCookingRepository.findByFoodItem_CodeAndKdsPlace_Code(foodItemCode, kdsPlaceCode)).map(configCookingMapper::entityToDto).orElse(null);
									if(kdsConfigCookingExistingDto == null) {
										kdsConfigCookingExistingDto = new KdsConfigCookingDto();
									}
									BeanUtils.copyProperties(item, kdsConfigCookingExistingDto);
									kdsConfigCookingExistingDto.setFoodItem(foodItemExistingDto);
									kdsConfigCookingExistingDto.setKdsPlace(kdsPlaceExistingDto);
									if(item.getMultiCookingTime() != null) {
										kdsConfigCookingExistingDto.setMultiCookingTime((int) Math.round(item.getMultiCookingTime()));
									}
									if(item.getSingleCookingTime() != null) {
										kdsConfigCookingExistingDto.setSingleCookingTime((int) Math.round(item.getSingleCookingTime()));
									}
									KdsConfigCooking kdsConfigCooking = Optional.ofNullable(kdsConfigCookingExistingDto).map(configCookingMapper::dtoToEntity).orElse(null);
									kdsConfigCookingRepository.save(kdsConfigCooking);
								} else {
									item.setStatus(false);
									item.setError(ErrorImportEnum.ERROR_KDS_PLACE_NOT_EXIST.val);
									ioKdsConfigCookingErrs.add(item);
								}
							} else {
								item.setStatus(false);
								item.setError(ErrorImportEnum.ERROR_FOODITEM_NOT_EXIST.val);
								ioKdsConfigCookingErrs.add(item);
							}
						} else {
							item.setStatus(false);
							item.setError(ErrorImportEnum.ERROR_FOODITEM_NOT_EXIST_OR_ERROR_FOODITEM_NOT_EXIST.val);
							ioKdsConfigCookingErrs.add(item);
						}
					} catch (Exception e) {
						log.error("ERROR: IMPORT KDS_CONFIG_COOKING EXCEPTION, EXCEPTION: {}", e);
						item.setError(e.toString());
						item.setStatus(false);
						ioKdsConfigCookingErrs.add(item);
					}
				}
			});
		}
		return ioKdsConfigCookingErrs;
	}
}
