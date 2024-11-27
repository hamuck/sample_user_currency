# sparta_schedule


---

# API 명세서

## User
| 기능        | Method | URL        | request | response | 상태코드                        |
|-----------|--------|------------|---------|----------|-----------------------------|
| 유저 생성(가입) | POST   | /api/users| 요청 body | 응답 정보    | 201: 정상 생성, 400: 등록 실패     |
| 유저 전체 조회  | GET    | /api/users | -       | 단건 응답 정보 | 200: 정상 조회                  |
| 유저 조회     | GET    | /api/users/{id} | -       | 단건 응답 정보 | 200: 정상 조회                  |
| 유저 삭제     | DELETE | /api/users/{id} | -       | -        | 200: 정상 수정, 404: 존재하지 않는 유저 |

## Currency
| 기능       | Method | URL                  | request | response       | 상태코드                        |
|----------|--------|----------------------|---------|----------------|-----------------------------|
| 통화 생성    | POST   | /api/currencies      | 요청 body | 응답 정보          | 201: 정상 생성                  |
| 통화 전체 조회 | GET    | /api/currencies      | -       | 다건 응답 정보(List) | 200: 정상 조회                  |
| 통화 선택 조회 | GET    | /api/currencies/{id} | -       | 단건 응답 정보       | 200: 정상 조회                  |

## Currency Exchange
| 기능          | Method | URL                | request | response | 상태코드                        |
|-------------|--------|--------------------|---------|----------|-----------------------------|
| 환전 요청 생성    | POST   | /api/exchange      | 요청 body | 응답 정보    | 201: 정상 생성, 400: 등록 실패      |
| 특정 환전 요청 조회 | GET    | /api/exchange/{id} | -       | 단건 응답 정보 | 200: 정상 조회                  |
| 환전 요청 취소    | POST   | /api/exchange/{id} | -       | -        | 200: 정상 수정                  |
| 환전 요청 삭제    | DELETE | /api/exchange      | -       | -        | 200: 정상 수정, 404: 존재하지 않는 요청 |
---

## 유저 생성
유저를 생성합니다.

|메서드| 요청 URL                        |
|---|-------------------------------|
|POST| http://{SERVER_URL}/api/users |

**Request**

```json
{
  "name" : "유저 이름", 
  "mail" : "유저 이메일"
}
```


| 파라미터     |타입   | 필수여부 | 설명     |
|----------|---|------|--------|
| username | String  | 필수   | 유저 이름  |
| usermail | String | 필수   | 유저 이메일 |

**Response**
```json
{
  "id": 1,
  "name": "유저 이름",
  "mail": "유저 이메일"
}
```

| 파라미터     | 타입     | 필수여부 | 설명       |
|----------|--------|------|----------|
| id       | Long   | 필수   | 유저 고유 번호 |
| username | String | 필수   | 유저 이름    |
| usermail | String | 필수   | 유저 이메일   |



---
## 전체 유저 조회
선택한 유저를 조회합니다.

|메서드| 요청 URL                        |
|---|-------------------------------|
|GET| http://{SERVER_URL}/api/users |

**Request**

- Request 없음


**Response**

```json
[
    {
      "id" : 1,
      "name" : "유저 이름",
      "mail" : "유저 이메일"
    },
    {
    "id" : 1,
    "name" : "유저 이름",
    "mail" : "유저 이메일"
    },
    {
    "id" : 1,
    "name" : "유저 이름",
    "mail" : "유저 이메일"
    }
]
```

| 파라미터     | 타입     | 필수여부 | 설명     |
|----------|--------|------|--------|
| id       | Long   | 필수   | 유저 고유 번호 |
| username | String | 필수   | 유저 이름  |
| usermail | String | 필수   | 유저 이메일 |


---

## 선택 유저 조회
선택한 유저를 조회합니다.

|메서드| 요청 URL                             |
|---|------------------------------------|
|GET| http://{SERVER_URL}/api/users/{id} |

**Request**

- Request 없음


**Response**

```json
{
  "id": 1,
  "name": "유저 이름",
  "mail": "유저 이메일"
}
```

| 파라미터     | 타입     | 필수여부 | 설명     |
|----------|--------|------|--------|
| id       | Long   | 필수   | 유저 고유 번호 |
| username | String | 필수   | 유저 이름  |
| usermail | String | 필수   | 유저 이메일 |


---
##  유저 삭제
선택한 유저의 정보를 삭제합니다.

| 메서드    | 요청 URL                             |
|--------|------------------------------------|
| DELETE | http://{SERVER_URL}/api/users/{id} |

**Request**

- Request 없음



**Response**

- body 메세지 : "정상적으로 삭제되었습니다."

---
## 통화 생성
통화를 생성합니다.

**Request**

```json
{
  "currencyName" : "통화 이름",
  "exchageRate" : 0.856745,
  "symbol" : "$"
}
```

| 메서드  | 요청 URL                             |
|------|------------------------------------|
| POST | http://{SERVER_URL}/api/currencies |


| 파라미터         | 타입         | 필수여부 | 설명    |
|--------------|------------|------|-------|
| currencyName | String     | 필수   | 통화 이름 | 
| exchangeRate | BigDecimal | 필수   | 환율    |
| symbol       | String     | 필수   | 통화 기호 |

**Response**

```json
{
  "id" : 1,
  "currencyName" : "통화 이름",
  "exchageRate" : 0.856745,
  "symbol" : "$"
}
```

