package com.alexr03.dev.banking.windows;

import com.alexr03.dev.banking.Utilities;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public abstract class WindowBase extends BasicWindow {
    public WindowBase() {
    }

    public WindowBase(String title) {
        super(title);
    }

    @Autowired
    protected Utilities utilities;

    @Autowired
    protected Logger esLogger;

    @Autowired
    protected MultiWindowTextGUI multiWindowTextGUI;

    @Autowired
    protected ApplicationContext applicationContext;

    public abstract void start();

    public WindowBase init() {
        this.start();
        return this;
    }
}
