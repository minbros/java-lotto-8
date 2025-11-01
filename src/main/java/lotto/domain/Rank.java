package lotto.domain;

import java.util.Arrays;

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
        return Arrays.stream(values())
                .filter(rank -> rank.matches(matchCount, matchesBonus))
                .findFirst()
                .orElse(NONE);
    }

    private boolean matches(int matchCount, boolean matchesBonus) {
        if (this == SECOND) {
            return this.matchCount == matchCount && matchesBonus;
        }
        return this.matchCount == matchCount;
    }
}
