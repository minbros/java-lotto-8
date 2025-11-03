package lotto.pipeline.stage;

import lotto.domain.Lotto;
import lotto.domain.LottoGenerator;
import lotto.view.OutputView;

import java.util.List;
import java.util.stream.IntStream;

import static lotto.domain.LottoRules.PRICE_PER_LOTTO;

/**
 * 구매 금액을 입력받아 해당되는 개수만큼 로또를 생성하고, 출력하는 클래스입니다.
 *
 * @see Lotto
 */
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
