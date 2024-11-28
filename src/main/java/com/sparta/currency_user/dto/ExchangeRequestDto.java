package com.sparta.currency_user.dto;

import lombok.Getter;

@Getter
public class ExchangeRequestDto {
    private Long userId;
    private Long toCurrencyId;
    private Long amountInKrw;

    public ExchangeRequestDto(Long userId, Long toCurrencyId, Long amountInKrw) {
        this.userId = userId;
        this.toCurrencyId = toCurrencyId;
        this.amountInKrw = amountInKrw;
    }

}
