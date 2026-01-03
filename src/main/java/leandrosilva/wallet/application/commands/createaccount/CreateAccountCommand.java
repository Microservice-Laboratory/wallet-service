package leandrosilva.wallet.application.commands.createaccount;

import java.math.BigDecimal;
import java.util.UUID;

import leandrosilva.wallet.domain.model.ECurrency;

public record CreateAccountCommand(
        UUID userId,
        ECurrency currency,
        BigDecimal balance) {
}
