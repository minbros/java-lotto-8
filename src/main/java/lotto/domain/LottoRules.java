package lotto.domain;

import java.util.HashSet;
import java.util.List;

public final class LottoRules {
    public static final int NUMBER_COUNT = 6;
    public static final int MINIMUM_NUMBER = 1;
    public static final int MAXIMUM_NUMBER = 45;

    public static boolean hasInvalidCount(List<Integer> numbers) {
        return numbers.size() != NUMBER_COUNT;
    }

    public static boolean hasDuplicate(List<Integer> numbers) {
        return numbers.size() != new HashSet<>(numbers).size();
    }

    public static boolean hasInvalidValue(List<Integer> numbers) {
        return numbers.stream().anyMatch(LottoRules::isOutOfRange);
    }

    public static boolean isOutOfRange(int number) {
        return number < MINIMUM_NUMBER || number > MAXIMUM_NUMBER;
    }

    private LottoRules() {
    }
}
