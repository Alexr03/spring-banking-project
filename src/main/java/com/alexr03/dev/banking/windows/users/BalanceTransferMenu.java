package com.alexr03.dev.banking.windows.users;

import com.alexr03.dev.banking.jpa.entities.users.UserEntity;
import com.alexr03.dev.banking.windows.WindowBase;
import com.googlecode.lanterna.gui2.Borders;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Panel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Scope("prototype")
@Slf4j
public class BalanceTransferMenu extends WindowBase {
    private final UserEntity userEntity;

    public BalanceTransferMenu(UserEntity userEntity) {
        super("Balance Transfer");
        this.userEntity = userEntity;
        this.setHints(Arrays.asList(Hint.FULL_SCREEN));
    }

    @Override
    public void start() {
        Panel panel = new Panel(new GridLayout(1));
        panel.setLayoutData(GridLayout.createLayoutData(
                GridLayout.Alignment.FILL,
                GridLayout.Alignment.FILL,
                true,
                true));

        this.setComponent(panel.withBorder(Borders.singleLine("Balance Transfer")));
    }

//    @Override
//    public void start() {
//        AccountRepository accountRepository = SpringConfiguration.contextProvider().getApplicationContext().getBean(AccountRepository.class);
//        log.info("-Balance Transfer for user: " + userEntity.getFullName() + " -");
//        List<AccountEntity> accountEntities = userEntity.getAccountEntities();
//        if(accountEntities.isEmpty()){
//            log.error("You have no accounts to balance transfer to/from");
//            return;
//        }
//        log.info("Accounts: ");
//        for (AccountEntity accountEntity : accountEntities) {
//            log.info("Account Number: " + accountEntity.getAccountNumber() + " | Sort code: " + accountEntity.getSortCode());
//        }
//        String regex = accountEntities.stream().map(x -> x.getAccountNumber().toString()).collect(Collectors.joining("|"));
//        log.debug(regex);
//        AccountEntity fromAccount = accountRepository.getOne(Long.parseLong(utilities.askUser("Enter account number to transfer from", regex)));
//        AccountEntity toAccount = accountRepository.getOne(Long.parseLong(utilities.askUser("Enter account number to transfer to", regex)));
//        BigDecimal amountToTransfer = BigDecimal.valueOf(Float.parseFloat(utilities.askUser("Amount to transfer")));
//        boolean transferSuccess = fromAccount.transferBalance(toAccount, amountToTransfer);
//        log.debug("Transfer success: " + transferSuccess);
//        esLogger.info("Balance Transfer", new HashMap<>() {
//            {
//                put("user", userEntity);
//                put("amount", amountToTransfer);
//                put("fromAccount", fromAccount);
//                put("toAccount", toAccount);
//                putAll(utilities.mapEvent(ELogEvent.USER_BALANCE_TRANSFER));
//            }
//        });
//    }
}
