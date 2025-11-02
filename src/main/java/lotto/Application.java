package lotto;

import camp.nextstep.edu.missionutils.Console;
import lotto.dto.LottoResult;
import lotto.pipeline.LottoPipeline;
import lotto.pipeline.stage.AmountInputStage;
import lotto.pipeline.stage.LottoGenerationStage;
import lotto.pipeline.stage.ResultCalculateStage;
import lotto.pipeline.stage.WinningLottoInputStage;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        LottoPipeline<Void, LottoResult> pipeline = initPipeline(inputView, outputView);
        LottoResult result = pipeline.execute(null);
        outputView.printResult(result);
        Console.close();
    }

    private static LottoPipeline<Void, LottoResult> initPipeline(InputView inputView, OutputView outputView) {
        return LottoPipeline.of(new AmountInputStage(inputView))
                .then(new LottoGenerationStage(outputView))
                .then(new WinningLottoInputStage(inputView))
                .then(new ResultCalculateStage());
    }
}
