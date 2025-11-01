package lotto.pipeline.stage;

import lotto.domain.Lotto;
import lotto.domain.LottoGenerator;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ClassCanBeRecord")
public class LottoGenerationStage implements Stage<Integer, List<Lotto>> {
    private final OutputView outputView;

    public LottoGenerationStage(OutputView outputView) {
        this.outputView = outputView;
    }

    @Override
    public List<Lotto> execute(Integer count) {
        List<Lotto> lottoList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Lotto lotto = LottoGenerator.generate();
            lottoList.add(lotto);
        }
        outputView.printLottoList(lottoList);
        return List.copyOf(lottoList);
    }
}
