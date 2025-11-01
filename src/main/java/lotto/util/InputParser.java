package lotto.util;

import lotto.exception.ErrorMessage;

import java.util.Arrays;
import java.util.List;

import static lotto.domain.LottoRules.PRICE_PER_LOTTO;

public final class InputParser {
    public static final int MAX_AMOUNT = 100_000;
    public static final String SEPARATOR = ",";

    public static int parseAmount(String input) {
        try {
            int amount = Integer.parseInt(input);
            validateAmount(amount);
            return amount;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.AMOUNT_INVALID_VALUE.getMessage());
        }
    }

    public static List<Integer> parseNumbers(String input) {
        return Arrays.stream(input.split(SEPARATOR, -1))
                .map(String::trim)
                .map(InputParser::parseNumber)
                .toList();
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

    private static int parseNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INPUT_IS_INVALID.getMessage());
        }
    }

    private InputParser() {
    }
}
