package com.gg.gpos.integration.manager;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gg.gpos.common.constant.HeaderEnum;
import com.gg.gpos.common.constant.MapKeyEnum;
import com.gg.gpos.common.constant.ReferenceDataEnum;
import com.gg.gpos.common.constant.SymbolEnum;
import com.gg.gpos.common.constant.SystemParamEnum;
import com.gg.gpos.common.exception.SystemException;
import com.gg.gpos.common.json.ConfigLogoutJson;
import com.gg.gpos.common.json.MasterDataSync;
import com.gg.gpos.common.json.RestaurantMenuDataSync;
import com.gg.gpos.common.json.SyncResponse;
import com.gg.gpos.common.json.Views;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MenuDataSyncManager {

	public String sendBusinessDataToRestaurant(final RestaurantMenuDataSync restaurantMenuDataSync, final String gatewayUrl, final Map<String, String> urls) {
		log.info("PROCESS: SEND BUSINESS DATA TO RESTAURANT");
		
		String apiMenuEngineeringUrl = gatewayUrl + urls.get(SystemParamEnum.API_BUSINESS_MENUENGINEERING_PATTERN.param);
		String apiMenuGroupUrl = gatewayUrl + urls.get(SystemParamEnum.API_BUSINESS_MENUGROUP_PATTERN.param);
		String apiCoCategoryUrl = gatewayUrl + urls.get(SystemParamEnum.API_BUSINESS_COCATEGORY_PATTERN.param);
		String apiCoMenuItemUrl = gatewayUrl + urls.get(SystemParamEnum.API_BUSINESS_COMENUITEM_PATTERN.param);
		String apiCoModifierUrl = gatewayUrl + urls.get(SystemParamEnum.API_BUSINESS_COMODIFIER_PATTER.param);
		String apiSpecialUrl = gatewayUrl + urls.get(SystemParamEnum.API_BUSINESS_COSPECIALTYPE_PATTERN.param);
		String apiPrintGroupUrl = gatewayUrl + urls.get(SystemParamEnum.API_BUSINESS_PRINTGROUP_PATTERN.param);
		String apiPlaceUrl = gatewayUrl + urls.get(SystemParamEnum.API_BUSINESS_PLACE_PATTERN.param);
		String apiHallplanUrl = gatewayUrl + urls.get(SystemParamEnum.API_BUSINESS_HALLPLAN_PATTERN.param);
		String apiParamUrl = gatewayUrl + urls.get(SystemParamEnum.API_BUSINESS_APPPARAM_PATTERN.param);
		String apiKaloGroupUrl = gatewayUrl + urls.get(SystemParamEnum.API_BUSINESS_KALO_GROUP_PATTERN.param);
		String apiDeleteCoMenuItemUrl = gatewayUrl + urls.get(SystemParamEnum.API_BUSINESS_DELETE_COMENUITEM_PATTERN.param);
		String apiDeleteCoCategoryUrl = gatewayUrl + urls.get(SystemParamEnum.API_BUSINESS_DELETE_COCATEGORY_PATTERN.param);
		String apiDeleteMenuengineeringUrl = gatewayUrl + urls.get(SystemParamEnum.API_BUSINESS_DELETE_MENUENGINEERING_PATTERN.param);
		String apiConfigQrOrderUrl = gatewayUrl + urls.get(SystemParamEnum.API_BUSINESS_RESTAURANT_INFO_PATTERN.param);
		
		
		String restaurantCode = restaurantMenuDataSync.getResCode();
		
		ObjectMapper mapper = new ObjectMapper();
		StringBuilder builder = new StringBuilder();
		
		// Gửi thông tin SO_CATEGORY
		if(builder.length() == 0 && !CollectionUtils.isEmpty(restaurantMenuDataSync.getMenuEngineerings())) {
			restaurantMenuDataSync.getMenuEngineerings().stream().forEach(item -> {
				try {
					String jsonData = mapper.writerWithView(Views.OrderCategory.class).writeValueAsString(item);
					ResponseEntity<SyncResponse> response = sendData(apiMenuEngineeringUrl, jsonData, restaurantCode);
					SyncResponse syncResponse = response.getBody();
					if (!String.valueOf(HttpStatus.OK.value()).equals(syncResponse.getCode())) {
						builder.append(syncResponse.getMess()).append(SymbolEnum.DOT.val);
					}
				} catch (JsonProcessingException | SystemException e) {
					builder.append(e.getMessage()).append(SymbolEnum.DOT.val);
					log.error("ERROR SENDING SO_CATEGORY EXCEPTION: {}", e);
				}
			});
		}
		
		// Gửi thông tin FOOD_GROUP
		if(builder.length() == 0 && !CollectionUtils.isEmpty(restaurantMenuDataSync.getMenuGroups())) {
			restaurantMenuDataSync.getMenuGroups().stream().forEach(item -> {
				try {
					String jsonData = mapper.writerWithView(Views.MenuGroup.class).writeValueAsString(item);
					ResponseEntity<SyncResponse> response = sendData(apiMenuGroupUrl, jsonData, restaurantCode);
					SyncResponse syncResponse = response.getBody();
					if (!String.valueOf(HttpStatus.OK.value()).equals(syncResponse.getCode())) {
						builder.append(syncResponse.getMess()).append(SymbolEnum.DOT.val);
					}
				} catch (JsonProcessingException | SystemException e) {
					builder.append(e.getMessage()).append(SymbolEnum.DOT.val);
					log.error("ERROR SENDING FOOD_GROUP EXCEPTION: {}", e);
				}
			});
		}
		
		// Gửi thông tin các nhóm SO_CATEGORY đã bị xóa
		if(builder.length() == 0 && !CollectionUtils.isEmpty(restaurantMenuDataSync.getDeleteSoCategoryItems())) {
			restaurantMenuDataSync.getDeleteSoCategoryItems().stream().forEach(item -> {
				try {
					String jsonData = mapper.writeValueAsString(item);
					ResponseEntity<SyncResponse> response = sendData(apiDeleteMenuengineeringUrl, jsonData, restaurantCode);
					SyncResponse syncResponse = response.getBody();
					if (!String.valueOf(HttpStatus.OK.value()).equals(syncResponse.getCode())) {
						builder.append(syncResponse.getMess()).append(SymbolEnum.DOT.val);
					}
				} catch (JsonProcessingException | SystemException e) {
					builder.append(e.getMessage()).append(SymbolEnum.DOT.val);
					log.error("ERROR SENDING SO_CATEGORY_DELETED EXCEPTION: {}", e);
				}
			});
		}
		
		// Gửi thông tin các nhóm CO_CATEGORY 
		if(builder.length() == 0 && !CollectionUtils.isEmpty(restaurantMenuDataSync.getCoCategories())) {
			restaurantMenuDataSync.getCoCategories().stream().forEach(item -> {
				try {
					String jsonData = mapper.writeValueAsString(item);
					ResponseEntity<SyncResponse> response = sendData(apiCoCategoryUrl, jsonData, restaurantCode);
					SyncResponse syncResponse = response.getBody();
					if (!String.valueOf(HttpStatus.OK.value()).equals(syncResponse.getCode())) {
						builder.append(syncResponse.getMess()).append(SymbolEnum.DOT.val);
					}
				} catch (JsonProcessingException | SystemException e) {
					builder.append(e.getMessage()).append(SymbolEnum.DOT.val);
					log.error("ERROR SENDING CO_CATEGORY EXCEPTION: {}", e);
				}
			});
		}
		
		// Gửi thông tin các nhóm CO_CATEGORY đã bị xóa
		if(builder.length() == 0 && !CollectionUtils.isEmpty(restaurantMenuDataSync.getDeleteCoCategories())) {
			restaurantMenuDataSync.getDeleteCoCategories().stream().forEach(item -> {
				try {
					String jsonData = mapper.writeValueAsString(item);
					ResponseEntity<SyncResponse> response = sendData(apiDeleteCoCategoryUrl, jsonData, restaurantCode);
					SyncResponse syncResponse = response.getBody();
					if (!String.valueOf(HttpStatus.OK.value()).equals(syncResponse.getCode())) {
						builder.append(syncResponse.getMess()).append(SymbolEnum.DOT.val);
					}
				} catch (JsonProcessingException | SystemException e) {
					builder.append(e.getMessage()).append(SymbolEnum.DOT.val);
					log.error("ERROR SENDING CO_CATEGORY_DELETED EXCEPTION: {}", e);
				}
			});
		}
		
		// Gửi thông tin các nhóm CO_FOOD_ITEM
		if(builder.length() == 0 && !CollectionUtils.isEmpty(restaurantMenuDataSync.getCoMenuItems())) {
			restaurantMenuDataSync.getCoMenuItems().stream().forEach(item -> {
				try {
					String jsonData = mapper.writeValueAsString(item);
					ResponseEntity<SyncResponse> response = sendData(apiCoMenuItemUrl, jsonData, restaurantCode);
					SyncResponse syncResponse = response.getBody();
					if (!String.valueOf(HttpStatus.OK.value()).equals(syncResponse.getCode())) {
						builder.append(syncResponse.getMess()).append(SymbolEnum.DOT.val);
					}
				} catch (JsonProcessingException | SystemException e) {
					builder.append(e.getMessage()).append(SymbolEnum.DOT.val);
					log.error("ERROR SENDING CO_FOOD_ITEM EXCEPTION: {}", e);
				}
			});
		}
		
		// Gửi thông tin các nhóm CO_FOOD_ITEM đã bị xóa
		if(builder.length() == 0 && !CollectionUtils.isEmpty(restaurantMenuDataSync.getDeleteCOMenuItems())) {
			restaurantMenuDataSync.getDeleteCOMenuItems().stream().forEach(item -> {
				try {
					String jsonData = mapper.writeValueAsString(item);
					ResponseEntity<SyncResponse> response = sendData(apiDeleteCoMenuItemUrl, jsonData, restaurantCode);
					SyncResponse syncResponse = response.getBody();
					if (!String.valueOf(HttpStatus.OK.value()).equals(syncResponse.getCode())) {
						builder.append(syncResponse.getMess()).append(SymbolEnum.DOT.val);
					}
				} catch (JsonProcessingException | SystemException e) {
					builder.append(e.getMessage()).append(SymbolEnum.DOT.val);
					log.error("ERROR SENDING CO_FOOD_ITEM_DELETED EXCEPTION: {}", e);
				}
			});
		}
		
		// Gửi thông tin các nhóm MODIFIER
		if(builder.length() == 0 && !CollectionUtils.isEmpty(restaurantMenuDataSync.getCoModifiers())) {
			restaurantMenuDataSync.getCoModifiers().stream().forEach(cm -> {
				try {
					String jsonData = mapper.writeValueAsString(cm);
					ResponseEntity<SyncResponse> response = sendData(apiCoModifierUrl, jsonData, restaurantCode);
					SyncResponse syncResponse = response.getBody();
					if (!String.valueOf(HttpStatus.OK.value()).equals(syncResponse.getCode())) {
						builder.append(syncResponse.getMess()).append(SymbolEnum.DOT.val);
					}
				} catch (JsonProcessingException | SystemException e) {
					builder.append(e.getMessage()).append(SymbolEnum.DOT.val);
					log.error("ERROR SENDING MODIFIER EXCEPTION: {}", e);
				}
			});
		}
		
		// Gửi thông tin các nhóm FEATURE
		if(builder.length() == 0 && !CollectionUtils.isEmpty(restaurantMenuDataSync.getCoSpecials())) {
			restaurantMenuDataSync.getCoSpecials().stream().forEach(cs -> {
				try {
					String jsonData = mapper.writeValueAsString(cs);
					ResponseEntity<SyncResponse> response = sendData(apiSpecialUrl, jsonData, restaurantCode);
					SyncResponse syncResponse = response.getBody();
					if (!String.valueOf(HttpStatus.OK.value()).equals(syncResponse.getCode())) {
						builder.append(syncResponse.getMess()).append(SymbolEnum.DOT.val);
					}
				} catch (JsonProcessingException | SystemException e) {
					builder.append(e.getMessage()).append(SymbolEnum.DOT.val);
					log.error("ERROR SENDING FEATURE EXCEPTION: {}", e);
				}
			});
		}
		
		// Gửi thông tin các nhóm KALO_GROUP
		if(builder.length() == 0 && restaurantMenuDataSync.getJsonKaloGroup() != null) {
			try {
				String jsonData = mapper.writeValueAsString(restaurantMenuDataSync.getJsonKaloGroup());
				ResponseEntity<SyncResponse> response = sendData(apiKaloGroupUrl, jsonData, restaurantCode);
				SyncResponse syncResponse = response.getBody();
				if (!String.valueOf(HttpStatus.OK.value()).equals(syncResponse.getCode())) {
					builder.append(syncResponse.getMess()).append(SymbolEnum.DOT.val);
				}
			} catch (JsonProcessingException | SystemException e) {
				builder.append(e.getMessage()).append(SymbolEnum.DOT.val);
				log.error("ERROR SENDING KALO_GROUP EXCEPTION: {}", e);
			}
		}
		
		// Gửi thông tin CONFIG_QR_ORDER
		if(builder.length() == 0 && restaurantMenuDataSync.getConfigRestaurantInfo() != null) {
			try {
				String jsonData = mapper.writeValueAsString(restaurantMenuDataSync.getConfigRestaurantInfo());
				ResponseEntity<SyncResponse> response = sendData(apiConfigQrOrderUrl, jsonData, restaurantCode);
				SyncResponse syncResponse = response.getBody();
				if (!String.valueOf(HttpStatus.OK.value()).equals(syncResponse.getCode())) {
					builder.append(syncResponse.getMess()).append(SymbolEnum.DOT.val);
				}
			} catch (JsonProcessingException | SystemException e) {
				builder.append(e.getMessage()).append(SymbolEnum.DOT.val);
				log.error("ERROR SENDING CONFIG_QR_ORDER EXCEPTION: {}", e);
			}
		}
		
		// Gửi thông tin về PARAM
		if(builder.length() == 0 && !CollectionUtils.isEmpty(restaurantMenuDataSync.getConfigParams())) {
			restaurantMenuDataSync.getConfigParams().stream().forEach(pr -> {
				try {
					String jsonData = mapper.writeValueAsString(pr);
					ResponseEntity<SyncResponse> response = sendData(apiParamUrl, jsonData, restaurantCode);
					SyncResponse syncResponse = response.getBody();
					if (!String.valueOf(HttpStatus.OK.value()).equals(syncResponse.getCode())) {
						builder.append(syncResponse.getMess()).append(SymbolEnum.DOT.val);
					}
				} catch (JsonProcessingException | SystemException e) {
					builder.append(e.getMessage()).append(SymbolEnum.DOT.val);
					log.error("ERROR SENDING CONFIG_PARAMS EXCEPTION: {}", e);
				}
			});
		}
		
		// Gửi thông tin về PRINT_GROUP
		if(builder.length() == 0 && !CollectionUtils.isEmpty(restaurantMenuDataSync.getPrintGroups())) {
			restaurantMenuDataSync.getPrintGroups().stream().forEach(pg -> {
				try {
					String jsonData = mapper.writeValueAsString(pg);
					ResponseEntity<SyncResponse> response = sendData(apiPrintGroupUrl, jsonData, restaurantCode);
					SyncResponse syncResponse = response.getBody();
					if (!String.valueOf(HttpStatus.OK.value()).equals(syncResponse.getCode())) {
						builder.append(syncResponse.getMess()).append(SymbolEnum.DOT.val);
					}
				} catch (JsonProcessingException | SystemException e) {
					builder.append(e.getMessage()).append(SymbolEnum.DOT.val);
					log.error("ERROR SENDING PRINT_GROUP EXCEPTION: {}", e);
				}
			});
		}
		
		// Gửi thông tin về PLACE
		if(builder.length() == 0 && !CollectionUtils.isEmpty(restaurantMenuDataSync.getPlaces())) {
			restaurantMenuDataSync.getPlaces().stream().forEach(p -> {
				try {
					String jsonData = mapper.writeValueAsString(p);
					ResponseEntity<SyncResponse> response = sendData(apiPlaceUrl, jsonData, restaurantCode);
					SyncResponse syncResponse = response.getBody();
					if (!String.valueOf(HttpStatus.OK.value()).equals(syncResponse.getCode())) {
						builder.append(syncResponse.getMess()).append(SymbolEnum.DOT.val);
					}
				} catch (JsonProcessingException | SystemException e) {
					builder.append(e.getMessage()).append(SymbolEnum.DOT.val);
					log.error("ERROR SENDING PLACE PLACE: {}", e);
				}
			});
		}
		
		// Gửi thông tin về HALL_PLAN
		if(builder.length() == 0 && !CollectionUtils.isEmpty(restaurantMenuDataSync.getHallplans())) {
			restaurantMenuDataSync.getHallplans().stream().forEach(h -> {
				try {
					String jsonData = mapper.writeValueAsString(h);
					ResponseEntity<SyncResponse> response = sendData(apiHallplanUrl, jsonData, restaurantCode);
					SyncResponse syncResponse = response.getBody();
					if (!String.valueOf(HttpStatus.OK.value()).equals(syncResponse.getCode())) {
						builder.append(syncResponse.getMess()).append(SymbolEnum.DOT.val);
					}
				} catch (JsonProcessingException | SystemException e) {
					builder.append(e.getMessage()).append(SymbolEnum.DOT.val);
					log.error("ERROR SENDING HALL_PLAN PLACE: {}", e);
				}
			});
		}
		return builder.toString();
	}
	
	/*
	 * Gửi thông tin phần unlock user
	 */
	public String sendDataLogoutUserToRestaurant(final ConfigLogoutJson configLogoutJson, final String url, final Integer restaurantCode) {
		log.info("PROCESS: SEND USER_CODE DATA TO RESTAURANT");
		ObjectMapper mapper = new ObjectMapper();
		StringBuilder builder = new StringBuilder();
		if(configLogoutJson != null) {
			try {
				String jsonData = mapper.writeValueAsString(configLogoutJson);
				ResponseEntity<SyncResponse> response = sendData(url, jsonData, String.valueOf(restaurantCode));
				SyncResponse syncResponse = response.getBody();
				if (!String.valueOf(HttpStatus.OK.value()).equals(syncResponse.getCode())) {
					return syncResponse.getMess();
				}
			} catch (JsonProcessingException | SystemException e) {
				builder.append(e.getMessage());
				log.error("ERROR PROCESS: SEND USER_CODE DATA TO RESTAURANT EXCEPTION: {}", e);
			}
		}
		return builder.toString();
	}
	
	public String sendMasterDataToRestaurant(final Map<String, Object> mapData, final String gatewayUrl, final Map<String, String> urls, final List<Integer> selectedReferenceDatas, String restaurantCode) {
		log.info("PROCESS: SEND MASTER_DATA TO RESTAURANT");
		ObjectMapper mapper = new ObjectMapper();
		StringBuilder builder = new StringBuilder();
		try {
			String apiMenuItemUrl = gatewayUrl + urls.get(SystemParamEnum.API_MASTER_MENUITEM_PATTERN.param);
			String apiCategoryUrl = gatewayUrl + urls.get(SystemParamEnum.API_MASTER_CATEGLIST_PATTERN.param);
			String apiOrderTypeUrl = gatewayUrl + urls.get(SystemParamEnum.API_MASTER_ORDERTYPE_PATTERN.param);
			String apiOrderCategoryUrl = gatewayUrl + urls.get(SystemParamEnum.API_MASTER_CATEGORY_PATTERN.param);
			String apiModifierUrl = gatewayUrl + urls.get(SystemParamEnum.API_MASTER_MODIFIER_PATTERN.param);
			String apiModiGroupsUrl = gatewayUrl + urls.get(SystemParamEnum.API_MASTER_MODIGROUPS_PATTERN.param);
			String apiModiSchemeUrl = gatewayUrl + urls.get(SystemParamEnum.API_MASTER_MODISCHEME_PATTERN.param);
			String apiModiSchemeDetailUrl = gatewayUrl + urls.get(SystemParamEnum.API_MASTER_MODISCHEMEDETAIL_PATTERN.param);
			String apiOrderVoidUrl = gatewayUrl + urls.get(SystemParamEnum.API_MASTER_ORDERVOID_PATTERN.param);
			String apiCurrencyUrl = gatewayUrl + urls.get(SystemParamEnum.API_MASTER_CURRENCY_PATTERN.param);
			String apiCurrencyRateUrl = gatewayUrl + urls.get(SystemParamEnum.API_MASTER_CURRENCYRATE_PATTERN.param);
			String apiMasterTableUrl = gatewayUrl + urls.get(SystemParamEnum.API_MASTER_TABLE_PATTERN.param);
			String apiHallplanUrl = gatewayUrl + urls.get(SystemParamEnum.API_MASTER_HALLPLAN_PATTERN.param);
			String apiMasterRestaurantUrl = gatewayUrl + urls.get(SystemParamEnum.API_MASTER_RESTAURANT_PATTERN.param);
			String apiEmployeeUrl = gatewayUrl + urls.get(SystemParamEnum.API_MASTER_EMPLOYEE_PATTERN.param);
			String apiGuestTypeUrl = gatewayUrl + urls.get(SystemParamEnum.API_MASTER_GUESTTYPE_PATTERN.param);
			
			
			if(mapData.containsKey(MapKeyEnum.CATEGORY.key) && builder.length() == 0 && selectedReferenceDatas.contains(ReferenceDataEnum.CATEGORY.key)) {
				try {
					MasterDataSync masterDataSync = (MasterDataSync) mapData.get(MapKeyEnum.CATEGORY.key);
					String jsonData = mapper.writerWithView(Views.MasterDataCategList.class).writeValueAsString(masterDataSync);
					ResponseEntity<SyncResponse> response = sendData(apiCategoryUrl, jsonData, restaurantCode);
					SyncResponse syncResponse = response.getBody();
					if (!String.valueOf(HttpStatus.OK.value()).equals(syncResponse.getCode())) {
						builder.append(syncResponse.getMess()).append(SymbolEnum.DOT.val);
					}
				} catch (JsonProcessingException | SystemException e) {
					builder.append(e.getMessage()).append(SymbolEnum.DOT.val);
					log.error("ERROR SENDING CATEGORY EXCEPTION: {}", e);
				}
			}
			if(mapData.containsKey(MapKeyEnum.CURRENCY.key) && builder.length() == 0 && selectedReferenceDatas.contains(ReferenceDataEnum.CURRENCY.key)) {
				try {
					MasterDataSync masterDataSync = (MasterDataSync) mapData.get(MapKeyEnum.CURRENCY.key);
					String jsonData = mapper.writerWithView(Views.MasterDataCurrency.class).writeValueAsString(masterDataSync);
					ResponseEntity<SyncResponse> response = sendData(apiCurrencyUrl, jsonData, restaurantCode);
					SyncResponse syncResponse = response.getBody();
					if (!String.valueOf(HttpStatus.OK.value()).equals(syncResponse.getCode())) {
						builder.append(syncResponse.getMess()).append(SymbolEnum.DOT.val);
					}
				} catch (JsonProcessingException | SystemException e) {
					builder.append(e.getMessage()).append(SymbolEnum.DOT.val);
					log.error("ERROR SENDING CURRENCY EXCEPTION: {}", e);
				}
			}
			if(mapData.containsKey(MapKeyEnum.FOOD_ITEM.key) && builder.length() == 0 && selectedReferenceDatas.contains(ReferenceDataEnum.FOOD_ITEM.key)) {
				try {
					MasterDataSync masterDataSync = (MasterDataSync) mapData.get(MapKeyEnum.FOOD_ITEM.key);
					String jsonData = mapper.writerWithView(Views.MasterDataMenuItem.class).writeValueAsString(masterDataSync);
					ResponseEntity<SyncResponse> response = sendData(apiMenuItemUrl, jsonData, restaurantCode);
					SyncResponse syncResponse = response.getBody();
					if (!String.valueOf(HttpStatus.OK.value()).equals(syncResponse.getCode())) {
						builder.append(syncResponse.getMess()).append(SymbolEnum.DOT.val);
					}
				} catch (JsonProcessingException | SystemException e) {
					builder.append(e.getMessage()).append(SymbolEnum.DOT.val);
					log.error("ERROR SENDING FOOD_ITEM EXCEPTION: {}", e);
				}
			}
			if(mapData.containsKey(MapKeyEnum.MODI_GROUP.key) && builder.length() == 0 && selectedReferenceDatas.contains(ReferenceDataEnum.MODI_GROUP.key)) {
				try {
					MasterDataSync masterDataSync = (MasterDataSync) mapData.get(MapKeyEnum.MODI_GROUP.key);
					String jsonData = mapper.writerWithView(Views.MasterDataModiGroups.class).writeValueAsString(masterDataSync);
					ResponseEntity<SyncResponse> response = sendData(apiModiGroupsUrl, jsonData, restaurantCode);
					SyncResponse syncResponse = response.getBody();
					if (!String.valueOf(HttpStatus.OK.value()).equals(syncResponse.getCode())) {
						builder.append(syncResponse.getMess()).append(SymbolEnum.DOT.val);
					}
				} catch (JsonProcessingException | SystemException e) {
					builder.append(e.getMessage()).append(SymbolEnum.DOT.val);
					log.error("ERROR SENDING MODI_GROUP EXCEPTION: {}", e);
				}
			}
			if(mapData.containsKey(MapKeyEnum.MODI_SCHEME.key) && builder.length() == 0 && selectedReferenceDatas.contains(ReferenceDataEnum.MODI_SCHEME.key)) {
				try {
					MasterDataSync masterDataSync = (MasterDataSync) mapData.get(MapKeyEnum.MODI_SCHEME.key);
					String jsonData = mapper.writerWithView(Views.MasterDataModiScheme.class).writeValueAsString(masterDataSync);
					ResponseEntity<SyncResponse> response = sendData(apiModiSchemeUrl, jsonData, restaurantCode);
					SyncResponse syncResponse = response.getBody();
					if (!String.valueOf(HttpStatus.OK.value()).equals(syncResponse.getCode())) {
						builder.append(syncResponse.getMess()).append(SymbolEnum.DOT.val);
					}
				} catch (JsonProcessingException | SystemException e) {
					builder.append(e.getMessage()).append(SymbolEnum.DOT.val);
					log.error("ERROR SENDING MODI_SCHEME EXCEPTION: {}", e);
				}
			}
			if(mapData.containsKey(MapKeyEnum.MODI_SCHEME_DETAIL.key) && builder.length() == 0 && selectedReferenceDatas.contains(ReferenceDataEnum.MODI_SCHEME_DETAIL.key)) {
				try {
					MasterDataSync masterDataSync = (MasterDataSync) mapData.get(MapKeyEnum.MODI_SCHEME_DETAIL.key);
					String jsonData = mapper.writerWithView(Views.MasterDataModiSchemeDetail.class).writeValueAsString(masterDataSync);
					ResponseEntity<SyncResponse> response = sendData(apiModiSchemeDetailUrl, jsonData, restaurantCode);
					SyncResponse syncResponse = response.getBody();
					if (!String.valueOf(HttpStatus.OK.value()).equals(syncResponse.getCode())) {
						builder.append(syncResponse.getMess()).append(SymbolEnum.DOT.val);
					}
				} catch (JsonProcessingException | SystemException e) {
					builder.append(e.getMessage()).append(SymbolEnum.DOT.val);
					log.error("ERROR SENDING MODI_SCHEME_DETAIL EXCEPTION: {}", e);
				}
			}
			if(mapData.containsKey(MapKeyEnum.MODIFIER.key) && builder.length() == 0 && selectedReferenceDatas.contains(ReferenceDataEnum.MODIFIER.key)) {
				try {
					MasterDataSync masterDataSync = (MasterDataSync) mapData.get(MapKeyEnum.MODIFIER.key);
					String jsonData  = mapper.writerWithView(Views.MasterDataModifier.class).writeValueAsString(masterDataSync);
					ResponseEntity<SyncResponse> response = sendData(apiModifierUrl, jsonData, restaurantCode);
					SyncResponse syncResponse = response.getBody();
					if (!String.valueOf(HttpStatus.OK.value()).equals(syncResponse.getCode())) {
						builder.append(syncResponse.getMess()).append(SymbolEnum.DOT.val);
					}
				} catch (JsonProcessingException | SystemException e) {
					builder.append(e.getMessage()).append(SymbolEnum.DOT.val);
					log.error("ERROR SENDING MODIFIER EXCEPTION: {}", e);
				}
			}
			if(mapData.containsKey(MapKeyEnum.ORDER_CATEGORY.key) && builder.length() == 0 && selectedReferenceDatas.contains(ReferenceDataEnum.ORDER_CATEGORY.key)) {
				try {
					MasterDataSync masterDataSync = (MasterDataSync) mapData.get(MapKeyEnum.ORDER_CATEGORY.key);
					String jsonData = mapper.writerWithView(Views.MasterDataOrderCategory.class).writeValueAsString(masterDataSync);
					ResponseEntity<SyncResponse> response = sendData(apiOrderCategoryUrl, jsonData, restaurantCode);
					SyncResponse syncResponse = response.getBody();
					if (!String.valueOf(HttpStatus.OK.value()).equals(syncResponse.getCode())) {
						builder.append(syncResponse.getMess()).append(SymbolEnum.DOT.val);
					}
				} catch (JsonProcessingException | SystemException e) {
					builder.append(e.getMessage()).append(SymbolEnum.DOT.val);
					log.error("ERROR SENDING ORDER_CATEGORY EXCEPTION: {}", e);
				}
			}
			if(mapData.containsKey(MapKeyEnum.ORDER_TYPE.key) && builder.length() == 0 && selectedReferenceDatas.contains(ReferenceDataEnum.ORDER_TYPE.key)) {
				try {
					MasterDataSync masterDataSync = (MasterDataSync) mapData.get(MapKeyEnum.ORDER_TYPE.key);
					String jsonData  = mapper.writerWithView(Views.MasterDataOrderType.class).writeValueAsString(masterDataSync);
					ResponseEntity<SyncResponse> response = sendData(apiOrderTypeUrl, jsonData, restaurantCode);
					SyncResponse syncResponse = response.getBody();
					if (!String.valueOf(HttpStatus.OK.value()).equals(syncResponse.getCode())) {
						builder.append(syncResponse.getMess()).append(SymbolEnum.DOT.val);
					}
				} catch (JsonProcessingException | SystemException e) {
					builder.append(e.getMessage()).append(SymbolEnum.DOT.val);
					log.error("ERROR SENDING ORDER_TYPE EXCEPTION: {}", e);
				}
			}
			if(mapData.containsKey(MapKeyEnum.ORDER_VOID.key) && builder.length() == 0 && selectedReferenceDatas.contains(ReferenceDataEnum.ORDER_VOID.key)) {
				try {
					MasterDataSync masterDataSync = (MasterDataSync) mapData.get(MapKeyEnum.ORDER_VOID.key);
					String jsonData  = mapper.writerWithView(Views.MasterDataOrderVoid.class).writeValueAsString(masterDataSync);
					ResponseEntity<SyncResponse> response = sendData(apiOrderVoidUrl, jsonData, restaurantCode);
					SyncResponse syncResponse = response.getBody();
					if (!String.valueOf(HttpStatus.OK.value()).equals(syncResponse.getCode())) {
						builder.append(syncResponse.getMess()).append(SymbolEnum.DOT.val);
					}
				} catch (JsonProcessingException | SystemException e) {
					builder.append(e.getMessage()).append(SymbolEnum.DOT.val);
					log.error("ERROR SENDING ORDER_VOID EXCEPTION: {}", e);
				}
			}
			if(mapData.containsKey(MapKeyEnum.CURRENCY_RATE.key) && builder.length() == 0 && selectedReferenceDatas.contains(ReferenceDataEnum.CURRENCY_RATE.key)) {
				try {
					MasterDataSync masterDataSync = (MasterDataSync) mapData.get(MapKeyEnum.CURRENCY_RATE.key);
					String jsonData  = mapper.writerWithView(Views.MasterDataCurrencyRate.class).writeValueAsString(masterDataSync);
					ResponseEntity<SyncResponse> response = sendData(apiCurrencyRateUrl, jsonData, restaurantCode);
					SyncResponse syncResponse = response.getBody();
					if (!String.valueOf(HttpStatus.OK.value()).equals(syncResponse.getCode())) {
						builder.append(syncResponse.getMess()).append(SymbolEnum.DOT.val);
					}
				} catch (JsonProcessingException | SystemException e) {
					builder.append(e.getMessage()).append(SymbolEnum.DOT.val);
					log.error("ERROR SENDING CURRENCY_RATE EXCEPTION: {}", e);
				}
			}
			if(mapData.containsKey(MapKeyEnum.HALL_PLAN.key) && builder.length() == 0 && selectedReferenceDatas.contains(ReferenceDataEnum.HALL_PLAN.key)) {
				try {
					MasterDataSync masterDataSync = (MasterDataSync) mapData.get(MapKeyEnum.HALL_PLAN.key);
					String jsonData  = mapper.writerWithView(Views.MasterDataHallPlan.class).writeValueAsString(masterDataSync);
					ResponseEntity<SyncResponse> response = sendData(apiHallplanUrl, jsonData, restaurantCode);
					SyncResponse syncResponse = response.getBody();
					if (!String.valueOf(HttpStatus.OK.value()).equals(syncResponse.getCode())) {
						builder.append(syncResponse.getMess()).append(SymbolEnum.DOT.val);
					}
				} catch (JsonProcessingException | SystemException e) {
					builder.append(e.getMessage()).append(SymbolEnum.DOT.val);
					log.error("ERROR SENDING HALL_PLAN EXCEPTION: {}", e);
				}
			}
			if(mapData.containsKey(MapKeyEnum.TABLE_KITCHEN.key) && builder.length() == 0 && selectedReferenceDatas.contains(ReferenceDataEnum.TABLE_KITCHEN.key)) {
				try {
					MasterDataSync masterDataSync = (MasterDataSync) mapData.get(MapKeyEnum.TABLE_KITCHEN.key);
					String jsonData = mapper.writerWithView(Views.MasterDataTable.class).writeValueAsString(masterDataSync);
					ResponseEntity<SyncResponse> response = sendData(apiMasterTableUrl, jsonData, restaurantCode);
					SyncResponse syncResponse = response.getBody();
					if (!String.valueOf(HttpStatus.OK.value()).equals(syncResponse.getCode())) {
						builder.append(syncResponse.getMess()).append(SymbolEnum.DOT.val);
					}
				} catch (JsonProcessingException | SystemException e) {
					builder.append(e.getMessage()).append(SymbolEnum.DOT.val);
					log.error("ERROR SENDING TABLE_KITCHEN EXCEPTION: {}", e);
				}
			}
			if(mapData.containsKey(MapKeyEnum.GUEST_TYPE.key) && builder.length() == 0 && selectedReferenceDatas.contains(ReferenceDataEnum.GUEST_TYPE.key)) {
				try {
					MasterDataSync masterDataSync = (MasterDataSync) mapData.get(MapKeyEnum.GUEST_TYPE.key);
					String jsonData = mapper.writerWithView(Views.MasterDataGuestType.class).writeValueAsString(masterDataSync);
					ResponseEntity<SyncResponse> response = sendData(apiGuestTypeUrl, jsonData, restaurantCode);
					SyncResponse syncResponse = response.getBody();
					if (!String.valueOf(HttpStatus.OK.value()).equals(syncResponse.getCode())) {
						builder.append(syncResponse.getMess()).append(SymbolEnum.DOT.val);
					}
				} catch (JsonProcessingException | SystemException e) {
					builder.append(e.getMessage()).append(SymbolEnum.DOT.val);
					log.error("ERROR SENDING GUEST_TYPE EXCEPTION: {}", e);
				}
			}
			if(mapData.containsKey(MapKeyEnum.EMPLOYEE.key) && builder.length() == 0 && selectedReferenceDatas.contains(ReferenceDataEnum.EMPLOYEE.key)) {
				try {
					MasterDataSync masterDataSync = (MasterDataSync) mapData.get(MapKeyEnum.EMPLOYEE.key);
					String jsonData = mapper.writerWithView(Views.MasterDataEmployee.class).writeValueAsString(masterDataSync);
					ResponseEntity<SyncResponse> response = sendData(apiEmployeeUrl, jsonData, restaurantCode);
					SyncResponse syncResponse = response.getBody();
					if (!String.valueOf(HttpStatus.OK.value()).equals(syncResponse.getCode())) {
						builder.append(syncResponse.getMess()).append(SymbolEnum.DOT.val);
					}
				} catch (JsonProcessingException | SystemException e) {
					builder.append(e.getMessage()).append(SymbolEnum.DOT.val);
					log.error("ERROR SENDING EMPLOYEE EXCEPTION: {}", e);
				}
			}
			if(mapData.containsKey(MapKeyEnum.RESTAURANT.key) && builder.length() == 0 && selectedReferenceDatas.contains(ReferenceDataEnum.RESTAURANT.key)) {
				try {
					MasterDataSync masterDataSync = (MasterDataSync) mapData.get(MapKeyEnum.RESTAURANT.key);
					String jsonData = mapper.writerWithView(Views.MasterDataRestaurant.class).writeValueAsString(masterDataSync);
					ResponseEntity<SyncResponse> response = sendData(apiMasterRestaurantUrl, jsonData, restaurantCode);
					SyncResponse syncResponse = response.getBody();
					if (!String.valueOf(HttpStatus.OK.value()).equals(syncResponse.getCode())) {
						builder.append(syncResponse.getMess()).append(SymbolEnum.DOT.val);
					}
				} catch (JsonProcessingException | SystemException e) {
					builder.append(e.getMessage()).append(SymbolEnum.DOT.val);
					log.error("ERROR SENDING RESTAURANT EXCEPTION: {}", e);
				}
			}
		} catch (Exception e) {
			builder.append(e.getMessage());
			log.info("ERROR PROCESS: SEND MASTER_DATA TO RESTAURANT EXCEPTION: {}", e);
		}
		
		return builder.toString();
	}
	
	
	private ResponseEntity<SyncResponse> sendData(final String url, final String jsonData, final String resCode) throws SystemException {
		log.debug("PROCESS: SENDING BUSINESS_DATA");
		log.debug("======> URL: " + url);
		log.debug("======> JSON-DATA: " + jsonData);
		
		try {
		    RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8));
			headers.setAccept(Collections.singletonList(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8)));
			if (StringUtils.isNotBlank(resCode)) {
				headers.add(HeaderEnum.KEY_RES_CODE.getKey(), resCode);
			}
			HttpEntity<?> requestEntity = new HttpEntity<>(new ObjectMapper().readTree(jsonData), headers);
			return restTemplate.exchange(url, HttpMethod.POST, requestEntity, SyncResponse.class);
		} catch(Exception e) {
			e.printStackTrace();
			throw new SystemException(e.getMessage(), e);
		}
	}
}
