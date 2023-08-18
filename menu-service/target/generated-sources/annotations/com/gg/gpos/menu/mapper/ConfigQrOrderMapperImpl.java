package com.gg.gpos.menu.mapper;

import com.gg.gpos.menu.dto.ConfigQrOrderDto;
import com.gg.gpos.menu.entity.ConfigQrOrder;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-01T17:40:57+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"
)
@Component
public class ConfigQrOrderMapperImpl implements ConfigQrOrderMapper {

    @Override
    public ConfigQrOrder dtoToEntity(ConfigQrOrderDto configQrOrderDto) {
        if ( configQrOrderDto == null ) {
            return null;
        }

        ConfigQrOrder configQrOrder = new ConfigQrOrder();

        configQrOrder.setId( configQrOrderDto.getId() );
        configQrOrder.setCoId( configQrOrderDto.getCoId() );
        configQrOrder.setRestaurantCode( configQrOrderDto.getRestaurantCode() );
        configQrOrder.setIp( configQrOrderDto.getIp() );
        configQrOrder.setPort( configQrOrderDto.getPort() );
        configQrOrder.setSrcImgInfo( configQrOrderDto.getSrcImgInfo() );
        configQrOrder.setUrlImgInfo( configQrOrderDto.getUrlImgInfo() );
        configQrOrder.setSrcImgLogo( configQrOrderDto.getSrcImgLogo() );
        configQrOrder.setUrlImgLogo( configQrOrderDto.getUrlImgLogo() );
        configQrOrder.setSrcImgCloseOrder( configQrOrderDto.getSrcImgCloseOrder() );
        configQrOrder.setUrlImgCloseOrder( configQrOrderDto.getUrlImgCloseOrder() );
        configQrOrder.setCreatedBy( configQrOrderDto.getCreatedBy() );
        configQrOrder.setCreatedDate( configQrOrderDto.getCreatedDate() );
        configQrOrder.setModifiedBy( configQrOrderDto.getModifiedBy() );
        configQrOrder.setModifiedDate( configQrOrderDto.getModifiedDate() );

        return configQrOrder;
    }

    @Override
    public ConfigQrOrderDto entityToDto(ConfigQrOrder configQrOrder) {
        if ( configQrOrder == null ) {
            return null;
        }

        ConfigQrOrderDto configQrOrderDto = new ConfigQrOrderDto();

        configQrOrderDto.setId( configQrOrder.getId() );
        configQrOrderDto.setCoId( configQrOrder.getCoId() );
        configQrOrderDto.setRestaurantCode( configQrOrder.getRestaurantCode() );
        configQrOrderDto.setIp( configQrOrder.getIp() );
        configQrOrderDto.setPort( configQrOrder.getPort() );
        configQrOrderDto.setSrcImgInfo( configQrOrder.getSrcImgInfo() );
        configQrOrderDto.setUrlImgInfo( configQrOrder.getUrlImgInfo() );
        configQrOrderDto.setSrcImgLogo( configQrOrder.getSrcImgLogo() );
        configQrOrderDto.setUrlImgLogo( configQrOrder.getUrlImgLogo() );
        configQrOrderDto.setSrcImgCloseOrder( configQrOrder.getSrcImgCloseOrder() );
        configQrOrderDto.setUrlImgCloseOrder( configQrOrder.getUrlImgCloseOrder() );
        configQrOrderDto.setCreatedBy( configQrOrder.getCreatedBy() );
        configQrOrderDto.setCreatedDate( configQrOrder.getCreatedDate() );
        configQrOrderDto.setModifiedBy( configQrOrder.getModifiedBy() );
        configQrOrderDto.setModifiedDate( configQrOrder.getModifiedDate() );

        return configQrOrderDto;
    }
}
