package lotto.exception;

public enum ErrorMessage {
    LOTTO_INVALID_NUMBER_COUNT("로또 번호는 6개여야 합니다.");

    private static final String ERROR_PREFIX = "[ERROR] ";

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return ERROR_PREFIX + message;
    }
}
