package lotto.domain;

public record WinningNumber(Lotto numbers, int bonusNumber) {
    public WinningNumber(Lotto numbers, int bonusNumber) {
        this.numbers = numbers;
        this.bonusNumber = bonusNumber;
    }
}
