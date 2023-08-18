package com.gg.gpos.integration.manager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.gg.gpos.common.json.*;
import com.gg.gpos.menu.dto.*;
import com.gg.gpos.menu.entity.*;
import com.gg.gpos.menu.repository.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.gg.gpos.common.constant.FoodTypeEnum;
import com.gg.gpos.common.constant.ModuleTypeEnum;
import com.gg.gpos.common.constant.StatusSyncEnum;
import com.gg.gpos.common.constant.SymbolEnum;
import com.gg.gpos.common.constant.SyncStatusEnum;
import com.gg.gpos.common.constant.TypeRestaurantDataEditEnum;
import com.gg.gpos.common.constant.UploadImageEnum;
import com.gg.gpos.integration.dto.SyncArchiveDto;
import com.gg.gpos.integration.dto.SyncDto;
import com.gg.gpos.integration.entity.Sync;
import com.gg.gpos.integration.entity.SyncArchive;
import com.gg.gpos.integration.mapper.SyncArchiveMapper;
import com.gg.gpos.integration.mapper.SyncMapper;
import com.gg.gpos.integration.repository.SyncArchiveRepository;
import com.gg.gpos.integration.repository.SyncRepository;
import com.gg.gpos.integration.repository.TmpSyncRepository;
import com.gg.gpos.menu.mapper.CoCategoryMapper;
import com.gg.gpos.menu.mapper.CoFoodItemMapper;
import com.gg.gpos.menu.mapper.CoFoodItemModifierMapper;
import com.gg.gpos.menu.mapper.ConfigQrOrderMapper;
import com.gg.gpos.menu.mapper.FeatureMapper;
import com.gg.gpos.menu.mapper.FoodItemMapper;
import com.gg.gpos.menu.mapper.KaloGroupMapper;
import com.gg.gpos.menu.mapper.RelatedFoodItemMapper;
import com.gg.gpos.menu.mapper.ToppingFoodItemMapper;
import com.gg.gpos.reference.dto.AttachmentDto;
import com.gg.gpos.reference.entity.Attachment;
import com.gg.gpos.reference.mapper.AttachmentMapper;
import com.gg.gpos.reference.repository.AttachmentRepository;
import com.gg.gpos.res.dto.RestaurantDto;
import com.gg.gpos.res.entity.Restaurant;
import com.gg.gpos.res.entity.SyncStatus;
import com.gg.gpos.res.mapper.RestaurantMapper;
import com.gg.gpos.res.repository.RestaurantRepository;
import com.gg.gpos.res.repository.SyncStatusRepository;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
@Transactional
public class SyncDataCoMenuManager {
	@Autowired
	private CoRepository coRepository;
	@Autowired
	private CoFoodItemRepository coFoodItemRepository;
	@Autowired
	private RelatedFoodItemRepository relatedFoodItemRepository;
	@Autowired
	private SoCategoryRepository soCategoryRepository;
	@Autowired
	private CoCategoryRepository coCategoryRepository;
	@Autowired
	private CoFoodGroupDisplayRepository coFoodGroupDisplayRepository;
	@Autowired
	private ToppingFoodItemRepository toppingFoodItemRepository;
	@Autowired
	private CatalogApplyToRestaurantRepository catalogApplyToRestaurantRepository;
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
	private SyncStatusRepository syncStatusRepository;
	@Autowired
	private RestaurantRepository restaurantRepository;
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
	private ConfigQrOrderRepository configQrOrderRepository;
	@Autowired
	private ConfigQrOrderMapper configQrOrderMapper;
	@Autowired
	private RestaurantMapper restaurantMapper;
	@Autowired
	private SyncRepository syncRepository;
	@Autowired
	private SyncMapper syncMapper;
	@Autowired
	private SyncArchiveRepository syncArchiveRepository;
	@Autowired
	private TmpSyncRepository tmpSyncRepository;
	@Autowired
	private SyncArchiveMapper syncArchiveMapper;
	@Autowired
	private DigitalMenuRepository digitalMenuRepository;
	
	
	/*
	 * Đồng bộ dữ liệu từ DANH MỤC xuống SERVER NHÀ HÀNG
	 */
	public RestaurantMenuDataSync syncDataFromCatalogToServerRestaurant(SyncDto syncDto, String restaurantName, String apiUrlFile, String gatewayImageUrl) {
		log.debug("PROCESS: SYNC DATA FROM CATALOG TO SERVER RESTAURANT, RESTAURANT_CODE: {}", syncDto.getRestaurantCode());
		RestaurantMenuDataSync restaurantMenuDataSync = new RestaurantMenuDataSync();
		try {
			Integer restaurantCode = syncDto.getRestaurantCode();
			Boolean isOverride = syncDto.getOverride();
			Long coMenuId = syncDto.getCatalogId();
			// Lấy danh sách CO_FOOD_ITEM_ID đã được chỉnh sửa ở danh mục CO_MENU
			List<Long> coFoodItemEditIds = new ArrayList<>();
			// Lấy danh sách FOOD_ITEM_CODE đã bị xóa ở danh mục CO_MENU để xóa các bản ghi có CODE tương ứng ở dưới nhà hàng
			List<String> deleteCoFoodItemCodeInCatalogs = new ArrayList<>();
			// Lấy danh sách CO_CATEGORY_ID đã được chỉnh sửa ở danh mục CO_MENU
			List<Long> coCategoryEditIds = new ArrayList<>();
			// Lấy danh sách CO_CATEGORY_CODE đã bị xóa ở danh mục CO_MENU để xóa các bản ghi có CODE tương ứng ở dưới nhà hàng
			List<String> deleteCoCategoryCodeInCatalogs = new ArrayList<>();
			if(!CollectionUtils.isEmpty(syncDto.getTmpSyncs())) {
				syncDto.getTmpSyncs().stream().forEach(tmpSync ->{
					switch (tmpSync.getType()) {
					case "co_foodItem":
						coFoodItemEditIds.add(Long.parseLong(tmpSync.getValue()));
						break;
					case "delete_co_foodItem":
						deleteCoFoodItemCodeInCatalogs.add(tmpSync.getValue());
						break;
					case "co_category":
						coCategoryEditIds.add(Long.parseLong(tmpSync.getValue()));
						break;
					case "delete_co_category":
						deleteCoCategoryCodeInCatalogs.add(tmpSync.getValue());
						break;
					default:
						break;
					}
				});		
			}
			
			// Danh sách các mã nhà hàng được chọn để đồng bộ dữ liệu
			log.debug("==========> LOG SYSTEM_PARAM: CO_ID: {}, IS_OVERRIDE: {}", coMenuId, isOverride);
			// Lấy thông tin về CO_MENU
			Co co = coRepository.getOne(coMenuId);
			
    		// Lấy danh sách các CO_CATEGORY có trong danh mục CO_MENU
    		List<CoCategoryDto> coCategoryDtoInCatalogs = coCategoryRepository.findByCoIdAndRestaurantCodeIsNull(coMenuId).stream().map(coCategoryMapper::entityToDto).collect(Collectors.toList());
    		// Lấy danh sách các CO_FOOD_ITEM có trong danh mục CO_MENU
    		List<CoFoodItemDto> coFoodItemDtoInCatalogs = coFoodItemRepository.findByCoAndRestaurantCodeIsNull(co).stream().map(coFoodItemMapper::entityToDto).collect(Collectors.toList());
    		// Lấy thông tin cấu hình ảnh QR-Order
    		ConfigQrOrderDto configQrOrderExistingDto = Optional.ofNullable(configQrOrderRepository.findByCoIdAndRestaurantCodeIsNull(coMenuId)).map(configQrOrderMapper::entityToDto).orElse(null);
			
			// Trường hợp đồng bộ ghi đè
			if(isOverride) {
				cloneCoMenuWithStatusOverride(coCategoryDtoInCatalogs, coFoodItemDtoInCatalogs, restaurantCode, false, configQrOrderExistingDto);
				// Convert thông tin menu về JSON để gửi xuống nhà hàng
				restaurantMenuDataSync = convertCoMenuToJsonWithStatusOverride(restaurantMenuDataSync, restaurantCode, restaurantName, apiUrlFile, gatewayImageUrl);
			} else {
				// Trường hợp đồng bộ update thì sẽ phân ra 2 TRƯỜNG HỢP
				
				// Lấy thông tin các bản ghi CO_FOOD_ITEM đã được chỉnh sửa ở danh mục CO_MENU
				List<CoFoodItemDto> coFoodItemEditDtoInCatalogs = coFoodItemRepository.findByIdIn(coFoodItemEditIds).stream().map(coFoodItemMapper::entityToDto).collect(Collectors.toList());
				// Lấy thông tin các bản ghi CO_CATEGORY đã được chỉnh sửa ở danh mục CO_MENU
				List<CoCategoryDto> coCategoryEditDtoInCatalogs = coCategoryRepository.findByIdIn(coCategoryEditIds).stream().map(coCategoryMapper::entityToDto).collect(Collectors.toList());
				// Lấy danh sách nhà hàng đang được áp dụng danh mục CO_MENU này
				List<Integer> restaurantCodeApplies = catalogApplyToRestaurantRepository.findByCoId(coMenuId).stream().map(item -> item.getRestaurantCode()).collect(Collectors.toList());		
				
				// TRƯỜNG HỢP 1: Đã có nhà hàng áp dụng danh mục này
				if(!CollectionUtils.isEmpty(restaurantCodeApplies)) {
		    		// Kiểm tra xem nếu code nhà hàng được chọn == code nhà hàng đang áp dụng thì clone các bản ghi được dánh dấu chỉnh sửa ở danh mục SO_MENU
					if(restaurantCodeApplies.contains(restaurantCode)) {
						cloneCoMenuWithStatusUpdate(coCategoryEditDtoInCatalogs, deleteCoCategoryCodeInCatalogs, coFoodItemEditDtoInCatalogs, deleteCoFoodItemCodeInCatalogs, restaurantCode, configQrOrderExistingDto);
						// Convert thông tin menu về JSON để gửi xuống nhà hàng
						restaurantMenuDataSync = convertCoMenuToJsonWithStatusUpdate(restaurantMenuDataSync, restaurantCode, restaurantName, apiUrlFile, gatewayImageUrl);
					} else {
						// Trường hợp k trùng CO_ID thì áp dụng như đồng bộ GHI ĐÈ nhưng có lưu vào bảng đánh dấu ITEM này đã được chỉnh sửa dưới nhà hàng
						cloneCoMenuWithStatusOverride(coCategoryDtoInCatalogs, coFoodItemDtoInCatalogs, restaurantCode, true, configQrOrderExistingDto);
						// Convert thông tin menu về JSON để gửi xuống nhà hàng
						restaurantMenuDataSync = convertCoMenuToJsonWithStatusOverride(restaurantMenuDataSync, restaurantCode, restaurantName, apiUrlFile, gatewayImageUrl);
					}
				} else {
					// TRƯỜNG HỢP 2: Chưa có nhà hàng áp dụng danh mục này thì áp dụng như đồng bộ GHI ĐÈ nhưng có lưu vào bảng đánh dấu các ITEM này đã được chỉnh sửa dưới nhà hàng
	    			cloneCoMenuWithStatusOverride(coCategoryDtoInCatalogs, coFoodItemDtoInCatalogs, restaurantCode, true, configQrOrderExistingDto);
	    			// Convert thông tin menu về JSON để gửi xuống nhà hàng
	    			restaurantMenuDataSync = convertCoMenuToJsonWithStatusOverride(restaurantMenuDataSync, restaurantCode, restaurantName, apiUrlFile, gatewayImageUrl);
				}
			}
			// Lưu thông tin xem danh mục CO_MENU này đang áp dụng cho những nhà hàng nào
			saveCatalogApplyToRestaurant(restaurantCode, coMenuId);
			// Set lại trạng thái của nhà hàng là "Chưa đồng bộ"
			savedStatusAfterSyncCoMenu(restaurantCode);
			
			
			// Lấy danh sách KALO_GROUP
			List<KaloGroupDto> kaloGroupDtos = kaloGroupRepository.findAll().stream().map(kaloGroupMapper::entityToDto).collect(Collectors.toList());
			restaurantMenuDataSync.setJsonKaloGroup(convertKaloGroupToJson(kaloGroupDtos));
			restaurantMenuDataSync.setResCode(String.valueOf(restaurantCode));
		} catch (Exception e) {
			log.debug("ERROR PROCESS: SYNC DATA FROM CATALOG TO SERVER RESTAURANT EXCEPTION: {}", e);
		}
		return restaurantMenuDataSync;
	}
	
