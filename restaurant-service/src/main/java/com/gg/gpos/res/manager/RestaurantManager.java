package com.gg.gpos.res.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.gg.gpos.common.constant.MapKeyEnum;
import com.gg.gpos.common.constant.StatusEnum;
import com.gg.gpos.common.constant.StatusSyncEnum;
import com.gg.gpos.common.constant.SymbolEnum;
import com.gg.gpos.common.util.FancyTree;
import com.gg.gpos.res.dto.BrandDto;
import com.gg.gpos.res.dto.DistrictDto;
import com.gg.gpos.res.dto.ProvinceBrandDto;
import com.gg.gpos.res.dto.ProvinceDto;
import com.gg.gpos.res.dto.RestaurantDto;
import com.gg.gpos.res.dto.SyncStatusDto;
import com.gg.gpos.res.dto.ZoneDto;
import com.gg.gpos.res.entity.Brand;
import com.gg.gpos.res.entity.District;
import com.gg.gpos.res.entity.Province;
import com.gg.gpos.res.entity.ProvinceBrand;
import com.gg.gpos.res.entity.Restaurant;
import com.gg.gpos.res.entity.RestaurantUser;
import com.gg.gpos.res.entity.SyncStatus;
import com.gg.gpos.res.entity.Zone;
import com.gg.gpos.res.mapper.BrandMapper;
import com.gg.gpos.res.mapper.DistrictMapper;
import com.gg.gpos.res.mapper.ProvinceBrandMapper;
import com.gg.gpos.res.mapper.ProvinceMapper;
import com.gg.gpos.res.mapper.RestaurantMapper;
import com.gg.gpos.res.mapper.SyncStatusMapper;
import com.gg.gpos.res.mapper.ZoneMapper;
import com.gg.gpos.res.repository.BrandRepository;
import com.gg.gpos.res.repository.DistrictRepository;
import com.gg.gpos.res.repository.ProvinceBrandRepository;
import com.gg.gpos.res.repository.ProvinceRepository;
import com.gg.gpos.res.repository.RestaurantRepository;
import com.gg.gpos.res.repository.RestaurantUserRepository;
import com.gg.gpos.res.repository.SyncStatusRepository;
import com.gg.gpos.res.repository.ZoneRepository;
import com.gg.gpos.res.specification.RestaurantSpecification;
import lombok.extern.slf4j.Slf4j;

/**
 * Generated by Speed Generator
 * 
 * @author <a href="mailto:ngtrungkien@gmail.com">Kien Nguyen</a>
 */
@Slf4j
@Service
@Transactional
public class RestaurantManager {
	@Autowired
	private ZoneMapper zoneMapper;
	@Autowired
	private ZoneRepository zoneRepository;
	@Autowired
	private ProvinceMapper provinceMapper;
	@Autowired
	private ProvinceRepository provinceRepository;
	@Autowired
	private DistrictMapper districtMapper;
	@Autowired
	private DistrictRepository districtRepository;
	@Autowired
	private RestaurantRepository restaurantRepository;
	@Autowired
	private RestaurantMapper restaurantMapper;
	@Autowired
	private BrandRepository brandRepository;
	@Autowired
	private BrandMapper brandMapper;
	@Autowired
	private ProvinceBrandRepository provinceBrandRepository;
	@Autowired
	private ProvinceBrandMapper provinceBrandMapper;
	@Autowired
	private RestaurantUserRepository restaurantUserRepository;
	@Autowired
	private RestaurantSpecification restaurantSpecification;
	@Autowired
	private SyncStatusRepository syncStatusRepository;
	@Autowired
	private SyncStatusMapper syncStatusMapper;
	public RestaurantDto get(Long id) {
		return restaurantRepository.findById(id).map(restaurantMapper::entityToDto).orElse(null);
	}
	
	public RestaurantDto get(Integer code) {
		return Optional.ofNullable(restaurantRepository.findByCode(code)).map(restaurantMapper::entityToDto).orElse(null);
	}
	
	public List<RestaurantDto> gets() {
		return restaurantRepository.findByStatus(StatusEnum.ACTIVE.status).stream().map(restaurantMapper::entityToDto).collect(Collectors.toList());
	}
	
