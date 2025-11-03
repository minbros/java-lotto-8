package lotto.pipeline.stage;

import lotto.domain.Lotto;
import lotto.domain.WinningLotto;
import lotto.dto.LottoData;
import lotto.util.InputParser;
import lotto.util.RetryingInputSupplier;
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
        Lotto lotto = RetryingInputSupplier.get(() -> {
            List<Integer> winningNumbers = InputParser.parseNumbers(inputView.readWinningNumbers());
            return new Lotto(winningNumbers);
        });
        return RetryingInputSupplier.get(() -> {
            int bonusNumber = InputParser.parseNumber(inputView.readBonusNumber());
            WinningLotto winningLotto = new WinningLotto(lotto, bonusNumber);
            return new LottoData(lottoList, winningLotto);
        });
    }
}