	/*
	 * Áp dụng danh mục CO_MENU từ nhà hàng
	 */
	public Boolean applyCatalogFromRestaurant(SyncDto syncDto) {
		log.debug("PROCESS: APPLY CO_MENU FROM RESTAURANT");
		try {
			Integer restaurantCode = syncDto.getRestaurantCode();
			Boolean isOverride = syncDto.getOverride();
			Long coMenuId = syncDto.getCatalogId();
			// Lấy danh sách CO_FOOD_ITEM_ID đã được chỉnh sửa ở danh mục CO_MENU
			List<Long> coFoodItemEditIds = new ArrayList<>();
			// Lấy danh sách FOOD_ITEM_CODE đã bị xóa ở danh mục CO_MENU để xóa các bản ghi có CODE tương ứng ở dưới nhà hàng
			List<String> deleteCoFoodItemCodeInCatalogs = new ArrayList<>();
			// Lấy danh sách CO_CATEGORY_ID đã được chỉnh sửa ở danh mục CO_MENU
			List<Long> coCategoryEditIds = new ArrayList<>();
			// Lấy danh sách CO_CATEGORY_CODE đã bị xóa ở danh mục CO_MENU để xóa các bản ghi có CODE tương ứng ở dưới nhà hàng
			List<String> deleteCoCategoryCodeInCatalogs = new ArrayList<>();
			if(!CollectionUtils.isEmpty(syncDto.getTmpSyncs())) {
				syncDto.getTmpSyncs().stream().forEach(tmpSync ->{
					switch (tmpSync.getType()) {
					case "co_foodItem":
						coFoodItemEditIds.add(Long.parseLong(tmpSync.getValue()));
						break;
					case "delete_co_foodItem":
						deleteCoFoodItemCodeInCatalogs.add(tmpSync.getValue());
						break;
					case "co_category":
						coCategoryEditIds.add(Long.parseLong(tmpSync.getValue()));
						break;
					case "delete_co_category":
						deleteCoCategoryCodeInCatalogs.add(tmpSync.getValue());
						break;
					default:
						break;
					}
				});		
			}
			
			// Danh sách các mã nhà hàng được chọn để đồng bộ dữ liệu
	    	log.debug("==========> LOG SYSTEM_PARAM: CO_ID: {}, IS_OVERRIDE: {}, RESTAURANT_CODE_APPLY: {}", coMenuId, isOverride, restaurantCode);
	    	// Lấy thông tin về CO_MENU
	    	Co co = coRepository.getOne(coMenuId);
	    	if(co != null) {
	    		// Lấy danh sách các CO_CATEGORY có trong danh mục CO_MENU
	    		List<CoCategoryDto> coCategoryDtoInCatalogs = coCategoryRepository.findByCoIdAndRestaurantCodeIsNull(coMenuId).stream().map(coCategoryMapper::entityToDto).collect(Collectors.toList());
	    		// Lấy danh sách các CO_FOOD_ITEM có trong danh mục CO_MENU
	    		List<CoFoodItemDto> coFoodItemDtoInCatalogs = coFoodItemRepository.findByCoAndRestaurantCodeIsNull(co).stream().map(coFoodItemMapper::entityToDto).collect(Collectors.toList());
	    		// Lấy thông tin cấu hình ảnh QR-Order
	    		ConfigQrOrderDto configQrOrderExistingDto = Optional.ofNullable(configQrOrderRepository.findByCoIdAndRestaurantCodeIsNull(coMenuId)).map(configQrOrderMapper::entityToDto).orElse(null);
	    		
	    		// trường hợp đồng bộ ghi đè
				if(isOverride) {
	    			cloneCoMenuWithStatusOverride(coCategoryDtoInCatalogs, coFoodItemDtoInCatalogs, restaurantCode, false, configQrOrderExistingDto);
				} else {
					// Trường hợp đồng bộ update thì sẽ phân ra 2 TRƯỜNG HỢP
					
					// Lấy thông tin các bản ghi CO_FOOD_ITEM đã được chỉnh sửa ở danh mục CO_MENU
					List<CoFoodItemDto> coFoodItemEditDtoInCatalogs = coFoodItemRepository.findByIdIn(coFoodItemEditIds).stream().map(coFoodItemMapper::entityToDto).collect(Collectors.toList());
					// Lấy thông tin các bản ghi CO_CATEGORY đã được chỉnh sửa ở danh mục CO_MENU
					List<CoCategoryDto> coCategoryEditDtoInCatalogs = coCategoryRepository.findByIdIn(coCategoryEditIds).stream().map(coCategoryMapper::entityToDto).collect(Collectors.toList());
					
					// Lấy thông tin xem nhà hàng này đang áp dụng dnah mục CO_MENU nào
			    	CatalogApplyToRestaurant catalogApplyToRestaurant = catalogApplyToRestaurantRepository.findByRestaurantCode(restaurantCode);
					// TRƯỜNG HỢP 1: Nhà hàng này đã áp dụng danh mục CO_MENU
					if(catalogApplyToRestaurant != null && catalogApplyToRestaurant.getCoId() != null) {
						// Kiểm tra xem nếu CO_ID của nhà hàng == CO_ID của danh mục thì clone các bản ghi được dánh dấu chỉnh sửa ở danh mục CO_MENU
						if(catalogApplyToRestaurant.getCoId().equals(coMenuId)) {
							cloneCoMenuWithStatusUpdate(coCategoryEditDtoInCatalogs, deleteCoCategoryCodeInCatalogs, coFoodItemEditDtoInCatalogs, deleteCoFoodItemCodeInCatalogs, restaurantCode, configQrOrderExistingDto);
						} else {
							// Trường hợp k trùng CO_ID thì áp dụng như đồng bộ GHI ĐÈ nhưng có lưu vào bảng đánh dấu ITEM này đã được chỉnh sửa dưới nhà hàng
							cloneCoMenuWithStatusOverride(coCategoryDtoInCatalogs, coFoodItemDtoInCatalogs, restaurantCode, true, configQrOrderExistingDto);
						}
					} else {
						// TRƯỜNG HỢP 2: Chưa có nhà hàng áp dụng danh mục này thì áp dụng như đồng bộ GHI ĐÈ nhưng có lưu vào bảng đánh dấu các ITEM này đã được chỉnh sửa dưới nhà hàng
		    			cloneCoMenuWithStatusOverride(coCategoryDtoInCatalogs, coFoodItemDtoInCatalogs, restaurantCode, true, configQrOrderExistingDto);
					}
				}
				// Lưu thông tin xem danh mục CO_MENU này đang áp dụng cho những nhà hàng nào
    			saveCatalogApplyToRestaurant(restaurantCode, coMenuId);
    			// Set lại trạng thái của nhà hàng là "Chưa đồng bộ"
    			savedStatusAfterSyncCoMenu(restaurantCode);
	    	}
	    	// Nếu đồng bộ thành công thì xóa bản ghi "SYNC" và tạo mới bản ghi lịch sử đồng bộ "SYNC_ARCHIVE"
			return deleteSyncAndInsertSyncArchiveAfterSync(syncDto, null);
		} catch (Exception e) {

			log.debug("ERROR PROCESS: APPLY CO_MENU FROM RESTAURANT EXCEPTION: {}", e);
			// Nếu đồng bộ thành công thì xóa bản ghi "SYNC" và tạo mới bản ghi lịch sử đồng bộ "SYNC_ARCHIVE"
			return deleteSyncAndInsertSyncArchiveAfterSync(syncDto, e.getMessage());
		}
	}
	
