package com.gg.gpos.api;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeviceRequest {
	private Integer resCode;
	private Long transceiverDeviceId;
	private Long callingDeviceId;
}
