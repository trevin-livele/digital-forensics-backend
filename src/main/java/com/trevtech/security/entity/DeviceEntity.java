package com.trevtech.security.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_device")public class DeviceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String deviceName;
    private String deviceType;
    private String serialNumber;
    private LocalDateTime acquisitionDateTime;


    @ManyToMany
    @JoinTable(name = "device_file",
            joinColumns = @JoinColumn(name = "device_id"),
            inverseJoinColumns = @JoinColumn(name = "file_id"))
    private Set<FileEntity> files = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "case_id")
    private CaseEntity caseEntity;
}
