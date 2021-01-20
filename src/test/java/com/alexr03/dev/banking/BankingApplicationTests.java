package com.alexr03.dev.banking;

import com.alexr03.dev.banking.jpa.entities.users.UserEntity;
import com.alexr03.dev.banking.jpa.repositories.UserRepository;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.swing.TerminalEmulatorAutoCloseTrigger;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Slf4j
class BankingApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ApplicationContext applicationContext;

	@SneakyThrows
	public BankingApplicationTests() {
		BankingApplication.terminal = new DefaultTerminalFactory()
				.setInitialTerminalSize(new TerminalSize(160, 40))
				.setTerminalEmulatorFrameAutoCloseTrigger(TerminalEmulatorAutoCloseTrigger.CloseOnExitPrivateMode)
				.createTerminal();
		BankingApplication.terminal.exitPrivateMode();
	}

	@Test
	void passwordWasEncrypted() {
		UserEntity build = UserEntity.builder().username("Jimboi").firstName("Jimmy").lastName("oof").addressLine1("ayy").build();
		build.setPassword("123");
		assertThat(build.getPassword()).isEqualTo(" ,�b�Y\u0007[�K\u0007\u0015-#Kp");
	}
}
