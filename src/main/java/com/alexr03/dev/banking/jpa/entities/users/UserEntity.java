package com.alexr03.dev.banking.jpa.entities.users;

import com.alexr03.dev.banking.jpa.entities.accounts.AccountEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Entity
@SuperBuilder
@NoArgsConstructor
@Table(name = "tbl_users")
@Getter
public class UserEntity extends PersonEntity {
    @ManyToMany(mappedBy = "users")
    List<AccountEntity> accountEntities;
}
