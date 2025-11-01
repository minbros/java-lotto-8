package lotto.pipeline.stage;

import lotto.domain.Lotto;
import lotto.domain.WinningLotto;
import lotto.dto.LottoData;
import lotto.view.FakeInputView;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WinningLottoInputStageTest {
    private final Lotto sampleLotto = new Lotto(List.of(6, 5, 4, 3, 2, 1));

    @Test
    void 당첨_번호와_보너스_번호_입력이_정상적으로_이루어진다() {
        FakeInputView inputView = new FakeInputView("1, 2, 3, 4, 5, 6", "7");
        WinningLottoInputStage stage = new WinningLottoInputStage(inputView);

        LottoData lottoData = stage.execute(List.of(sampleLotto));

        assertLottoData(lottoData);
    }

    @Test
    void 당첨_번호를_올바르게_입력할_때까지_계속_입력받는다() {
        String[] inputs = new String[]{"1, 2, 3, 4, 5, 100", "1, 2, 3, 4, 5, 5", "1, 2, 3, 4, 5, 6", "7"};
        FakeInputView inputView = new FakeInputView(inputs);
        WinningLottoInputStage stage = new WinningLottoInputStage(inputView);

        LottoData lottoData = stage.execute(List.of(sampleLotto));

        assertLottoData(lottoData);
    }

    private void assertLottoData(LottoData lottoData) {
        assertThat(lottoData.lottoList()).containsExactly(sampleLotto);
        assertThat(lottoData.winningLotto())
                .extracting(w -> w.lotto().getNumbers(), WinningLotto::bonusNumber)
                .containsExactly(List.of(1, 2, 3, 4, 5, 6), 7);
    }
}