	/*
	 * Áp dụng danh mục CO_MENU xuống nhà hàng - bản mới sử dụng bảng Sync để đồng bộ lần lượt
	 */
	public Boolean syncCoMenuFromCatalogToRestaurant(SyncDto syncDto) {
		log.debug("PROCESS: APPLY CO_MENU FROM CATALOG TO RESTAURANT, RESTAURANTS_CODE: {}", syncDto.getRestaurantCode());
		try {
			Integer restaurantCode = syncDto.getRestaurantCode();
			Boolean isOverride = syncDto.getOverride();
			Long coMenuId = syncDto.getCatalogId();
			// Lấy danh sách CO_FOOD_ITEM_ID đã được chỉnh sửa ở danh mục CO_MENU
			List<Long> coFoodItemEditIds = new ArrayList<>();
			// Lấy danh sách FOOD_ITEM_CODE đã bị xóa ở danh mục CO_MENU để xóa các bản ghi có CODE tương ứng ở dưới nhà hàng
			List<String> deleteCoFoodItemCodeInCatalogs = new ArrayList<>();
			// Lấy danh sách CO_CATEGORY_ID đã được chỉnh sửa ở danh mục CO_MENU
			List<Long> coCategoryEditIds = new ArrayList<>();
			// Lấy danh sách CO_CATEGORY_CODE đã bị xóa ở danh mục CO_MENU để xóa các bản ghi có CODE tương ứng ở dưới nhà hàng
			List<String> deleteCoCategoryCodeInCatalogs = new ArrayList<>();
			if(!CollectionUtils.isEmpty(syncDto.getTmpSyncs())) {
				syncDto.getTmpSyncs().stream().forEach(tmpSync ->{
					switch (tmpSync.getType()) {
					case "co_foodItem":
						coFoodItemEditIds.add(Long.parseLong(tmpSync.getValue()));
						break;
					case "delete_co_foodItem":
						deleteCoFoodItemCodeInCatalogs.add(tmpSync.getValue());
						break;
					case "co_category":
						coCategoryEditIds.add(Long.parseLong(tmpSync.getValue()));
						break;
					case "delete_co_category":
						deleteCoCategoryCodeInCatalogs.add(tmpSync.getValue());
						break;
					default:
						break;
					}
				});		
			}
			
			// Danh sách các mã nhà hàng được chọn để đồng bộ dữ liệu
			log.debug("==========> LOG SYSTEM_PARAM: CO_ID: {}, IS_OVERRIDE: {}", coMenuId, isOverride);
			// Lấy thông tin về CO_MENU
			Co co = coRepository.getOne(coMenuId);
			
			// Lấy danh sách các CO_CATEGORY có trong danh mục CO_MENU
    		List<CoCategoryDto> coCategoryDtoInCatalogs = coCategoryRepository.findByCoIdAndRestaurantCodeIsNull(coMenuId).stream().map(coCategoryMapper::entityToDto).collect(Collectors.toList());
    		// Lấy danh sách các CO_FOOD_ITEM có trong danh mục CO_MENU
    		List<CoFoodItemDto> coFoodItemDtoInCatalogs = coFoodItemRepository.findByCoAndRestaurantCodeIsNull(co).stream().map(coFoodItemMapper::entityToDto).collect(Collectors.toList());
    		// Lấy thông tin cấu hình ảnh QR-Order
    		ConfigQrOrderDto configQrOrderExistingDto = Optional.ofNullable(configQrOrderRepository.findByCoIdAndRestaurantCodeIsNull(coMenuId)).map(configQrOrderMapper::entityToDto).orElse(null);
			
			// trường hợp đồng bộ ghi đè
			if(isOverride) {
    			cloneCoMenuWithStatusOverride(coCategoryDtoInCatalogs, coFoodItemDtoInCatalogs, restaurantCode, false, configQrOrderExistingDto);
    			// Lưu thông tin xem danh mục CO_MENU này đang áp dụng cho những nhà hàng nào
    			saveCatalogApplyToRestaurant(restaurantCode, coMenuId);
    			// Set lại trạng thái của nhà hàng là "Chưa đồng bộ"
    			savedStatusAfterSyncCoMenu(restaurantCode);
				
			} else {
				// Trường hợp đồng bộ update thì sẽ phân ra 2 TRƯỜNG HỢP
				
				// Lấy thông tin các bản ghi CO_FOOD_ITEM đã được chỉnh sửa ở danh mục CO_MENU
				List<CoFoodItemDto> coFoodItemEditDtoInCatalogs = coFoodItemRepository.findByIdIn(coFoodItemEditIds).stream().map(coFoodItemMapper::entityToDto).collect(Collectors.toList());
				
				// Lấy thông tin các bản ghi CO_CATEGORY đã được chỉnh sửa ở danh mục CO_MENU
				List<CoCategoryDto> coCategoryEditDtoInCatalogs = coCategoryRepository.findByIdIn(coCategoryEditIds).stream().map(coCategoryMapper::entityToDto).collect(Collectors.toList());
				
				// Lấy danh sách nhà hàng đang được áp dụng danh mục CO_MENU này
				List<Integer> restaurantCodeApplies = catalogApplyToRestaurantRepository.findByCoId(coMenuId).stream().map(item -> item.getRestaurantCode()).collect(Collectors.toList());		

				// TRƯỜNG HỢP 1: Đã có nhà hàng áp dụng danh mục này
				if(!CollectionUtils.isEmpty(restaurantCodeApplies)) { 
					// Kiểm tra xem nếu code nhà hàng được chọn == code nhà hàng đang áp dụng thì clone các bản ghi được dánh dấu chỉnh sửa ở danh mục CO_MENU
					if(restaurantCodeApplies.contains(restaurantCode)) {
						cloneCoMenuWithStatusUpdate(coCategoryEditDtoInCatalogs, deleteCoCategoryCodeInCatalogs, coFoodItemEditDtoInCatalogs, deleteCoFoodItemCodeInCatalogs, restaurantCode, configQrOrderExistingDto);
					} else {
						// Trường hợp k trùng mã code thì áp dụng như đồng bộ GHI ĐÈ nhưng có lưu vào bảng đánh dấu ITEM này đã được chỉnh sửa dưới nhà hàng
						cloneCoMenuWithStatusOverride(coCategoryDtoInCatalogs, coFoodItemDtoInCatalogs, restaurantCode, true, configQrOrderExistingDto);
					}
					// Lưu thông tin xem danh mục CO_MENU này đang áp dụng cho những nhà hàng nào
	    			saveCatalogApplyToRestaurant(restaurantCode, coMenuId);
	    			// Set lại trạng thái của nhà hàng là "Chưa đồng bộ"
	    			savedStatusAfterSyncCoMenu(restaurantCode);
					
				} else {
					// TRƯỜNG HỢP 2: Chưa có nhà hàng áp dụng danh mục này thì áp dụng như đồng bộ GHI ĐÈ nhưng có lưu vào bảng đánh dấu các ITEM này đã được chỉnh sửa dưới nhà hàng
	    			cloneCoMenuWithStatusOverride(coCategoryDtoInCatalogs, coFoodItemDtoInCatalogs, restaurantCode, true, configQrOrderExistingDto);
	    			// Lưu thông tin xem danh mục CO_MENU này đang áp dụng cho những nhà hàng nào
	    			saveCatalogApplyToRestaurant(restaurantCode, coMenuId);
	    			// Set lại trạng thái của nhà hàng là "Chưa đồng bộ"
	    			savedStatusAfterSyncCoMenu(restaurantCode);
					
				}
			}
			// Nếu đồng bộ thành công thì xóa bản ghi "SYNC" và tạo mới bản ghi lịch sử đồng bộ "SYNC_ARCHIVE"
			return deleteSyncAndInsertSyncArchiveAfterSync(syncDto, null);
			 
		} catch (Exception e) {
			log.error("ERROR PROCESS: APPLY CO_MENU FROM CATALOG TO RESTAURANT EXCEPTION: {}", e);
			// Nếu đồng bộ thành công thì xóa bản ghi "SYNC" và tạo mới bản ghi lịch sử đồng bộ "SYNC_ARCHIVE"
			return deleteSyncAndInsertSyncArchiveAfterSync(syncDto, e.getMessage());
		}
	}
	
	/*
	 * Set lại trạng thái bảng SYNC sau khi đã đồng bộ xong
	 */
	private Boolean deleteSyncAndInsertSyncArchiveAfterSync(SyncDto syncDto, String result) {
		log.debug("PROCESS FUNCTION: DELETE SYNC AND INSERT SYNC_ARCHIVE");
		try {
			SyncArchiveDto syncArchiveDto = new SyncArchiveDto();
			BeanUtils.copyProperties(syncDto, syncArchiveDto);
			syncArchiveDto.setId(null);
			if(result != null) {
				syncArchiveDto.setStatus(SyncStatusEnum.FAIL.val);
				syncArchiveDto.setResult(result);
				// Set lại trạng thái của bảng đồng bộ sync là đồng bộ bị lỗi
				Sync sync = Optional.of(syncDto).map(syncMapper::dtoToEntity).orElse(null);
				sync.setStatus(SyncStatusEnum.FAIL.val);
				sync.setResult(result);
				syncRepository.save(sync);
			} else {
				syncArchiveDto.setStatus(SyncStatusEnum.SUCCESS.val);
				syncArchiveDto.setResult(SyncStatusEnum.SUCCESS.val);
				// Xóa dữ liệu về bảng TMP_SYNC (Dữ liệu này lưu thông tin các bản ghi đã được chỉnh sửa)
				Sync sync = Optional.of(syncDto).map(syncMapper::dtoToEntity).orElse(null);
				tmpSyncRepository.deleteBySync(sync);
				// Xóa thông tin dữ liệu về bản SYNC
				syncRepository.delete(sync);
			}
			syncArchiveDto.setCreatedDate(LocalDateTime.now());
			syncArchiveDto.setSyncEndDate(LocalDateTime.now());
			SyncArchive syncArchive = Optional.of(syncArchiveDto).map(syncArchiveMapper::dtoToEntity).orElse(null);
			syncArchiveRepository.save(syncArchive);
			return true;
		} catch (Exception e) {
			log.debug("ERROR PROCESS FUNCTION: DELETE SYNC AND INSERT SYNC_ARCHIVE");
			return false;
		}
	}
	
