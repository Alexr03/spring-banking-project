package com.alexr03.dev.banking.windows;

import org.springframework.stereotype.Component;

@Component
public class ExitMenu extends WindowBase {
    @Override
    public void start() {
        System.exit(0);
    }
}
