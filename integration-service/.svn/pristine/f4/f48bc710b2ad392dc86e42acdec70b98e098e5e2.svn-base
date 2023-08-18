package com.gg.gpos.integration.manager;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.net.ssl.SSLContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.client.RestTemplate;

import com.gg.gpos.common.constant.DefaultValueEnum;
import com.gg.gpos.common.constant.FoodTypeEnum;
import com.gg.gpos.common.constant.RK7QueryEnum;
import com.gg.gpos.menu.dto.CategoryDto;
import com.gg.gpos.menu.dto.CurrencyDto;
import com.gg.gpos.menu.dto.CurrencyRateDto;
import com.gg.gpos.menu.dto.EmployeeDto;
import com.gg.gpos.menu.dto.FoodItemDto;
import com.gg.gpos.menu.dto.GuestTypeDto;
import com.gg.gpos.menu.dto.HallplanDto;
import com.gg.gpos.menu.dto.ModiGroupDto;
import com.gg.gpos.menu.dto.ModiSchemeDetailDto;
import com.gg.gpos.menu.dto.ModiSchemeDto;
import com.gg.gpos.menu.dto.ModifierDto;
import com.gg.gpos.menu.dto.OrderCategoryDto;
import com.gg.gpos.menu.dto.OrderTypeDto;
import com.gg.gpos.menu.dto.OrderVoidDto;
import com.gg.gpos.menu.dto.RestaurantMasterDto;
import com.gg.gpos.menu.dto.SapInfoDto;
import com.gg.gpos.menu.dto.TableKitchenDto;
import com.gg.rk7.xml.RK7QueryResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MasterDataSyncManager {
	private HttpComponentsClientHttpRequestFactory getRequestFactory() {
	    TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

        HttpComponentsClientHttpRequestFactory requestFactory =  new HttpComponentsClientHttpRequestFactory();
		try {
			SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
			                .loadTrustMaterial(null, acceptingTrustStrategy)
			                .build();

	        final SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
	        final Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
	                .register("https", sslsf)
	                .register("http", new PlainConnectionSocketFactory())
	                .build();
	        final BasicHttpClientConnectionManager connectionManager = new BasicHttpClientConnectionManager(socketFactoryRegistry);
	        final CloseableHttpClient httpClient = HttpClients.custom()
	                .setSSLSocketFactory(sslsf)
	                .setConnectionManager(connectionManager)
	                .build();	    

		    requestFactory.setHttpClient(httpClient);

		} catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException e) {
			log.error("ERROR: ", e);
		}

	    return requestFactory;
	}
	
	private RK7QueryResult syncFromRK7(String url, String basicAuth, RK7QueryEnum type) {
		log.info("Entering syncFromRK7()... ");
		log.debug("URL: " + url);
		
	    RestTemplate restTemplate = new RestTemplate();
		// set this to bypass ssl validation
		restTemplate.setRequestFactory(getRequestFactory());

	    HttpHeaders headers = new HttpHeaders();
	    headers.setAcceptCharset(Arrays.asList(StandardCharsets.UTF_8));
	    headers.setContentType(new MediaType(MediaType.APPLICATION_XML, StandardCharsets.UTF_8));
	    headers.setBasicAuth(Base64Utils.encodeToString(basicAuth.getBytes()));
	    HttpEntity<String> request = new HttpEntity<>(type.getQuery(), headers);	    
	    
	    ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
	    log.debug("STATUS:" + response.getStatusCodeValue());
	    log.debug("BODY: " + response.getBody());
	    
	    try {
		    JAXBContext jaxbContext = JAXBContext.newInstance(RK7QueryResult.class);
		    Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
	        //use ByteArrayInputStream to get the bytes of the String and convert them to InputStream.
	        InputStream inputStream = new ByteArrayInputStream(response.getBody().getBytes(StandardCharsets.UTF_8));
	        return (RK7QueryResult) unmarshaller.unmarshal(inputStream);
		} catch (JAXBException e) {
			log.error("ERROR: ", e);
		}
		
	    return null;
	}

	
	public List<CategoryDto> syncCategoryFromRK7(String rk7Url, String rk7BasicAuth) {
		log.info("Entering syncCategoryFromRK7()... ");
		
		RK7QueryResult rk7QueryResult = syncFromRK7(rk7Url, rk7BasicAuth, RK7QueryEnum.CATEGORY);
		List<CategoryDto> categoryDtos = new ArrayList<>();
		if (rk7QueryResult != null) {
			rk7QueryResult.getCommandResult().getRK7Reference().getItems().getItem().stream().forEach(item -> {
				if (item != null) {
					CategoryDto categoryDto = new CategoryDto();
					categoryDto.setId(item.getIdent());
					categoryDto.setItemId(item.getItemIdent());
					categoryDto.setParentId(item.getParent());
					categoryDto.setCode(item.getCode());
					categoryDto.setName(item.getName());
					categoryDto.setStatus(item.getStatus());
					categoryDto.setFlags(item.getFlags());
					categoryDto.setRightLvl(item.getRightLvl());
					categoryDto.setObjectSifr(item.getObjectSifr());
					categoryDtos.add(categoryDto);
				}
			});
		}
		return categoryDtos;
	}

	public List<CurrencyDto> syncCurrencyFromRK7(String rk7Url, String rk7BasicAuth) {
		log.info("Entering syncCurrencyFromRK7()... ");
		
		RK7QueryResult rk7QueryResult = syncFromRK7(rk7Url, rk7BasicAuth, RK7QueryEnum.CURRENCY);
		List<CurrencyDto> currencyDtos = new ArrayList<>();
		if (rk7QueryResult != null) {
			rk7QueryResult.getRK7Reference().getItems().getItem().stream().forEach(item -> {
				CurrencyDto currencyDto = new CurrencyDto();
				currencyDto.setId(item.getIdent());
				currencyDto.setItemIdent(item.getItemIdent());
				currencyDto.setRateClass(item.getRateClass());
				currencyDto.setStatus(item.getStatus());
				currencyDto.setCode(item.getCode());
				currencyDtos.add(currencyDto);
			});
		}
		return currencyDtos;
	}

	public List<CurrencyRateDto> syncCurrencyRateFromRK7(String rk7Url, String rk7BasicAuth) {
		log.info("Entering syncCurrencyRateFromRK7()... ");
		
		RK7QueryResult rk7QueryResult = syncFromRK7(rk7Url, rk7BasicAuth, RK7QueryEnum.CURRENCY_RATE);
		List<CurrencyRateDto> currencyRateDtos = new ArrayList<>();
		if (rk7QueryResult != null) {
			rk7QueryResult.getRK7Reference().getItems().getItem().stream().forEach(item -> {
				CurrencyRateDto currencyRateDto = new CurrencyRateDto();
				currencyRateDto.setId(item.getIdent());
				currencyRateDto.setItemIdent(item.getItemIdent());
				currencyRateDto.setRate(item.getRate());
				currencyRateDto.setStatus(item.getStatus());
				currencyRateDtos.add(currencyRateDto);
			});
		}
		return currencyRateDtos;
	}

	public List<HallplanDto> syncHallplanFromRK7(String rk7Url, String rk7BasicAuth) {
		log.info("Entering syncHallplanFromRK7()... ");
		
		RK7QueryResult rk7QueryResult = syncFromRK7(rk7Url, rk7BasicAuth, RK7QueryEnum.HALL_PLAN);
		List<HallplanDto> hallplanDtos = new ArrayList<>();
		if (rk7QueryResult != null) {
			rk7QueryResult.getCommandResult().getRK7Reference().getItems().getItem().stream().forEach(item -> {
				if (item != null) {
					HallplanDto hallplanDto = new HallplanDto();
					hallplanDto.setId(item.getIdent());
					hallplanDto.setItemId(item.getItemIdent());
					hallplanDto.setParentId(item.getMainParentIdent());
					hallplanDto.setCode(item.getCode());
					hallplanDto.setName(item.getName());
					hallplanDto.setStatus(item.getStatus());
					hallplanDto.setCurrentRes(item.isIsCurrentRes());
					hallplanDto.setFlags(item.getFlags());
					hallplanDto.setCurrentRes(item.isIsCurrentRes());
					hallplanDto.setObjectSifr(item.getObjectSifr());
					hallplanDtos.add(hallplanDto);
				}
			});
		}
		return hallplanDtos;
	}

	public List<FoodItemDto> syncMenuItemFromRK7(String rk7Url, String rk7BasicAuth) {
		log.info("Entering syncMenuItemFromRK7()... ");
		
		// sync sap info
		List<SapInfoDto> sapInfoDtos = syncSapCodeFromRK7(rk7Url, rk7BasicAuth);
		List<SapInfoDto> filterSapInfoDtosByType = sapInfoDtos.stream().filter(s -> DefaultValueEnum.DEFAULT_RK_TYPE_IDEN.val.equals(s.getRKTypeIdent())).collect(Collectors.toList());
		
		RK7QueryResult rk7QueryResult = syncFromRK7(rk7Url, rk7BasicAuth, RK7QueryEnum.MENU_ITEM);
		List<FoodItemDto> foodItemDtos = new ArrayList<>();
		if (rk7QueryResult != null) {
			rk7QueryResult.getCommandResult().getRK7Reference().getItems().getItem().stream().forEach(item -> {
				if (item != null && item.getIdent() >= 0) {
					FoodItemDto foodItemDto = new FoodItemDto();
					foodItemDto.setId(item.getIdent());
					foodItemDto.setItemId(item.getItemIdent());
					foodItemDto.setParentId(item.getParent());
					foodItemDto.setCode(item.getCode());
					foodItemDto.setName(item.getName());
					foodItemDto.setUnsignedName(StringUtils.stripAccents(item.getName()));
					foodItemDto.setStatus(item.getStatus());
					foodItemDto.setComboJoinMode(item.getComboJoinMode());
					foodItemDto.setComboScheme(item.getComboScheme());
					foodItemDto.setModiScheme(item.getModiScheme());
					foodItemDto.setRightLvl(item.getRightLvl());
					foodItemDto.setFlags(item.getFlags());
					foodItemDto.setCookMins(item.getCookMins());
					foodItemDto.setObjectSifr(item.getObjectSifr());
					foodItemDto.setType(FoodTypeEnum.MENU_ITEM.val);

					if (filterSapInfoDtosByType != null && !filterSapInfoDtosByType.isEmpty()) {
						List<SapInfoDto> filterSapInfoDtosByMenuItem = filterSapInfoDtosByType.stream().filter(s -> String.valueOf(item.getIdent()).equals(s.getObjectIdent())).collect(Collectors.toList());
						if (filterSapInfoDtosByMenuItem != null && !filterSapInfoDtosByMenuItem.isEmpty()) {
							SapInfoDto sapInfoDto = filterSapInfoDtosByMenuItem.get(0);
							foodItemDto.setSapCode(sapInfoDto.getSapCode());
						}
					}
					
					foodItemDtos.add(foodItemDto);
				}
			});
		}
		
		return foodItemDtos;
	}
	
	public List<ModifierDto> syncModifierFromRK7(String rk7Url, String rk7BasicAuth) {
		log.info("Entering syncModifierFromRK7()... ");
		
		RK7QueryResult rk7QueryResult = syncFromRK7(rk7Url, rk7BasicAuth, RK7QueryEnum.MODIFIER);
		List<ModifierDto> modifierDtos = new ArrayList<>();
		if (rk7QueryResult != null) {
			rk7QueryResult.getCommandResult().getRK7Reference().getItems().getItem().stream().forEach(item -> {
				if (item != null) {
					ModifierDto modifierDto = new ModifierDto();
					modifierDto.setId(item.getIdent());
					modifierDto.setItemId(item.getItemIdent());
					modifierDto.setParentId(item.getParent());
					modifierDto.setCode(item.getCode());
					modifierDto.setName(item.getName());
					modifierDto.setUnsignedName(StringUtils.stripAccents(item.getName()));
					modifierDto.setStatus(item.getStatus());
					modifierDto.setRightLvl(item.getRightLvl());
					modifierDto.setFlags(item.getFlags());
					modifierDto.setObjectSifr(item.getObjectSifr());
					modifierDto.setDish(item.getDish());
					modifierDto.setMaxOneDish(item.getMaxOneDish());
					modifierDtos.add(modifierDto);
				}
			});
		}
		return modifierDtos;
	}
	
	public List<ModiGroupDto> syncModiGroupFromRK7(String rk7Url, String rk7BasicAuth) {
		log.info("Entering syncModiGroupFromRK7()... ");
		
		RK7QueryResult rk7QueryResult = syncFromRK7(rk7Url, rk7BasicAuth, RK7QueryEnum.MODI_GROUP);
		List<ModiGroupDto> modiGroupDtos = new ArrayList<>();
		if (rk7QueryResult != null) {
			rk7QueryResult.getCommandResult().getRK7Reference().getItems().getItem().stream().forEach(item -> {
				if (item != null) {
					ModiGroupDto modiGroupDto = new ModiGroupDto();
					modiGroupDto.setId(item.getIdent());
					modiGroupDto.setItemId(item.getItemIdent());
					modiGroupDto.setParentId(item.getParent());
					modiGroupDto.setCode(item.getCode());
					modiGroupDto.setName(item.getName());
					modiGroupDto.setStatus(item.getStatus());
					modiGroupDto.setFlags(item.getFlags());
					modiGroupDto.setObjectSifr(item.getObjectSifr());
					modiGroupDtos.add(modiGroupDto);
				}
			});
		}
		return modiGroupDtos;
	}

	public List<ModiSchemeDto> syncModiSchemeFromRK7(String rk7Url, String rk7BasicAuth) {
		log.info("Entering syncModiSchemeFromRK7()... ");
		
		RK7QueryResult rk7QueryResult = syncFromRK7(rk7Url, rk7BasicAuth, RK7QueryEnum.MODI_SCHEME);
		List<ModiSchemeDto> modiSchemeDtos = new ArrayList<>();
		if (rk7QueryResult != null) {
			rk7QueryResult.getCommandResult().getRK7Reference().getItems().getItem().stream().forEach(item -> {
				if (item != null) {
					ModiSchemeDto modiSchemeDto = new ModiSchemeDto();
					modiSchemeDto.setId(item.getIdent());
					modiSchemeDto.setItemId(item.getItemIdent());
					modiSchemeDto.setParentId(item.getParent());
					modiSchemeDto.setCode(item.getCode());
					modiSchemeDto.setName(item.getName());
					modiSchemeDto.setStatus(item.getStatus());
					modiSchemeDto.setSourceIdent(item.getSourceIdent());
					modiSchemeDto.setFlags(item.getFlags());
					modiSchemeDto.setObjectSifr(item.getObjectSifr());
					modiSchemeDto.setEditRight(item.getEditRight());
					modiSchemeDto.setActiveHierarchy(item.getActiveHierarchy());
					modiSchemeDto.setAssignChildsOnServer(item.getAssignChildsOnServer());
					modiSchemeDto.setAutoOpen(item.getAutoOpen());
					modiSchemeDto.setIgnoreDefaultForKitchen(item.getIgnoreDefaultForKitchen());
					modiSchemeDto.setModiSchemeType(item.getModiSchemeType());
					modiSchemeDtos.add(modiSchemeDto);
				}
			});
		}
		return modiSchemeDtos;
	}

	public List<ModiSchemeDetailDto> syncModiSchemeDetailFromRK7(String rk7Url, String rk7BasicAuth) {
		log.info("Entering syncModiSchemeDetailFromRK7()... ");
		
		RK7QueryResult rk7QueryResult = syncFromRK7(rk7Url, rk7BasicAuth, RK7QueryEnum.MODI_SCHEME_DETAIL);
		List<ModiSchemeDetailDto> modiSchemeDetailDtos = new ArrayList<>();
		if (rk7QueryResult != null) {
			rk7QueryResult.getCommandResult().getRK7Reference().getItems().getItem().stream().forEach(item -> {
				ModiSchemeDetailDto modiSchemeDetailDto = new ModiSchemeDetailDto();
				modiSchemeDetailDto.setId(item.getIdent());
				modiSchemeDetailDto.setItemId(item.getItemIdent());
				modiSchemeDetailDto.setParentId(item.getParent());
				modiSchemeDetailDto.setAssignChildsOnServer(item.getAssignChildsOnServer());
				modiSchemeDetailDto.setChangesPrice(item.getChangesPrice());
				modiSchemeDetailDto.setDefaultModifier(item.getDefaultModifier());
				modiSchemeDetailDto.setDownLimit(item.getDownLimit());
				modiSchemeDetailDto.setFlags(item.getFlags());
				modiSchemeDetailDto.setFreeCount(item.getFreeCount());
				modiSchemeDetailDto.setModiGroup(item.getModiGroup());
				modiSchemeDetailDto.setModiScheme(item.getModiScheme());
				modiSchemeDetailDto.setObjectSifr(item.getObjectSifr());
				modiSchemeDetailDto.setReadOnlyName(item.getReadOnlyName());
				modiSchemeDetailDto.setReplaceDefModifier(item.getReplaceDefModifier());
				modiSchemeDetailDto.setShQuantity(item.getShQuantity());
				modiSchemeDetailDto.setSortNum(item.getSortNum());
				modiSchemeDetailDto.setSourceIdent(item.getSourceIdent());
				modiSchemeDetailDto.setUpLimit(item.getUpLimit());
				modiSchemeDetailDto.setUseDownLimit(item.getUseDownLimit());
				modiSchemeDetailDto.setUseUpLimit(item.getUseUpLimit());
				modiSchemeDetailDtos.add(modiSchemeDetailDto);
			});
		}
		return modiSchemeDetailDtos;
	}

	public List<OrderCategoryDto> syncOrderCategoryFromRK7(String rk7Url, String rk7BasicAuth) {
		log.info("Entering syncModiSchemeDetailFromRK7()... ");
		
		RK7QueryResult rk7QueryResult = syncFromRK7(rk7Url, rk7BasicAuth, RK7QueryEnum.ORDER_CATEGORY);
		List<OrderCategoryDto> orderCategoryDtos = new ArrayList<>();
		if (rk7QueryResult != null) {
			rk7QueryResult.getCommandResult().getRK7Reference().getItems().getItem().stream().forEach(item -> {
				if (item != null) {
					OrderCategoryDto orderCategoryDto = new OrderCategoryDto();
					orderCategoryDto.setId(item.getIdent());
					orderCategoryDto.setItemId(item.getItemIdent());
					orderCategoryDto.setParentId(item.getParent());
					orderCategoryDto.setCode(item.getCode());
					orderCategoryDto.setName(item.getName());
					orderCategoryDto.setStatus(item.getStatus());
					orderCategoryDto.setRightLvl(item.getRightLvl());
					orderCategoryDto.setObjectSifr(item.getObjectSifr());
					orderCategoryDto.setFlags(item.getFlags());
					orderCategoryDtos.add(orderCategoryDto);
				}
			});
		}
		return orderCategoryDtos;
	}
	
	public List<OrderTypeDto> syncOrderTypeFromRK7(String rk7Url, String rk7BasicAuth) {
		log.info("Entering syncOrderTypeFromRK7()... ");
		
		RK7QueryResult rk7QueryResult = syncFromRK7(rk7Url, rk7BasicAuth, RK7QueryEnum.ORDER_TYPE);
		List<OrderTypeDto> orderTypeDtos = new ArrayList<>();
		if (rk7QueryResult != null) {
			rk7QueryResult.getCommandResult().getRK7Reference().getItems().getItem().stream().forEach(item -> {
				if (item != null) {
					OrderTypeDto orderTypeDto = new OrderTypeDto();
					orderTypeDto.setId(item.getIdent());
					orderTypeDto.setItemId(item.getItemIdent());
					orderTypeDto.setParentId(item.getParent());
					orderTypeDto.setCode(item.getCode());
					orderTypeDto.setName(item.getName());
					orderTypeDto.setStatus(item.getStatus());
					orderTypeDto.setFlags(item.getFlags());
					orderTypeDto.setRightLvl(item.getRightLvl());
					orderTypeDto.setObjectSifr(item.getObjectSifr());
					orderTypeDtos.add(orderTypeDto);
				}
			});
		}
		return orderTypeDtos;
	}

	public List<OrderVoidDto> syncOrderVoidFromRK7(String rk7Url, String rk7BasicAuth) {
		log.info("Entering syncOrderVoidFromRK7()... ");
		
		RK7QueryResult rk7QueryResult = syncFromRK7(rk7Url, rk7BasicAuth, RK7QueryEnum.ORDER_VOID);
		List<OrderVoidDto> orderVoidDtos = new ArrayList<>();
		if (rk7QueryResult != null) {
			rk7QueryResult.getCommandResult().getRK7Reference().getItems().getItem().stream().forEach(item -> {
				if (item != null) {
					OrderVoidDto orderVoidDto = new OrderVoidDto();
					orderVoidDto.setId(item.getIdent());
					orderVoidDto.setItemId(item.getItemIdent());
					orderVoidDto.setParentId(item.getParent());
					orderVoidDto.setCode(item.getCode());
					orderVoidDto.setName(item.getName());
					orderVoidDto.setStatus(item.getStatus());
					orderVoidDto.setFlags(item.getFlags());
					orderVoidDto.setRightLvl(item.getRightLvl());
					orderVoidDto.setObjectSifr(item.getObjectSifr());
					orderVoidDto.setImplOnDishVoid(item.getImplOnDishVoid());
					orderVoidDto.setImplOnUnprintedDish(item.getImplOnUnprintedDish());
					orderVoidDto.setInputName(item.getInputName());
					orderVoidDtos.add(orderVoidDto);
				}
			});
		}
		return orderVoidDtos;
	}
	
	public List<TableKitchenDto> syncTableFromRK7(String rk7Url, String rk7BasicAuth) {
		log.info("Entering syncTableFromRK7()... ");
		
		RK7QueryResult rk7QueryResult = syncFromRK7(rk7Url, rk7BasicAuth, RK7QueryEnum.TABLE);
		List<TableKitchenDto> tableKitchenDtos = new ArrayList<>();
		if (rk7QueryResult != null) {
			rk7QueryResult.getCommandResult().getRK7Reference().getItems().getItem().stream().forEach(item -> {
				if (item != null) {
					TableKitchenDto tableKitchenDto = new TableKitchenDto();
					tableKitchenDto.setId(item.getIdent());
					tableKitchenDto.setItemId(item.getItemIdent());
					tableKitchenDto.setParentId(item.getParent());
					tableKitchenDto.setCode(item.getCode());
					tableKitchenDto.setName(item.getName());
					tableKitchenDto.setStatus(item.getStatus());
					tableKitchenDto.setFlags(item.getFlags());
					tableKitchenDto.setRightLvl(item.getRightLvl());
					tableKitchenDto.setObjectSifr(item.getObjectSifr());
					tableKitchenDto.setCurrentRes(item.isIsCurrentRes());
					tableKitchenDto.setHallPlanId(item.getHall());
					tableKitchenDtos.add(tableKitchenDto);
				}
			});
		}
		return tableKitchenDtos;
	}
	
	public List<EmployeeDto> syncEmployeeFromRK7(String rk7Url, String rk7BasicAuth) {
		log.info("Entering syncEmployeeFromRK7()... ");
		
		RK7QueryResult rk7QueryResult = syncFromRK7(rk7Url, rk7BasicAuth, RK7QueryEnum.EMPLOYEE);
		List<EmployeeDto> employeeDtos = new ArrayList<>();
		if (rk7QueryResult != null) {
			rk7QueryResult.getCommandResult().getRK7Reference().getItems().getItem().stream().forEach(item -> {
				if (item != null) {
					EmployeeDto employeeDto = new EmployeeDto();
					employeeDto.setId(item.getIdent());
					employeeDto.setItemId(item.getItemIdent());
					employeeDto.setParentId(item.getMainParentIdent());
					employeeDto.setCode(item.getCode());
					employeeDto.setName(item.getName());
					employeeDto.setStatus(item.getStatus());
					employeeDto.setFlags(item.getFlags());
					employeeDto.setLogin(item.isIsLogin());
					employeeDto.setObjectSifr(item.getObjectSifr());
					employeeDtos.add(employeeDto);
				}
			});
		}
		return employeeDtos;
	}
	
	public List<GuestTypeDto> syncGuestTypeFromRK7(String rk7Url, String rk7BasicAuth) {
		log.info("Entering syncGuestTypeFromRK7()... ");
		
		RK7QueryResult rk7QueryResult = syncFromRK7(rk7Url, rk7BasicAuth, RK7QueryEnum.GUEST_TYPE);
		List<GuestTypeDto> guestTypeDtos = new ArrayList<>();
		if (rk7QueryResult != null) {
			rk7QueryResult.getCommandResult().getRK7Reference().getItems().getItem().stream().forEach(item -> {
				if (item != null) {
					GuestTypeDto guestTypeDto = new GuestTypeDto();
					guestTypeDto.setId(item.getIdent());
					guestTypeDto.setItemId(item.getItemIdent());
					guestTypeDto.setParentId(item.getParent());
					guestTypeDto.setCode(item.getCode());
					guestTypeDto.setName(item.getName());
					guestTypeDto.setObjectSifr(item.getObjectSifr());
					guestTypeDto.setStatus(item.getStatus());
					guestTypeDto.setFlags(item.getFlags());
					guestTypeDto.setRightLvl(item.getRightLvl());
					guestTypeDtos.add(guestTypeDto);
				}
			});
		}
		return guestTypeDtos;
	}


	public List<RestaurantMasterDto> syncRestaurantMasterFromRK7(String rk7Url, String rk7BasicAuth) {
		log.info("Entering syncRestaurantMasterFromRK7()... ");
		
		RK7QueryResult rk7QueryResult = syncFromRK7(rk7Url, rk7BasicAuth, RK7QueryEnum.RESTAURANT);
		List<RestaurantMasterDto> restaurantMasterDtos = new ArrayList<>();
		if (rk7QueryResult != null) {
			rk7QueryResult.getCommandResult().getRK7Reference().getItems().getItem().stream().forEach(item -> {
				if (item != null) {
					RestaurantMasterDto restaurantMasterDto = new RestaurantMasterDto();
					restaurantMasterDto.setId(item.getIdent());
					restaurantMasterDto.setItemId(item.getItemIdent());
					restaurantMasterDto.setParentId(item.getParent());
					restaurantMasterDto.setCode(item.getCode());
					restaurantMasterDto.setName(item.getName());
					restaurantMasterDto.setParentId(item.getMainParentIdent());
					restaurantMasterDto.setStatus(item.getStatus());
					restaurantMasterDto.setObjectSifr(item.getObjectSifr());
					restaurantMasterDto.setFlags(item.getFlags());
					restaurantMasterDto.setGenGggAdd(item.getGenGGGADD());
					restaurantMasterDto.setGenGggBrand(item.getGenGGGBRAND());
					restaurantMasterDto.setGenGggRco(item.getGenGGGRCO());
					restaurantMasterDto.setGenGggRname(item.getGenGGGRNAME());
					restaurantMasterDto.setGenGggTel(item.getGenGGGTEL());
					restaurantMasterDto.setGenGggWeb(item.getGenGGGWEB());
					restaurantMasterDto.setGenSapBankTerminalId(item.getGenSAPBANKTERMINALID());
					restaurantMasterDto.setGenSapCode(item.getGenSAPCODE());
					restaurantMasterDtos.add(restaurantMasterDto);
				}
			});
		}
		return restaurantMasterDtos;
	}
	
	public List<SapInfoDto> syncSapCodeFromRK7(String rk7Url, String rk7BasicAuth) {
		log.info("Entering syncSapCodeFromRK7()... ");
		
		RK7QueryResult rk7QueryResult = syncFromRK7(rk7Url, rk7BasicAuth, RK7QueryEnum.SAP_INFO);
		List<SapInfoDto> sapInfoDtos = new ArrayList<>();
		if (rk7QueryResult != null) {
			rk7QueryResult.getRK7Reference().getItems().getItem().stream().forEach(item -> {
				SapInfoDto sapInfoDto = new SapInfoDto();
				sapInfoDto.setId(item.getIdent());
				sapInfoDto.setItemIdent(item.getItemIdent());
				sapInfoDto.setObjectIdent(item.getObjectIdent());
				sapInfoDto.setObjectRefNo(item.getObjectRefNo());
				sapInfoDto.setRKTypeIdent(item.getRKTypeIdent());
				sapInfoDto.setSapCode(item.getPropertyVal());
				sapInfoDtos.add(sapInfoDto);
			});
		}
		return sapInfoDtos;
	}
	
}