	/*
	 * Clone danh sách các CO_FOOD_ITEM và CO_CATEGORY có trong CO_MENU xuống nhà hàng với trạng thái "UPDATE"
	 */
	private void cloneCoMenuWithStatusUpdate(List<CoCategoryDto> coCategoryExistingDtos, List<String> deleteCoCategoryCodeInCatalogs, List<CoFoodItemDto> coFoodItemExistingDtos, List<String> deleteCoFoodItemCodeInCatalogs, Integer restaurantCode, ConfigQrOrderDto configQrOrderExistingDto) {
		log.debug("PROCESS FUNCTION: CLONE CO_MENU WITH STATUS UPDATE BY RESTAURANT_CODE: {}", restaurantCode);
		try {
			// các biến cần dùng
			String coCategoryModuleType = ModuleTypeEnum.CO_CATEGORY.val;
			String coFoodItemModuleType = ModuleTypeEnum.CO_FOOD_ITEM.val;
			String configQrOrderModuleType = ModuleTypeEnum.CONFIG_QR_ORDER.val;
			String coCategoryTypeRestaurantDataEdit = TypeRestaurantDataEditEnum.CO_CATEGORY.val;
			String coFoodItemTypeRestaurantDataEdit = TypeRestaurantDataEditEnum.CO_FOODITEM.val;
			
    		// Xóa các dữ liệu đã bị xóa ở danh mục CO_MENU 
			deleteCoMenuWithStatusUpdate(deleteCoCategoryCodeInCatalogs, deleteCoFoodItemCodeInCatalogs, restaurantCode);
    		
			if(!CollectionUtils.isEmpty(coCategoryExistingDtos)) {
				// Lấy danh sách CO_CATEGORY_CODE đã chỉnh sửa hoặc đã bị xóa ở dưới nhà hàng
				List<String> coCategoryCodeChangedAndDeleteds = getCoCategoryCodeChangedAndDeleteds(restaurantCode);
				// Tạo mới dữ liệu
	    		coCategoryExistingDtos.stream().forEach(coCategoryExistingDto ->{
					CoCategory coCategoryExisting = Optional.ofNullable(coCategoryExistingDto).map(coCategoryMapper::dtoToEntity).orElse(null);
					// Lấy thông tin OrderCategoryCode để kiểm tra xem CODE này đã bị chỉnh sửa hoặc đã bị xóa ở phía nhà hàng chưa
					String orderCategoryCode = coCategoryExisting.getSoCategory().getOrderCategory().getCode();
					// Lấy thông tin SO_CATEGRY ở nhà hàng mà CO_CATEGORY này sẽ trỏ tới 
					List<SoCategory> soCategoryExistings = soCategoryRepository.findByRestaurantCodeAndOrderCategory(restaurantCode, coCategoryExisting.getSoCategory().getOrderCategory());
					if(!CollectionUtils.isEmpty(soCategoryExistings) && !coCategoryCodeChangedAndDeleteds.contains(orderCategoryCode)) {
						CoCategory coCategoryExistingInRestaurant = coCategoryRepository.findByorderCategoryCodeAndResCode(orderCategoryCode, restaurantCode);
	        			if(coCategoryExistingInRestaurant != null) {
	        				deleteCoCategory(coCategoryExistingInRestaurant);
	        			}
						
						SoCategory soCategoryExisting = soCategoryExistings.get(0);
						CoCategoryDto coCategoryDto = new CoCategoryDto();
						BeanUtils.copyProperties(coCategoryExistingDto, coCategoryDto);
						coCategoryDto.setId(null);
						coCategoryDto.setRestaurantCode(restaurantCode);
						CoCategory coCategory = Optional.ofNullable(coCategoryDto).map(coCategoryMapper::dtoToEntity).orElse(null);
						coCategory.setSoCategory(soCategoryExisting);
						CoCategory coCategorySaved = coCategoryRepository.save(coCategory);

						// Lấy thông tin các DIGITAL-MENU cần clone
						List<DigitalMenuDto> digitalMenuDtoExistingDtos =  coCategoryExistingDto.getDigitalMenus();
						if(!CollectionUtils.isEmpty(digitalMenuDtoExistingDtos)) {
							digitalMenuDtoExistingDtos.stream().forEach(digitalMenuDto ->{
								DigitalMenu digitalMenu = new DigitalMenu();
								digitalMenu.setCoCategory(coCategorySaved);
								digitalMenu.setOrderNumber(digitalMenuDto.getOrderNumber());
								digitalMenu.setSrcImg(digitalMenuDto.getSrcImg());
								digitalMenu.setUrlImg(digitalMenuDto.getUrlImg());
								digitalMenu.setUrl(digitalMenuDto.getUrl());
								digitalMenu.setName(digitalMenuDto.getName());
								digitalMenuRepository.save(digitalMenu);
							});
						}

						// Lấy thông tin các CO_FOOD_GROUP_DISPLAY cần clone
						List<CoFoodGroupDisplayDto> coFoodGroupDisplayExistingDtos =  coCategoryExistingDto.getCoFoodGroupDisplays();
						if(!CollectionUtils.isEmpty(coFoodGroupDisplayExistingDtos)) {
							coFoodGroupDisplayExistingDtos.stream().forEach(coFoodGroupDisplayExistingDto ->{
								CoFoodGroupDisplay coFoodGroupDisplay = new CoFoodGroupDisplay();
	    						coFoodGroupDisplay.setCoCategory(coCategorySaved);
	    						coFoodGroupDisplay.setFoodGroupCode(coFoodGroupDisplayExistingDto.getFoodGroupCode());
	    						coFoodGroupDisplayRepository.save(coFoodGroupDisplay);
							});
						}
						Long newId = coCategorySaved.getId();
						// lưu thông tin ảnh
						cloneAttachment(coCategoryExistingDto.getId(), newId, coCategoryModuleType, restaurantCode, orderCategoryCode);
						// Đánh dấu bản ghi này đã được update để phục vụ cho việc đồng bộ UPDATE xuống SERVER nhà hàng
						saveRestaurantDataEdit(newId.toString(), coCategoryTypeRestaurantDataEdit, restaurantCode);
					}
				});
			}
			
			if(!CollectionUtils.isEmpty(coFoodItemExistingDtos)) {
				// Lưu lại mã id đã được thêm dưới nhà hàng
				List<Long> foodItemIdExist = new ArrayList<>(); 
				// Lấy danh sách CO_FOOD_ITEM_CODE đã chỉnh sửa hoặc đã bị xóa ở dưới nhà hàng
				List<String> coFoodItemCodeChangedAndDeleteds = getCoFoodItemCodeChangedAndDeleteds(restaurantCode);
				// Tạo mới dữ liệu
	    		coFoodItemExistingDtos.stream().forEach(coFoodItemExistingDto ->{
	    			CoFoodItem coFoodItemExisting = Optional.ofNullable(coFoodItemExistingDto).map(coFoodItemMapper::dtoToEntity).orElse(null);
	    			Long foodItemId = coFoodItemExisting.getFoodItem().getId();
	    			// trường hợp món ăn này đã có rồi thì không thêm vào nữa
	    			if(!foodItemIdExist.contains(foodItemId)) {
	    				// Lấy thông tin FoodItemCode để kiểm tra xem CODE này đã bị chỉnh sửa hoặc đã bị xóa ở phía nhà hàng chưa
		    			String foodItemCode = coFoodItemExisting.getFoodItem().getCode();
		    			if(!coFoodItemCodeChangedAndDeleteds.contains(foodItemCode)) {
		    				CoFoodItem coFoodItemExistingInRestaurant = coFoodItemRepository.findByRestaurantCodeAndFoodItem_Code(restaurantCode,foodItemCode);
		        			if(coFoodItemExistingInRestaurant != null) {
		    					deleteCoFoodItem(coFoodItemExistingInRestaurant);
		        			}
		    				
		    				// Lấy dữ liệu các thông tin bổ sung cho món ăn cần clone
			    			List<RelatedFoodItem> relatedFoodItemExistings = relatedFoodItemRepository.findByCoFoodItem(coFoodItemExisting);
		        			List<ToppingFoodItem> toppingFoodItemExistings = toppingFoodItemRepository.findByCoFoodItem(coFoodItemExisting);
		        			List<CoFoodItemModifier> coFoodItemModifierExistings = coFoodItemModifierRepository.findByCoFoodItem(coFoodItemExisting);
		        			List<Feature> featureExistings = new ArrayList<>(coFoodItemExisting.getFeatures());
		        			
		        			CoFoodItemDto coFoodItemDto = new CoFoodItemDto();
		        			BeanUtils.copyProperties(coFoodItemExistingDto, coFoodItemDto);
		        			coFoodItemDto.setId(null);
		        			CoFoodItem coFoodItem = Optional.ofNullable(coFoodItemDto).map(coFoodItemMapper::dtoToEntity).orElse(null);
		        			coFoodItem.setRestaurantCode(restaurantCode);
		        			coFoodItem.setCo(coFoodItemExisting.getCo());
		        			coFoodItem.setFoodItem(coFoodItemExisting.getFoodItem());
							coFoodItem.setFeatures(featureExistings);
							CoFoodItem coFoodItemSaved = coFoodItemRepository.save(coFoodItem);
		        			
							if(!CollectionUtils.isEmpty(coFoodItemModifierExistings)) {
								coFoodItemModifierExistings.stream().forEach(item ->{
									CoFoodItemModifier coFoodItemModifier = new CoFoodItemModifier();
									coFoodItemModifier.setCoFoodItem(coFoodItemSaved);
									coFoodItemModifier.setModifier(item.getModifier());
									coFoodItemModifier.setType(item.getType());
									coFoodItemModifier.setNumberOfChili(item.getNumberOfChili());
									coFoodItemModifierRepository.save(coFoodItemModifier);
								});
							}
							
							if(!CollectionUtils.isEmpty(relatedFoodItemExistings)) {
								relatedFoodItemExistings.stream().forEach(r ->{
									RelatedFoodItem relatedFoodItem = new RelatedFoodItem();
		    						relatedFoodItem.setCoFoodItem(coFoodItemSaved);
		    						relatedFoodItem.setFoodItemCode(r.getFoodItemCode());
									relatedFoodItem.setFoodItemName(r.getFoodItemName());
		    						relatedFoodItem.setType(r.getType());
		    						relatedFoodItem.setSapCode(r.getSapCode());
		    						relatedFoodItem.setNameDisplay(r.getNameDisplay());
		    						relatedFoodItemRepository.save(relatedFoodItem);
		    					});
							}
							
							if(!CollectionUtils.isEmpty(toppingFoodItemExistings)) {
								toppingFoodItemExistings.stream().forEach(r ->{
									ToppingFoodItem toppingFoodItem = new ToppingFoodItem();
		    						toppingFoodItem.setCoFoodItem(coFoodItemSaved);
		    						toppingFoodItem.setFoodItemCode(r.getFoodItemCode());
		    						toppingFoodItem.setFoodItemName(r.getFoodItemName());
		    						toppingFoodItem.setModifierCode(r.getModifierCode());
		    						toppingFoodItem.setModifierName(r.getModifierName());
		    						toppingFoodItem.setSapCode(r.getSapCode());
		    						toppingFoodItemRepository.save(toppingFoodItem);
		    					});
							}
							
							Long newId = coFoodItemSaved.getId();
							// lưu thông tin ảnh
							cloneAttachment(coFoodItemExistingDto.getId(), newId, coFoodItemModuleType, restaurantCode, foodItemCode);
							// Đánh dấu bản ghi này đã được update để phục vụ cho việc đồng bộ UPDATE xuống SERVER nhà hàng
							saveRestaurantDataEdit(newId.toString(), coFoodItemTypeRestaurantDataEdit, restaurantCode);
		    			}
		    			foodItemIdExist.add(foodItemId);	    			}
	    		});
			}
			
			ConfigQrOrder configQrOrderExistingInRestaurant = configQrOrderRepository.findByRestaurantCode(String.valueOf(restaurantCode)); 
			RestaurantDto restaurantDto = Optional.of(restaurantRepository.findByCode(restaurantCode)).map(restaurantMapper::entityToDto).orElse(null);
			if(restaurantDto != null && configQrOrderExistingDto != null) {
				// Trường hợp đã có dữ liệu liên quan đến CONFIG_QR_ORDER thì check điều kiện CO_ID == null để xem bản ghi đã bị nhà hàng chỉnh sửa chưa
				if(configQrOrderExistingInRestaurant != null) {
					// Trường hợp phía nhà hàng đã sửa thông tin ảnh thì không cập nhật - CO_ID == null có nghĩa là thông tin bảng đã được cập nhật
					if(configQrOrderExistingInRestaurant.getCoId() != null) {
						// xóa dữ liệu trước khi tạo mới
						attachmentRepository.deleteByModuleIdAndModuleType(configQrOrderExistingInRestaurant.getId(), ModuleTypeEnum.CONFIG_QR_ORDER.val);
						configQrOrderRepository.delete(configQrOrderExistingInRestaurant);
						
						ConfigQrOrderDto configQrOrderDto = new ConfigQrOrderDto();
						BeanUtils.copyProperties(configQrOrderExistingDto, configQrOrderDto);
						configQrOrderDto.setId(null);
						configQrOrderDto.setIp(restaurantDto.getIp());
						configQrOrderDto.setPort(restaurantDto.getPort());
						configQrOrderDto.setRestaurantCode(String.valueOf(restaurantCode));
						ConfigQrOrder configQrOrder = Optional.ofNullable(configQrOrderDto).map(configQrOrderMapper::dtoToEntity).orElse(null);
						ConfigQrOrderDto savedConfigQrOrderDto = Optional.ofNullable(configQrOrderRepository.save(configQrOrder)).map(configQrOrderMapper::entityToDto).orElse(null);
						// lưu thông tin ảnh
						cloneAttachment(configQrOrderExistingDto.getId(), savedConfigQrOrderDto.getId(), configQrOrderModuleType, restaurantCode, null);
					}
				} else {
					ConfigQrOrderDto configQrOrderDto = new ConfigQrOrderDto();
					BeanUtils.copyProperties(configQrOrderExistingDto, configQrOrderDto);
					configQrOrderDto.setId(null);
					configQrOrderDto.setIp(restaurantDto.getIp());
					configQrOrderDto.setPort(restaurantDto.getPort());
					configQrOrderDto.setRestaurantCode(String.valueOf(restaurantCode));
					ConfigQrOrder configQrOrder = Optional.ofNullable(configQrOrderDto).map(configQrOrderMapper::dtoToEntity).orElse(null);
					ConfigQrOrderDto savedConfigQrOrderDto = Optional.ofNullable(configQrOrderRepository.save(configQrOrder)).map(configQrOrderMapper::entityToDto).orElse(null);
					// lưu thông tin ảnh
					cloneAttachment(configQrOrderExistingDto.getId(), savedConfigQrOrderDto.getId(), configQrOrderModuleType, restaurantCode, null);
				}
			}
		} catch (Exception e) {
			log.error("ERROR PROCESS FUNCTION: CLONE CO_MENU WITH STATUS UPDATE EXCEPTION: {}", e);
		}
	}
	
