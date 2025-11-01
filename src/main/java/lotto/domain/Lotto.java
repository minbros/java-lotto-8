package lotto.domain;

import lotto.exception.ErrorMessage;
import lotto.exception.LottoException;

import java.util.HashSet;
import java.util.List;

public class Lotto {
    public static final int SIZE = 6;
    public static final int MINIMUM_VALUE = 1;
    public static final int MAXIMUM_VALUE = 45;

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = List.copyOf(numbers);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != SIZE) {
            throw new LottoException(ErrorMessage.LOTTO_INVALID_NUMBER_COUNT.getMessage());
        }
        if (numbers.size() != new HashSet<>(numbers).size()) {
            throw new LottoException(ErrorMessage.LOTTO_DUPLICATE_NUMBERS.getMessage());
        }
        if (numbers.stream().anyMatch(num -> num < MINIMUM_VALUE || num > MAXIMUM_VALUE)) {
            throw new LottoException(ErrorMessage.LOTTO_INVALID_NUMBER_VALUE.getMessage());
        }
    }
}
