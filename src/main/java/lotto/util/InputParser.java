package lotto.util;

import lotto.exception.ErrorMessage;

import java.util.Arrays;
import java.util.List;

/**
 * 사용자 입력을 파싱하는 클래스입니다.
 */
public final class InputParser {
    public static final String SEPARATOR = ",";

    /**
     * @throws IllegalArgumentException 입력받은 숫자가 다음과 같을 경우
     *                                  <ul>
     *                                      <li>양수가 아닐 때</li>
     *                                      <li>int 범위에 맞지 않을 때</li>
     *                                      <li>숫자로 구성되어 있지 않을 때</li>
     *                                  </ul>
     */
    public static int parseNumber(String input) {
        try {
            int number = Integer.parseInt(input);
            validate(number);
            return number;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.NUMBER_IS_INVALID.getMessage());
        }
    }

    /**
     * {@value SEPARATOR}를 기준으로 숫자를 구분합니다.
     *
     * @throws IllegalArgumentException 입력받은 숫자 중 해당되는 값이 존재하는 경우
     *                                  <ul>
     *                                      <li>양수가 아닐 때</li>
     *                                      <li>int 범위에 맞지 않을 때</li>
     *                                      <li>숫자로 구성되어 있지 않을 때</li>
     *                                      <li>{@code 1,,2,,3,,4,,5,,6}과 같이 {@value SEPARATOR}가 잘못 적용된 경우</li>
     *                                  </ul>
     */
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
