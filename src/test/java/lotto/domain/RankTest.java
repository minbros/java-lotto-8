package lotto.domain;

import lotto.exception.ErrorMessage;
import lotto.exception.RankException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static lotto.domain.LottoRules.LOTTO_NUMBER_COUNT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @ParameterizedTest
    @ValueSource(ints = {-1, LOTTO_NUMBER_COUNT + 1})
    void 잘못된_매칭_개수를_전달하면_예외가_발생한다(int matchCount) {
        assertThatThrownBy(() -> Rank.of(matchCount, false))
                .isInstanceOf(RankException.class)
                .hasMessageContaining(ErrorMessage.RANK_INVALID_MATCH_COUNT.getMessage());
    }
}
