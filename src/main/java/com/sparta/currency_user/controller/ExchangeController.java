package com.sparta.currency_user.controller;

import com.sparta.currency_user.dto.ExchangeRequestDto;
import com.sparta.currency_user.dto.ExchangeResponseDto;
import com.sparta.currency_user.dto.UserExchangeGroupResponseDto;
import com.sparta.currency_user.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exchange")
@RequiredArgsConstructor
public class ExchangeController {
    private final ExchangeService exchangeService;

    //환전 요청 생성
    @PostMapping
    public ResponseEntity<ExchangeResponseDto> createExchange (@RequestBody ExchangeRequestDto exchangeRequestDto){
        ExchangeResponseDto exchangeResponseDto = exchangeService.save(exchangeRequestDto);
        return new ResponseEntity<>(exchangeResponseDto, HttpStatus.CREATED);
    }

    //환전 요청 조회(유저 아이디)
    @GetMapping("/{user_id}")
    public ResponseEntity<List<ExchangeResponseDto>> findExchange(@PathVariable Long user_id){
        List<ExchangeResponseDto> exchangeResponseDto = exchangeService.findByUserId(user_id);
        return new ResponseEntity<>(exchangeResponseDto, HttpStatus.OK);
    }

    //환전 요청 그룹 조회(유저 아이디)
    @GetMapping("/total/{user_id}")
    public ResponseEntity<UserExchangeGroupResponseDto> findExchangeGroup(@PathVariable Long user_id){
        UserExchangeGroupResponseDto userExchangeGroupResponseDto = exchangeService.findExchangeGroup(user_id);
        return new ResponseEntity<>(userExchangeGroupResponseDto, HttpStatus.OK);
    }

    //환전 상태 취소로 변경
    @PatchMapping("/{id}")
    public ResponseEntity<ExchangeResponseDto> updateStatus(@PathVariable Long id){
        ExchangeResponseDto exchangeResponseDto = exchangeService.updateStatus(id);
        return new ResponseEntity<>(exchangeResponseDto, HttpStatus.OK);
    }

}
