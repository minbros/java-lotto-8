package lotto.util;

import lotto.exception.ErrorMessage;

import java.util.Arrays;
import java.util.List;

public final class InputParser {
    public static final String SEPARATOR = ",";

    public static int parseNumber(String input) {
        try {
            int number = Integer.parseInt(input);
            validate(number);
            return number;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.NUMBER_IS_INVALID.getMessage());
        }
    }

    public static List<Integer> parseNumbers(String input) {
        return Arrays.stream(input.split(SEPARATOR, -1))
                .map(String::trim)
                .map(InputParser::parseNumber)
                .toList();
    }

    private static void validate(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException(ErrorMessage.NUMBER_MUST_BE_POSITIVE.getMessage());
        }
    }

    private InputParser() {
    }
}
