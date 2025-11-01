package lotto.controller.stage;

import lotto.view.InputView;

public class AmountInputStage implements Stage<Void, Integer>{
    private final InputView inputView;

    public AmountInputStage(InputView inputView) {
        this.inputView = inputView;
    }

    @Override
    public Integer execute(Void input) {
        return null;
    }
}
