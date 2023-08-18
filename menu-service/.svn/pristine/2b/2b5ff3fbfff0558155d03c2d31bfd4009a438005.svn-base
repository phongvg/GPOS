package com.gg.gpos.menu.manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gg.gpos.common.constant.FoodGroupLevelEnum;
import com.gg.gpos.common.constant.FoodTypeEnum;
import com.gg.gpos.common.constant.FunctionTypeEnum;
import com.gg.gpos.common.constant.MenuTypeEnum;
import com.gg.gpos.common.constant.ModuleTypeEnum;
import com.gg.gpos.common.constant.StatusEnum;
import com.gg.gpos.common.constant.SymbolEnum;
import com.gg.gpos.common.constant.SystemParamEnum;
import com.gg.gpos.common.constant.UploadImageEnum;
import com.gg.gpos.common.json.ConfigCOCategory;
import com.gg.gpos.common.json.ConfigCOMenuItem;
import com.gg.gpos.common.json.ConfigCOModifier;
import com.gg.gpos.common.json.ConfigCOSpecialType;
import com.gg.gpos.common.json.ConfigMenuEngineering;
import com.gg.gpos.common.json.FileContent;
import com.gg.gpos.common.json.JsonCOMenuItem;
import com.gg.gpos.common.json.JsonChildDish;
import com.gg.gpos.common.json.JsonDefaultDish;
import com.gg.gpos.common.json.JsonDishItem;
import com.gg.gpos.common.json.JsonExtraItem;
import com.gg.gpos.common.json.JsonItemSize;
import com.gg.gpos.common.json.JsonMenuGroupDetail;
import com.gg.gpos.common.json.JsonMenuItem;
import com.gg.gpos.common.json.JsonOrderCategory;
import com.gg.gpos.common.json.JsonToppingFoodItem;
import com.gg.gpos.common.json.MasterDataCategList;
import com.gg.gpos.common.json.MasterDataCurrency;
import com.gg.gpos.common.json.MasterDataCurrencyRate;
import com.gg.gpos.common.json.MasterDataEmployee;
import com.gg.gpos.common.json.MasterDataGuestType;
import com.gg.gpos.common.json.MasterDataHallPlan;
import com.gg.gpos.common.json.MasterDataMenuItem;
import com.gg.gpos.common.json.MasterDataModiGroups;
import com.gg.gpos.common.json.MasterDataModiScheme;
import com.gg.gpos.common.json.MasterDataModiSchemeDetail;
import com.gg.gpos.common.json.MasterDataModifier;
import com.gg.gpos.common.json.MasterDataOrderCategory;
import com.gg.gpos.common.json.MasterDataOrderType;
import com.gg.gpos.common.json.MasterDataOrderVoid;
import com.gg.gpos.common.json.MasterDataRestaurant;
import com.gg.gpos.common.json.MasterDataSync;
import com.gg.gpos.common.json.MasterDataTable;
import com.gg.gpos.common.json.RestaurantSync;
import com.gg.gpos.menu.entity.CoCategory;
import com.gg.gpos.menu.entity.CoFoodGroupDisplay;
import com.gg.gpos.menu.entity.CoFoodItem;
import com.gg.gpos.menu.entity.CoFoodItemModifier;
import com.gg.gpos.menu.entity.FoodGroup;
import com.gg.gpos.menu.entity.FoodGroupItem;
import com.gg.gpos.menu.entity.FoodItem;
import com.gg.gpos.menu.entity.KaloGroup;
import com.gg.gpos.menu.entity.OrderCategory;
import com.gg.gpos.menu.entity.RelatedFoodItem;
import com.gg.gpos.menu.entity.SoCategory;
import com.gg.gpos.menu.entity.SoCategoryFoodGroup;
import com.gg.gpos.menu.entity.ToppingFoodItem;
import com.gg.gpos.menu.repository.CategoryRepository;
import com.gg.gpos.menu.repository.CoCategoryRepository;
import com.gg.gpos.menu.repository.CoFoodGroupDisplayRepository;
import com.gg.gpos.menu.repository.CoFoodItemModifierRepository;
import com.gg.gpos.menu.repository.CurrencyRateRepository;
import com.gg.gpos.menu.repository.CurrencyRepository;
import com.gg.gpos.menu.repository.EmployeeRepository;
import com.gg.gpos.menu.repository.FoodGroupItemRepository;
import com.gg.gpos.menu.repository.FoodGroupRepository;
import com.gg.gpos.menu.repository.FoodItemRepository;
import com.gg.gpos.menu.repository.GuestTypeRepository;
import com.gg.gpos.menu.repository.HallplanRepository;
import com.gg.gpos.menu.repository.KaloGroupRepository;
import com.gg.gpos.menu.repository.ModiGroupRepository;
import com.gg.gpos.menu.repository.ModiSchemeDetailRepository;
import com.gg.gpos.menu.repository.ModiSchemeRepository;
import com.gg.gpos.menu.repository.ModifierRepository;
import com.gg.gpos.menu.repository.OrderCategoryRepository;
import com.gg.gpos.menu.repository.OrderTypeRepository;
import com.gg.gpos.menu.repository.OrderVoidRepository;
import com.gg.gpos.menu.repository.RelatedFoodItemRepository;
import com.gg.gpos.menu.repository.RestaurantMasterRepository;
import com.gg.gpos.menu.repository.SoCategoryFoodGroupRepository;
import com.gg.gpos.menu.repository.TableKitchenRepository;
import com.gg.gpos.menu.repository.ToppingFoodItemRepository;
import com.gg.gpos.reference.entity.Attachment;
import com.gg.gpos.reference.repository.AttachmentRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class DataSyncManager {
	@Autowired
	private SoCategoryFoodGroupRepository soCategoryFoodGroupRepository;
	@Autowired
	private FoodGroupItemRepository foodGroupItemRepository;
	@Autowired
	private CoCategoryRepository coCategoryRepository;
	@Autowired
	private RelatedFoodItemRepository relatedFoodItemRepository;
	@Autowired
	private FoodGroupRepository foodGroupRepository;
	@Autowired
	private FoodItemRepository foodItemRepository;
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
	private CoFoodGroupDisplayRepository coFoodGroupDisplayRepository;
	@Autowired
	private ToppingFoodItemRepository toppingFoodItemRepository;
	@Autowired
	private AttachmentRepository attachmentRepository;
	@Autowired
	private KaloGroupRepository kaloGroupRepository;
	@Autowired
	private CoFoodItemModifierRepository coFoodItemModifierRepository;
	

	// prepare data CoCategory
	private RestaurantSync prepareDataCoCategory(List<CoCategory> coCategories,Integer resCode,String apiUrl,String resName){
		List<ConfigCOCategory> syncCOCategories = new ArrayList<>();
		RestaurantSync restaurantSync = new RestaurantSync();
		List<FileContent> files = new ArrayList<>();
		if(coCategories != null && !coCategories.isEmpty()) {
			coCategories.stream().forEach(coCategory ->{
				ConfigCOCategory configCOCategory = new ConfigCOCategory();
				String orderCategoryCode = coCategory.getSoCategory().getOrderCategory().getCode();
				List<CoFoodGroupDisplay> coFoodGroupDisplays = coFoodGroupDisplayRepository.findByCoCategory(coCategory); 
				List<String> coFoodGroupCodes = new ArrayList<>(); 
				Long coCategoryId = coCategory.getId();
				
				if(coFoodGroupDisplays != null && !coFoodGroupDisplays.isEmpty()) {
					coFoodGroupCodes = coFoodGroupDisplays.stream().map(cc -> cc.getFoodGroupCode()).collect(Collectors.toList());
				}
				if(orderCategoryCode != null) {
					configCOCategory.setOrderCategoryCode(orderCategoryCode);
				}
				if(coCategoryId != null) {
					configCOCategory.setId(coCategoryId);
				}
				if(coCategory.getNameVn() != null) {
					configCOCategory.setNameVn(coCategory.getNameVn());
				}
				if(coCategory.getNameEn() != null) {
					configCOCategory.setNameEn(coCategory.getNameEn());
				}
				if(coCategory.getDescVn() != null) {
					configCOCategory.setDescVn(coCategory.getDescVn());
				}
				if(coCategory.getDescEn() != null) {
					configCOCategory.setDescEn(coCategory.getDescEn());
				}
				if(coCategory.getAlacarteLabel() != null) {
					configCOCategory.setAlacarteLabel(coCategory.getAlacarteLabel());
				}
				if(coCategory.getBuffetLabel() != null) {
					configCOCategory.setBuffetLabel(coCategory.getBuffetLabel());
				}
				if(coCategory.getDrinksLabel() != null) {
					configCOCategory.setDrinksLabel(coCategory.getDrinksLabel());
				}
				if(coCategory.getType() != null) {
					configCOCategory.setType(coCategory.getType());
				}
				if(coCategory.getAlacarteLabelEn() != null) {
					configCOCategory.setAlacarteLabelEn(coCategory.getAlacarteLabelEn());
				}
				if(coCategory.getBuffetLabelEn() != null) {
					configCOCategory.setBuffetLabelEn(coCategory.getBuffetLabelEn());
				}
				if(coCategory.getDrinksLabelEn() != null) {
					configCOCategory.setDrinksLabelEn(coCategory.getDrinksLabelEn());
				}
				if(coCategory.getPhotoDisplayPosition() != null) {
					configCOCategory.setPhotoDisplayPosition(coCategory.getPhotoDisplayPosition());
				}
				if(coCategory.getAverageAmount() != null) {
					configCOCategory.setAverageAmount(coCategory.getAverageAmount());
				}
				if(coCategory.getNote() != null) {
					configCOCategory.setNote(coCategory.getNote());
				}
				if(coFoodGroupCodes != null && !coFoodGroupCodes.isEmpty()) {
					configCOCategory.setMenuGroupCodes(coFoodGroupCodes);
				}
				configCOCategory.setTimeModify(System.currentTimeMillis());
				//pick-up attachment
				List<Attachment> avatars = attachmentRepository.findByModuleIdAndModuleTypeAndFunctionType(coCategoryId, ModuleTypeEnum.CO_CATEGORY.val, FunctionTypeEnum.AVATAR.val);
				if (avatars != null && !avatars.isEmpty()) {
					configCOCategory.setSrcImg(UploadImageEnum.IMG_CATEGORY_PATH.val + avatars.get(0).getFileName());
					FileContent file = new FileContent();
					file.setAbsolutePath(avatars.get(0).getAbsolutePath());
					file.setFileName(avatars.get(0).getFileName());
					file.setFolder( UploadImageEnum.IMG_CATEGORY_FOLDER.val);
					file.setResCode(resCode);
					file.setResName(resName);
					file.setApiUrl(apiUrl);
					file.setType(ModuleTypeEnum.CO_CATEGORY.val);
					file.setCode(orderCategoryCode);
					file.setAttachmentId(avatars.get(0).getId());
					files.add(file);
				}
				
				List<Attachment> singleCategoryPhotots = attachmentRepository.findByModuleIdAndModuleTypeAndFunctionType(coCategoryId, ModuleTypeEnum.CO_CATEGORY.val, FunctionTypeEnum.SINGLE_CATEGORY.val);
				if (singleCategoryPhotots != null && !singleCategoryPhotots.isEmpty()) {
					configCOCategory.setSrcImgSingleCategory(UploadImageEnum.IMG_CATEGORY_PATH.val + singleCategoryPhotots.get(0).getFileName());
					FileContent file = new FileContent();
					file.setAbsolutePath(singleCategoryPhotots.get(0).getAbsolutePath());
					file.setFileName(singleCategoryPhotots.get(0).getFileName());
					file.setFolder( UploadImageEnum.IMG_CATEGORY_FOLDER.val);
					file.setResCode(resCode);
					file.setResName(resName);
					file.setApiUrl(apiUrl);
					file.setType(ModuleTypeEnum.CO_CATEGORY.val);
					file.setCode(orderCategoryCode);
					file.setAttachmentId(singleCategoryPhotots.get(0).getId());
					files.add(file);
				}
				
				List<Attachment> multiCategoryPhotots = attachmentRepository.findByModuleIdAndModuleTypeAndFunctionType(coCategoryId, ModuleTypeEnum.CO_CATEGORY.val, FunctionTypeEnum.MULTI_CATEGORY.val);
				if (multiCategoryPhotots != null && !multiCategoryPhotots.isEmpty()) {
					configCOCategory.setSrcImgMultiCategory(UploadImageEnum.IMG_CATEGORY_PATH.val + multiCategoryPhotots.get(0).getFileName());
					FileContent file = new FileContent();
					file.setAbsolutePath(multiCategoryPhotots.get(0).getAbsolutePath());
					file.setFileName(multiCategoryPhotots.get(0).getFileName());
					file.setFolder( UploadImageEnum.IMG_CATEGORY_FOLDER.val);
					file.setResCode(resCode);
					file.setResName(resName);
					file.setApiUrl(apiUrl);
					file.setType(ModuleTypeEnum.CO_CATEGORY.val);
					file.setCode(orderCategoryCode);
					file.setAttachmentId(multiCategoryPhotots.get(0).getId());
					files.add(file);
				}
				
				List<Attachment> images = attachmentRepository.findByModuleIdAndModuleTypeAndFunctionType(coCategoryId, ModuleTypeEnum.CO_CATEGORY.val, FunctionTypeEnum.AVATAR_ABOUTUS_MENU.val);
				if (images != null && !images.isEmpty()) {
					configCOCategory.setSrcImgIntros(images.stream().map(i -> UploadImageEnum.IMG_CATEGORY_PATH.val + i.getFileName()).collect(Collectors.toList()));
					images.stream().forEach(i -> {
		    			FileContent file = new FileContent();
		    			file.setAbsolutePath(i.getAbsolutePath());
		    			file.setFileName(i.getFileName());
		    			file.setFolder(UploadImageEnum.IMG_CATEGORY_FOLDER.val);
		    			file.setResCode(resCode);
		    			file.setResName(resName);
		    			file.setApiUrl(apiUrl);
		    			file.setType(ModuleTypeEnum.CO_CATEGORY.val);
						file.setCode(orderCategoryCode);
						file.setAttachmentId(i.getId());
		    			files.add(file);
					});
				}
				List<Attachment> videoIntroMenus = attachmentRepository.findByModuleIdAndModuleTypeAndFunctionType(coCategoryId,ModuleTypeEnum.CO_CATEGORY.val, FunctionTypeEnum.VIDEO_ABOUTUS_MENU.val);
				if (videoIntroMenus != null && !videoIntroMenus.isEmpty()) {
					configCOCategory.setSrcVideoIntros(videoIntroMenus.stream().map(i -> UploadImageEnum.VIDEO_MENU_PATH.val + i.getFileName()).collect(Collectors.toList()));
					videoIntroMenus.stream().forEach(i -> {
		    			FileContent file = new FileContent();
		    			file.setAbsolutePath(i.getAbsolutePath());
		    			file.setFileName(i.getFileName());
		    			file.setFolder(UploadImageEnum.VIDEO_CATEGORY_FOLDER.val);
		    			file.setResCode(resCode);
		    			file.setResName(resName);
		    			file.setApiUrl(apiUrl);
		    			file.setType(ModuleTypeEnum.CO_CATEGORY.val);
						file.setCode(orderCategoryCode);
						file.setAttachmentId(i.getId());
		    			files.add(file);
					});
				}
				
				List<Attachment> videoIntroRes = attachmentRepository.findByModuleIdAndModuleTypeAndFunctionType(coCategoryId, ModuleTypeEnum.CO_CATEGORY.val, FunctionTypeEnum.VIDEO_ABOUTUS_RES.val);
				if (videoIntroRes != null && !videoIntroRes.isEmpty()) {
					configCOCategory.setRestaurantLstVideo(videoIntroRes.stream().map(i -> UploadImageEnum.VIDEO_MENU_PATH.val + i.getFileName()).collect(Collectors.toList()));
					videoIntroRes.stream().forEach(i -> {
		    			FileContent file = new FileContent();
		    			file.setAbsolutePath(i.getAbsolutePath());
		    			file.setFileName(i.getFileName());
		    			file.setFolder(UploadImageEnum.VIDEO_CATEGORY_FOLDER.val);
		    			file.setResCode(resCode);
		    			file.setResName(resName);
		    			file.setApiUrl(apiUrl);
		    			file.setType(ModuleTypeEnum.CO_CATEGORY.val);
						file.setCode(orderCategoryCode);
						file.setAttachmentId(i.getId());
		    			files.add(file);
					});
				}
				
				List<Attachment> imgIntroRes = attachmentRepository.findByModuleIdAndModuleTypeAndFunctionType(coCategoryId, ModuleTypeEnum.CO_CATEGORY.val, FunctionTypeEnum.AVATAR_ABOUTUS_RES.val);
				if (imgIntroRes != null && !imgIntroRes.isEmpty()) {
					configCOCategory.setRestaurantLstImg(imgIntroRes.stream().map(i -> UploadImageEnum.IMG_CATEGORY_PATH.val + i.getFileName()).collect(Collectors.toList()));
					imgIntroRes.stream().forEach(i -> {
		    			FileContent file = new FileContent();
		    			file.setAbsolutePath(i.getAbsolutePath());
		    			file.setFileName(i.getFileName());
		    			file.setFolder(UploadImageEnum.IMG_CATEGORY_FOLDER.val);
		    			file.setResCode(resCode);
		    			file.setResName(resName);
		    			file.setApiUrl(apiUrl);
		    			file.setType(ModuleTypeEnum.CO_CATEGORY.val);
						file.setCode(orderCategoryCode);
						file.setAttachmentId(i.getId());
		    			files.add(file);
					});
				}
				// pick-up attachment data
				syncCOCategories.add(configCOCategory);
			});
		}
		restaurantSync.setCoCategories(syncCOCategories);
		restaurantSync.setFileContents(files);
		return restaurantSync;
	}
	
	// prepare data CoFoodItem
	private RestaurantSync prepareDataCoFoodItem(List<CoFoodItem> coFoodItems,Integer resCode,String apiUrl,String resName) {
		RestaurantSync restaurantSync = new RestaurantSync();
		List<FileContent> files = new ArrayList<>();
		List<ConfigCOMenuItem> jsonCOMenuItems = new ArrayList<>();
		Set<ConfigCOModifier> jsonCOModifiers = new HashSet<>();	// using Set to not allow duplicated object
		Set<ConfigCOSpecialType> jsonCOConfigCOSpecialTypes = new HashSet<>();
		if(coFoodItems != null && !coFoodItems.isEmpty()) {
			coFoodItems.stream().forEach(cfi -> {
				FoodItem foodItem = cfi.getFoodItem();
				String code = foodItem.getCode();
				ConfigCOMenuItem configCOMenuItem = new ConfigCOMenuItem();
				List<String> infoFoodItems = new ArrayList<>();
				Long coFoodItemId = cfi.getId();
				if(cfi.getInfoFoodItem() != null) {
					infoFoodItems = new ArrayList<>(Arrays.asList(cfi.getInfoFoodItem().split(SymbolEnum.COMMA.val)));
					configCOMenuItem.setInfoFoodItem(infoFoodItems);
				}
				if(code != null) {
					configCOMenuItem.setCode(code);
				}
				if(foodItem.getSapCode() != null) {
					configCOMenuItem.setSapCode(foodItem.getSapCode());
				}
				if(coFoodItemId != null) {
					configCOMenuItem.setId(coFoodItemId);
				}
				if(cfi.getNameVn() != null) {
					configCOMenuItem.setNameVn(cfi.getNameVn());
				}
				if(cfi.getNameEn() != null) {
					configCOMenuItem.setNameEn(cfi.getNameEn());
				}
				if(cfi.getDescVn() != null) {
					configCOMenuItem.setDescVn(cfi.getDescVn());
				}
				if(cfi.getDescEn() != null) {
					configCOMenuItem.setDescEn(cfi.getDescEn());
				}
				if(cfi.getMaxPerTransaction() != null) {
					configCOMenuItem.setMaxPerTransaction(cfi.getMaxPerTransaction());
				}
				if(cfi.getMaxForKitchen() != null) {
					configCOMenuItem.setMaxForKitchen(cfi.getMaxForKitchen());
				}
				if(cfi.getPositionNumber() != null) {
					configCOMenuItem.setPositionNumber(cfi.getPositionNumber());
				}
				if(cfi.getCoImageSize() != null) {
					configCOMenuItem.setCoImageSize(cfi.getCoImageSize());
				}
				if(cfi.getBufferLabel() != null) {
					configCOMenuItem.setBufferLabel(cfi.getBufferLabel());
				}
				if(cfi.getViewType() != null) {
					configCOMenuItem.setViewType(cfi.getViewType());
				}
				if(cfi.getNumberOfPeopleEat() != null) {
					configCOMenuItem.setNumberOfPeopleEat(cfi.getNumberOfPeopleEat());
				}
				if(cfi.getHideIcon() != null) {
					configCOMenuItem.setHideIcon(cfi.getHideIcon());
				}
				
				if(cfi.getNote() != null) {
					configCOMenuItem.setNote(cfi.getNote());
				}
				if(cfi.getNoteQuantitative() != null) {
					configCOMenuItem.setNoteQuantitative(cfi.getNoteQuantitative());
				}
				
				if(cfi.getKaloGroupId() != null) {
					KaloGroup kaloGroup = kaloGroupRepository.findById(cfi.getKaloGroupId()).get();
					if(kaloGroup != null) {
						configCOMenuItem.setKaloGroupCode(kaloGroup.getCode());
					}
				}
				configCOMenuItem.setTimeModify(System.currentTimeMillis());
				
				//pick-up attachment
				List<Attachment> avatars = attachmentRepository.findByModuleIdAndModuleTypeAndFunctionType(coFoodItemId, ModuleTypeEnum.CO_FOOD_ITEM.val, FunctionTypeEnum.AVATAR.val);
				if (avatars != null && !avatars.isEmpty()) {
					configCOMenuItem.setSrcImgThumbnail(UploadImageEnum.IMG_MENU_PATH.val + avatars.get(0).getFileName());
					FileContent file = new FileContent();
					file.setAbsolutePath(avatars.get(0).getAbsolutePath());
					file.setFileName(avatars.get(0).getFileName());
					file.setFolder(UploadImageEnum.IMG_MENU_FOLDER.val);
					file.setResCode(resCode);
					file.setResName(resName);
					file.setApiUrl(apiUrl);
					file.setType(ModuleTypeEnum.CO_FOOD_ITEM.val);
					file.setCode(code);
					file.setAttachmentId(avatars.get(0).getId());
					files.add(file);
				}
				List<Attachment> drinkPhotos = attachmentRepository.findByModuleIdAndModuleTypeAndFunctionType(coFoodItemId, ModuleTypeEnum.CO_FOOD_ITEM.val, FunctionTypeEnum.DRINK_PHOTO.val);
				if (drinkPhotos != null && !drinkPhotos.isEmpty()) {
					configCOMenuItem.setSrcImgSquare(UploadImageEnum.IMG_MENU_PATH.val + drinkPhotos.get(0).getFileName());
					FileContent file = new FileContent();
					file.setAbsolutePath(drinkPhotos.get(0).getAbsolutePath());
					file.setFileName(drinkPhotos.get(0).getFileName());
					file.setFolder(UploadImageEnum.IMG_MENU_FOLDER.val);
					file.setResCode(resCode);
					file.setResName(resName);
					file.setApiUrl(apiUrl);
					file.setType(ModuleTypeEnum.CO_FOOD_ITEM.val);
					file.setCode(code);
					file.setAttachmentId(drinkPhotos.get(0).getId());
					files.add(file);
				}
				List<Attachment> imgQuarters = attachmentRepository.findByModuleIdAndModuleTypeAndFunctionType(coFoodItemId, ModuleTypeEnum.CO_FOOD_ITEM.val, FunctionTypeEnum.QUARTER_PHOTO.val);
				if (imgQuarters != null && !imgQuarters.isEmpty()) {
					configCOMenuItem.setSrcImgQuarter(UploadImageEnum.IMG_MENU_PATH.val + imgQuarters.get(0).getFileName());
					FileContent file = new FileContent();
					file.setAbsolutePath(imgQuarters.get(0).getAbsolutePath());
					file.setFileName(imgQuarters.get(0).getFileName());
					file.setFolder(UploadImageEnum.IMG_MENU_FOLDER.val);
					file.setResCode(resCode);
					file.setResName(resName);
					file.setApiUrl(apiUrl);
					file.setType(ModuleTypeEnum.CO_FOOD_ITEM.val);
					file.setCode(code);
					file.setAttachmentId(imgQuarters.get(0).getId());
					files.add(file);
				}
				List<Attachment> images = attachmentRepository.findByModuleIdAndModuleTypeAndFunctionType(coFoodItemId,ModuleTypeEnum.CO_FOOD_ITEM.val, FunctionTypeEnum.AVATAR.val);
				if (images != null && !images.isEmpty()) {
					Attachment img = images.get(0);
					configCOMenuItem.setSrcImg(UploadImageEnum.IMG_MENU_PATH.val + img.getFileName());
					FileContent file = new FileContent();
					file.setAbsolutePath(img.getAbsolutePath());
					file.setFileName(img.getFileName());
					file.setFolder(UploadImageEnum.IMG_MENU_FOLDER.val);
					file.setResCode(resCode);
					file.setResName(resName);
					file.setApiUrl(apiUrl);
					file.setType(ModuleTypeEnum.CO_FOOD_ITEM.val);
					file.setCode(code);
					file.setAttachmentId(img.getId());
					files.add(file);
				}
				List<Attachment> imgHalfs = attachmentRepository.findByModuleIdAndModuleTypeAndFunctionType(coFoodItemId, ModuleTypeEnum.CO_FOOD_ITEM.val, FunctionTypeEnum.HALF_PHOTO.val);
				if (imgHalfs != null && !imgHalfs.isEmpty()) {
					configCOMenuItem.setSrcImgHalf(UploadImageEnum.IMG_MENU_PATH.val + imgHalfs.get(0).getFileName());
					FileContent file = new FileContent();
					file.setAbsolutePath(imgHalfs.get(0).getAbsolutePath());
					file.setFileName(imgHalfs.get(0).getFileName());
					file.setFolder(UploadImageEnum.IMG_MENU_FOLDER.val);
					file.setResCode(resCode);
					file.setResName(resName);
					file.setApiUrl(apiUrl);
					file.setType(ModuleTypeEnum.CO_FOOD_ITEM.val);
					file.setCode(code);
					file.setAttachmentId(imgHalfs.get(0).getId());
					files.add(file);
				}
				
				List<Attachment> imgToppings = attachmentRepository.findByModuleIdAndModuleTypeAndFunctionType(coFoodItemId, ModuleTypeEnum.CO_FOOD_ITEM.val, FunctionTypeEnum.TOPPING_PHOTO.val);
				if (imgToppings != null && !imgToppings.isEmpty()) {
					configCOMenuItem.setSrcImgTopping(UploadImageEnum.IMG_MENU_PATH.val + imgToppings.get(0).getFileName());
					FileContent file = new FileContent();
					file.setAbsolutePath(imgToppings.get(0).getAbsolutePath());
					file.setFileName(imgToppings.get(0).getFileName());
					file.setFolder(UploadImageEnum.IMG_MENU_FOLDER.val);
					file.setResCode(resCode);
					file.setResName(resName);
					file.setApiUrl(apiUrl);
					file.setType(ModuleTypeEnum.CO_FOOD_ITEM.val);
					file.setAttachmentId(imgToppings.get(0).getId());
					file.setCode(code);
					files.add(file);
				}
				
				List<Attachment> imgGroups = attachmentRepository.findByModuleIdAndModuleTypeAndFunctionType(coFoodItemId, ModuleTypeEnum.CO_FOOD_ITEM.val, FunctionTypeEnum.GROUP.val);
				if (imgGroups != null && !imgGroups.isEmpty()) {
					configCOMenuItem.setSrcImgGroup(UploadImageEnum.IMG_MENU_PATH.val + imgGroups.get(0).getFileName());
					FileContent file = new FileContent();
					file.setAbsolutePath(imgGroups.get(0).getAbsolutePath());
					file.setFileName(imgGroups.get(0).getFileName());
					file.setFolder(UploadImageEnum.IMG_MENU_FOLDER.val);
					file.setResCode(resCode);
					file.setResName(resName);
					file.setApiUrl(apiUrl);
					file.setType(ModuleTypeEnum.CO_FOOD_ITEM.val);
					file.setCode(code);
					file.setAttachmentId(imgGroups.get(0).getId());
					files.add(file);
				}
				
				List<Attachment> imgGroupHiddens = attachmentRepository.findByModuleIdAndModuleTypeAndFunctionType(coFoodItemId, ModuleTypeEnum.CO_FOOD_ITEM.val, FunctionTypeEnum.GROUP_HIDDEN.val);
				if (imgGroupHiddens != null && !imgGroupHiddens.isEmpty()) {
					configCOMenuItem.setSrcImgGroupHidden(UploadImageEnum.IMG_MENU_PATH.val + imgGroupHiddens.get(0).getFileName());
					FileContent file = new FileContent();
					file.setAbsolutePath(imgGroupHiddens.get(0).getAbsolutePath());
					file.setFileName(imgGroupHiddens.get(0).getFileName());
					file.setFolder(UploadImageEnum.IMG_MENU_FOLDER.val);
					file.setResCode(resCode);
					file.setResName(resName);
					file.setApiUrl(apiUrl);
					file.setType(ModuleTypeEnum.CO_FOOD_ITEM.val);
					file.setCode(code);
					file.setAttachmentId(imgGroupHiddens.get(0).getId());
					files.add(file);
				}
				
				List<Attachment> imgHorizontals = attachmentRepository.findByModuleIdAndModuleTypeAndFunctionType(coFoodItemId, ModuleTypeEnum.CO_FOOD_ITEM.val, FunctionTypeEnum.HORIZONTAL_PHOTO.val);
				if (imgHorizontals != null && !imgHorizontals.isEmpty()) {
					configCOMenuItem.setSrcImg2xHorizontal(UploadImageEnum.IMG_MENU_PATH.val + imgHorizontals.get(0).getFileName());
					FileContent file = new FileContent();
					file.setAbsolutePath(imgHorizontals.get(0).getAbsolutePath());
					file.setFileName(imgHorizontals.get(0).getFileName());
					file.setFolder(UploadImageEnum.IMG_MENU_FOLDER.val);
					file.setResCode(resCode);
					file.setResName(resName);
					file.setApiUrl(apiUrl);
					file.setType(ModuleTypeEnum.CO_FOOD_ITEM.val);
					file.setCode(code);
					file.setAttachmentId(imgHorizontals.get(0).getId());
					files.add(file);
				}
				
				List<Attachment> imgVerticals = attachmentRepository.findByModuleIdAndModuleTypeAndFunctionType(coFoodItemId, ModuleTypeEnum.CO_FOOD_ITEM.val, FunctionTypeEnum.VERTICAL_PHOTO.val);
				if (imgVerticals != null && !imgVerticals.isEmpty()) {
					configCOMenuItem.setSrcImg2xVertical(UploadImageEnum.IMG_MENU_PATH.val + imgVerticals.get(0).getFileName());
					FileContent file = new FileContent();
					file.setAbsolutePath(imgVerticals.get(0).getAbsolutePath());
					file.setFileName(imgVerticals.get(0).getFileName());
					file.setFolder(UploadImageEnum.IMG_MENU_FOLDER.val);
					file.setResCode(resCode);
					file.setResName(resName);
					file.setApiUrl(apiUrl);
					file.setType(ModuleTypeEnum.CO_FOOD_ITEM.val);
					file.setCode(code);
					file.setAttachmentId(imgVerticals.get(0).getId());
					files.add(file);
				}
				
				if(cfi.getExtraFoodItem() != null && !cfi.getExtraFoodItem().isEmpty()) {
					String[] str = cfi.getExtraFoodItem().split(SymbolEnum.HYPHEN.val);
					JsonExtraItem jsonExtraItem = new JsonExtraItem();
					FoodItem fItem = foodItemRepository.findByCode(str[0]);
					if(fItem != null) {
						jsonExtraItem.setCode(fItem.getCode());
						jsonExtraItem.setName(fItem.getName());
						jsonExtraItem.setNameVn(fItem.getName());
						jsonExtraItem.setNameEn(fItem.getName());
						configCOMenuItem.setExtraItem(jsonExtraItem);
					}
					
				}
				
				// related food item codes and item size
				List<String> relatedFoodItemCodes = new ArrayList<>();
				List<JsonItemSize> itemSizes = new ArrayList<>();
				List<RelatedFoodItem> relatedFoodItems = relatedFoodItemRepository.findByCoFoodItem(cfi);
				if(relatedFoodItems != null && !relatedFoodItems.isEmpty()) {
					relatedFoodItems.stream().forEach(related ->{
						if(related.getType().equals(FoodTypeEnum.RELATED_FOODITEM.val)) {
							relatedFoodItemCodes.add(related.getFoodItemCode());
						}
						if(related.getType().equals(FoodTypeEnum.SIZE_FOODITEM.val)) {
							JsonItemSize jsonItemSize = new JsonItemSize();
							jsonItemSize.setFoodItemCode(related.getFoodItemCode());
							jsonItemSize.setFoodItemName(related.getFoodItemName());
							jsonItemSize.setFoodItemNameEn(related.getFoodItemName());
							jsonItemSize.setFoodItemNameVn(related.getFoodItemName());
							if(related.getNameDisplay() != null) {
								jsonItemSize.setNameDisplay(related.getNameDisplay());
							}
							itemSizes.add(jsonItemSize);
						}
					});
					if(relatedFoodItemCodes != null && !relatedFoodItemCodes.isEmpty()) {
						configCOMenuItem.setRelatedItems(relatedFoodItemCodes);
					}
					if(itemSizes != null && !itemSizes.isEmpty()) {
						configCOMenuItem.setItemSizes(itemSizes);
					}
				}
				
				// topping item 
				List<ToppingFoodItem> toppingFoodItems = toppingFoodItemRepository.findByCoFoodItem(cfi);
				List<JsonToppingFoodItem> jsonToppings = new ArrayList<>();
				if(toppingFoodItems != null && !toppingFoodItems.isEmpty()) {
					toppingFoodItems.stream().forEach(tp ->{
						JsonToppingFoodItem jsonTopping = new JsonToppingFoodItem();
						BeanUtils.copyProperties(tp, jsonTopping);
						jsonTopping.setFoodItemNameVn(tp.getFoodItemName());
						jsonTopping.setFoodItemNameEn(tp.getFoodItemName());
						jsonToppings.add(jsonTopping);
					});
					if(jsonToppings != null && !jsonToppings.isEmpty()) {
						configCOMenuItem.setItemToppings(jsonToppings);
					}
				}
				
				// modifiers
				List<CoFoodItemModifier> coFoodItemModifiers = coFoodItemModifierRepository.findByCoFoodItem(cfi);
				if(!coFoodItemModifiers.isEmpty()) {
					coFoodItemModifiers.stream().forEach(m -> {
						ConfigCOModifier configCOModifier = new ConfigCOModifier();
						configCOModifier.setCode(m.getModifier().getCode());
						configCOModifier.setName(m.getModifier().getName());
						configCOModifier.setType(m.getType());
						configCOModifier.setNumberOfChili(m.getNumberOfChili());
						configCOMenuItem.getModifiers().add(configCOModifier);
						jsonCOModifiers.add(configCOModifier);
					});
				}
				
				// special types
				cfi.getFeatures().stream().forEach(f -> {
					ConfigCOSpecialType configCOSpecialType = new ConfigCOSpecialType();
					configCOSpecialType.setId(f.getId());
					configCOSpecialType.setCode(f.getCode());
					configCOSpecialType.setNameVn(f.getNameVn());
					configCOSpecialType.setNameEn(f.getNameEn());
					configCOSpecialType.setDescVn(f.getDescVn());
					configCOSpecialType.setDescEn(f.getDescEn());
					
					configCOMenuItem.getItemSpecialTypes().add(configCOSpecialType);
					jsonCOConfigCOSpecialTypes.add(configCOSpecialType);
				});
				
				// menu item detail
				JsonCOMenuItem jsonCOMenuItem = new JsonCOMenuItem();
				jsonCOMenuItem.setCode(configCOMenuItem.getCode());
				jsonCOMenuItem.setNameVn(configCOMenuItem.getNameVn());
				jsonCOMenuItem.setNameEn(configCOMenuItem.getNameEn());
				jsonCOMenuItem.setKalo(cfi.getKalo());
				configCOMenuItem.getCoMenuItemDetails().add(jsonCOMenuItem);
				
				jsonCOMenuItems.add(configCOMenuItem);
			});
		}
		// return data of config-co-menu-item api
		restaurantSync.setCoMenuItems(jsonCOMenuItems);
		// return data of config-co-modifier api
		restaurantSync.setCoModifiers(new ArrayList<>(jsonCOModifiers));
		// return data of config-co-special api
		restaurantSync.setCoSpecials(new ArrayList<>(jsonCOConfigCOSpecialTypes));
		restaurantSync.setFileContents(files);
		return restaurantSync;
	}
		
	// prepare data soCategory
	private RestaurantSync prepareDataSoCategory(List<SoCategory> soCategories,Integer resCode,String apiUrl,boolean isHasCate,String resName) {
		RestaurantSync restaurantSync = new RestaurantSync();
		List<FileContent> files = new ArrayList<>();
		List<ConfigMenuEngineering> syncMenuEngineerings = new ArrayList<>();
		List<JsonMenuGroupDetail> syncMenuGroups = new ArrayList<>();
		List<ConfigCOCategory> syncCOCategories = new ArrayList<>();
		if (soCategories != null && !soCategories.isEmpty()) {
			soCategories.stream().forEach(sc -> { 
				OrderCategory orderCategory = sc.getOrderCategory(); 
				String orderCategoryCode = orderCategory.getCode();
				ConfigMenuEngineering configMenuEngineering = new ConfigMenuEngineering();
	        	for (MenuTypeEnum e : MenuTypeEnum.values()) {
	        		if (e.key.equals(sc.getType())) {
	        			configMenuEngineering.setMenuType(e.val);		
	        		}
	        	}
	        	
	        	//pickup data for adult buffer ticket and kid buffer ticket
	        	JsonDefaultDish jsonDefaultDish = new JsonDefaultDish();
	        	JsonChildDish jsonChildDish = new JsonChildDish();
	        	if(sc.getAdultBufferTicket() != null) {
	        		JsonDishItem jsonDishItem = new JsonDishItem();
	        		String[] str = sc.getAdultBufferTicket().split(SymbolEnum.HYPHEN.val);
	        		FoodItem foodItem = foodItemRepository.findByCode(str[0]);
	        		if(foodItem != null) {
	        			jsonDishItem.setId(foodItem.getId());
		        		jsonDishItem.setItemId(foodItem.getItemId());
		        		jsonDishItem.setCode(foodItem.getCode());
		        		jsonDishItem.setName(foodItem.getName());
		        		jsonDishItem.setStatus(foodItem.getStatus());
		        		jsonDishItem.setParentId(foodItem.getParentId());
		        		jsonDefaultDish.setJsonDishItem(jsonDishItem);
	        		}
	        		configMenuEngineering.setJsonChildDish(jsonChildDish);
	        		
	        	}
	        	
	        	if(sc.getKidBufferTicket() != null) {
	        		JsonDishItem jsonDishItem = new JsonDishItem();
	        		String[] str = sc.getKidBufferTicket().split(SymbolEnum.HYPHEN.val);
	        		FoodItem foodItem = foodItemRepository.findByCode(str[0]);
	        		if(foodItem != null) {
		        		jsonDishItem.setId(foodItem.getId());
		        		jsonDishItem.setItemId(foodItem.getItemId());
		        		jsonDishItem.setCode(foodItem.getCode());
		        		jsonDishItem.setName(foodItem.getName());
		        		jsonDishItem.setStatus(foodItem.getStatus());
		        		jsonDishItem.setParentId(foodItem.getParentId());
		        		jsonChildDish.setJsonDishItem(jsonDishItem);
	        		}
	        		configMenuEngineering.setJsonDefaultDish(jsonDefaultDish);
	        	}
	        	
				// pickup data for order-category 
				JsonOrderCategory jsonOrderCategory = new JsonOrderCategory();
				jsonOrderCategory.setId(orderCategory.getId());
				jsonOrderCategory.setItemId(orderCategory.getId());
				jsonOrderCategory.setCode(orderCategoryCode);
				jsonOrderCategory.setName(orderCategory.getName());
				jsonOrderCategory.setStatus(orderCategory.getStatus());
				if (sc.getOrderCategoryParentId() != null) {
					jsonOrderCategory.setOrderCategoryParentId(sc.getOrderCategoryParentId());	
				}
				if (StringUtils.isNotBlank(orderCategory.getRightLvl())) {
					jsonOrderCategory.setRightLvl(orderCategory.getRightLvl());	
				}
				if (orderCategory.getObjectSifr() != null) {
					jsonOrderCategory.setObjectSifr(orderCategory.getObjectSifr());	
				}
				configMenuEngineering.setJsonOrderCategory(jsonOrderCategory);
				
				// pickup data for co-category   
				if(isHasCate) {
					CoCategory coCategory = coCategoryRepository.findBySoCategoryAndRestaurantCode(sc, resCode);
					boolean isExistingCoCategory = coCategory != null;
					ConfigCOCategory configCOCategory = new ConfigCOCategory();
					if (isExistingCoCategory) {
						List<CoFoodGroupDisplay> coFoodGroupDisplays = coFoodGroupDisplayRepository.findByCoCategory(coCategory); 
						List<String> coFoodGroupCodes = new ArrayList<>(); 
						Long coCategoryId = coCategory.getId();
						
						if(coFoodGroupDisplays != null && !coFoodGroupDisplays.isEmpty()) {
							coFoodGroupCodes = coFoodGroupDisplays.stream().map(cc -> cc.getFoodGroupCode()).collect(Collectors.toList());
						}
						if(orderCategory.getCode() != null) {
							configCOCategory.setOrderCategoryCode(orderCategoryCode);
						}
						if(coCategoryId != null) {
							configCOCategory.setId(coCategoryId);
						}
						if(coCategory.getNameVn() != null) {
							configCOCategory.setNameVn(coCategory.getNameVn());
						}
						if(coCategory.getNameEn() != null) {
							configCOCategory.setNameEn(coCategory.getNameEn());
						}
						if(coCategory.getDescVn() != null) {
							configCOCategory.setDescVn(coCategory.getDescVn());
						}
						if(coCategory.getDescEn() != null) {
							configCOCategory.setDescEn(coCategory.getDescEn());
						}
						if(coCategory.getAlacarteLabel() != null) {
							configCOCategory.setAlacarteLabel(coCategory.getAlacarteLabel());
						}
						if(coCategory.getBuffetLabel() != null) {
							configCOCategory.setBuffetLabel(coCategory.getBuffetLabel());
						}
						if(coCategory.getDrinksLabel() != null) {
							configCOCategory.setDrinksLabel(coCategory.getDrinksLabel());
						}
						if(coCategory.getType() != null) {
							configCOCategory.setType(coCategory.getType());
						}
						if(coCategory.getAlacarteLabelEn() != null) {
							configCOCategory.setAlacarteLabelEn(coCategory.getAlacarteLabelEn());
						}
						if(coCategory.getBuffetLabelEn() != null) {
							configCOCategory.setBuffetLabelEn(coCategory.getBuffetLabelEn());
						}
						if(coCategory.getDrinksLabelEn() != null) {
							configCOCategory.setDrinksLabelEn(coCategory.getDrinksLabelEn());
						}
						if(coCategory.getPhotoDisplayPosition() != null) {
							configCOCategory.setPhotoDisplayPosition(coCategory.getPhotoDisplayPosition());
						}
						if(coFoodGroupCodes != null && !coFoodGroupCodes.isEmpty()) {
							configCOCategory.setMenuGroupCodes(coFoodGroupCodes);
						}
						configCOCategory.setTimeModify(System.currentTimeMillis());
						//pick-up attachment
						List<Attachment> avatars = attachmentRepository.findByModuleIdAndModuleTypeAndFunctionType(coCategoryId, ModuleTypeEnum.CO_CATEGORY.val, FunctionTypeEnum.AVATAR.val);
						if (avatars != null && !avatars.isEmpty()) {
							configCOCategory.setSrcImg(UploadImageEnum.IMG_CATEGORY_PATH.val + avatars.get(0).getFileName());
							FileContent file = new FileContent();
							file.setAbsolutePath(avatars.get(0).getAbsolutePath());
							file.setFileName(avatars.get(0).getFileName());
							file.setFolder( UploadImageEnum.IMG_CATEGORY_FOLDER.val);
							file.setResCode(resCode);
							file.setResName(resName);
							file.setApiUrl(apiUrl);
							file.setType(ModuleTypeEnum.CO_CATEGORY.val);
							file.setCode(orderCategoryCode);
							file.setAttachmentId(avatars.get(0).getId());
							files.add(file);
						}
						List<Attachment> images = attachmentRepository.findByModuleIdAndModuleTypeAndFunctionType(coCategoryId, ModuleTypeEnum.CO_CATEGORY.val, FunctionTypeEnum.AVATAR_ABOUTUS_MENU.val);
						if (images != null && !images.isEmpty()) {
							configCOCategory.setSrcImgIntros(images.stream().map(i -> UploadImageEnum.IMG_CATEGORY_PATH.val + i.getFileName()).collect(Collectors.toList()));
							images.stream().forEach(i -> {
				    			FileContent file = new FileContent();
				    			file.setAbsolutePath(i.getAbsolutePath());
				    			file.setFileName(i.getFileName());
				    			file.setFolder(UploadImageEnum.IMG_CATEGORY_FOLDER.val);
				    			file.setResCode(resCode);
				    			file.setResName(resName);
				    			file.setApiUrl(apiUrl);
				    			file.setType(ModuleTypeEnum.CO_CATEGORY.val);
								file.setCode(orderCategoryCode);
								file.setAttachmentId(i.getId());
				    			files.add(file);
							});
						}
						List<Attachment> videoIntroMenus = attachmentRepository.findByModuleIdAndModuleTypeAndFunctionType(coCategoryId,ModuleTypeEnum.CO_CATEGORY.val, FunctionTypeEnum.VIDEO_ABOUTUS_MENU.val);
						if (videoIntroMenus != null && !videoIntroMenus.isEmpty()) {
							configCOCategory.setSrcVideoIntros(videoIntroMenus.stream().map(i -> UploadImageEnum.VIDEO_MENU_PATH.val + i.getFileName()).collect(Collectors.toList()));
							videoIntroMenus.stream().forEach(i -> {
				    			FileContent file = new FileContent();
				    			file.setAbsolutePath(i.getAbsolutePath());
				    			file.setFileName(i.getFileName());
				    			file.setFolder(UploadImageEnum.VIDEO_CATEGORY_FOLDER.val);
				    			file.setResCode(resCode);
				    			file.setResName(resName);
				    			file.setApiUrl(apiUrl);
				    			file.setType(ModuleTypeEnum.CO_CATEGORY.val);
								file.setCode(orderCategoryCode);
								file.setAttachmentId(i.getId());
				    			files.add(file);
							});
						}
						
						List<Attachment> videoIntroRes = attachmentRepository.findByModuleIdAndModuleTypeAndFunctionType(coCategoryId, ModuleTypeEnum.CO_CATEGORY.val, FunctionTypeEnum.VIDEO_ABOUTUS_RES.val);
						if (videoIntroRes != null && !videoIntroRes.isEmpty()) {
							configCOCategory.setRestaurantLstVideo(videoIntroRes.stream().map(i -> UploadImageEnum.VIDEO_MENU_PATH.val + i.getFileName()).collect(Collectors.toList()));
							videoIntroRes.stream().forEach(i -> {
				    			FileContent file = new FileContent();
				    			file.setAbsolutePath(i.getAbsolutePath());
				    			file.setFileName(i.getFileName());
				    			file.setFolder(UploadImageEnum.VIDEO_CATEGORY_FOLDER.val);
				    			file.setResCode(resCode);
				    			file.setResName(resName);
				    			file.setApiUrl(apiUrl);
				    			file.setType(ModuleTypeEnum.CO_CATEGORY.val);
								file.setCode(orderCategoryCode);
								file.setAttachmentId(i.getId());
				    			files.add(file);
							});
						}
						
						List<Attachment> imgIntroRes = attachmentRepository.findByModuleIdAndModuleTypeAndFunctionType(coCategoryId, ModuleTypeEnum.CO_CATEGORY.val, FunctionTypeEnum.AVATAR_ABOUTUS_RES.val);
						if (imgIntroRes != null && !imgIntroRes.isEmpty()) {
							configCOCategory.setRestaurantLstImg(imgIntroRes.stream().map(i -> UploadImageEnum.IMG_CATEGORY_PATH.val + i.getFileName()).collect(Collectors.toList()));
							imgIntroRes.stream().forEach(i -> {
				    			FileContent file = new FileContent();
				    			file.setAbsolutePath(i.getAbsolutePath());
				    			file.setFileName(i.getFileName());
				    			file.setFolder(UploadImageEnum.IMG_CATEGORY_FOLDER.val);
				    			file.setResCode(resCode);
				    			file.setResName(resName);
				    			file.setApiUrl(apiUrl);
				    			file.setType(ModuleTypeEnum.CO_CATEGORY.val);
								file.setCode(orderCategoryCode);
								file.setAttachmentId(i.getId());
				    			files.add(file);
							});
						}
						syncCOCategories.add(configCOCategory);	
					}
				}
				
				// pickup data for food group 
				List<SoCategoryFoodGroup> soCategoryFoodGroups = soCategoryFoodGroupRepository.findBySoCategoryAndRestaurantCode(sc.getId(), resCode); 
				List<JsonMenuGroupDetail> syncMenuGroup = new ArrayList<>();
				soCategoryFoodGroups.stream().forEach(scfg -> {
					FoodGroup foodGroup = scfg.getFoodGroup();
					if(foodGroup.getChanged() != null && !foodGroup.getChanged().equals(StatusEnum.DELETE.status)) {
						System.out.println("code : "+ foodGroup.getCode());
						List<Attachment> avatars = attachmentRepository.findByModuleIdAndModuleTypeAndFunctionType(foodGroup.getId(), ModuleTypeEnum.FOOD_GROUP.val, FunctionTypeEnum.AVATAR.val);
						if (avatars != null && !avatars.isEmpty()) {
							FileContent file = new FileContent();
							file.setAbsolutePath(avatars.get(0).getAbsolutePath());
							file.setFileName(avatars.get(0).getFileName());
							file.setFolder(UploadImageEnum.IMG_MENU_FOLDER.val);
							file.setResCode(resCode);
							file.setResName(resName);
							file.setApiUrl(apiUrl);
							file.setType(ModuleTypeEnum.FOOD_GROUP.val);
							file.setCode(foodGroup.getCode());
							file.setAttachmentId(avatars.get(0).getId());
							files.add(file);
						}
						
						// check this group has children or not, if has json-menu-item will handle data of child group. if not then get food item of group
						List<JsonMenuItem> jsonMenuItems = new ArrayList<>(); 
						
						List<FoodGroup> subFoodGroups = foodGroupRepository.findByParentAndRestaurantCode(foodGroup, resCode);
						if (subFoodGroups != null && !subFoodGroups.isEmpty()) {
							// pickup food-group-detail if foodGroup is parent
							JsonMenuGroupDetail jsonMenuGroupDetailParent = new JsonMenuGroupDetail();
							jsonMenuGroupDetailParent.setId(foodGroup.getId());
							jsonMenuGroupDetailParent.setCode(foodGroup.getCode());
							jsonMenuGroupDetailParent.setName(foodGroup.getNameVn());
							if(foodGroup.getSrcImage() != null) {
								jsonMenuGroupDetailParent.setSrcImage(foodGroup.getSrcImage());
							}
							if(foodGroup.getNameEn() != null) {
								jsonMenuGroupDetailParent.setNameEn(foodGroup.getNameEn());
							}
							if(foodGroup.getMenuType().getCode() != null) {
								jsonMenuGroupDetailParent.setGroupType(foodGroup.getMenuType().getCode());
							}
							if (scfg.getGroupOrder() != null) {
								jsonMenuGroupDetailParent.setOrderNo(scfg.getGroupOrder());	
							}
							if(foodGroup.getLevel() != null) {
								jsonMenuGroupDetailParent.setLevel(foodGroup.getLevel());
							}else {
								jsonMenuGroupDetailParent.setLevel(FoodGroupLevelEnum.MENU_NORMAL.val);
							}
							
							subFoodGroups.stream().forEach(g -> {
								if(g.getChanged() != null && !g.getChanged().equals(StatusEnum.DELETE.status)) {
									JsonMenuItem jsonMenuItem = new JsonMenuItem();
									jsonMenuItem.setCode(String.valueOf(g.getCode()));
									jsonMenuItem.setType(g.getType());
									jsonMenuItem.setNameVn(g.getNameVn());
									if(g.getSoCategoryFoodGroups().get(0).getGroupOrder() != null) {
										jsonMenuItem.setOrderNumberItem(g.getSoCategoryFoodGroups().get(0).getGroupOrder());
									}
									jsonMenuItems.add(jsonMenuItem);
								}
								
							});
							
							jsonMenuGroupDetailParent.setJsonMenuItems(jsonMenuItems);
							syncMenuGroup.add(jsonMenuGroupDetailParent);
							syncMenuGroups.add(jsonMenuGroupDetailParent);
							
						} else if(foodGroup.getParent() != null){
							List<FoodGroupItem> foodGroupItems = foodGroupItemRepository.findByFoodGroupAndRestaurantCode(foodGroup, resCode);
							foodGroupItems.stream().forEach(fgi -> {
								if(fgi.getChanged() == null || (fgi.getChanged() != null && !fgi.getChanged().equals(StatusEnum.DELETE.status))) {
									FoodItem foodItem = fgi.getFoodItem();
									JsonMenuItem jsonMenuItem = new JsonMenuItem();
									jsonMenuItem.setCode(String.valueOf(foodItem.getCode()));
									jsonMenuItem.setType(foodItem.getType());
									jsonMenuItem.setNameVn(foodItem.getName());
									jsonMenuItem.setOrderNumberItem(fgi.getItemOrder());
									jsonMenuItems.add(jsonMenuItem);
								}
							});
							
							// pickup food-group-detail and items
							JsonMenuGroupDetail jsonMenuGroupDetail = new JsonMenuGroupDetail();
							jsonMenuGroupDetail.setId(foodGroup.getId());
							jsonMenuGroupDetail.setCode(foodGroup.getCode());
							jsonMenuGroupDetail.setName(foodGroup.getNameVn());
							if(foodGroup.getSrcImage() != null) {
								jsonMenuGroupDetail.setSrcImage(foodGroup.getSrcImage());
							}
							if(foodGroup.getNameEn() != null) {
								jsonMenuGroupDetail.setNameEn(foodGroup.getNameEn());
							}
							if(foodGroup.getMenuType().getCode() != null) {
								jsonMenuGroupDetail.setGroupType(foodGroup.getMenuType().getCode());
							}
							if (scfg.getGroupOrder() != null) {
								jsonMenuGroupDetail.setOrderNo(scfg.getGroupOrder());	
							}
							if(foodGroup.getLevel() != null) {
								jsonMenuGroupDetail.setLevel(foodGroup.getLevel());
							}else {
								jsonMenuGroupDetail.setLevel(FoodGroupLevelEnum.MENU_NORMAL.val);
							}
							jsonMenuGroupDetail.setJsonMenuItems(jsonMenuItems);
							syncMenuGroups.add(jsonMenuGroupDetail);
							
						} else {
							// pickup food-group-detail if foodGroup is parent
							JsonMenuGroupDetail jsonMenuGroupDetailParent = new JsonMenuGroupDetail();
							jsonMenuGroupDetailParent.setId(foodGroup.getId());
							jsonMenuGroupDetailParent.setCode(foodGroup.getCode());
							jsonMenuGroupDetailParent.setName(foodGroup.getNameVn());
							if(foodGroup.getSrcImage() != null) {
								jsonMenuGroupDetailParent.setSrcImage(foodGroup.getSrcImage());
							}
							if(foodGroup.getNameEn() != null) {
								jsonMenuGroupDetailParent.setNameEn(foodGroup.getNameEn());
							}
							if(foodGroup.getMenuType().getCode() != null) {
								jsonMenuGroupDetailParent.setGroupType(foodGroup.getMenuType().getCode());
							}
							if (scfg.getGroupOrder() != null) {
								jsonMenuGroupDetailParent.setOrderNo(scfg.getGroupOrder());	
							}
							if(foodGroup.getLevel() != null) {
								jsonMenuGroupDetailParent.setLevel(foodGroup.getLevel());
							}else {
								jsonMenuGroupDetailParent.setLevel(FoodGroupLevelEnum.MENU_NORMAL.val);
							}
							
							List<FoodGroupItem> foodGroupItems = foodGroupItemRepository.findByFoodGroupAndRestaurantCode(foodGroup, resCode);
							foodGroupItems.stream().forEach(fgi -> {
								if(fgi.getChanged() == null || (fgi.getChanged() != null && !fgi.getChanged().equals(StatusEnum.DELETE.status))) {
									FoodItem foodItem = fgi.getFoodItem();
									JsonMenuItem jsonMenuItem = new JsonMenuItem();
									jsonMenuItem.setCode(String.valueOf(foodItem.getCode()));
									jsonMenuItem.setType(foodItem.getType());
									jsonMenuItem.setNameVn(foodItem.getName());
									jsonMenuItem.setOrderNumberItem(fgi.getItemOrder());
									jsonMenuItems.add(jsonMenuItem);
								}
								
							});
							
							jsonMenuGroupDetailParent.setJsonMenuItems(jsonMenuItems);
							syncMenuGroup.add(jsonMenuGroupDetailParent);
							syncMenuGroups.add(jsonMenuGroupDetailParent);
						}
					}
					
					
				});
				configMenuEngineering.setJsonMenuGroups(syncMenuGroup);
				syncMenuEngineerings.add(configMenuEngineering);
			});
		}
		restaurantSync.setMenuEngineerings(syncMenuEngineerings);
		restaurantSync.setMenuGroups(syncMenuGroups);
		restaurantSync.setFileContents(files);
		restaurantSync.setCoCategories(syncCOCategories);
		return restaurantSync;
	}

	public MasterDataSync prepareMasterDataToSendToRestaurant(String type, Integer version) {
		MasterDataSync masterDataSync = new MasterDataSync();
		masterDataSync.setVersion(version);
		
		int rate = 10000;
		
		if (SystemParamEnum.API_MASTER_CATEGLIST_PATTERN.param.equals(type)) {
			List<MasterDataCategList> list = categoryRepository.findActives().stream().map(src -> {
				MasterDataCategList dest = new MasterDataCategList();
				BeanUtils.copyProperties(src, dest);
				if (src.getParentId() == null) {
					dest.setParentId(0L);
				}
				return dest;
			}).collect(Collectors.toList());
			masterDataSync.setMasterDataCategLists(list);
			
		} else if (SystemParamEnum.API_MASTER_CATEGORY_PATTERN.param.equals(type)) {
			List<MasterDataOrderCategory> list = orderCategoryRepository.findActives().stream().map(src -> {
				MasterDataOrderCategory dest = new MasterDataOrderCategory();
				BeanUtils.copyProperties(src, dest);
				if (src.getParentId() == null) {
					dest.setParentId(0L);
				}
				return dest;
			}).collect(Collectors.toList());
			masterDataSync.setMasterDataOrderCategories(list);

		} else if (SystemParamEnum.API_MASTER_CURRENCY_PATTERN.param.equals(type)) {
			List<MasterDataCurrency> list = currencyRepository.findActives().stream().map(src -> {
				MasterDataCurrency dest = new MasterDataCurrency();
				BeanUtils.copyProperties(src, dest);
				return dest;
			}).collect(Collectors.toList());
			masterDataSync.setMasterDataCurrencies(list);

		} else if (SystemParamEnum.API_MASTER_CURRENCYRATE_PATTERN.param.equals(type)) {
			List<MasterDataCurrencyRate> list = currencyRateRepository.findActives().stream().map(src -> {
				MasterDataCurrencyRate dest = new MasterDataCurrencyRate();
				BeanUtils.copyProperties(src, dest);
				Long newRate = dest.getRate().longValue()/rate;
				dest.setRate(newRate);
				
				return dest;
			}).collect(Collectors.toList());
			masterDataSync.setMasterDataCurrencyRates(list);

		} else if (SystemParamEnum.API_MASTER_EMPLOYEE_PATTERN.param.equals(type)) {
			List<MasterDataEmployee> list = employeeRepository.findActives().stream().map(src -> {
				MasterDataEmployee dest = new MasterDataEmployee();
				BeanUtils.copyProperties(src, dest);
				if (src.getParentId() == null) {
					dest.setParentId(0L);
				}
				return dest;
			}).collect(Collectors.toList());
			masterDataSync.setMasterDataEmployees(list);

		} else if (SystemParamEnum.API_MASTER_GUESTTYPE_PATTERN.param.equals(type)) {
			List<MasterDataGuestType> list = guestTypeRepository.findActives().stream().map(src -> {
				MasterDataGuestType dest = new MasterDataGuestType();
				BeanUtils.copyProperties(src, dest);
				if (src.getParentId() == null) {
					dest.setParentId(0L);
				}
				return dest;
			}).collect(Collectors.toList());
			masterDataSync.setMasterDataGuestTypes(list);

		} else if (SystemParamEnum.API_MASTER_HALLPLAN_PATTERN.param.equals(type)) {
			List<MasterDataHallPlan> list = hallplanRepository.findActives().stream().map(src -> {
				MasterDataHallPlan dest = new MasterDataHallPlan();
				BeanUtils.copyProperties(src, dest);
				if (src.getParentId() == null) {
					dest.setParentId(0L);
				}
				return dest;
			}).collect(Collectors.toList());
			masterDataSync.setMasterDataHallPlans(list);

		} else if (SystemParamEnum.API_MASTER_MENUITEM_PATTERN.param.equals(type)) {
			List<MasterDataMenuItem> list = foodItemRepository.findActives().stream().map(src -> {
				MasterDataMenuItem dest = new MasterDataMenuItem();
				BeanUtils.copyProperties(src, dest);
				if(src.getCookMins() == null) {
					dest.setCookMins(0);
				}
				return dest;
			}).collect(Collectors.toList());
			masterDataSync.setMasterDataMenuItems(list);

		} else if (SystemParamEnum.API_MASTER_MODIFIER_PATTERN.param.equals(type)) {
			List<MasterDataModifier> list = modifierRepository.findActives().stream().map(src -> {
				MasterDataModifier dest = new MasterDataModifier();
				BeanUtils.copyProperties(src, dest);
				if (src.getParentId() == null) {
					dest.setParentId(0L);
				}
				return dest;
			}).collect(Collectors.toList());
			masterDataSync.setMasterDataModifiers(list);

		} else if (SystemParamEnum.API_MASTER_MODIGROUPS_PATTERN.param.equals(type)) {
			List<MasterDataModiGroups> list = modiGroupRepository.findActives().stream().map(src -> {
				MasterDataModiGroups dest = new MasterDataModiGroups();
				BeanUtils.copyProperties(src, dest);
				if (src.getParentId() == null) {
					dest.setParentId(0L);
				}
				return dest;
			}).collect(Collectors.toList());
			masterDataSync.setMasterDataModiGroups(list);

		} else if (SystemParamEnum.API_MASTER_MODISCHEME_PATTERN.param.equals(type)) {
			List<MasterDataModiScheme> list = modiSchemeRepository.findActives().stream().map(src -> {
				MasterDataModiScheme dest = new MasterDataModiScheme();
				BeanUtils.copyProperties(src, dest);
				if (src.getParentId() == null) {
					dest.setParentId(0L);
				}
				return dest;
			}).collect(Collectors.toList());
			masterDataSync.setMasterDataModiSchemes(list);

		} else if (SystemParamEnum.API_MASTER_MODISCHEMEDETAIL_PATTERN.param.equals(type)) {
			List<MasterDataModiSchemeDetail> list = modiSchemeDetailRepository.findAll().stream().map(src -> {
				MasterDataModiSchemeDetail dest = new MasterDataModiSchemeDetail();
				BeanUtils.copyProperties(src, dest);
				if (src.getParentId() == null) {
					dest.setParentId(0L);
				}
				return dest;
			}).collect(Collectors.toList());
			masterDataSync.setMasterDataModiSchemeDetails(list);
			
		} else if (SystemParamEnum.API_MASTER_ORDERTYPE_PATTERN.param.equals(type)) {
			List<MasterDataOrderType> list = orderTypeRepository.findActives().stream().map(src -> {
				MasterDataOrderType dest = new MasterDataOrderType();
				BeanUtils.copyProperties(src, dest);
				if (src.getParentId() == null) {
					dest.setParentId(0L);
				}
				return dest;
			}).collect(Collectors.toList());
			masterDataSync.setMasterDataOrderTypes(list);

		} else if (SystemParamEnum.API_MASTER_ORDERVOID_PATTERN.param.equals(type)) {
			List<MasterDataOrderVoid> list = orderVoidRepository.findActives().stream().map(src -> {
				MasterDataOrderVoid dest = new MasterDataOrderVoid();
				BeanUtils.copyProperties(src, dest);
				if (src.getParentId() == null) {
					dest.setParentId(0L);
				}
				return dest;
			}).collect(Collectors.toList());
			masterDataSync.setMasterDataOrderVoids(list);

		} else if (SystemParamEnum.API_MASTER_RESTAURANT_PATTERN.param.equals(type)) {
			List<MasterDataRestaurant> list = restaurantMasterRepository.findActives().stream().map(src -> {
				MasterDataRestaurant dest = new MasterDataRestaurant();
				BeanUtils.copyProperties(src, dest);
				return dest;
			}).collect(Collectors.toList());
			masterDataSync.setMasterDataRestaurants(list);

		} else if (SystemParamEnum.API_MASTER_TABLE_PATTERN.param.equals(type)) {
			List<MasterDataTable> list = tableKitchenRepository.findActives().stream().map(src -> {
				MasterDataTable dest = new MasterDataTable();
				BeanUtils.copyProperties(src, dest);
				if (src.getParentId() == null) {
					dest.setParentId(0L);
				}
				return dest;
			}).collect(Collectors.toList());
			masterDataSync.setMasterDataTables(list);

		} else {}
		
		return masterDataSync;
	}

}
