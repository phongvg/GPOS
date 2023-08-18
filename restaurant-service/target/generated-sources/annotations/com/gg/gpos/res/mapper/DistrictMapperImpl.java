package com.gg.gpos.res.mapper;

import com.gg.gpos.res.dto.BrandDto;
import com.gg.gpos.res.dto.DistrictBrandDto;
import com.gg.gpos.res.dto.DistrictDto;
import com.gg.gpos.res.dto.ProvinceBrandDto;
import com.gg.gpos.res.dto.ProvinceDto;
import com.gg.gpos.res.dto.RestaurantDto;
import com.gg.gpos.res.dto.SyncStatusDto;
import com.gg.gpos.res.dto.ZoneDto;
import com.gg.gpos.res.entity.Brand;
import com.gg.gpos.res.entity.District;
import com.gg.gpos.res.entity.DistrictBrand;
import com.gg.gpos.res.entity.Province;
import com.gg.gpos.res.entity.ProvinceBrand;
import com.gg.gpos.res.entity.Restaurant;
import com.gg.gpos.res.entity.SyncStatus;
import com.gg.gpos.res.entity.Zone;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-01T17:40:07+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"
)
@Component
public class DistrictMapperImpl implements DistrictMapper {

    @Override
    public District dtoToEntity(DistrictDto districtDto) {
        if ( districtDto == null ) {
            return null;
        }

        District district = new District();

        district.setId( districtDto.getId() );
        district.setName( districtDto.getName() );
        district.setDisplayOrder( districtDto.getDisplayOrder() );
        district.setProvince( provinceDtoToProvince( districtDto.getProvince() ) );
        district.setDistrictBrands( districtBrandDtoListToDistrictBrandList( districtDto.getDistrictBrands() ) );

        return district;
    }

    @Override
    public DistrictDto entityToDto(District district) {
        if ( district == null ) {
            return null;
        }

        DistrictDto districtDto = new DistrictDto();

        districtDto.setId( district.getId() );
        districtDto.setName( district.getName() );
        districtDto.setDisplayOrder( district.getDisplayOrder() );
        districtDto.setProvince( provinceToProvinceDto( district.getProvince() ) );
        districtDto.setDistrictBrands( districtBrandListToDistrictBrandDtoList( district.getDistrictBrands() ) );

        return districtDto;
    }

    protected List<Province> provinceDtoListToProvinceList(List<ProvinceDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Province> list1 = new ArrayList<Province>( list.size() );
        for ( ProvinceDto provinceDto : list ) {
            list1.add( provinceDtoToProvince( provinceDto ) );
        }

        return list1;
    }

    protected Zone zoneDtoToZone(ZoneDto zoneDto) {
        if ( zoneDto == null ) {
            return null;
        }

        Zone zone = new Zone();

        zone.setId( zoneDto.getId() );
        zone.setCode( zoneDto.getCode() );
        zone.setName( zoneDto.getName() );
        zone.setProvinces( provinceDtoListToProvinceList( zoneDto.getProvinces() ) );

        return zone;
    }

    protected List<District> districtDtoListToDistrictList(List<DistrictDto> list) {
        if ( list == null ) {
            return null;
        }

        List<District> list1 = new ArrayList<District>( list.size() );
        for ( DistrictDto districtDto : list ) {
            list1.add( dtoToEntity( districtDto ) );
        }

        return list1;
    }

    protected Brand brandDtoToBrand(BrandDto brandDto) {
        if ( brandDto == null ) {
            return null;
        }

        Brand brand = new Brand();

        brand.setId( brandDto.getId() );
        brand.setName( brandDto.getName() );
        brand.setLongName( brandDto.getLongName() );
        brand.setDisplayOrder( brandDto.getDisplayOrder() );

        return brand;
    }

    protected List<Restaurant> restaurantDtoListToRestaurantList(List<RestaurantDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Restaurant> list1 = new ArrayList<Restaurant>( list.size() );
        for ( RestaurantDto restaurantDto : list ) {
            list1.add( restaurantDtoToRestaurant( restaurantDto ) );
        }

        return list1;
    }

