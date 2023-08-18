package com.gg.gpos.menu.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.gg.gpos.common.constant.FoodGroupLevelEnum;
import com.gg.gpos.common.constant.MapKeyEnum;
import com.gg.gpos.common.constant.MenuTypeEnum;
import com.gg.gpos.common.constant.ModuleTypeEnum;
import com.gg.gpos.common.constant.StatusEnum;
import com.gg.gpos.common.constant.StatusSyncEnum;
import com.gg.gpos.common.constant.SymbolEnum;
import com.gg.gpos.common.constant.SyncStatusEnum;
import com.gg.gpos.common.constant.TypeRestaurantDataEditEnum;
import com.gg.gpos.common.constant.UploadImageEnum;
import com.gg.gpos.common.json.ConfigMenuEngineering;
import com.gg.gpos.common.json.FileContent;
import com.gg.gpos.common.json.JsonChildDish;
import com.gg.gpos.common.json.JsonDefaultDish;
import com.gg.gpos.common.json.JsonDishItem;
import com.gg.gpos.common.json.JsonMenuGroupDetail;
import com.gg.gpos.common.json.JsonMenuItem;
import com.gg.gpos.common.json.JsonOrderCategory;
import com.gg.gpos.common.json.RestaurantMenuDataSync;
import com.gg.gpos.menu.dto.FoodGroupDto;
import com.gg.gpos.menu.dto.FoodItemDto;
import com.gg.gpos.menu.dto.OrderCategoryDto;
import com.gg.gpos.menu.dto.SoCategoryDto;
import com.gg.gpos.menu.dto.SoCategoryFoodGroupDto;
import com.gg.gpos.menu.dto.SoDto;
import com.gg.gpos.menu.entity.CatalogApplyToRestaurant;
import com.gg.gpos.menu.entity.CoCategory;
import com.gg.gpos.menu.entity.CoFoodItem;
import com.gg.gpos.menu.entity.ConfigQrOrder;
import com.gg.gpos.menu.entity.FoodGroup;
import com.gg.gpos.menu.entity.FoodGroupItem;
import com.gg.gpos.menu.entity.FoodItem;
import com.gg.gpos.menu.entity.RestaurantDataEdit;
import com.gg.gpos.menu.entity.SoCategory;
import com.gg.gpos.menu.entity.SoCategoryFoodGroup;
import com.gg.gpos.menu.mapper.FoodGroupMapper;
import com.gg.gpos.menu.mapper.FoodItemMapper;
import com.gg.gpos.menu.mapper.OrderCategoryMapper;
import com.gg.gpos.menu.mapper.SoCategoryFoodGroupMapper;
import com.gg.gpos.menu.mapper.SoCategoryMapper;
import com.gg.gpos.menu.repository.CatalogApplyToRestaurantRepository;
import com.gg.gpos.menu.repository.CatalogDataEditRepository;
import com.gg.gpos.menu.repository.CoCategoryRepository;
import com.gg.gpos.menu.repository.CoFoodGroupDisplayRepository;
import com.gg.gpos.menu.repository.CoFoodItemModifierRepository;
import com.gg.gpos.menu.repository.CoFoodItemRepository;
import com.gg.gpos.menu.repository.ConfigQrOrderRepository;
import com.gg.gpos.menu.repository.FoodGroupItemRepository;
import com.gg.gpos.menu.repository.FoodGroupRepository;
import com.gg.gpos.menu.repository.FoodItemRepository;
import com.gg.gpos.menu.repository.RelatedFoodItemRepository;
import com.gg.gpos.menu.repository.RestaurantDataEditRepository;
import com.gg.gpos.menu.repository.SoCategoryFoodGroupRepository;
import com.gg.gpos.menu.repository.SoCategoryRepository;
import com.gg.gpos.menu.repository.ToppingFoodItemRepository;
import com.gg.gpos.reference.dto.AttachmentDto;
import com.gg.gpos.reference.entity.Attachment;
import com.gg.gpos.reference.mapper.AttachmentMapper;
import com.gg.gpos.reference.repository.AttachmentRepository;
import com.gg.gpos.res.entity.Restaurant;
import com.gg.gpos.res.entity.SyncStatus;
import com.gg.gpos.res.repository.RestaurantRepository;
import com.gg.gpos.res.repository.SyncStatusRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class SyncSoMenuManager {
	@Autowired
	private SoCategoryRepository soCategoryRepository;
	@Autowired
	private SoCategoryFoodGroupRepository soCategoryFoodGroupRepository;
	@Autowired
	private FoodGroupRepository foodGroupRepository;
	@Autowired
	private FoodGroupItemRepository foodGroupItemRepository;
	@Autowired
	private CoFoodItemRepository coFoodItemRepository;
	@Autowired
	private RelatedFoodItemRepository relatedFoodItemRepository;
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
	private CatalogDataEditRepository catalogDataEditRepository;
	@Autowired
	private AttachmentRepository attachmentRepository;
	@Autowired
	private CoFoodItemModifierRepository coFoodItemModifierRepository;
	@Autowired
	private AttachmentMapper attachmentMapper;
	@Autowired
	private FoodGroupMapper foodGroupMapper;
	@Autowired
	private SoCategoryFoodGroupMapper soCategoryFoodGroupMapper;
	@Autowired
	private SoCategoryMapper soCategoryMapper;
	@Autowired
	private SyncStatusRepository syncStatusRepository;
	@Autowired
	private RestaurantRepository restaurantRepository;
	@Autowired
	private OrderCategoryMapper orderCategoryMapper;
	@Autowired
	private FoodItemRepository foodItemRepository;
	@Autowired
	private FoodItemMapper foodItemMapper;
	@Autowired
	private ConfigQrOrderRepository configQrOrderRepository;
	
	/*
	 * Đồng bộ dữ liệu từ DANH MỤC xuống SERVER NHÀ HÀNG
	 */
	public RestaurantMenuDataSync syncDataFromCatalogToServerRestaurant(Long soMenuId, Integer restaurantCode, List<SoCategoryDto> soCategoryDtoInCatalogs, List<SoCategoryDto> soCategoryEditDtoInCatalogs, List<String> deleteSoCategoryCodeInCatalogs, List<Integer> restaurantCodeApplies, Boolean isOverride, String username, String apiUrlFile, String restaurantName) {
		log.debug("PROCESS: SYNC DATA FROM CATALOG TO SERVER RESTAURANTS, SO_MENU_ID: {}, RESTAURANT_CODE: {}, IS_OVERRIDE: {}, RESTAURANT_CODE_APPLIES: {}", soMenuId, restaurantCode, isOverride, restaurantCodeApplies);
		RestaurantMenuDataSync restaurantMenuDataSync = new RestaurantMenuDataSync();
		try {
			List<SoCategory> soCategoryInCatalogs = soCategoryDtoInCatalogs.stream().map(soCategoryMapper::dtoToEntity).collect(Collectors.toList());
			List<SoCategory> soCategoryEditInCatalogs = soCategoryEditDtoInCatalogs.stream().map(soCategoryMapper::dtoToEntity).collect(Collectors.toList());
			// Trường hợp đồng bộ ghi đè
			if(isOverride) {
				// Clone tất cả SO_CATEGORY có trong danh mục SO xuống nhà hàng || Không lưu thông tin SO_CATEGORY_ID đánh dấu ITEM đó đã được chỉnh sửa
				cloneSoCategoryWithStatusOverride(soCategoryInCatalogs, restaurantCode, false, username);
			} else {
				// Trường hợp đồng bộ update thì sẽ phân ra 2 TRƯỜNG HỢP
				
				// TRƯỜNG HỢP 1: Đã có nhà hàng áp dụng danh mục này
				if(!CollectionUtils.isEmpty(restaurantCodeApplies)) {
		    		// Kiểm tra xem nếu code nhà hàng được chọn == code nhà hàng đang áp dụng thì clone các bản ghi được dánh dấu chỉnh sửa ở danh mục SO_MENU
					if(restaurantCodeApplies.contains(restaurantCode)) {
						cloneSoCategoryWithStatusUpdate(soCategoryEditInCatalogs, deleteSoCategoryCodeInCatalogs, restaurantCode, username);
					} else {
						// Trường hợp k trùng SO_ID thì áp dụng như đồng bộ GHI ĐÈ nhưng có lưu vào bảng đánh dấu ITEM này đã được chỉnh sửa dưới nhà hàng
						cloneSoCategoryWithStatusOverride(soCategoryInCatalogs, restaurantCode, true, username);
					}
				} else {
					// TRƯỜNG HỢP 2: Chưa có nhà hàng áp dụng danh mục này thì áp dụng như đồng bộ GHI ĐÈ nhưng có lưu vào bảng đánh dấu các ITEM này đã được chỉnh sửa dưới nhà hàng
					cloneSoCategoryWithStatusOverride(soCategoryInCatalogs, restaurantCode, true, username);
				}
			}
			// Lưu thông tin xem danh mục SO_MENU này đang áp dụng cho những nhà hàng nào
			saveCatalogApplyToRestaurant(restaurantCode, soMenuId, isOverride);
			// Set lại trạng thái của nhà hàng là "Chưa đồng bộ"
			savedStatusAfterSyncCoMenu(restaurantCode);
			
			// Convert thông tin menu về JSON để gửi xuống nhà hàng
			restaurantMenuDataSync = convertSoMenuToJsonWithStatusOverride(restaurantMenuDataSync, restaurantCode, restaurantName, apiUrlFile, isOverride);
			restaurantMenuDataSync.setResCode(String.valueOf(restaurantCode));
		} catch (Exception e) {
			log.debug("ERROR PROCESS: SYNC DATA FROM CATALOG TO SERVER RESTAURANTS EXCEPTION: {}", e);
		}
		return restaurantMenuDataSync;
	}
	
	
	
	/*
	 * COPY dữ liệu từ nhà hàng này sang nhà hàng khác
	 */
	public void copyFromRestaurantToRestaurants(SoCategoryDto soCategoryDto, String username) {
		log.debug("PROCESS: COPY SO_MENU FROM RESTAURANT TO RESTAURANTS");
		try {
			List<Integer> resCodes = soCategoryDto.getResCodes();
			Integer fromRestaurantCode = soCategoryDto.getRestaurantCode();
	    	Boolean isOverride = soCategoryDto.isOverride();
	    	log.debug("==========> LOG SYSTEM_PARAM: FROM_RESTAURANT_CODE: {}, IS_OVERRIDE: {}, RESTAURANT_CODE_APPLIES: {}", fromRestaurantCode, isOverride, resCodes);
			
	    	// Lấy thông tin xem nhà hàng này đang áp dụng dnah mục CO_MENU nào
	    	CatalogApplyToRestaurant catalogApplyToRestaurant = catalogApplyToRestaurantRepository.findByRestaurantCode(fromRestaurantCode);
	    	if(catalogApplyToRestaurant != null && catalogApplyToRestaurant.getCoId() != null && !CollectionUtils.isEmpty(resCodes)) {
	    		Long soId = catalogApplyToRestaurant.getSoId();
	    		// Lấy danh sách các SO_CATEGORY có ở dưới NHÀ HÀNG cấu hình
				List<SoCategory> soCategoryInRestaurants = soCategoryRepository.findByRestaurantCode(fromRestaurantCode);
	    		
	    		// trường hợp đồng bộ ghi đè
				if(isOverride) {
					resCodes.stream().forEach(toRestaurantCode ->{
						cloneSoCategoryWithStatusOverride(soCategoryInRestaurants, toRestaurantCode, false, username);
		    			// Lưu thông tin xem danh mục SO_MENU này đang áp dụng cho những nhà hàng nào
		    			saveCatalogApplyToRestaurant(toRestaurantCode, soId, isOverride);
		    			// Set lại trạng thái của nhà hàng là "Chưa đồng bộ"
		    			savedStatusAfterSyncCoMenu(toRestaurantCode);
					});
				} else {
					// Trường hợp đồng bộ update thì sẽ phân ra 2 TRƯỜNG HỢP
					
					// Lấy danh sách SO_CATEGORY_ID đã được chỉnh sửa ở NHÀ HÀNG
					List<Long> soCategoryIdEditIds = restaurantDataEditRepository.findByRestaurantCodeAndType(fromRestaurantCode, TypeRestaurantDataEditEnum.SO_CATEGORY.val).stream().map(p -> Long.parseLong(p.getValue())).collect(Collectors.toList());
					// Lấy thông tin các bản ghi SO_CATEGORY_ID đã được chỉnh sửa ở NHÀ HÀNG
					List<SoCategory> soCategoryEditInRestaurants = soCategoryRepository.findByIdIn(soCategoryIdEditIds);
					
					// Lấy danh sách nhà hàng đang được áp dụng danh mục CO_MENU này
					List<Integer> restaurantCodeApplies = catalogApplyToRestaurantRepository.findBySoId(soId).stream().map(item -> item.getRestaurantCode()).collect(Collectors.toList());		
					// TRƯỜNG HỢP 1: Đã có nhà hàng áp dụng danh mục này
					if(!CollectionUtils.isEmpty(restaurantCodeApplies)) { 
						resCodes.stream().forEach(toRestaurantCode ->{
							// Kiểm tra xem nếu code nhà hàng được chọn == code nhà hàng đang áp dụng thì clone các bản ghi được dánh dấu chỉnh sửa ở danh mục CO_MENU
							if(restaurantCodeApplies.contains(toRestaurantCode)) {
								cloneSoCategoryWithStatusUpdate(soCategoryEditInRestaurants, new ArrayList<>(), toRestaurantCode, username);
							} else {
								// Trường hợp k trùng mã code thì áp dụng như đồng bộ GHI ĐÈ nhưng có lưu vào bảng đánh dấu ITEM này đã được chỉnh sửa dưới nhà hàng
								cloneSoCategoryWithStatusOverride(soCategoryInRestaurants, toRestaurantCode, true, username);
							}
							// Lưu thông tin xem danh mục SO_MENU này đang áp dụng cho những nhà hàng nào
							saveCatalogApplyToRestaurant(toRestaurantCode, soId, isOverride);
							// Set lại trạng thái của nhà hàng là "Chưa đồng bộ"
							savedStatusAfterSyncCoMenu(toRestaurantCode);
						});
					} else {
						// TRƯỜNG HỢP 2: Chưa có nhà hàng áp dụng danh mục này thì áp dụng như đồng bộ GHI ĐÈ nhưng có lưu vào bảng đánh dấu các ITEM này đã được chỉnh sửa dưới nhà hàng
						resCodes.stream().forEach(toRestaurantCode ->{
							cloneSoCategoryWithStatusOverride(soCategoryInRestaurants, toRestaurantCode, true, username);
			    			// Lưu thông tin xem danh mục SO_MENU này đang áp dụng cho những nhà hàng nào
			    			saveCatalogApplyToRestaurant(toRestaurantCode, soId, isOverride);
			    			// Set lại trạng thái của nhà hàng là "Chưa đồng bộ"
			    			savedStatusAfterSyncCoMenu(toRestaurantCode);
						});
					}
				}
	    	}
		} catch (Exception e) {
			log.error("ERROR PROCESS: COPY SO_MENU FROM RESTAURANT TO RESTAURANTS EXCEPTION: {}", e);
		}
	}
	
	/*
	 * Áp dụng danh mục SO_MENU từ nhà hàng
	 */
	public void applyCatalogFromRestaurant(SoCategoryDto soCategoryDto, String username) {
		log.debug("PROCESS: APPLY SO_MENU FROM RESTAURANTS");
		try {
			Integer restaurantCode = soCategoryDto.getRestaurantCode();
			Long soId = soCategoryDto.getSoId();
			Boolean isOverride = soCategoryDto.isOverride();
	    	log.debug("==========> LOG SYSTEM_PARAM: SO_MENU_ID: {}, IS_OVERRIDE: {}, RESTAURANT_CODE: {}", soId, isOverride, restaurantCode);
	    	// Lấy thông tin về GROUP_PARAM
	        
			// Lấy danh sách các SO_CATEGORY có trong danh mục SO_MENU
			List<SoCategory> soCategoryInCatalogs = soCategoryRepository.findBySoId(soId);
			
			// Trường hợp đồng bộ ghi đè
			if(isOverride) {
				// Clone tất cả SO_CATEGORY có trong danh mục SO xuống nhà hàng || Không lưu thông tin SO_CATEGORY_ID đánh dấu ITEM đó đã được chỉnh sửa
				cloneSoCategoryWithStatusOverride(soCategoryInCatalogs, restaurantCode, false, username);
			} else {
				// Trường hợp đồng bộ update thì sẽ phân ra 2 TRƯỜNG HỢP
				
				// Lấy danh sách SO_CATEGORY_ID đã được chỉnh sửa ở danh mục SO_MENU
				List<Long> soCategoryEditIds = catalogDataEditRepository.findByTypeAndCatalogId(TypeRestaurantDataEditEnum.SO_CATEGORY.val,soId).stream().map(p -> Long.parseLong(p.getValue())).collect(Collectors.toList());
				// Lấy thông tin các bản ghi SO_CATEGORY đã được chỉnh sửa ở danh mục SO_MENU
				List<SoCategory> soCategoryEditInCatalogs = soCategoryRepository.findByIdIn(soCategoryEditIds);
				// Lấy danh sách SO_CATEGORY_CODE đã bị xóa ở danh mục SO_MENU để xóa các bản ghi có CODE tương ứng ở dưới nhà hàng
				List<String> deleteSoCategoryCodeInCatalogs = catalogDataEditRepository.findByTypeAndCatalogId(TypeRestaurantDataEditEnum.DELETE_SO_CATEGORY.val, soId).stream().map(c -> c.getValue()).collect(Collectors.toList());
				
				
				// Lấy danh sách nhà hàng đang được áp dụng danh mục SO_MENU này
		    	CatalogApplyToRestaurant catalogApplyToRestaurant = catalogApplyToRestaurantRepository.findByRestaurantCode(restaurantCode);
				// TRƯỜNG HỢP 1: Đã có nhà hàng áp dụng danh mục này
		    	if(catalogApplyToRestaurant != null && catalogApplyToRestaurant.getSoId() != null) {
		    		// Kiểm tra xem nếu SO_ID của nhà hàng == SO_ID của danh mục thì clone các bản ghi được dánh dấu chỉnh sửa ở danh mục SO_MENU
		    		if(catalogApplyToRestaurant.getSoId().equals(soId)) {
						cloneSoCategoryWithStatusUpdate(soCategoryEditInCatalogs, deleteSoCategoryCodeInCatalogs, restaurantCode, username);
					} else {
						// Trường hợp k trùng SO_ID thì áp dụng như đồng bộ GHI ĐÈ nhưng có lưu vào bảng đánh dấu ITEM này đã được chỉnh sửa dưới nhà hàng
						cloneSoCategoryWithStatusOverride(soCategoryInCatalogs, restaurantCode, true, username);
					}
				} else {
					// TRƯỜNG HỢP 2: Chưa có nhà hàng áp dụng danh mục này thì áp dụng như đồng bộ GHI ĐÈ nhưng có lưu vào bảng đánh dấu các ITEM này đã được chỉnh sửa dưới nhà hàng
					cloneSoCategoryWithStatusOverride(soCategoryInCatalogs, restaurantCode, true, username);
				}
			}
			// Lưu thông tin xem danh mục SO_MENU này đang áp dụng cho những nhà hàng nào
			saveCatalogApplyToRestaurant(restaurantCode, soId, isOverride);
			// Set lại trạng thái của nhà hàng là "Chưa đồng bộ"
			savedStatusAfterSyncCoMenu(restaurantCode);
		} catch (Exception e) {
			log.debug("ERROR APPLY GROUP_PARAM FROM RESTAURANTS EXCEPTION: {}", e);
		}
	}
	
	/*
	 * Áp dụng danh mục SO_MENU xuống nhà hàng
	 */
	public void syncSoMenuFromCatalogToRestaurants(SoDto soDto, String username) {
		log.debug("PROCESS: SYNC SO_MENU FROM CATALOG TO RESTAURANTS");
		try {
			// Danh sách các mã nhà hàng được chọn để đồng bộ dữ liệu
			List<String> resCodes = StringUtils.commaDelimitedListToSet(soDto.getSelectedRestaurantCodes()).stream().filter(item -> !item.contains(SymbolEnum.UNDERSCORE.val)).collect(Collectors.toList());
			Long soId = soDto.getId();
			Boolean isOverride = soDto.getOverride();
			log.debug("==========> LOG SYSTEM_PARAM: SO_ID: {}, IS_OVERRIDE: {}, RESTAURANT_CODE_APPLIES: {}", soId, isOverride, resCodes);
			
			if (soDto != null && !StringUtils.isEmpty(resCodes)) {
				// Lấy danh sách các SO_CATEGORY có trong danh mục SO_MENU
				List<SoCategory> soCategoryInCatalogs = soCategoryRepository.findBySoId(soId);
				// Trường hợp đồng bộ ghi đè
				if(isOverride) {
					resCodes.stream().forEach(item ->{
						Integer restaurantCode = Integer.parseInt(item);
						// Clone tất cả SO_CATEGORY có trong danh mục SO xuống nhà hàng || Không lưu thông tin SO_CATEGORY_ID đánh dấu ITEM đó đã được chỉnh sửa
						cloneSoCategoryWithStatusOverride(soCategoryInCatalogs, restaurantCode, false, username);
						
						// Lưu thông tin xem danh mục SO_MENU này đang áp dụng cho những nhà hàng nào
						saveCatalogApplyToRestaurant(restaurantCode, soId, isOverride);
						
						// Set lại trạng thái của nhà hàng là "Chưa đồng bộ"
		    			savedStatusAfterSyncCoMenu(restaurantCode);
					});
				} else {
					// Trường hợp đồng bộ update thì sẽ phân ra 2 TRƯỜNG HỢP
					
					// Lấy danh sách SO_CATEGORY_ID đã được chỉnh sửa ở danh mục SO_MENU
					List<Long> soCategoryEditIds = catalogDataEditRepository.findByTypeAndCatalogId(TypeRestaurantDataEditEnum.SO_CATEGORY.val,soId).stream().map(p -> Long.parseLong(p.getValue())).collect(Collectors.toList());
					// Lấy thông tin các bản ghi SO_CATEGORY đã được chỉnh sửa ở danh mục SO_MENU
					List<SoCategory> soCategoryEditInCatalogs = soCategoryRepository.findByIdIn(soCategoryEditIds);
					// Lấy danh sách SO_CATEGORY_CODE đã bị xóa ở danh mục SO_MENU để xóa các bản ghi có CODE tương ứng ở dưới nhà hàng
					List<String> deleteSoCategoryCodeInCatalogs = catalogDataEditRepository.findByTypeAndCatalogId(TypeRestaurantDataEditEnum.DELETE_SO_CATEGORY.val, soId).stream().map(c -> c.getValue()).collect(Collectors.toList());
					
					
					// Lấy danh sách nhà hàng đang được áp dụng danh mục SO_MENU này
					List<Integer> restaurantCodeApplies = catalogApplyToRestaurantRepository.findBySoId(soId).stream().map(item -> item.getRestaurantCode()).collect(Collectors.toList());		
					// TRƯỜNG HỢP 1: Đã có nhà hàng áp dụng danh mục này
					if(!CollectionUtils.isEmpty(restaurantCodeApplies)) { 
						resCodes.stream().forEach(item ->{
							Integer restaurantCode = Integer.parseInt(item); 
							// Kiểm tra xem nếu code nhà hàng được chọn == code nhà hàng đang áp dụng thì clone các bản ghi được dánh dấu chỉnh sửa ở danh mục SO_MENU
							if(restaurantCodeApplies.contains(restaurantCode)) {
								cloneSoCategoryWithStatusUpdate(soCategoryEditInCatalogs, deleteSoCategoryCodeInCatalogs, restaurantCode, username);
							} else {
								// Trường hợp k trùng mã code thì áp dụng như đồng bộ GHI ĐÈ nhưng có lưu vào bảng đánh dấu ITEM này đã được chỉnh sửa dưới nhà hàng
								cloneSoCategoryWithStatusOverride(soCategoryInCatalogs, restaurantCode, true, username);
							}
							// Lưu thông tin xem danh mục SO_MENU này đang áp dụng cho những nhà hàng nào
							saveCatalogApplyToRestaurant(restaurantCode, soId, isOverride);
			    			// Set lại trạng thái của nhà hàng là "Chưa đồng bộ"
			    			savedStatusAfterSyncCoMenu(restaurantCode);
						});
					} else {
						// TRƯỜNG HỢP 2: Chưa có nhà hàng áp dụng danh mục này thì áp dụng như đồng bộ GHI ĐÈ nhưng có lưu vào bảng đánh dấu các ITEM này đã được chỉnh sửa dưới nhà hàng
						resCodes.stream().forEach(item ->{
							Integer restaurantCode = Integer.parseInt(item);
							cloneSoCategoryWithStatusOverride(soCategoryInCatalogs, restaurantCode, true, username);
							// Lưu thông tin xem danh mục SO_MENU này đang áp dụng cho những nhà hàng nào
							saveCatalogApplyToRestaurant(restaurantCode, soId, isOverride);
			    			// Set lại trạng thái của nhà hàng là "Chưa đồng bộ"
			    			savedStatusAfterSyncCoMenu(restaurantCode);
						});
					}
				}
			}
			// Xóa các dữ liệu lưu trữ thông tin các SO_CATEGORY đã chỉnh sửa ở trong danh mục SO_MENU (Các dữ liệu này chỉ phục vụ cho việc đồng bộ update)
			deleteCatalogDataEdit(soId);
		} catch (Exception e) {
			log.error("ERROR SYNC SO_MENU FROM CATALOG TO RESTAURANTS EXCEPTION: {}", e);
		}
	}
	
	/*
	 * Clone danh sách các SO_CATEGORY có trong SO_MENU xuống nhà hàng với trạng thái "UPDATE"
	 */
	private void cloneSoCategoryWithStatusUpdate(List<SoCategory> soCategories, List<String> deleteSoCategoryCodeInCatalogs, Integer restaurantCode, String username) {
		log.debug("PROCESS FUNCTION: CLONE SO_CATEGORY WITH STATUS UPDATE BY RESTAURANT_CODE: {}", restaurantCode);
		try {
			// Xóa các dữ liệu SO và CO (Phải xóa cả CO do danh mục CO sẽ thuộc danh mục SO) cũ ở dưới nhà hàng trước khi đồng bộ
			deleteSoMenuAndCoMenuWithStatusUpdate(deleteSoCategoryCodeInCatalogs, restaurantCode);
			// Clone dữ liệu
			if(!CollectionUtils.isEmpty(soCategories)) {
				// Lấy danh sách SO_CATEGORY_CODE đã chỉnh sửa hoặc đã bị xóa ở dưới nhà hàng
				List<String> coCategoryCodeChangedAndDeleteds = getSoCategoryCodeChangedAndDeleteds(restaurantCode);
				// tạo mới dữ liệu
				soCategories.stream().forEach(soCategoryExisting -> {
					String orderCategoryCode = soCategoryExisting.getOrderCategory().getCode();
					if(!coCategoryCodeChangedAndDeleteds.contains(orderCategoryCode)) {
						// SoCategoryDto soCategoryExistingDto = Optional.ofNullable(soCategoryExisting).map(soCategoryMapper::entityToDto).orElse(null);
						SoCategory soCategoryExistingInRestaurant = soCategoryRepository.findByOrderCategoryCodeAndResCode(orderCategoryCode, restaurantCode);
	    				CoCategory coCategoryExistingInRestaurant = coCategoryRepository.findByorderCategoryCodeAndResCode(orderCategoryCode,restaurantCode);
					
	    				if(soCategoryExistingInRestaurant == null || soCategoryExistingInRestaurant.getSo() != null) {
							if(soCategoryExistingInRestaurant == null) {
								soCategoryExistingInRestaurant = new SoCategory();
							}
							soCategoryExistingInRestaurant.setAdultBufferTicket(soCategoryExisting.getAdultBufferTicket());
							soCategoryExistingInRestaurant.setKidBufferTicket(soCategoryExisting.getKidBufferTicket());
							soCategoryExistingInRestaurant.setOrderCategory(soCategoryExisting.getOrderCategory());
							soCategoryExistingInRestaurant.setOrderCategoryParentId(soCategoryExisting.getOrderCategoryParentId());
							soCategoryExistingInRestaurant.setRestaurantCode(restaurantCode);
							soCategoryExistingInRestaurant.setType(soCategoryExisting.getType());
							soCategoryExistingInRestaurant.setSo(soCategoryExisting.getSo());
							soCategoryExistingInRestaurant.setStatus(soCategoryExisting.isStatus());
							// chỉnh sửa lại thông tin MENU_TYPE trong CO_CATEGORY
							if(coCategoryExistingInRestaurant != null) {
								coCategoryExistingInRestaurant.setType(soCategoryExisting.getType());
								coCategoryExistingInRestaurant.setStatus(soCategoryExisting.isStatus());
		        				CoCategory savedCoCategory = coCategoryRepository.save(coCategoryExistingInRestaurant);
		        				saveRestaurantDataEdit(savedCoCategory.getId().toString(), TypeRestaurantDataEditEnum.CO_CATEGORY.val, restaurantCode);
		        			}
						}
	        			SoCategory savedSoCategory = soCategoryRepository.save(soCategoryExistingInRestaurant);
	        			// Đánh dấu bản ghi này đã được update để phục vụ cho việc đồng bộ UPDATE xuống SERVER nhà hàng
	        			restaurantDataEditRepository.deleteByValueAndRestaurantCodeAndType(orderCategoryCode, restaurantCode, TypeRestaurantDataEditEnum.DELETE_SO_CATEGORY.val);
	        			restaurantDataEditRepository.deleteByValueAndRestaurantCodeAndType(orderCategoryCode, restaurantCode, TypeRestaurantDataEditEnum.DELETE_CO_CATEGORY.val);
	        			saveRestaurantDataEdit(savedSoCategory.getId().toString(), TypeRestaurantDataEditEnum.SO_CATEGORY.val, restaurantCode);
	        			
	        			// Lấy danh sách các FOOD_GROUP đã được chỉnh sửa để clone
	        			List<SoCategoryFoodGroup> soCategoryFoodGroupExistings = soCategoryExisting.getSoCategoryFoodGroups();
						List<SoCategoryFoodGroup> soCategoryFoodGroups = new ArrayList<>();
	        			List<SoCategoryFoodGroup> soCategoryFoodGroupHasParents = new ArrayList<>();
	        			List<SoCategoryFoodGroup> soCategoryFoodGroupDeletes = new ArrayList<>();
	        			List<SoCategoryFoodGroup> soCategoryFoodGroupDeleteHasParents = new ArrayList<>();
	        			List<String> foodGroupCodeDisplayDels = new ArrayList<>();
	        			
	        			if (!CollectionUtils.isEmpty(soCategoryFoodGroupExistings)) {
	        				soCategoryFoodGroupExistings.stream().forEach(soCategoryFoodGroupExisting ->{
	        					if(soCategoryFoodGroupExisting.getFoodGroup().getChanged() != null && soCategoryFoodGroupExisting.getFoodGroup().getChanged().equals(StatusEnum.ACTIVE.status)){
	        						if(soCategoryFoodGroupExisting.getFoodGroup().getParent() != null) {
	        							soCategoryFoodGroupHasParents.add(soCategoryFoodGroupExisting);
		        					} else {
		        						soCategoryFoodGroups.add(soCategoryFoodGroupExisting);
		        					}
	        					} else if(soCategoryFoodGroupExisting.getFoodGroup().getChanged() != null && soCategoryFoodGroupExisting.getFoodGroup().getChanged().equals(StatusEnum.DELETE.status)){
	        						if(soCategoryFoodGroupExisting.getFoodGroup().getParent() != null) {
	        							soCategoryFoodGroupDeleteHasParents.add(soCategoryFoodGroupExisting);
		        					} else {
		        						soCategoryFoodGroupDeletes.add(soCategoryFoodGroupExisting);
		        					}
	        					}
	        				});
	        				
	        				// Xóa các FOOD_GROUP đã được dánh dấu xóa theo FOOD_GROUP_CODE và RESTAURANT_CODE
	        				if (!CollectionUtils.isEmpty(soCategoryFoodGroupDeleteHasParents)) {
	        					foodGroupCodeDisplayDels.addAll(deleteFoodGroupInRestaurant(soCategoryFoodGroupDeleteHasParents, restaurantCode, savedSoCategory));
	        				}
	        				if (!CollectionUtils.isEmpty(soCategoryFoodGroupDeletes)) {
	        					foodGroupCodeDisplayDels.addAll(deleteFoodGroupInRestaurant(soCategoryFoodGroupDeletes, restaurantCode, savedSoCategory));
	        				}
	        				
	        				// Xóa bỏ các nhóm hiển thị CO_FOOD_GROUP_DISPLAY đã được cấu hình ở CO_CATEGORY dưới nhà hàng
	        				if(coCategoryExistingInRestaurant != null && !CollectionUtils.isEmpty(foodGroupCodeDisplayDels)){
	        					coFoodGroupDisplayRepository.deleteByCoCategoryAndFoodGroupCodeIn(coCategoryExistingInRestaurant, foodGroupCodeDisplayDels);
	        					saveRestaurantDataEdit(coCategoryExistingInRestaurant.getId().toString(), TypeRestaurantDataEditEnum.CO_CATEGORY.val, restaurantCode);
	        				}
	        				
	        				// Lưu thông tin nhóm món ăn không có nhóm cha và các món ăn có trong nhóm
	        				savedFoodGroupWithStatusUpdate(restaurantCode, savedSoCategory, soCategoryFoodGroups, username);
	        				// Lưu thông tin nhóm món ăn có nhóm cha và các món ăn có trong nhóm
	        				savedFoodGroupWithStatusUpdate(restaurantCode, savedSoCategory, soCategoryFoodGroupHasParents, username);
	        			}
					
					}
				});
			}
		} catch (Exception e) {
			log.error("ERROR PROCESS FUNCTION: CLONE SO_CATEGORY WITH STATUS UPDATE EXCEPTION: {}", e);
		}
	}
	
	/*
	 * Clone danh sách các SO_CATEGORY có trong SO_MENU xuống nhà hàng với trạng thái "GHI ĐÈ"
	 * Trường hợp chọn áp dụng UPDATE nhưng phía nhà hàng chưa áp dụng danh mục thì cũng được tính là GHI ĐÈ nhưng sẽ CÓ LƯU thông tin đánh dấu bản ghi này đã được update để phục vụ cho việc đồng bộ UPDATE xuống SERVER nhà hàng
	 */
	private void cloneSoCategoryWithStatusOverride(List<SoCategory> soCategories, Integer restaurantCode, Boolean isSavedRestaurantDataEdit, String username) {
		log.debug("PROCESS FUNCTION: CLONE SO_CATEGORY WITH STATUS OVERRIDE BY RESTAURANT_CODE: {}, IS_SAVED_RESTAURANT_DATA_EDIT: {}", restaurantCode, isSavedRestaurantDataEdit);
		try {
			if(!CollectionUtils.isEmpty(soCategories)) {
				// Xóa các dữ liệu đánh dấu bản ghi đã bị update hoặc đã bị xóa
				restaurantDataEditRepository.deleteByRestaurantCode(restaurantCode);
				// Xóa các dữ liệu SO và CO (Phải xóa cả CO do danh mục CO sẽ thuộc danh mục SO) cũ ở dưới nhà hàng trước khi đồng bộ
				deleteSoMenuAndCoMenuWithStatusOverride(restaurantCode);
				// tạo mới dữ liệu
				soCategories.stream().forEach(soCategoryExisting -> {
					SoCategoryDto soCategoryExistingDto = Optional.ofNullable(soCategoryExisting).map(soCategoryMapper::entityToDto).orElse(null);
					// Tạo mới SO_CATEGORY
					SoCategoryDto soCategoryDto = new SoCategoryDto();
					BeanUtils.copyProperties(soCategoryExistingDto, soCategoryDto);
					soCategoryDto.setId(null);
					soCategoryDto.setRestaurantCode(restaurantCode);
					SoCategory soCategory = Optional.ofNullable(soCategoryDto).map(soCategoryMapper::dtoToEntity).orElse(null);
					
					soCategory.setOrderCategory(soCategoryExisting.getOrderCategory());
					soCategory.setSo(soCategoryExisting.getSo());
					SoCategory savedSoCategory = soCategoryRepository.save(soCategory);
					
					List<SoCategoryFoodGroup> soCategoryFoodGroupExistings = soCategoryExisting.getSoCategoryFoodGroups();
					List<SoCategoryFoodGroup> soCategoryFoodGroups = new ArrayList<>();
        			List<SoCategoryFoodGroup> soCategoryFoodGroupHasParents = new ArrayList<>();
        			
        			// tạo mới SO_CATEGORY_FOOD_GROUP and FOOD_GROUP
        			if(!CollectionUtils.isEmpty(soCategoryFoodGroupExistings)) {
        				soCategoryFoodGroupExistings.stream().forEach(soCategoryFoodGroupExisting ->{
        					if(soCategoryFoodGroupExisting.getFoodGroup().getParent() != null) {
        						soCategoryFoodGroupHasParents.add(soCategoryFoodGroupExisting);
        					} else {
        						soCategoryFoodGroups.add(soCategoryFoodGroupExisting);
        					}
        				});
        				// Lưu thông tin nhóm món ăn không có nhóm cha và các món ăn có trong nhóm
        				savedFoodGroupWithStatusOverride(restaurantCode, savedSoCategory, soCategoryFoodGroups, username);
        				// Lưu thông tin nhóm món ăn có nhóm cha và các món ăn có trong nhóm
        				savedFoodGroupWithStatusOverride(restaurantCode, savedSoCategory, soCategoryFoodGroupHasParents, username);
        			}
        			
        			// Đánh dấu bản ghi này đã được update để phục vụ cho việc đồng bộ UPDATE xuống SERVER nhà hàng
        			if(isSavedRestaurantDataEdit) {
	        			saveRestaurantDataEdit(savedSoCategory.getId().toString(), TypeRestaurantDataEditEnum.SO_CATEGORY.val, restaurantCode);
        			}
        			
				});
			}
		} catch (Exception e) {
			log.error("ERROR PROCESS FUNCTION: CLONE SO_CATEGORY WITH STATUS OVERRIDE EXCEPTION: {}", e);
		}
	}
	
	/*
	 * Tạo mới các dữ liệu liên quan đến FOOD_GROUP với trạng thái "UPDATE"
	 */
	private void savedFoodGroupWithStatusUpdate(Integer restaurantCode, SoCategory soCategory, List<SoCategoryFoodGroup> soCategoryFoodGroups, String username) {
		log.debug("PROCESS FUNCTION: SAVE FOOD_GROUP WITH STATUS UPDATE BY RESTAURANT_CODE: {}", restaurantCode);
		try {
			String moduleType = ModuleTypeEnum.FOOD_GROUP.val;
			// Lấy danh sách các FOOD_GROUP_CODE đã bị xóa ở dưới nhà hàng
			List<String> foodGroupCodeDels = foodGroupRepository.findFCodeByResCodeAndScIdAndChanged(restaurantCode, soCategory.getId(),StatusEnum.DELETE.status);
			if(!CollectionUtils.isEmpty(soCategoryFoodGroups)) {
				soCategoryFoodGroups.stream().forEach(soCategoryFoodGroupExisting ->{
					FoodGroup foodGroupExisting = soCategoryFoodGroupExisting.getFoodGroup(); 
					FoodGroupDto foodGroupExistingDto = Optional.ofNullable(foodGroupExisting).map(foodGroupMapper::entityToDto).orElse(null);
					String foodGroupCode = foodGroupExisting.getCode();
					// Kiểm tra xem nếu FOOD_GROUP_CODE đã nằm trong các CODE bị nhà hàng xóa thì không cần update ITEM này
					if(!foodGroupCodeDels.contains(foodGroupCode)) {
						// Kiểm tra xem FOOD_GROUP đã tồn tại ở dưới nhà hàng với mã CODE này chưa
						FoodGroup foodGroupInRestaurant = foodGroupRepository.findByCodeAndRestaurantCode(foodGroupCode, restaurantCode);
						// Nếu FOOD_GROUP chưa tồn tại thì tạo mới dữ liệu như bình thường
						if(foodGroupInRestaurant == null) {
							FoodGroupDto foodGroupDto = new FoodGroupDto();
							BeanUtils.copyProperties(foodGroupExistingDto, foodGroupDto);
							foodGroupDto.setId(null);
							foodGroupDto.setChanged(StatusEnum.INACTIVE.status);
							foodGroupDto.setRestaurantCode(restaurantCode);
							foodGroupDto.setCreatedBy(username);
							foodGroupDto.setModifiedBy(username);
							FoodGroup foodGroup = Optional.ofNullable(foodGroupDto).map(foodGroupMapper::dtoToEntity).orElse(null);
							foodGroup.setMenuType(foodGroupExisting.getMenuType());
							if(foodGroupExistingDto.getParent() != null) {
								// Tìm kiếm nhóm cha và lấy thông tin CODE của FOOD_GROUP
								FoodGroup foodGroupParentExistingInCatalog = foodGroupRepository.findById(foodGroupExistingDto.getParent().getId()).get();
								FoodGroup foodGroupParent = foodGroupRepository.findByCodeAndRestaurantCode(foodGroupParentExistingInCatalog.getCode(), restaurantCode);
								foodGroup.setParent(foodGroupParent);
							}
							FoodGroup savedFoodGroup = foodGroupRepository.save(foodGroup);
							
							// lưu thông tin ảnh FOOD_GROUP
							cloneAttachment(foodGroupExistingDto.getId(), savedFoodGroup.getId(), moduleType, restaurantCode, savedFoodGroup.getCode());
							
							// tạo mới thông tin SO_CATEGORY_FOOD_GROUP
							SoCategoryFoodGroupDto soCategoryFoodGroupDto = new SoCategoryFoodGroupDto();
							BeanUtils.copyProperties(soCategoryFoodGroupExisting, soCategoryFoodGroupDto);
							soCategoryFoodGroupDto.setId(null);
							soCategoryFoodGroupDto.setRestaurantCode(restaurantCode);
							if(savedFoodGroup.getParent() != null) {
								soCategoryFoodGroupDto.setFoodGroupParentId(savedFoodGroup.getParent().getId());
							}
							SoCategoryFoodGroup soCategoryFoodGroup = Optional.ofNullable(soCategoryFoodGroupDto).map(soCategoryFoodGroupMapper::dtoToEntity).orElse(null);
							soCategoryFoodGroup.setFoodGroup(savedFoodGroup);
							soCategoryFoodGroup.setSoCategory(soCategory);
							soCategoryFoodGroupRepository.save(soCategoryFoodGroup);
							
							List<FoodGroupItem> foodGroupItemExistings = foodGroupItemRepository.findByFoodGroupAndRestaurantCodeIsNull(foodGroupExisting);
							foodGroupItemExistings.stream().forEach(foodGroupItemExisting -> {
								FoodGroupItem foodGroupItem = new FoodGroupItem();
								foodGroupItem.setFoodGroup(savedFoodGroup);
								foodGroupItem.setFoodItem(foodGroupItemExisting.getFoodItem());
								foodGroupItem.setItemOrder(foodGroupItemExisting.getItemOrder());
								foodGroupItem.setChanged(StatusEnum.ACTIVE.status);
								foodGroupItem.setRestaurantCode(restaurantCode);
								foodGroupItemRepository.save(foodGroupItem);
							});
						} else {
							// Nếu đã tồn tại thì phân ra làm 2 TRƯỜNG HỢP
							
							// TRƯỜNG HỢP 1: NẾU FOOD_GROUP này chưa bị chỉnh sửa (Có CHANGED = 0) thì xóa dữ liệu và tạo mới
							if(foodGroupInRestaurant.getChanged().equals(StatusEnum.INACTIVE.status)) {
								// Xóa bỏ dữ liệu cũ
								foodGroupItemRepository.deleteByFoodGroupAndRestaurantCode(foodGroupInRestaurant, restaurantCode);
								soCategoryFoodGroupRepository.deleteByFoodGroupAndSoCategoryAndRestaurantCode(foodGroupInRestaurant, soCategory, restaurantCode);
								attachmentRepository.deleteByModuleIdAndModuleType(foodGroupInRestaurant.getId(), moduleType);
								// foodGroupRepository.delete(foodGroupInRestaurant);  //Do cùng 1 transaction nên nếu xóa thì cũng sẽ k update được dữ liệu
							
								// Tạo mới dữ liệu
								FoodGroupDto foodGroupDto = new FoodGroupDto();
								BeanUtils.copyProperties(foodGroupExistingDto, foodGroupDto);
								foodGroupDto.setId(foodGroupInRestaurant.getId());
								foodGroupDto.setChanged(StatusEnum.INACTIVE.status);
								foodGroupDto.setRestaurantCode(restaurantCode);
								foodGroupDto.setCreatedBy(username);
								foodGroupDto.setModifiedBy(username);
								FoodGroup foodGroup = Optional.ofNullable(foodGroupDto).map(foodGroupMapper::dtoToEntity).orElse(null);
								foodGroup.setMenuType(foodGroupExisting.getMenuType());
								if(foodGroupExistingDto.getParent() != null) {
									// Tìm kiếm nhóm cha và lấy thông tin CODE của FOOD_GROUP
									FoodGroup foodGroupParentExistingInCatalog = foodGroupRepository.findById(foodGroupExistingDto.getParent().getId()).get();
									FoodGroup foodGroupParent = foodGroupRepository.findByCodeAndRestaurantCode(foodGroupParentExistingInCatalog.getCode(), restaurantCode);
									foodGroup.setParent(foodGroupParent);
								}
								FoodGroup savedFoodGroup = foodGroupRepository.save(foodGroup);
								
								// lưu thông tin ảnh FOOD_GROUP
								cloneAttachment(foodGroupExistingDto.getId(), savedFoodGroup.getId(), moduleType, restaurantCode, savedFoodGroup.getCode());
								
								// tạo mới thông tin SO_CATEGORY_FOOD_GROUP
								SoCategoryFoodGroupDto soCategoryFoodGroupDto = new SoCategoryFoodGroupDto();
								BeanUtils.copyProperties(soCategoryFoodGroupExisting, soCategoryFoodGroupDto);
								soCategoryFoodGroupDto.setId(null);
								soCategoryFoodGroupDto.setRestaurantCode(restaurantCode);
								if(savedFoodGroup.getParent() != null) {
									soCategoryFoodGroupDto.setFoodGroupParentId(savedFoodGroup.getParent().getId());
								}
								SoCategoryFoodGroup soCategoryFoodGroup = Optional.ofNullable(soCategoryFoodGroupDto).map(soCategoryFoodGroupMapper::dtoToEntity).orElse(null);
								soCategoryFoodGroup.setFoodGroup(savedFoodGroup);
								soCategoryFoodGroup.setSoCategory(soCategory);
								soCategoryFoodGroupRepository.save(soCategoryFoodGroup);
								
								List<FoodGroupItem> foodGroupItemExistings = foodGroupItemRepository.findByFoodGroupAndRestaurantCodeIsNull(foodGroupExisting);
								foodGroupItemExistings.stream().forEach(foodGroupItemExisting -> {
									FoodGroupItem foodGroupItem = new FoodGroupItem();
									foodGroupItem.setFoodGroup(savedFoodGroup);
									foodGroupItem.setFoodItem(foodGroupItemExisting.getFoodItem());
									foodGroupItem.setItemOrder(foodGroupItemExisting.getItemOrder());
									foodGroupItem.setChanged(StatusEnum.ACTIVE.status);
									foodGroupItem.setRestaurantCode(restaurantCode);
									foodGroupItemRepository.save(foodGroupItem);
								});
							} else {
								// TRƯỜNG HỢP 2: Nếu FOOD_GROUP này đã bị chỉnh sửa ở phía nhà hàng thì thực hiện tiếp các bước sau
								
								
								// tạo mới thông tin SO_CATEGORY_FOOD_GROUP
								List<SoCategoryFoodGroup> soCategoryFoodGroupsExisting = foodGroupInRestaurant.getSoCategoryFoodGroups();
								if(CollectionUtils.isEmpty(soCategoryFoodGroupsExisting)) {
									SoCategoryFoodGroupDto soCategoryFoodGroupDto = new SoCategoryFoodGroupDto();
									BeanUtils.copyProperties(soCategoryFoodGroupExisting, soCategoryFoodGroupDto);
									soCategoryFoodGroupDto.setId(null);
									soCategoryFoodGroupDto.setRestaurantCode(restaurantCode);
									if(foodGroupInRestaurant.getParent() != null) {
										soCategoryFoodGroupDto.setFoodGroupParentId(foodGroupInRestaurant.getParent().getId());
									}
									SoCategoryFoodGroup soCategoryFoodGroup = Optional.ofNullable(soCategoryFoodGroupDto).map(soCategoryFoodGroupMapper::dtoToEntity).orElse(null);
									soCategoryFoodGroup.setFoodGroup(foodGroupInRestaurant);
									soCategoryFoodGroup.setSoCategory(soCategory);
									soCategoryFoodGroupRepository.save(soCategoryFoodGroup);
								}
								
								// Lấy các bản ghi FOOD_GROUP_ITEM ở danh mục để clone
								List<FoodGroupItem> foodGroupItemInCatalogs = foodGroupItemRepository.findByFoodGroupAndRestaurantCodeIsNull(foodGroupExisting);
								List<FoodGroupItem> foodGroupItemInRestaurants = foodGroupItemRepository.findByFoodGroupAndChangedAndRestaurantCode(foodGroupInRestaurant, StatusEnum.ACTIVE.status, restaurantCode);
								if(!CollectionUtils.isEmpty(foodGroupItemInCatalogs)) {
									if(!CollectionUtils.isEmpty(foodGroupItemInRestaurants)) {
										// lấy danh danh FOOD_ITEM_ID có trong FOOD_GROUP_ITEM ở danh mục
										List<Long> foodGroupFoodItemIdInCatalogs = foodGroupItemInCatalogs.stream().map(fgi ->fgi.getFoodItem().getId()).collect(Collectors.toList());
										// lấy danh danh FOOD_ITEM_ID có trong FOOD_GROUP_ITEM ở nhà hàng
										List<Long> foodGroupFoodItemIdInRestaurants = foodGroupItemInRestaurants.stream().map(fgi ->fgi.getFoodItem().getId()).collect(Collectors.toList());
										// Lấy danh sách FOOD_ITEM_ID đã bị xóa trong FOOD_GROUP_ITEM ở nhà hàng 
										List<Long> foodGroupFoodItemIdDeletedInRestaurants = foodGroupItemRepository.findByFoodGroupAndChangedAndRestaurantCode(foodGroupInRestaurant, StatusEnum.DELETE.status, restaurantCode).stream().map(fgi ->fgi.getFoodItem().getId()).collect(Collectors.toList());
										// Lấy danh sách FOOD_ITEM_ID đã được thêm trong FOOD_GROUP_ITEM ở nhà hàng 
										List<Long> foodGroupFoodItemIdAddedInRestaurants = foodGroupItemRepository.findByFoodGroupAndChangedAndRestaurantCode(foodGroupInRestaurant, null, restaurantCode).stream().map(fgi ->fgi.getFoodItem().getId()).collect(Collectors.toList());
										List<Long> newFoodItemIds = new ArrayList<>();
										List<Long> deletedFoodItemIds = new ArrayList<>();
										// Lặp để lấy các ITEM bị xóa
										foodGroupFoodItemIdInRestaurants.stream().forEach(fiId -> {
											if (!foodGroupFoodItemIdInCatalogs.contains(fiId)) {
												deletedFoodItemIds.add(fiId);
											}
										});
										if(!CollectionUtils.isEmpty(deletedFoodItemIds)) {
											deletedFoodItemIds.stream().forEach(fiId -> {
												FoodItem foodItem = new FoodItem();
												foodItem.setId(fiId);
												foodGroupItemRepository.deleteByFoodGroupAndChangedAndRestaurantCode(foodGroupInRestaurant, StatusEnum.ACTIVE.status, restaurantCode);
											});
										}
										
										// Lặp để lấy các ITEM được thêm vào
										foodGroupFoodItemIdInCatalogs.stream().forEach(fiId -> {
											if (!foodGroupFoodItemIdInRestaurants.contains(fiId)) {
												newFoodItemIds.add(fiId);
											}
										});
										if(!CollectionUtils.isEmpty(newFoodItemIds)) {
											Integer size = foodGroupFoodItemIdInRestaurants.size() + foodGroupFoodItemIdAddedInRestaurants.size();
											for ( int i = 0; i < newFoodItemIds.size(); i++) {
												Long fiId = newFoodItemIds.get(i);
												FoodItem foodItem = new FoodItem();
												foodItem.setId(fiId);
												if(!foodGroupFoodItemIdDeletedInRestaurants.contains(fiId) && !foodGroupFoodItemIdAddedInRestaurants.contains(fiId)) {
													FoodGroupItem fGroupItem = new FoodGroupItem();
													fGroupItem.setItemOrder(size + i + 1);
													fGroupItem.setFoodGroup(foodGroupInRestaurant);
													fGroupItem.setFoodItem(foodItem);
													fGroupItem.setRestaurantCode(restaurantCode);	
													fGroupItem.setChanged(StatusEnum.ACTIVE.status);
													foodGroupItemRepository.save(fGroupItem);
												}
											}
										}
										
										
									} else {
										// nếu trường hợp FOOD_GROUP_ITEM ở nhà hàng không có item thì xóa hết các bản ghi FOOD_GROUP_ITEM ở phía nhà hàng mà chưa được chỉnh sửa
										foodGroupItemInCatalogs.stream().forEach(fgi -> {
											FoodGroupItem newFoodGroupItem = new FoodGroupItem();
											newFoodGroupItem.setFoodGroup(foodGroupInRestaurant);
											newFoodGroupItem.setFoodItem(fgi.getFoodItem());
											newFoodGroupItem.setItemOrder(fgi.getItemOrder());
											newFoodGroupItem.setChanged(StatusEnum.ACTIVE.status);
											newFoodGroupItem.setRestaurantCode(restaurantCode);
											foodGroupItemRepository.save(newFoodGroupItem);
										});
									}
								} else {
									// nếu trường hợp FOOD_GROUP_ITEM ở danh mục không có item thì xóa hết các bản ghi FOOD_GROUP_ITEM ở phía nhà hàng mà chưa được chỉnh sửa
									foodGroupItemRepository.deleteByFoodGroupAndChangedAndRestaurantCode(foodGroupInRestaurant, StatusEnum.ACTIVE.status, restaurantCode);
								}
							}
						}
					}
				});
			}
		} catch (Exception e) {
			log.error("ERROR PROCESS FUNCTION: SAVE FOOD_GROUP WITH STATUS UPDATE EXCEPTION: {}", e);
		}
	}

	/*
	 * Tạo mới các dữ liệu liên quan đến FOOD_GROUP với trạng thái "GHI ĐÈ"
	 */
	private void savedFoodGroupWithStatusOverride(Integer restaurantCode, SoCategory soCategory, List<SoCategoryFoodGroup> soCategoryFoodGroups, String username) {
		log.debug("PROCESS FUNCTION: SAVE FOOD_GROUP WITH STATUS OVERRIDE BY RESTAURANT_CODE: {}", restaurantCode);
		try {
			String moduleType = ModuleTypeEnum.FOOD_GROUP.val;
			if(!CollectionUtils.isEmpty(soCategoryFoodGroups)) {
				soCategoryFoodGroups.stream().forEach(soCategoryFoodGroupExisting ->{
					FoodGroup foodGroupExisting = soCategoryFoodGroupExisting.getFoodGroup();
					FoodGroupDto foodGroupExistingDto = Optional.ofNullable(foodGroupExisting).map(foodGroupMapper::entityToDto).orElse(null);
					FoodGroupDto foodGroupDto = new FoodGroupDto();
					BeanUtils.copyProperties(foodGroupExistingDto, foodGroupDto);
					foodGroupDto.setId(null);
					foodGroupDto.setChanged(StatusEnum.INACTIVE.status);
					foodGroupDto.setRestaurantCode(restaurantCode);
					foodGroupDto.setCreatedBy(username);
					foodGroupDto.setModifiedBy(username);
					FoodGroup foodGroup = Optional.ofNullable(foodGroupDto).map(foodGroupMapper::dtoToEntity).orElse(null);
					foodGroup.setMenuType(foodGroupExisting.getMenuType());
					if(foodGroupExistingDto.getParent() != null) {
						// Tìm kiếm nhóm cha và lấy thông tin CODE của FOOD_GROUP
						FoodGroup foodGroupParentExistingInCatalog = foodGroupRepository.findById(foodGroupExistingDto.getParent().getId()).get();
						FoodGroup foodGroupParent = foodGroupRepository.findByCodeAndRestaurantCode(foodGroupParentExistingInCatalog.getCode(), restaurantCode);
						foodGroup.setParent(foodGroupParent);
					}
					FoodGroup savedFoodGroup = foodGroupRepository.save(foodGroup);
					
					// lưu thông tin ảnh FOOD_GROUP
					cloneAttachment(foodGroupExistingDto.getId(), savedFoodGroup.getId(), moduleType, restaurantCode, savedFoodGroup.getCode());
					
					// tạo mới thông tin SO_CATEGORY_FOOD_GROUP
					SoCategoryFoodGroupDto soCategoryFoodGroupDto = new SoCategoryFoodGroupDto();
					BeanUtils.copyProperties(soCategoryFoodGroupExisting, soCategoryFoodGroupDto);
					soCategoryFoodGroupDto.setId(null);
					soCategoryFoodGroupDto.setRestaurantCode(restaurantCode);
					if(savedFoodGroup.getParent() != null) {
						soCategoryFoodGroupDto.setFoodGroupParentId(savedFoodGroup.getParent().getId());
					}
					SoCategoryFoodGroup soCategoryFoodGroup = Optional.ofNullable(soCategoryFoodGroupDto).map(soCategoryFoodGroupMapper::dtoToEntity).orElse(null);
					soCategoryFoodGroup.setFoodGroup(savedFoodGroup);
					soCategoryFoodGroup.setSoCategory(soCategory);
					soCategoryFoodGroupRepository.save(soCategoryFoodGroup);
					
					List<FoodGroupItem> foodGroupItemExistings = foodGroupItemRepository.findByFoodGroupAndRestaurantCodeIsNull(foodGroupExisting);
					foodGroupItemExistings.stream().forEach(foodGroupItemExisting -> {
						FoodGroupItem foodGroupItem = new FoodGroupItem();
						foodGroupItem.setFoodGroup(savedFoodGroup);
						foodGroupItem.setFoodItem(foodGroupItemExisting.getFoodItem());
						foodGroupItem.setItemOrder(foodGroupItemExisting.getItemOrder());
						foodGroupItem.setChanged(StatusEnum.ACTIVE.status);
						foodGroupItem.setRestaurantCode(restaurantCode);
						foodGroupItemRepository.save(foodGroupItem);
					});
				});
			}
		} catch (Exception e) {
			log.error("ERROR PROCESS FUNCTION: SAVE FOOD_GROUP WITH STATUS OVERRIDE EXCEPTION: {}", e);
		}
	}
	
	/*
	 * Function xóa các nhóm FOOD_GROUP dưới nhà hàng
	 */
	private List<String> deleteFoodGroupInRestaurant(List<SoCategoryFoodGroup> soCategoryFoodGroups, Integer restaurantCode, SoCategory soCategory){
		log.error("PROCESS FUNCTION: DELETE FOOD_GROUP IN RESTAURANT, RESTAURANT_CODE: {}, SO_CATEGORY_FOOD_GROUP: {}", restaurantCode, soCategoryFoodGroups);
		List<String> foodGroupCodeDels = new ArrayList<>();
		List<String> foodGroupCodeChanged = new ArrayList<>();
		String moduleType = ModuleTypeEnum.FOOD_GROUP.val;
		try {
			// Lấy các FOOD_GROUP_CODE đã bị chỉnh sửa hoặc xóa ở dưới nhà hàng
			foodGroupCodeChanged.addAll((foodGroupRepository.findFCodeByResCodeAndScIdAndChanged(restaurantCode, soCategory.getId(),StatusEnum.ACTIVE.status)));
			foodGroupCodeChanged.addAll((foodGroupRepository.findFCodeByResCodeAndScIdAndChanged(restaurantCode, soCategory.getId(),StatusEnum.DELETE.status)));
			soCategoryFoodGroups.stream().forEach(scfg ->{
				FoodGroup foodGroup = scfg.getFoodGroup();
				String foodGroupCode = foodGroup.getCode();
				// Chỉ xóa các ITEM chưa bị chỉnh sửa hoặc bị xóa ở dưới nhà hàng
				if(!foodGroupCodeChanged.contains(foodGroupCode)) {
					FoodGroup delFoodGroup = foodGroupRepository.findByCodeAndRestaurantCode(foodGroupCode, restaurantCode);
					if(delFoodGroup != null) {
						foodGroupItemRepository.deleteByFoodGroupAndRestaurantCode(delFoodGroup, restaurantCode);
						soCategoryFoodGroupRepository.deleteByFoodGroupAndSoCategoryAndRestaurantCode(delFoodGroup, soCategory, restaurantCode);
						attachmentRepository.deleteByModuleIdAndModuleType(delFoodGroup.getId(), moduleType);
						foodGroupRepository.delete(delFoodGroup);
						foodGroupCodeDels.add(delFoodGroup.getCode());
					} 
				}
			});
		} catch (Exception e) {
			log.error("ERROR PROCESS FUNCTION: DELETE FOOD_GROUP IN RESTAURANT EXCEPTION: {}", e);
		}
		return foodGroupCodeDels;
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
	
	/*
	 * Xóa dữ liệu SO và CO của nhà hàng trước khi đồng bộ với trạng thái "GHI ĐÈ"
	 */
	private void deleteSoMenuAndCoMenuWithStatusOverride(Integer restaurantCode) {
		log.debug("PROCESS FUNCTION: DELETE SO_MENU AND CO_MENU WITH STATUS OVERRIDE BY RESTAURANT_CODE: {}", restaurantCode);
		try {
			// các biến cần dùng
			String foodGroupModuleType = ModuleTypeEnum.FOOD_GROUP.val;
			String coFoodItemModuleType = ModuleTypeEnum.CO_FOOD_ITEM.val;
			String coCategoryModuleType = ModuleTypeEnum.CO_CATEGORY.val;
			String configQrOrderModuleType = ModuleTypeEnum.CONFIG_QR_ORDER.val;
			
			// Lấy tất cả bản ghi của SO_MENU và CO_MENU cần xóa
			
			List<FoodGroup> foodGroups = foodGroupRepository.findByRestaurantCode(restaurantCode);
			List<CoCategory> coCategories = coCategoryRepository.findByRestaurantCode(restaurantCode);
			List<CoFoodItem> coFoodItems = coFoodItemRepository.findByRestaurantCode(restaurantCode);
			ConfigQrOrder configQrOrder = configQrOrderRepository.findByRestaurantCode(String.valueOf(restaurantCode));
			// Do cần xóa cả phần nhóm món ăn nên cần xóa các nhóm món ăn có nhóm cha trước thì mới xóa được các nhóm món ăn không có nhóm cha
			Set<FoodGroup> deletedFoodGroups = new HashSet<>();
			
			// Xóa thông tin SO_CATEGORY
			soCategoryFoodGroupRepository.deleteByRestaurantCode(restaurantCode);	
			
			if(!CollectionUtils.isEmpty(foodGroups)) {
				foodGroups.stream().forEach(foodGroup ->{
					// Xóa thông tin nhóm món ăn có nhóm cha và thông tin các món ăn có trong nhóm
					if(foodGroup.getParent() != null){
						foodGroupItemRepository.deleteByFoodGroupAndRestaurantCode(foodGroup, restaurantCode);
						attachmentRepository.deleteByModuleIdAndModuleType(foodGroup.getId(), foodGroupModuleType);
						foodGroupRepository.delete(foodGroup);
					} else {
						deletedFoodGroups.add(foodGroup);
					}
				});
			}
			
			// Xóa thông tin nhóm món ăn không có nhóm cha và thông tin các món ăn có trong nhóm
			if(!CollectionUtils.isEmpty(deletedFoodGroups)) {
				deletedFoodGroups.stream().forEach(foodGroup ->{
					foodGroupItemRepository.deleteByFoodGroupAndRestaurantCode(foodGroup, restaurantCode);
					attachmentRepository.deleteByModuleIdAndModuleType(foodGroup.getId(), foodGroupModuleType);
					foodGroupRepository.delete(foodGroup);
				});
			}
			// Xóa thông tin các món ăn có trong CO_MENU
			if(!CollectionUtils.isEmpty(coFoodItems)) {
				coFoodItems.stream().forEach(coFoodItem ->{
					coFoodItemModifierRepository.deleteByCoFoodItem(coFoodItem);
					relatedFoodItemRepository.deleteByCoFoodItem(coFoodItem);
					toppingFoodItemRepository.deleteByCoFoodItem(coFoodItem);
					attachmentRepository.deleteByModuleIdAndModuleType(coFoodItem.getId(), coFoodItemModuleType);
					coFoodItemRepository.delete(coFoodItem);
				});
			}
			// Xóa thông tin các nhóm MENU trong CO_MENU
			if(!CollectionUtils.isEmpty(coCategories)) {
				coCategories.stream().forEach(coCategory ->{
					coFoodGroupDisplayRepository.deleteByCoCategory(coCategory);
					attachmentRepository.deleteByModuleIdAndModuleType(coCategory.getId(), coCategoryModuleType);
					coCategoryRepository.delete(coCategory);
				});
			}
			// Xóa thông tin liên quan đến CONFIG_QR_ORDER
			if(configQrOrder != null) {
				attachmentRepository.deleteByModuleIdAndModuleType(configQrOrder.getId(), configQrOrderModuleType);
				configQrOrderRepository.delete(configQrOrder);
			}
			
			// Xóa thông tin SO_CATEGORY
			soCategoryRepository.deleteByRestaurantCode(restaurantCode);
		} catch (Exception e) {
			log.error("ERROR PROCESS FUNCTION: DELETE SO_MENU AND CO_MENU WITH STATUS OVERRIDE EXCEPTION: {}", e);
		}
	}
	
	/*
	 * Xóa dữ liệu SO và CO của nhà hàng trước khi đồng bộ với trạng thái "UPDATE"
	 */
	private void deleteSoMenuAndCoMenuWithStatusUpdate(List<String> deleteSoCategoryCodeInCatalogs, Integer restaurantCode) {
		log.debug("PROCESS FUNCTION: DELETE SO_MENU AND CO_MENU WITH STATUS UPDATE BY RESTAURANT_CODE: {}, DELETE_SO_CATEGORY_CODE: {}", restaurantCode, deleteSoCategoryCodeInCatalogs);
		try {
			// các biến cần dùng
			String foodGroupModuleType = ModuleTypeEnum.FOOD_GROUP.val;
			// Xóa thông tin SO_MENU
			if(!CollectionUtils.isEmpty(deleteSoCategoryCodeInCatalogs)) {
				List<SoCategory> soCategories = soCategoryRepository.findByRestaurantCodeAndOrderCateCode(deleteSoCategoryCodeInCatalogs,restaurantCode);
				if(!CollectionUtils.isEmpty(soCategories)) {
					soCategories.stream().forEach(soCategory ->{
						String orderCategoryCode = soCategory.getOrderCategory().getCode();
						// Xóa thông tin CO_CATEGORY do CO_CATEGORY trỏ đến SO_CATEGORY làm khóa
						CoCategory coCategory = coCategoryRepository.findBySoCategoryAndRestaurantCode(soCategory, restaurantCode);
						if(coCategory != null) {
							coFoodGroupDisplayRepository.deleteByCoCategory(coCategory);
							attachmentRepository.deleteByModuleIdAndModuleType(coCategory.getId(), ModuleTypeEnum.CO_CATEGORY.val);
							coCategoryRepository.delete(coCategory);
							// Đánh dấu bản ghi này đã bị XÓA để phục vụ cho việc đồng bộ UPDATE xuống SERVER nhà hàng
							saveRestaurantDataEdit(orderCategoryCode, TypeRestaurantDataEditEnum.DELETE_CO_CATEGORY.val, restaurantCode);
						}
						// Xóa thông tin SO_CATEGORY
						Set<FoodGroup> deletedFoodGroup = new HashSet<>();
						Set<FoodGroup> deletedFoodGroupHasParent = new HashSet<>();
						// lấy các data cần xóa 
						List<SoCategoryFoodGroup> soCategoryFoodGroups = soCategoryFoodGroupRepository.findBySoCategoryAndRestaurantCode(soCategory.getId(), restaurantCode);
						if(!CollectionUtils.isEmpty(soCategoryFoodGroups)) {
							soCategoryFoodGroups.stream().forEach(scfg ->{
								if(scfg.getFoodGroup().getParent() != null) {
									deletedFoodGroupHasParent.add(scfg.getFoodGroup());
								} else {
									deletedFoodGroup.add(scfg.getFoodGroup());
								}
								// Chỉ xóa các dữ liệu mà không bị nhà hàng chỉnh sửa (Nếu chỉnh sửa thì sẽ có value CHANGED = 1)
								if(scfg.getFoodGroup().getChanged() == null || !scfg.getFoodGroup().getChanged().equals(StatusEnum.ACTIVE.status)) {
									soCategoryFoodGroupRepository.delete(scfg);
								}
							});
						}
						// Xóa data FOOD_GROUP mà có nhóm cha
						if(!CollectionUtils.isEmpty(deletedFoodGroupHasParent)) {
							deletedFoodGroupHasParent.stream().forEach(foodGroup -> {
								// Chỉ xóa các dữ liệu mà không bị nhà hàng chỉnh sửa (Nếu chỉnh sửa thì sẽ có value CHANGED = 1)
								if(foodGroup.getChanged() == null || !foodGroup.getChanged().equals(StatusEnum.ACTIVE.status)) {
									foodGroupItemRepository.deleteByFoodGroupAndRestaurantCode(foodGroup, restaurantCode);
									attachmentRepository.deleteByModuleIdAndModuleType(foodGroup.getId(), foodGroupModuleType);
									foodGroupRepository.delete(foodGroup);
								}
							});
						}
						// Xóa data FOOD_GROUP mà không có nhóm cha
						if(!CollectionUtils.isEmpty(deletedFoodGroup)) {
							deletedFoodGroup.stream().forEach(foodGroup -> {
								// Chỉ xóa các dữ liệu mà không bị nhà hàng chỉnh sửa (Nếu chỉnh sửa thì sẽ có value CHANGED = 1)
								if(foodGroup.getChanged() == null || !foodGroup.getChanged().equals(StatusEnum.ACTIVE.status)) {
									foodGroupItemRepository.deleteByFoodGroupAndRestaurantCode(foodGroup, restaurantCode);
									attachmentRepository.deleteByModuleIdAndModuleType(foodGroup.getId(), foodGroupModuleType);
									foodGroupRepository.delete(foodGroup);
								}
								
							});
						}
						soCategoryRepository.delete(soCategory);
						// Đánh dấu bản ghi này đã bị XÓA để phục vụ cho việc đồng bộ UPDATE xuống SERVER nhà hàng
						saveRestaurantDataEdit(orderCategoryCode, TypeRestaurantDataEditEnum.DELETE_SO_CATEGORY.val, restaurantCode);
					});
				}
			}
		} catch (Exception e) {
			log.error("ERROR PROCESS FUNCTION: DELETE SO_MENU AND CO_MENU WITH STATUS UPDATE EXCEPTION: {}", e);
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
	 * Lấy danh sách SO_CATEGORY_CODE đã chỉnh sửa hoặc đã bị xóa ở dưới nhà hàng
	 */
	private List<String> getSoCategoryCodeChangedAndDeleteds(Integer restaurantCode){
		log.debug("PROCESS FUNCTION: GET SO_CATEGORY_CODE CHANGED AND DELETED, RESTAURANT_CODE: {}", restaurantCode);
		List<String> soCategoryCodes = new ArrayList<>();
		try {
			soCategoryCodes.addAll(restaurantDataEditRepository.findValues(restaurantCode, TypeRestaurantDataEditEnum.DEL_SO_CATEGORY_OLD_RES.val));
			soCategoryCodes.addAll(restaurantDataEditRepository.findValues(restaurantCode, TypeRestaurantDataEditEnum.DELETE_SO_CATEGORY_RES.val));
		} catch (Exception e) {
			log.error("ERROR PROCESS FUNCTION: GET CO_CATEGORY_CODE CHANGED AND DELETED EXCEPTION: {}", e);
		}
		return soCategoryCodes;
	}

	/*
	 * Xóa các dữ liệu lưu trữ thông tin các CO_FOOD_ITEM và CO_CATEGORY đã chỉnh sửa ở trong danh mục CO_MENU (Các dữ liệu này chỉ phục vụ cho việc đồng bộ update)
	 */
	private void deleteCatalogDataEdit(Long catalogId) {
		log.debug("PROCESS FUNCTION: DELETE DATA CATALOG_DATA_EDIT, CATALOG_ID: {}", catalogId);
		try {
			catalogDataEditRepository.deleteByCatalogIdAndType(catalogId, TypeRestaurantDataEditEnum.SO_CATEGORY.val);
			catalogDataEditRepository.deleteByCatalogIdAndType(catalogId, TypeRestaurantDataEditEnum.DELETE_SO_CATEGORY.val);
		} catch (Exception e) {
			log.error("ERROR PROCESS FUNCTION: DELETE DATA CATALOG_DATA_EDIT EXCEPTION: {}", e);
		}
	}
	
	/*
	 * Lưu dữ liệu vào bảng đánh dấu các nhà hàng đã áp dụng danh mục nào 
	 * Nếu ghi đè danh mục SO_MENU thì cần set lại danh mục CO_MENU = null
	 */
	private void saveCatalogApplyToRestaurant(Integer resCode, Long soMenuId, Boolean isOverride) {
		log.debug("PROCESS FUNCTION: SAVE CATALOG-APPLY-TO-RESTAURANT BY RESTAURANT_CODE AND CO_MENU_ID, RESTAURANT_CODE: {}, SO_MENU_ID: {}", resCode, soMenuId);
		try {
			CatalogApplyToRestaurant applyToRestaurant = catalogApplyToRestaurantRepository.findByRestaurantCode(resCode);
			if(applyToRestaurant == null) {
				applyToRestaurant = new CatalogApplyToRestaurant();
			} else {
				// trường hợp nhà hàng đã áp dụng SO_MENU rồi mà muốn áp dụng danh mục SO_MENU Khác thì cần set giá trị CO_MENU = null
				if(applyToRestaurant.getSoId() != null && !applyToRestaurant.getSoId().equals(soMenuId)) {
					applyToRestaurant.setCoId(null);
				}
			}
			if(isOverride) {
				applyToRestaurant.setCoId(null);
			}
			applyToRestaurant.setRestaurantCode(resCode);
			applyToRestaurant.setSoId(soMenuId);
			catalogApplyToRestaurantRepository.save(applyToRestaurant);
		} catch (Exception e) {
			log.error("ERROR PROCESS FUNCTION: SAVE CATALOG-APPLY-TO-RESTAURANT EXCEPTION: {}", e);
		}
	}
	
	/*
	 * Convert List thành file Json (trạng thái GHI ĐÈ)
	 */
	private RestaurantMenuDataSync convertSoMenuToJsonWithStatusOverride(RestaurantMenuDataSync restaurantMenuDataSync, Integer restaurantCode, String restaurantName, String apiUrlFile, Boolean isOverride) {
		log.debug("PROCESS FUNCTION: CONVERT SO_MENU LIST TO JSON WITH STATUS OVERRIDE");
		List<FileContent> fileContents = new ArrayList<>();
		List<ConfigMenuEngineering> configMenuEngineerings = new ArrayList<>();
		List<JsonMenuGroupDetail> jsonMenuGroupDetails = new ArrayList<>();
		try {
			List<ConfigMenuEngineering> deleteMenuEngineerings = new ArrayList<>();
			// các biến cần dùng
			Integer defaultGroupLevel = FoodGroupLevelEnum.MENU_NORMAL.val;
			String folderUpload = UploadImageEnum.IMG_MENU_FOLDER.val;
			String moduleType = ModuleTypeEnum.FOOD_GROUP.val;
			Integer deleteStatus = StatusEnum.DELETE.status;
			List<SoCategory> soCategoryInRestaurants = new ArrayList<>();
			// Lấy danh sách các bản ghi cần được đồng bộ
			if(isOverride) {
				soCategoryInRestaurants.addAll(soCategoryRepository.findByRestaurantCode(restaurantCode));  
			} else {
				// lấy danh sách các bản ghi đã bị chỉnh sửa để đồng bộ
				List<Long> soCategoryEditIds = restaurantDataEditRepository.findByRestaurantCodeAndType(restaurantCode, TypeRestaurantDataEditEnum.SO_CATEGORY.val).stream().map(p -> Long.parseLong(p.getValue())).collect(Collectors.toList());
				soCategoryInRestaurants.addAll(soCategoryRepository.findByIdIn(soCategoryEditIds));
				// Lấy danh sách các bản ghi đã bị xóa
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
			}
			// PHẦN THÔNG TIN LIÊN QUAN ĐẾN SO_CATEGORY
			if(!CollectionUtils.isEmpty(soCategoryInRestaurants)) {
				soCategoryInRestaurants.stream().forEach(soCategoryInRestaurant ->{
					if(soCategoryInRestaurant.isStatus()) {
						ConfigMenuEngineering configMenuEngineering = new ConfigMenuEngineering();
						
						OrderCategoryDto orderCategoryDto = Optional.ofNullable(soCategoryInRestaurant.getOrderCategory()).map(orderCategoryMapper::entityToDto).orElse(null); 
						// dữ liệu liên quan đến ORDER_CATEGORY
						JsonOrderCategory jsonOrderCategory = new JsonOrderCategory();
						BeanUtils.copyProperties(orderCategoryDto, jsonOrderCategory);
						if (soCategoryInRestaurant.getOrderCategoryParentId() != null) {
							jsonOrderCategory.setOrderCategoryParentId(soCategoryInRestaurant.getOrderCategoryParentId());	
						}
						configMenuEngineering.setJsonOrderCategory(jsonOrderCategory);
						// Dữ liệu menu_type
						for (MenuTypeEnum e : MenuTypeEnum.values()) {
			        		if (e.key.equals(soCategoryInRestaurant.getType())) {
			        			configMenuEngineering.setMenuType(e.val);		
			        		}
			        	}
						// Dữ liệu vé buffet người lớn
						JsonDefaultDish jsonDefaultDish = new JsonDefaultDish();
						if(soCategoryInRestaurant.getAdultBufferTicket() != null) {
							JsonDishItem jsonDishItem = new JsonDishItem();
			        		String[] str = soCategoryInRestaurant.getAdultBufferTicket().split(SymbolEnum.HYPHEN.val);
			        		FoodItemDto foodItemDto = Optional.ofNullable(foodItemRepository.findByCode(str[0])).map(foodItemMapper::entityToDto).orElse(null);
			        		if(foodItemDto != null) {
			        			BeanUtils.copyProperties(foodItemDto, jsonDishItem);
			        			jsonDefaultDish.setJsonDishItem(jsonDishItem);
			        		}
			        		configMenuEngineering.setJsonDefaultDish(jsonDefaultDish);
						}
						// Dữ liệu vé buffet trẻ em
						JsonChildDish jsonChildDish = new JsonChildDish();
						if(soCategoryInRestaurant.getKidBufferTicket() != null) {
							JsonDishItem jsonDishItem = new JsonDishItem();
			        		String[] str = soCategoryInRestaurant.getKidBufferTicket().split(SymbolEnum.HYPHEN.val);
			        		FoodItemDto foodItemDto = Optional.ofNullable(foodItemRepository.findByCode(str[0])).map(foodItemMapper::entityToDto).orElse(null);
			        		if(foodItemDto != null) {
			        			BeanUtils.copyProperties(foodItemDto, jsonDishItem);
			        			jsonChildDish.setJsonDishItem(jsonDishItem);
			        		}
			        		configMenuEngineering.setJsonChildDish(jsonChildDish);
						}
						
						// Dữ liệu liên quan đến FOOD_GROUP
						List<SoCategoryFoodGroup> soCategoryFoodGroupExistings = soCategoryFoodGroupRepository.findBySoCategoryAndRestaurantCode(soCategoryInRestaurant.getId(), restaurantCode);
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
										fileContents.add(file);
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
						jsonOrderCategory.setCode(soCategoryInRestaurant.getOrderCategory().getCode());
						delMenuEngineering.setJsonOrderCategory(jsonOrderCategory);
						deleteMenuEngineerings.add(delMenuEngineering);
					}
				});
			}
			restaurantMenuDataSync.setMenuEngineerings(configMenuEngineerings);
			restaurantMenuDataSync.setMenuGroups(jsonMenuGroupDetails);
			restaurantMenuDataSync.setFileContents(fileContents);
			restaurantMenuDataSync.setDeleteSoCategoryItems(deleteMenuEngineerings);
		} catch (Exception e) {
			log.error("ERROR PROCESS FUNCTION: CONVERT SO_MENU LIST TO JSON WITH STATUS OVERRIDE EXCEPTION: {}", e);
		}
		return restaurantMenuDataSync;
	}
	
	/*
	 * Lấy dữ liệu phục vụ cho việc đồng bộ từ DANH MỤC xuống SERVER NHÀ HÀNG
	 */
	public Map<String, Object> getSoMenuDataSync(Long soMenuId) {
		log.debug("PROCESS FUNCTION: GET SO_MENU DATA SYNC, SO_MENU_ID: {}", soMenuId);
		Map<String, Object> map = new HashMap<>();
		try {
			// Lấy danh sách các SO_CATEGORY có trong danh mục SO_MENU
			List<SoCategoryDto> soCategoryDtos = new ArrayList<>();
			List<SoCategory> soCategoryDtoInCatalogs = soCategoryRepository.findBySoId(soMenuId);
			if(!CollectionUtils.isEmpty(soCategoryDtoInCatalogs)) {
				soCategoryDtoInCatalogs.stream().forEach(soCategoryExisting ->{
					SoCategoryDto soCategoryDto = Optional.ofNullable(soCategoryExisting).map(soCategoryMapper::entityToDto).orElse(null);
					List<SoCategoryFoodGroupDto> soCategoryFoodGroupDtos = new ArrayList<>();
					if(!CollectionUtils.isEmpty(soCategoryExisting.getSoCategoryFoodGroups())) {
						soCategoryExisting.getSoCategoryFoodGroups().stream().forEach(item -> {
							FoodGroupDto foodGroupDto = Optional.ofNullable(item.getFoodGroup()).map(foodGroupMapper::entityToDto).orElse(null);
							SoCategoryFoodGroupDto soCategoryFoodGroupDto = Optional.ofNullable(item).map(soCategoryFoodGroupMapper::entityToDto).orElse(null);
							soCategoryFoodGroupDto.setFoodGroup(foodGroupDto);
							soCategoryFoodGroupDtos.add(soCategoryFoodGroupDto);
						});
					}
					soCategoryDto.setSoCategoryFoodGroups(soCategoryFoodGroupDtos);
					soCategoryDtos.add(soCategoryDto);
				});
			}
			map.put(MapKeyEnum.SO_CATEGORY_IN_CATALOG.key, soCategoryDtos);
			
			// Lấy danh sách các SO_CATEGORY có trong danh mục SO_MENU
			List<SoCategoryDto> soCategoryEditInCatalogDtos = new ArrayList<>();
			// Lấy danh sách SO_CATEGORY_ID đã được chỉnh sửa ở danh mục SO_MENU
			List<Long> soCategoryEditIds = catalogDataEditRepository.findByTypeAndCatalogId(TypeRestaurantDataEditEnum.SO_CATEGORY.val, soMenuId).stream().map(p -> Long.parseLong(p.getValue())).collect(Collectors.toList());
			// Lấy thông tin các bản ghi SO_CATEGORY đã được chỉnh sửa ở danh mục SO_MENU
			List<SoCategory> soCategoryEditDtoInCatalogs = soCategoryRepository.findByIdIn(soCategoryEditIds);
			if(!CollectionUtils.isEmpty(soCategoryEditDtoInCatalogs)) {
				soCategoryEditDtoInCatalogs.stream().forEach(soCategoryExisting ->{
					SoCategoryDto soCategoryDto = Optional.ofNullable(soCategoryExisting).map(soCategoryMapper::entityToDto).orElse(null);
					List<SoCategoryFoodGroupDto> soCategoryFoodGroupDtos = new ArrayList<>();
					if(!CollectionUtils.isEmpty(soCategoryExisting.getSoCategoryFoodGroups())) {
						soCategoryExisting.getSoCategoryFoodGroups().stream().forEach(item -> {
							FoodGroupDto foodGroupDto = Optional.ofNullable(item.getFoodGroup()).map(foodGroupMapper::entityToDto).orElse(null);
							SoCategoryFoodGroupDto soCategoryFoodGroupDto = Optional.ofNullable(item).map(soCategoryFoodGroupMapper::entityToDto).orElse(null);
							soCategoryFoodGroupDto.setFoodGroup(foodGroupDto);
							soCategoryFoodGroupDtos.add(soCategoryFoodGroupDto);
						});
					}
					soCategoryDto.setSoCategoryFoodGroups(soCategoryFoodGroupDtos);
					soCategoryEditInCatalogDtos.add(soCategoryDto);
				});
			}
			map.put(MapKeyEnum.SO_CATEGORY_EDIT_IN_CATALOG.key, soCategoryEditInCatalogDtos);
			
			// Lấy danh sách SO_CATEGORY_CODE đã bị xóa ở danh mục SO_MENU để xóa các bản ghi có CODE tương ứng ở dưới nhà hàng
			List<String> deleteSoCategoryCodeInCatalogs = catalogDataEditRepository.findByTypeAndCatalogId(TypeRestaurantDataEditEnum.DELETE_SO_CATEGORY.val, soMenuId).stream().map(c -> c.getValue()).collect(Collectors.toList());
			map.put(MapKeyEnum.DELETE_SO_CATEGORY_CODE_IN_CATALOG.key, deleteSoCategoryCodeInCatalogs);
		} catch (Exception e) {
			log.error("ERROR PROCESS: GET SO_MENU DATA SYNC EXCEPTION: {}", e);
		}
		return map;
	}
}
