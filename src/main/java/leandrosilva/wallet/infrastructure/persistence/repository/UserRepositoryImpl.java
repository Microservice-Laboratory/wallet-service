package leandrosilva.wallet.infrastructure.persistence.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import leandrosilva.wallet.domain.model.User;
import leandrosilva.wallet.domain.repository.UserRepository;
import leandrosilva.wallet.infrastructure.persistence.entity.UserEntity;
import leandrosilva.wallet.infrastructure.persistence.mapper.UserMapper;

@Component
public class UserRepositoryImpl extends BaseRepositoryImpl<User, UUID, UserEntity> implements UserRepository {

    private final JpaUserRepository jpaUserRepository;
    private final UserMapper mapper;

    public UserRepositoryImpl(JpaUserRepository jpaRepository, UserMapper mapper) {
        super(jpaRepository);
        this.jpaUserRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    protected User toDomain(UserEntity entity) {
        return mapper.toDomain(entity);
    }

    @Override
    protected UserEntity toEntity(User domain) {
        return mapper.toEntity(domain);
    }

    @Override
    public Optional<User> findByDocumentNumber(String documentNumber) {
        return jpaUserRepository.findByDocumentNumber(documentNumber).map(this::toDomain);
    }

    @Override
    public boolean existsByDocumentNumber(String documentNumber) {
        return jpaUserRepository.existsByDocumentNumber(documentNumber);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaUserRepository.existsByEmail(email);
    }
}
