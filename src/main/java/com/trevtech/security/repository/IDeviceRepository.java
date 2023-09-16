package com.trevtech.security.repository;

import com.trevtech.security.entity.DataEntity;
import com.trevtech.security.entity.DeviceEntity;
import com.trevtech.security.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDeviceRepository extends JpaRepository <DeviceEntity, Long> {

    List<DeviceEntity> findFileById(long fileId);
}
