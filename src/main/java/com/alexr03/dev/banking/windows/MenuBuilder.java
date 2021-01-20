//package com.alexr03.dev.banking.windows;
//
//import com.alexr03.dev.banking.Utilities;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.LinkedHashMap;
//
//@Component
//@Slf4j
//public class MenuBuilder {
//    @Autowired
//    private ApplicationContext applicationContext;
//
//    @Autowired
//    private Utilities utilities;
//
//    private HashMap<String, IWindow> menus;
//
//    public MenuBuilder() {
//    }
//
//    public void setMenus(HashMap<String, IWindow> menus) {
//        this.menus = new LinkedHashMap<>(menus);
//    }
//
//    public IWindow selectOption() {
//        StringBuilder responseRegex = new StringBuilder("[");
//        menus.put("Exit", applicationContext.getBean(ExitMenu.class));
//
//        for (int i = 0; i < menus.keySet().size(); i++) {
//            log.info(String.format("[%s] %s", i + 1, menus.keySet().toArray()[i]));
//            responseRegex.append(i + 1).append("|");
//        }
//        responseRegex.deleteCharAt(responseRegex.lastIndexOf("|"));
//        responseRegex.append("]");
//        String selectedOption = utilities.askUser("Select an option", responseRegex.toString());
//
//        return (new ArrayList<>(menus.values())).get(Integer.parseInt(selectedOption) - 1);
//    }
//}
