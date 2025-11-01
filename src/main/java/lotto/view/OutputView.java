package lotto.view;

import lotto.domain.Lotto;

import java.util.List;

public class OutputView {
    private static final String PROMPT_FOR_COUNT = "개를 구매했습니다.";

    public void printLottoList(List<Lotto> lottoList) {
        int count = lottoList.size();
        System.out.println(getLottoCountMessage(count));
        lottoList.forEach(lotto -> System.out.println(lotto.getNumbers()));
        System.out.println();
    }

    private static String getLottoCountMessage(int count) {
        return count + PROMPT_FOR_COUNT;
    }
}
