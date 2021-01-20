package com.alexr03.dev.banking.windows;

import com.alexr03.dev.banking.jpa.entities.users.StaffEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Component
@Scope("prototype")
@Slf4j
public class StaffMenu extends WindowBase {

    private final StaffEntity staffEntity;

    public StaffMenu(StaffEntity staffEntity) {
        this.staffEntity = staffEntity;
    }

    @Override
    public void start() {
        log.info("Welcome " + staffEntity.getFullName() + "!");
    }
}
