package lotto.domain;

import lotto.exception.ErrorMessage;
import lotto.exception.LottoException;

import java.util.List;

import static lotto.domain.LottoRules.*;

/**
 * 기본적으로 제공되는 로또 클래스입니다.
 */
@SuppressWarnings("ClassCanBeRecord")
public class Lotto {
    private final List<Integer> numbers;

    /**
     * 입력한 번호들은 오름차순으로 정렬됩니다.
     *
     * @throws LottoException <ul>
     *                        <li>번호가 {@value LottoRules#NUMBER_COUNT}개가 아닌 경우</li>
     *                        <li>중복되는 번호가 있을 경우</li>
     *                        <li>{@value LottoRules#MINIMUM_NUMBER}부터 {@value LottoRules#MAXIMUM_NUMBER}까지의 값이 아닌 번호가 있을 경우</li>
     *                        </ul>
     */
    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers.stream().sorted().toList();
    }

    public boolean contains(int number) {
        return numbers.contains(number);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    private static void validate(List<Integer> numbers) {
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
