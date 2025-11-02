package lotto.pipeline;

import lotto.pipeline.stage.Stage;

import java.util.function.Function;

@SuppressWarnings("ClassCanBeRecord")
public class LottoPipeline<I, O> {
    private final Function<I, O> function;

    public LottoPipeline(Function<I, O> function) {
        this.function = function;
    }

    public static <I, O> LottoPipeline<I, O> of(Stage<I, O> stage) {
        return new LottoPipeline<>(stage::execute);
    }

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
