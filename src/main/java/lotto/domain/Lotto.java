package lotto.domain;

import lotto.exception.ErrorMessage;
import lotto.exception.LottoException;

import java.util.List;

import static lotto.domain.LottoRules.*;

public record Lotto(List<Integer> numbers) {
    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = List.copyOf(numbers);
    }

    public boolean contains(int number) {
        return numbers.contains(number);
    }

    private void validate(List<Integer> numbers) {
        if (hasInvalidCount(numbers)) {
            throw new LottoException(ErrorMessage.LOTTO_INVALID_NUMBER_COUNT.getMessage());
        }
        if (hasDuplicate(numbers)) {
            throw new LottoException(ErrorMessage.LOTTO_DUPLICATE_NUMBERS.getMessage());
        }
        if (hasInvalidValue(numbers)) {
            throw new LottoException(ErrorMessage.LOTTO_INVALID_NUMBER_VALUE.getMessage());
        }
    }
}
