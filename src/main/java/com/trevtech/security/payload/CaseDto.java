package com.trevtech.security.payload;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CaseDto {
    private Long id;
    private String caseName;
    private String description;
    private LocalDateTime creationDateTime;
}