    protected DistrictBrand districtBrandDtoToDistrictBrand(DistrictBrandDto districtBrandDto) {
        if ( districtBrandDto == null ) {
            return null;
        }

        DistrictBrand districtBrand = new DistrictBrand();

        districtBrand.setId( districtBrandDto.getId() );
        districtBrand.setDistrict( dtoToEntity( districtBrandDto.getDistrict() ) );
        districtBrand.setBrand( brandDtoToBrand( districtBrandDto.getBrand() ) );
        districtBrand.setRestaurants( restaurantDtoListToRestaurantList( districtBrandDto.getRestaurants() ) );

        return districtBrand;
    }

    protected SyncStatus syncStatusDtoToSyncStatus(SyncStatusDto syncStatusDto) {
        if ( syncStatusDto == null ) {
            return null;
        }

        SyncStatus syncStatus = new SyncStatus();

        syncStatus.setId( syncStatusDto.getId() );
        syncStatus.setRestaur( restaurantDtoToRestaurant( syncStatusDto.getRestaur() ) );
        syncStatus.setMasterDataSyncStatus( syncStatusDto.getMasterDataSyncStatus() );
        syncStatus.setBusinessSyncStatus( syncStatusDto.getBusinessSyncStatus() );

        return syncStatus;
    }

    protected List<SyncStatus> syncStatusDtoListToSyncStatusList(List<SyncStatusDto> list) {
        if ( list == null ) {
            return null;
        }

        List<SyncStatus> list1 = new ArrayList<SyncStatus>( list.size() );
        for ( SyncStatusDto syncStatusDto : list ) {
            list1.add( syncStatusDtoToSyncStatus( syncStatusDto ) );
        }

        return list1;
    }

    protected Restaurant restaurantDtoToRestaurant(RestaurantDto restaurantDto) {
        if ( restaurantDto == null ) {
            return null;
        }

        Restaurant restaurant = new Restaurant();

        restaurant.setId( restaurantDto.getId() );
        restaurant.setCode( restaurantDto.getCode() );
        restaurant.setName( restaurantDto.getName() );
        restaurant.setAddress( restaurantDto.getAddress() );
        restaurant.setPhone( restaurantDto.getPhone() );
        restaurant.setStatus( restaurantDto.getStatus() );
        restaurant.setLatitude( restaurantDto.getLatitude() );
        restaurant.setLongitude( restaurantDto.getLongitude() );
        restaurant.setEmail( restaurantDto.getEmail() );
        restaurant.setIp( restaurantDto.getIp() );
        restaurant.setPort( restaurantDto.getPort() );
        restaurant.setOnline( restaurantDto.getOnline() );
        restaurant.setVersion( restaurantDto.getVersion() );
        restaurant.setUnsignedName( restaurantDto.getUnsignedName() );
        restaurant.setDistrictBrand( districtBrandDtoToDistrictBrand( restaurantDto.getDistrictBrand() ) );
        restaurant.setProvinceBrand( provinceBrandDtoToProvinceBrand( restaurantDto.getProvinceBrand() ) );
        restaurant.setSyncStatus( syncStatusDtoListToSyncStatusList( restaurantDto.getSyncStatus() ) );

        return restaurant;
    }

    protected ProvinceBrand provinceBrandDtoToProvinceBrand(ProvinceBrandDto provinceBrandDto) {
        if ( provinceBrandDto == null ) {
            return null;
        }

        ProvinceBrand provinceBrand = new ProvinceBrand();

        provinceBrand.setId( provinceBrandDto.getId() );
        provinceBrand.setProvince( provinceDtoToProvince( provinceBrandDto.getProvince() ) );
        provinceBrand.setBrand( brandDtoToBrand( provinceBrandDto.getBrand() ) );
        provinceBrand.setRestaurants( restaurantDtoListToRestaurantList( provinceBrandDto.getRestaurants() ) );

        return provinceBrand;
    }

    protected List<ProvinceBrand> provinceBrandDtoListToProvinceBrandList(List<ProvinceBrandDto> list) {
        if ( list == null ) {
            return null;
        }

        List<ProvinceBrand> list1 = new ArrayList<ProvinceBrand>( list.size() );
        for ( ProvinceBrandDto provinceBrandDto : list ) {
            list1.add( provinceBrandDtoToProvinceBrand( provinceBrandDto ) );
        }

        return list1;
    }

