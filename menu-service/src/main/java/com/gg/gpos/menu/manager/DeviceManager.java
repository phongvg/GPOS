package com.gg.gpos.menu.manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gg.gpos.common.constant.SymbolEnum;
import com.gg.gpos.menu.dto.DeviceDto;
import com.gg.gpos.menu.entity.Device;
import com.gg.gpos.menu.entity.TableKitchen;
import com.gg.gpos.menu.mapper.DeviceMapper;
import com.gg.gpos.menu.repository.DeviceRepository;
import com.gg.gpos.menu.repository.TableKitchenRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class DeviceManager {
	@Autowired
	private DeviceRepository deviceRepository;
	@Autowired 
	private DeviceMapper deviceMapper;
	@Autowired
	private TableKitchenRepository tableKitchenRepository;
	
	public DeviceDto get(Long deviceId,Long tableId) {
		log.debug("Entering 'get by deviceId' method...");
		if(tableId != null) {
			return Optional.ofNullable(deviceRepository.findByDeviceIdAndAndTableKitchenIdIsNotNull(deviceId)).map(deviceMapper::entityToDto).orElse(null);
		}else {
			return Optional.ofNullable(deviceRepository.findByDeviceIdAndTableKitchenIdIsNull(deviceId)).map(deviceMapper::entityToDto).orElse(null);
		}
	}
	
	public List<DeviceDto> gets(Integer resCode){
		log.debug("Entering 'get by resCode' method...");
		List<DeviceDto> devices = deviceRepository.findByRestaurantCode(resCode).stream().map(deviceMapper::entityToDto).collect(Collectors.toList());
		if(devices != null && !devices.isEmpty()) {
			devices.stream().forEach(device -> {
				if(device.getTableKitchenId() != null) {
					TableKitchen tableKitchen = tableKitchenRepository.findById(device.getTableKitchenId()).get();
					if(tableKitchen != null) {
						String tableKitchenText = tableKitchen.getCode() + " - " + tableKitchen.getName();
						device.setTableKitchenText(tableKitchenText);
					}
				}
			});
		}
		return devices;
	}
	
	public void save(DeviceDto deviceDto){
		Integer code = deviceDto.getRestaurantCode();
		if(deviceDto.getRestaurantDeviceSelected() != null && !deviceDto.getRestaurantDeviceSelected().isEmpty()) {
			deviceRepository.deleteByRestaurantCodeAndTableKitchenIdIsNull(code);
			List<String> deviceId = new ArrayList<>(Arrays.asList(deviceDto.getRestaurantDeviceSelected().split(SymbolEnum.COMMA.val)));
			for(String id : deviceId) {
				Device devi = new Device();
				devi.setDeviceId(Long.parseLong(id));
				devi.setRestaurantCode(code);
				deviceRepository.save(devi);
			}
		}
		if(deviceDto.getDeviceInTableSelected() != null && !deviceDto.getDeviceInTableSelected().isEmpty() && deviceDto.getTableIdSelected() != null && !deviceDto.getTableIdSelected().isEmpty()) {
			List<String> deviceIdInTbale = new ArrayList<>(Arrays.asList(deviceDto.getDeviceInTableSelected().split(SymbolEnum.COMMA.val)));
			List<String> tableId = new ArrayList<>(Arrays.asList(deviceDto.getTableIdSelected().split(SymbolEnum.COMMA.val)));
			deviceRepository.deleteByRestaurantCodeAndTableKitchenIdIsNotNull(code);
			for(int i = 0 ; i < tableId.size() ; i++) {
				Device devi = new Device();
				devi.setDeviceId(Long.parseLong(deviceIdInTbale.get(i)));
				devi.setTableKitchenId(Long.parseLong(tableId.get(i)));
				devi.setRestaurantCode(code);
				deviceRepository.save(devi);
			}
		}
	}
	
	public List<DeviceDto> gets(Integer resCode, Long transceiverDeviceId, Long callingDeviceId) {
		return deviceRepository.findByRestaurantCodeAndDeviceIdOrDeviceId(resCode, transceiverDeviceId, callingDeviceId).stream().map(deviceMapper::entityToDto).collect(Collectors.toList());
	}
}
