package lotto.domain;

import java.util.HashSet;
import java.util.List;

public final class LottoRules {
    public static final int NUMBER_COUNT = 6;
    public static final int MINIMUM_NUMBER = 1;
    public static final int MAXIMUM_NUMBER = 45;

    static boolean hasInvalidCount(List<Integer> numbers) {
        return numbers.size() != NUMBER_COUNT;
    }

    static boolean hasDuplicate(List<Integer> numbers) {
        return numbers.size() != new HashSet<>(numbers).size();
    }

    static boolean hasInvalidValue(List<Integer> numbers) {
        return numbers.stream().anyMatch(LottoRules::isOutOfRange);
    }

    static boolean isOutOfRange(int number) {
        return number < MINIMUM_NUMBER || number > MAXIMUM_NUMBER;
    }

    private LottoRules() {
    }
}
