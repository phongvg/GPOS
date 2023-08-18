package com.gg.gpos.menu.mapper;

import com.gg.gpos.menu.dto.DeviceDto;
import com.gg.gpos.menu.entity.Device;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-01T17:40:57+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"
)
@Component
public class DeviceMapperImpl implements DeviceMapper {

    @Override
    public Device dtoToEntity(DeviceDto deviceDto) {
        if ( deviceDto == null ) {
            return null;
        }

        Device device = new Device();

        device.setId( deviceDto.getId() );
        device.setRestaurantCode( deviceDto.getRestaurantCode() );
        device.setTableKitchenId( deviceDto.getTableKitchenId() );
        device.setDeviceId( deviceDto.getDeviceId() );

        return device;
    }

    @Override
    public DeviceDto entityToDto(Device device) {
        if ( device == null ) {
            return null;
        }

        DeviceDto deviceDto = new DeviceDto();

        deviceDto.setId( device.getId() );
        deviceDto.setRestaurantCode( device.getRestaurantCode() );
        deviceDto.setTableKitchenId( device.getTableKitchenId() );
        deviceDto.setDeviceId( device.getDeviceId() );

        return deviceDto;
    }
}