    protected Province provinceDtoToProvince(ProvinceDto provinceDto) {
        if ( provinceDto == null ) {
            return null;
        }

        Province province = new Province();

        province.setId( provinceDto.getId() );
        province.setName( provinceDto.getName() );
        province.setDisplayOrder( provinceDto.getDisplayOrder() );
        province.setZone( zoneDtoToZone( provinceDto.getZone() ) );
        province.setDistricts( districtDtoListToDistrictList( provinceDto.getDistricts() ) );
        province.setProvinceBrands( provinceBrandDtoListToProvinceBrandList( provinceDto.getProvinceBrands() ) );

        return province;
    }

    protected List<DistrictBrand> districtBrandDtoListToDistrictBrandList(List<DistrictBrandDto> list) {
        if ( list == null ) {
            return null;
        }

        List<DistrictBrand> list1 = new ArrayList<DistrictBrand>( list.size() );
        for ( DistrictBrandDto districtBrandDto : list ) {
            list1.add( districtBrandDtoToDistrictBrand( districtBrandDto ) );
        }

        return list1;
    }

    protected List<ProvinceDto> provinceListToProvinceDtoList(List<Province> list) {
        if ( list == null ) {
            return null;
        }

        List<ProvinceDto> list1 = new ArrayList<ProvinceDto>( list.size() );
        for ( Province province : list ) {
            list1.add( provinceToProvinceDto( province ) );
        }

        return list1;
    }

    protected ZoneDto zoneToZoneDto(Zone zone) {
        if ( zone == null ) {
            return null;
        }

        ZoneDto zoneDto = new ZoneDto();

        zoneDto.setId( zone.getId() );
        zoneDto.setCode( zone.getCode() );
        zoneDto.setName( zone.getName() );
        zoneDto.setProvinces( provinceListToProvinceDtoList( zone.getProvinces() ) );

        return zoneDto;
    }

    protected List<DistrictDto> districtListToDistrictDtoList(List<District> list) {
        if ( list == null ) {
            return null;
        }

        List<DistrictDto> list1 = new ArrayList<DistrictDto>( list.size() );
        for ( District district : list ) {
            list1.add( entityToDto( district ) );
        }

        return list1;
    }

    protected BrandDto brandToBrandDto(Brand brand) {
        if ( brand == null ) {
            return null;
        }

        BrandDto brandDto = new BrandDto();

        brandDto.setId( brand.getId() );
        brandDto.setName( brand.getName() );
        brandDto.setLongName( brand.getLongName() );
        brandDto.setDisplayOrder( brand.getDisplayOrder() );

        return brandDto;
    }

    protected List<RestaurantDto> restaurantListToRestaurantDtoList(List<Restaurant> list) {
        if ( list == null ) {
            return null;
        }

        List<RestaurantDto> list1 = new ArrayList<RestaurantDto>( list.size() );
        for ( Restaurant restaurant : list ) {
            list1.add( restaurantToRestaurantDto( restaurant ) );
        }

        return list1;
    }

    protected DistrictBrandDto districtBrandToDistrictBrandDto(DistrictBrand districtBrand) {
        if ( districtBrand == null ) {
            return null;
        }

        DistrictBrandDto districtBrandDto = new DistrictBrandDto();

        districtBrandDto.setId( districtBrand.getId() );
        districtBrandDto.setDistrict( entityToDto( districtBrand.getDistrict() ) );
        districtBrandDto.setBrand( brandToBrandDto( districtBrand.getBrand() ) );
        districtBrandDto.setRestaurants( restaurantListToRestaurantDtoList( districtBrand.getRestaurants() ) );

        return districtBrandDto;
    }

    protected SyncStatusDto syncStatusToSyncStatusDto(SyncStatus syncStatus) {
        if ( syncStatus == null ) {
            return null;
        }

        SyncStatusDto syncStatusDto = new SyncStatusDto();

        syncStatusDto.setId( syncStatus.getId() );
        syncStatusDto.setMasterDataSyncStatus( syncStatus.getMasterDataSyncStatus() );
        syncStatusDto.setBusinessSyncStatus( syncStatus.getBusinessSyncStatus() );
        syncStatusDto.setRestaur( restaurantToRestaurantDto( syncStatus.getRestaur() ) );

        return syncStatusDto;
    }

