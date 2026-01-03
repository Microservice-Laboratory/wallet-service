package leandrosilva.wallet.infrastructure.api.dto;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import leandrosilva.wallet.domain.model.ECurrency;

public record CreateAccountRequest(
        @NotNull UUID userId,
        @NotNull ECurrency currency,
        @NotNull @PositiveOrZero BigDecimal balance) {
}
