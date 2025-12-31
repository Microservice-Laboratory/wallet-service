package leandrosilva.wallet.domain.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import leandrosilva.wallet.domain.exception.DomainException;
import lombok.Getter;

@Getter
public class Account {
    private final UUID id;
    private final UUID userId;
    private BigDecimal balance;
    private final String currency;
    private EAccountStatus status;
    private final Instant createdAt;
    private final Long version;

    public Account(UUID id, UUID userId, BigDecimal balance, String currency, EAccountStatus status, Instant createdAt,
            Long version) {
        this.id = id;
        this.userId = userId;
        this.balance = balance;
        this.currency = currency;
        this.status = status;
        this.createdAt = createdAt;
        this.version = version;
    }

    private void validateAccountActive() {
        if (this.status != EAccountStatus.ACTIVE) {
            throw new DomainException("Operação não permitida: conta está " + this.status);
        }
    }

    public void withdraw(BigDecimal amount) {
        validateAccountActive();

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new DomainException("O valor do saque deve ser positivo");
        }
        if (this.balance.compareTo(amount) < 0) {
            throw new DomainException("Saldo insuficiente");
        }

        this.balance = this.balance.subtract(amount);
    }

    public void deposit(BigDecimal amount) {
        validateAccountActive();

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new DomainException("O valor do depósito deve ser positivo");
        }

        this.balance = this.balance.add(amount);
    }

    public void block() {
        this.status = EAccountStatus.BLOCKED;
    }
}
