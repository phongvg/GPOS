package com.gg.gpos.integration.manager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gg.gpos.common.constant.*;
import com.gg.gpos.menu.dto.SyncResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gg.gpos.common.json.RestaurantMenuDataSync;
import com.gg.gpos.integration.dto.SyncDto;
import com.gg.gpos.menu.dto.VersionDto;
import com.gg.gpos.menu.manager.RestaurantDataEditManager;
import com.gg.gpos.menu.manager.SyncRestaurantDataManager;
import com.gg.gpos.menu.manager.VersionManager;
import com.gg.gpos.reference.manager.SystemParameterManager;
import com.gg.gpos.res.dto.RestaurantDto;
import com.gg.gpos.res.manager.RestaurantManager;
import com.gg.gpos.res.manager.SyncStatusManager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ThreadSyncManager {
	@Autowired
    private ThreadPoolTaskExecutor taskExecutor;
	@Autowired
	private SyncDataCoMenuManager syncDataCoMenuManager;
	@Autowired
	private SyncDataGroupParamManager syncDataGroupParamManager;
	@Autowired
	private SyncDataSoMenuManager syncDataSoMenuManager;
	@Autowired
	private RestaurantSyncManager restaurantSyncManager;
	@Autowired
	private SystemParameterManager systemParameterManager;
	@Autowired
	private RestaurantManager restaurantManager;
	@Autowired
    private SyncStatusManager syncStatusManager;
	@Autowired
	private SyncManager syncManager;
	@Autowired
	private MenuDataSyncManager menuDataSyncManager;
	@Autowired
    private RestaurantDataEditManager restaurantDataEditManager;
	@Autowired
    private SyncRestaurantDataManager syncRestaurantDataManager;
	@Autowired
	private VersionManager versionManager;
	
	private List<Integer> restaurantCodeAsyncs = new ArrayList<>();
	private List<Integer> restaurantcodeSyncNow = new ArrayList<>();

	public SyncResponseDto syncNow(SyncDto syncDto) {
		log.info("ENTERING SYNC_FILE, SYNC_DTO: {}", syncDto);
		SyncResponseDto syncResponseDto = new SyncResponseDto();
		syncResponseDto.setStatus(true);
		if(restaurantcodeSyncNow.size() < 1) {
			if(!restaurantCodeAsyncs.contains(syncDto.getRestaurantCode())) {
				syncDto.setSyncStartDate(LocalDateTime.now());
				syncManager.updateSync(syncDto, ResultEnum.WAITING_SYNC.val, SyncStatusEnum.WAIT_FOR_SYNC_NOW.val);
			} else {
				syncResponseDto.setStatus(false);
				syncResponseDto.setMessage("Nhà hàng thực hiện việc đồng bộ. Để tránh việc có thể xảy ra lỗi khi thực hiện đồng bộ. Vùi lòng chờ cho tác vụ hoàn thành hoặc chọn nhà hàng khác.");
			}
		} else {
			syncResponseDto.setStatus(false);
			syncResponseDto.setMessage("Hệ thống đang có 1 tác vụ thực hiện việc đồng bộ bộ ngay. Vùi lòng chờ cho tác vụ này hoàn thành.");
		}
		return syncResponseDto;
	}

	public void syncData(List<SyncDto> syncDtoHasStatusWattings, List<SyncDto> syncDtoHasStatusInProcessings, List<SyncDto> syncDtoHasStatusWaitForSyncNow) {
		log.info("ENTERING JOB SYNC_DATA, SYNC_DTO_HAS_STATUS_WATTINGS: {}, SYNC_DTO_HAS_STATUS_INPROCESSINGS: {}", syncDtoHasStatusWattings, syncDtoHasStatusInProcessings);
		// Tạo danh sách các CompletableFuture để lưu trữ các tác vụ xử lý nhà hàng
        List<CompletableFuture<Void>> completableFutures = new ArrayList<>();
       
        if(!CollectionUtils.isEmpty(syncDtoHasStatusInProcessings)) {
	        for (SyncDto syncDtoHasStatusInProcessing : syncDtoHasStatusInProcessings) {
	        	if(!restaurantCodeAsyncs.contains(syncDtoHasStatusInProcessing.getRestaurantCode()) && restaurantCodeAsyncs.size() < 2) {
					syncDtoHasStatusInProcessing.setSyncStartDate(LocalDateTime.now());
					syncManager.updateSync(syncDtoHasStatusInProcessing, ResultEnum.WAITING_RESULT.val);
					restaurantCodeAsyncs.add(syncDtoHasStatusInProcessing.getRestaurantCode());
					CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
		                // Xử lý đồng bộ cho từng nhà hàng
						syncRestaurantData(syncDtoHasStatusInProcessing);
		            }, taskExecutor);
		            completableFutures.add(future);
	        	}

	        }
		}
        
        if(!CollectionUtils.isEmpty(syncDtoHasStatusWattings)) {
	        for (SyncDto syncDtoHasStatusWatting : syncDtoHasStatusWattings) {
	        	if(!restaurantCodeAsyncs.contains(syncDtoHasStatusWatting.getRestaurantCode()) && restaurantCodeAsyncs.size() < 2) {
					syncDtoHasStatusWatting.setSyncStartDate(LocalDateTime.now());
					syncManager.updateSync(syncDtoHasStatusWatting, ResultEnum.WAITING_RESULT.val);
					restaurantCodeAsyncs.add(syncDtoHasStatusWatting.getRestaurantCode());
					CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
		                // Xử lý đồng bộ cho từng nhà hàng
						syncRestaurantData(syncDtoHasStatusWatting);
		            }, taskExecutor);

		            completableFutures.add(future);
	        	}
	        }
		}

		if(!CollectionUtils.isEmpty(syncDtoHasStatusWaitForSyncNow)) {
			for (SyncDto syncDto : syncDtoHasStatusWaitForSyncNow) {
				if(!restaurantCodeAsyncs.contains(syncDto.getRestaurantCode()) && restaurantcodeSyncNow.size() < 1) {
					syncDto.setSyncStartDate(LocalDateTime.now());
					syncManager.updateSync(syncDto, ResultEnum.WAITING_RESULT.val, SyncStatusEnum.WAIT_FOR_SYNC_NOW.val);
					restaurantcodeSyncNow.add(syncDto.getRestaurantCode());
					CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
						// Xử lý đồng bộ cho từng nhà hàng
						syncRestaurantData(syncDto);
					}, taskExecutor);
					completableFutures.add(future);
				}
			}
		}
    }

	@SuppressWarnings("unchecked")
	private void syncRestaurantData(SyncDto syncDto) {
    	Boolean isFinishProcess = false;
		try {
    		String typeSync = syncDto.getTypeSync();
    		String typeData = syncDto.getTypeData();
    		
    		String gatewayUrl = systemParameterManager.get(SystemParamEnum.API_GATEWAY_URL.param).getParamValue();
    		Integer restaurantCode = syncDto.getRestaurantCode();
    		
    		// Đồng bộ dữ liệu từ GMONITOR -> SERVER NHÀ HÀNG
    		if(typeSync.equals(TypeSyncEnum.SYNC_MASTER_DATA_TO_SERVER_RESTAURANT.val) ) {
    			// Lấy thông tin một số API_URL
	        	Map<String, String> urls = systemParameterManager.gets(SystemParamEnum.API_MASTER_PREFIX.param); 
	        	String checkOffOnlineRestaurantUrl = gatewayUrl + systemParameterManager.get(SystemParamEnum.API_BUSINESS_CHECK_STATUS_RESTAURANT_PATTERN.param).getParamValue();
		    	
		    	RestaurantDto restaurantDto = restaurantManager.get(restaurantCode);
				if(restaurantDto != null) {
    				// Lấy thông tin các loại master-data cần đẩy xuống
    				List<Integer> selectedReferenceDatas = new ArrayList<>();
    				if(!CollectionUtils.isEmpty(syncDto.getTmpSyncs())) {
    					selectedReferenceDatas = syncDto.getTmpSyncs().stream().map(tmpSync -> Integer.parseInt(tmpSync.getValue())).collect(Collectors.toList());
    				}
    				// Lấy danh sách JSON MASTER-DATA
    				Map<String, Object> mapData = syncRestaurantDataManager.getMaterDatas();
					if(restaurantDto.getIp() != null && restaurantDto.getPort() != null) {
						// trường hợp CÓ thông tin nhà hàng thì kiểm tra xem server của nhà hàng co online không
						if(checkOnlineRestaurant(checkOffOnlineRestaurantUrl, restaurantCode)) {
							// Gửi thông tin các item
							String errors = menuDataSyncManager.sendMasterDataToRestaurant(mapData, gatewayUrl, urls, selectedReferenceDatas, String.valueOf(restaurantCode));
							if (StringUtils.hasLength(errors)) {
								// set lại trạng thái của nhà hàng là "Đồng bộ menu thất bại"
	    						syncStatusManager.saveSyncStatusIfSyncToRestaurantServerFail(restaurantDto, TypeDataEnum.MASTER_DATA.val);
					    		// lưu lại thông tin về lịch sử đồng bộ
								isFinishProcess = syncManager.deleteSyncAndInsertSyncArchiveAfterSync(syncDto, ResultEnum.FAIL.val);
							} else {
								// set lại trạng thái của nhà hàng là "Đồng bộ menu thành công"
					    		syncStatusManager.saveSyncStatusIfSyncToRestaurantServerSucess(restaurantDto, TypeDataEnum.MASTER_DATA.val);
					    		// lưu lại thông tin về lịch sử đồng bộ
								isFinishProcess = syncManager.deleteSyncAndInsertSyncArchiveAfterSync(syncDto, null);
					    		// Lưu lại thông tin version các lần đồng bộ MASTER_DATA
			    	    		List<VersionDto> versionDtos = (List<VersionDto>) mapData.get(MapKeyEnum.VERSIONS.key);
			    	    		versionManager.saves(versionDtos);
							}
						} else {
							// trường hợp không có thông tin nhà hàng thì lưu lại vào bảng "thông tin danh sách đồng bộ"
							isFinishProcess = syncManager.deleteSyncAndInsertSyncArchiveAfterSync(syncDto, ResultEnum.RESTAURANT_OFFLINE.val);
    						// set lại trạng thái của nhà hàng là "Đồng bộ menu thất bại"
    						syncStatusManager.saveSyncStatusIfSyncToRestaurantServerFail(restaurantDto, TypeDataEnum.MASTER_DATA.val);
						}
					} else {
						isFinishProcess = syncManager.deleteSyncAndInsertSyncArchiveAfterSync(syncDto, ResultEnum.NOT_IP_OR_NOT_PORT.val);
						// set lại trạng thái của nhà hàng là "Đồng bộ menu thất bại"
						syncStatusManager.saveSyncStatusIfSyncToRestaurantServerFail(restaurantDto, TypeDataEnum.MASTER_DATA.val);
					}
				}
    		}
    		
    		// Áp dụng DANH MỤC xuống NHÀ HÀNG
    		if(typeSync.equals(TypeSyncEnum.APPLY_FROM_CATALOG_TO_RESTAURANT.val) ) {
    			switch (typeData) {
    			case "Business-co-menu-data":
    				// Xử lý: áp dụng dữ liệu CO-MENU từ DANH MỤC xuống NHÀ HÀNG
					isFinishProcess = syncDataCoMenuManager.syncCoMenuFromCatalogToRestaurant(syncDto);
    				break;
    			case "Business-so-menu-data":
    				// Xử lý: áp dụng dữ liệu SO-MENU từ DANH MỤC xuống NHÀ HÀNG
					isFinishProcess = syncDataSoMenuManager.syncSoMenuFromCatalogToRestaurant(syncDto);
    				break;
    			case "Business-param-data":
    				// Xử lý: áp dụng dữ liệu GROUP-PARAM-MENU từ DANH MỤC xuống NHÀ HÀNG
					isFinishProcess = syncDataGroupParamManager.syncGroupParamFromCatalogToRestaurant(syncDto);
    				break;
    			default:
    				// Notthing
    				break;
    			}
    		}
    		
    		// Áp dụng DANH MỤC từ NHÀ HÀNG
    		if(typeSync.equals(TypeSyncEnum.APPLY_CATALOG_FROM_RESTAURANT.val) ) {
    			switch (typeData) {
    			case "Business-co-menu-data":
    				// Xử lý: áp dụng dữ liệu CO-MENU từ NHÀ HÀNG
					isFinishProcess = syncDataCoMenuManager.applyCatalogFromRestaurant(syncDto);
    				break;
    			case "Business-so-menu-data":
    				// Xử lý: áp dụng dữ liệu SO-MENU từ NHÀ HÀNG
					isFinishProcess = syncDataSoMenuManager.applyCatalogFromRestaurant(syncDto);
    				break;
    			case "Business-param-data":
    				// Xử lý: áp dụng dữ liệu GROUP-PARAM-MENU từ NHÀ HÀNG
					isFinishProcess = syncDataGroupParamManager.applyCatalogFromRestaurant(syncDto);
    				break;
    			default:
    				// Notthing
    				break;
    			}
    		}
    		
    		// đồng bộ từ DANH MỤC xuống SERVER NHÀ HÀNG
    		if(typeSync.equals(TypeSyncEnum.SYNC_BUSINESS_DATA_FROM_CATALOG_TO_SERVER_RESTAURANT.val) ) {
    			// Lấy thông tin một số API_URL
	        	Map<String, String> urls = systemParameterManager.gets(SystemParamEnum.API_BUSINESS_PREFIX.param); 
		    	String checkOffOnlineRestaurantUrl = gatewayUrl + urls.get(SystemParamEnum.API_BUSINESS_CHECK_STATUS_RESTAURANT_PATTERN.param); 
		    	String apiUrlFile = gatewayUrl + urls.get(SystemParamEnum.API_BUSINESS_FILESAVE_PATTERN.param);
		    	String gatewayImageUrl = urls.get(SystemParamEnum.API_BUSINESS_GATEWAY_IMAGE_URL_PATTERN.param);
    			
		    	RestaurantDto restaurantDto = restaurantManager.get(restaurantCode);
				if(restaurantDto != null) {
					switch (typeData) {
	    			case "Business-co-menu-data":
	    				// Thực hiện đồng bộ thông tin xuống nhà hàng trước sau đó mới gửi thông tin đã đồng bộ từ DANH MỤC -> NHÀ HÀNG đến SERVER của NHÀ HÀNG
						RestaurantMenuDataSync restaurantCoMenuDataSync = syncDataCoMenuManager.syncDataFromCatalogToServerRestaurant(syncDto, restaurantDto.getName(), apiUrlFile, gatewayImageUrl);
						if(restaurantDto.getIp() != null && restaurantDto.getPort() != null) {
							// trường hợp CÓ thông tin nhà hàng thì kiểm tra xem server của nhà hàng co online không
							if(checkOnlineRestaurant(checkOffOnlineRestaurantUrl, restaurantCode)) {
								// Phần đồng bộ ảnh => Tạo mới bản ghi có trạng thái chờ đồng bộ
								if(!CollectionUtils.isEmpty(restaurantCoMenuDataSync.getCoFoodItemIds()) || !CollectionUtils.isEmpty(restaurantCoMenuDataSync.getCoCategoryIds())) {
									// Lưu vào bảng chờ đồng bộ
									syncManager.savedSyncFileToRestaurantServer(restaurantDto, restaurantCoMenuDataSync.getCoFoodItemIds(), restaurantCoMenuDataSync.getCoCategoryIds(), restaurantCoMenuDataSync.getFoodGroupIds());
								}
								// Gửi thông tin các item
								String errors = menuDataSyncManager.sendBusinessDataToRestaurant(restaurantCoMenuDataSync, gatewayUrl, urls);
								if (StringUtils.hasLength(errors)) {
									// set lại trạng thái của nhà hàng là "Đồng bộ menu thất bại"
		    						syncStatusManager.saveSyncStatusIfSyncToRestaurantServerFail(restaurantDto, TypeDataEnum.BUSINESS_DATA.val);
						    		// lưu lại thông tin về lịch sử đồng bộ
	        						syncManager.deleteSyncAndInsertSyncArchiveAfterSync(syncDto, ResultEnum.FAIL.val);
								} else {
									// set lại trạng thái của nhà hàng là "Đồng bộ menu thành công"
						    		syncStatusManager.saveSyncStatusIfSyncToRestaurantServerSucess(restaurantDto, TypeDataEnum.BUSINESS_DATA.val);
						    		// lưu lại thông tin về lịch sử đồng bộ
						    		isFinishProcess = syncManager.deleteSyncAndInsertSyncArchiveAfterSync(syncDto, null);
						    		// Xóa dữ liệu lưu trữ thông tin các item đã chỉnh sửa ở dưới nhà hàng nếu đồng bộ thành công
						    		restaurantDataEditManager.deleteDataCoMenuEdit(restaurantCode);
								}
							} else {
								// trường hợp không có thông tin nhà hàng thì lưu lại vào bảng "thông tin danh sách đồng bộ"
								isFinishProcess = syncManager.deleteSyncAndInsertSyncArchiveAfterSync(syncDto, ResultEnum.RESTAURANT_OFFLINE.val);
	    						// set lại trạng thái của nhà hàng là "Đồng bộ menu thất bại"
	    						syncStatusManager.saveSyncStatusIfSyncToRestaurantServerFail(restaurantDto, TypeDataEnum.BUSINESS_DATA.val);
							}
						} else {
							isFinishProcess = syncManager.deleteSyncAndInsertSyncArchiveAfterSync(syncDto, ResultEnum.NOT_IP_OR_NOT_PORT.val);
							// set lại trạng thái của nhà hàng là "Đồng bộ menu thất bại"
    						syncStatusManager.saveSyncStatusIfSyncToRestaurantServerFail(restaurantDto, TypeDataEnum.BUSINESS_DATA.val);
						}
	    				break;
	    			case "Business-so-menu-data":
	    				// Thực hiện đồng bộ thông tin xuống nhà hàng trước sau đó mới gửi thông tin đã đồng bộ từ DANH MỤC -> NHÀ HÀNG đến SERVER của NHÀ HÀNG
	    				RestaurantMenuDataSync restaurantSoMenuDataSync = syncDataSoMenuManager.syncDataFromCatalogToServerRestaurant(syncDto, apiUrlFile, gatewayImageUrl);
	    				if(restaurantDto.getIp() != null && restaurantDto.getPort() != null) {
							// trường hợp CÓ thông tin nhà hàng thì kiểm tra xem server của nhà hàng co online không
							if(checkOnlineRestaurant(checkOffOnlineRestaurantUrl, restaurantCode)) {
								// Phần đồng bộ ảnh => Tạo mới bản ghi có trạng thái chờ đồng bộ
								if(!CollectionUtils.isEmpty(restaurantSoMenuDataSync.getFoodGroupIds())) {
									// Lưu vào bảng chờ đồng bộ
									syncManager.savedSyncFileToRestaurantServer(restaurantDto, restaurantSoMenuDataSync.getCoFoodItemIds(), restaurantSoMenuDataSync.getCoCategoryIds(), restaurantSoMenuDataSync.getFoodGroupIds());
								}
								// Gửi thông tin các item
								String errors = menuDataSyncManager.sendBusinessDataToRestaurant(restaurantSoMenuDataSync, gatewayUrl, urls);
								if (StringUtils.hasLength(errors)) {
									// set lại trạng thái của nhà hàng là "Đồng bộ menu thất bại"
		    						syncStatusManager.saveSyncStatusIfSyncToRestaurantServerFail(restaurantDto, TypeDataEnum.BUSINESS_DATA.val);
						    		// lưu lại thông tin về lịch sử đồng bộ
									isFinishProcess = syncManager.deleteSyncAndInsertSyncArchiveAfterSync(syncDto, ResultEnum.FAIL.val);
								} else {
									// set lại trạng thái của nhà hàng là "Đồng bộ menu thành công"
						    		syncStatusManager.saveSyncStatusIfSyncToRestaurantServerSucess(restaurantDto, TypeDataEnum.BUSINESS_DATA.val);
						    		// lưu lại thông tin về lịch sử đồng bộ
									isFinishProcess = syncManager.deleteSyncAndInsertSyncArchiveAfterSync(syncDto, null);
						    		// Xóa dữ liệu lưu trữ thông tin các item đã chỉnh sửa ở dưới nhà hàng nếu đồng bộ thành công
						    		restaurantDataEditManager.deleteDataSoMenuEdit(restaurantCode);
								}
							} else {
								// trường hợp không có thông tin nhà hàng thì lưu lại vào bảng "thông tin danh sách đồng bộ"
								isFinishProcess = syncManager.deleteSyncAndInsertSyncArchiveAfterSync(syncDto, ResultEnum.RESTAURANT_OFFLINE.val);
	    						// set lại trạng thái của nhà hàng là "Đồng bộ menu thất bại"
	    						syncStatusManager.saveSyncStatusIfSyncToRestaurantServerFail(restaurantDto, TypeDataEnum.BUSINESS_DATA.val);
							}
						} else {
							isFinishProcess = syncManager.deleteSyncAndInsertSyncArchiveAfterSync(syncDto, ResultEnum.NOT_IP_OR_NOT_PORT.val);
							// set lại trạng thái của nhà hàng là "Đồng bộ menu thất bại"
    						syncStatusManager.saveSyncStatusIfSyncToRestaurantServerFail(restaurantDto, TypeDataEnum.BUSINESS_DATA.val);
						}
	    				break;
	    			case "Business-param-data":
	    				// Thực hiện đồng bộ thông tin xuống nhà hàng trước sau đó mới gửi thông tin đã đồng bộ từ DANH MỤC -> NHÀ HÀNG đến SERVER của NHÀ HÀNG
						RestaurantMenuDataSync restaurantGroupParamMenuDataSync = syncDataGroupParamManager.syncDataFromCatalogToServerRestaurant(syncDto);
						if(restaurantDto.getIp() != null && restaurantDto.getPort() != null) {
							// trường hợp CÓ thông tin nhà hàng thì kiểm tra xem server của nhà hàng co online không
							if(checkOnlineRestaurant(checkOffOnlineRestaurantUrl, restaurantCode)) {
								// Gửi thông tin các item
								String errors = menuDataSyncManager.sendBusinessDataToRestaurant(restaurantGroupParamMenuDataSync, gatewayUrl, urls);
								if (StringUtils.hasLength(errors)) {
									// set lại trạng thái của nhà hàng là "Đồng bộ menu thất bại"
		    						syncStatusManager.saveSyncStatusIfSyncToRestaurantServerFail(restaurantDto, TypeDataEnum.BUSINESS_DATA.val);
						    		// lưu lại thông tin về lịch sử đồng bộ
									isFinishProcess = syncManager.deleteSyncAndInsertSyncArchiveAfterSync(syncDto, ResultEnum.FAIL.val);
								} else {
									// set lại trạng thái của nhà hàng là "Đồng bộ menu thành công"
						    		syncStatusManager.saveSyncStatusIfSyncToRestaurantServerSucess(restaurantDto, TypeDataEnum.BUSINESS_DATA.val);
						    		// lưu lại thông tin về lịch sử đồng bộ
									isFinishProcess = syncManager.deleteSyncAndInsertSyncArchiveAfterSync(syncDto, null);
						    		// Xóa dữ liệu lưu trữ thông tin các item đã chỉnh sửa ở dưới nhà hàng nếu đồng bộ thành công
						    		restaurantDataEditManager.deleteDataGroupParamEdit(restaurantCode);
								}
							} else {
								// trường hợp không có thông tin nhà hàng thì lưu lại vào bảng "thông tin danh sách đồng bộ"
								isFinishProcess = syncManager.deleteSyncAndInsertSyncArchiveAfterSync(syncDto, ResultEnum.RESTAURANT_OFFLINE.val);
	    						// set lại trạng thái của nhà hàng là "Đồng bộ menu thất bại"
	    						syncStatusManager.saveSyncStatusIfSyncToRestaurantServerFail(restaurantDto, TypeDataEnum.BUSINESS_DATA.val);
							}
						} else {
							isFinishProcess = syncManager.deleteSyncAndInsertSyncArchiveAfterSync(syncDto, ResultEnum.NOT_IP_OR_NOT_PORT.val);
							// set lại trạng thái của nhà hàng là "Đồng bộ menu thất bại"
    						syncStatusManager.saveSyncStatusIfSyncToRestaurantServerFail(restaurantDto, TypeDataEnum.BUSINESS_DATA.val);
						}
	    				break;
	    			default:
	    				// Notthing
	    				break;
	    			}
				}
    		}
    		
    		// đồng bộ dữ liệu xuống SERVER NHÀ HÀNG
    		if(typeSync.equals(TypeSyncEnum.SYNC_BUSINESS_DATA_TO_SERVER_RESTAURANT.val) ) {
    			// Lấy thông tin một số API_URL
	        	Map<String, String> urls = systemParameterManager.gets(SystemParamEnum.API_BUSINESS_PREFIX.param); 
		    	String checkOffOnlineRestaurantUrl = gatewayUrl + urls.get(SystemParamEnum.API_BUSINESS_CHECK_STATUS_RESTAURANT_PATTERN.param); 
		    	String apiUrlFile = gatewayUrl + urls.get(SystemParamEnum.API_BUSINESS_FILESAVE_PATTERN.param);
		    	String gatewayImageUrl = urls.get(SystemParamEnum.API_BUSINESS_GATEWAY_IMAGE_URL_PATTERN.param);
    			
		    	RestaurantDto restaurantDto = restaurantManager.get(restaurantCode);
				if(restaurantDto != null) {
    				// Thực hiện convert dữ liệu thành json sau đó mới gửi thông tin đến SERVER của NHÀ HÀNG
					RestaurantMenuDataSync restaurantMenuDataSync = syncRestaurantDataManager.convertBusinessDataToJson(restaurantCode, syncDto.getOverride(), apiUrlFile, restaurantDto.getName(), gatewayImageUrl);
					if(restaurantDto.getIp() != null && restaurantDto.getPort() != null) {
						// trường hợp CÓ thông tin nhà hàng thì kiểm tra xem server của nhà hàng co online không
						if(checkOnlineRestaurant(checkOffOnlineRestaurantUrl, restaurantCode)) {
							// Phần đồng bộ ảnh => Tạo mới bản ghi có trạng thái chờ đồng bộ
							if(!CollectionUtils.isEmpty(restaurantMenuDataSync.getCoFoodItemIds()) || !CollectionUtils.isEmpty(restaurantMenuDataSync.getCoCategoryIds()) || !CollectionUtils.isEmpty(restaurantMenuDataSync.getFoodGroupIds())) {
								// Lưu vào bảng chờ đồng bộ
								syncManager.savedSyncFileToRestaurantServer(restaurantDto, restaurantMenuDataSync.getCoFoodItemIds(), restaurantMenuDataSync.getCoCategoryIds(), restaurantMenuDataSync.getFoodGroupIds());
							}
							
							// Gửi thông tin các item
							String errors = menuDataSyncManager.sendBusinessDataToRestaurant(restaurantMenuDataSync, gatewayUrl, urls);
							if (StringUtils.hasLength(errors)) {
								// set lại trạng thái của nhà hàng là "Đồng bộ menu thất bại"
	    						syncStatusManager.saveSyncStatusIfSyncToRestaurantServerFail(restaurantDto, TypeDataEnum.BUSINESS_DATA.val);
					    		// lưu lại thông tin về lịch sử đồng bộ
								isFinishProcess = syncManager.deleteSyncAndInsertSyncArchiveAfterSync(syncDto, ResultEnum.FAIL.val);
							} else {
								// set lại trạng thái của nhà hàng là "Đồng bộ menu thành công"
					    		syncStatusManager.saveSyncStatusIfSyncToRestaurantServerSucess(restaurantDto, TypeDataEnum.BUSINESS_DATA.val);
					    		// lưu lại thông tin về lịch sử đồng bộ
								isFinishProcess = syncManager.deleteSyncAndInsertSyncArchiveAfterSync(syncDto, null);
					    		// Xóa dữ liệu lưu trữ thông tin các item đã chỉnh sửa ở dưới nhà hàng nếu đồng bộ thành công
					    		restaurantDataEditManager.deleteDataSoMenuEdit(restaurantCode);
							}
						} else {
							// trường hợp không có thông tin nhà hàng thì lưu lại vào bảng "thông tin danh sách đồng bộ"
							isFinishProcess = syncManager.deleteSyncAndInsertSyncArchiveAfterSync(syncDto, ResultEnum.RESTAURANT_OFFLINE.val);
    						// set lại trạng thái của nhà hàng là "Đồng bộ menu thất bại"
    						syncStatusManager.saveSyncStatusIfSyncToRestaurantServerFail(restaurantDto, TypeDataEnum.BUSINESS_DATA.val);
						}
					} else {
						isFinishProcess = syncManager.deleteSyncAndInsertSyncArchiveAfterSync(syncDto, ResultEnum.NOT_IP_OR_NOT_PORT.val);
						// set lại trạng thái của nhà hàng là "Đồng bộ menu thất bại"
						syncStatusManager.saveSyncStatusIfSyncToRestaurantServerFail(restaurantDto, TypeDataEnum.BUSINESS_DATA.val);
					}
				}
    		}
    		
		} catch (Exception e) {
			log.error("ERROR SYNC_RESTAURANT_DATA EXCEPTION: {}", e);
		}
		if (isFinishProcess) {
			if (syncDto.getStatus().equals(SyncStatusEnum.WAIT_FOR_SYNC_NOW.val)) {
				restaurantcodeSyncNow.removeIf(number -> number == syncDto.getRestaurantCode());
			} else {
				restaurantCodeAsyncs.removeIf(number -> number == syncDto.getRestaurantCode());
			}
		}
    }
	
    private boolean checkOnlineRestaurant(String apiUrl, Integer resCode) {
    	log.debug("PROCESS FUNCTION: CHECK OFF/ONLINE RESTAURANT");    
    	boolean check;
    	try {
    		check = restaurantSyncManager.check(apiUrl,resCode);
		} catch (JsonProcessingException e) {
			check = false;
			e.printStackTrace();
		}
    	return check;
    }
    
    
