package com.gg.gpos.menu.manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import com.gg.gpos.common.json.*;
import com.gg.gpos.menu.dto.*;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import com.gg.gpos.common.constant.FoodGroupLevelEnum;
import com.gg.gpos.common.constant.FoodTypeEnum;
import com.gg.gpos.common.constant.MapKeyEnum;
import com.gg.gpos.common.constant.MenuTypeEnum;
import com.gg.gpos.common.constant.ModuleTypeEnum;
import com.gg.gpos.common.constant.StatusEnum;
import com.gg.gpos.common.constant.SymbolEnum;
import com.gg.gpos.common.constant.TypeRestaurantDataEditEnum;
import com.gg.gpos.common.constant.UploadImageEnum;
import com.gg.gpos.menu.entity.CoFoodItem;
import com.gg.gpos.menu.entity.FoodGroup;
import com.gg.gpos.menu.entity.FoodGroupItem;
import com.gg.gpos.menu.entity.FoodItem;
import com.gg.gpos.menu.entity.RestaurantDataEdit;
import com.gg.gpos.menu.entity.SoCategory;
import com.gg.gpos.menu.entity.SoCategoryFoodGroup;
import com.gg.gpos.menu.mapper.AreaMapper;
import com.gg.gpos.menu.mapper.CoCategoryMapper;
import com.gg.gpos.menu.mapper.CoFoodItemMapper;
import com.gg.gpos.menu.mapper.CoFoodItemModifierMapper;
import com.gg.gpos.menu.mapper.ConfigQrOrderMapper;
import com.gg.gpos.menu.mapper.FeatureMapper;
import com.gg.gpos.menu.mapper.FoodGroupMapper;
import com.gg.gpos.menu.mapper.FoodItemMapper;
import com.gg.gpos.menu.mapper.KaloGroupMapper;
import com.gg.gpos.menu.mapper.KitchenMapper;
import com.gg.gpos.menu.mapper.OrderCategoryMapper;
import com.gg.gpos.menu.mapper.ParamMapper;
import com.gg.gpos.menu.mapper.PrintGroupMapper;
import com.gg.gpos.menu.mapper.RelatedFoodItemMapper;
import com.gg.gpos.menu.mapper.ToppingFoodItemMapper;
import com.gg.gpos.menu.mapper.VersionMapper;
import com.gg.gpos.menu.repository.AreaRepository;
import com.gg.gpos.menu.repository.CategoryRepository;
import com.gg.gpos.menu.repository.CoCategoryRepository;
import com.gg.gpos.menu.repository.CoFoodItemModifierRepository;
import com.gg.gpos.menu.repository.CoFoodItemRepository;
import com.gg.gpos.menu.repository.ConfigQrOrderRepository;
import com.gg.gpos.menu.repository.CurrencyRateRepository;
import com.gg.gpos.menu.repository.CurrencyRepository;
import com.gg.gpos.menu.repository.EmployeeRepository;
import com.gg.gpos.menu.repository.FoodGroupItemRepository;
import com.gg.gpos.menu.repository.FoodGroupRepository;
import com.gg.gpos.menu.repository.FoodItemRepository;
import com.gg.gpos.menu.repository.GuestTypeRepository;
import com.gg.gpos.menu.repository.HallplanRepository;
import com.gg.gpos.menu.repository.KaloGroupRepository;
import com.gg.gpos.menu.repository.KitchenRepository;
import com.gg.gpos.menu.repository.ModiGroupRepository;
import com.gg.gpos.menu.repository.ModiSchemeDetailRepository;
import com.gg.gpos.menu.repository.ModiSchemeRepository;
import com.gg.gpos.menu.repository.ModifierRepository;
import com.gg.gpos.menu.repository.OrderCategoryRepository;
import com.gg.gpos.menu.repository.OrderTypeRepository;
import com.gg.gpos.menu.repository.OrderVoidRepository;
import com.gg.gpos.menu.repository.ParamRepository;
import com.gg.gpos.menu.repository.PrintGroupRepository;
import com.gg.gpos.menu.repository.RelatedFoodItemRepository;
import com.gg.gpos.menu.repository.RestaurantDataEditRepository;
import com.gg.gpos.menu.repository.RestaurantMasterRepository;
import com.gg.gpos.menu.repository.SoCategoryFoodGroupRepository;
import com.gg.gpos.menu.repository.SoCategoryRepository;
import com.gg.gpos.menu.repository.TableKitchenRepository;
import com.gg.gpos.menu.repository.ToppingFoodItemRepository;
import com.gg.gpos.menu.repository.VersionRepository;
import com.gg.gpos.reference.dto.AttachmentDto;
import com.gg.gpos.reference.mapper.AttachmentMapper;
import com.gg.gpos.reference.repository.AttachmentRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class SyncRestaurantDataManager {
	@Autowired
	private CoFoodItemRepository coFoodItemRepository;
	@Autowired
	private RelatedFoodItemRepository relatedFoodItemRepository;
	@Autowired
	private SoCategoryRepository soCategoryRepository;
	@Autowired
	private CoCategoryRepository coCategoryRepository;
	@Autowired
	private ToppingFoodItemRepository toppingFoodItemRepository;
	@Autowired
	private RestaurantDataEditRepository restaurantDataEditRepository;
	@Autowired
	private AttachmentRepository attachmentRepository;
	@Autowired
	private CoFoodItemModifierRepository coFoodItemModifierRepository;
	@Autowired
	private CoCategoryMapper coCategoryMapper;
	@Autowired
	private CoFoodItemMapper coFoodItemMapper;
	@Autowired
	private AttachmentMapper attachmentMapper;
	@Autowired
	private KaloGroupRepository kaloGroupRepository;
	@Autowired
	private KaloGroupMapper kaloGroupMapper;
	@Autowired
	private FoodItemRepository foodItemRepository;
	@Autowired
	private FoodItemMapper foodItemMapper;
	@Autowired
	private CoFoodItemModifierMapper coFoodItemModifierMapper; 
	@Autowired
	private ToppingFoodItemMapper toppingFoodItemMapper; 
	@Autowired
	private FeatureMapper featureMapper;
	@Autowired
	private RelatedFoodItemMapper relatedFoodItemMapper;
	@Autowired
	private ParamRepository paramRepository;
	@Autowired
	private ParamMapper paramMapper;
	@Autowired
	private SoCategoryFoodGroupRepository soCategoryFoodGroupRepository;
	@Autowired
	private FoodGroupRepository foodGroupRepository;
	@Autowired
	private FoodGroupItemRepository foodGroupItemRepository;
	@Autowired
	private FoodGroupMapper foodGroupMapper;
	@Autowired
	private PrintGroupRepository printGroupRepository;
	@Autowired
	private KitchenRepository kitchenRepository;
	@Autowired
	private AreaRepository areaRepository;
	@Autowired
	private PrintGroupMapper printGroupMapper;
	@Autowired
	private KitchenMapper kitchenMapper;
	@Autowired
	private AreaMapper areaMapper;
	@Autowired
	private OrderCategoryMapper orderCategoryMapper;
	@Autowired
	private ConfigQrOrderRepository configQrOrderRepository;
	@Autowired
	private ConfigQrOrderMapper configQrOrderMapper;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private CurrencyRepository currencyRepository;
	@Autowired
	private CurrencyRateRepository currencyRateRepository;
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private GuestTypeRepository guestTypeRepository;
	@Autowired
	private HallplanRepository hallplanRepository;
	@Autowired
	private ModifierRepository modifierRepository;
	@Autowired
	private ModiGroupRepository modiGroupRepository;
	@Autowired
	private ModiSchemeRepository modiSchemeRepository;
	@Autowired
	private OrderCategoryRepository orderCategoryRepository;
	@Autowired
	private OrderTypeRepository orderTypeRepository;
	@Autowired
	private OrderVoidRepository orderVoidRepository;
	@Autowired
	private TableKitchenRepository tableKitchenRepository;
	@Autowired
	private RestaurantMasterRepository restaurantMasterRepository;
	@Autowired
	private ModiSchemeDetailRepository modiSchemeDetailRepository;
	@Autowired
	private VersionRepository versionRepository;
	@Autowired
	private VersionMapper versionMapper;

	/*
	 * Convert BUSINESS_DATA về JSON để gửi dữ liệu xuống SERVER NHÀ HÀNG
	 */
	public RestaurantMenuDataSync convertBusinessDataToJson(Integer restaurantCode, Boolean isOverride, String apiUrlFile, String restaurantName, String gatewayImageUrl) {
		log.debug("PROCESS: CONVERT BUSINESS_DATA TO JSON, BY RESTAURANT_CODE: {}, RESTAURANT_NAME: {}, IS_OVERRIDE: {}", restaurantCode, restaurantName, isOverride);
		RestaurantMenuDataSync restaurantMenuDataSync = new RestaurantMenuDataSync();
		try {
			// Phần dữ liệu PRINT_GROUP
			List<PrintGroupDto> printGroupDtos = printGroupRepository.findByRestaurantCode(restaurantCode).stream().map(printGroupMapper::entityToDto).collect(Collectors.toList());
			if(!CollectionUtils.isEmpty(printGroupDtos)) {
				List<ConfigPrintGroup> configPrintGroups = new ArrayList<>();
				printGroupDtos.stream().forEach(printGroupDto ->{
					ConfigPrintGroup configPrintGroup = new ConfigPrintGroup();
					BeanUtils.copyProperties(printGroupDto, configPrintGroup);
					configPrintGroups.add(configPrintGroup);
				});
				restaurantMenuDataSync.setPrintGroups(configPrintGroups);
			}
			
			// Phần dữ liệu KITCHEN_AREA
			List<KitchenDto> kitchenDtos = kitchenRepository.findByRestaurantCode(restaurantCode).stream().map(kitchenMapper::entityToDto).collect(Collectors.toList());
			if(!CollectionUtils.isEmpty(kitchenDtos)) {
				List<ConfigPlace> configPlaces = new ArrayList<>();
				kitchenDtos.stream().forEach(kitchenDto ->{
					ConfigPlace configPlace = new ConfigPlace();
					BeanUtils.copyProperties(kitchenDto, configPlace);
					List<HallplanDto> hallplanDtos = kitchenDto.getHallplans();
					if(!CollectionUtils.isEmpty(hallplanDtos)) {
						configPlace.setHallPlanIds(hallplanDtos.stream().map(h -> h.getId()).collect(Collectors.toList()));
					}
					configPlaces.add(configPlace);
				});
				restaurantMenuDataSync.setPlaces(configPlaces);
			}
			
			// Phần dữ liệu RESTAURANT_AREA - HALLPLAN
			List<AreaDto> areaDtos = areaRepository.findByRestaurantCode(restaurantCode).stream().map(areaMapper::entityToDto).collect(Collectors.toList());
			if(!CollectionUtils.isEmpty(areaDtos)) {
				List<ConfigHallplan> configHallplans = new ArrayList<>();
				areaDtos.stream().forEach(areaDto ->{
					HallplanDto hallplanDto = areaDto.getHallplan();
					ConfigHallplan configHallplan = new ConfigHallplan();
					BeanUtils.copyProperties(hallplanDto, configHallplan);
					if(StringUtils.isNotBlank(areaDto.getPrinterName())) {
						configHallplan.setPrinter(areaDto.getPrinterName());
					}
					if(StringUtils.isNotBlank(areaDto.getPrintPosCode())) {
						configHallplan.setStationCode(areaDto.getPrintPosCode());
					}
					configHallplans.add(configHallplan);
				});
				restaurantMenuDataSync.setHallplans(configHallplans);
			}
			
			// Phần dữ liệu GROUP_PARAM
			List<ParamDto> paramDtos = paramRepository.findByRestaurantCode(restaurantCode).stream().map(paramMapper::entityToDto).collect(Collectors.toList());
			if(!CollectionUtils.isEmpty(paramDtos)) {
				List<ConfigParam> configParams = new ArrayList<>();
				paramDtos.stream().forEach(paramDto ->{
					ConfigParam configParam = new ConfigParam();
					BeanUtils.copyProperties(paramDto, configParam);
					if (paramDto.isStatus()) {
						configParam.setStatus(StatusEnum.ACTIVE.status);
					}
					configParams.add(configParam);
				});
				restaurantMenuDataSync.setConfigParams(configParams);
			}
			
			// Phần dữ liệu KALO_GROUP
			List<KaloGroupDto> kaloGroupDtos = kaloGroupRepository.findAll().stream().map(kaloGroupMapper::entityToDto).collect(Collectors.toList());
			if(!CollectionUtils.isEmpty(kaloGroupDtos)) {
				JsonKaloGroup jsonKaloGroup = new JsonKaloGroup();
				List<JsonKaloGroupDetail> jsonKaloGroupDetails = new ArrayList<>();
				kaloGroupDtos.stream().forEach(kaloGroupDto ->{
					JsonKaloGroupDetail jsonKaloGroupDetail = new JsonKaloGroupDetail();
					BeanUtils.copyProperties(kaloGroupDto, jsonKaloGroupDetail);
					jsonKaloGroupDetails.add(jsonKaloGroupDetail);
				});
				jsonKaloGroup.setJsonKaloGroupDetails(jsonKaloGroupDetails);
				restaurantMenuDataSync.setJsonKaloGroup(jsonKaloGroup);
			}
			
			// Phần dữ liệu SO_MENU và CO_MENU
			List<SoCategory> soCategoryExistings = new ArrayList<>();
			List<CoCategoryDto> coCategoryExistingDtos = new ArrayList<>();
			List<CoFoodItemDto> coFoodItemExistingDtos = new ArrayList<>();
			List<Long> coFoodItemIds = new ArrayList<>();
			List<Long> coCategoryIds = new ArrayList<>();
			List<Long> foodGroupIds = new ArrayList<>();
			
			// thông tin JSON xóa ITEM
			List<JsonDeleteCoCategory> jsonDeleteCoCategories = new ArrayList<>();
			List<ConfigMenuEngineering> deleteMenuEngineerings = new ArrayList<>();
			
			if(isOverride) {
				// Nếu ghi đè thì lấy toàn bộ các ITEM dưới nhà hàng
				soCategoryExistings.addAll(soCategoryRepository.findByRestaurantCode(restaurantCode)); 
				coCategoryExistingDtos.addAll(coCategoryRepository.findByRestaurantCode(restaurantCode).stream().map(coCategoryMapper::entityToDto).collect(Collectors.toList()));
				coFoodItemExistingDtos.addAll(coFoodItemRepository.findByRestaurantCode(restaurantCode).stream().map(coFoodItemMapper::entityToDto).collect(Collectors.toList()));
			} else {
				// Nếu là update thì lấy danh sách các bản ghi đã bị chỉnh sửa để đồng bộ
				
				// lấy danh sách các bản ghi SO_CATEGORY đã bị chỉnh sửa để đồng bộ
				List<Long> soCategoryEditIds = restaurantDataEditRepository.findByRestaurantCodeAndType(restaurantCode, TypeRestaurantDataEditEnum.SO_CATEGORY.val).stream().map(p -> Long.parseLong(p.getValue())).collect(Collectors.toList());
				soCategoryExistings.addAll(soCategoryRepository.findByIdIn(soCategoryEditIds));
				
				// Lấy danh sách các bản ghi SO_CATEGORY đã bị xóa ở dưới nhà hàng
				List<RestaurantDataEdit> deleteSoCategoryDatas = new ArrayList<>();
				deleteSoCategoryDatas.addAll(restaurantDataEditRepository.findByRestaurantCodeAndType(restaurantCode, TypeRestaurantDataEditEnum.DELETE_SO_CATEGORY.val));
				deleteSoCategoryDatas.addAll(restaurantDataEditRepository.findByRestaurantCodeAndType(restaurantCode, TypeRestaurantDataEditEnum.DELETE_SO_CATEGORY_RES.val));
				if(!CollectionUtils.isEmpty(deleteSoCategoryDatas)) {
					deleteSoCategoryDatas.stream().forEach(item ->{
						ConfigMenuEngineering delMenuEngineering = new ConfigMenuEngineering();
						JsonOrderCategory jsonOrderCategory = new JsonOrderCategory();
						jsonOrderCategory.setCode(item.getValue());
						delMenuEngineering.setJsonOrderCategory(jsonOrderCategory);
						deleteMenuEngineerings.add(delMenuEngineering);
					});
					
				}
				
				// Lấy danh sách các bản ghi CO_CATEGORY đã được chỉnh sửa ở dưới nhà hàng
				List<Long> coCategoryEditIds = restaurantDataEditRepository.findByRestaurantCodeAndType(restaurantCode, TypeRestaurantDataEditEnum.CO_CATEGORY.val).stream().map(p -> Long.parseLong(p.getValue())).collect(Collectors.toList());
				coCategoryExistingDtos.addAll(coCategoryRepository.findByIdIn(coCategoryEditIds).stream().map(coCategoryMapper::entityToDto).collect(Collectors.toList()));
				
				// Lấy danh sách các bản ghi CO_CATEGORY đã bị xóa dưới nhà hàng
				List<RestaurantDataEdit> deleteCoCategoryDataInRestaurants = new ArrayList<>();
				deleteCoCategoryDataInRestaurants.addAll(restaurantDataEditRepository.findByRestaurantCodeAndType(restaurantCode, TypeRestaurantDataEditEnum.DELETE_CO_CATEGORY.val));
				deleteCoCategoryDataInRestaurants.addAll(restaurantDataEditRepository.findByRestaurantCodeAndType(restaurantCode, TypeRestaurantDataEditEnum.DELETE_CO_CATEGORY_RES.val));
				if(!CollectionUtils.isEmpty(deleteCoCategoryDataInRestaurants)) {
					deleteCoCategoryDataInRestaurants.stream().forEach(item ->{
						JsonDeleteCoCategory jsonDeleteCoCategory = new JsonDeleteCoCategory();
						jsonDeleteCoCategory.setCode(item.getValue());
						jsonDeleteCoCategories.add(jsonDeleteCoCategory);
					});
				}
				
				// Lấy danh sách các bản ghi CO_FOOD_ITEM đã được chỉnh sửa ở dưới nhà hàng
				List<Long> coFoodItemEditIds = restaurantDataEditRepository.findByRestaurantCodeAndType(restaurantCode, TypeRestaurantDataEditEnum.CO_FOODITEM.val).stream().map(p -> Long.parseLong(p.getValue())).collect(Collectors.toList());
				coFoodItemExistingDtos.addAll(coFoodItemRepository.findByIdIn(coFoodItemEditIds).stream().map(coFoodItemMapper::entityToDto).collect(Collectors.toList()));
			
				// Lấy danh sách các bản ghi CO_FOODITEM đã bị xóa dưới nhà hàng
				List<RestaurantDataEdit> deleteCoFoodItemDataInRestaurants = new ArrayList<>();
				deleteCoFoodItemDataInRestaurants.addAll(restaurantDataEditRepository.findByRestaurantCodeAndType(restaurantCode, TypeRestaurantDataEditEnum.DELETE_CO_FOODITEM.val));
				deleteCoFoodItemDataInRestaurants.addAll(restaurantDataEditRepository.findByRestaurantCodeAndType(restaurantCode, TypeRestaurantDataEditEnum.DELETE_CO_FOODITEM_RES.val));
				if(!CollectionUtils.isEmpty(deleteCoFoodItemDataInRestaurants)) {
					List<JsonDeleteCOMenuItem> jsonDeleteCOMenuItems = new ArrayList<>();
					deleteCoFoodItemDataInRestaurants.stream().forEach(item ->{
						JsonDeleteCOMenuItem jsonDeleteCOMenuItem = new JsonDeleteCOMenuItem();
						jsonDeleteCOMenuItem.setCode(item.getValue());
						jsonDeleteCOMenuItems.add(jsonDeleteCOMenuItem);
					});
					restaurantMenuDataSync.setDeleteCOMenuItems(jsonDeleteCOMenuItems);
				}
			}
			
			// Convert dữ liệu liên quan đến SO_MENU
			Integer defaultGroupLevel = FoodGroupLevelEnum.MENU_NORMAL.val;
			String folderUpload = UploadImageEnum.IMG_MENU_FOLDER.val;
			String moduleType = ModuleTypeEnum.FOOD_GROUP.val;
			Integer deleteStatus = StatusEnum.DELETE.status;
			// PHẦN THÔNG TIN LIÊN QUAN ĐẾN SO_CATEGORY
			List<FileContent> fileAttachments = new ArrayList<>();
			List<ConfigMenuEngineering> configMenuEngineerings = new ArrayList<>();
			List<JsonMenuGroupDetail> jsonMenuGroupDetails = new ArrayList<>();
			if(!CollectionUtils.isEmpty(soCategoryExistings)) {
				soCategoryExistings.stream().forEach(soCategoryExisting ->{
					// Kiểm tra trạng thái của loại MENU. Nếu trạng thái OFF thì gọi API xóa và ngược lại
					if(soCategoryExisting.isStatus()) {
						ConfigMenuEngineering configMenuEngineering = new ConfigMenuEngineering();
						
						OrderCategoryDto orderCategoryDto = Optional.ofNullable(soCategoryExisting.getOrderCategory()).map(orderCategoryMapper::entityToDto).orElse(null); 
						// dữ liệu liên quan đến ORDER_CATEGORY
						JsonOrderCategory jsonOrderCategory = new JsonOrderCategory();
						BeanUtils.copyProperties(orderCategoryDto, jsonOrderCategory);
						if (soCategoryExisting.getOrderCategoryParentId() != null) {
							jsonOrderCategory.setOrderCategoryParentId(soCategoryExisting.getOrderCategoryParentId());	
						}
						configMenuEngineering.setJsonOrderCategory(jsonOrderCategory);
						// Dữ liệu menu_type
						for (MenuTypeEnum e : MenuTypeEnum.values()) {
			        		if (e.key.equals(soCategoryExisting.getType())) {
			        			configMenuEngineering.setMenuType(e.val);		
			        		}
			        	}
						// Dữ liệu vé buffet người lớn
						JsonDefaultDish jsonDefaultDish = new JsonDefaultDish();
						if(soCategoryExisting.getAdultBufferTicket() != null) {
							JsonDishItem jsonDishItem = new JsonDishItem();
			        		String[] str = soCategoryExisting.getAdultBufferTicket().split(SymbolEnum.HYPHEN.val);
			        		FoodItemDto foodItemDto = Optional.ofNullable(foodItemRepository.findByCode(str[0])).map(foodItemMapper::entityToDto).orElse(null);
			        		if(foodItemDto != null) {
			        			BeanUtils.copyProperties(foodItemDto, jsonDishItem);
			        			jsonDefaultDish.setJsonDishItem(jsonDishItem);
			        		}
			        		configMenuEngineering.setJsonDefaultDish(jsonDefaultDish);
						}
						// Dữ liệu vé buffet trẻ em
						JsonChildDish jsonChildDish = new JsonChildDish();
						if(soCategoryExisting.getKidBufferTicket() != null) {
							JsonDishItem jsonDishItem = new JsonDishItem();
			        		String[] str = soCategoryExisting.getKidBufferTicket().split(SymbolEnum.HYPHEN.val);
			        		FoodItemDto foodItemDto = Optional.ofNullable(foodItemRepository.findByCode(str[0])).map(foodItemMapper::entityToDto).orElse(null);
			        		if(foodItemDto != null) {
			        			BeanUtils.copyProperties(foodItemDto, jsonDishItem);
			        			jsonChildDish.setJsonDishItem(jsonDishItem);
			        		}
			        		configMenuEngineering.setJsonChildDish(jsonChildDish);
						}
						
						// Dữ liệu liên quan đến FOOD_GROUP
						List<SoCategoryFoodGroup> soCategoryFoodGroupExistings = soCategoryFoodGroupRepository.findBySoCategoryAndRestaurantCode(soCategoryExisting.getId(), restaurantCode);
//						List<SoCategoryFoodGroup> soCategoryFoodGroupExistings = soCategoryExisting.getSoCategoryFoodGroups();;
						List<JsonMenuGroupDetail> configMenuGroupDetails = new ArrayList<>();
						if(!CollectionUtils.isEmpty(soCategoryFoodGroupExistings)) {
							soCategoryFoodGroupExistings.stream().forEach(soCategoryFoodGroupExisting ->{
								FoodGroup foodGroupExisting = soCategoryFoodGroupExisting.getFoodGroup();
								FoodGroupDto foodGroupExistingDto = Optional.ofNullable(foodGroupExisting).map(foodGroupMapper::entityToDto).orElse(null);
								// Chỉ gửi các bản ghi có Changed != delete và khác Null
								if(foodGroupExistingDto.getChanged() != null && !foodGroupExistingDto.getChanged().equals(deleteStatus)) {
									// Thông tin dữ liệu ảnh
									List<AttachmentDto> attachmentExistingDtos = attachmentRepository.findByModuleIdAndModuleType(foodGroupExistingDto.getId(), moduleType).stream().map(attachmentMapper::entityToDto).collect(Collectors.toList());
									if(!CollectionUtils.isEmpty(attachmentExistingDtos)) {
										// Lưu thông tin ModuleId ảnh cần đồng bộ
										foodGroupIds.add(foodGroupExistingDto.getId());
										
										AttachmentDto attachmentDto = attachmentExistingDtos.get(0);
										FileContent file = new FileContent();
										file.setAbsolutePath(attachmentDto.getAbsolutePath());
										file.setFileName(attachmentDto.getFileName());
										file.setFolder(folderUpload);
										file.setResCode(restaurantCode);
										file.setResName(restaurantName);
										file.setApiUrl(apiUrlFile);
										file.setType(moduleType);
										file.setCode(foodGroupExistingDto.getCode());
										file.setAttachmentId(attachmentDto.getId());
										fileAttachments.add(file);
									}
									
									List<JsonMenuItem> jsonMenuItems = new ArrayList<>();
									List<FoodGroup> subFoodGroups = foodGroupRepository.findByParentAndRestaurantCode(foodGroupExisting, restaurantCode);
									if(!CollectionUtils.isEmpty(subFoodGroups)) {
										// Thông tin các nhóm FOOD_GROUP con nếu FOOD_GROUP_EXISTING là nhóm cha
										JsonMenuGroupDetail jsonMenuGroupDetailParent = new JsonMenuGroupDetail();
										BeanUtils.copyProperties(foodGroupExistingDto, jsonMenuGroupDetailParent);
										jsonMenuGroupDetailParent.setName(foodGroupExistingDto.getNameVn());
										if(foodGroupExistingDto.getMenuType().getCode() != null) {
											jsonMenuGroupDetailParent.setGroupType(foodGroupExistingDto.getMenuType().getCode());
										}
										if (soCategoryFoodGroupExisting.getGroupOrder() != null) {
											jsonMenuGroupDetailParent.setOrderNo(soCategoryFoodGroupExisting.getGroupOrder());	
										}
										if(foodGroupExistingDto.getLevel() != null) {
											jsonMenuGroupDetailParent.setLevel(foodGroupExistingDto.getLevel());
										}else {
											jsonMenuGroupDetailParent.setLevel(defaultGroupLevel);
										}
										
										subFoodGroups.stream().forEach(subFoodGroup -> {
											if(subFoodGroup.getChanged() != null && !subFoodGroup.getChanged().equals(deleteStatus)) {
												JsonMenuItem jsonMenuItem = new JsonMenuItem();
												BeanUtils.copyProperties(subFoodGroup, jsonMenuItem);
												if (soCategoryFoodGroupExisting.getGroupOrder() != null) {
													jsonMenuItem.setOrderNumberItem(soCategoryFoodGroupExisting.getGroupOrder());	
												}
												jsonMenuItems.add(jsonMenuItem);
											}
											
										});
										jsonMenuGroupDetailParent.setJsonMenuItems(jsonMenuItems);
										configMenuGroupDetails.add(jsonMenuGroupDetailParent);
										jsonMenuGroupDetails.add(jsonMenuGroupDetailParent);
										
									} else if(foodGroupExisting.getParent() != null){
										// Nhóm FOOD_GROUP_EXISTING có nhóm cha
										List<FoodGroupItem> foodGroupItems = foodGroupItemRepository.findByFoodGroupAndRestaurantCode(foodGroupExisting, restaurantCode);
										if(!CollectionUtils.isEmpty(foodGroupItems)) {
											foodGroupItems.stream().forEach(foodGroupItem -> {
												if(foodGroupItem.getChanged() == null || (foodGroupItem.getChanged() != null && !foodGroupItem.getChanged().equals(deleteStatus))) {
													FoodItem foodItem = foodGroupItem.getFoodItem();
													JsonMenuItem jsonMenuItem = new JsonMenuItem();
													BeanUtils.copyProperties(foodItem, jsonMenuItem);
													jsonMenuItem.setNameVn(foodItem.getName());
													jsonMenuItem.setOrderNumberItem(foodGroupItem.getItemOrder());
													jsonMenuItems.add(jsonMenuItem);
												}
											});
										}
										
										JsonMenuGroupDetail jsonMenuGroupDetail = new JsonMenuGroupDetail();
										BeanUtils.copyProperties(foodGroupExistingDto, jsonMenuGroupDetail);
										jsonMenuGroupDetail.setName(foodGroupExistingDto.getNameVn());
										if(foodGroupExistingDto.getMenuType().getCode() != null) {
											jsonMenuGroupDetail.setGroupType(foodGroupExistingDto.getMenuType().getCode());
										}
										if (soCategoryFoodGroupExisting.getGroupOrder() != null) {
											jsonMenuGroupDetail.setOrderNo(soCategoryFoodGroupExisting.getGroupOrder());	
										}
										if(foodGroupExistingDto.getLevel() != null) {
											jsonMenuGroupDetail.setLevel(foodGroupExistingDto.getLevel());
										}else {
											jsonMenuGroupDetail.setLevel(defaultGroupLevel);
										}
										jsonMenuGroupDetail.setJsonMenuItems(jsonMenuItems);
										jsonMenuGroupDetails.add(jsonMenuGroupDetail);
										
									} else {
										JsonMenuGroupDetail jsonMenuGroupDetailParent = new JsonMenuGroupDetail();
										BeanUtils.copyProperties(foodGroupExistingDto, jsonMenuGroupDetailParent);
										jsonMenuGroupDetailParent.setName(foodGroupExistingDto.getNameVn());
										if(foodGroupExistingDto.getMenuType().getCode() != null) {
											jsonMenuGroupDetailParent.setGroupType(foodGroupExistingDto.getMenuType().getCode());
										}
										if (soCategoryFoodGroupExisting.getGroupOrder() != null) {
											jsonMenuGroupDetailParent.setOrderNo(soCategoryFoodGroupExisting.getGroupOrder());	
										}
										if(foodGroupExistingDto.getLevel() != null) {
											jsonMenuGroupDetailParent.setLevel(foodGroupExistingDto.getLevel());
										}else {
											jsonMenuGroupDetailParent.setLevel(defaultGroupLevel);
										}
										
										List<FoodGroupItem> foodGroupItems = foodGroupItemRepository.findByFoodGroupAndRestaurantCode(foodGroupExisting, restaurantCode);
										if(!CollectionUtils.isEmpty(foodGroupItems)) {
											foodGroupItems.stream().forEach(foodGroupItem -> {
												if(foodGroupItem.getChanged() == null || (foodGroupItem.getChanged() != null && !foodGroupItem.getChanged().equals(StatusEnum.DELETE.status))) {
													FoodItem foodItem = foodGroupItem.getFoodItem();
													JsonMenuItem jsonMenuItem = new JsonMenuItem();
													BeanUtils.copyProperties(foodItem, jsonMenuItem);
													jsonMenuItem.setNameVn(foodItem.getName());
													jsonMenuItem.setOrderNumberItem(foodGroupItem.getItemOrder());
													jsonMenuItems.add(jsonMenuItem);
												}
											});
										}
										jsonMenuGroupDetailParent.setJsonMenuItems(jsonMenuItems);
										configMenuGroupDetails.add(jsonMenuGroupDetailParent);
										jsonMenuGroupDetails.add(jsonMenuGroupDetailParent);
									}
								}
							});
							configMenuEngineering.setJsonMenuGroups(configMenuGroupDetails);
							configMenuEngineerings.add(configMenuEngineering);
						}
					} else {
						// Trường hợp MENU có trạng thái OFF
						ConfigMenuEngineering delMenuEngineering = new ConfigMenuEngineering();
						JsonOrderCategory jsonOrderCategory = new JsonOrderCategory();
						jsonOrderCategory.setCode(soCategoryExisting.getOrderCategory().getCode());
						delMenuEngineering.setJsonOrderCategory(jsonOrderCategory);
						deleteMenuEngineerings.add(delMenuEngineering);
					}
				});
			}
			restaurantMenuDataSync.setMenuEngineerings(configMenuEngineerings);
			restaurantMenuDataSync.setMenuGroups(jsonMenuGroupDetails);
			
			// các biến cần dùng
			String coCategoryModuleType = ModuleTypeEnum.CO_CATEGORY.val;
			String coFoodItemModuleType = ModuleTypeEnum.CO_FOOD_ITEM.val;
			String imgCategoryFolderUpload = UploadImageEnum.IMG_CATEGORY_FOLDER.val;
			String videoCategoryFolderUpload = UploadImageEnum.VIDEO_CATEGORY_FOLDER.val;
			String imgCategoryPath = UploadImageEnum.IMG_CATEGORY_PATH.val;
			String videoCategoryPath = UploadImageEnum.VIDEO_MENU_PATH.val;
			String imgMenuFolderUpload = UploadImageEnum.IMG_MENU_FOLDER.val;
			String imgMenuPath = UploadImageEnum.IMG_MENU_PATH.val;
			
			// PHẦN THÔNG TIN LIÊN QUAN ĐẾN CO_CATEGORY
			List<ConfigCOCategory> configCOCategories = new ArrayList<>();
			if(!CollectionUtils.isEmpty(coCategoryExistingDtos)) {
				coCategoryExistingDtos.stream().forEach(coCategoryExistingDto ->{
					// Kiểm tra trạng thái của loại MENU. Nếu trạng thái OFF thì gọi API xóa và ngược lại
	    			if(coCategoryExistingDto.isStatus()) {
						ConfigCOCategory configCOCategory = new ConfigCOCategory();
		    			// Thông tin CO_CATEGORY
		    			BeanUtils.copyProperties(coCategoryExistingDto, configCOCategory);
		    			String orderCategoryCode = coCategoryExistingDto.getSoCategory().getOrderCategory().getCode();
		    			configCOCategory.setOrderCategoryCode(orderCategoryCode);
		    			// Lấy thông tin các CO_FOOD_GROUP_DISPLAY_CODE
						List<CoFoodGroupDisplayDto> coFoodGroupDisplayExistingDtos =  coCategoryExistingDto.getCoFoodGroupDisplays();
						if(!CollectionUtils.isEmpty(coFoodGroupDisplayExistingDtos)) {
							List<String> coFoodGroupCodes = coFoodGroupDisplayExistingDtos.stream().map(cc -> cc.getFoodGroupCode()).collect(Collectors.toList());
							configCOCategory.setMenuGroupCodes(coFoodGroupCodes);
						}
						configCOCategory.setTimeModify(System.currentTimeMillis());

						// Lấy thông tin các DIGITAL-MENU
						List<DigitalMenuDto> digitalMenuDtos = coCategoryExistingDto.getDigitalMenus();
						if (!CollectionUtils.isEmpty(digitalMenuDtos)) {
							List<JsonDigitalMenu> jsonDigitalMenus = new ArrayList<>();
							digitalMenuDtos.stream().forEach(item -> {
								JsonDigitalMenu jsonDigitalMenu = new JsonDigitalMenu();
								BeanUtils.copyProperties(item, jsonDigitalMenu);
								jsonDigitalMenus.add(jsonDigitalMenu);
							});
							configCOCategory.setDigitalMenus(jsonDigitalMenus);
						}

						// Thông tin ảnh CO_CATEGORY
		    			List<AttachmentDto> attachmentExistingDtos = attachmentRepository.findByModuleIdAndModuleType(coCategoryExistingDto.getId(), coCategoryModuleType).stream().map(attachmentMapper::entityToDto).collect(Collectors.toList());
						if(!CollectionUtils.isEmpty(attachmentExistingDtos)) {
							// Lưu thông tin ModuleId ảnh cần đồng bộ
							coCategoryIds.add(coCategoryExistingDto.getId());
							
							List<String> srcImgIntros = new ArrayList<>();
							List<String> srcVideoIntros = new ArrayList<>();
							List<String> restaurantLstVideos = new ArrayList<>();
							List<String> restaurantLstImgs = new ArrayList<>();
							
							attachmentExistingDtos.stream().forEach(attachmentExistingDto -> {
								FileContent fileContent = new FileContent();
								fileContent.setAbsolutePath(attachmentExistingDto.getAbsolutePath());
								fileContent.setFileName(attachmentExistingDto.getFileName());
								fileContent.setResCode(restaurantCode);
								fileContent.setResName(restaurantName);
								fileContent.setApiUrl(apiUrlFile);
								fileContent.setType(coCategoryModuleType);
								fileContent.setCode(orderCategoryCode);
								fileContent.setAttachmentId(attachmentExistingDto.getId());
								
								String functionType = attachmentExistingDto.getFunctionType();
								switch (functionType) {
								case "avatar":
									fileContent.setFolder(imgCategoryFolderUpload);
									configCOCategory.setSrcImg(imgCategoryPath + attachmentExistingDto.getFileName());
									fileAttachments.add(fileContent);
									break;
								case "avatar_aboutus_menu":
									srcImgIntros.add(imgCategoryPath + attachmentExistingDto.getFileName());
									fileContent.setFolder(imgCategoryFolderUpload);
									fileAttachments.add(fileContent);
									break;
								case "avatar_aboutus_res":
									restaurantLstImgs.add(imgCategoryPath + attachmentExistingDto.getFileName());
									fileContent.setFolder(imgCategoryFolderUpload);
									fileAttachments.add(fileContent);
									break;
								case "video_aboutus_menu":
									srcVideoIntros.add(videoCategoryPath + attachmentExistingDto.getFileName());
									fileContent.setFolder(videoCategoryFolderUpload);
									fileAttachments.add(fileContent);
									break;
								case "video_aboutus_res":
									restaurantLstVideos.add(videoCategoryPath + attachmentExistingDto.getFileName());
									fileContent.setFolder(videoCategoryFolderUpload);
									fileAttachments.add(fileContent);
									break;
								case "single_category":
									fileContent.setFolder(imgCategoryFolderUpload);
									configCOCategory.setSrcImgSingleCategory(imgCategoryPath + attachmentExistingDto.getFileName());
									fileAttachments.add(fileContent);
									break;
								case "multi_category":
									fileContent.setFolder(imgCategoryFolderUpload);
									configCOCategory.setSrcImgMultiCategory(imgCategoryPath + attachmentExistingDto.getFileName());
									fileAttachments.add(fileContent);
									break;
								default:
									break;
								}
							});

							// Set thông tin về ảnh trong file JSON CO_CATEGORY
							configCOCategory.setSrcImgIntros(srcImgIntros);
							configCOCategory.setSrcVideoIntros(srcVideoIntros);
							configCOCategory.setRestaurantLstVideo(restaurantLstVideos);
							configCOCategory.setRestaurantLstImg(restaurantLstImgs);
						}
						configCOCategories.add(configCOCategory);
					} else {
						// Trường hợp MENU có trạng thái OFF
	    				String orderCategoryCode = coCategoryExistingDto.getSoCategory().getOrderCategory().getCode();
	    				JsonDeleteCoCategory jsonDeleteCoCategory = new JsonDeleteCoCategory();
	    				jsonDeleteCoCategory.setCode(orderCategoryCode);
	    				jsonDeleteCoCategories.add(jsonDeleteCoCategory);
					}
				});
			}
			
			// PHẦN THÔNG TIN LIÊN QUAN ĐẾN CO_FOOD_ITEM
			List<ConfigCOMenuItem> configCOMenuItems = new ArrayList<>();
			Set<ConfigCOModifier> configCOModifiers = new HashSet<>();	
			Set<ConfigCOSpecialType> configCOSpecialTypes = new HashSet<>();
			if(!CollectionUtils.isEmpty(coFoodItemExistingDtos)) {
				coFoodItemExistingDtos.stream().forEach(coFoodItemExistingDto ->{
	    			FoodItemDto foodItemDto = coFoodItemExistingDto.getFoodItem();
	    			String foodItemCode = foodItemDto.getCode();
	    			// Thông tin CO_FOOD_ITEM
	    			ConfigCOMenuItem configCOMenuItem = new ConfigCOMenuItem();
	    			BeanUtils.copyProperties(coFoodItemExistingDto, configCOMenuItem);
					configCOMenuItem.setCode(foodItemCode);
	    			if(coFoodItemExistingDto.getInfoFoodItem() != null) {
	    				String chartSplit = new StringBuilder().append(SymbolEnum.AMPERSAND.val).append(SymbolEnum.AMPERSAND.val).toString();
	    				List<String> infoFoodItems = new ArrayList<>(Arrays.asList(coFoodItemExistingDto.getInfoFoodItem().split(chartSplit)));
						configCOMenuItem.setInfoFoodItem(infoFoodItems);
					}
	    			if(StringUtils.isNotBlank(foodItemDto.getSapCode())) {
						configCOMenuItem.setSapCode(foodItemDto.getSapCode());
					}
	    			if(coFoodItemExistingDto.getKaloGroupId() != null) {
						KaloGroupDto kaloGroupDto = kaloGroupRepository.findById(coFoodItemExistingDto.getKaloGroupId()).map(kaloGroupMapper::entityToDto).orElse(null);
						if(kaloGroupDto != null) {
							configCOMenuItem.setKaloGroupCode(kaloGroupDto.getCode());
						}
					}
	    			if(StringUtils.isNotBlank(coFoodItemExistingDto.getExtraFoodItem())) {
						String[] str = coFoodItemExistingDto.getExtraFoodItem().split(SymbolEnum.HYPHEN.val);
						JsonExtraItem jsonExtraItem = new JsonExtraItem();
						FoodItemDto foodItemExistingDto = Optional.ofNullable(foodItemRepository.findByCode(str[0])).map(foodItemMapper::entityToDto).orElse(null);
						if(foodItemExistingDto != null) {
							BeanUtils.copyProperties(foodItemExistingDto, jsonExtraItem);
							jsonExtraItem.setNameVn(foodItemExistingDto.getName());
							jsonExtraItem.setNameEn(foodItemExistingDto.getName());
							configCOMenuItem.setExtraItem(jsonExtraItem);
						}
					}
					configCOMenuItem.setTimeModify(System.currentTimeMillis());
	    			
	    			CoFoodItem coFoodItemExisting = Optional.ofNullable(coFoodItemExistingDto).map(coFoodItemMapper::dtoToEntity).orElse(null);
	    			List<RelatedFoodItemDto> relatedFoodItemExistingDtos = relatedFoodItemRepository.findByCoFoodItem(coFoodItemExisting).stream().map(relatedFoodItemMapper::entityToDto).collect(Collectors.toList());
        			List<ToppingFoodItemDto> toppingFoodItemExistingDtos = toppingFoodItemRepository.findByCoFoodItem(coFoodItemExisting).stream().map(toppingFoodItemMapper::entityToDto).collect(Collectors.toList());
        			List<CofoodItemModifierDto> coFoodItemModifierExistings = coFoodItemModifierRepository.findByCoFoodItem(coFoodItemExisting).stream().map(coFoodItemModifierMapper::entityToDto).collect(Collectors.toList());
        			List<FeatureDto> featureExistingDtos = new ArrayList<>(coFoodItemExisting.getFeatures()).stream().map(featureMapper::entityToDto).collect(Collectors.toList());
        			
        			// convert các thông tin liên quan: Món liên quan, SIZE 
    				List<String> relatedFoodItemCodes = new ArrayList<>();
    				List<JsonItemSize> itemSizes = new ArrayList<>();
    				if(!CollectionUtils.isEmpty(relatedFoodItemExistingDtos)) {
    					relatedFoodItemExistingDtos.stream().forEach(item ->{
    						if(item.getType().equals(FoodTypeEnum.RELATED_FOODITEM.val)) {
    							relatedFoodItemCodes.add(item.getFoodItemCode());
    						}
    						if(item.getType().equals(FoodTypeEnum.SIZE_FOODITEM.val)) {
    							JsonItemSize jsonItemSize = new JsonItemSize();
    							BeanUtils.copyProperties(item, jsonItemSize);
    							jsonItemSize.setFoodItemNameEn(item.getFoodItemName());
    							jsonItemSize.setFoodItemNameVn(item.getFoodItemName());
    							itemSizes.add(jsonItemSize);
    						}
    					});
    				}
    				if(!CollectionUtils.isEmpty(relatedFoodItemCodes)) {
						configCOMenuItem.setRelatedItems(relatedFoodItemCodes);
					}
    				if(!CollectionUtils.isEmpty(itemSizes)) {
						configCOMenuItem.setItemSizes(itemSizes);
					}
        			
    				// convert các thông tin liên quan: TOPPING
    				List<JsonToppingFoodItem> jsonToppingFoodItems = new ArrayList<>();
    				if(!CollectionUtils.isEmpty(toppingFoodItemExistingDtos)) {
    					toppingFoodItemExistingDtos.stream().forEach(item ->{
    						JsonToppingFoodItem jsonToppingFoodItem = new JsonToppingFoodItem();
    						BeanUtils.copyProperties(item, jsonToppingFoodItem);
    						jsonToppingFoodItem.setFoodItemNameVn(item.getFoodItemName());
    						jsonToppingFoodItem.setFoodItemNameEn(item.getFoodItemName());
    						jsonToppingFoodItems.add(jsonToppingFoodItem);
    					});
    				}
    				if(!CollectionUtils.isEmpty(jsonToppingFoodItems)) {
						configCOMenuItem.setItemToppings(jsonToppingFoodItems);
					}
    				
    				// convert các thông tin liên quan: MODIFIER
    				List<ConfigCOModifier> coModifiers = new ArrayList<>();
    				if(!CollectionUtils.isEmpty(coFoodItemModifierExistings)) {
    					coFoodItemModifierExistings.stream().forEach(m -> {
    						ConfigCOModifier configCOModifier = new ConfigCOModifier();
    						configCOModifier.setCode(m.getModifier().getCode());
    						configCOModifier.setName(m.getModifier().getName());
    						configCOModifier.setType(m.getType());
    						configCOModifier.setNumberOfChili(m.getNumberOfChili());
    						coModifiers.add(configCOModifier);
    						configCOModifiers.add(configCOModifier);
    					});
    				}
    				if(!CollectionUtils.isEmpty(coModifiers)) {
						configCOMenuItem.setModifiers(coModifiers);
					}
    				
    				// convert các thông tin liên quan: FEATURE
    				List<ConfigCOSpecialType> coSpecialTypes = new ArrayList<>();
    				if(!CollectionUtils.isEmpty(featureExistingDtos)) {
    					featureExistingDtos.stream().forEach(item -> {
    						ConfigCOSpecialType configCOSpecialType = new ConfigCOSpecialType();
    						BeanUtils.copyProperties(item, configCOSpecialType);
    						coSpecialTypes.add(configCOSpecialType);
    						configCOSpecialTypes.add(configCOSpecialType);
    					});
    				}
    				if(!CollectionUtils.isEmpty(coSpecialTypes)) {
						configCOMenuItem.setItemSpecialTypes(coSpecialTypes);
					}
    				
    				// menu item detail
    				JsonCOMenuItem jsonCOMenuItem = new JsonCOMenuItem();
    				BeanUtils.copyProperties(coFoodItemExisting, jsonCOMenuItem);
    				jsonCOMenuItem.setCode(foodItemCode);
    				configCOMenuItem.getCoMenuItemDetails().add(jsonCOMenuItem);
    				
    				// Thông tin ảnh CO_FOODITEM
	    			List<AttachmentDto> attachmentExistingDtos = attachmentRepository.findByModuleIdAndModuleType(coFoodItemExisting.getId(), coFoodItemModuleType).stream().map(attachmentMapper::entityToDto).collect(Collectors.toList());
					if(!CollectionUtils.isEmpty(attachmentExistingDtos)) {
						// Lưu thông tin ModuleId ảnh cần đồng bộ
						coFoodItemIds.add(coFoodItemExisting.getId());
						attachmentExistingDtos.stream().forEach(attachmentExistingDto -> {
							FileContent fileContent = new FileContent();
							fileContent.setAbsolutePath(attachmentExistingDto.getAbsolutePath());
							fileContent.setFileName(attachmentExistingDto.getFileName());
							fileContent.setResCode(restaurantCode);
							fileContent.setResName(restaurantName);
							fileContent.setApiUrl(apiUrlFile);
							fileContent.setType(coCategoryModuleType);
							fileContent.setCode(foodItemCode);
							fileContent.setFolder(imgMenuFolderUpload);
							fileContent.setAttachmentId(attachmentExistingDto.getId());
							
							String functionType = attachmentExistingDto.getFunctionType();
							String urlImage = imgMenuPath + attachmentExistingDto.getFileName();
							switch (functionType) {
							case "avatar":
								configCOMenuItem.setSrcImgThumbnail(urlImage);
								configCOMenuItem.setSrcImg(urlImage);
								fileAttachments.add(fileContent);
								break;
							case "half_image":
								configCOMenuItem.setSrcImgHalf(urlImage);
								fileAttachments.add(fileContent);
								break;
							case "topping_image":
								configCOMenuItem.setSrcImgTopping(urlImage);
								fileAttachments.add(fileContent);
								break;
							case "group_image":
								configCOMenuItem.setSrcImgGroup(urlImage);
								fileAttachments.add(fileContent);
								break;
							case "group_hidden_image":
								configCOMenuItem.setSrcImgGroupHidden(urlImage);
								fileAttachments.add(fileContent);
								break;
							case "horizontal_image":
								configCOMenuItem.setSrcImg2xHorizontal(urlImage);
								fileAttachments.add(fileContent);
								break;
							case "vertical_image":
								configCOMenuItem.setSrcImg2xVertical(urlImage);
								fileAttachments.add(fileContent);
								break;
							case "quarter_image":
								configCOMenuItem.setSrcImgQuarter(urlImage);
								fileAttachments.add(fileContent);
								break;
							case "drink_image":
								configCOMenuItem.setSrcImgSquare(urlImage);
								fileAttachments.add(fileContent);
								break;
							case "qr_order_image":
								configCOMenuItem.setSrcImgQr(urlImage);
								configCOMenuItem.setUrlImgQr(new StringBuilder().append(gatewayImageUrl).append(attachmentExistingDto.getUrl()).toString());
								break;
							default:
								break;
							}
						});
					}
					configCOMenuItems.add(configCOMenuItem);
	    		});
			}
			
			// PHẦN THÔNG TIN LIÊN QUAN ĐẾN CONFIG_QR_ORDER
			ConfigQrOrderDto configQrOrderExistingDto = Optional.ofNullable(configQrOrderRepository.findByRestaurantCode(String.valueOf(restaurantCode))).map(configQrOrderMapper::entityToDto).orElse(null);
			ConfigRestaurantInfo configRestaurantInfo = new ConfigRestaurantInfo();
			if(configQrOrderExistingDto != null) {
				BeanUtils.copyProperties(configQrOrderExistingDto, configRestaurantInfo);
				restaurantMenuDataSync.setConfigRestaurantInfo(configRestaurantInfo);
			}
			restaurantMenuDataSync.setDeleteCoCategories(jsonDeleteCoCategories);
			restaurantMenuDataSync.setDeleteSoCategoryItems(deleteMenuEngineerings);
			restaurantMenuDataSync.setCoMenuItems(configCOMenuItems);
			restaurantMenuDataSync.setCoModifiers(new ArrayList<>(configCOModifiers));
			restaurantMenuDataSync.setCoSpecials(new ArrayList<>(configCOSpecialTypes));
			restaurantMenuDataSync.setFileContents(fileAttachments);
			restaurantMenuDataSync.setCoCategories(configCOCategories);
			restaurantMenuDataSync.setResCode(String.valueOf(restaurantCode));
			restaurantMenuDataSync.setFoodGroupIds(foodGroupIds);
			restaurantMenuDataSync.setCoFoodItemIds(coFoodItemIds);
			restaurantMenuDataSync.setCoCategoryIds(coCategoryIds);
			
		} catch (Exception e) {
			log.error("ERROR PROCESS: CONVERT BUSINESS_DATA TO JSON EXCEPTION: {}", e);
		}
		return restaurantMenuDataSync;
	}


	/*
	 * Lấy tất cả dữ liệu MASTER_DATA
	 */
	public Map<String, Object> getMaterDatas() {
		log.debug("PROCESS: GET ALL MASTER_DATA");
		Map<String, Object> map = new HashedMap<>();
		List<VersionDto> versionExistingDtos = new ArrayList<>();
		try {
			Integer rate = 10000;
			Integer stepOfVersion = 1;
			
			List<VersionDto> versionDtos = versionRepository.findAll().stream().map(versionMapper::entityToDto).collect(Collectors.toList());
			if(!CollectionUtils.isEmpty(versionDtos)) {
				versionDtos.stream().forEach(versionDto ->{
					Integer versionNo = versionDto.getVersionNo() + stepOfVersion;
					MasterDataSync masterDataSync = new MasterDataSync();
					masterDataSync.setVersion(versionNo);
					String tableName = versionDto.getTableName();
					switch (tableName) {
					case "CATEGORY":
						List<MasterDataCategList> masterDataCategLists = categoryRepository.findActives().stream().map(src -> {
							MasterDataCategList dest = new MasterDataCategList();
							BeanUtils.copyProperties(src, dest);
							if (src.getParentId() == null) {
								dest.setParentId(0L);
							}
							return dest;
						}).collect(Collectors.toList());
						masterDataSync.setMasterDataCategLists(masterDataCategLists);
						map.put(MapKeyEnum.CATEGORY.key, masterDataSync);
						break;
					case "CURRENCY":
						List<MasterDataCurrency> masterDataCurrencies = currencyRepository.findActives().stream().map(src -> {
							MasterDataCurrency dest = new MasterDataCurrency();
							BeanUtils.copyProperties(src, dest);
							return dest;
						}).collect(Collectors.toList());
						masterDataSync.setMasterDataCurrencies(masterDataCurrencies);
						map.put(MapKeyEnum.CURRENCY.key, masterDataSync);
						break;
					case "FOOD_ITEM":
						List<MasterDataMenuItem> masterDataMenuItems = foodItemRepository.findActives().stream().map(src -> {
							MasterDataMenuItem dest = new MasterDataMenuItem();
							BeanUtils.copyProperties(src, dest);
							if(src.getCookMins() == null) {
								dest.setCookMins(0);
							}
							return dest;
						}).collect(Collectors.toList());
						masterDataSync.setMasterDataMenuItems(masterDataMenuItems);
						map.put(MapKeyEnum.FOOD_ITEM.key, masterDataSync);
						break;
					case "MODI_GROUP":
						List<MasterDataModiGroups> masterDataModiGroups = modiGroupRepository.findActives().stream().map(src -> {
							MasterDataModiGroups dest = new MasterDataModiGroups();
							BeanUtils.copyProperties(src, dest);
							if (src.getParentId() == null) {
								dest.setParentId(0L);
							}
							return dest;
						}).collect(Collectors.toList());
						masterDataSync.setMasterDataModiGroups(masterDataModiGroups);
						map.put(MapKeyEnum.MODI_GROUP.key, masterDataSync);
						break;
					case "MODI_SCHEME":
						List<MasterDataModiScheme> masterDataModiSchemes = modiSchemeRepository.findActives().stream().map(src -> {
							MasterDataModiScheme dest = new MasterDataModiScheme();
							BeanUtils.copyProperties(src, dest);
							if (src.getParentId() == null) {
								dest.setParentId(0L);
							}
							return dest;
						}).collect(Collectors.toList());
						masterDataSync.setMasterDataModiSchemes(masterDataModiSchemes);
						map.put(MapKeyEnum.MODI_SCHEME.key, masterDataSync);
						break;
					case "MODI_SCHEME_DETAIL":
						List<MasterDataModiSchemeDetail> masterDataModiSchemeDetails = modiSchemeDetailRepository.findAll().stream().map(src -> {
							MasterDataModiSchemeDetail dest = new MasterDataModiSchemeDetail();
							BeanUtils.copyProperties(src, dest);
							if (src.getParentId() == null) {
								dest.setParentId(0L);
							}
							return dest;
						}).collect(Collectors.toList());
						masterDataSync.setMasterDataModiSchemeDetails(masterDataModiSchemeDetails);
						map.put(MapKeyEnum.MODI_SCHEME_DETAIL.key, masterDataSync);
						break;
					case "MODIFIER":
						List<MasterDataModifier> masterDataModifiers = modifierRepository.findActives().stream().map(src -> {
							MasterDataModifier dest = new MasterDataModifier();
							BeanUtils.copyProperties(src, dest);
							if (src.getParentId() == null) {
								dest.setParentId(0L);
							}
							return dest;
						}).collect(Collectors.toList());
						masterDataSync.setMasterDataModifiers(masterDataModifiers);
						map.put(MapKeyEnum.MODIFIER.key, masterDataSync);
						break;
					case "ORDER_CATEGORY":
						List<MasterDataOrderCategory> masterDataOrderCategories = orderCategoryRepository.findActives().stream().map(src -> {
							MasterDataOrderCategory dest = new MasterDataOrderCategory();
							BeanUtils.copyProperties(src, dest);
							if (src.getParentId() == null) {
								dest.setParentId(0L);
							}
							return dest;
						}).collect(Collectors.toList());
						masterDataSync.setMasterDataOrderCategories(masterDataOrderCategories);
						map.put(MapKeyEnum.ORDER_CATEGORY.key, masterDataSync);
						break;
					case "ORDER_TYPE":
						List<MasterDataOrderType> masterDataOrderTypes = orderTypeRepository.findActives().stream().map(src -> {
							MasterDataOrderType dest = new MasterDataOrderType();
							BeanUtils.copyProperties(src, dest);
							if (src.getParentId() == null) {
								dest.setParentId(0L);
							}
							return dest;
						}).collect(Collectors.toList());
						masterDataSync.setMasterDataOrderTypes(masterDataOrderTypes);
						map.put(MapKeyEnum.ORDER_TYPE.key, masterDataSync);
						break;
					case "ORDER_VOID":
						List<MasterDataOrderVoid> masterDataOrderVoids = orderVoidRepository.findActives().stream().map(src -> {
							MasterDataOrderVoid dest = new MasterDataOrderVoid();
							BeanUtils.copyProperties(src, dest);
							if (src.getParentId() == null) {
								dest.setParentId(0L);
							}
							return dest;
						}).collect(Collectors.toList());
						masterDataSync.setMasterDataOrderVoids(masterDataOrderVoids);
						map.put(MapKeyEnum.ORDER_VOID.key, masterDataSync);
						break;
					case "CURRENCY_RATE":
						List<MasterDataCurrencyRate> masterDataCurrencyRates = currencyRateRepository.findActives().stream().map(src -> {
							MasterDataCurrencyRate dest = new MasterDataCurrencyRate();
							BeanUtils.copyProperties(src, dest);
							Long newRate = dest.getRate().longValue()/rate;
							dest.setRate(newRate);
							return dest;
						}).collect(Collectors.toList());
						masterDataSync.setMasterDataCurrencyRates(masterDataCurrencyRates);
						map.put(MapKeyEnum.CURRENCY_RATE.key, masterDataSync);
						break;
					case "HALL_PLAN":
						List<MasterDataHallPlan> masterDataHallPlans = hallplanRepository.findActives().stream().map(src -> {
							MasterDataHallPlan dest = new MasterDataHallPlan();
							BeanUtils.copyProperties(src, dest);
							if (src.getParentId() == null) {
								dest.setParentId(0L);
							}
							return dest;
						}).collect(Collectors.toList());
						masterDataSync.setMasterDataHallPlans(masterDataHallPlans);
						map.put(MapKeyEnum.HALL_PLAN.key, masterDataSync);
						break;
					case "TABLE_KITCHEN":
						List<MasterDataTable> masterDataTables = tableKitchenRepository.findActives().stream().map(src -> {
							MasterDataTable dest = new MasterDataTable();
							BeanUtils.copyProperties(src, dest);
							if (src.getParentId() == null) {
								dest.setParentId(0L);
							}
							return dest;
						}).collect(Collectors.toList());
						masterDataSync.setMasterDataTables(masterDataTables);
						map.put(MapKeyEnum.TABLE_KITCHEN.key, masterDataSync);
						break;
					case "GUEST_TYPE":
						List<MasterDataGuestType> masterDataGuestTypes = guestTypeRepository.findActives().stream().map(src -> {
							MasterDataGuestType dest = new MasterDataGuestType();
							BeanUtils.copyProperties(src, dest);
							if (src.getParentId() == null) {
								dest.setParentId(0L);
							}
							return dest;
						}).collect(Collectors.toList());
						masterDataSync.setMasterDataGuestTypes(masterDataGuestTypes);
						map.put(MapKeyEnum.GUEST_TYPE.key, masterDataSync);
						break;
					case "EMPLOYEE":
						List<MasterDataEmployee> masterDataEmployees = employeeRepository.findActives().stream().map(src -> {
							MasterDataEmployee dest = new MasterDataEmployee();
							BeanUtils.copyProperties(src, dest);
							if (src.getParentId() == null) {
								dest.setParentId(0L);
							}
							return dest;
						}).collect(Collectors.toList());
						masterDataSync.setMasterDataEmployees(masterDataEmployees);
						map.put(MapKeyEnum.EMPLOYEE.key, masterDataSync);
						break;
					case "RESTAURANT":
						List<MasterDataRestaurant> masterDataRestaurants = restaurantMasterRepository.findActives().stream().map(src -> {
							MasterDataRestaurant dest = new MasterDataRestaurant();
							BeanUtils.copyProperties(src, dest);
							return dest;
						}).collect(Collectors.toList());
						masterDataSync.setMasterDataRestaurants(masterDataRestaurants);
						map.put(MapKeyEnum.RESTAURANT.key, masterDataSync);
						break;
					default:
						break;
					}
					versionDto.setVersionNo(versionNo);
					versionExistingDtos.add(versionDto);
				});
			}
			map.put(MapKeyEnum.VERSIONS.key, versionExistingDtos);
		} catch (Exception e) {
			log.error("ERROR PROCESS: GET ALL MASTER_DATA EXCEPTION: {}", e);
		}
		return map;
	}

}
