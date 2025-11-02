package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.Rank;
import lotto.dto.LottoResult;

import java.text.NumberFormat;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String PROMPT_FOR_SEPARATOR = "당첨 통계" + LINE_SEPARATOR + "--------------";
    private static final String PROMPT_FOR_COUNT = "개를 구매했습니다.";

    public void printLottoList(List<Lotto> lottoList) {
        int count = lottoList.size();
        System.out.println(getLottoCountMessage(count));
        lottoList.forEach(lotto -> System.out.println(lotto.getNumbers()));
        System.out.println();
    }

    public void printResult(LottoResult result) {
        String outputMessage = PROMPT_FOR_SEPARATOR + LINE_SEPARATOR +
                getWinningMessages(result) + LINE_SEPARATOR +
                getReturnRateMessage(result);
        System.out.println(outputMessage);
    }

    private static String getLottoCountMessage(int count) {
        return count + PROMPT_FOR_COUNT;
    }

    private static String getWinningMessages(LottoResult result) {
        return result.ranks().entrySet().stream()
                .filter(entry -> entry.getKey() != Rank.NONE)
                .sorted(Comparator.comparingInt(e -> e.getKey().getMatchCount()))
                .map(entry -> formatWinningMessage(entry.getKey(), entry.getValue()))
                .collect(Collectors.joining(LINE_SEPARATOR));
    }

    private static String getReturnRateMessage(LottoResult result) {
        return String.format("총 수익률은 %.2f입니다.%n", result.returnRate());
    }

    private static String formatWinningMessage(Rank rank, int count) {
        String bonusMessage = "";
        if (rank.hasBonus()) {
            bonusMessage = ", 보너스 볼 일치";
        }

        return String.format("%d개 일치%s (%s원) - %d개%n",
                rank.getMatchCount(),
                bonusMessage,
                NumberFormat.getInstance().format(rank.getPrize()),
                count
        );
    }
}
