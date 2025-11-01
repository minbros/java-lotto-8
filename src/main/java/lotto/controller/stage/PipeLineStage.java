package lotto.controller.stage;

@FunctionalInterface
public interface PipeLineStage<I, O> {
    O execute(I input);
}
