package com.alexr03.dev.banking.jpa.repositories;

import com.alexr03.dev.banking.jpa.entities.users.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<StaffEntity, Long> {
    StaffEntity findByUsername(String username);
}
