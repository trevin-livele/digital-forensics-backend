package com.trevtech.security.payload;

import lombok.Data;

@Data
public class DashboardCountsDTO {
    private Long caseCount;
    private Long dataAnalysisCount;
    private Long reportCount;
    private Long deviceInventoryCount;
}
