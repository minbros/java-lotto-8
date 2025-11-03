package lotto.domain;

import lotto.exception.ErrorMessage;
import lotto.exception.LottoException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7);

        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(LottoException.class)
                .hasMessageContaining(ErrorMessage.LOTTO_INVALID_NUMBER_COUNT.getMessage());
    }

    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 5);

        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(LottoException.class)
                .hasMessageContaining(ErrorMessage.LOTTO_DUPLICATE_NUMBERS.getMessage());
    }

    @Test
    void 로또_번호는_모두_1부터_45까지의_정수여야_한다() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 46);

        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(LottoException.class)
                .hasMessageContaining(ErrorMessage.LOTTO_INVALID_NUMBER_VALUE.getMessage());
    }
}
