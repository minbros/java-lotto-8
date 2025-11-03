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
    private static final String PROMPT_FOR_SEPARATOR = "당첨 통계" + LINE_SEPARATOR + "-".repeat(50);
    private static final String PROMPT_FOR_COUNT = "개를 구매했습니다.";
    private static final NumberFormat NUMBER_FORMAT = NumberFormat.getInstance();

    public void printLottoList(List<Lotto> lottoList) {
        int count = lottoList.size();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getLottoCountMessage(count)).append(LINE_SEPARATOR);
        lottoList.forEach(lotto -> stringBuilder.append(lotto.getNumbers()).append(LINE_SEPARATOR));
        System.out.println(stringBuilder);
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
                .sorted(Comparator.comparingLong(e -> e.getKey().getPrize()))
                .map(entry -> formatWinningMessage(entry.getKey(), entry.getValue()))
                .collect(Collectors.joining(LINE_SEPARATOR));
    }

    private static String getReturnRateMessage(LottoResult result) {
        return String.format("총 수익률은 %.1f%%입니다.", result.returnRate() * 100);
    }

    private static String formatWinningMessage(Rank rank, int count) {
        String bonusMessage = "";
        if (rank.hasBonus()) {
            bonusMessage = ", 보너스 볼 일치";
        }

        return String.format("%d개 일치%s (%s원) - %d개",
                rank.getMatchCount(),
                bonusMessage,
                NUMBER_FORMAT.format(rank.getPrize()),
                count
        );
    }
}
