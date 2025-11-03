package lotto.dto;

import lotto.domain.Rank;

import java.util.Map;

/**
 * @param ranks 순위 별 당첨 개수
 * @param returnRate 수익률 (0 ~ 1의 값)
 */
public record LottoResult(Map<Rank, Integer> ranks, double returnRate) {
}