	/*
	 * Clone danh sách các CO_FOOD_ITEM và CO_CATEGORY có trong CO_MENU xuống nhà hàng với trạng thái "GHI ĐÈ"
	 * Trường hợp chọn áp dụng UPDATE nhưng phía nhà hàng chưa áp dụng danh mục thì cũng được tính là GHI ĐÈ nhưng sẽ CÓ LƯU thông tin đánh dấu bản ghi này đã được update để phục vụ cho việc đồng bộ UPDATE xuống SERVER nhà hàng
	 */
	private void cloneCoMenuWithStatusOverride(List<CoCategoryDto> coCategoryExistingDtos, List<CoFoodItemDto> coFoodItemExistingDtos, Integer restaurantCode, Boolean isSavedRestaurantDataEdit, ConfigQrOrderDto configQrOrderExistingDto) {
		log.debug("PROCESS FUNCTION: CLONE CO_MENU WITH STATUS OVERRIDE BY RESTAURANT_CODE: {}, IS_SAVED_RESTAURANT_DATA_EDIT: {}", restaurantCode, isSavedRestaurantDataEdit);
		try {
			// các biến cần dùng
			String coCategoryModuleType = ModuleTypeEnum.CO_CATEGORY.val;
			String coFoodItemModuleType = ModuleTypeEnum.CO_FOOD_ITEM.val;
			String configQrOrderModuleType = ModuleTypeEnum.CONFIG_QR_ORDER.val;
			String coCategoryTypeRestaurantDataEdit = TypeRestaurantDataEditEnum.CO_CATEGORY.val;
			String coFoodItemTypeRestaurantDataEdit = TypeRestaurantDataEditEnum.CO_FOODITEM.val;
			
    		// Xóa các dữ liệu CO_MENU cũ ở dưới nhà hàng trước khi đồng bộ
			deleteCoMenuWithStatusOverride(restaurantCode);
			
			// Xóa các dữ liệu lữu trữ thông tin các CO_CATEGORY đã chỉnh sửa ở dưới nhà hàng (liên quan đến việc đồng bộ update xuống server nhà hàng)
			restaurantDataEditRepository.deleteByRestaurantCodeAndType(restaurantCode,coCategoryTypeRestaurantDataEdit);
    		restaurantDataEditRepository.deleteByRestaurantCodeAndType(restaurantCode,TypeRestaurantDataEditEnum.DELETE_CO_CATEGORY.val);
    		restaurantDataEditRepository.deleteByRestaurantCodeAndType(restaurantCode,TypeRestaurantDataEditEnum.DELETE_CO_CATEGORY_RES.val);
    		restaurantDataEditRepository.deleteByRestaurantCodeAndType(restaurantCode,TypeRestaurantDataEditEnum.DELETE_CO_FG_DISPLAY.val);
    		restaurantDataEditRepository.deleteByRestaurantCodeAndType(restaurantCode,TypeRestaurantDataEditEnum.DELETE_CO_CATEGORY_OLD.val);
    		
    		// Xóa các dữ liệu lữu trữ thông tin các CO_FOOD_ITEM đã chỉnh sửa ở dưới nhà hàng (liên quan đến việc đồng bộ update xuống server nhà hàng)
    		restaurantDataEditRepository.deleteByRestaurantCodeAndType(restaurantCode,TypeRestaurantDataEditEnum.CO_FOODITEM.val);
    		restaurantDataEditRepository.deleteByRestaurantCodeAndType(restaurantCode,TypeRestaurantDataEditEnum.DELETE_CO_FOODITEM.val);
    		restaurantDataEditRepository.deleteByRestaurantCodeAndType(restaurantCode,TypeRestaurantDataEditEnum.DELETE_CO_FOODITEM_OLD.val);
    		restaurantDataEditRepository.deleteByRestaurantCodeAndType(restaurantCode,TypeRestaurantDataEditEnum.DELETE_CO_FOODITEM_RES.val);
    		
			if(!CollectionUtils.isEmpty(coCategoryExistingDtos)) {
				// Tạo mới dữ liệu
	    		coCategoryExistingDtos.stream().forEach(coCategoryExistingDto ->{
					CoCategory coCategoryExisting = Optional.ofNullable(coCategoryExistingDto).map(coCategoryMapper::dtoToEntity).orElse(null);
					// Lấy thông tin SO_CATEGRY ở nhà hàng mà CO_CATEGORY này sẽ trỏ tới 
					List<SoCategory> soCategoryExistings = soCategoryRepository.findByRestaurantCodeAndOrderCategory(restaurantCode, coCategoryExisting.getSoCategory().getOrderCategory());
					if(!CollectionUtils.isEmpty(soCategoryExistings)) {
						SoCategory soCategoryExisting = soCategoryExistings.get(0);
						CoCategoryDto coCategoryDto = new CoCategoryDto();
						BeanUtils.copyProperties(coCategoryExistingDto, coCategoryDto);
						coCategoryDto.setId(null);
						coCategoryDto.setRestaurantCode(restaurantCode);
						CoCategory coCategory = Optional.ofNullable(coCategoryDto).map(coCategoryMapper::dtoToEntity).orElse(null);
						coCategory.setSoCategory(soCategoryExisting);
						CoCategory coCategorySaved = coCategoryRepository.save(coCategory);

						// Lấy thông tin các DIGITAL-MENU cần clone
						List<DigitalMenuDto> digitalMenuDtoExistingDtos =  coCategoryExistingDto.getDigitalMenus();
						if(!CollectionUtils.isEmpty(digitalMenuDtoExistingDtos)) {
							digitalMenuDtoExistingDtos.stream().forEach(digitalMenuDto ->{
								DigitalMenu digitalMenu = new DigitalMenu();
								digitalMenu.setCoCategory(coCategorySaved);
								digitalMenu.setOrderNumber(digitalMenuDto.getOrderNumber());
								digitalMenu.setSrcImg(digitalMenuDto.getSrcImg());
								digitalMenu.setUrlImg(digitalMenuDto.getUrlImg());
								digitalMenu.setUrl(digitalMenuDto.getUrl());
								digitalMenu.setName(digitalMenuDto.getName());
								digitalMenuRepository.save(digitalMenu);
							});
						}

						// Lấy thông tin các CO_FOOD_GROUP_DISPLAY cần clone
						List<CoFoodGroupDisplayDto> coFoodGroupDisplayExistingDtos =  coCategoryExistingDto.getCoFoodGroupDisplays();
						if(!CollectionUtils.isEmpty(coFoodGroupDisplayExistingDtos)) {
							coFoodGroupDisplayExistingDtos.stream().forEach(coFoodGroupDisplayExistingDto ->{
								CoFoodGroupDisplay coFoodGroupDisplay = new CoFoodGroupDisplay();
	    						coFoodGroupDisplay.setCoCategory(coCategorySaved);
	    						coFoodGroupDisplay.setFoodGroupCode(coFoodGroupDisplayExistingDto.getFoodGroupCode());
	    						coFoodGroupDisplayRepository.save(coFoodGroupDisplay);
							});
						}
						Long newId = coCategorySaved.getId();
						// lưu thông tin ảnh
						cloneAttachment(coCategoryExistingDto.getId(), newId, coCategoryModuleType, restaurantCode, soCategoryExisting.getOrderCategory().getCode());
						// Đánh dấu bản ghi này đã được update để phục vụ cho việc đồng bộ UPDATE xuống SERVER nhà hàng
						if(isSavedRestaurantDataEdit) {
							saveRestaurantDataEdit(newId.toString(), coCategoryTypeRestaurantDataEdit, restaurantCode);
						}
					}
				});
			}
			
			
			if(!CollectionUtils.isEmpty(coFoodItemExistingDtos)) {
				// Lưu lại mã id đã được thêm dưới nhà hàng
				List<Long> foodItemIdExist = new ArrayList<>(); 
				
				// Tạo mới dữ liệu
	    		coFoodItemExistingDtos.stream().forEach(coFoodItemExistingDto ->{
	    			CoFoodItem coFoodItemExisting = Optional.ofNullable(coFoodItemExistingDto).map(coFoodItemMapper::dtoToEntity).orElse(null);
	    			Long foodItemId = coFoodItemExisting.getFoodItem().getId();
	    			// trường hợp món ăn này đã có rồi thì không thêm vào nữa
	    			if(!foodItemIdExist.contains(foodItemId)) {
	    				// Lấy dữ liệu các thông tin bổ sung cho món ăn cần clone
		    			List<RelatedFoodItem> relatedFoodItemExistings = relatedFoodItemRepository.findByCoFoodItem(coFoodItemExisting);
	        			List<ToppingFoodItem> toppingFoodItemExistings = toppingFoodItemRepository.findByCoFoodItem(coFoodItemExisting);
	        			List<CoFoodItemModifier> coFoodItemModifierExistings = coFoodItemModifierRepository.findByCoFoodItem(coFoodItemExisting);
	        			List<Feature> featureExistings = new ArrayList<>(coFoodItemExisting.getFeatures());
	        			
	        			CoFoodItemDto coFoodItemDto = new CoFoodItemDto();
	        			BeanUtils.copyProperties(coFoodItemExistingDto, coFoodItemDto);
	        			coFoodItemDto.setId(null);
	        			CoFoodItem coFoodItem = Optional.ofNullable(coFoodItemDto).map(coFoodItemMapper::dtoToEntity).orElse(null);
	        			coFoodItem.setRestaurantCode(restaurantCode);
	        			coFoodItem.setCo(coFoodItemExisting.getCo());
	        			coFoodItem.setFoodItem(coFoodItemExisting.getFoodItem());
						coFoodItem.setFeatures(featureExistings);
						CoFoodItem coFoodItemSaved = coFoodItemRepository.save(coFoodItem);
	        			
						if(!CollectionUtils.isEmpty(coFoodItemModifierExistings)) {
							coFoodItemModifierExistings.stream().forEach(item ->{
								CoFoodItemModifier coFoodItemModifier = new CoFoodItemModifier();
								coFoodItemModifier.setCoFoodItem(coFoodItemSaved);
								coFoodItemModifier.setModifier(item.getModifier());
								coFoodItemModifier.setType(item.getType());
								coFoodItemModifier.setNumberOfChili(item.getNumberOfChili());
								coFoodItemModifierRepository.save(coFoodItemModifier);
							});
						}
						
						if(!CollectionUtils.isEmpty(relatedFoodItemExistings)) {
							relatedFoodItemExistings.stream().forEach(r ->{
								RelatedFoodItem relatedFoodItem = new RelatedFoodItem();
	    						relatedFoodItem.setCoFoodItem(coFoodItemSaved);
	    						relatedFoodItem.setFoodItemCode(r.getFoodItemCode());
								relatedFoodItem.setFoodItemName(r.getFoodItemName());
	    						relatedFoodItem.setType(r.getType());
	    						relatedFoodItem.setSapCode(r.getSapCode());
	    						relatedFoodItem.setNameDisplay(r.getNameDisplay());
	    						relatedFoodItemRepository.save(relatedFoodItem);
	    					});
						}
						
						if(!CollectionUtils.isEmpty(toppingFoodItemExistings)) {
							toppingFoodItemExistings.stream().forEach(r ->{
								ToppingFoodItem toppingFoodItem = new ToppingFoodItem();
	    						toppingFoodItem.setCoFoodItem(coFoodItemSaved);
	    						toppingFoodItem.setFoodItemCode(r.getFoodItemCode());
	    						toppingFoodItem.setFoodItemName(r.getFoodItemName());
	    						toppingFoodItem.setModifierCode(r.getModifierCode());
	    						toppingFoodItem.setModifierName(r.getModifierName());
	    						toppingFoodItem.setSapCode(r.getSapCode());
	    						toppingFoodItemRepository.save(toppingFoodItem);
	    					});
						}
						
						Long newId = coFoodItemSaved.getId();
						// lưu thông tin ảnh
						cloneAttachment(coFoodItemExistingDto.getId(), newId, coFoodItemModuleType, restaurantCode, coFoodItemExisting.getFoodItem().getCode());
						// Đánh dấu bản ghi này đã được update để phục vụ cho việc đồng bộ UPDATE xuống SERVER nhà hàng
						if(isSavedRestaurantDataEdit) {
							saveRestaurantDataEdit(newId.toString(), coFoodItemTypeRestaurantDataEdit, restaurantCode);
						}
						foodItemIdExist.add(foodItemId);
	    			}
	    		});
			}
			
			RestaurantDto restaurantDto = Optional.of(restaurantRepository.findByCode(restaurantCode)).map(restaurantMapper::entityToDto).orElse(null);
			if(restaurantDto != null && configQrOrderExistingDto != null) {
				ConfigQrOrderDto configQrOrderDto = new ConfigQrOrderDto();
				BeanUtils.copyProperties(configQrOrderExistingDto, configQrOrderDto);
				configQrOrderDto.setId(null);
				configQrOrderDto.setIp(restaurantDto.getIp());
				configQrOrderDto.setPort(restaurantDto.getPort());
				configQrOrderDto.setRestaurantCode(String.valueOf(restaurantCode));
				ConfigQrOrder configQrOrder = Optional.ofNullable(configQrOrderDto).map(configQrOrderMapper::dtoToEntity).orElse(null);
				ConfigQrOrderDto savedConfigQrOrderDto = Optional.ofNullable(configQrOrderRepository.save(configQrOrder)).map(configQrOrderMapper::entityToDto).orElse(null);
				// lưu thông tin ảnh
				cloneAttachment(configQrOrderExistingDto.getId(), savedConfigQrOrderDto.getId(), configQrOrderModuleType, restaurantCode, null);
			}
		} catch (Exception e) {
			log.error("ERROR PROCESS FUNCTION: CLONE CO_MENU WITH STATUS OVERRIDE EXCEPTION: {}", e);
		}
	}
	
