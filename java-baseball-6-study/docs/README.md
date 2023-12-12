# 숫자 야구 게임

    컴퓨터와 대결하는 숫자 야구 게임 입니다.
    컴퓨터의 숫자를 모두 맞추면 승리합니다.

# 기능 목록

- 게임 초기화 및 시작
    - [x] 시작 문구 출력
    - [x] 컴퓨터가 1~9 사이의 서로 다른 숫자 3개 무작위로 선택
  
- 사용자 입력 처리
    - [x] 사용자에게 3가지 숫자 입력 요청 문구 출력
    - [x] 입력 값 검증 후 올바르지 않을 경우 InvalidInputException 발생
  
- 게임 로직 처리
    - [x] 사용자의 입력과 컴퓨터의 숫자 비교하여 스트라이크, 볼 계산
  
- 결과 출력 (볼, 스트라이크, 낫싱)
    - [x] 3스트라이크인 경우 게임 종료 및 메세지 출력
  
- 게임 재시작 또는 종료
    - [x] 사용자에게 게임 재시작(1) 또는 완전한 종료(2)를 입력하도록 문구 출력
    - [x] 입력 값 검증 후 올바르지 않을 경우 InvalidInputException 발생
    - [x] 사용자 입력값에 따라 재시작 또는 완전한 종료 처리

## 클래스 설계

### 1. domain

- NumberBaseballGame : 게임 로직을 처리합니다. 사용자의 입력을 받아 컴퓨터의 숫자와 비교하고 결과를 생성합니다.
- BaseballNumber : 야구 게임에서 사용되는 숫자를 나타내는 객체입니다.

### 2. View

- InputView : 사용자로부터 입력을 받는 기능을 관리합니다.
- OutputView : 게임의 상태와 결과를 화면에 출력하는 기능을 관리합니다. 

### 3. Controller

- GameController : 사용자의 입력을 받고 게임의 흐름을 제어합니다.

### 4. Exception

- InvalidInputException : 잘못된 입력을 처리하는 사용자 정의 예외입니다.

### 5. Util

- NumberGenerator : 숫자 생성기 인터페이스
- RandomNumberGenerator : 랜덤 숫자 생성 구현체

## 리팩터링

- [x] 메소드 분리
- [x] 문자열 포장
- [x] 객체 역할 분리
- [x] static 메소드만 있는 클래스 인스턴스 생성 금지
- [x] enum 굳이 게터 안만들고 public final 으로 사용