// Off danh mục CO_MENU và gửi dữ liệu xuống NHÀ HÀNG
//	if(typeSync.equals(TypeSyncEnum.OFF_CO_MENU.val) ) {
//		// Lấy thông tin một số API_URL
//    	Map<String, String> urls = systemParameterManager.gets(SystemParamEnum.API_BUSINESS_PREFIX.param); 
//    	String checkOffOnlineRestaurantUrl = gatewayUrl + urls.get(SystemParamEnum.API_BUSINESS_CHECK_STATUS_RESTAURANT_PATTERN.param); 
//		
//    	RestaurantDto restaurantDto = restaurantManager.get(restaurantCode);
//		if(restaurantDto != null) {
//			// Thực hiện convert dữ liệu thành json sau đó mới gửi thông tin đến SERVER của NHÀ HÀNG
//			RestaurantMenuDataSync restaurantMenuDataSync = syncRestaurantDataManager.convertDataOffCoMenuToJson(restaurantCode, syncDto.getValue());
//			if(restaurantDto.getIp() != null && restaurantDto.getPort() != null) {
//				// trường hợp CÓ thông tin nhà hàng thì kiểm tra xem server của nhà hàng co online không
//				if(checkOnlineRestaurant(checkOffOnlineRestaurantUrl, restaurantCode)) {
//					// Gửi thông tin các item
//					String errors = menuDataSyncManager.sendBusinessDataToRestaurant(restaurantMenuDataSync, gatewayUrl, urls);
//					if (StringUtils.hasLength(errors)) {
//						// set lại trạng thái của nhà hàng là "Đồng bộ menu thất bại"
//						syncStatusManager.saveSyncStatusIfSyncToRestaurantServerFail(restaurantDto, TypeDataEnum.BUSINESS_DATA.val);
//			    		// lưu lại thông tin về lịch sử đồng bộ
//						syncManager.deleteSyncAndInsertSyncArchiveAfterSync(syncDto, ResultEnum.FAIL.val);
//					} else {
//						// set lại trạng thái của nhà hàng là "Đồng bộ menu thành công"
//			    		syncStatusManager.saveSyncStatusIfSyncToRestaurantServerSucess(restaurantDto, TypeDataEnum.BUSINESS_DATA.val);
//			    		// lưu lại thông tin về lịch sử đồng bộ
//			    		syncManager.deleteSyncAndInsertSyncArchiveAfterSync(syncDto, null);
//					}
//				} else {
//					// trường hợp không có thông tin nhà hàng thì lưu lại vào bảng "thông tin danh sách đồng bộ"
//					syncManager.deleteSyncAndInsertSyncArchiveAfterSync(syncDto, ResultEnum.RESTAURANT_OFFLINE.val);
//					// set lại trạng thái của nhà hàng là "Đồng bộ menu thất bại"
//					syncStatusManager.saveSyncStatusIfSyncToRestaurantServerFail(restaurantDto, TypeDataEnum.BUSINESS_DATA.val);
//				}
//			} else {
//				syncManager.deleteSyncAndInsertSyncArchiveAfterSync(syncDto, ResultEnum.NOT_IP_OR_NOT_PORT.val);
//				// set lại trạng thái của nhà hàng là "Đồng bộ menu thất bại"
//				syncStatusManager.saveSyncStatusIfSyncToRestaurantServerFail(restaurantDto, TypeDataEnum.BUSINESS_DATA.val);
//			}
//		}
//	}
//	
//	// Off danh mục SO_MENU và gửi dữ liệu xuống NHÀ HÀNG
//	if(typeSync.equals(TypeSyncEnum.OFF_SO_MENU.val) ) {
//		// Lấy thông tin một số API_URL
//    	Map<String, String> urls = systemParameterManager.gets(SystemParamEnum.API_BUSINESS_PREFIX.param); 
//    	String checkOffOnlineRestaurantUrl = gatewayUrl + urls.get(SystemParamEnum.API_BUSINESS_CHECK_STATUS_RESTAURANT_PATTERN.param); 
//		
//    	RestaurantDto restaurantDto = restaurantManager.get(restaurantCode);
//		if(restaurantDto != null) {
//			// Thực hiện convert dữ liệu thành json sau đó mới gửi thông tin đến SERVER của NHÀ HÀNG
//			RestaurantMenuDataSync restaurantMenuDataSync = syncRestaurantDataManager.convertDataOffSoMenuToJson(restaurantCode, syncDto.getValue());
//			if(restaurantDto.getIp() != null && restaurantDto.getPort() != null) {
//				// trường hợp CÓ thông tin nhà hàng thì kiểm tra xem server của nhà hàng co online không
//				if(checkOnlineRestaurant(checkOffOnlineRestaurantUrl, restaurantCode)) {
//					// Gửi thông tin các item
//					String errors = menuDataSyncManager.sendBusinessDataToRestaurant(restaurantMenuDataSync, gatewayUrl, urls);
//					if (StringUtils.hasLength(errors)) {
//						// set lại trạng thái của nhà hàng là "Đồng bộ menu thất bại"
//						syncStatusManager.saveSyncStatusIfSyncToRestaurantServerFail(restaurantDto, TypeDataEnum.BUSINESS_DATA.val);
//			    		// lưu lại thông tin về lịch sử đồng bộ
//						syncManager.deleteSyncAndInsertSyncArchiveAfterSync(syncDto, ResultEnum.FAIL.val);
//					} else {
//						// set lại trạng thái của nhà hàng là "Đồng bộ menu thành công"
//			    		syncStatusManager.saveSyncStatusIfSyncToRestaurantServerSucess(restaurantDto, TypeDataEnum.BUSINESS_DATA.val);
//			    		// lưu lại thông tin về lịch sử đồng bộ
//			    		syncManager.deleteSyncAndInsertSyncArchiveAfterSync(syncDto, null);
//					}
//				} else {
//					// trường hợp không có thông tin nhà hàng thì lưu lại vào bảng "thông tin danh sách đồng bộ"
//					syncManager.deleteSyncAndInsertSyncArchiveAfterSync(syncDto, ResultEnum.RESTAURANT_OFFLINE.val);
//					// set lại trạng thái của nhà hàng là "Đồng bộ menu thất bại"
//					syncStatusManager.saveSyncStatusIfSyncToRestaurantServerFail(restaurantDto, TypeDataEnum.BUSINESS_DATA.val);
//				}
//			} else {
//				syncManager.deleteSyncAndInsertSyncArchiveAfterSync(syncDto, ResultEnum.NOT_IP_OR_NOT_PORT.val);
//				// set lại trạng thái của nhà hàng là "Đồng bộ menu thất bại"
//				syncStatusManager.saveSyncStatusIfSyncToRestaurantServerFail(restaurantDto, TypeDataEnum.BUSINESS_DATA.val);
//			}
//		}
//	}
}