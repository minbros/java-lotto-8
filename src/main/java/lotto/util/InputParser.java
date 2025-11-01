package lotto.util;

import lotto.exception.ErrorMessage;

import static lotto.domain.LottoRules.PRICE_PER_LOTTO;

public final class InputParser {
    public static final int MAX_AMOUNT = 100_000;

    public static int parseAmount(String input) {
        try {
            int amount = Integer.parseInt(input);
            validateAmount(amount);
            return amount;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.AMOUNT_INVALID_VALUE.getMessage());
        }
    }

    private static void validateAmount(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(ErrorMessage.AMOUNT_MUST_BE_POSITIVE.getMessage());
        }
        if (amount > MAX_AMOUNT) {
            throw new IllegalArgumentException(ErrorMessage.AMOUNT_TOO_LARGE.getMessage());
        }
        if (amount % PRICE_PER_LOTTO != 0) {
            throw new IllegalArgumentException(ErrorMessage.AMOUNT_DOES_NOT_FIT.getMessage());
        }
    }

    private InputParser() {
    }
}
