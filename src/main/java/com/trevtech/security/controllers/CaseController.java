package com.trevtech.security.controllers;


import com.trevtech.security.payload.CaseDto;
import com.trevtech.security.payload.CaseResponse;
import com.trevtech.security.service.ICaseService;
import com.trevtech.security.service.impl.CaseServiceImpl;
import com.trevtech.security.utils.AppConstants;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cases")
public class CaseController {
    private ICaseService caseService;


    public CaseController(ICaseService caseService) {
        this.caseService = caseService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    // create case rest api
    @PostMapping
    public ResponseEntity<CaseDto> createCase(@Valid @RequestBody CaseDto caseDto){
        return new ResponseEntity<>(caseService.createCase(caseDto), HttpStatus.CREATED);
    }

    // get all cases rest api
    @GetMapping
    public CaseResponse getAllCases(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {

    return caseService.getAllCases(pageNo, pageSize, sortBy, sortDir);
    }

    // get case by id
    @GetMapping("/{id}")
    public ResponseEntity<CaseDto> getCaseById(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(caseService.getCaseById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    // update case by id rest api
    @PutMapping("/{id}")
    public ResponseEntity<CaseDto> updateCase(@Valid @RequestBody CaseDto caseDto, @PathVariable(name = "id") long id){
        CaseDto caseResponse = caseService.updateCase(caseDto, id);
        return new ResponseEntity<>(caseResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    // delete case rest api
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCase(@PathVariable(name = "id") long id){
        caseService.deleteCaseById(id);
        return new ResponseEntity<>("Case entity deleted successfully.", HttpStatus.OK);
    }

}
