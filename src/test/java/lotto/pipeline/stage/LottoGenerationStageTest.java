package lotto.pipeline.stage;

import camp.nextstep.edu.missionutils.test.Assertions;
import lotto.domain.Lotto;
import lotto.view.OutputView;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoGenerationStageTest {
    private final LottoGenerationStage stage = new LottoGenerationStage(new OutputView());

    @SuppressWarnings("unchecked")
    @Test
    void 정상적으로_로또를_생성한다() {
        List<Integer> numbers1 = List.of(1, 2, 3, 4, 5, 6);
        List<Integer> numbers2 = List.of(7, 8, 9, 10, 11, 12);
        List<Integer> numbers3 = List.of(13, 14, 15, 16, 17, 18);
        List<Integer> numbers4 = List.of(19, 20, 21, 22, 23, 24);

        Assertions.assertRandomUniqueNumbersInRangeTest(
                () -> {
                    List<Lotto> lottoList = stage.execute(4000);
                    assertThat(lottoList).extracting(Lotto::getNumbers)
                            .containsExactly(numbers1, numbers2, numbers3, numbers4);
                },
                numbers1, numbers2, numbers3, numbers4
        );
    }
}
