package leandrosilva.wallet.application.dto;

import java.math.BigDecimal;
import java.util.UUID;

import leandrosilva.wallet.domain.model.Account;
import leandrosilva.wallet.domain.model.EAccountStatus;
import leandrosilva.wallet.domain.model.ECurrency;

public record AccountDto(UUID id, UUID userId, BigDecimal balance, ECurrency currency, EAccountStatus status) {
    public static AccountDto from(Account account) {
        return new AccountDto(account.getId(), account.getUserId(), account.getBalance(), account.getCurrency(),
                account.getStatus());
    }
}
