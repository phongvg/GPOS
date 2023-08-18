package com.gg.gpos.menu.mapper;
import org.mapstruct.Mapper;

import com.gg.gpos.menu.dto.DeviceDto;
import com.gg.gpos.menu.entity.Device;

@Mapper(componentModel = "spring")
public interface DeviceMapper {
	Device dtoToEntity(DeviceDto deviceDto);
	DeviceDto entityToDto(Device device);
}
