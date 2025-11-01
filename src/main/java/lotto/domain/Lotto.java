package lotto.domain;

import lotto.exception.ErrorMessage;
import lotto.exception.LottoException;

import java.util.HashSet;
import java.util.List;

import static lotto.domain.LottoRules.*;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = List.copyOf(numbers);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new LottoException(ErrorMessage.LOTTO_INVALID_NUMBER_COUNT.getMessage());
        }
        if (numbers.size() != new HashSet<>(numbers).size()) {
            throw new LottoException(ErrorMessage.LOTTO_DUPLICATE_NUMBERS.getMessage());
        }
        if (numbers.stream().anyMatch(num -> num < MINIMUM_NUMBER || num > MAXIMUM_NUMBER)) {
            throw new LottoException(ErrorMessage.LOTTO_INVALID_NUMBER_VALUE.getMessage());
        }
    }
}
