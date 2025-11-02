package lotto.dto;

import lotto.domain.Rank;

import java.util.Map;

public record LottoResult(Map<Rank, Integer> ranks, double returnRate) {
}
