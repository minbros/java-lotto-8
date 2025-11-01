package lotto.domain;

import lotto.exception.ErrorMessage;
import lotto.exception.LottoException;

import java.util.HashSet;
import java.util.List;

public class Lotto {
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
    }
}
