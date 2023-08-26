package com.trevtech.security.payload;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DeviceDto {
    private String deviceName;
    private String deviceType;
    private String serialNumber;
    private LocalDateTime acquisitionDateTime;
}
