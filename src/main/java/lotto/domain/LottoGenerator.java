package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.List;

import static lotto.domain.LottoRules.*;

public final class LottoGenerator {
    public static Lotto generate() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(MINIMUM_NUMBER, MAXIMUM_NUMBER, NUMBER_COUNT);
        return new Lotto(numbers);
    }

    private LottoGenerator() {
    }
}
