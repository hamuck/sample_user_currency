package com.sparta.currency_user.validator;

import com.sparta.currency_user.entity.Currency;
import com.sparta.currency_user.repository.CurrencyRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Slf4j
@RequiredArgsConstructor
public class CurrencyValidator {
    private final CurrencyRepository currencyRepository;

    @PostConstruct
    public void validateCurrency() {
        currencyRepository.findAll().forEach(currency -> {
            BigDecimal exchangeRate = currency.getExchangeRate();
            BigDecimal maxExchangeRate = new BigDecimal(1000000);
            if (exchangeRate.compareTo(BigDecimal.ZERO) <= 0 || exchangeRate.compareTo(maxExchangeRate) > 0) {
                log.warn("환율의 범위 입력이 잘못되었습니다.");
            }
        });
    }
}
