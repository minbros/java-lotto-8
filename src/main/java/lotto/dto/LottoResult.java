package lotto.dto;

import lotto.domain.Rank;

import java.util.EnumMap;

public record LottoResult(EnumMap<Rank, Integer> ranks, double returnRate) {
}
