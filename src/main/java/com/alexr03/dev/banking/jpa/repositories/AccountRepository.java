package com.alexr03.dev.banking.jpa.repositories;

import com.alexr03.dev.banking.jpa.entities.accounts.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
}
