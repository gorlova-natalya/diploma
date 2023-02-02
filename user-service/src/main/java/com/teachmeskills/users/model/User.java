package com.teachmeskills.users.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.NonFinal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@NonFinal
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "login")
    String login;

    @Column(name = "password")
    String password;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    Role role;

    public User(String login, String password, Role role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }
}
