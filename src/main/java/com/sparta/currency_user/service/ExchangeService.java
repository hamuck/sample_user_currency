package com.sparta.currency_user.service;

import com.sparta.currency_user.dto.ExchangeRequestDto;
import com.sparta.currency_user.dto.ExchangeResponseDto;
import com.sparta.currency_user.dto.UserExchangeGroupResponseDto;

import java.util.List;

public interface ExchangeService {
    ExchangeResponseDto save(ExchangeRequestDto exchangeRequestDto);
    List<ExchangeResponseDto> findByUserId(Long id);
    ExchangeResponseDto updateStatus(Long id);
    UserExchangeGroupResponseDto findExchangeGroup(Long userId);
}
