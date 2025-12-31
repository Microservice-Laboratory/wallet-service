package leandrosilva.wallet.infrastructure.persistence.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import leandrosilva.wallet.infrastructure.persistence.entity.AccountEntity;

@Repository
public interface JpaAccountRepository extends JpaRepository<AccountEntity, UUID> {
    List<AccountEntity> findAllByUserId(UUID userId);

    boolean existsByUserIdAndCurrency(UUID userId, String currency);
}
