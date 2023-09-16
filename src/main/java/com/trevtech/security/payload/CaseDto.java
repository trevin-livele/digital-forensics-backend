package com.trevtech.security.payload;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class CaseDto {
    private Long id;
    private String caseName;
    private String description;
    private LocalDateTime creationDateTime;
    private Set<FileDto>files;
    private Set<DeviceDto>devices;
}
