package com.gg.gpos.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import com.gg.gpos.common.constant.ModuleTypeEnum;
import com.gg.gpos.controller.BaseController;
import com.gg.gpos.reference.manager.AttachmentManager;
import com.gg.gpos.reference.manager.SystemParameterManager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableScheduling
@Controller
public class SchedulerController extends BaseController {
	@Autowired
	private AttachmentManager attachmentManager;
	@Autowired
	private SystemParameterManager systemParameterManager;
	
	/*
	 * Job tìm và xóa các bản ghi attachment và file ảnh sau khi đồng bộ từ danh mục xuống nhà hàng
	 */
	@Scheduled(cron = "${scheduled.cron.expression}")
    private void schedulingDeleteAttachment() {
    	log.info("ENTERING 'SCHEDULED DELETE ATTACHMENT' METHOD ...");    
    	attachmentManager.delAttAfterSync(ModuleTypeEnum.DEL_ATTACHMENT.val);
    }
	
	/*
	 * Job check trạng thái của server nhà hàng online hay offline => chạy 2h/1 lần
	 */
//	@Scheduled(fixedRateString = "${scheduled.fixed.rate}")
//	private void schedulingCheckOnlineRestaurant() {
//		log.info("ENTERING 'SCHEDULED CHECK ONLINE RESTAURANT' METHOD ...");    
//		SystemParameterDto gatewayUrl = systemParameterManager.get(SystemParamEnum.API_GATEWAY_URL.param);
//		SystemParameterDto url = systemParameterManager.get(SystemParamEnum.API_CHECK_STATUS_RESTAURANT.param);
//		String apiUrl = gatewayUrl.getParamValue() + url.getParamValue();
//		List<RestaurantDto> restaurantDtos = restaurantManager.gets();
//  		if(restaurantDtos != null && !restaurantDtos.isEmpty()) {
//	  		restaurantDtos.stream().forEach(restaurant ->{
//	  			if(restaurant.getIp() != null && !restaurant.getIp().isEmpty() && restaurant.getPort() != null && !restaurant.getPort().isEmpty()) {
//	  				try {
//	  					restaurant = restaurantSyncManager.check(apiUrl, restaurant.getCode(),restaurant);
//						} catch (JsonProcessingException e) {
//							restaurant.setOnline(StatusEnum.INACTIVE.status);
//						}
//	  			}else {
//	  				restaurant.setOnline(StatusEnum.INACTIVE.status);
//	  			}
//	  			restaurantManager.save(restaurant);
//	  		});
//  		}
//	}
}
