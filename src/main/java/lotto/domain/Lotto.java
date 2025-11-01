package lotto.domain;

import lotto.exception.ErrorMessage;
import lotto.exception.LottoException;

import java.util.HashSet;
import java.util.List;

public class Lotto {
    private static final int LOTTO_SIZE = 6;
    private static final int LOTTO_MINIMUM_VALUE = 1;
    private static final int LOTTO_MAXIMUM_VALUE = 45;

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = List.copyOf(numbers);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new LottoException(ErrorMessage.LOTTO_INVALID_NUMBER_COUNT.getMessage());
        }
        if (numbers.size() != new HashSet<>(numbers).size()) {
            throw new LottoException(ErrorMessage.LOTTO_DUPLICATE_NUMBERS.getMessage());
        }
        if (numbers.stream().anyMatch(num -> num < LOTTO_MINIMUM_VALUE || num > LOTTO_MAXIMUM_VALUE)) {
            throw new LottoException(ErrorMessage.LOTTO_INVALID_NUMBER_VALUE.getMessage());
        }
    }
}
