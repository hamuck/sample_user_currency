package com.sparta.currency_user.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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

    //@Email 어노테이션을 통해 형식을 검증한다. 이때 @Email은 null이 허용되므로 추가로 nullable을 사용하였다.
    @Email(message = "이메일 형식이 옳지 않습니다")
    @Column(nullable = false)
    private String email;

    @OneToMany(mappedBy = "user",cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<UserCurrency> userCurrencyList = new ArrayList<>();

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public User() {}
}