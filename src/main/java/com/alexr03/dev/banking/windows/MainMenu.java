//package com.alexr03.dev.banking.windows;
//
//import com.alexr03.dev.banking.Utilities;
//import com.alexr03.dev.banking.jpa.entities.users.StaffEntity;
//import com.alexr03.dev.banking.jpa.entities.users.UserEntity;
//import com.alexr03.dev.banking.jpa.repositories.StaffRepository;
//import com.alexr03.dev.banking.jpa.repositories.UserRepository;
//import com.alexr03.dev.banking.windows.users.UserMenu;
//import com.alexr03.dev.banking.models.ELogEvent;
//import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
//import lombok.SneakyThrows;
//import lombok.extern.slf4j.Slf4j;
//import org.slf4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDate;
//import java.util.HashMap;
//import java.util.Random;
//
//@Component
//@Scope("prototype")
//@Slf4j
//public class MainMenu implements IWindow {
//
//    @Autowired
//    private ApplicationContext applicationContext;
//    @Autowired
//    private Utilities utilities;
//    @Autowired
//    private Logger esLogger;
//
////    @Override
////    public void start() {
////        IMenu menuToOpen;
////        log.info("Welcome!");
////        String type = utilities.askUser("Would you like to (L)ogin or (R)egister", "(?i)(l|login|r|register)");
////        if (type.toLowerCase().startsWith("l")) {
////            log.info("-Login-");
////            String userType = utilities.askUser("Are you (S)taff or (C)lient", "(?i)(c|client|s|staff)");
////            if (userType.toLowerCase().startsWith("s")) {
////                menuToOpen = staffLogin();
////            } else {
////                menuToOpen = userLogin();
////            }
////        } else if (type.toLowerCase().startsWith("r")) {
////            log.info("-Register-");
////            menuToOpen = registerUser();
////        } else {
////            log.info("No idea how you got here - start again!");
////            start();
////            return;
////        }
////
////        if (menuToOpen != null) {
////            menuToOpen.start();
////        } else {
////            log.error("Couldn't open menu.");
////            start();
////            return;
////        }
////    }
//
//    private IWindow registerUser() {
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
//    }
//
//    private IWindow staffLogin() {
//        StaffRepository bean = applicationContext.getBean(StaffRepository.class);
//        String username = utilities.askUser("Username");
//        StaffEntity byUsername = bean.findByUsername(username);
//        if (byUsername != null && byUsername.checkPassword(utilities.askUser("Password"))) {
//            esLogger.info("Login Success - Staff", new HashMap<>() {
//                {
//                    put("user", byUsername);
//                    putAll(utilities.mapEvent(ELogEvent.STAFF_LOGIN_SUCCESS));
//                }
//            });
//            log.info("Login success!");
//            return applicationContext.getBean(StaffMenu.class, byUsername);
//        } else {
//            esLogger.info("Login Failure - Credentials invalid or could not find user.", new HashMap<>() {
//                {
//                    put("user", username);
//                    putAll(utilities.mapEvent(ELogEvent.STAFF_LOGIN_FAILED));
//                }
//            });
//            log.info("Credentials invalid or could not find user.");
//        }
//
//        return null;
//    }
//
//    private IWindow userLogin() {
//        UserRepository bean = applicationContext.getBean(UserRepository.class);
//        String username = utilities.askUser("Username");
//        UserEntity byUsername = bean.findByUsername(username);
//        if (byUsername != null && byUsername.checkPassword(utilities.askUser("Password"))) {
//            esLogger.info("Login Success - User", new HashMap<>() {
//                {
//                    put("user", byUsername);
//                    putAll(utilities.mapEvent(ELogEvent.USER_LOGIN_SUCCESS));
//                }
//            });
//            log.info("Login success!");
//            return applicationContext.getBean(UserMenu.class, byUsername);
//        } else {
//            esLogger.info("Login Failure - Credentials invalid or could not find user.", new HashMap<>() {
//                {
//                    put("username", username);
//                    putAll(utilities.mapEvent(ELogEvent.USER_LOGIN_FAILED));
//                }
//            });
//            log.info("Credentials invalid or could not find user.");
//        }
//
//        return null;
//    }
//
//    @SneakyThrows
//    @Override
//    public void start() {
//        LoginWindow loginMenu = new LoginWindow();
//        MultiWindowTextGUI gui = applicationContext.getBean(MultiWindowTextGUI.class);
//        loginMenu.start();
//        gui.updateScreen();
//        gui.addWindowAndWait(loginMenu);
//    }
//}
