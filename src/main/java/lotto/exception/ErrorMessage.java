package lotto.exception;

public enum ErrorMessage {
    LOTTO_DUPLICATE_NUMBERS("중복되는 로또 번호가 있습니다."),
    LOTTO_INVALID_NUMBER_COUNT("로또 번호는 6개여야 합니다."),
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
