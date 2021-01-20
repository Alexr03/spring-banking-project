package com.alexr03.dev.banking.configuration;

import com.alexr03.dev.banking.BankingApplication;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.SimpleTheme;
import com.googlecode.lanterna.gui2.DefaultWindowManager;
import com.googlecode.lanterna.gui2.EmptySpace;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@Slf4j
public class TerminalContext {
    @Autowired
    private Logger esLogger;

    @SneakyThrows
    @Bean
    @Scope("singleton")
    Terminal terminal() {
        return BankingApplication.terminal;
    }

    @SneakyThrows
    @Bean
    @Scope("singleton")
    Screen screen() {
        TerminalScreen terminalScreen = new TerminalScreen(terminal());
        terminalScreen.startScreen();
        return terminalScreen;
    }

    @SneakyThrows
    @Bean
    @Scope("singleton")
    MultiWindowTextGUI multiWindowTextGUI() {
        return new MultiWindowTextGUI(screen(), new DefaultWindowManager(), new EmptySpace(TextColor.ANSI.BLUE));
    }
}
