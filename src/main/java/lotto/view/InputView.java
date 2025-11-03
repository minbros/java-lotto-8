package lotto.view;

import camp.nextstep.edu.missionutils.Console;

/**
 * 입력을 담당하는 클래스입니다.
 */
public class InputView {
    private static final String PROMPT_FOR_PURCHASE = "구매금액을 입력해 주세요.";
    private static final String PROMPT_FOR_WINNING_NUMBERS = "당첨 번호를 입력해 주세요.";
    private static final String PROMPT_FOR_BONUS_NUMBER = "보너스 번호를 입력해 주세요.";

    public String readAmount() {
        return readWithPrompt(PROMPT_FOR_PURCHASE);
    }

    public String readWinningNumbers() {
        return readWithPrompt(PROMPT_FOR_WINNING_NUMBERS);
    }

    public String readBonusNumber() {
        return readWithPrompt(PROMPT_FOR_BONUS_NUMBER);
    }

    private String readWithPrompt(String prompt) {
        System.out.println(prompt);
        String input = Console.readLine();
        System.out.println();
        return input;
    }
}
