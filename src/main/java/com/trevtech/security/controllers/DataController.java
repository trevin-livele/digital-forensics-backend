package com.trevtech.security.controllers;

import com.trevtech.security.payload.DataDto;
import com.trevtech.security.payload.FileDto;
import com.trevtech.security.service.FilesService;
import com.trevtech.security.service.IDataService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/data")
public class DataController {
    IDataService dataService;

    public DataController(IDataService dataService){
        this.dataService = dataService;
    }

    @PostMapping("/{caseId}/data")
    public ResponseEntity<DataDto> createData(@PathVariable(value = "caseId") long caseid,
                                              @Valid @RequestBody DataDto dataDto){
        return new ResponseEntity<>(dataService.createData(caseid, dataDto), HttpStatus.CREATED);
    }

    @GetMapping("/cases/{caseId}/data")
    public List<DataDto> getDataByCaseId(@PathVariable(value = "caseId") Long caseId){
        return dataService.getDataByCaseId(caseId);
    }

    @GetMapping("/cases/{caseId}/data/{id}")
    public ResponseEntity<DataDto> getDataById(@PathVariable(value = "caseId") Long caseId,
                                               @PathVariable(value = "id") Long dataId){
        DataDto dataDto = dataService.getDataById(caseId, dataId);
        return new ResponseEntity<>(dataDto, HttpStatus.OK);
    }


    @PutMapping("/cases/{casesId}/data/{id}")
    public ResponseEntity<DataDto> updateData(@PathVariable(value = "caseId") Long caseId,
                                              @PathVariable(value = "id") Long dataId,
                                              @Valid @RequestBody DataDto dataDto){
        DataDto updatedData = dataService.updateData(caseId, dataId, dataDto);
        return new ResponseEntity<>(updatedData, HttpStatus.OK);
    }

    @DeleteMapping("/cases/{caseId}/data/{id}")
    public ResponseEntity<String> deleteData(@PathVariable(value = "caseId") Long caseId,
                                             @PathVariable(value = "id") Long dataId){
        dataService.deleteDataById(caseId, dataId);
        return new ResponseEntity<>("Data deleted successfully", HttpStatus.OK);
    }



}
