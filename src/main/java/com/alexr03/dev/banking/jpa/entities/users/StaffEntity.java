package com.alexr03.dev.banking.jpa.entities.users;

import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@SuperBuilder
@NoArgsConstructor
@Table(name = "tbl_staff")
public class StaffEntity extends PersonEntity {

}
