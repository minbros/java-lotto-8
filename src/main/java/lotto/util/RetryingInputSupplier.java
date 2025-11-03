package lotto.util;

import java.util.function.Supplier;

public final class RetryingInputSupplier {
    public static <T> T get(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private RetryingInputSupplier() {
    }
}
