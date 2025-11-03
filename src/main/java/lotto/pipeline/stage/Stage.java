package lotto.pipeline.stage;

/**
 * 파이프라인의 stage를 정의하는 함수형 인터페이스입니다.
 *
 * @param <I> 입력값의 자료형
 * @param <O> 반환값의 자료형
 */
@FunctionalInterface
public interface Stage<I, O> {
    O execute(I input);
}
