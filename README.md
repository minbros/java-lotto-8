# 로또
파이프라인 아키첵처를 적용했습니다. 파이프라인 아키텍처를 통해 앱의 흐름이 다음과 같이 순차적으로 진행됩니다.
> 사용자 입력 - 입력 검증 - 로또 번호 생성 - 당첨 금액 확인 - 결과 출력

## 기능 구현 사항
### 사용자 입력 (Void -> UserInput)
- 구매 금액, 당첨 번호, 보너스 번호를 입력받습니다.
- 각각의 원시 입력을 `UserInput` 클래스에 맞게 파싱합니다.
- `UserInput` 객체를 생성해 반환합니다.

### 입력 검증 (UserInput -> UserInput)
- `UserInput` 객체의 필드를 각각 검증합니다.
- 검증 후 그대로 `UserInput` 객체로 반환합니다.

### 로또 번호 생성 (UserInput -> GameData)
- `UserInput`의 `amount`를 참조해 금액만큼 로또 번호를 랜덤하게 생성합니다.
- 생성된 로또 번호들을 출력합니다.
- 생성된 로또 번호들을 추가해 `GameData` 객체로 반환합니다.

### 당첨 금액 확인 (GameData -> GameResult)
- `GameData`의 필드를 참조해 당첨 금액을 확인합니다.
- 확인한 결과를 `GameResult` 객체로 반환합니다.

### 결과 출력 (GameResult -> Void)
- `GameResult`의 필드를 참조해 결과를 출력합니다.
