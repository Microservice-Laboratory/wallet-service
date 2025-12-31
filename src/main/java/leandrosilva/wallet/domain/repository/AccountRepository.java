package leandrosilva.wallet.domain.repository;

import java.util.List;
import java.util.UUID;

import leandrosilva.wallet.domain.model.Account;

public interface AccountRepository extends BaseRepository<Account, UUID> {
    List<Account> findAllByUserId(UUID userId);

    boolean existsByUserIdAndCurrency(UUID userId, String currency);
}
