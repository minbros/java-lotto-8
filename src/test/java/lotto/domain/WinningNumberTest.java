package lotto.domain;

import lotto.exception.ErrorMessage;
import lotto.exception.LottoException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static lotto.domain.LottoRules.MAXIMUM_NUMBER;
import static lotto.domain.LottoRules.MINIMUM_NUMBER;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningNumberTest {
    private final List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

    @ParameterizedTest
    @ValueSource(ints = {MINIMUM_NUMBER - 1, MAXIMUM_NUMBER + 1})
    void 보너스_번호가_적합하지_않으면_예외가_발생한다(int bonusNumber) {
        assertThatThrownBy(() -> new WinningNumber(numbers, bonusNumber))
                .isInstanceOf(LottoException.class)
                .hasMessageContaining(ErrorMessage.BONUS_INVALID_NUMBER_VALUE.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    void 보너스_번호가_당첨_번호와_겹치면_예외가_발생한다(int bonusNumber) {
        assertThatThrownBy(() -> new WinningNumber(numbers, bonusNumber))
                .isInstanceOf(LottoException.class)
                .hasMessageContaining(ErrorMessage.BONUS_DUPLICATE_NUMBERS.getMessage());
    }
}
