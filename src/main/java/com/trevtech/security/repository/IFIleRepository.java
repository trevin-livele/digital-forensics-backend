package com.trevtech.security.repository;

import com.trevtech.security.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFIleRepository extends JpaRepository <FileEntity , Long> {
}
