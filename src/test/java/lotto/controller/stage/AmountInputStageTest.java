package lotto.controller.stage;

import lotto.exception.ErrorMessage;
import lotto.view.FakeInputView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AmountInputStageTest {
    @Test
    void 구매_금액을_입력받으면_정수로_변환한다() {
        FakeInputView inputView = new FakeInputView("8000");
        AmountInputStage stage = new AmountInputStage(inputView);

        int amount = stage.execute(null);

        assertThat(amount).isEqualTo(8000);
    }

    @ParameterizedTest
    @CsvSource({
            "7500, AMOUNT_DOES_NOT_FIT",
            "10000000, AMOUNT_TOO_LARGE"    // 천만 원
    })
    void 잘못된_구매_금액을_입력받으면_예외가_발생한다(String input, String errorKey) {
        FakeInputView inputView = new FakeInputView(input);
        AmountInputStage stage = new AmountInputStage(inputView);

        String expectedMessage = ErrorMessage.valueOf(errorKey).getMessage();
        assertThatThrownBy(() -> stage.execute(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(expectedMessage);
    }
}
