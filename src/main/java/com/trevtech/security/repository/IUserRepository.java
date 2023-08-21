package com.trevtech.security.repository;

import com.trevtech.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository <User, Long> {
}
