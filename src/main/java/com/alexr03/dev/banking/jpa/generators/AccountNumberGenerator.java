package com.alexr03.dev.banking.jpa.generators;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
public class AccountNumberGenerator implements IdentifierGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        try {
            log.debug("Creating account number");
            int i = ThreadLocalRandom.current().nextInt(1000000, 99999999 + 1);
            log.debug("Account number generated: " + i);
            return (long) i;
        } catch (Exception e){
            log.error(e.getMessage(), e);
            return -1;
        }
    }
}
