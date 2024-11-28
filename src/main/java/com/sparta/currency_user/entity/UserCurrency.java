package com.sparta.currency_user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class UserCurrency extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "currency_id", nullable = false)
    private Currency currency;

    @Column(nullable = false)
    private Long amountInKrw;

    private BigDecimal amountAfterExchange;

    private String status = "normal";

    @Version
    private Long version = 0L;

    public UserCurrency() {}

    public UserCurrency(User user, Currency currency, Long amountInKrw, BigDecimal amountAfterExchange, String status) {
        this.user = user;
        this.currency = currency;
        this.amountInKrw = amountInKrw;
        this.amountAfterExchange = amountAfterExchange;
        this.status = status;
    }

    public void setStatus(){
        this.status = "cancelled";
    }

    public void setUser(User user){
        this.user = user;
    }

    public void setCurrency(Currency currency){
        this.currency = currency;
    }

}
