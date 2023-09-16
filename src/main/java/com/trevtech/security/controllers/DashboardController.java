package com.trevtech.security.controllers;

import com.trevtech.security.payload.DashboardCountsDTO;
import com.trevtech.security.repository.IDeviceRepository;
import com.trevtech.security.repository.IFIleRepository;
import com.trevtech.security.repository.IUserRepository;
import com.trevtech.security.repository.IcaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/dashboard")
public class DashboardController {

    @Autowired
    IcaseRepository icaseRepository;
    @Autowired
    IDeviceRepository iDeviceRepository;
    @Autowired
    IFIleRepository ifIleRepository;
    @Autowired
    IUserRepository iUserRepository;

        @GetMapping("/counts")
        public DashboardCountsDTO getDashboardCounts() {
            DashboardCountsDTO countsDTO = new DashboardCountsDTO();
            countsDTO.setCaseCount(icaseRepository.count());
            countsDTO.setDataAnalysisCount(iUserRepository.count());
            countsDTO.setReportCount(ifIleRepository.count());
            countsDTO.setDeviceInventoryCount(iDeviceRepository.count());
            return countsDTO;
        }
    }
