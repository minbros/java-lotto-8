package lotto.pipeline.stage;

import lotto.domain.Lotto;
import lotto.domain.LottoGenerator;
import lotto.view.OutputView;

import java.util.List;
import java.util.stream.IntStream;

import static lotto.domain.LottoRules.PRICE_PER_LOTTO;

@SuppressWarnings("ClassCanBeRecord")
public class LottoGenerationStage implements Stage<Integer, List<Lotto>> {
    private final OutputView outputView;

    public LottoGenerationStage(OutputView outputView) {
        this.outputView = outputView;
    }

    @Override
    public List<Lotto> execute(Integer amount) {
        int lottoCount = amount / PRICE_PER_LOTTO;
        List<Lotto> lottoList = IntStream.range(0, lottoCount)
                .mapToObj(i -> LottoGenerator.generate())
                .toList();
        outputView.printLottoList(lottoList);
        return lottoList;
    }
}