	/*
	 * Xóa dữ liệu CO của nhà hàng trước khi đồng bộ với trạng thái "UPDATE"
	 */
	private void deleteCoMenuWithStatusUpdate(List<String> deleteCoCategoryCodeInCatalogs, List<String> deleteCoFoodItemCodeInCatalogs, Integer restaurantCode) {
		log.debug("PROCESS FUNCTION: DELETE CO_MENU WITH STATUS UPDATE BY RESTAURANT_CODE: {}, DELETE_CO_CATEGORY_CODE: {}, DELETE_CO_FOOD_ITEM_CODE: {}", restaurantCode, deleteCoCategoryCodeInCatalogs, deleteCoFoodItemCodeInCatalogs);
		try {
			// Xóa thông tin các món ăn có trong CO_MENU
			if(!CollectionUtils.isEmpty(deleteCoFoodItemCodeInCatalogs)) {
				List<CoFoodItem> coFoodItems = coFoodItemRepository.findByResCodeAndAndSoIdIsNotNullAndFoodItemCodeIn(restaurantCode, deleteCoFoodItemCodeInCatalogs);
				if(!CollectionUtils.isEmpty(coFoodItems)) {
					coFoodItems.stream().forEach(coFoodItem ->{
						deleteCoFoodItem(coFoodItem);
						saveRestaurantDataEdit(coFoodItem.getFoodItem().getCode(), TypeRestaurantDataEditEnum.DELETE_CO_FOODITEM_RES.val ,coFoodItem.getRestaurantCode());
					});
				}
			}
			// Xóa thông tin các nhóm MENU trong CO_MENU
			if(!CollectionUtils.isEmpty(deleteCoCategoryCodeInCatalogs)) {
				List<CoCategory> coCategories = coCategoryRepository.findByResCodeAndCoIdIsNotNullAndOrderCategoryCodeIn(restaurantCode, deleteCoCategoryCodeInCatalogs);
				if(!CollectionUtils.isEmpty(coCategories)) {
					coCategories.stream().forEach(coCategory ->{
						deleteCoCategory(coCategory);
						saveRestaurantDataEdit(coCategory.getSoCategory().getOrderCategory().getCode(), TypeRestaurantDataEditEnum.DELETE_CO_CATEGORY_RES.val ,coCategory.getRestaurantCode());
					});
				}
			}
		} catch (Exception e) {
			log.error("ERROR PROCESS FUNCTION: DELETE CO_MENU WITH STATUS UPDATE EXCEPTION: {}", e);
		}
	}
	
	/*
	 * Xóa dữ liệu CO của nhà hàng trước khi đồng bộ với trạng thái "GHI ĐÈ"
	 */
	public void deleteCoMenuWithStatusOverride(Integer restaurantCode) {
		log.debug("PROCESS FUNCTION: DELETE CO_MENU WITH STATUS OVERRIDE BY RESTAURANT_CODE: {}", restaurantCode);
		try {
			List<CoCategory> coCategories = coCategoryRepository.findByRestaurantCode(restaurantCode);
			List<CoFoodItem> coFoodItems = coFoodItemRepository.findByRestaurantCode(restaurantCode);
			ConfigQrOrder configQrOrder = configQrOrderRepository.findByRestaurantCode(String.valueOf(restaurantCode));
			// Xóa thông tin các món ăn có trong CO_MENU
			if(!CollectionUtils.isEmpty(coFoodItems)) {
				coFoodItems.stream().forEach(coFoodItem ->{
					deleteCoFoodItem(coFoodItem);
				});
			}
			// Xóa thông tin các nhóm MENU trong CO_MENU
			if(!CollectionUtils.isEmpty(coCategories)) {
				coCategories.stream().forEach(coCategory ->{
					deleteCoCategory(coCategory);
				});
			}
			// Xóa thông tin ConfigQrOrder
			if(configQrOrder != null) {
				attachmentRepository.deleteByModuleIdAndModuleType(configQrOrder.getId(), ModuleTypeEnum.CONFIG_QR_ORDER.val);
				configQrOrderRepository.delete(configQrOrder);
			}
		} catch (Exception e) {
			log.error("ERROR PROCESS FUNCTION: DELETE CO_MENU WITH STATUS OVERRIDE EXCEPTION: {}", e);
		}
	}
	
