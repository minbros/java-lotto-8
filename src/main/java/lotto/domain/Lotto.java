package lotto.domain;

import lotto.exception.ErrorMessage;
import lotto.exception.LottoException;

import java.util.HashSet;
import java.util.List;

public class Lotto {
    private static final int LOTTO_MINIMUM = 1;
    private static final int LOTTO_MAXIMUM = 45;

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new LottoException(ErrorMessage.LOTTO_INVALID_NUMBER_COUNT.getMessage());
        }
        if (numbers.size() != new HashSet<>(numbers).size()) {
            throw new LottoException(ErrorMessage.LOTTO_DUPLICATE_NUMBERS.getMessage());
        }
        if (numbers.stream().anyMatch(num -> num < LOTTO_MINIMUM || num > LOTTO_MAXIMUM)) {
            throw new LottoException(ErrorMessage.LOTTO_INVALID_NUMBER_VALUE.getMessage());
        }
    }
}
