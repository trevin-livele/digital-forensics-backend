package com.trevtech.security.payload;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class DataDto {
    private String dataName;
    private String dataType;
    private Long dataSize;
    private LocalDateTime acquisitionDateTime;
}