	/*
	 * Ghi lại thông tin ITEM đã chỉnh sửa để phục vụ cho việc đồng bộ update thông tin về server nhà hàng (Gửi từ GPOS xuống SERVER nhà hàng)
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
	private void saveCatalogApplyToRestaurant(Integer resCode, Long coMenuId) {
		log.debug("PROCESS FUNCTION: SAVE CATALOG-APPLY-TO-RESTAURANT BY RESTAURANT_CODE AND CO_MENU_ID, RESTAURANT_CODE: {}, CO_MENU_ID: {}", resCode, coMenuId);
		try {
			CatalogApplyToRestaurant applyToRestaurant = catalogApplyToRestaurantRepository.findByRestaurantCode(resCode);
			if(applyToRestaurant == null) {
				applyToRestaurant = new CatalogApplyToRestaurant();
			}
			applyToRestaurant.setRestaurantCode(resCode);
			applyToRestaurant.setCoId(coMenuId);
			catalogApplyToRestaurantRepository.save(applyToRestaurant);
		} catch (Exception e) {
			log.error("ERROR PROCESS FUNCTION: SAVE CATALOG-APPLY-TO-RESTAURANT EXCEPTION: {}", e);
		}
	}

	/*
	 * Lấy danh sách CO_FOOD_ITEM_CODE đã chỉnh sửa hoặc đã bị xóa ở dưới nhà hàng
	 */
	private List<String> getCoFoodItemCodeChangedAndDeleteds(Integer restaurantCode){
		log.debug("PROCESS FUNCTION: GET CO_FOOD_ITEM_CODE CHANGED AND DELETED, RESTAURANT_CODE: {}", restaurantCode);
		List<String> coFoodItemCodes = new ArrayList<>();
		try {
			coFoodItemCodes.addAll(coFoodItemRepository.findFoodItemCode(restaurantCode));
			coFoodItemCodes.addAll(restaurantDataEditRepository.findValues(restaurantCode,TypeRestaurantDataEditEnum.DEL_CO_FOODITEM_OLD_RES.val));
			coFoodItemCodes.addAll(restaurantDataEditRepository.findValues(restaurantCode,TypeRestaurantDataEditEnum.DELETE_CO_FOODITEM_RES.val));
		} catch (Exception e) {
			log.error("ERROR PROCESS FUNCTION: GET CO_FOOD_ITEM_CODE CHANGED AND DELETED EXCEPTION: {}", e);
		}
		return coFoodItemCodes;
	}
	
	/*
	 * Lấy danh sách CO_CATEGORY_CODE đã chỉnh sửa hoặc đã bị xóa ở dưới nhà hàng
	 */
	private List<String> getCoCategoryCodeChangedAndDeleteds(Integer restaurantCode){
		log.debug("PROCESS FUNCTION: GET CO_CATEGORY_CODE CHANGED AND DELETED, RESTAURANT_CODE: {}", restaurantCode);
		List<String> coCategoryCodes = new ArrayList<>();
		try {
			coCategoryCodes.addAll(coCategoryRepository.findOrderCateCodes(restaurantCode));
			coCategoryCodes.addAll(restaurantDataEditRepository.findValues(restaurantCode,TypeRestaurantDataEditEnum.DEL_CO_CATEGORY_OLD_RES.val));
			coCategoryCodes.addAll(restaurantDataEditRepository.findValues(restaurantCode,TypeRestaurantDataEditEnum.DELETE_CO_CATEGORY_RES.val));
		} catch (Exception e) {
			log.error("ERROR PROCESS FUNCTION: GET CO_CATEGORY_CODE CHANGED AND DELETED EXCEPTION: {}", e);
		}
		return coCategoryCodes;
	}
	
	/*
	 * Xóa thông tin CO_CATEGORY
	 */
	private void deleteCoCategory(CoCategory coCategory){
		log.debug("PROCESS FUNCTION: DELETE CO_CATEGORY, CO_CATEGORY: {}", coCategory);
		try {
			digitalMenuRepository.deleteByCoCategory(coCategory);
			coFoodGroupDisplayRepository.deleteByCoCategory(coCategory);
			attachmentRepository.deleteByModuleIdAndModuleType(coCategory.getId(), ModuleTypeEnum.CO_CATEGORY.val);
			coCategoryRepository.delete(coCategory);
		} catch (Exception e) {
			log.error("ERROR PROCESS FUNCTION: DELETE CO_CATEGORY EXCEPTION: {}", e);
		}
	}
	
	/*
	 * Xóa thông tin CO_FOOD_ITEM
	 */
	private void deleteCoFoodItem(CoFoodItem coFoodItem){
		log.debug("PROCESS FUNCTION: DELETE CO_FOOD_ITEM, CO_FOOD_ITEM: {}", coFoodItem);
		try {
			coFoodItemModifierRepository.deleteByCoFoodItem(coFoodItem);
			relatedFoodItemRepository.deleteByCoFoodItem(coFoodItem);
			toppingFoodItemRepository.deleteByCoFoodItem(coFoodItem);
			attachmentRepository.deleteByModuleIdAndModuleType(coFoodItem.getId(), ModuleTypeEnum.CO_FOOD_ITEM.val);
			coFoodItemRepository.delete(coFoodItem);
		} catch (Exception e) {
			log.error("ERROR PROCESS FUNCTION: DELETE CO_FOOD_ITEM EXCEPTION: {}", e);
		}
	}

	/*
	 * Set lại trạng thái của nhà hàng là "Chưa đồng bộ" sau khi thực hiện áp dụng danh mục
	 */
	private void savedStatusAfterSyncCoMenu(Integer restaurantCode) {
		log.debug("PROCESS FUNCTION: SAVED STATUS AFTER SYNC CO_MENU, RESTAURANT_CODE: {}", restaurantCode);
		try {
			SyncStatus syncStatus = syncStatusRepository.findByRestaur_code(restaurantCode);
			if(syncStatus == null) {
				syncStatus = new SyncStatus();
				Restaurant restaurant = restaurantRepository.findByCode(restaurantCode);
				syncStatus.setRestaur(restaurant);
			}
			syncStatus.setBusinessSyncStatus(StatusSyncEnum.NOT_SYNC.val);
			syncStatusRepository.save(syncStatus);
		} catch (Exception e) {
			log.error("ERROR PROCESS FUNCTION: SAVED STATUS AFTER SYNC CO_MENU EXCEPTION: {}", e);
		}
	}
	
