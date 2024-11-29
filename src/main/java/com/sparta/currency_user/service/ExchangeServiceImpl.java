package com.sparta.currency_user.service;

import com.sparta.currency_user.dto.ExchangeRequestDto;
import com.sparta.currency_user.dto.ExchangeResponseDto;
import com.sparta.currency_user.dto.UserExchangeGroupResponseDto;
import com.sparta.currency_user.entity.Currency;
import com.sparta.currency_user.entity.User;
import com.sparta.currency_user.entity.UserCurrency;
import com.sparta.currency_user.exception.CurrencyNotFoundException;
import com.sparta.currency_user.exception.ExchangeRequestEmptyException;
import com.sparta.currency_user.exception.UserNotFountException;
import com.sparta.currency_user.repository.CurrencyRepository;
import com.sparta.currency_user.repository.UserCurrencyRepository;
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
    private final UserCurrencyRepository userCurrencyRepository;
    private final CurrencyRepository currencyRepository;
    private final UserRepository userRepository;

    //환전 요청을 저장
    @Override
    @Transactional
    public ExchangeResponseDto save(ExchangeRequestDto dto) {
        //유저와 통화 정보를 확인
        User user = userRepository.findById(dto.getUserId()).orElseThrow(
                ()-> new UserNotFountException("유저 정보가 존재하지 않습니다. 유저 id: " + dto.getUserId())
        );
        Currency currency = currencyRepository.findById(dto.getToCurrencyId()).orElseThrow(
                ()-> new CurrencyNotFoundException("통화 정보가 존재하지 않습니다. 통화 id: " + dto.getToCurrencyId())
        );

        // amountAfterExchange 메서드를 사용해 환전 후 값 구하기
        BigDecimal afterExchange = amountAfterExchange(dto.getAmountInKrw(), currency.getId());

        //입력받은 정보로 UserCurrency 객체 생성, repository에 저장
        UserCurrency userCurrency = new UserCurrency(user,currency,dto.getAmountInKrw(),afterExchange,"normal");
        userCurrency = userCurrencyRepository.save(userCurrency);

        return new ExchangeResponseDto(
                userCurrency.getId(),userCurrency.getUser().getId(),
                userCurrency.getCurrency().getCurrencyName(),userCurrency.getAmountInKrw(),
                userCurrency.getAmountAfterExchange(),userCurrency.getStatus()
                );
    }

    //유저 고유 번호로 환전 요청 정보 조회
    @Override
    public List<ExchangeResponseDto> findByUserId(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                ()-> new UserNotFountException("유저 정보가 존재하지 않습니다. 유저 id: " + id)
        );
        List<ExchangeResponseDto> exchangeResponseDtoList =
                userCurrencyRepository.findAllByUser(user);

        if (exchangeResponseDtoList.isEmpty()) {
            throw new ExchangeRequestEmptyException("환전 요청이 존재하지 않습니다. 유저 id: "+id);
        }
        return exchangeResponseDtoList;
    }

    //상태를 normal > cancelled 로 변경
    @Override
    @Transactional
    public ExchangeResponseDto updateStatus(Long id) {
        UserCurrency userCurrency = userCurrencyRepository.findById(id).orElseThrow(
                ()-> new ExchangeRequestEmptyException("환전 요청이 존재하지 않습니다. 유저 id: "+id)
        );

        userCurrency.setStatusCancelled();
        userCurrencyRepository.save(userCurrency);

        return new ExchangeResponseDto(
                userCurrency.getId(),userCurrency.getUser().getId(),
                userCurrency.getCurrency().getCurrencyName(),userCurrency.getAmountInKrw(),
                userCurrency.getAmountAfterExchange(),userCurrency.getStatus()
        );
    }

    //유저 정보로 조회한 환전 요청을 요청 수 , 환전 요청 krw 총 합 형식으로 반환
    @Override
    public UserExchangeGroupResponseDto findExchangeGroup(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                ()-> new UserNotFountException("유저 정보가 존재하지 않습니다. 유저 id: " + userId)
        );
        return new UserExchangeGroupResponseDto(userCurrencyRepository.countExchangeRequestByUser(user), userCurrencyRepository.totalAmountKrwByUser(user));
    }

    //환전 계산 메서드
    private BigDecimal amountAfterExchange(Long krw, Long currencyId) {
        Currency currency = currencyRepository.findById(currencyId).orElseThrow(
                ()-> new CurrencyNotFoundException("통화 정보가 존재하지 않습니다. 통화 id: " + currencyId)
        );
        BigDecimal amountInKrw = BigDecimal.valueOf(krw);
        BigDecimal exchangeRate = currency.getExchangeRate();

        // 환전 금액 계산
        return amountInKrw.divide(exchangeRate, 2, RoundingMode.HALF_UP);
    }
}