| 파라미터     | 타입     | 필수여부 | 설명        |
|----------|--------|------|-----------|
| id       | Long   | 필수   | 스케쥴 고유 번호 |
| currencyName | String     | 필수   | 통화 이름 | 
| exchangeRate | BigDecimal | 필수   | 환율    |
| symbol       | String     | 필수   | 통화 기호 |


---

## 통화 전체 조회
저장되어있는 모든 통화를 조회합니다.

**Request**

- Request 없음

| 메서드 | 요청 URL                             |
|-----|------------------------------------|
| GET | http://{SERVER_URL}/api/currencies |

**Response**

```json
[
      {
        "id" : 1,
        "currencyName" : "통화 이름",
        "exchageRate" : 0.856745,
        "symbol" : "$"
      },
      {
        "id" : 1,
        "currencyName" : "통화 이름",
        "exchageRate" : 0.856745,
        "symbol" : "$"
      },
      {
        "id" : 1,
        "currencyName" : "통화 이름",
        "exchageRate" : 0.856745,
        "symbol" : "$"
      }
]
```

| 파라미터     | 타입     | 필수여부 | 설명        |
|----------|--------|------|-----------|
| id       | Long   | 필수   | 스케쥴 고유 번호 |
| currencyName | String     | 필수   | 통화 이름 | 
| exchangeRate | BigDecimal | 필수   | 환율    |
| symbol       | String     | 필수   | 통화 기호 |

---

## 통화 선택 조회
선택한 통화의 정보를 조회합니다.

| 메서드 | 요청 URL                                  |
|-----|-----------------------------------------|
| GET | http://{SERVER_URL}/api/currencies/{id} |

**Request**

- Request 없음

**Response**
```json
{
  "id" : 1,
  "currencyName" : "통화 이름",
  "exchageRate" : 0.856745,
  "symbol" : "$"
}
```


| 파라미터     | 타입     | 필수여부 | 설명        |
|----------|--------|------|-----------|
| id       | Long   | 필수   | 스케쥴 고유 번호 |
| currencyName | String     | 필수   | 통화 이름 | 
| exchangeRate | BigDecimal | 필수   | 환율    |
| symbol       | String     | 필수   | 통화 기호 |


---


## 환전 요청 생성
유저를 생성합니다.

|메서드| 요청 URL                       |
|---|------------------------------|
|POST| http://{SERVER_URL}/exchange |

**Request**

```json
{
  "userId" : 1, 
  "toCurrencyId" : 2,
  "amountInKrw" : 1000
}
```


| 파라미터         | 타입   | 필수여부 | 설명       |
|--------------|------|------|----------|
| userId       | Long | 필수   | 유저 고유 번호 |
| toCurrencyId | Long | 필수   | 통화 고유 번호 |
| amountInKrw  | Long | 필수   | 환전 전 금액  |

**Response**
```json
{
  "id" : 1,
  "userId": 1,
  "toCurrencyName": "달러",
  "amountInkrw": 1000,
  "amountAfterExchange" : 250.0,
  "status" : "nomal"
}
```

| 파라미터                | 타입     | 필수여부 | 설명       |
|---------------------|--------|------|----------|
| userId              | Long   | 필수   | 유저 고유 번호 |
| toCurrencyName      | String | 필수   | 통화 이름    |
| amountInKrw         | Long   | 필수   | 환전 전 금액  |
| amountAfterExchange | Long   | 필수   | 환전 후 금액  |
| status              | String | 필수   | 환전 상태    |

---

## 특정 환전 요청 조회
선택한 환전 요청을 조회합니다.

|메서드| 요청 URL                                |
|---|---------------------------------------|
|GET| http://{SERVER_URL}/api/exchange/{id} |

**Request**

- Request 없음


**Response**
```json
{
  "id" : 1,
  "userId": 1,
  "toCurrencyName": "달러",
  "amountInkrw": 1000,
  "amountAfterExchange" : 250.0,
  "status" : "nomal"
}
```

| 파라미터                | 타입     | 필수여부 | 설명       |
|---------------------|--------|------|----------|
| userId              | Long   | 필수   | 유저 고유 번호 |
| toCurrencyName      | String | 필수   | 통화 이름    |
| amountInKrw         | Long   | 필수   | 환전 전 금액  |
| amountAfterExchange | Long   | 필수   | 환전 후 금액  |
| status              | String | 필수   | 환전 상태    |


---
##  환전 요청 취소
선택한 환전 요청을 취소(수정)합니다.

| 메서드  | 요청 URL                                |
|------|---------------------------------------|
| POST | http://{SERVER_URL}/api/exchange/{id} |

**Request**

- Request 없음



**Response**


```json
{
  "id" : 1,
  "userId": 1,
  "toCurrencyName": "달러",
  "amountInkrw": 1000,
  "amountAfterExchange" : 250.0,
  "status" : "fail"
}
```
---
##  환전 요청 삭제
선택한 환전 요청을 삭제합니다.

| 메서드    | 요청 URL                                |
|--------|---------------------------------------|
| DELETE | http://{SERVER_URL}/api/exchange/{id} |

**Request**

- Request 없음



**Response**

- body 메세지 : "정상적으로 삭제되었습니다."

---
# ERD

![스크린샷 2024-11-27 오후 8 19 46](https://github.com/user-attachments/assets/090b7b54-0dc9-4493-8c9f-8ba790c38c02)





