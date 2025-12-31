package leandrosilva.wallet.domain.repository;

import java.util.Optional;
import java.util.UUID;

import leandrosilva.wallet.domain.model.User;

public interface UserRepository extends BaseRepository<User, UUID> {
    Optional<User> findByDocumentNumber(String documentNumber);

    boolean existsByDocumentNumber(String documentNumber);

    boolean existsByEmail(String email);
}
