package lotto.exception;

/**
 * {@link lotto.domain.Rank} 생성 시 유효하지 않은 값이 입력되면 발생하는 예외 클래스입니다.
 */
public class RankException extends IllegalArgumentException {
    public RankException(String message) {
        super(message);
    }
}
