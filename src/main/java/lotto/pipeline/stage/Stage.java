package lotto.pipeline.stage;

@FunctionalInterface
public interface Stage<I, O> {
    O execute(I input);
}
