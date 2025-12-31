package leandrosilva.wallet.infrastructure.persistence.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import leandrosilva.wallet.domain.model.Account;
import leandrosilva.wallet.domain.repository.AccountRepository;
import leandrosilva.wallet.infrastructure.persistence.entity.AccountEntity;
import leandrosilva.wallet.infrastructure.persistence.mapper.AccountMapper;

@Component
public class AccountRepositoryImpl extends BaseRepositoryImpl<Account, UUID, AccountEntity>
        implements AccountRepository {

    private final JpaAccountRepository jpaAccountRepository;
    private final AccountMapper mapper;

    public AccountRepositoryImpl(JpaAccountRepository jpaRepository, AccountMapper mapper) {
        super(jpaRepository);
        this.jpaAccountRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    protected Account toDomain(AccountEntity entity) {
        return mapper.toDomain(entity);
    }

    @Override
    protected AccountEntity toEntity(Account domain) {
        return mapper.toEntity(domain);
    }

    @Override
    public List<Account> findAllByUserId(UUID userId) {
        return jpaAccountRepository.findAllByUserId(userId)
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public boolean existsByUserIdAndCurrency(UUID userId, String currency) {
        return jpaAccountRepository.existsByUserIdAndCurrency(userId, currency);
    }
}
