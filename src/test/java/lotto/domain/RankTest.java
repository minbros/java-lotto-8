package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {
    @Test
    void 생성자가_정상적으로_동작하는지_확인한다() {
        Rank first = Rank.of(6, false);
        Rank second = Rank.of(5, true);
        Rank third = Rank.of(5, false);
        Rank none = Rank.of(2, true);
        List<Rank> ranks = List.of(first, second, third, none);

        assertThat(ranks).containsExactly(Rank.FIRST, Rank.SECOND, Rank.THIRD, Rank.NONE);
    }
}
