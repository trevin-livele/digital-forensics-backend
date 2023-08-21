package com.trevtech.security.repository;

import com.trevtech.security.entity.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDeviceRepository extends JpaRepository <DeviceEntity, Long> {
}
