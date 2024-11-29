package com.sparta.currency_user.repository;

import com.sparta.currency_user.dto.ExchangeResponseDto;
import com.sparta.currency_user.entity.User;
import com.sparta.currency_user.entity.UserCurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface UserCurrencyRepository extends JpaRepository<UserCurrency, Long> {
    //유저 검색 리스트로 출력
    @Query(
            "SELECT new com.sparta.currency_user.dto.ExchangeResponseDto(uc)"+
                    "FROM UserCurrency AS uc WHERE uc.user= :user"
    )
    List<ExchangeResponseDto> findAllByUser(@Param("user") User user);

    //유저 검색 후 count로 반환
    @Query("SELECT COUNT(uc) "+
            "FROM UserCurrency AS uc WHERE uc.user = :user AND uc.status = 'normal'"
    )
    Long countExchangeRequestByUser(@Param("user") User user);

    //유저 검색 후 sum으로 krw 환전 요청의 합 반환
    @Query("SELECT SUM(uc.amountInKrw) "+
            "FROM UserCurrency AS uc WHERE uc.user = :user AND uc.status = 'normal'"
    )
    BigDecimal totalAmountKrwByUser(@Param("user") User user);
}
