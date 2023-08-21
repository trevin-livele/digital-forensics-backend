package com.trevtech.security.repository;

import com.trevtech.security.entity.CaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IcaseRepository extends JpaRepository<CaseEntity, Long> {


}
