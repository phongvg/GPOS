package com.gg.gpos.integration.manager;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gg.gpos.common.constant.HeaderEnum;
import com.gg.gpos.common.constant.HttpStatusEnum;
import com.gg.gpos.common.constant.MapKeyEnum;
import com.gg.gpos.common.constant.StatusEnum;
import com.gg.gpos.common.constant.SymbolEnum;
import com.gg.gpos.common.constant.SystemParamEnum;
import com.gg.gpos.common.exception.SystemException;
import com.gg.gpos.common.json.CheckStatusResponse;
import com.gg.gpos.common.json.ConfigCOCategory;
import com.gg.gpos.common.json.ConfigCOMenuItem;
import com.gg.gpos.common.json.ConfigCOModifier;
import com.gg.gpos.common.json.ConfigCOSpecialType;
import com.gg.gpos.common.json.ConfigHallplan;
import com.gg.gpos.common.json.ConfigMenuEngineering;
import com.gg.gpos.common.json.ConfigParam;
import com.gg.gpos.common.json.ConfigPlace;
import com.gg.gpos.common.json.ConfigPrintGroup;
import com.gg.gpos.common.json.ConfigPrinter;
import com.gg.gpos.common.json.JsonDeleteCOMenuItem;
import com.gg.gpos.common.json.JsonDeleteCoCategory;
import com.gg.gpos.common.json.JsonKaloGroup;
import com.gg.gpos.common.json.JsonMenuGroupDetail;
import com.gg.gpos.common.json.MasterDataSync;
import com.gg.gpos.common.json.RestaurantSync;
import com.gg.gpos.common.json.SyncResponse;
import com.gg.gpos.common.json.Views;
import com.gg.gpos.integration.dto.ResponseMessage;
import com.gg.gpos.integration.dto.Restaurant;
import com.gg.gpos.integration.exception.IntegrationException;
import com.gg.gpos.res.dto.BrandDto;
import com.gg.gpos.res.dto.DistrictDto;
import com.gg.gpos.res.dto.ProvinceBrandDto;
import com.gg.gpos.res.dto.ProvinceDto;
import com.gg.gpos.res.dto.RestaurantDto;
import com.gg.gpos.res.dto.ZoneDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RestaurantSyncManager {
	private static final String ERROR = "ERROR: ";
	
	/*
	 * Sync master data to Restaurant
	*/
	public Map<String, Object> syncRestaurantFromServer(String referenceDataUrl) throws IntegrationException {
		log.info("Entering syncRestaurant()... ");

		Map<String, Object> map = new HashMap<>();
	    RestTemplate restTemplate = new RestTemplate();
		
		// Set the Accept and Content type header
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8));
		headers.setAccept(Collections.singletonList(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8)));
		HttpEntity<?> requestEntity = new HttpEntity<>(headers);
		ResponseEntity<ResponseMessage> response = restTemplate.exchange(referenceDataUrl, HttpMethod.GET, requestEntity, ResponseMessage.class);
		
		if (HttpStatus.OK.equals(response.getStatusCode())) {
			Set<ZoneDto> zones = new HashSet<>();
			Set<ProvinceDto> provinces = new HashSet<>();
			Set<DistrictDto> districts = new HashSet<>();
			Set<BrandDto> brands = new HashSet<>();
			Set<ProvinceBrandDto> provinceBrands = new HashSet<>();

			Map<String, ZoneDto> zoneMap = new HashMap<>();
			AtomicInteger atomicInteger = new AtomicInteger();  // Initial value is 0
			
			ResponseMessage message = response.getBody();
			List<Restaurant> restaurants = message.getResult();
			List<RestaurantDto> restaurantList = restaurants.stream().filter(r -> {
				boolean isValid = false;
				if (StringUtils.isNotBlank(r.getRegionName()) && r.getProvince() != null && r.getDistrict() != null && r.getBrand() != null) {
					isValid = true;
				}
				return isValid;
			}).map(r -> {
				
				log.debug("RESTAURANT-CODE: " + r.getCode());
				
				RestaurantDto restaurantDto = new RestaurantDto();
				restaurantDto.setCode(r.getCode());
				restaurantDto.setName(r.getName());
				restaurantDto.setAddress(r.getAddress());
				restaurantDto.setPhone(r.getTelephone());
				restaurantDto.setIp(r.getGposIp());
				restaurantDto.setPort(r.getGposPort());
				restaurantDto.setStatus(r.getIsActive());
				restaurantDto.setLatitude(r.getLatitude());
				restaurantDto.setLongitude(r.getLongitude());
				restaurantDto.setEmail(r.getEmail());
				
				ZoneDto zone = null;
				if (zoneMap.containsKey(r.getRegionName())) {
					zone = zoneMap.get(r.getRegionName());
				} else {
					int zoneId = atomicInteger.incrementAndGet();
					zone = new ZoneDto();
					zone.setId(Long.valueOf(zoneId));
					zone.setName(r.getRegionName());
					zoneMap.put(r.getRegionName(), zone);
					zones.add(zone);
				}
				
				ProvinceDto province = new ProvinceDto();
				province.setId(r.getProvince().getId());
				province.setName(r.getProvince().getName());
				province.setDisplayOrder(r.getProvince().getOrder());
				province.setZone(zone);
				try {
					provinces.add(province);
				} catch (Exception e) {
					log.debug("Ignore duplicated province.");
				}

				DistrictDto district = new DistrictDto();
				district.setId(r.getDistrict().getId());
				district.setName(r.getDistrict().getName());
				district.setDisplayOrder(r.getDistrict().getOrder());
				district.setProvince(province);
				try {
					districts.add(district);
				} catch (Exception e) {
					log.debug("Ignore duplicated district.");
				}

				BrandDto brand = new BrandDto();
				brand.setId(r.getBrand().getId());
				brand.setName(r.getBrand().getName());
				try {
					brands.add(brand);
				} catch (Exception e) {
					log.debug("Ignore duplicated brand.");
				}

				ProvinceBrandDto provinceBrand = new ProvinceBrandDto();
				provinceBrand.setProvince(province);
				provinceBrand.setBrand(brand);
				try {
					provinceBrands.add(provinceBrand);
				} catch (Exception e) {
					log.debug("Ignore duplicated province brand.");
				}

				restaurantDto.setProvinceBrand(provinceBrand);

				return restaurantDto;
			}).collect(Collectors.toList());

			map.put(MapKeyEnum.ZONE.key, new ArrayList<>(zones));
			map.put(MapKeyEnum.PROVINCE.key, new ArrayList<>(provinces));
			map.put(MapKeyEnum.DISTRICT.key, new ArrayList<>(districts));
			map.put(MapKeyEnum.BRAND.key, new ArrayList<>(brands));
			map.put(MapKeyEnum.RES.key, restaurantList);
			map.put(MapKeyEnum.PROVINCE_BRAND.key, new ArrayList<>(provinceBrands));
		} else {
			throw new IntegrationException(response.getStatusCode().toString(), "Bad Integration");
		}

		return map;
	}

	public SyncResponse sendMasterDataToRestaurant(final MasterDataSync masterDataSync, final String type, final String url, final String resCodes) {
		log.info("Entering sendMasterDataToRestaurant() method... ");

		SyncResponse syncResponse = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = "{}";
			if (SystemParamEnum.API_MASTER_CATEGLIST_PATTERN.param.equals(type)) {
				jsonData = mapper.writerWithView(Views.MasterDataCategList.class).writeValueAsString(masterDataSync);
			} else if (SystemParamEnum.API_MASTER_CATEGORY_PATTERN.param.equals(type)) {
				jsonData = mapper.writerWithView(Views.MasterDataOrderCategory.class).writeValueAsString(masterDataSync);
			} else if (SystemParamEnum.API_MASTER_CURRENCY_PATTERN.param.equals(type)) {
				jsonData = mapper.writerWithView(Views.MasterDataCurrency.class).writeValueAsString(masterDataSync);
			} else if (SystemParamEnum.API_MASTER_CURRENCYRATE_PATTERN.param.equals(type)) {
				jsonData = mapper.writerWithView(Views.MasterDataCurrencyRate.class).writeValueAsString(masterDataSync);
			} else if (SystemParamEnum.API_MASTER_EMPLOYEE_PATTERN.param.equals(type)) {
				jsonData = mapper.writerWithView(Views.MasterDataEmployee.class).writeValueAsString(masterDataSync);
			} else if (SystemParamEnum.API_MASTER_GUESTTYPE_PATTERN.param.equals(type)) {
				jsonData = mapper.writerWithView(Views.MasterDataGuestType.class).writeValueAsString(masterDataSync);
			} else if (SystemParamEnum.API_MASTER_HALLPLAN_PATTERN.param.equals(type)) {
				jsonData = mapper.writerWithView(Views.MasterDataHallPlan.class).writeValueAsString(masterDataSync);
			} else if (SystemParamEnum.API_MASTER_MENUITEM_PATTERN.param.equals(type)) {
				jsonData = mapper.writerWithView(Views.MasterDataMenuItem.class).writeValueAsString(masterDataSync);
			} else if (SystemParamEnum.API_MASTER_MODIFIER_PATTERN.param.equals(type)) {
				jsonData = mapper.writerWithView(Views.MasterDataModifier.class).writeValueAsString(masterDataSync);
			} else if (SystemParamEnum.API_MASTER_MODIGROUPS_PATTERN.param.equals(type)) {
				jsonData = mapper.writerWithView(Views.MasterDataModiGroups.class).writeValueAsString(masterDataSync);
			} else if (SystemParamEnum.API_MASTER_MODISCHEME_PATTERN.param.equals(type)) {
				jsonData = mapper.writerWithView(Views.MasterDataModiScheme.class).writeValueAsString(masterDataSync);
			} else if (SystemParamEnum.API_MASTER_MODISCHEMEDETAIL_PATTERN.param.equals(type)) {
				jsonData = mapper.writerWithView(Views.MasterDataModiSchemeDetail.class).writeValueAsString(masterDataSync);
			} else if (SystemParamEnum.API_MASTER_ORDERTYPE_PATTERN.param.equals(type)) {
				jsonData = mapper.writerWithView(Views.MasterDataOrderType.class).writeValueAsString(masterDataSync);
			} else if (SystemParamEnum.API_MASTER_ORDERVOID_PATTERN.param.equals(type)) {
				jsonData = mapper.writerWithView(Views.MasterDataOrderVoid.class).writeValueAsString(masterDataSync);
			} else if (SystemParamEnum.API_MASTER_RESTAURANT_PATTERN.param.equals(type)) {
				jsonData = mapper.writerWithView(Views.MasterDataRestaurant.class).writeValueAsString(masterDataSync);
			} else if (SystemParamEnum.API_MASTER_TABLE_PATTERN.param.equals(type)) {
				jsonData = mapper.writerWithView(Views.MasterDataTable.class).writeValueAsString(masterDataSync);
			} else {}
			
			ResponseEntity<SyncResponse> response = sendData(url, jsonData, resCodes);
			syncResponse = response.getBody();
			
			log.debug("====> Code: " + syncResponse.getCode() + ", Message: " + syncResponse.getMess());
			
		} catch (Exception e) {
			syncResponse = new SyncResponse();
			syncResponse.setCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
			syncResponse.setMess(e.getMessage());
			
			log.error(ERROR, e);
		}
		
		return syncResponse;
	}
	

	/***************
	 *** End sync master data
	**************/
	
	/***************
	 *** Sync business data to restaurant
	**************/
	private ResponseEntity<SyncResponse> sendData(final String url, final String jsonData, final String resCodes) throws SystemException {
		log.info("Entering sendData() method...");
		log.debug("=> URL: " + url);
		log.debug("=> JSON-DATA: " + jsonData);
		
		try {
		    RestTemplate restTemplate = new RestTemplate();
			
			// create headers
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8));
			headers.setAccept(Collections.singletonList(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8)));
			if (StringUtils.isNotBlank(resCodes)) {
				headers.add(HeaderEnum.KEY_RES_CODE.getKey(), resCodes);
			}
			HttpEntity<?> requestEntity = new HttpEntity<>(new ObjectMapper().readTree(jsonData), headers);
		
			return restTemplate.exchange(url, HttpMethod.POST, requestEntity, SyncResponse.class);
			
		} catch(Exception e) {
			e.printStackTrace();
			throw new SystemException(e.getMessage(), e);
		}
	}
	 
	public List<String> getPrinters(final String url, final Integer resCode) {
		List<String> printers = null;
		try {
		    RestTemplate restTemplate = new RestTemplate();
			
			// create headers
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8));
			headers.setAccept(Collections.singletonList(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8)));
			headers.add(HeaderEnum.KEY_RES_CODE.getKey(), resCode.toString());
			HttpEntity<?> requestEntity = new HttpEntity<>(headers);
		
			ResponseEntity<ConfigPrinter> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, ConfigPrinter.class);
			ConfigPrinter configPrinter = response.getBody();
			if (HttpStatusEnum.STATUS_200.code.equals(configPrinter.getCode())) {
				printers = configPrinter.getData();
			}
			
		} catch(Exception e) {
			log.error(e.toString());
		}
		
		return printers;
	}

	
	public boolean check(final String url,final Integer resCode) throws JsonProcessingException {
		boolean check = true;
		try {
			CheckStatusResponse syncResponse = checkOnline(url, resCode.toString());
			if (!String.valueOf(HttpStatus.OK.value()).equals(syncResponse.getCode())) {
				check = false;
			}
		} catch (Exception e) {
			log.error(ERROR, e);
			check = false;
		}
		return check;
	}
	
	public RestaurantDto check(final String url,final Integer resCode,RestaurantDto restaurant) throws JsonProcessingException {
		log.info("Entering check online restaurant() method...");
		try {
			CheckStatusResponse syncResponse = checkOnline(url, resCode.toString());
			if (!String.valueOf(HttpStatus.OK.value()).equals(syncResponse.getCode())) {
				restaurant.setOnline(StatusEnum.INACTIVE.status);
				restaurant.setVersion(null);
			} else {
				restaurant.setOnline(StatusEnum.ACTIVE.status);
				if(syncResponse.getData() != null && !syncResponse.getData().isEmpty()) {
					restaurant.setVersion(syncResponse.getData());
				}
			}
		} catch (Exception e) {
			restaurant.setOnline(StatusEnum.INACTIVE.status);
			log.error(ERROR, e);
		}
		return restaurant;
	}
	
	private CheckStatusResponse checkOnline(final String url,final String resCode) {
		log.debug("=> URL: " + url);
		log.debug("=> resCode: " + resCode);
		try {
		    RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
			// create headers
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8));
			headers.setAccept(Collections.singletonList(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8)));
			if (StringUtils.isNotBlank(resCode)) {
				headers.add(HeaderEnum.KEY_RES_CODE.getKey(), resCode);
			}
			HttpEntity<?> requestEntity = new HttpEntity<>(headers);
		
			return restTemplate.exchange(url, HttpMethod.GET, requestEntity, CheckStatusResponse.class).getBody();
		} catch(Exception e) {
			log.error("ERROR: Restaurant offline");
			CheckStatusResponse checkStatusResponse = new CheckStatusResponse();
			checkStatusResponse.setCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
			return checkStatusResponse;
		}
	}
	
	private SimpleClientHttpRequestFactory getClientHttpRequestFactory() {
	    SimpleClientHttpRequestFactory clientHttpRequestFactory
	                      = new SimpleClientHttpRequestFactory();
	    //Connect timeout
	    clientHttpRequestFactory.setConnectTimeout(3_000);
	     
	    //Read timeout
	    clientHttpRequestFactory.setReadTimeout(3_000);
	    return clientHttpRequestFactory;
	}
	
}
