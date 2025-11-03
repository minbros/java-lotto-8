package lotto.pipeline.stage;

import lotto.domain.LottoRules;
import lotto.exception.ErrorMessage;
import lotto.util.InputParser;
import lotto.util.RetryingInputSupplier;
import lotto.view.InputView;

import static lotto.domain.LottoRules.MAX_AMOUNT;

/**
 * 구매 금액을 입력받아 검증 후 {@link Integer} 타입으로 변환하는 클래스입니다.
 */
@SuppressWarnings("ClassCanBeRecord")
public class AmountInputStage implements Stage<Void, Integer> {
    private final InputView inputView;

    public AmountInputStage(InputView inputView) {
        this.inputView = inputView;
    }

    @Override
    public Integer execute(Void ignore) {
        return RetryingInputSupplier.get(() -> {
            int amount = InputParser.parseNumber(inputView.readAmount());
            validate(amount);
            return amount;
        });
    }

    private static void validate(int amount) {
        if (amount > MAX_AMOUNT) {
            throw new IllegalArgumentException(ErrorMessage.AMOUNT_TOO_LARGE.getMessage());
        }
        if (amount % LottoRules.PRICE_PER_LOTTO != 0) {
            throw new IllegalArgumentException(ErrorMessage.AMOUNT_DOES_NOT_FIT.getMessage());
        }
    }
}
