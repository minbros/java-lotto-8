package lotto.pipeline.stage;

import lotto.domain.Lotto;
import lotto.domain.Rank;
import lotto.domain.WinningLotto;
import lotto.dto.LottoData;
import lotto.dto.LottoResult;

import java.util.*;

import static lotto.domain.LottoRules.PRICE_PER_LOTTO;

/**
 * {@link LottoData}를 가져와 당첨 결과와 수익률을 {@link LottoResult}로 반환합니다.
 */
public class ResultCalculateStage implements Stage<LottoData, LottoResult> {
    @Override
    public LottoResult execute(LottoData lottoData) {
        List<Lotto> lottoList = lottoData.lottoList();
        WinningLotto winningLotto = lottoData.winningLotto();
        EnumMap<Rank, Integer> ranks = calculateRanks(lottoList, winningLotto);
        double returnRate = calculateReturnRate(lottoList.size(), ranks);
        return new LottoResult(Map.copyOf(ranks), returnRate);
    }

    private static EnumMap<Rank, Integer> calculateRanks(List<Lotto> lottoList, WinningLotto winningLotto) {
        EnumMap<Rank, Integer> ranks = initRanks();
        for (Lotto lotto : lottoList) {
            int matchCount = winningLotto.getMatchCount(lotto);
            boolean matchesBonus = winningLotto.hasBonus(lotto);
            Rank rank = Rank.of(matchCount, matchesBonus);
            ranks.merge(rank, 1, Integer::sum);
        }
        return ranks;
    }

    private static double calculateReturnRate(int lottoCount, EnumMap<Rank, Integer> ranks) {
        int amount = lottoCount * PRICE_PER_LOTTO;
        long totalPrize = calculateTotalPrize(ranks);
        return (double) totalPrize / amount;
    }

    private static long calculateTotalPrize(EnumMap<Rank, Integer> ranks) {
        return ranks.entrySet().stream()
                .mapToLong(rank -> rank.getKey().getPrize() * rank.getValue())
                .sum();
    }

    private static EnumMap<Rank, Integer> initRanks() {
        EnumMap<Rank, Integer> ranks = new EnumMap<>(Rank.class);
        Arrays.stream(Rank.values()).forEach(rank -> ranks.put(rank, 0));
        return ranks;
    }
}
