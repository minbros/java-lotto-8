package lotto.pipeline.stage;

import lotto.domain.Lotto;
import lotto.dto.LottoData;
import lotto.view.InputView;

import java.util.List;

@SuppressWarnings("ClassCanBeRecord")
public class WinningLottoInputStage implements Stage<List<Lotto>, LottoData> {
    private final InputView inputView;

    public WinningLottoInputStage(InputView inputView) {
        this.inputView = inputView;
    }

    @Override
    public LottoData execute(List<Lotto> lottoList) {
        return null;
    }
}