    protected List<SyncStatusDto> syncStatusListToSyncStatusDtoList(List<SyncStatus> list) {
        if ( list == null ) {
            return null;
        }

        List<SyncStatusDto> list1 = new ArrayList<SyncStatusDto>( list.size() );
        for ( SyncStatus syncStatus : list ) {
            list1.add( syncStatusToSyncStatusDto( syncStatus ) );
        }

        return list1;
    }

    protected RestaurantDto restaurantToRestaurantDto(Restaurant restaurant) {
        if ( restaurant == null ) {
            return null;
        }

        RestaurantDto restaurantDto = new RestaurantDto();

        restaurantDto.setId( restaurant.getId() );
        restaurantDto.setCode( restaurant.getCode() );
        restaurantDto.setName( restaurant.getName() );
        restaurantDto.setAddress( restaurant.getAddress() );
        restaurantDto.setPhone( restaurant.getPhone() );
        restaurantDto.setStatus( restaurant.getStatus() );
        restaurantDto.setLatitude( restaurant.getLatitude() );
        restaurantDto.setLongitude( restaurant.getLongitude() );
        restaurantDto.setEmail( restaurant.getEmail() );
        restaurantDto.setIp( restaurant.getIp() );
        restaurantDto.setPort( restaurant.getPort() );
        restaurantDto.setOnline( restaurant.getOnline() );
        restaurantDto.setVersion( restaurant.getVersion() );
        restaurantDto.setUnsignedName( restaurant.getUnsignedName() );
        restaurantDto.setProvinceBrand( provinceBrandToProvinceBrandDto( restaurant.getProvinceBrand() ) );
        restaurantDto.setDistrictBrand( districtBrandToDistrictBrandDto( restaurant.getDistrictBrand() ) );
        restaurantDto.setSyncStatus( syncStatusListToSyncStatusDtoList( restaurant.getSyncStatus() ) );

        return restaurantDto;
    }

    protected ProvinceBrandDto provinceBrandToProvinceBrandDto(ProvinceBrand provinceBrand) {
        if ( provinceBrand == null ) {
            return null;
        }

        ProvinceBrandDto provinceBrandDto = new ProvinceBrandDto();

        provinceBrandDto.setId( provinceBrand.getId() );
        provinceBrandDto.setProvince( provinceToProvinceDto( provinceBrand.getProvince() ) );
        provinceBrandDto.setBrand( brandToBrandDto( provinceBrand.getBrand() ) );
        provinceBrandDto.setRestaurants( restaurantListToRestaurantDtoList( provinceBrand.getRestaurants() ) );

        return provinceBrandDto;
    }

    protected List<ProvinceBrandDto> provinceBrandListToProvinceBrandDtoList(List<ProvinceBrand> list) {
        if ( list == null ) {
            return null;
        }

        List<ProvinceBrandDto> list1 = new ArrayList<ProvinceBrandDto>( list.size() );
        for ( ProvinceBrand provinceBrand : list ) {
            list1.add( provinceBrandToProvinceBrandDto( provinceBrand ) );
        }

        return list1;
    }

    protected ProvinceDto provinceToProvinceDto(Province province) {
        if ( province == null ) {
            return null;
        }

        ProvinceDto provinceDto = new ProvinceDto();

        provinceDto.setId( province.getId() );
        provinceDto.setName( province.getName() );
        provinceDto.setDisplayOrder( province.getDisplayOrder() );
        provinceDto.setZone( zoneToZoneDto( province.getZone() ) );
        provinceDto.setDistricts( districtListToDistrictDtoList( province.getDistricts() ) );
        provinceDto.setProvinceBrands( provinceBrandListToProvinceBrandDtoList( province.getProvinceBrands() ) );

        return provinceDto;
    }

    protected List<DistrictBrandDto> districtBrandListToDistrictBrandDtoList(List<DistrictBrand> list) {
        if ( list == null ) {
            return null;
        }

        List<DistrictBrandDto> list1 = new ArrayList<DistrictBrandDto>( list.size() );
        for ( DistrictBrand districtBrand : list ) {
            list1.add( districtBrandToDistrictBrandDto( districtBrand ) );
        }

        return list1;
    }
}
