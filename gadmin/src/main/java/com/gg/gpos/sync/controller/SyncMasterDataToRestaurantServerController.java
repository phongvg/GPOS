package com.gg.gpos.sync.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.jms.JMSException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;

import com.gg.gpos.common.constant.SymbolEnum;
import com.gg.gpos.controller.BaseController;
import com.gg.gpos.integration.manager.SyncManager;
import com.gg.gpos.res.dto.RestaurantDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SyncMasterDataToRestaurantServerController extends BaseController {
    @Autowired
	private SyncManager syncManager;
    
    /*
     * Đồng bộ DỮ LIỆU MASTER_DATA từ NHÀ HÀNG xuống SERVER NHÀ HÀNG
     * Có thể chọn nhiều nhà hàng để đồng bộ
     */
    @PostMapping("all/sync-master-to-res")
	public String syncMasterDataToRestaurantsServer(@Valid RestaurantDto restaurantDto, HttpServletRequest request) throws JMSException {
		log.info("ENTERING 'SYNC MASTER_DATA TO RESTAURANTS SERVER' METHOD...");   
		
		// Lấy danh sách nhà hàng đã chọn để đồng bộ
		List<RestaurantDto> restaurantDtos = new ArrayList<>();
		// TRƯỜNG HỢP ĐỒNG BỘ CÁC NHÀ HÀNG CÓ TRẠNG THÁI "ĐỒNG BỘ MASTER DATA" BỊ LỖI
		if(restaurantDto.isCheckSyncMasterData()) {
			restaurantDtos.addAll(restaurantManager.getsRestaurantDtoWithSyncStatusFailOrNotSync());
		} else {
			// TRƯỜNG HỢP NGƯỜI DÙNG CHỌN CÁC NHÀ HÀNG VÀ CÁC LOẠI MASTER DATA CẦN THIẾT ĐỂ ĐẨY XUỐNG
			Set<String> keys = StringUtils.commaDelimitedListToSet(restaurantDto.getSelectedRestaurantCodes());
        	// Danh sách các mã nhà hàng được chọn để đồng bộ dữ liệu
    		List<String> resCodes = keys.stream().filter(key -> {
        		return !key.contains(SymbolEnum.UNDERSCORE.val);
        	}).collect(Collectors.toList());
    		restaurantDtos.addAll(restaurantManager.getRestaurantByRestaurantCodes(resCodes));
		}
		syncManager.savedSyncMasterData(restaurantDtos, restaurantDto.getReferenceDatas());
    	addMessage(request, getText("apply.to.restaurant.sucess", request.getLocale()));
    	return "redirect:/all/sync-to-res";
	}
}
