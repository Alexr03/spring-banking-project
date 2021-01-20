package com.alexr03.dev.banking.jpa.entities.users;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.util.DigestUtils;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@MappedSuperclass
@Data
@SuperBuilder
@NoArgsConstructor
public abstract class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;
    private String addressLine1;
    private String addressLine2;
    private String postCode;
    private String notes;
    private String username;
    @ToString.Exclude private String password;
    private Date dateOfBirth;

    public String getFullName(){
        return this.getFirstName() + " " + this.getLastName();
    }

    public void setPassword(String password) {
        byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);
        byte[] md5Hash = DigestUtils.md5Digest(passwordBytes);
        this.password = new String(md5Hash,  StandardCharsets.UTF_8);
    }

    public Boolean checkPassword(String password){
        byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);
        byte[] md5Hash = DigestUtils.md5Digest(passwordBytes);
        return this.getPassword().equals(new String(md5Hash, StandardCharsets.UTF_8));
    }
}