	/*
	 * Convert List thành file Json (trạng thái GHI ĐÈ)
	 */
	private RestaurantMenuDataSync convertCoMenuToJsonWithStatusOverride(RestaurantMenuDataSync restaurantMenuDataSync, Integer restaurantCode, String restaurantName, String apiUrlFile, String gatewayImageUrl) {
		log.debug("PROCESS FUNCTION: CONVERT CO_MENU LIST TO JSON WITH STATUS OVERRIDE");
		// các biến cần dùng
		String coCategoryModuleType = ModuleTypeEnum.CO_CATEGORY.val;
		String coFoodItemModuleType = ModuleTypeEnum.CO_FOOD_ITEM.val;
		String imgCategoryFolderUpload = UploadImageEnum.IMG_CATEGORY_FOLDER.val;
		String videoCategoryFolderUpload = UploadImageEnum.VIDEO_CATEGORY_FOLDER.val;
		String imgCategoryPath = UploadImageEnum.IMG_CATEGORY_PATH.val;
		String videoCategoryPath = UploadImageEnum.VIDEO_MENU_PATH.val;
		String imgMenuFolderUpload = UploadImageEnum.IMG_MENU_FOLDER.val;
		String imgMenuPath = UploadImageEnum.IMG_MENU_PATH.val;
		
		try {
			// PHẦN THÔNG TIN LIÊN QUAN ĐẾN CO_CATEGORY
			List<ConfigCOCategory> configCOCategories = new ArrayList<>();
			// Thông tin JSON các CO_CATEOGRY có trạng thái là OFF
			List<JsonDeleteCoCategory> jsonDeleteCoCategories = new ArrayList<>();
			List<FileContent> fileAttachments = new ArrayList<>();
			List<Long> coCategoryIds = new ArrayList<>();
			List<CoCategoryDto> coCategoryEditDtoInRestaurants = coCategoryRepository.findByRestaurantCode(restaurantCode).stream().map(coCategoryMapper::entityToDto).collect(Collectors.toList());
			if(!CollectionUtils.isEmpty(coCategoryEditDtoInRestaurants)) {
				coCategoryEditDtoInRestaurants.stream().forEach(coCategoryExistingDto ->{
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
			List<Long> coFoodItemIds = new ArrayList<>();
			
			List<CoFoodItemDto> coFoodItemEditDtoInRestaurants = coFoodItemRepository.findByRestaurantCode(restaurantCode).stream().map(coFoodItemMapper::entityToDto).collect(Collectors.toList());
			if(!CollectionUtils.isEmpty(coFoodItemEditDtoInRestaurants)) {
				coFoodItemEditDtoInRestaurants.stream().forEach(coFoodItemExistingDto ->{
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
							fileContent.setType(coFoodItemModuleType);
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
			restaurantMenuDataSync.setCoMenuItems(configCOMenuItems);
			restaurantMenuDataSync.setCoModifiers(new ArrayList<>(configCOModifiers));
			restaurantMenuDataSync.setCoSpecials(new ArrayList<>(configCOSpecialTypes));
			restaurantMenuDataSync.setFileContents(fileAttachments);
			restaurantMenuDataSync.setCoCategories(configCOCategories);
			restaurantMenuDataSync.setDeleteCoCategories(jsonDeleteCoCategories);
			restaurantMenuDataSync.setCoCategoryIds(coCategoryIds);
			restaurantMenuDataSync.setCoFoodItemIds(coFoodItemIds);
		} catch (Exception e) {
			log.error("ERROR PROCESS FUNCTION: CONVERT CO_MENU LIST TO JSON WITH STATUS OVERRIDE EXCEPTION: {}", e);
		}
		return restaurantMenuDataSync;
	}

	/*
	 * Convert List thành file Json (trạng thái UPDATE)
	 */
	private RestaurantMenuDataSync convertCoMenuToJsonWithStatusUpdate(RestaurantMenuDataSync restaurantMenuDataSync, Integer restaurantCode, String restaurantName, String apiUrlFile, String gatewayImageUrl) {
		log.debug("PROCESS FUNCTION: CONVERT CO_MENU LIST TO JSON WITH STATUS UPDATE");
		// các biến cần dùng
		String coCategoryModuleType = ModuleTypeEnum.CO_CATEGORY.val;
		String coFoodItemModuleType = ModuleTypeEnum.CO_FOOD_ITEM.val;
		String imgCategoryFolderUpload = UploadImageEnum.IMG_CATEGORY_FOLDER.val;
		String videoCategoryFolderUpload = UploadImageEnum.VIDEO_CATEGORY_FOLDER.val;
		String imgCategoryPath = UploadImageEnum.IMG_CATEGORY_PATH.val;
		String videoCategoryPath = UploadImageEnum.VIDEO_MENU_PATH.val;
		String imgMenuFolderUpload = UploadImageEnum.IMG_MENU_FOLDER.val;
		String imgMenuPath = UploadImageEnum.IMG_MENU_PATH.val;
		
		try {
			// PHẦN THÔNG TIN LIÊN QUAN ĐẾN CO_CATEGORY
			List<ConfigCOCategory> configCOCategories = new ArrayList<>();
			List<FileContent> fileAttachments = new ArrayList<>();
			List<Long> coCategoryIds = new ArrayList<>();
			// Thông tin JSON các CO_CATEOGRY có trạng thái là OFF
			List<JsonDeleteCoCategory> jsonDeleteCoCategories = new ArrayList<>();
			
			// Lấy danh sách các bản ghi CO_CATEGORY đã được chỉnh sửa ở dưới nhà hàng
			List<Long> coCategoryEditIds = restaurantDataEditRepository.findByRestaurantCodeAndType(restaurantCode, TypeRestaurantDataEditEnum.CO_CATEGORY.val).stream().map(p -> Long.parseLong(p.getValue())).collect(Collectors.toList());
			List<CoCategoryDto> coCategoryEditDtoInRestaurants = coCategoryRepository.findByIdIn(coCategoryEditIds).stream().map(coCategoryMapper::entityToDto).collect(Collectors.toList());
			if(!CollectionUtils.isEmpty(coCategoryEditDtoInRestaurants)) {
				coCategoryEditDtoInRestaurants.stream().forEach(coCategoryExistingDto ->{
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
			List<Long> coFoodItemIds = new ArrayList<>();
			
			// Lấy danh sách các bản ghi CO_FOOD_ITEM đã được chỉnh sửa ở dưới nhà hàng
			List<Long> coFoodItemEditIds = restaurantDataEditRepository.findByRestaurantCodeAndType(restaurantCode, TypeRestaurantDataEditEnum.CO_FOODITEM.val).stream().map(p -> Long.parseLong(p.getValue())).collect(Collectors.toList());
			List<CoFoodItemDto> coFoodItemEditDtoInRestaurants = coFoodItemRepository.findByIdIn(coFoodItemEditIds).stream().map(coFoodItemMapper::entityToDto).collect(Collectors.toList());
			if(!CollectionUtils.isEmpty(coFoodItemEditDtoInRestaurants)) {
				coFoodItemEditDtoInRestaurants.stream().forEach(coFoodItemExistingDto ->{
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
							fileContent.setType(coFoodItemModuleType);
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
			
			
			// Lấy danh sách các bản ghi CO_FOODITEM đã bị xóa dưới nhà hàng
			List<RestaurantDataEdit> deleteCoFoodItemDataInRestaurants = new ArrayList<>();
			deleteCoFoodItemDataInRestaurants.addAll(restaurantDataEditRepository.findByRestaurantCodeAndType(restaurantCode, TypeRestaurantDataEditEnum.DELETE_CO_FOODITEM.val));
			deleteCoFoodItemDataInRestaurants.addAll(restaurantDataEditRepository.findByRestaurantCodeAndType(restaurantCode, TypeRestaurantDataEditEnum.DELETE_CO_FOODITEM_RES.val));
			List<JsonDeleteCOMenuItem> jsonDeleteCOMenuItems = new ArrayList<>();
			if(!CollectionUtils.isEmpty(deleteCoFoodItemDataInRestaurants)) {
				deleteCoFoodItemDataInRestaurants.stream().forEach(item ->{
					JsonDeleteCOMenuItem jsonDeleteCOMenuItem = new JsonDeleteCOMenuItem();
					jsonDeleteCOMenuItem.setCode(item.getValue());
					jsonDeleteCOMenuItems.add(jsonDeleteCOMenuItem);
				});
			}
			
			// Lấy danh sách các bản ghi CO_CATEGORY đã bị xóa dưới nhà hàng
			List<RestaurantDataEdit> deleteCoCategoryDataInRestaurants = new ArrayList<>();
			deleteCoFoodItemDataInRestaurants.addAll(restaurantDataEditRepository.findByRestaurantCodeAndType(restaurantCode, TypeRestaurantDataEditEnum.DELETE_CO_CATEGORY.val));
			deleteCoFoodItemDataInRestaurants.addAll(restaurantDataEditRepository.findByRestaurantCodeAndType(restaurantCode, TypeRestaurantDataEditEnum.DELETE_CO_CATEGORY_RES.val));
			if(!CollectionUtils.isEmpty(deleteCoCategoryDataInRestaurants)) {
				deleteCoCategoryDataInRestaurants.stream().forEach(item ->{
					JsonDeleteCoCategory jsonDeleteCoCategory = new JsonDeleteCoCategory();
					jsonDeleteCoCategory.setCode(item.getValue());
					jsonDeleteCoCategories.add(jsonDeleteCoCategory);
				});
			}
			
			// PHẦN THÔNG TIN LIÊN QUAN ĐẾN CONFIG_QR_ORDER
			ConfigQrOrderDto configQrOrderExistingDto = Optional.ofNullable(configQrOrderRepository.findByRestaurantCode(String.valueOf(restaurantCode))).map(configQrOrderMapper::entityToDto).orElse(null);
			ConfigRestaurantInfo configRestaurantInfo = new ConfigRestaurantInfo();
			if(configQrOrderExistingDto != null) {
				BeanUtils.copyProperties(configQrOrderExistingDto, configRestaurantInfo);
				restaurantMenuDataSync.setConfigRestaurantInfo(configRestaurantInfo);
			}
			
			restaurantMenuDataSync.setCoMenuItems(configCOMenuItems);
			restaurantMenuDataSync.setCoModifiers(new ArrayList<>(configCOModifiers));
			restaurantMenuDataSync.setCoSpecials(new ArrayList<>(configCOSpecialTypes));
			restaurantMenuDataSync.setFileContents(fileAttachments);
			restaurantMenuDataSync.setCoCategories(configCOCategories);
			restaurantMenuDataSync.setDeleteCOMenuItems(jsonDeleteCOMenuItems);
			restaurantMenuDataSync.setDeleteCoCategories(jsonDeleteCoCategories);
			restaurantMenuDataSync.setCoCategoryIds(coCategoryIds);
			restaurantMenuDataSync.setCoFoodItemIds(coFoodItemIds);
		} catch (Exception e) {
			log.error("ERROR PROCESS FUNCTION: CONVERT CO_MENU LIST TO JSON WITH STATUS UPDATE EXCEPTION: {}", e);
		}
		return restaurantMenuDataSync;
	}
	
	/*
	 * Convert KALO_GROUP thành file Json
	 */
	private JsonKaloGroup convertKaloGroupToJson(List<KaloGroupDto> kaloGroupDtos) {
		log.debug("PROCESS FUNCTION: CONVERT KALO_GROUP LIST TO JSON");
		JsonKaloGroup jsonKaloGroup = new JsonKaloGroup();
		List<JsonKaloGroupDetail> jsonKaloGroupDetails = new ArrayList<>();
		try {
			if(!CollectionUtils.isEmpty(kaloGroupDtos)) {
				kaloGroupDtos.stream().forEach(kaloGroupDto ->{
					JsonKaloGroupDetail jsonKaloGroupDetail = new JsonKaloGroupDetail();
					BeanUtils.copyProperties(kaloGroupDto, jsonKaloGroupDetail);
					jsonKaloGroupDetails.add(jsonKaloGroupDetail);
				});
			}
			jsonKaloGroup.setJsonKaloGroupDetails(jsonKaloGroupDetails);
		} catch (Exception e) {
			log.error("ERROR PROCESS FUNCTION: CONVERT KALO_GROUP LIST TO JSON EXCEPTION: {}", e);
		}
		return jsonKaloGroup;
	}
	
	/*
	 * Clone dữ liệu liên quan đến thông tin ảnh
	 */
	private void cloneAttachment(Long idExisting, Long newId, String moduleType, Integer restaurantCode, String moduleCode) {
		log.debug("PROCESS FUNCTION: CLONE ATTACHMENT, ID_EXISTING: {}, NEW_ID: {}", idExisting, newId);
		try {
			List<Attachment> attachments = attachmentRepository.findByModuleIdAndModuleType(idExisting, moduleType);
			if(!CollectionUtils.isEmpty(attachments)) {
				attachments.stream().forEach(item ->{
					AttachmentDto attachmentDto = new AttachmentDto();
					BeanUtils.copyProperties(item, attachmentDto);
					attachmentDto.setId(null);
					attachmentDto.setModuleId(newId);
					attachmentDto.setModuleCode(moduleCode);
					attachmentDto.setRestaurantCode(restaurantCode);
					attachmentDto.setSyncStatus(SyncStatusEnum.WAITING.val);
					Attachment attachment = Optional.ofNullable(attachmentDto).map(attachmentMapper::dtoToEntity).orElse(null);
					attachmentRepository.save(attachment);
				});
			}
		} catch (Exception e) {
			log.error("ERROR PROCESS FUNCTION: CLONE ATTACHMENT EXCEPTION: {}", e);
		}
	}
}
