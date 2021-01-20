package com.alexr03.dev.banking;

import com.alexr03.dev.banking.jpa.entities.accounts.AccountEntity;
import com.alexr03.dev.banking.jpa.entities.users.UserEntity;
import com.alexr03.dev.banking.jpa.repositories.AccountRepository;
import com.alexr03.dev.banking.jpa.repositories.UserRepository;
import com.alexr03.dev.banking.windows.LoginWindow;
import com.alexr03.dev.banking.models.ELogEvent;
import com.alexr03.dev.banking.windows.users.UserMenu;
import com.github.lalyos.jfiglet.FigletFont;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.SimpleTerminalResizeListener;
import com.googlecode.lanterna.terminal.Terminal;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.math.BigDecimal;
import java.util.ArrayList;

@SpringBootApplication
@EnableJpaRepositories
@Slf4j
public class BankingApplication implements CommandLineRunner {

    public static Terminal terminal;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private Utilities utilities;

    @SneakyThrows
    public static void main(String[] args) {
        terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(160, 40)).createTerminal();
        SpringApplication.run(BankingApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            Logger esLogger = (Logger) applicationContext.getBean("es-logger");
            esLogger.info("Bank Application has started.", utilities.mapEvent(ELogEvent.APPLICATION_STARTED));
            System.out.println(FigletFont.convertOneLine("Alex's scam bank"));
            applicationContext.getBean(LoginWindow.class).start();
//            applicationContext.getBean(UserMenu.class, applicationContext.getBean(UserRepository.class).findByUsername("Alex")).start();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    private void createUser() {
        UserRepository bean = applicationContext.getBean(UserRepository.class);
        UserEntity alex = UserEntity.builder().username("Alex").addressLine1("Shitsea").build();
        alex.setPassword("123");
        bean.save(alex);
    }

    private void createAccount() {
        AccountRepository bean = applicationContext.getBean(AccountRepository.class);
        UserRepository bean2 = applicationContext.getBean(UserRepository.class);
        AccountEntity one = new AccountEntity();
        one.setBalance(new BigDecimal("0.00"));
        UserEntity alex = bean2.findByUsername("Alex");
        log.info("Alex: " + alex);
        ArrayList<UserEntity> arrayList = new ArrayList<>();
        arrayList.add(alex);
        one.setUsers(arrayList);
        bean.save(one);
    }
}
