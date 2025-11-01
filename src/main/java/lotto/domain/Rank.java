package lotto.domain;

import lotto.exception.ErrorMessage;
import lotto.exception.RankException;

import java.util.Arrays;

import static lotto.domain.LottoRules.*;

public enum Rank {
    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FOURTH(4, false, 50_000),
    FIFTH(3, false, 5_000),
    NONE(0, false, 0);

    private final int matchCount;
    private final boolean matchesBonus;
    private final int prize;

    Rank(int matchCount, boolean matchesBonus, int prize) {
        this.matchCount = matchCount;
        this.matchesBonus = matchesBonus;
        this.prize = prize;
    }

    public static Rank of(int matchCount, boolean matchesBonus) {
        validateMatchCount(matchCount);
        return Arrays.stream(values())
                .filter(rank -> rank.matches(matchCount, matchesBonus))
                .findFirst()
                .orElse(NONE);
    }

    private static void validateMatchCount(int matchCount) {
        if (matchCount < 0 || matchCount > LOTTO_NUMBER_COUNT) {
            throw new RankException(ErrorMessage.RANK_INVALID_MATCH_COUNT.getMessage());
        }
    }

    private boolean matches(int matchCount, boolean matchesBonus) {
        if (this == SECOND) {
            return this.matchCount == matchCount && matchesBonus;
        }
        return this.matchCount == matchCount;
    }
}
