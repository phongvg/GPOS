package com.gg.gpos.menu.manager;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import com.gg.gpos.common.constant.TypeRestaurantDataEditEnum;
import com.gg.gpos.menu.dto.GroupParamDto;
import com.gg.gpos.menu.dto.ParamDto;
import com.gg.gpos.menu.entity.CatalogApplyToRestaurant;
import com.gg.gpos.menu.entity.Param;
import com.gg.gpos.menu.entity.RestaurantDataEdit;
import com.gg.gpos.menu.mapper.ParamMapper;
import com.gg.gpos.menu.repository.CatalogApplyToRestaurantRepository;
import com.gg.gpos.menu.repository.ParamRepository;
import com.gg.gpos.menu.repository.RestaurantDataEditRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class SyncGroupParamManager {
	@Autowired
	private CatalogApplyToRestaurantRepository catalogApplyToRestaurantRepository;
	@Autowired
	private RestaurantDataEditRepository restaurantDataEditRepository;
	@Autowired
	private ParamRepository paramRepository;
	@Autowired
	private ParamMapper paramMapper;
	
	/*
	 * COPY dữ liệu từ nhà hàng này sang nhà hàng khác
	 */
	public void copyFromRestaurantToRestaurants(GroupParamDto groupParamDto) {
		log.debug("PROCESS: COPY GROUP_PARAM FROM RESTAURANT TO RESTAURANTS");
		try {
			List<Integer> resCodes = groupParamDto.getResCodes();
			Integer fromRestaurantCode = groupParamDto.getRestaurantCode();
	    	Boolean isOverride = groupParamDto.getOverride();
	    	log.debug("==========> LOG SYSTEM_PARAM: FROM_RESTAURANT_CODE: {}, IS_OVERRIDE: {}, RESTAURANT_CODE_APPLIES: {}", fromRestaurantCode, isOverride, resCodes);
	    	
	    	CatalogApplyToRestaurant applyToRestaurant = catalogApplyToRestaurantRepository.findByRestaurantCode(fromRestaurantCode);
	    	if(applyToRestaurant != null && applyToRestaurant.getGroupParamId() != null && !CollectionUtils.isEmpty(resCodes)) {
	    		Long groupParamId = applyToRestaurant.getGroupParamId();
	    		// Lấy danh sách PARAM ở dưới NHÀ HÀNG cấu hình
				List<ParamDto> paramDtoInRestaurants = paramRepository.findByRestaurantCode(fromRestaurantCode).stream().map(paramMapper::entityToDto).collect(Collectors.toList());
				
	    		// Trường hợp đồng bộ ghi đè
	    		if(isOverride) {
	    			resCodes.stream().forEach(toRestaurantCode -> {
						// COPY tất cả PARAM có trong danh mục GROUP_PARAM xuống nhà hàng || Không lưu thông tin PARAM_ID đánh dấu ITEM đó đã được chỉnh sửa
	    				cloneParamWithStatusOverride(paramDtoInRestaurants, toRestaurantCode, false);
						// Lưu thông tin xem danh mục GROUP_PARAM này đang áp dụng cho những nhà hàng nào
						saveCatalogApplyToRestaurant(toRestaurantCode,groupParamId);
		        	});
				} else {
					// Trường hợp đồng bộ update thì sẽ phân ra 2 TRƯỜNG HỢP
					
					// Lấy danh sách PARAM_ID đã được chỉnh sửa ở nhà hàng
					List<Long> paramEditIds = restaurantDataEditRepository.findByRestaurantCodeAndType(fromRestaurantCode, TypeRestaurantDataEditEnum.PARAM.val).stream().map(p -> Long.parseLong(p.getValue())).collect(Collectors.toList());
					// Lấy thông tin các bản ghi PARAM đã được chỉnh sửa ở NHÀ HÀNG
					List<ParamDto> paramEditDtoInRestaurants = paramRepository.findByIdIn(paramEditIds).stream().map(paramMapper::entityToDto).collect(Collectors.toList());
					// Lấy danh sách nhà hàng đang được áp dụng danh mục GROUP_PARAM mà nhà hàng này áp dụng
					List<Integer> restaurantCodeApplies = catalogApplyToRestaurantRepository.findByGroupParamId(groupParamId).stream().map(item -> item.getRestaurantCode()).collect(Collectors.toList());		
					
					// TRƯỜNG HỢP 1: Đã có nhà hàng áp dụng danh mục này
					if(!CollectionUtils.isEmpty(restaurantCodeApplies)) {
						resCodes.stream().forEach(toRestaurantCode ->{
							// Kiểm tra xem nếu code nhà hàng được chọn == code nhà hàng đang áp dụng thì clone các bản ghi được dánh dấu chỉnh sửa ở danh mục GROUP_PARAM
							if(restaurantCodeApplies.contains(toRestaurantCode)) {
								cloneParamWithStatusUpdate(paramEditDtoInRestaurants, toRestaurantCode);
							} else {
								// Trường hợp k trùng mã code thì áp dụng như đồng bộ GHI ĐÈ nhưng có lưu vào bảng đánh dấu PARAM này đã được chỉnh sửa dưới nhà hàng
								cloneParamWithStatusOverride(paramDtoInRestaurants, toRestaurantCode, true);
							}
							// Lưu thông tin xem danh mục GROUP_PARAM này đang áp dụng cho những nhà hàng nào
							saveCatalogApplyToRestaurant(toRestaurantCode,groupParamId);
						});
					} else {
						// TRƯỜNG HỢP 2: Chưa có nhà hàng áp dụng danh mục này thì áp dụng như đồng bộ GHI ĐÈ nhưng có lưu vào bảng đánh dấu PARAM này đã được chỉnh sửa dưới nhà hàng
						resCodes.stream().forEach(toRestaurantCode ->{
							// Áp dụng như đồng bộ GHI ĐÈ nhưng có lưu vào bảng đánh dấu PARAM này đã được chỉnh sửa dưới nhà hàng
							cloneParamWithStatusOverride(paramDtoInRestaurants, toRestaurantCode, true);
							// Lưu thông tin xem danh mục GROUP_PARAM này đang áp dụng cho những nhà hàng nào
							saveCatalogApplyToRestaurant(toRestaurantCode,groupParamId);
		            	});
					}
				}
	    	}
		} catch (Exception e) {
			log.debug("ERROR COPY GROUP_PARAM FROM RESTAURANT TO RESTAURANTS EXCEPTION: {}", e);
		}
	}
	
	/*
	 * Clone danh sách các PARAM có trong GROUP_PARAM xuống nhà hàng với trạng thái "GHI ĐÈ"
	 * Trường hợp chọn áp dụng UPDATE nhưng phía nhà hàng chưa áp dụng danh mục thì cũng được tính là GHI ĐÈ nhưng sẽ CÓ LƯU thông tin đánh dấu bản ghi này đã được update để phục vụ cho việc đồng bộ UPDATE xuống SERVER nhà hàng
	 */
	private void cloneParamWithStatusOverride(List<ParamDto> paramDtos, Integer restaurantCode, Boolean isSavedRestaurantDataEdit) {
		log.debug("PROCESS FUNCTION: CLONE PARAM WITH STATUS OVERRIDE BY RESTAURANT_CODE: {}, IS_SAVED_RESTAURANT_DATA_EDIT: {}", restaurantCode, isSavedRestaurantDataEdit);
		try {
			String type = TypeRestaurantDataEditEnum.PARAM.val;
			if(!CollectionUtils.isEmpty(paramDtos)) {
				// Xóa các dữ liệu param cũ ở dưới nhà hàng trước khi đồng bộ
				paramRepository.deleteByRestaurantCode(restaurantCode);
				// Xóa các dữ liệu lữu trữ thông tin các PARAM đã chỉnh sửa ở dưới nhà hàng (liên quan đến việc đồng bộ update xuống server nhà hàng)
				restaurantDataEditRepository.deleteByRestaurantCodeAndType(restaurantCode, type);
				// Tạo mới dữ liệu
				paramDtos.stream().forEach(item ->{
					ParamDto paramDto = new ParamDto();
					BeanUtils.copyProperties(item, paramDto);
					paramDto.setId(null);
					paramDto.setRestaurantCode(restaurantCode);
					Param param = Optional.ofNullable(paramDto).map(paramMapper::dtoToEntity).orElse(null);
					Param paramSaved = paramRepository.save(param);
					// Đánh dấu bản ghi này đã được update để phục vụ cho việc đồng bộ UPDATE xuống SERVER nhà hàng
					if(isSavedRestaurantDataEdit) {
						saveRestaurantDataEdit(paramSaved.getId().toString(), type, restaurantCode);
					}
				});
			}
		} catch (Exception e) {
			log.debug("ERROR PROCESS FUNCTION: CLONE PARAM WITH STATUS OVERRIDE EXCEPTION: {}", e);
		}
	}
	
	/*
	 * Clone danh sách các PARAM có trong GROUP_PARAM xuống nhà hàng với trạng thái "UPDATE"
	 */
	private void cloneParamWithStatusUpdate(List<ParamDto> paramDtos, Integer restaurantCode) {
		log.debug("PROCESS FUNCTION: CLONE PARAM WITH STATUS UPDATE BY RESTAURANT_CODE: {}", restaurantCode);
		try {
			String type = TypeRestaurantDataEditEnum.PARAM.val;
			if(!CollectionUtils.isEmpty(paramDtos)) {
				// Lấy các PARAM_CODE đã được chỉnh sửa ở phía nhà hàng. Không update các PARAM có CODE này
				List<String> paramCodeExistingInRestaurants = paramRepository.findByRestaurantCodeAndGroupParamIsNull(restaurantCode).stream().map(p -> p.getParamCode()).collect(Collectors.toList());
				// Tạo mới dữ liệu
				paramDtos.stream().forEach(item ->{
					if(!paramCodeExistingInRestaurants.contains(item.getParamCode())) {
						paramRepository.deleteByRestaurantCodeAndParamCodeAndGroupParamIsNotNull(restaurantCode, item.getParamCode());
						ParamDto paramDto = new ParamDto();
						BeanUtils.copyProperties(item, paramDto);
						paramDto.setId(null);
						paramDto.setRestaurantCode(restaurantCode);
						Param param = Optional.ofNullable(paramDto).map(paramMapper::dtoToEntity).orElse(null);
						Param paramSaved = paramRepository.save(param);
						// Đánh dấu bản ghi này đã được update để phục vụ cho việc đồng bộ UPDATE xuống SERVER nhà hàng
						saveRestaurantDataEdit(paramSaved.getId().toString(), type, restaurantCode);
					}
				});
			}
		} catch (Exception e) {
			log.debug("ERROR PROCESS FUNCTION: CLONE PARAM WITH STATUS UPDATE EXCEPTION: {}", e);
		}
	}
	
	/*
	 * Ghi lại thông tin param đã chỉnh sửa để phục vụ cho việc đồng bộ update thông tin về server nhà hàng (Gửi từ GPOS xuống SERVER nhà hàng)
	 */
	private void saveRestaurantDataEdit(String value, String type,Integer resCode) {
		log.debug("PROCESS FUNCTION: SAVE RESTAURANT_DATA_EDIT, RESTAURANT_CODE: {}, TYPE: {}, VALUE: {}", resCode, type, value);
		try {
			restaurantDataEditRepository.deleteByValueAndRestaurantCodeAndType(value, resCode, type);
			RestaurantDataEdit restaurantDataEdit = restaurantDataEditRepository.findByValueAndRestaurantCodeAndType(value, resCode, type);
			if(restaurantDataEdit == null) {
				restaurantDataEdit = new RestaurantDataEdit();
			}
			restaurantDataEdit.setValue(value);
			restaurantDataEdit.setType(type);
			restaurantDataEdit.setRestaurantCode(resCode);
			restaurantDataEditRepository.save(restaurantDataEdit);
		} catch (Exception e) {
			log.debug("ERROR PROCESS FUNCTION: SAVE RESTAURANT_DATA_EDIT, RESTAURANT_CODE: {}, TYPE: {}, VALUE: {}", resCode, type, value);
		}
	}

	/*
	 * Lưu dữ liệu vào bảng đánh dấu các nhà hàng đã áp dụng danh mục nào
	 */
	private void saveCatalogApplyToRestaurant(Integer resCode, Long groupParamId) {
		log.debug("PROCESS FUNCTION: SAVE CATALOG-APPLY-TO-RESTAURANT BY RESTAURANT_CODE AND GROUP_PARAM_ID, RESTAURANT_CODE: {}, GROUP_PARAM_ID: {}", resCode, groupParamId);
		try {
			CatalogApplyToRestaurant applyToRestaurant = catalogApplyToRestaurantRepository.findByRestaurantCode(resCode);
			if(applyToRestaurant == null) {
				applyToRestaurant = new CatalogApplyToRestaurant();
			}
			applyToRestaurant.setRestaurantCode(resCode);
			applyToRestaurant.setGroupParamId(groupParamId);
			catalogApplyToRestaurantRepository.save(applyToRestaurant);
		} catch (Exception e) {
			log.error("ERROR PROCESS FUNCTION: SAVE CATALOG-APPLY-TO-RESTAURANT EXCEPTION: {}", e);
		}
	}
}
