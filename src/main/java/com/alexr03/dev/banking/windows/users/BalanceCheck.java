package com.alexr03.dev.banking.windows.users;

import com.alexr03.dev.banking.Utilities;
import com.alexr03.dev.banking.jpa.entities.accounts.AccountEntity;
import com.alexr03.dev.banking.jpa.entities.users.UserEntity;
import com.alexr03.dev.banking.windows.WindowBase;
import com.alexr03.dev.banking.models.ELogEvent;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.stream.Collectors;

@Component
@Scope("prototype")
@Slf4j
public class BalanceCheck extends WindowBase {
    private final UserEntity userEntity;

    @Autowired
    private Utilities utilities;

    @Autowired
    private Logger esLogger;

    public BalanceCheck(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public void start() {
        log.info("Total balance: £" + userEntity.getAccountEntities().stream().map(AccountEntity::getBalance).collect(Collectors.toList()).stream().reduce(BigDecimal.ZERO, BigDecimal::add));
        for (AccountEntity accountEntity : userEntity.getAccountEntities()) {
            log.info("------------------------------------------------");
            log.info("Account Number: " + accountEntity.getAccountNumber() + " | Sort code: " + accountEntity.getSortCode());
            log.info("Balance: £" + accountEntity.getBalance());
        }
        esLogger.info("Completed balance check", new HashMap<>() {
            {
                put("user", userEntity);
                putAll(utilities.mapEvent(ELogEvent.USER_BALANCE_CHECK));
            }
        });
    }
}
