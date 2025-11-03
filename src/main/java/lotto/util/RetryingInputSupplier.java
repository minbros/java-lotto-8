package lotto.util;

import java.util.function.Supplier;

/**
 * 사용자 입력을 받는 과정에서 에러 발생 시 에러 메시지를 출력하고,
 * 정상적인 입력을 받을 때까지 입력을 반복하는 클래스입니다.
 */
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
