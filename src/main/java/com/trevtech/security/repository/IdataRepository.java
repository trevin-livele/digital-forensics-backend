package com.trevtech.security.repository;

import com.trevtech.security.entity.CaseEntity;
import com.trevtech.security.entity.DataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IdataRepository extends JpaRepository<DataEntity, Long> {
}
