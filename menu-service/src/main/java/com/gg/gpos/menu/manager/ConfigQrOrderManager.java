package com.gg.gpos.menu.manager;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.gg.gpos.common.constant.ModuleTypeEnum;
import com.gg.gpos.common.constant.SymbolEnum;
import com.gg.gpos.common.constant.SystemParamEnum;
import com.gg.gpos.common.constant.UploadImageEnum;
import com.gg.gpos.menu.dto.ConfigQrOrderDto;
import com.gg.gpos.menu.entity.ConfigQrOrder;
import com.gg.gpos.menu.mapper.ConfigQrOrderMapper;
import com.gg.gpos.menu.repository.ConfigQrOrderRepository;
import com.gg.gpos.reference.dto.AttachmentDto;
import com.gg.gpos.reference.mapper.AttachmentMapper;
import com.gg.gpos.reference.mapper.SystemParameterMapper;
import com.gg.gpos.reference.repository.AttachmentRepository;
import com.gg.gpos.reference.repository.SystemParameterRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class ConfigQrOrderManager {
	@Autowired
	private ConfigQrOrderMapper configQrOrderMapper;
	@Autowired
	private ConfigQrOrderRepository configQrOrderRepository;
	@Autowired
	private SystemParameterRepository systemParameterRepository;
	@Autowired
	private SystemParameterMapper systemParameterMapper;
	@Autowired
	private AttachmentRepository attachmentRepository;
	@Autowired
	private AttachmentMapper attachmentMapper;
	
	public ConfigQrOrderDto getByCoIdAndRestaurantCodeNull(Long coId) {
		log.debug("PROCESS: GET CONFIG_QR_ORDER BY CO_ID AND RESTAURANT_CODE IS NULL, CO_ID: {}", coId);
		ConfigQrOrderDto configQrOrderDto = Optional.ofNullable(configQrOrderRepository.findByCoIdAndRestaurantCodeIsNull(coId)).map(configQrOrderMapper::entityToDto).orElse(null);
		if(configQrOrderDto != null) {
			List<AttachmentDto> attachmentExistingDtos = attachmentRepository.findByModuleIdAndModuleType(configQrOrderDto.getId(), ModuleTypeEnum.CONFIG_QR_ORDER.val).stream().map(attachmentMapper::entityToDto).collect(Collectors.toList());
			if(!CollectionUtils.isEmpty(attachmentExistingDtos)) {
				attachmentExistingDtos.stream().forEach(attachmentExistingDto ->{
					String functionType = attachmentExistingDto.getFunctionType();
					String url = attachmentExistingDto.getUrl();
					switch (functionType) {
					case "info_image":
						configQrOrderDto.setInfoPhotoUrl(url);
						break;
					case "logo_image":
						configQrOrderDto.setLogoPhotoUrl(url);
						break;
					case "close_order_image":
						configQrOrderDto.setCloseOrderPhotoUrl(url);
						break;
					default:
						break;
					}
				});
			}
			return configQrOrderDto;
		} else {
			return new ConfigQrOrderDto();
		}
		
	}
	
	public ConfigQrOrderDto getByRestaurantCode(String restaurantCode) {
		log.debug("PROCESS: GET CONFIG_QR_ORDER BY RESTAURANT_CODE, RESTAURANT_CODE: {}", restaurantCode);
		ConfigQrOrderDto configQrOrderDto = Optional.ofNullable(configQrOrderRepository.findByRestaurantCode(restaurantCode)).map(configQrOrderMapper::entityToDto).orElse(null);
		if(configQrOrderDto != null) {
			List<AttachmentDto> attachmentExistingDtos = attachmentRepository.findByModuleIdAndModuleType(configQrOrderDto.getId(), ModuleTypeEnum.CONFIG_QR_ORDER.val).stream().map(attachmentMapper::entityToDto).collect(Collectors.toList());
			if(!CollectionUtils.isEmpty(attachmentExistingDtos)) {
				attachmentExistingDtos.stream().forEach(attachmentExistingDto ->{
					String functionType = attachmentExistingDto.getFunctionType();
					String url = attachmentExistingDto.getUrl();
					switch (functionType) {
					case "info_image":
						configQrOrderDto.setInfoPhotoUrl(url);
						break;
					case "logo_image":
						configQrOrderDto.setLogoPhotoUrl(url);
						break;
					case "close_order_image":
						configQrOrderDto.setCloseOrderPhotoUrl(url);
						break;
					default:
						break;
					}
				});
			}
			return configQrOrderDto;
		} else {
			return new ConfigQrOrderDto();
		}
	}
	
	public ConfigQrOrderDto save(ConfigQrOrderDto configQrOrderDto) {
		log.debug("PROCESS: SAVE CONFIG_QR_ORDER, CONFIG_QR_ORDER_DTO: {}", configQrOrderDto);
		ConfigQrOrder configQrOrder = Optional.ofNullable(configQrOrderDto).map(configQrOrderMapper::dtoToEntity).orElse(null);
		if(configQrOrder != null) {
			return Optional.ofNullable(configQrOrderRepository.save(configQrOrder)).map(configQrOrderMapper::entityToDto).orElse(null);
		} else {
			return null;
		}
	}
	
	public void saveInfoSrcImage(ConfigQrOrderDto configQrOrderDto) {
		log.debug("PROCESS: SAVE CONFIG_QR_ORDER, CONFIG_QR_ORDER_DTO: {}", configQrOrderDto);
		configQrOrderDto.setSrcImgInfo(null);
		configQrOrderDto.setUrlImgInfo(null);
		configQrOrderDto.setSrcImgLogo(null);
		configQrOrderDto.setUrlImgLogo(null);
		configQrOrderDto.setSrcImgCloseOrder(null);
		configQrOrderDto.setUrlImgCloseOrder(null);
		// Lưu lại thông tin để convert json
		ConfigQrOrder configQrOrder = Optional.ofNullable(configQrOrderDto).map(configQrOrderMapper::dtoToEntity).orElse(null);
		if(configQrOrder != null) {
			List<AttachmentDto> attachmentExistingDtos = attachmentRepository.findByModuleIdAndModuleType(configQrOrderDto.getId(), ModuleTypeEnum.CONFIG_QR_ORDER.val).stream().map(attachmentMapper::entityToDto).collect(Collectors.toList());
			if(!CollectionUtils.isEmpty(attachmentExistingDtos)) {
				String uploadFolder = UploadImageEnum.RESTAURANT_INFO.val;
				String slash = SymbolEnum.SLASH.val;
				String gatewayImageUrl = systemParameterRepository.findByParamName(SystemParamEnum.API_BUSINESS_GATEWAY_IMAGE_URL_PATTERN.param).map(systemParameterMapper::entityToDto).orElse(null).getParamValue();
				attachmentExistingDtos.stream().forEach(attachmentExistingDto ->{
					String functionType = attachmentExistingDto.getFunctionType();
					String src = new StringBuilder().append(slash).append(uploadFolder).append(slash).append(attachmentExistingDto.getFileName()).toString(); // ví dụ: /restaurant/image1.jpg
					String url = new StringBuilder().append(gatewayImageUrl).append(attachmentExistingDto.getUrl()).toString(); // ví dụ: /restaurant/image1.jpg
					switch (functionType) {
					case "info_image":
						configQrOrder.setSrcImgInfo(src);
						configQrOrder.setUrlImgInfo(url);
						break;
					case "logo_image":
						configQrOrder.setSrcImgLogo(src);
						configQrOrder.setUrlImgLogo(url);
						break;
					case "close_order_image":
						configQrOrder.setSrcImgCloseOrder(src);
						configQrOrder.setUrlImgCloseOrder(url);
						break;
					default:
						break;
					}
				});
				configQrOrderRepository.save(configQrOrder);
			}
		} 
	}
	
}
