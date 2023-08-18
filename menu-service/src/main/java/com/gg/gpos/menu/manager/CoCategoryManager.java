package com.gg.gpos.menu.manager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import com.gg.gpos.common.constant.*;
import com.gg.gpos.menu.entity.*;
import com.gg.gpos.menu.repository.*;
import com.gg.gpos.reference.entity.SystemParameter;
import com.gg.gpos.reference.repository.SystemParameterRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import com.gg.gpos.common.helper.ImageHelper;
import com.gg.gpos.io.dto.IOCoCategoryDto;
import com.gg.gpos.menu.dto.CoCategoryDto;
import com.gg.gpos.menu.dto.CoDto;
import com.gg.gpos.menu.dto.CoFoodGroupDisplayDto;
import com.gg.gpos.menu.dto.FoodGroupDto;
import com.gg.gpos.menu.dto.SoCategoryDto;
import com.gg.gpos.menu.mapper.CoCategoryMapper;
import com.gg.gpos.menu.mapper.CoFoodGroupDisplayMapper;
import com.gg.gpos.menu.mapper.CoMapper;
import com.gg.gpos.menu.mapper.FoodGroupMapper;
import com.gg.gpos.menu.mapper.SoCategoryMapper;
import com.gg.gpos.reference.dto.AttachmentDto;
import com.gg.gpos.reference.entity.Attachment;
import com.gg.gpos.reference.mapper.AttachmentMapper;
import com.gg.gpos.reference.repository.AttachmentRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class CoCategoryManager {
	
	@Autowired
	private CoCategoryRepository coCategoryRepository;
	@Autowired
	private CoCategoryMapper coCategoryMapper;
	@Autowired
	private CoFoodGroupDisplayRepository coFoodGroupDisplayRepository;
	@Autowired
	private RestaurantDataEditRepository restaurantDataEditRepository;
	@Autowired
	private CatalogDataEditRepository catalogDataEditRepository;
	@Autowired
	private AttachmentRepository attachmentRepository;
	@Autowired
	private CoFoodGroupDisplayMapper coFoodGroupDisplayMapper;
	@Autowired
	private AttachmentMapper attachmentMapper;
	@Autowired
	private CatalogApplyToRestaurantRepository catalogApplyToRestaurantRepository;
	@Autowired 
	private CoMapper coMapper;
	@Autowired
	private CoRepository coRepository;
	@Autowired
	private SoCategoryRepository soCategoryRepository;
	@Autowired
	private SoCategoryMapper soCategoryMapper;
	@Autowired
	private FoodGroupMapper foodGroupMapper;
	@Autowired
	private FoodGroupRepository foodGroupRepository;
	@Autowired
	private ImageHelper imageHelper;
	@Autowired
	private DigitalMenuRepository digitalMenuRepository;
	@Autowired
	private SystemParameterRepository systemParameterRepository;
	
	public CoCategoryDto get(Long id) {
		return coCategoryRepository.findById(id).map(coCategoryMapper::entityToDto).orElse(null);
	}
	
	public boolean getByScId(Long scId) {
		boolean check = false;
		List<CoCategory> categories = coCategoryRepository.findByScId(scId);
		if(categories != null && !categories.isEmpty()) {
			check = true;
		}
		return check;
	}

	private void delAttachment(Long moduleId,String moduleType) {
		log.info("Entering 'delAttachment(Long moduleId,String moduleType)' method...");
		log.debug("Params: moduleId: " + moduleId + ", moduleType: " + moduleType);
		List<Attachment> attachments = attachmentRepository.findByModuleIdAndModuleType(moduleId, moduleType);
		if(attachments != null && !attachments.isEmpty()) {
			attachments.stream().forEach(attachment ->{
				if(attachment.getAbsolutePath() != null) {
					Long countAbsolutePath = attachmentRepository.countByAbsolutePath(attachment.getAbsolutePath());
					if(countAbsolutePath.equals(CountAbsolutePathEnum.ONE.val)) {
						try {
							Files.deleteIfExists(Paths.get(attachment.getAbsolutePath()));
							attachmentRepository.delete(attachment);
						} catch (IOException e) {
							log.error(e.getMessage());
						}
					}else {
						attachmentRepository.delete(attachment);
					}
				}
			});
		}
	}
	
	public void delCoCategoryById(Long id) {
		CoCategory coCategory = coCategoryRepository.getOne(id);
		String orderCode = coCategory.getSoCategory().getOrderCategory().getCode();
		if(coCategory.getRestaurantCode() != null) {
			restaurantDataEditRepository.deleteByValueAndRestaurantCodeAndType(orderCode, coCategory.getRestaurantCode(), TypeRestaurantDataEditEnum.DELETE_CO_CATEGORY_RES.val);
			RestaurantDataEdit restaurantDataEdit = restaurantDataEditRepository.findByValueAndRestaurantCodeAndType(orderCode, coCategory.getRestaurantCode(), TypeRestaurantDataEditEnum.DELETE_CO_CATEGORY_RES.val);
			if(restaurantDataEdit == null) {
				restaurantDataEdit = new RestaurantDataEdit();
			}
			restaurantDataEdit.setValue(orderCode);
			restaurantDataEdit.setType(TypeRestaurantDataEditEnum.DELETE_CO_CATEGORY_RES.val);
			restaurantDataEdit.setRestaurantCode(coCategory.getRestaurantCode());
			restaurantDataEditRepository.save(restaurantDataEdit);
		} else {
			Long coId = coCategory.getCoId();
			catalogDataEditRepository.deleteByCatalogIdAndTypeAndValue(coId,TypeRestaurantDataEditEnum.DELETE_CO_CATEGORY.val, orderCode);
	    	CatalogDataEdit catalogDataEdit = catalogDataEditRepository.findByCatalogIdAndTypeAndValue(coId,TypeRestaurantDataEditEnum.DELETE_CO_CATEGORY.val, orderCode);
			if(catalogDataEdit == null) {
				catalogDataEdit = new CatalogDataEdit();
			}
			catalogDataEdit.setCatalogId(coId);
			catalogDataEdit.setValue(orderCode);
			catalogDataEdit.setType(TypeRestaurantDataEditEnum.DELETE_CO_CATEGORY.val);
			catalogDataEditRepository.save(catalogDataEdit);
		}
		coFoodGroupDisplayRepository.deleteByCoCategory(coCategory);
		delAttachment(coCategory.getId(), ModuleTypeEnum.CO_CATEGORY.val);
		coCategoryRepository.delete(coCategory);
	}
	
	public Page<CoCategoryDto> getsByCoId(CoCategoryDto criteria){
		log.debug("Entering 'gets(criteria)' method...");
		Page<CoCategory> page = coCategoryRepository.findByCoIdAndRestaurantCodeIsNull(criteria.getCoId(), PageRequest.of(criteria.getPage(), criteria.getSize()));
		return new PageImpl<>(page.getContent().stream().map(coCategoryMapper::entityToDto).collect(Collectors.toList()),PageRequest.of(criteria.getPage(), criteria.getSize()), page.getTotalElements());
	}
	
	public Page<CoCategoryDto> gets(CoCategoryDto criteria){
		log.debug("Entering 'gets(criteria)' method...");
		Page<CoCategory> page = coCategoryRepository.findByRestaurantCode(criteria.getRestaurantCode(),PageRequest.of(criteria.getPage(), criteria.getSize()));
		return new PageImpl<>(page.getContent().stream().map(coCategoryMapper::entityToDto).collect(Collectors.toList()),PageRequest.of(criteria.getPage(), criteria.getSize()), page.getTotalElements());
	}
	
	public Page<CoCategoryDto> getCoCateChanged(CoCategoryDto criteria){
		log.debug("Entering 'getCoCateChanged(criteria)' method...");
		Page<CoCategory> page = coCategoryRepository.findByRestaurantCodeAndCoIdIsNull(criteria.getRestaurantCode(),PageRequest.of(criteria.getPage(), criteria.getSize()));
		return new PageImpl<>(page.getContent().stream().map(coCategoryMapper::entityToDto).collect(Collectors.toList()),PageRequest.of(criteria.getPage(), criteria.getSize()), page.getTotalElements());
	}
	
	public CoCategoryDto save(CoCategoryDto coCategoryDto) {
		log.debug("PROCESS: SAVE CO_CATEGORY, CO_CATEGORY: {}", coCategoryDto);
		try {
			List<String> fileNames = new ArrayList<>(Arrays.asList(coCategoryDto.getFileNameImages().split(",")));
			String coCategoryPath = UploadImageEnum.IMG_CATEGORY_PATH.val;
			StringBuilder gatewayUrl = new StringBuilder();
			Optional<SystemParameter> systemParameter = systemParameterRepository.findByParamName(SystemParamEnum.API_BUSINESS_GATEWAY_IMAGE_URL_PATTERN.param);
			if (systemParameter.isPresent()) {
				gatewayUrl.append(systemParameter.get().getParamValue());
			}
			MultipartFile multipartFile = coCategoryDto.getAvatar();
			if(multipartFile != null && !multipartFile.isEmpty()) {
				String fileName = multipartFile.getOriginalFilename();
				String srcImg = ModuleTypeEnum.MENU.val + fileName;
				coCategoryDto.setSrcImg(srcImg);
				coCategoryDto.setSrcImgIntros(srcImg);
			}
			if(coCategoryDto.getRestaurantCode() != null) {
				coCategoryDto.setCoId(null);
			}
			CoCategory coCategory = Optional.ofNullable(coCategoryDto).map(coCategoryMapper::dtoToEntity).orElse(null);
			if (coCategory != null) {
				CoCategory category = coCategoryRepository.save(coCategory);
				// Digital-Menu
				digitalMenuRepository.deleteByCoCategory(category);
				if (!CollectionUtils.isEmpty(coCategoryDto.getDigitalMenus())) {
					coCategoryDto.getDigitalMenus().stream().forEach(item ->{
						if (item != null) {
							if(item.getStatus() != null && !item.getStatus().equals("DELETE")) {
								DigitalMenu digitalMenu = new DigitalMenu();
								digitalMenu.setCoCategory(category);
								Integer index = fileNames.indexOf(item.getName());
								if (index != -1) {
									digitalMenu.setOrderNumber(index + 1);
								} else {
									digitalMenu.setOrderNumber(0);
								}
								digitalMenu.setName(item.getName());
								digitalMenu.setUrl(item.getUrl());
								digitalMenu.setUrlImg(coCategoryPath + item.getName());
								digitalMenu.setSrcImg(gatewayUrl.toString() + item.getUrl());
								digitalMenuRepository.save(digitalMenu);
							}
						}
					});
				}

				// display foodGroup
				String selectedFoodGroupCodes = coCategoryDto.getListFoodGroupCodes();
				if(selectedFoodGroupCodes != null && !selectedFoodGroupCodes.isEmpty()) {
					List<String> selectedfoodGroupCodes = org.springframework.util.StringUtils.commaDelimitedListToSet(selectedFoodGroupCodes).stream().map(String::valueOf).collect(Collectors.toList());
					List<CoFoodGroupDisplay> coFoodGroupDisplays = coFoodGroupDisplayRepository.findByCoCategory(coCategory);
					List<String> foodGroupCodes = new ArrayList<>();
					if(coFoodGroupDisplays != null && !coFoodGroupDisplays.isEmpty()) {
						foodGroupCodes = coFoodGroupDisplays.stream().map(cc -> cc.getFoodGroupCode()).collect(Collectors.toList());
					}

					List<String> existingFoodGroupCodes = foodGroupCodes;
					if(existingFoodGroupCodes != null && !existingFoodGroupCodes.isEmpty()) {
						List<String> newFoodGroupCodes = new ArrayList<>();
						List<String> deletedFoodGroupCodes = new ArrayList<>();
						existingFoodGroupCodes.stream().forEach(c ->{
							deletedFoodGroupCodes.add(c);
						});
						selectedfoodGroupCodes.stream().forEach(c->{
							newFoodGroupCodes.add(c);
						});
						if(!deletedFoodGroupCodes.isEmpty()) {
							coFoodGroupDisplayRepository.deleteByCoCategoryAndFoodGroupCodeIn(category, deletedFoodGroupCodes);
						}

						if(!newFoodGroupCodes.isEmpty()) {
							newFoodGroupCodes.stream().forEach(code ->{
								CoFoodGroupDisplay coFoodGroupDisplay = new CoFoodGroupDisplay();
								coFoodGroupDisplay.setCoCategory(category);
								coFoodGroupDisplay.setFoodGroupCode(code);
								coFoodGroupDisplayRepository.save(coFoodGroupDisplay);
							});
						}


					}else {
						selectedfoodGroupCodes.stream().forEach(fcode ->{
							CoFoodGroupDisplay coFoodGroupDisplay = new CoFoodGroupDisplay();
							coFoodGroupDisplay.setCoCategory(category);
							coFoodGroupDisplay.setFoodGroupCode(fcode);
							coFoodGroupDisplayRepository.save(coFoodGroupDisplay);
						});
					}
				}else {
					List<CoFoodGroupDisplay> coFoodGroupDisplayExisting = coFoodGroupDisplayRepository.findByCoCategory(coCategory);
					if(coFoodGroupDisplayExisting != null && !coFoodGroupDisplayExisting.isEmpty()) {
						coFoodGroupDisplayRepository.deleteByCoCategory(coCategory);
					}
				}
				return Optional.ofNullable(category).map(coCategoryMapper::entityToDto).orElse(null);
			} else {
				return null;
			}
		} catch (Exception e) {
			log.error("ERROR SAVE CO_CATEGORY EXCEPTION: {}", e);
			return null;
		}
	}
	
	public List<CoCategoryDto> getByResCode(Integer resCode) {
		return coCategoryRepository.findByRestaurantCode(resCode).stream().map(coCategoryMapper::entityToDto).collect(Collectors.toList());
	}
	
	/*
	 * lấy dữ liệu CO_CATEGORY theo CO_ID hoặc RESTAURANT_CODE để export
	 */
	public List<IOCoCategoryDto> getIOCoCategoryDtosByCoIdOrResCode(Long coId, Integer resCode) {
		log.debug("PROCESS: GET IO_CO_CATEGORY_DTO BY CO_ID OR RESTAURANT_CODE, CO_ID: {}, RESTAURANT_CODE: {}",coId, resCode);
		List<IOCoCategoryDto> ioCoCategoryDtos = new ArrayList<>();
		List<CoCategoryDto> coCategoryExistingDtos =  new ArrayList<>();
		String moduleTypeImage = ModuleTypeEnum.CO_CATEGORY.val;
		String AMPERSAND = new StringBuilder(SymbolEnum.AMPERSAND.val).append(SymbolEnum.AMPERSAND.val).toString();
		// trường hợp CO_ID khác null thì export CO_CATEGORY của danh mục
		if(coId != null) {
			coCategoryExistingDtos.addAll(coCategoryRepository.findByCoIdAndRestaurantCodeIsNull(coId).stream().map(coCategoryMapper::entityToDto).collect(Collectors.toList()));
		}
		// trường hợp RESTAURANT_CODE khác null thì export CO_CATEGORY của nhà hàng
		if(resCode != null) {
			coCategoryExistingDtos.addAll(coCategoryRepository.findByRestaurantCode(resCode).stream().map(coCategoryMapper::entityToDto).collect(Collectors.toList()));
		}
		if(!CollectionUtils.isEmpty(coCategoryExistingDtos)) {
			coCategoryExistingDtos.stream().forEach(coCategoryExistingDto -> {
				CoCategory coCategoryExisting = Optional.ofNullable(coCategoryExistingDto).map(coCategoryMapper::dtoToEntity).orElse(null);
				IOCoCategoryDto ioCoCategoryDto = new IOCoCategoryDto();
				BeanUtils.copyProperties(coCategoryExistingDto, ioCoCategoryDto);
				ioCoCategoryDto.setOrderCategoryCode(coCategoryExistingDto.getSoCategory().getOrderCategory().getCode());
				if(coCategoryExistingDto.getAverageAmount() != null) {
					ioCoCategoryDto.setAverageAmount(Double.valueOf(coCategoryExistingDto.getAverageAmount()));
				}
				if(coCategoryExistingDto.getType() != null) {
					ioCoCategoryDto.setType(Double.valueOf(coCategoryExistingDto.getType()));
				}
				if(coCategoryExistingDto.getPhotoDisplayPosition() != null) {
					ioCoCategoryDto.setPhotoDisplayPosition(Double.valueOf(coCategoryExistingDto.getPhotoDisplayPosition()));
				}
				List<CoFoodGroupDisplayDto> coFoodGroupDisplayExistingDtos = coFoodGroupDisplayRepository.findByCoCategory(coCategoryExisting).stream().map(coFoodGroupDisplayMapper::entityToDto).collect(Collectors.toList());
				if(!CollectionUtils.isEmpty(coFoodGroupDisplayExistingDtos)) {
					StringBuilder builder = new StringBuilder();
					coFoodGroupDisplayExistingDtos.stream().forEach(item ->{
						builder.append(item.getFoodGroupCode()).append(SymbolEnum.COMMA.val);
					});
					if(builder.length() >= 1) {
						ioCoCategoryDto.setSubGroupCodes(builder.toString().substring(0, builder.toString().length()-1));
					}
				}
				List<AttachmentDto> attachmentExistingDtos = attachmentRepository.findByModuleIdAndModuleType(coCategoryExistingDto.getId(), moduleTypeImage).stream().map(attachmentMapper::entityToDto).collect(Collectors.toList());
				if(!CollectionUtils.isEmpty(attachmentExistingDtos)) {
					StringBuilder imgIntroMenuUrl = new StringBuilder();
					StringBuilder imgIntroResUrl = new StringBuilder();
					attachmentExistingDtos.stream().forEach(attachmentExistingDto -> {
						String functionType = attachmentExistingDto.getFunctionType();
						//String urlImage = attachmentExistingDto.getUrl().substring(10, attachmentExistingDto.getUrl().length());
						String urlImage = attachmentExistingDto.getUrl();
						switch (functionType) {
						case "avatar":
							ioCoCategoryDto.setAvatarUrl(urlImage);
							break;
						case "avatar_aboutus_menu":
							imgIntroMenuUrl.append(urlImage).append(AMPERSAND);
							break;
						case "avatar_aboutus_res":
							imgIntroResUrl.append(urlImage).append(AMPERSAND);
							break;
						case "video_aboutus_menu":
							ioCoCategoryDto.setVideoIntroMenuUrl(urlImage);
							break;
						case "video_aboutus_res":
							ioCoCategoryDto.setVideoIntroResUrl(urlImage);
							break;
						case "single_category":
							ioCoCategoryDto.setSingleCategoryPhotoUrl(urlImage);
							break;
						case "multi_category":
							ioCoCategoryDto.setMultiCategoryPhotoUrl(urlImage);
							break;
						default:
							break;
						}
					});
					if(imgIntroMenuUrl.length() >= 3) {
						ioCoCategoryDto.setIntroImgMenuUrls(imgIntroMenuUrl.toString().substring(0, imgIntroMenuUrl.toString().length()-2));
					}
					if(imgIntroResUrl.length() >= 3) {
						ioCoCategoryDto.setIntroImgResUrl(imgIntroResUrl.toString().substring(0, imgIntroResUrl.toString().length()-2));
					}
				}
				ioCoCategoryDto.setStatus(true);
				ioCoCategoryDtos.add(ioCoCategoryDto);
			});
		}
		return ioCoCategoryDtos;
	}
	
	public List<IOCoCategoryDto> importCoCategory (List<IOCoCategoryDto> ioCoCategoryDtos, Long coId, String attachmentPath) {
		log.debug("PROCESS: IMPORT CO_CATEGORY, IO_CO_CATEGORY_DTO: {}, CO_ID: {}", ioCoCategoryDtos, coId);
		List<IOCoCategoryDto> ioCoCategoryErrs = new ArrayList<>();
		// Khai báo một số biến thường dùng
		final String AMPERSAND = new StringBuffer(SymbolEnum.AMPERSAND.val).append(SymbolEnum.AMPERSAND.val).toString();
		String moduleTypeImage = ModuleTypeEnum.CO_CATEGORY.val;
		String avatarFunctionType = FunctionTypeEnum.AVATAR.val;
		String avatarAboutusMenuFunctionType = FunctionTypeEnum.AVATAR_ABOUTUS_MENU.val;
		String videoAboutusMenuFunctionType = FunctionTypeEnum.VIDEO_ABOUTUS_MENU.val;
		String avatarAboutusResFunctionType = FunctionTypeEnum.AVATAR_ABOUTUS_RES.val;
		String videoAboutusResFunctionType = FunctionTypeEnum.VIDEO_ABOUTUS_RES.val;
		String singleCategoryPhotoFunctionType = FunctionTypeEnum.SINGLE_CATEGORY.val;
		String multiCategoryFunctionType = FunctionTypeEnum.MULTI_CATEGORY.val;
		String catalogDataEditType = TypeRestaurantDataEditEnum.CO_CATEGORY.val;
		// Value của attachmentPath = /opt/gpos/resources/ nhưng do url import từ file excel đã có resources nên chỉ lấy attachmentPath = /opt/gpos/
		String splitAttachmentPath = attachmentPath.substring(0, attachmentPath.length()-11); 
		
		// Lấy danh sách nhà hàng đang được áp dụng danh mục CO này
		List<Integer> restaurantCodeApplies = catalogApplyToRestaurantRepository.findByCoId(coId).stream().map(item -> item.getRestaurantCode()).collect(Collectors.toList());		
		List<Long> newCoCategoryIds = new ArrayList<>();
		// Lấy thông tin danh mục CO
		CoDto coExistingDto = coRepository.findById(coId).map(coMapper::entityToDto).orElse(null);
		if(!CollectionUtils.isEmpty(ioCoCategoryDtos) && coExistingDto != null) {
			Long soId = coExistingDto.getSoId();
			ioCoCategoryDtos.stream().forEach(ioCoCategoryDto -> {
				if(StringUtils.isNotBlank(ioCoCategoryDto.getError())) {
					ioCoCategoryDto.setStatus(false);
					ioCoCategoryErrs.add(ioCoCategoryDto);
	    		} else {
	    			try {
	    				// check xem mã OrderCategory này đã được tạo ở danh mục SO chưa
	    				SoCategoryDto soCategoryExistingDto =  Optional.ofNullable(soCategoryRepository.findBySoIdAndOrderCategoryCode(soId,ioCoCategoryDto.getOrderCategoryCode())).map(soCategoryMapper::entityToDto).orElse(null);
	    				if(soCategoryExistingDto != null) {
	    					// check xem với mã OrderCategory này đã được tạo ở CO_CATEGORY hay chưa nếu chưa thì tạo mới
	    					CoCategoryDto coCategoryExistingDto = Optional.ofNullable(coCategoryRepository.findBySoCategoryIdAndCoIdAndResCodeIsNull(soCategoryExistingDto.getId(),coId)).map(coCategoryMapper::entityToDto).orElse(null);
	    					if(coCategoryExistingDto == null) {
	    						coCategoryExistingDto = new CoCategoryDto();
	    					}
	    					BeanUtils.copyProperties(ioCoCategoryDto, coCategoryExistingDto);
	    					coCategoryExistingDto.setSoCategory(soCategoryExistingDto);
	    					if(ioCoCategoryDto.getAverageAmount() != null) {
	    						coCategoryExistingDto.setAverageAmount(ioCoCategoryDto.getAverageAmount().longValue());
	    					}
	    					if(ioCoCategoryDto.getType() != null) {
	    						coCategoryExistingDto.setType(ioCoCategoryDto.getType().intValue());
	    					}
	    					if(ioCoCategoryDto.getPhotoDisplayPosition() != null) {
	    						coCategoryExistingDto.setPhotoDisplayPosition(ioCoCategoryDto.getPhotoDisplayPosition().intValue());
	    					}
	    					coCategoryExistingDto.setCoId(coId);
	    					coCategoryExistingDto.setStatus(true);
//	    					coCategoryExistingDto.setSrcImg(ioCoCategoryDto.getSrcImg());
//	    					coCategoryExistingDto.setSrcImgIntros(ioCoCategoryDto.getSrcImgIntros());
	    					
	    					CoCategory coCategory = Optional.ofNullable(coCategoryExistingDto).map(coCategoryMapper::dtoToEntity).orElse(null);
	    					CoCategory newCoCategory = coCategoryRepository.save(coCategory);
	    					
	    					Long moduleId = newCoCategory.getId();
	        	    		newCoCategoryIds.add(moduleId);
	    					// Lưu thông tin ảnh đại diện
    						if(StringUtils.isNotBlank(ioCoCategoryDto.getAvatarUrl())) {
								String url = ioCoCategoryDto.getAvatarUrl();
								String fileType = url.substring(url.lastIndexOf(SymbolEnum.DOT.val) + 1);
								String fileUrl = url.substring(url.lastIndexOf(SymbolEnum.SLASH.val) + 1);
								String fileName = fileUrl.substring(fileUrl.lastIndexOf(SymbolEnum.BACKSLASH.val) + 1);
								String absolutePath = new StringBuilder(splitAttachmentPath).append(url).toString();
								if(!imageHelper.checkImageUrlErr(fileName) && fileType.length() < 6) {
									saveAttachmentDto(moduleId, moduleTypeImage, avatarFunctionType, fileName, fileType, url, absolutePath);
								} else {
									ioCoCategoryDto.setStatus(false);
									ioCoCategoryDto.setError(ErrorImportEnum.ERROR_ATTACHMENT_FORMAT.val);
									ioCoCategoryErrs.add(ioCoCategoryDto);
								}
							}
    						
    						// lưu thông tin ảnh giới thiệu menu
            	    		if(StringUtils.isNotBlank(ioCoCategoryDto.getIntroImgMenuUrls())) {
            	    			String[] arr = StringUtils.split(ioCoCategoryDto.getIntroImgMenuUrls(), AMPERSAND);
            	    			if (arr.length >= 1) {
            	    				// Xóa dữ liệu ảnh cũ trước khi tạo mới
            	    				attachmentRepository.deleteByModuleIdAndModuleTypeAndFunctionType(moduleId, moduleTypeImage, avatarAboutusMenuFunctionType);
            	    				Set<String> urls = new LinkedHashSet<>(Arrays.asList(arr));
            	    				urls.stream().forEach(url ->{
            	    					String fileType = url.substring(url.lastIndexOf(SymbolEnum.DOT.val) + 1);
            	    					String fileUrl = url.substring(url.lastIndexOf(SymbolEnum.SLASH.val) + 1);
        								String fileName = fileUrl.substring(fileUrl.lastIndexOf(SymbolEnum.BACKSLASH.val) + 1);
        								String absolutePath = new StringBuilder(splitAttachmentPath).append(url).toString();
        								if(!imageHelper.checkImageUrlErr(fileName) && fileType.length() < 6) {
        									saveAttachmentDto(moduleId, moduleTypeImage, avatarAboutusMenuFunctionType, fileName, fileType, url, absolutePath);
        								} else {
        									ioCoCategoryDto.setStatus(false);
        									ioCoCategoryDto.setError(ErrorImportEnum.ERROR_ATTACHMENT_FORMAT.val);
        									ioCoCategoryErrs.add(ioCoCategoryDto);
        								}
            	    				});
            	    			}
            	    		}
            	    		
            	    		// lưu thông tin ảnh giới thiệu nhà hàng
            	    		if(StringUtils.isNotBlank(ioCoCategoryDto.getIntroImgResUrl())) {
            	    			String[] arr = StringUtils.split(ioCoCategoryDto.getIntroImgResUrl(), AMPERSAND);
            	    			if (arr.length >= 1) {
            	    				// Xóa dữ liệu ảnh cũ trước khi tạo mới
            	    				attachmentRepository.deleteByModuleIdAndModuleTypeAndFunctionType(moduleId, moduleTypeImage, avatarAboutusResFunctionType);
            	    				Set<String> urls = new LinkedHashSet<>(Arrays.asList(arr));
            	    				urls.stream().forEach(url ->{
            	    					String fileType = url.substring(url.lastIndexOf(SymbolEnum.DOT.val) + 1);
            	    					String fileUrl = url.substring(url.lastIndexOf(SymbolEnum.SLASH.val) + 1);
        								String fileName = fileUrl.substring(fileUrl.lastIndexOf(SymbolEnum.BACKSLASH.val) + 1);
        								String absolutePath = new StringBuilder(splitAttachmentPath).append(url).toString();
        								if(!imageHelper.checkImageUrlErr(fileName) && fileType.length() < 6) {
        									saveAttachmentDto(moduleId, moduleTypeImage, avatarAboutusResFunctionType, fileName, fileType, url, absolutePath);
        								} else {
        									ioCoCategoryDto.setStatus(false);
        									ioCoCategoryDto.setError(ErrorImportEnum.ERROR_ATTACHMENT_FORMAT.val);
        									ioCoCategoryErrs.add(ioCoCategoryDto);
        								}
            	    				});
            	    			}
            	    		}
            	    		
            	    		// Lưu thông tin video giới thiệu menu
    						if(StringUtils.isNotBlank(ioCoCategoryDto.getVideoIntroMenuUrl())) {
								String url = ioCoCategoryDto.getVideoIntroMenuUrl();
								String fileType = url.substring(url.lastIndexOf(SymbolEnum.DOT.val) + 1);
								String fileUrl = url.substring(url.lastIndexOf(SymbolEnum.SLASH.val) + 1);
								String fileName = fileUrl.substring(fileUrl.lastIndexOf(SymbolEnum.BACKSLASH.val) + 1);
								String absolutePath = new StringBuilder(splitAttachmentPath).append(url).toString();
								if(!imageHelper.checkImageUrlErr(fileName) && fileType.length() < 6) {
									saveAttachmentDto(moduleId, moduleTypeImage, videoAboutusMenuFunctionType, fileName, fileType, url, absolutePath);
								} else {
									ioCoCategoryDto.setStatus(false);
									ioCoCategoryDto.setError(ErrorImportEnum.ERROR_ATTACHMENT_FORMAT.val);
									ioCoCategoryErrs.add(ioCoCategoryDto);
								}
							}
    						
    						// Lưu thông tin video giới thiệu nhà hàng
    						if(StringUtils.isNotBlank(ioCoCategoryDto.getVideoIntroResUrl())) {
								String url = ioCoCategoryDto.getVideoIntroResUrl();
								String fileType = url.substring(url.lastIndexOf(SymbolEnum.DOT.val) + 1);
								String fileUrl = url.substring(url.lastIndexOf(SymbolEnum.SLASH.val) + 1);
								String fileName = fileUrl.substring(fileUrl.lastIndexOf(SymbolEnum.BACKSLASH.val) + 1);
								String absolutePath = new StringBuilder(splitAttachmentPath).append(url).toString();
								if(!imageHelper.checkImageUrlErr(fileName) && fileType.length() < 6) {
									saveAttachmentDto(moduleId, moduleTypeImage, videoAboutusResFunctionType, fileName, fileType, url, absolutePath);
								} else {
									ioCoCategoryDto.setStatus(false);
									ioCoCategoryDto.setError(ErrorImportEnum.ERROR_ATTACHMENT_FORMAT.val);
									ioCoCategoryErrs.add(ioCoCategoryDto);
								}
							}
    						
    						// Lưu thông tin ảnh 1 category
    						if(StringUtils.isNotBlank(ioCoCategoryDto.getSingleCategoryPhotoUrl())) {
								String url = ioCoCategoryDto.getSingleCategoryPhotoUrl();
								String fileType = url.substring(url.lastIndexOf(SymbolEnum.DOT.val) + 1);
								String fileUrl = url.substring(url.lastIndexOf(SymbolEnum.SLASH.val) + 1);
								String fileName = fileUrl.substring(fileUrl.lastIndexOf(SymbolEnum.BACKSLASH.val) + 1);
								String absolutePath = new StringBuilder(splitAttachmentPath).append(url).toString();
								if(!imageHelper.checkImageUrlErr(fileName) && fileType.length() < 6) {
									saveAttachmentDto(moduleId, moduleTypeImage, singleCategoryPhotoFunctionType, fileName, fileType, url, absolutePath);
								} else {
									ioCoCategoryDto.setStatus(false);
									ioCoCategoryDto.setError(ErrorImportEnum.ERROR_ATTACHMENT_FORMAT.val);
									ioCoCategoryErrs.add(ioCoCategoryDto);
								}
							}
    						
    						// Lưu thông tin ảnh >= 2 category
    						if(StringUtils.isNotBlank(ioCoCategoryDto.getMultiCategoryPhotoUrl())) {
								String url = ioCoCategoryDto.getMultiCategoryPhotoUrl();
								String fileType = url.substring(url.lastIndexOf(SymbolEnum.DOT.val) + 1);
								String fileUrl = url.substring(url.lastIndexOf(SymbolEnum.SLASH.val) + 1);
								String fileName = fileUrl.substring(fileUrl.lastIndexOf(SymbolEnum.BACKSLASH.val) + 1);
								String absolutePath = new StringBuilder(splitAttachmentPath).append(url).toString();
								if(!imageHelper.checkImageUrlErr(fileName) && fileType.length() < 6) {
									saveAttachmentDto(moduleId, moduleTypeImage, multiCategoryFunctionType, fileName, fileType, url, absolutePath);
								} else {
									ioCoCategoryDto.setStatus(false);
									ioCoCategoryDto.setError(ErrorImportEnum.ERROR_ATTACHMENT_FORMAT.val);
									ioCoCategoryErrs.add(ioCoCategoryDto);
								}
							}
							
    						// lưu thông tin về "Danh sách các group hiển thị trong CO"
    						if(StringUtils.isNotBlank(ioCoCategoryDto.getSubGroupCodes())) {
								List<String> foodGroupCodes = new ArrayList<>(Arrays.asList(ioCoCategoryDto.getSubGroupCodes().split(SymbolEnum.COMMA.val)));
								// lấy danh sách các FoodGroup đã được tạo trong danh mục SO mà danh mục CO này đang trỏ đến
								List<FoodGroupDto>  foodGroups = foodGroupRepository.findBySoCategoryId(soCategoryExistingDto.getId()).stream().map(foodGroupMapper::entityToDto).collect(Collectors.toList());
								if(!CollectionUtils.isEmpty(foodGroupCodes) && !CollectionUtils.isEmpty(foodGroups)) {
									// Xóa các bản ghi cũ trước khi tạo data mới
									coFoodGroupDisplayRepository.deleteByCoCategory(coCategory);
									List<String>  foodGroupCodeExistings = foodGroups.stream().map(fg -> fg.getCode()).collect(Collectors.toList());
									if(foodGroupCodeExistings != null && !foodGroupCodeExistings.isEmpty()) {
										foodGroupCodes.stream().forEach(code ->{
											if(foodGroupCodeExistings.contains(code)) {
												CoFoodGroupDisplay coFoodGroupDisplay = new CoFoodGroupDisplay();
												coFoodGroupDisplay.setCoCategory(newCoCategory);
												coFoodGroupDisplay.setFoodGroupCode(code);
												coFoodGroupDisplayRepository.save(coFoodGroupDisplay);
											}
										});
									}
									
								}
							}
							
	    				} else {
	    					ioCoCategoryDto.setStatus(false);
	    					ioCoCategoryDto.setError(ErrorImportEnum.ORDER_CATEGORY_NOT_EXIST.val);
							ioCoCategoryErrs.add(ioCoCategoryDto);
	    				}
	    			} catch (Exception e) {
	    				log.error("ERROR: IMPORT CO_CATEGORY EXCEPTION, EXCEPTION: {}", e);
	    				ioCoCategoryDto.setStatus(false);
	    				ioCoCategoryDto.setError(e.getMessage());
	    				ioCoCategoryErrs.add(ioCoCategoryDto);
					}
	    		}
			});
			
			//Check xem nếu restaurantCodeApplies != null thì lưu vào bảng để đánh dấu item này đã chỉnh sửa => Dùng cho việc đồng bộ update
    		if(!CollectionUtils.isEmpty(restaurantCodeApplies) && !CollectionUtils.isEmpty(newCoCategoryIds)) {
    			newCoCategoryIds.stream().forEach(id ->{
    				// Xóa dữ liệu cũ trước khi lưu data
    				catalogDataEditRepository.deleteByCatalogIdAndTypeAndValue(coId, catalogDataEditType, id.toString());
    				CatalogDataEdit catalogDataEdit = new CatalogDataEdit();
    				catalogDataEdit.setCatalogId(coId);
    				catalogDataEdit.setValue(id.toString());
    				catalogDataEdit.setType(catalogDataEditType);
    				catalogDataEditRepository.save(catalogDataEdit);
    			});
    		}
		}
		return ioCoCategoryErrs;
	}
	
	/*
	 * Function tạo mới đường dẫn của ảnh
	 */
	private AttachmentDto saveAttachmentDto(Long moduleId, String moduleType, String functionType, String  fileName, String fileType, String url, String absolutePath) {
		log.debug("PROCESS: SAVE ATTACHMENT, MODULE_ID: {}, MODULE_TYPE: {}, FUNCTION_TYPE: {}, URL: {}, ABSOLUTE_PATH: {}", moduleId, moduleType, functionType, url, absolutePath);
		
		/*
		 * MODULE_ID: ID của CO_FOOD_ITEM
		 * MODULE_TYPE: Loại ảnh, ví dụ: ảnh Cateogry, ảnh CoFoodItem
		 * FUNCTION_TYPE: Chức năng của ảnh để hiển thị, ví dụ: ảnh đại diện, ảnh đồ uống, ảnh 1/4
		 */
		
		try {
			// Xóa data cũ trước khi tạo mới || với functionType có giá trị "avatar_aboutus_menu, avatar_aboutus_res" thì không xóa ở function này (Do các loại ảnh này có thể có nhiều hơn 1 ảnh)
			if(!functionType.equals(FunctionTypeEnum.IMAGES.val)) {
				attachmentRepository.deleteByModuleIdAndModuleTypeAndFunctionType(moduleId, moduleType, functionType);
			}
			// tạo mới data
			Attachment attachment = new Attachment();
			attachment.setModuleId(moduleId);
			attachment.setModuleType(moduleType);
			attachment.setFunctionType(functionType);
			attachment.setUrl(url);
			attachment.setAbsolutePath(absolutePath);
			attachment.setFileName(fileName);
			attachment.setFileType(fileType);
			return Optional.ofNullable(attachmentRepository.save(attachment)).map(attachmentMapper::entityToDto).orElse(null);
		} catch (Exception e) {
			log.debug("ERROR SAVE ATTACHMENT EXCEPTION: {}", e);
			return null;
		}
	}
}
