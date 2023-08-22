package com.trevtech.security.repository;

import com.trevtech.security.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IFIleRepository extends JpaRepository <FileEntity , Long> {

    List<FileEntity> findCaseById(long caseId);
}
