package com.alexr03.dev.banking.jpa.repositories;

import com.alexr03.dev.banking.jpa.entities.users.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
}
