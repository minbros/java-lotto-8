package lotto.controller.stage;

import lotto.domain.Lotto;
import lotto.view.OutputView;

import java.util.List;

@SuppressWarnings("ClassCanBeRecord")
public class LottoGenerationStage implements Stage<Integer, List<Lotto>> {
    private final OutputView outputView;

    public LottoGenerationStage(OutputView outputView) {
        this.outputView = outputView;
    }

    @Override
    public List<Lotto> execute(Integer input) {
        return List.of();
    }
}
