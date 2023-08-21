package com.trevtech.security.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


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
    @ManyToOne
    @JoinColumn(name = "file_id")
    private FileEntity fileEntity;

}
