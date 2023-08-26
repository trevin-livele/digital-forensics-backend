package com.trevtech.security.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_data")
public class DataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dataName;
    private String dataType;
    private Long dataSize;
    private LocalDateTime acquisitionDateTime;

    @ManyToMany(mappedBy = "data")
    private List<FileEntity> fileEntities;

    @ManyToOne
    @JoinColumn(name = "case_id")
    private CaseEntity caseEntity;
}
