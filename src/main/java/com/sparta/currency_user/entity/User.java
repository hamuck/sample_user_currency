package com.sparta.currency_user.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    @OneToMany(mappedBy = "user")
    private List<UserCurrency> userCurrencyList = new ArrayList<>();

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public User() {}
}