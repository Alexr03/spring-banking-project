package com.alexr03.dev.banking.windows.users;

import com.alexr03.dev.banking.jpa.entities.users.UserEntity;
import com.alexr03.dev.banking.windows.WindowBase;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@Scope("prototype")
@Slf4j
public class UserMenu extends WindowBase {

    private Panel mainContent;

    private final UserEntity userEntity;

    public UserMenu(UserEntity userEntity) {
        super("Customer Dashboard");
        this.setHints(Collections.singletonList(Hint.FULL_SCREEN));
        this.userEntity = userEntity;
    }

    @Override
    public void start() {
        Panel panel = new Panel(new GridLayout(3));

        Panel userDetailsPanel = buildPersonalDetailsPanel();
        panel.addComponent(userDetailsPanel.withBorder(Borders.singleLine("Personal Details")).setPreferredSize(new TerminalSize(35, 40)));

        mainContent = new Panel(new GridLayout(1));
        mainContent.setLayoutData(GridLayout.createLayoutData(
                GridLayout.Alignment.FILL,
                GridLayout.Alignment.FILL,
                true,
                true));
        panel.addComponent(mainContent.withBorder(Borders.singleLine("Main Content")));

        Panel functionsPanel = buildFunctionsPanel();
        panel.addComponent(functionsPanel.withBorder(Borders.singleLine("Functions")).setSize(new TerminalSize(6, 12)));
        this.setComponent(panel);

        //Remove
        multiWindowTextGUI.addWindowAndWait(this);
    }

    private Panel buildFunctionsPanel() {
        Panel functionsPanel = new Panel();
        functionsPanel.setLayoutData(GridLayout.createLayoutData(
                GridLayout.Alignment.FILL,
                GridLayout.Alignment.FILL,
                false,
                false));
        ActionListBox actionListBox = new ActionListBox();
        actionListBox.addItem("Balance Transfer", () -> {
            mainContent.removeAllComponents();
            WindowBase bean = applicationContext.getBean(BalanceTransferMenu.class, userEntity).init();
            bean.getComponent().addTo(mainContent);
            log.info("oof");
        });
        actionListBox.addItem("Balance Transfer2", () -> {
            log.info("oof2");
        });
        actionListBox.addItem("Reset", () -> {
            mainContent.removeAllComponents();
        });
        functionsPanel.addComponent(actionListBox);
        return functionsPanel;
    }

    private Panel buildPersonalDetailsPanel() {
        Panel userDetailsPanel = new Panel();
        userDetailsPanel.setLayoutData(GridLayout.createLayoutData(
                GridLayout.Alignment.BEGINNING,
                GridLayout.Alignment.FILL,
                false,
                false, 1, 1));

        userDetailsPanel.addComponent(new Label("Name: " + this.userEntity.getFullName()));
        userDetailsPanel.addComponent(new Label("DOB: " + this.userEntity.getDateOfBirth().toString()));
        userDetailsPanel.addComponent(new Label("Address Line 1: " + this.userEntity.getAddressLine1()));
        userDetailsPanel.addComponent(new Label("Postcode: " + this.userEntity.getPostCode()));

        return userDetailsPanel;
    }

//    private LinkedHashMap<String, IWindow> getMenuOptions() {
//        LinkedHashMap<String, IWindow> options = new LinkedHashMap<>();
//        options.put("Balance Transfer", applicationContext.getBean(BalanceTransferMenu.class, userEntity));
//        options.put("Balance Check", applicationContext.getBean(BalanceCheck.class, userEntity));
//        return options;
//    }
}
