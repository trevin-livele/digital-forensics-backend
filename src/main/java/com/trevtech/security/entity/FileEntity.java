package com.trevtech.security.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_files")
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    private String filePath;
    private String fileType;
    private Long fileSize;
    private LocalDateTime creationDateTime;
    @ManyToOne
    @JoinColumn(name = "case_id")
    private CaseEntity caseEntity;

    @OneToMany(mappedBy = "fileEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DeviceEntity> deviceEntities = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "file_data",
            joinColumns = @JoinColumn(name = "file_id"),
            inverseJoinColumns = @JoinColumn(name = "data_id")
    )
    private List<DataEntity> data = new ArrayList<>();

}
