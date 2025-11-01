package lotto.controller.stage;

import lotto.domain.LottoRules;
import lotto.exception.ErrorMessage;
import lotto.util.InputParser;
import lotto.view.InputView;

@SuppressWarnings("ClassCanBeRecord")
public class AmountInputStage implements Stage<Void, Integer>{
    private static final int MAX_AMOUNT = 1_000_000;

    private final InputView inputView;

    public AmountInputStage(InputView inputView) {
        this.inputView = inputView;
    }

    @Override
    public Integer execute(Void input) {
        int amount = InputParser.parseNumber(inputView.readAmount());
        validate(amount);
        return amount;
    }

    private void validate(int amount) {
        if (amount > MAX_AMOUNT) {
            throw new IllegalArgumentException(ErrorMessage.AMOUNT_TOO_LARGE.getMessage());
        }
        if (amount % LottoRules.PRICE_PER_LOTTO != 0) {
            throw new IllegalArgumentException(ErrorMessage.AMOUNT_DOES_NOT_FIT.getMessage());
        }
    }
}
