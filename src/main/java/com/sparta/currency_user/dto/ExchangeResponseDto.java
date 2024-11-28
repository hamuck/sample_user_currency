package com.sparta.currency_user.dto;

import com.sparta.currency_user.entity.Currency;
import com.sparta.currency_user.entity.User;
import com.sparta.currency_user.entity.UserCurrency;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ExchangeResponseDto {
    private Long id;
    private Long userId;
    private String toCurrencyName;
    private Long amountInKrw;
    private BigDecimal amountAfterExchange;
    private String status = "nomal";

    public ExchangeResponseDto(Long id, Long userId, String toCurrencyName, Long amountInKrw, BigDecimal amountAfterExchange, String status) {
        this.id = id;
        this.userId = userId;
        this.toCurrencyName = toCurrencyName;
        this.amountInKrw = amountInKrw;
        this.amountAfterExchange = amountAfterExchange;
        this.status = status;
    }

    public ExchangeResponseDto(UserCurrency userCurrency) {
        this.id = userCurrency.getId();
        this.userId = userCurrency.getUser().getId();
        this.toCurrencyName = userCurrency.getCurrency().getCurrencyName();
        this.amountInKrw = userCurrency.getAmountInKrw();
        this.amountAfterExchange = userCurrency.getAmountAfterExchange();
        this.status = userCurrency.getStatus();
    }

    public UserCurrency toUserCurrency(User user, Currency currency) {
        return new UserCurrency(
                user, currency, this.amountInKrw, this.amountAfterExchange, this.status
        );
    }

}
