package com.alexr03.dev.banking.windows;

import com.alexr03.dev.banking.jpa.entities.users.PersonEntity;
import com.alexr03.dev.banking.jpa.entities.users.StaffEntity;
import com.alexr03.dev.banking.jpa.entities.users.UserEntity;
import com.alexr03.dev.banking.jpa.repositories.StaffRepository;
import com.alexr03.dev.banking.jpa.repositories.UserRepository;
import com.alexr03.dev.banking.windows.users.UserMenu;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogBuilder;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Slf4j
public class LoginWindow extends WindowBase {
    public LoginWindow() {
        super("Alex's Scam Bank");
        this.setHints(Arrays.asList(Window.Hint.CENTERED));
    }

    @Override
    public void start() {
        Panel panel = new Panel(new LinearLayout(Direction.HORIZONTAL));
        panel.setLayoutManager(new GridLayout(1));

        panel.addComponent(new Button("Login", () -> {
            panel.removeAllComponents();
            this.userLogin();
        }));
        panel.addComponent(new EmptySpace(new TerminalSize(0, 0)));
        panel.addComponent(new Button("Register", () -> {
            panel.removeAllComponents();
            this.userLogin();
        }));
        panel.addComponent(new Button("Exit", () -> {
            this.close();
            System.exit(0);
        }));

        this.setComponent(panel);
        multiWindowTextGUI.addWindowAndWait(this);
    }

    private void registerUser() {
//        UserEntity.UserEntityBuilder<?, ?> builder = UserEntity.builder();
//        builder.firstName(utilities.askUser("Please enter First Name"));
//        builder.lastName(utilities.askUser("Please enter Last Name"));
//        builder.dateOfBirth(java.sql.Date.valueOf(LocalDate.parse(utilities.askUser("Please enter DOB"))));
//        builder.addressLine1(utilities.askUser("Please enter Address Line 1"));
//        builder.addressLine2(utilities.askUser("Please enter Address Line 2 (Optional)"));
//        builder.postCode(utilities.askUser("Please enter Postcode"));
//        UserEntity build = builder.build();
//        build.setPassword(utilities.askUser("Enter a password"));
//        build.setUsername(build.getFirstName() + "_" + new Random().nextInt(1000));
//        UserRepository bean = applicationContext.getBean(UserRepository.class);
//        bean.save(build);
//        esLogger.info("User Registered", new HashMap<>() {
//            {
//                put("user", build);
//                putAll(utilities.mapEvent(ELogEvent.USER_REGISTERED));
//            }
//        });
//        log.info("Thank you for registering!");
//
//        return applicationContext.getBean(UserMenu.class, build);
    }

    private void userLogin() {
        Panel panel = new Panel(new GridLayout(2));
//        panel.setLayoutManager(new GridLayout(2));

        panel.addComponent(new Label("Username:"));
        TextBox usernameTextBox = new TextBox();
        panel.addComponent(usernameTextBox);

        panel.addComponent(new Label("Password:"));
        TextBox passwordTextBox = new TextBox().setMask('*');
        panel.addComponent(passwordTextBox);

        panel.addComponent(new Label("User Type:"));
        RadioBoxList<String> userType = new RadioBoxList<>(new TerminalSize(14, 2));
        userType.addItem("Customer");
        userType.addItem("Staff");
        userType.setCheckedItemIndex(0);
        panel.addComponent(userType);

        panel.addComponent(new EmptySpace(new TerminalSize(0, 2))); // Empty space underneath labels

        panel.addComponent(new Button("Login", () -> {
            if (userType.getCheckedItem().toLowerCase().startsWith("s")) { // Staff
                StaffRepository bean = applicationContext.getBean(StaffRepository.class);
                StaffEntity byUsername = bean.findByUsername(usernameTextBox.getText());
                if(checkCredentials(byUsername, passwordTextBox.getText())){
                    this.close();
                    this.multiWindowTextGUI.addWindowAndWait(applicationContext.getBean(UserMenu.class, byUsername).init());
                }
            } else {
                UserRepository bean = applicationContext.getBean(UserRepository.class);
                UserEntity byUsername = bean.findByUsername(usernameTextBox.getText());
                if(checkCredentials(byUsername, passwordTextBox.getText())){
                    this.close();
                    this.multiWindowTextGUI.addWindowAndWait(applicationContext.getBean(UserMenu.class, byUsername).init());
                }
            }
        }));

        panel.addComponent(new Button("Back", this::start));

        this.setComponent(panel);
    }

    private boolean checkCredentials(PersonEntity personEntity, String password) {
        if (personEntity == null || !personEntity.checkPassword(password)) {
            new MessageDialogBuilder().setTitle("Error").setText("Username/Password incorrect").addButton(MessageDialogButton.OK).build().showDialog(multiWindowTextGUI);
            return false;
        }

        return true;
    }
}