	public List<RestaurantDto> getAll() {
		return restaurantRepository.findAll().stream().map(restaurantMapper::entityToDto).collect(Collectors.toList());
	}
	
	public RestaurantDto save(RestaurantDto restaurantDto) {
		Restaurant restaurant = Optional.ofNullable(restaurantDto).map(restaurantMapper::dtoToEntity).orElse(null);
		if (restaurant != null) {
			return Optional.ofNullable(restaurantRepository.save(restaurant)).map(restaurantMapper::entityToDto).orElse(null);
		} else {
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	public void syncResFromServer(Map<String, Object> map) {
		log.info("Entering syncFromServer()... ");
		
		// save zone information
		List<ZoneDto> fromZones = (List<ZoneDto>)map.get(MapKeyEnum.ZONE.key);
		fromZones.stream().forEach(z -> {
			Zone zone = Optional.ofNullable(z).map(zoneMapper::dtoToEntity).orElse(null);
			zone = zoneRepository.save(zone);
		});
		
		// save province information
		List<ProvinceDto> fromProvinces = (List<ProvinceDto>)map.get(MapKeyEnum.PROVINCE.key);
		fromProvinces.stream().forEach(p -> {
			Province province = Optional.ofNullable(p).map(provinceMapper::dtoToEntity).orElse(null);
			if (province != null) {
				provinceRepository.save(province);
			}
		});
		
		// save district information
		List<DistrictDto> fromDistricts = (List<DistrictDto>)map.get(MapKeyEnum.DISTRICT.key);
		fromDistricts.stream().forEach(d -> {
			District district = Optional.ofNullable(d).map(districtMapper::dtoToEntity).orElse(null);
			if (district != null) {
				districtRepository.save(district);
			}
		});
		
		// save brand information
		List<BrandDto> fromBrands = (List<BrandDto>)map.get(MapKeyEnum.BRAND.key);
		fromBrands.stream().forEach(b -> {
			Brand brand = Optional.ofNullable(b).map(brandMapper::dtoToEntity).orElse(null);
			if (brand != null) {
				brandRepository.save(brand);
			}
		});
		
		// save province brand information
		Map<String, ProvinceBrand> provinceBrandMap = new HashMap<>();
		List<ProvinceBrandDto> fromProvinceBrands = (List<ProvinceBrandDto>)map.get(MapKeyEnum.PROVINCE_BRAND.key);
		fromProvinceBrands.stream().forEach(db -> {
			ProvinceBrand provinceBrand = Optional.ofNullable(db).map(provinceBrandMapper::dtoToEntity).orElse(null);
			if (provinceBrand != null) {
				ProvinceBrand persistProvinceBrand = Optional.ofNullable(provinceBrandRepository.findByProvinceAndBrand(provinceBrand.getProvince(), provinceBrand.getBrand())).orElse(new ProvinceBrand());
				persistProvinceBrand.setProvince(provinceBrand.getProvince());
				persistProvinceBrand.setBrand(provinceBrand.getBrand());
				persistProvinceBrand = provinceBrandRepository.save(persistProvinceBrand);
				provinceBrandMap.put(persistProvinceBrand.getProvince().getId() + "_" + persistProvinceBrand.getBrand().getId(), persistProvinceBrand);
			}
		});
		// save restaurant information
		List<RestaurantDto> fromRestaurants = (List<RestaurantDto>)map.get(MapKeyEnum.RES.key);
		fromRestaurants.stream().forEach(r -> {
			Restaurant restaurant = Optional.ofNullable(r).map(restaurantMapper::dtoToEntity).orElse(null);
			if (restaurant != null) {
				Restaurant persistRestaurant = Optional.ofNullable(restaurantRepository.findByCode(restaurant.getCode())).orElse(new Restaurant());
				persistRestaurant.setAddress(restaurant.getAddress());
				persistRestaurant.setProvinceBrand(provinceBrandMap.get(restaurant.getProvinceBrand().getProvince().getId() + "_" + restaurant.getProvinceBrand().getBrand().getId()));
				persistRestaurant.setCode(restaurant.getCode());
				persistRestaurant.setEmail(restaurant.getEmail());
				persistRestaurant.setIp(restaurant.getIp());
				persistRestaurant.setPort(restaurant.getPort());
				persistRestaurant.setLatitude(restaurant.getLatitude());
				persistRestaurant.setLongitude(restaurant.getLongitude());
				persistRestaurant.setName(restaurant.getName());
				persistRestaurant.setPhone(restaurant.getPhone());
				persistRestaurant.setStatus(restaurant.getStatus());
				
				restaurantRepository.save(persistRestaurant);
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	public void syncFromServer(Map<String, Object> map) {
		log.info("Entering syncFromServer()... ");
		
		// save zone information
		List<ZoneDto> fromZones = (List<ZoneDto>)map.get(MapKeyEnum.ZONE.key);
		fromZones.stream().forEach(z -> {
			Zone zone = Optional.ofNullable(z).map(zoneMapper::dtoToEntity).orElse(null);
			zone = zoneRepository.save(zone);
		});
		
		// save province information
		List<ProvinceDto> fromProvinces = (List<ProvinceDto>)map.get(MapKeyEnum.PROVINCE.key);
		fromProvinces.stream().forEach(p -> {
			Province province = Optional.ofNullable(p).map(provinceMapper::dtoToEntity).orElse(null);
			if (province != null) {
				provinceRepository.save(province);
			}
		});
		
		// save district information
		List<DistrictDto> fromDistricts = (List<DistrictDto>)map.get(MapKeyEnum.DISTRICT.key);
		fromDistricts.stream().forEach(d -> {
			District district = Optional.ofNullable(d).map(districtMapper::dtoToEntity).orElse(null);
			if (district != null) {
				districtRepository.save(district);
			}
		});
		
		// save brand information
		List<BrandDto> fromBrands = (List<BrandDto>)map.get(MapKeyEnum.BRAND.key);
		fromBrands.stream().forEach(b -> {
			Brand brand = Optional.ofNullable(b).map(brandMapper::dtoToEntity).orElse(null);
			if (brand != null) {
				brandRepository.save(brand);
			}
		});
		
		// save province brand information
		Map<String, ProvinceBrand> provinceBrandMap = new HashMap<>();
		List<ProvinceBrandDto> fromProvinceBrands = (List<ProvinceBrandDto>)map.get(MapKeyEnum.PROVINCE_BRAND.key);
		fromProvinceBrands.stream().forEach(db -> {
			ProvinceBrand provinceBrand = Optional.ofNullable(db).map(provinceBrandMapper::dtoToEntity).orElse(null);
			if (provinceBrand != null) {
				ProvinceBrand persistProvinceBrand = Optional.ofNullable(provinceBrandRepository.findByProvinceAndBrand(provinceBrand.getProvince(), provinceBrand.getBrand())).orElse(new ProvinceBrand());
				persistProvinceBrand.setProvince(provinceBrand.getProvince());
				persistProvinceBrand.setBrand(provinceBrand.getBrand());
				persistProvinceBrand = provinceBrandRepository.save(persistProvinceBrand);
				provinceBrandMap.put(persistProvinceBrand.getProvince().getId() + "_" + persistProvinceBrand.getBrand().getId(), persistProvinceBrand);
			}
		});
		
		// save restaurant information
		List<RestaurantDto> fromRestaurants = (List<RestaurantDto>)map.get(MapKeyEnum.RES.key);
		List<Integer> existingRestaurantCodes = restaurantRepository.findAll().stream().map(r -> r.getCode()).collect(Collectors.toList());;
		List<Integer> resCodes = new ArrayList<>();
		fromRestaurants.stream().forEach(r -> {
			Restaurant restaurant = Optional.ofNullable(r).map(restaurantMapper::dtoToEntity).orElse(null);
			if (restaurant != null) {
				Restaurant persistRestaurant = Optional.ofNullable(restaurantRepository.findByCode(restaurant.getCode())).orElse(new Restaurant());
				persistRestaurant.setAddress(restaurant.getAddress());
				persistRestaurant.setProvinceBrand(provinceBrandMap.get(restaurant.getProvinceBrand().getProvince().getId() + "_" + restaurant.getProvinceBrand().getBrand().getId()));
				persistRestaurant.setCode(restaurant.getCode());
				persistRestaurant.setEmail(restaurant.getEmail());
				persistRestaurant.setIp(restaurant.getIp());
				persistRestaurant.setPort(restaurant.getPort());
				persistRestaurant.setLatitude(restaurant.getLatitude());
				persistRestaurant.setLongitude(restaurant.getLongitude());
				persistRestaurant.setName(restaurant.getName());
				persistRestaurant.setPhone(restaurant.getPhone());
				persistRestaurant.setStatus(restaurant.getStatus());
				persistRestaurant.setUnsignedName(org.apache.commons.lang3.StringUtils.stripAccents(restaurant.getName()).toLowerCase());
				resCodes.add(restaurant.getCode());				
				Restaurant res = restaurantRepository.save(persistRestaurant);
				
				// set table sync status
				SyncStatus syncStatus = syncStatusRepository.findByRestaur(restaurant);
				if(syncStatus == null) {
					syncStatus = new SyncStatus();
				}
				syncStatus.setMasterDataSyncStatus(StatusSyncEnum.NOT_SYNC.val);
				syncStatus.setRestaur(res);
				syncStatusRepository.save(syncStatus);
			}
		});
		
		if(existingRestaurantCodes != null && !existingRestaurantCodes.isEmpty() && !resCodes.isEmpty()) {
			existingRestaurantCodes.stream().forEach(rCode ->{
				if(!resCodes.contains(rCode)) {
					Restaurant restaurant = restaurantRepository.findByCode(rCode);
					restaurant.setStatus(StatusEnum.INACTIVE.status);
					restaurantRepository.save(restaurant);
				}
			});
		}
	}
	
	public Page<RestaurantDto> gets(RestaurantDto criteria) {
		Page<Restaurant> page = restaurantRepository.findAll(restaurantSpecification.filter(criteria), PageRequest.of(criteria.getPage(), criteria.getSize()));
		return new PageImpl<>(page.getContent().stream().map(restaurantMapper::entityToDto).collect(Collectors.toList()), PageRequest.of(criteria.getPage(), criteria.getSize()), page.getTotalElements());
	}

	public Long countRestaurant() {
		return restaurantRepository.count();
	}
	
	public Long countRestaurantByOnOffline(Integer status) {
		return restaurantRepository.countByOnline(status);
	}
	
	public List<RestaurantDto> getRelatedRestaurants(Integer restaurantCode){
		log.debug("entering 'getRelatedRestaurants' method...");
		return restaurantRepository.findRelatedRestaurants(restaurantCode).stream().map(restaurantMapper::entityToDto).collect(Collectors.toList());
	}
	
	public List<Integer> getAppliedRestaurantCodes(String username) {
		log.debug("entering 'getAppliedRestaurantCodes' method...");
		List<RestaurantUser> restaurantUsers = restaurantUserRepository.findByUsername(username);
		return restaurantUsers.stream().map(r -> r.getRestaurant().getCode()).collect(Collectors.toList());
	}
	
	public FancyTree getFancyTree(FancyTree comNode,List<Integer> resCodeSelecteds, String keyword) {
		log.debug("PROCESS: GET ALL FANCY_TREE BY KEYWORD");
		if(StringUtils.isNotBlank(keyword)) {
			return getTreeByKeyword(comNode, resCodeSelecteds, keyword);
		} else {
			return getTree(comNode, resCodeSelecteds);
		}
	}
	
	/*
	 * Get all tree theo keyword và list code nhà hàng đang áp dụng đồng bộ
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private FancyTree getTreeByKeyword(FancyTree comNode,List<Integer> resCodeSelecteds, String keyword) {
		log.debug("PROCESS: GET FANCY_TREE BY KEYWORD AND APPLIED_RESTAURANT,KEYWORD: {}, APPLIED_RESTAURANT: {}",keyword, resCodeSelecteds);
		List<Restaurant> res = new ArrayList<>();
		try {
			Integer resCode = Integer.parseInt(keyword);
			res = restaurantRepository.findByCodeOrUnsignedNameContainingAndStatus(resCode, keyword,StatusEnum.ACTIVE.status); 
		} catch (Exception e) {
			res = restaurantRepository.findByUnsignedNameContainingAndStatus(keyword,StatusEnum.ACTIVE.status); 
		}
		if(!CollectionUtils.isEmpty(resCodeSelecteds)) {
			res.addAll(restaurantRepository.findByCodeIn(resCodeSelecteds));
		}
		List<Integer> resCodes = res.stream().map(r -> r.getCode()).collect(Collectors.toList());
		List<ProvinceBrand> proBrands = res.stream().map(r -> r.getProvinceBrand()).collect(Collectors.toList());
		if(proBrands != null && !proBrands.isEmpty()) {
			List<Long> provinceBrandIds = proBrands.stream().map(pb -> pb.getId()).collect(Collectors.toList());
			List<Long> provinceIds = proBrands.stream().map(pb -> pb.getProvince().getId()).collect(Collectors.toList());
			List<Zone> zones = zoneRepository.findAll();
	    	if (!zones.isEmpty()) {
	    		List zoneNodes = new ArrayList();
	    		zones.stream().forEach(z -> {
	    			FancyTree zoneNode = new FancyTree();
	    			zoneNode.setTitle(z.getName());
	    			List<Province> provinces = z.getProvinces();
	    			if (provinces != null && !provinces.isEmpty()) {
	    				List provinceNodes = new ArrayList();
	    				provinces.stream().forEach(p -> {
	    					if(provinceIds.contains(p.getId())) {
	    						FancyTree provinceNode = new FancyTree();
		    					provinceNode.setTitle(p.getName());
									List<ProvinceBrand> provinceBrands = p.getProvinceBrands();
									if (provinceBrands != null && !provinceBrands.isEmpty()) {
										List brandNodes = new ArrayList();
										provinceBrands.stream().forEach(b -> {
											if(provinceBrandIds.contains(b.getId())) {
												FancyTree brandNode = new FancyTree();
												brandNode.setTitle(b.getBrand().getName());
												List<Restaurant> restaurants = b.getRestaurants();
												if (restaurants != null && !restaurants.isEmpty()) {
													List restaurantNodes = new ArrayList();
													restaurants.stream().forEach(r -> {
														if(r.getStatus().equals(StatusEnum.ACTIVE.status) && resCodes.contains(r.getCode())) {
															FancyTree restaurantNode = new FancyTree();
															StringBuilder title = new StringBuilder();
															title.append(r.getCode()).append(SymbolEnum.SPACE.val).append(SymbolEnum.HYPHEN.val).append(SymbolEnum.SPACE.val).append(r.getName());
															restaurantNode.setTitle(title.toString());
															restaurantNode.setKey(r.getCode().toString());
															if (resCodeSelecteds != null && !resCodeSelecteds.isEmpty() && resCodeSelecteds.contains(r.getCode())) {
																restaurantNode.setSelected(true);
															}
															restaurantNodes.add(restaurantNode);
														}
													});
													brandNode.setChildren(restaurantNodes);
												}
												brandNodes.add(brandNode);
											}
										});
										provinceNode.setChildren(brandNodes);
									}
		    					provinceNodes.add(provinceNode);
	    					}
	    				});
	    				zoneNode.setChildren(provinceNodes);
	    			}
	    			zoneNodes.add(zoneNode);
	    		});
	    		comNode.setChildren(zoneNodes);
	    	}
		}
    	return comNode;
	}

	/*
	 * Get all tree theo list code nhà hàng đang áp dụng đồng bộ
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private FancyTree getTree(FancyTree comNode, List<Integer> appliedRestaurants) {
		log.debug("PROCESS: GET FANCY_TREE, APPLIED_RESTAURANT: {}", appliedRestaurants);
    	List<Zone> zones = zoneRepository.findAll();
    	if (!zones.isEmpty()) {
    		List zoneNodes = new ArrayList();
    		zones.stream().forEach(z -> {
    			FancyTree zoneNode = new FancyTree();
    			zoneNode.setTitle(z.getName());
    			List<Province> provinces = z.getProvinces();
    			if (provinces != null && !provinces.isEmpty()) {
    				
    				List provinceNodes = new ArrayList();
    				provinces.stream().forEach(p -> {
    					FancyTree provinceNode = new FancyTree();
    					provinceNode.setTitle(p.getName());
							List<ProvinceBrand> provinceBrands = p.getProvinceBrands();
							if (provinceBrands != null && !provinceBrands.isEmpty()) {
								List brandNodes = new ArrayList();
								provinceBrands.stream().forEach(b -> {
									FancyTree brandNode = new FancyTree();
									brandNode.setTitle(b.getBrand().getName());
									List<Restaurant> restaurants = b.getRestaurants();
									if (restaurants != null && !restaurants.isEmpty()) {
										List restaurantNodes = new ArrayList();
										restaurants.stream().forEach(r -> {
											if(r.getStatus().equals(StatusEnum.ACTIVE.status)) {
												FancyTree restaurantNode = new FancyTree();
												StringBuilder title = new StringBuilder();
												title.append(r.getCode()).append(SymbolEnum.SPACE.val).append(SymbolEnum.HYPHEN.val).append(SymbolEnum.SPACE.val).append(r.getName());
												restaurantNode.setTitle(title.toString());
												restaurantNode.setKey(r.getCode().toString());
												if (appliedRestaurants != null && !appliedRestaurants.isEmpty() && appliedRestaurants.contains(r.getCode())) {
													restaurantNode.setSelected(true);
												}
												restaurantNodes.add(restaurantNode);
											}
										});
										brandNode.setChildren(restaurantNodes);
									}
									brandNodes.add(brandNode);
								});
								provinceNode.setChildren(brandNodes);
							}
    					provinceNodes.add(provinceNode);
    				});
    				zoneNode.setChildren(provinceNodes);
    			}
    			zoneNodes.add(zoneNode);
    		});
    		comNode.setChildren(zoneNodes);
    	}
    	return comNode;
	}
	
	public List<RestaurantDto> getRestaurantByRestaurantCodes(List<String> restaurantCodes) {
		log.debug("PROCESS FUNCTION: GET RESTAURANT_DTOS BY RESTAURANT_CODES, RESTAURANT_CODES: {}", restaurantCodes);
		List<RestaurantDto> restaurantDtos = new ArrayList<>();
		try {
			restaurantCodes.stream().forEach(code ->{
				Integer restaurantCode = Integer.parseInt(code);
				restaurantDtos.add(Optional.ofNullable(restaurantRepository.findByCode(restaurantCode)).map(restaurantMapper::entityToDto).orElse(null));
			});
		} catch (Exception e) {
			log.debug("ERROR PROCESS FUNCTION: GET RESTAURANT_DTOS BY RESTAURANT_CODES, EXCEPTION: {}", e);
		}
		return restaurantDtos;
	}
	
	public List<RestaurantDto> getsRestaurantDtoWithSyncStatusFailOrNotSync(){
		log.debug("PROCESS FUNCTION: GET RESTAURANT_DTOS WITH SYNC_STATUS FAIL OR NOT SYNC");
		try {
			List<SyncStatusDto> syncStatusDtos = syncStatusRepository.findBySttSyncMasterData(StatusSyncEnum.SYNC_FAIL.val,StatusSyncEnum.NOT_SYNC.val).stream().map(syncStatusMapper::entityToDto).collect(Collectors.toList());
			return syncStatusDtos.stream().map(ss -> ss.getRestaur()).collect(Collectors.toList());
		} catch (Exception e) {
			log.error("ERROR PROCESS FUNCTION: GET RESTAURANT_DTOS WITH SYNC_STATUS FAIL OR NOT SYNC EXCEPTION: {}");
		}
		return new ArrayList<>();
	}
	
	public List<RestaurantDto> getsRestaurantDtoWithSyncStatusBusinessDataFailOrNotSync(){
		log.debug("PROCESS FUNCTION: GET RESTAURANT_DTOS WITH SYNC_STATUS BUSINESS_DATA FAIL OR NOT SYNC");
		try {
			List<SyncStatusDto> syncStatusDtos = syncStatusRepository.findBySttSyncBusinessData(StatusSyncEnum.SYNC_FAIL.val,StatusSyncEnum.NOT_SYNC.val).stream().map(syncStatusMapper::entityToDto).collect(Collectors.toList());
			return syncStatusDtos.stream().map(ss -> ss.getRestaur()).collect(Collectors.toList());
		} catch (Exception e) {
			log.error("ERROR PROCESS FUNCTION: GET RESTAURANT_DTOS WITH SYNC_STATUS BUSINESS_DATA FAIL OR NOT SYNC EXCEPTION: {}");
		}
		return new ArrayList<>();
	}
}