package lotto.pipeline;

import lotto.pipeline.stage.Stage;

import java.util.function.Function;

/**
 * 파이프라인에 이용되는 {@link Stage}를 모아놓는 클래스입니다.
 *
 * @param <I> 입력값의 자료형
 * @param <O> 반환값의 자료형
 */
@SuppressWarnings("ClassCanBeRecord")
public class LottoPipeline<I, O> {
    private final Function<I, O> function;

    private LottoPipeline(Function<I, O> function) {
        this.function = function;
    }

    /**
     * 파이프라인을 처음 생성할 때 사용하는 메서드입니다.
     *
     * @param stage 처음 추가할 스테이지
     * @see Stage
     */
    public static <I, O> LottoPipeline<I, O> of(Stage<I, O> stage) {
        return new LottoPipeline<>(stage::execute);
    }

    /**
     * 파이프라인에 스테이지를 추가할 때 사용하는 메서드입니다.
     * <p>기존 파이프라인 실행 함수에서 새로운 함수를 합성시킵니다.</p>
     *
     * @param nextStage 추가할 스테이지<br>
     *                  해당 스테이지의 입력 자료형은 기존 파이프라인의 반환 자료형과 일치해야 합니다.
     * @param <N>       nextStage의 반환 자료형
     * @see Stage
     */
    public <N> LottoPipeline<I, N> then(Stage<O, N> nextStage) {
        return new LottoPipeline<>(input -> {
            O middle = execute(input);
            return nextStage.execute(middle);
        });
    }

    public O execute(I input) {
        return function.apply(input);
    }
}
