package leandrosilva.wallet.infrastructure.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.transaction.Transactional;
import leandrosilva.wallet.domain.repository.BaseRepository;

public abstract class BaseRepositoryImpl<T, I, E> implements BaseRepository<T, I> {
    protected final JpaRepository<E, I> jpaRepository;

    protected BaseRepositoryImpl(JpaRepository<E, I> jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    protected abstract T toDomain(E entity);

    protected abstract E toEntity(T domain);

    @Override
    public Optional<T> findById(I id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    @Transactional
    public T save(T domainObject) {
        E entity = toEntity(domainObject);
        E savedEntity = jpaRepository.save(entity);

        return toDomain(savedEntity);
    }
}
