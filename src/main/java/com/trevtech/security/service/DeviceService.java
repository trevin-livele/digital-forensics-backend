package com.trevtech.security.service;
import com.trevtech.security.payload.DeviceDto;
import com.trevtech.security.payload.DeviceResponse;
import com.trevtech.security.payload.FileDto;

import java.util.List;

public interface DeviceService {

    DeviceDto createDevice(long fileId, DeviceDto deviceDto);

    DeviceDto getDeviceById(Long fileId, Long deviceId);

    DeviceDto updateDevice(Long fileId, long deviceId, DeviceDto deviceRequest);

    void deleteDeviceById(Long fileId, Long deviceId);

    List<DeviceDto> getDeviceByFileId(long deviceId);
}