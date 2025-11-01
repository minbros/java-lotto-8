package lotto.util;

import lotto.exception.ErrorMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class InputParserTest {
    @Test
    void 구매_금액을_정수로_변환한다() {
        String input = "2000";

        int amount = InputParser.parseNumber(input);

        assertThat(amount).isEqualTo(2000);
    }

    @ParameterizedTest
    @CsvSource({
            "thousand, NUMBER_IS_INVALID",
            "천만 원, NUMBER_IS_INVALID",
            "0, NUMBER_MUST_BE_POSITIVE"
    })
    void 잘못된_구매_금액을_입력하면_예외가_발생한다(String input, String errorKey) {
        String expectedMessage = ErrorMessage.valueOf(errorKey).getMessage();
        assertThatThrownBy(() -> InputParser.parseNumber(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(expectedMessage);
    }

    @Test
    void 당첨_번호를_리스트로_변환한다() {
        String input = "1, 2, 3, 4, 5, 6";

        List<Integer> numbers = InputParser.parseNumbers(input);

        assertThat(numbers).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,,2,,3,,4,,5,,6", ",1,2,3,4,5,6", "1,2,3,4,5,6,"})
    void 잘못된_당첨_번호를_입력하면_예외가_발생한다(String input) {
        assertThatThrownBy(() -> InputParser.parseNumbers(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.NUMBER_IS_INVALID.getMessage());
    }
}
