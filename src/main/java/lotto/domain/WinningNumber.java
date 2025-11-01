package lotto.domain;

import lotto.exception.ErrorMessage;
import lotto.exception.LottoException;

import java.util.List;

import static lotto.domain.LottoRules.isOutOfRange;

public record WinningNumber(Lotto lotto, int bonusNumber) {
    public WinningNumber(List<Integer> numbers, int bonusNumber) {
        this(new Lotto(numbers), bonusNumber);
    }

    public WinningNumber {
        validateBonusNumber(lotto, bonusNumber);
    }

    public int getMatchCount(Lotto otherLotto) {
        return (int) lotto.numbers().stream()
                .filter(otherLotto::contains)
                .count();
    }

    public boolean matchesBonus(Lotto otherLotto) {
        return otherLotto.contains(bonusNumber);
    }

    private void validateBonusNumber(Lotto lotto, int bonusNumber) {
        if (isOutOfRange(bonusNumber)) {
            throw new LottoException(ErrorMessage.BONUS_INVALID_NUMBER_VALUE.getMessage());
        }
        if (lotto.contains(bonusNumber)) {
            throw new LottoException(ErrorMessage.BONUS_DUPLICATE_NUMBERS.getMessage());
        }
    }
}
