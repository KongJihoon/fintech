# 📖 핀테크(Fin-Tech) 프로젝트

## ⛅ 개요

- 계좌 입금, 출금, 송금 기능을 구현한 핀테크 프로젝트입니다.

## ⚙ 기술 스택

<div> 
  <img src="https://img.shields.io/badge/Java-181717?style=for-the-badge&logo=Conda-Forge&logoColor=white"> 
  <img src="https://img.shields.io/badge/SpringBoot-181717?style=for-the-badge&logo=SpringBoot&logoColor=white"> 
  <img src="https://img.shields.io/badge/MySQL-181717?style=for-the-badge&logo=MySql&logoColor=white">
  <img src="https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white">
</div>

## 📆 개발 기간
- 2024.02.19 ~ 2024.03.25

## 1️⃣ ERD

![img](https://github.com/KongJihoon/fintech/assets/138794635/f2aa6b9f-3ff9-4b47-aa06-e2c1921eb728)


## 2️⃣ 기능 사항

### 회원 가입
- [x] 이름, 휴대폰번호, 비밀번호를 이용하여 회원가입
- [x] 휴대폰번호 중복 불가
- [x] 회원 가입 시 CUSTOMER 권한 부여

### 로그인
- [x] 휴대폰 번호, 비밀번호를 이용한 로그인
- [x] 모든 기능은 로그인을 후 사용가능(Jwt 토큰 발급)

### 계좌
- [x] 계좌 생성, 조회, 삭제(계좌 생성은 zerobase은행의 계좌만 가능)
- [x] 고객 당 계좌는 10개만 가능
- [x] 계좌 해지 시 상태 변화(IN_USE, UN_REGISTERED)
- [x] 중복된 계좌 번호는 생성 불가
- [x] 모든 기능은 로그인 후 사용 가능(Jwt 토큰 이용)
- [x] 계좌 목록 조회(이름, 은행명, 계좌번호, 잔액 표시)

### 타 은행 계좌
- [x] 은행명, 소유주명, 계좌번호를 이용해 등록
- [x] 계좌번호는 중복되면 안되며, 타 은행 계좌 등록은 ZERO_BASE은행을 제외한 계좌만 가능하다 (OOP, SPRING, RDBMS, BACK)

### 계좌 검색
- [x] 계좌 번호와 은행명을 이용한 계좌 검색(등록 되있거나 생성된 계좌만 조회 가능)
- [x] 계좌 검색 시 해당 계좌 이름, 은행명, 계좌 번호, 잔액 표시

### 입출금
- [x] 입금 기능(입금 시 입금자명, 계좌번호, 입금 금액 입력)
- [x] 입금 시 계좌번호가 존재하지 않거나 해지 상태인 경우 입금 불가
- [x] 자신의 계좌에 있는 금액 출금(출금 시 출금자명, 계좌번호, 비밀번호, 출금 금액 입력)
- [x] 출금 시 계좌번호가 존재하지 않거나 비밀번호가 일치하지 않거나 해지 상태인 경우 출금 불가

### 송금
- [x] 본인 계좌의 잔액 내에서 송금 기능
- [x] 송금 시 송금자 계좌번호, 받는사람 계좌번호, 송금자 비밀번호, 송금할 금액 입력
- [x] 송금 시 송금 금액이 계좌 잔액 보다 크면 송금 불가

### 입출금 내역 조회
- [x] 본인의 계좌 입출금 내역 조회 기능(사용자 이름, 거래 금액, 거래 날짜, 거래 유형)
