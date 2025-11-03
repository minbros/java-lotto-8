package lotto.dto;

import lotto.domain.Lotto;
import lotto.domain.WinningLotto;

import java.util.List;

/**
 * @param lottoList 생성된 로또 목록
 * @param winningLotto 당첨 번호와 보너스 번호
 */
public record LottoData(List<Lotto> lottoList, WinningLotto winningLotto) {
}
