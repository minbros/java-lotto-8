package lotto.pipeline.stage;

import lotto.domain.Lotto;
import lotto.domain.WinningLotto;
import lotto.dto.LottoData;
import lotto.util.InputParser;
import lotto.util.RetryingInputSupplier;
import lotto.view.InputView;

import java.util.List;

/**
 * 사용자로부터 당첨 번호와 보너스 번호를 입력받고, 생성된 로또 번호를 가져와 {@link LottoData}를 생성합니다.
 */
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
