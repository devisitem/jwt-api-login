package me.kimchidev.jwtapilogin;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
public class Account {
    @Id @GeneratedValue
    private Long id;

    @Setter
    @Column(unique = true)
    private String userId;

    private String password;
}
