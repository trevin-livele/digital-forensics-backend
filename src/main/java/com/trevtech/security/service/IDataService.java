package com.trevtech.security.service;

import com.trevtech.security.payload.CaseDto;
import com.trevtech.security.payload.CaseResponse;
import com.trevtech.security.payload.DataDto;
import com.trevtech.security.payload.FileDto;

import java.util.List;

public interface IDataService {
    DataDto  createData(long caseId, DataDto dataDto);
    List<DataDto> getDataByCaseId(long dataId);
    DataDto getDataById(Long caseId, Long dataId);

    DataDto updateData(Long caseId, long dataId, DataDto dataRequest);
    void deleteDataById(Long caseId, Long dataId);
}
