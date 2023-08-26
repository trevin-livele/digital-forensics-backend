package com.trevtech.security.repository;

import com.trevtech.security.entity.CaseEntity;
import com.trevtech.security.entity.DataEntity;
import com.trevtech.security.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IdataRepository extends JpaRepository<DataEntity, Long> {
    List<DataEntity> findCaseById(long caseId);
}
