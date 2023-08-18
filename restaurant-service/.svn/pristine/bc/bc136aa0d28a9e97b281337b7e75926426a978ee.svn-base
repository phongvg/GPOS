package com.gg.gpos.res.manager;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.gg.gpos.common.constant.StatusSyncEnum;
import com.gg.gpos.common.constant.SymbolEnum;
import com.gg.gpos.common.constant.TypeDataEnum;
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
public class SyncStatusManager {
	
	@Autowired
	private SyncStatusRepository syncStatusRepository;
	@Autowired
	private RestaurantRepository restaurantRepository;
	@Autowired
	private RestaurantMapper restaurantMapper;
	
	public void changeSttAfterSync(){
		log.info("Entering to changeSttAfterSync() method... ");
		List<Restaurant> restaurants = restaurantRepository.findAll();
		if(!restaurants.isEmpty()) {
			restaurants.stream().forEach(res ->{
				SyncStatus syncStatus = syncStatusRepository.findByRestaur(res);
				if(syncStatus == null) {
					syncStatus = new SyncStatus();
					syncStatus.setRestaur(res);
				}
				syncStatus.setMasterDataSyncStatus(StatusSyncEnum.NOT_SYNC.val);
				syncStatusRepository.save(syncStatus);
			});
		}
	}
	
	public void saveSttAfterApplyMenu(String codes, List<Integer> resCodes, Integer code) {
		log.info("Entering to 'saveSttAfterApplyMenu(String codes, List<Integer> resCodes, Integer code)' method... ");
		if(code != null) {
    		SyncStatus syncStatus = syncStatusRepository.findByRestaur_code(code);
			if(syncStatus == null) {
				syncStatus = new SyncStatus();
				Restaurant restaurant = restaurantRepository.findByCode(code);
				syncStatus.setRestaur(restaurant);
			}
			syncStatus.setBusinessSyncStatus(StatusSyncEnum.NOT_SYNC.val);
			syncStatusRepository.save(syncStatus);
    	}else if(codes != null && !codes.isEmpty()) {
    		Set<String> keys = StringUtils.commaDelimitedListToSet(codes);
        	List<String> filterKey = keys.stream().filter(key -> {
        		return !key.contains(SymbolEnum.UNDERSCORE.val);
        	}).collect(Collectors.toList());
        	
        	filterKey.stream().forEach(resCode ->{
        		SyncStatus syncStatus = syncStatusRepository.findByRestaur_code(Integer.parseInt(resCode));
    			if(syncStatus == null) {
    				syncStatus = new SyncStatus();
    				Restaurant restaurant = restaurantRepository.findByCode(code);
    				syncStatus.setRestaur(restaurant);
    			}
    			syncStatus.setBusinessSyncStatus(StatusSyncEnum.NOT_SYNC.val);
    			syncStatusRepository.save(syncStatus);
        	});
    	}else if(resCodes != null && !resCodes.isEmpty()) {
    		resCodes.stream().forEach(resCode ->{
        		SyncStatus syncStatus = syncStatusRepository.findByRestaur_code(resCode);
    			if(syncStatus == null) {
    				syncStatus = new SyncStatus();
    				Restaurant restaurant = restaurantRepository.findByCode(code);
    				syncStatus.setRestaur(restaurant);
    			}
    			syncStatus.setBusinessSyncStatus(StatusSyncEnum.NOT_SYNC.val);
    			syncStatusRepository.save(syncStatus);
        	});
    	}
	}

	public void saveSttAfterEditDataBusiness(Integer code) {
		SyncStatus syncStatus = syncStatusRepository.findByRestaur_code(code);
		if(syncStatus == null) {
			syncStatus = new SyncStatus();
			Restaurant restaurant = restaurantRepository.findByCode(code);
			syncStatus.setRestaur(restaurant);
		}
		syncStatus.setBusinessSyncStatus(StatusSyncEnum.NOT_SYNC.val);
		syncStatusRepository.save(syncStatus);
	}
	
	/*
	 * Set lại trạng thái của nhà hàng là "Đồng bộ dữ liệu thất bại"
	 */
	public void saveSyncStatusIfSyncToRestaurantServerFail(RestaurantDto restaurantDto, String type) {
		log.debug("PROCESS: SAVE SYNC_STATUS WITH STATUS FAIL");
		try {
			Restaurant restaurant = Optional.ofNullable(restaurantDto).map(restaurantMapper::dtoToEntity).orElse(null);
			SyncStatus syncStatus = syncStatusRepository.findByRestaur(restaurant);
			if(syncStatus == null) {
				syncStatus = new SyncStatus();
				syncStatus.setRestaur(restaurant);
			}
			if(type.equals(TypeDataEnum.BUSINESS_DATA.val)) {
				syncStatus.setBusinessSyncStatus(StatusSyncEnum.SYNC_FAIL.val);
			} else {
				syncStatus.setMasterDataSyncStatus(StatusSyncEnum.SYNC_FAIL.val);
			}
			syncStatusRepository.save(syncStatus);
		} catch (Exception e) {
			log.error("ERROR PROCESS: SAVE SYNC_STATUS WITH STATUS FAIL EXCEPTION: {}", e);
		}
	}
	
	/*
	 * Set lại trạng thái của nhà hàng là "Đồng bộ dữ liệu thành công"
	 */
	public void saveSyncStatusIfSyncToRestaurantServerSucess(RestaurantDto restaurantDto, String type) {
		log.debug("PROCESS: SAVE SYNC_STATUS WITH STATUS SUCCESS");
		try {
			Restaurant restaurant = Optional.ofNullable(restaurantDto).map(restaurantMapper::dtoToEntity).orElse(null);
			SyncStatus syncStatus = syncStatusRepository.findByRestaur(restaurant);
			if(syncStatus == null) {
				syncStatus = new SyncStatus();
				syncStatus.setRestaur(restaurant);
			}
			if(type.equals(TypeDataEnum.BUSINESS_DATA.val)) {
				syncStatus.setBusinessSyncStatus(StatusSyncEnum.SYNC_SUCCCESS.val);
			} else {
				syncStatus.setMasterDataSyncStatus(StatusSyncEnum.SYNC_SUCCCESS.val);
			}
			syncStatusRepository.save(syncStatus);
		} catch (Exception e) {
			log.error("ERROR PROCESS: SAVE SYNC_STATUS WITH STATUS SUCCESS EXCEPTION: {}", e);
		}
	}
}
