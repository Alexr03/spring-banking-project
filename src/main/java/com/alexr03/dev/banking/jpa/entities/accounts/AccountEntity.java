package com.alexr03.dev.banking.jpa.entities.accounts;

import com.alexr03.dev.banking.configuration.SpringConfiguration;
import com.alexr03.dev.banking.jpa.entities.users.UserEntity;
import com.alexr03.dev.banking.jpa.repositories.AccountRepository;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "tbl_accounts")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AccountEntity {
    @Id
    @GeneratedValue(generator = "AccountNumberGenerator", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "AccountNumberGenerator", strategy = "com.alexr03.dev.banking.jpa.generators.AccountNumberGenerator")
    @Setter(value = AccessLevel.NONE)
    private Long accountNumber;

    @ToString.Exclude
    @ManyToMany
    @JoinTable(
            name = "tbl_account_users",
            joinColumns = {@JoinColumn(name = "account_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private List<UserEntity> users;

    private BigDecimal balance;

    @Transient
    private String sortCode = "04-00-04";

    public boolean transferBalance(AccountEntity toAccount, BigDecimal amount){
        AccountRepository accountRepository = SpringConfiguration.contextProvider().getApplicationContext().getBean(AccountRepository.class);
        if(toAccount.getBalance().compareTo(amount) >= 0){
            toAccount.setBalance(toAccount.getBalance().subtract(amount));
            this.setBalance(this.getBalance().add(amount));
            accountRepository.save(toAccount);
            accountRepository.save(this);
            return true;
        }
        return false;
    }
}
