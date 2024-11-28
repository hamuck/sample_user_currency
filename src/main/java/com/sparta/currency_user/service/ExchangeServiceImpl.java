package com.sparta.currency_user.service;

import com.sparta.currency_user.dto.ExchangeRequestDto;
import com.sparta.currency_user.dto.ExchangeResponseDto;
import com.sparta.currency_user.entity.Currency;
import com.sparta.currency_user.entity.User;
import com.sparta.currency_user.entity.UserCurrency;
import com.sparta.currency_user.repository.CurrencyRepository;
import com.sparta.currency_user.repository.ExchangeRepository;
import com.sparta.currency_user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExchangeServiceImpl implements ExchangeService {
    private final ExchangeRepository exchangeRepository;
    private final CurrencyRepository currencyRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public ExchangeResponseDto save(ExchangeRequestDto dto) {
        User user = userRepository.findById(dto.getUserId()).orElseThrow(
                ()-> new RuntimeException("User not found")
        );
        Currency currency = currencyRepository.findById(dto.getToCurrencyId()).orElseThrow(
                ()-> new RuntimeException("Currency not found")
        );

        BigDecimal afterExchange = amountAfterExchange(dto.getAmountInKrw(), currency.getId());

        UserCurrency userCurrency = new UserCurrency(user,currency,dto.getAmountInKrw(),afterExchange,"normal");

        userCurrency = exchangeRepository.save(userCurrency);

        return new ExchangeResponseDto(
                userCurrency.getId(),userCurrency.getUser().getId(),
                userCurrency.getCurrency().getCurrencyName(),userCurrency.getAmountInKrw(),
                userCurrency.getAmountAfterExchange(),userCurrency.getStatus()
                );
    }

    @Override
    public List<ExchangeResponseDto> findByUserId(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("User not found")
        );
        List<ExchangeResponseDto> exchangeResponseDtoList =
                exchangeRepository.findAllByUser(user);

        return exchangeResponseDtoList;
    }

    @Override
    @Transactional
    public ExchangeResponseDto updateStatus(Long id) {
        UserCurrency userCurrency = exchangeRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Currency Exchange Request not found")
        );

        userCurrency.setStatus();
        exchangeRepository.save(userCurrency);

        return new ExchangeResponseDto(
                userCurrency.getId(),userCurrency.getUser().getId(),
                userCurrency.getCurrency().getCurrencyName(),userCurrency.getAmountInKrw(),
                userCurrency.getAmountAfterExchange(),userCurrency.getStatus()
        );
    }

    @Override
    public void delete(Long id) {

    }

    private BigDecimal amountAfterExchange(Long krw, Long currencyId) {
        Currency currency = currencyRepository.findById(currencyId).orElseThrow(
                ()-> new RuntimeException("Currency not found")
        );
        BigDecimal amountInKrw = BigDecimal.valueOf(krw);
        BigDecimal exchangeRate = currency.getExchangeRate();

        // 환전 금액 계산
        return amountInKrw.divide(exchangeRate, 2, RoundingMode.HALF_UP);
    }
}
