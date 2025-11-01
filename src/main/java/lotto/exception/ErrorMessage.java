package lotto.exception;

import lotto.domain.LottoRules;

public enum ErrorMessage {
    AMOUNT_DOES_NOT_FIT("구매 금액은 " + LottoRules.PRICE_PER_LOTTO + " 단위여야 합니다."),
    AMOUNT_INVALID_VALUE("구매 금액이 올바르지 않습니다."),
    AMOUNT_MUST_BE_POSITIVE("구매 금액은 양수여야 합니다."),
    AMOUNT_TOO_LARGE("구매 금액이 너무 큽니다."),

    BONUS_DUPLICATE_NUMBERS("보너스 번호가 당첨 번호와 중복됩니다."),
    BONUS_INVALID_NUMBER_VALUE("보너스 번호의 값이 범위에 맞지 않습니다."),

    LOTTO_DUPLICATE_NUMBERS("중복되는 로또 번호가 있습니다."),
    LOTTO_INVALID_NUMBER_COUNT("로또 번호는 " + LottoRules.NUMBER_COUNT + "개여야 합니다."),
    LOTTO_INVALID_NUMBER_VALUE("로또 번호의 값이 범위에 맞지 않습니다."),

    RANK_INVALID_MATCH_COUNT("매칭 개수가 잘못되었습니다.");

    private static final String ERROR_PREFIX = "[ERROR] ";

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return ERROR_PREFIX + message;
    }
}
