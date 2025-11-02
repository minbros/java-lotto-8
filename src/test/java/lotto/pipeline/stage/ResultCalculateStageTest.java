package lotto.pipeline.stage;

import lotto.domain.Lotto;
import lotto.domain.Rank;
import lotto.domain.WinningLotto;
import lotto.dto.LottoData;
import lotto.dto.LottoResult;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static lotto.domain.LottoRules.PRICE_PER_LOTTO;
import static org.assertj.core.api.Assertions.assertThat;

class ResultCalculateStageTest {
    private final List<Lotto> sampleLottoList = List.of(
            new Lotto(List.of(4, 5, 6, 7, 8, 9)), new Lotto(List.of(7, 8, 9, 10, 11, 12)),
            new Lotto(List.of(2, 3, 4, 5, 6, 7)), new Lotto(List.of(1, 2, 3, 4, 44, 45)));

    private final WinningLotto winningLotto = new WinningLotto(
            new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7);

    private final LottoData lottoData = new LottoData(sampleLottoList, winningLotto);

    private final ResultCalculateStage stage = new ResultCalculateStage();

    @Test
    void 당첨_결과와_수익률을_정확하게_구한다() {
        EnumMap<Rank, Integer> expectedRanks = new EnumMap<>(Map.of(
                Rank.FIRST, 0, Rank.SECOND, 1, Rank.THIRD, 0,
                Rank.FOURTH, 1, Rank.FIFTH, 1, Rank.NONE, 1
        ));
        int amount = PRICE_PER_LOTTO * sampleLottoList.size();
        int totalPrize = getTotalPrize(expectedRanks);
        double expectedReturnRate = (double) (totalPrize - amount) / amount;

        LottoResult lottoResult = stage.execute(lottoData);

        assertThat(lottoResult).extracting(LottoResult::ranks, LottoResult::returnRate)
                .containsExactly(expectedRanks, expectedReturnRate);
    }

    private static int getTotalPrize(EnumMap<Rank, Integer> expectedRanks) {
        return expectedRanks.entrySet().stream()
                .mapToInt(rank -> rank.getKey().getPrize() * rank.getValue())
                .sum();
    }
}
