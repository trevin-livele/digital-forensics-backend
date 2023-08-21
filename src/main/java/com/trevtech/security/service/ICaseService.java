package com.trevtech.security.service;

import com.trevtech.security.payload.CaseDto;
import com.trevtech.security.payload.CaseResponse;

public interface ICaseService {

    CaseDto createCase(CaseDto caseDto);

    CaseResponse getAllCases(int pageNo, int pageSize, String sortBy, String sortDir);

    CaseDto getCaseById(long id);

    CaseDto updateCase(CaseDto caseDto, long id);

    void deleteCaseById(long id);
}
