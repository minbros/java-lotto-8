package lotto.view;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class FakeInputView extends InputView {
    private final Queue<String> inputs = new ArrayDeque<>();

    public FakeInputView(String... inputs) {
        Arrays.stream(inputs).forEach(this.inputs::offer);
    }

    @Override
    public String readAmount() {
        return inputs.poll();
    }

    @Override
    public String readWinningNumbers() {
        return inputs.poll();
    }

    @Override
    public String readBonusNumber() {
        return inputs.poll();
    }
}
