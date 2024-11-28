package com.sparta.currency_user.controller;

import com.sparta.currency_user.dto.ExchangeRequestDto;
import com.sparta.currency_user.dto.ExchangeResponseDto;
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

    @PostMapping
    public ResponseEntity<ExchangeResponseDto> createExchange (@RequestBody ExchangeRequestDto exchangeRequestDto){
        ExchangeResponseDto exchangeResponseDto = exchangeService.save(exchangeRequestDto);
        return new ResponseEntity<>(exchangeResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<List<ExchangeResponseDto>> findExchange(@PathVariable Long user_id){
        List<ExchangeResponseDto> exchangeResponseDto = exchangeService.findByUserId(user_id);
        return new ResponseEntity<>(exchangeResponseDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ExchangeResponseDto> updateStatus(@PathVariable Long id){
        ExchangeResponseDto exchangeResponseDto = exchangeService.updateStatus(id);
        return new ResponseEntity<>(exchangeResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExchange(@PathVariable Long id){
        exchangeService.delete(id);
        return new ResponseEntity<>("정상적으로 삭제되었습니다.", HttpStatus.OK);
    }

}
