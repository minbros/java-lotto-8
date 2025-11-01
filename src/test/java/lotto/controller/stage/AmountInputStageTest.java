package lotto.controller.stage;

import lotto.view.FakeInputView;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AmountInputStageTest {
    @Test
    void 구매_금액을_입력받으면_정수로_변환한다() {
        FakeInputView inputView = new FakeInputView("8000");
        AmountInputStage stage = new AmountInputStage(inputView);

        int amount = stage.execute(null);

        assertThat(amount).isEqualTo(8000);
    }

    @Test
    void 잘못된_구매_금액을_입력받으면_예외가_발생한다() {
        String[] inputs = new String[]{"10000000", "9500", "8000"};
        FakeInputView inputView = new FakeInputView(inputs);
        AmountInputStage stage = new AmountInputStage(inputView);

        int amount = stage.execute(null);

        assertThat(amount).isEqualTo(8000);
    }
}
