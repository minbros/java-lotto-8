package lotto.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String PROMPT_FOR_PURCHASE = "구매금액을 입력해 주세요.";
    private static final String PROMPT_FOR_WINNING_NUMBERS = "당첨 번호를 입력해 주세요.";
    private static final String PROMPT_FOR_BONUS_NUMBER = "보너스 번호를 입력해 주세요.";

    public String readAmount() {
        System.out.println(PROMPT_FOR_PURCHASE);
        return Console.readLine();
    }

    public String readWinningNumbers() {
        System.out.println(PROMPT_FOR_WINNING_NUMBERS);
        return Console.readLine();
    }

    public String readBonusNumber() {
        System.out.println(PROMPT_FOR_BONUS_NUMBER);
        return Console.readLine();
    }
}
