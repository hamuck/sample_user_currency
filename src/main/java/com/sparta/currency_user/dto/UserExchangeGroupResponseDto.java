package com.sparta.currency_user.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class UserExchangeGroupResponseDto {
    private Long count;
    private BigDecimal total;

    public UserExchangeGroupResponseDto() {}

    public UserExchangeGroupResponseDto(Long count, BigDecimal total) {
        this.count = count;
        this.total = total;
    }
}
