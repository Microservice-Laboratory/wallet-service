package leandrosilva.wallet.domain.repository;

import java.util.Optional;

public interface BaseRepository<T, I> {
    Optional<T> findById(I id);

    T save(T entity);
}