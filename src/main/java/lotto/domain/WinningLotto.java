package lotto.domain;

import lotto.exception.ErrorMessage;
import lotto.exception.LottoException;

import java.util.List;

import static lotto.domain.LottoRules.isOutOfRange;

/**
 * @param lotto       당첨 번호
 * @param bonusNumber 보너스 번호
 */
public record WinningLotto(Lotto lotto, int bonusNumber) {
    /**
     * @throws LottoException <ul>
     *                        <li>당첨 번호가 {@value LottoRules#NUMBER_COUNT}개가 아닌 경우</li>
     *                        <li>당첨 번호와 보너스 번호 중 중복되는 번호가 있을 경우</li>
     *                        <li>{@value LottoRules#MINIMUM_NUMBER}부터 {@value LottoRules#MAXIMUM_NUMBER}까지의 값이 아닌 번호가 있을 경우</li>
     *                        </ul>
     * @see Lotto#Lotto(List)
     */
    public WinningLotto(List<Integer> numbers, int bonusNumber) {
        this(new Lotto(numbers), bonusNumber);
    }

    /**
     * @throws LottoException <ul>
     *                        <li>보너스 번호가 {@value LottoRules#MINIMUM_NUMBER}부터 {@value LottoRules#MAXIMUM_NUMBER}까지의 값이 아닌 경우</li>
     *                        <li>보너스 번호가 당첨 번호와 중복될 경우</li>
     *                        </ul>
     */
    public WinningLotto {
        validateBonusNumber(lotto, bonusNumber);
    }

    public int getMatchCount(Lotto otherLotto) {
        return (int) lotto.getNumbers().stream()
                .filter(otherLotto::contains)
                .count();
    }

    public boolean hasBonus(Lotto otherLotto) {
        return otherLotto.contains(bonusNumber);
    }

    private static void validateBonusNumber(Lotto lotto, int bonusNumber) {
        if (isOutOfRange(bonusNumber)) {
            throw new LottoException(ErrorMessage.BONUS_INVALID_NUMBER_VALUE.getMessage());
        }
        if (lotto.contains(bonusNumber)) {
            throw new LottoException(ErrorMessage.BONUS_DUPLICATE_NUMBERS.getMessage());
        }
    }
}
