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
    date = "2023-08-01T17:40:06+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"
)
@Component
public class ZoneMapperImpl implements ZoneMapper {

    @Override
    public Zone dtoToEntity(ZoneDto zoneDto) {
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

    @Override
    public ZoneDto entityToDto(Zone zone) {
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

    @Override
    public ProvinceDto provinceToProvinceDto(Province province) {
        if ( province == null ) {
            return null;
        }

        ProvinceDto provinceDto = new ProvinceDto();

        provinceDto.setId( province.getId() );
        provinceDto.setName( province.getName() );
        provinceDto.setDisplayOrder( province.getDisplayOrder() );

        provinceDto.setDistricts( null );
        provinceDto.setProvinceBrands( null );
        provinceDto.setZone( null );

        return provinceDto;
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

    protected DistrictBrand districtBrandDtoToDistrictBrand(DistrictBrandDto districtBrandDto) {
        if ( districtBrandDto == null ) {
            return null;
        }

        DistrictBrand districtBrand = new DistrictBrand();

        districtBrand.setId( districtBrandDto.getId() );
        districtBrand.setDistrict( districtDtoToDistrict( districtBrandDto.getDistrict() ) );
        districtBrand.setBrand( brandDtoToBrand( districtBrandDto.getBrand() ) );
        districtBrand.setRestaurants( restaurantDtoListToRestaurantList( districtBrandDto.getRestaurants() ) );

        return districtBrand;
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

    protected District districtDtoToDistrict(DistrictDto districtDto) {
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

    protected List<District> districtDtoListToDistrictList(List<DistrictDto> list) {
        if ( list == null ) {
            return null;
        }

        List<District> list1 = new ArrayList<District>( list.size() );
        for ( DistrictDto districtDto : list ) {
            list1.add( districtDtoToDistrict( districtDto ) );
        }

        return list1;
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
        province.setZone( dtoToEntity( provinceDto.getZone() ) );
        province.setDistricts( districtDtoListToDistrictList( provinceDto.getDistricts() ) );
        province.setProvinceBrands( provinceBrandDtoListToProvinceBrandList( provinceDto.getProvinceBrands() ) );

        return province;
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
}
