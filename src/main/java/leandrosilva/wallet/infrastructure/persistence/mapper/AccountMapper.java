package leandrosilva.wallet.infrastructure.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import leandrosilva.wallet.domain.model.Account;
import leandrosilva.wallet.infrastructure.persistence.entity.AccountEntity;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    // Mapeia do Banco para o Domínio
    // O MapStruct entende que entity.getUser().getId() deve ir para o userId do
    // domínio
    @Mapping(source = "user.id", target = "userId")
    Account toDomain(AccountEntity entity);

    // Mapeia do Domínio para o Banco
    @Mapping(source = "userId", target = "user.id")
    @Mapping(target = "user.fullName", ignore = true) // Ignoramos os outros campos do User
    @Mapping(target = "user.documentNumber", ignore = true)
    @Mapping(target = "user.email", ignore = true)
    @Mapping(target = "user.status", ignore = true)
    @Mapping(target = "user.createdAt", ignore = true)
    AccountEntity toEntity(Account domain);
}
