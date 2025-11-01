package lotto.dto;

import lotto.domain.Lotto;
import lotto.domain.WinningLotto;

import java.util.List;

public record LottoData(List<Lotto> lottoList, WinningLotto winningLotto) {
}
