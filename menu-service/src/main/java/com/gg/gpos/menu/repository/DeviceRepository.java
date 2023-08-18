package com.gg.gpos.menu.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.gg.gpos.menu.entity.Device;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long>, JpaSpecificationExecutor<Device>{
	Device findByDeviceIdAndTableKitchenIdIsNull(Long deviceId);
	Device findByDeviceIdAndAndTableKitchenIdIsNotNull(Long deviceId);
	List<Device> findByRestaurantCode(Integer resCode);
	public void deleteByRestaurantCodeAndTableKitchenIdIsNull(Integer resCode);
	public void deleteByRestaurantCodeAndTableKitchenIdIsNotNull(Integer resCode);
	List<Device> findByRestaurantCodeAndDeviceIdOrDeviceId(Integer resCode, Long transceiverDeviceId, Long callingDeviceId);
}
