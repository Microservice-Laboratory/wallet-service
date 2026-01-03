package leandrosilva.wallet.infrastructure.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import leandrosilva.wallet.domain.model.Account;
import leandrosilva.wallet.infrastructure.persistence.entity.AccountEntity;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    @Mapping(source = "userId", target = "user.id")
    AccountEntity toEntity(Account domain);

    @Mapping(source = "user.id", target = "userId")
    Account toDomain(AccountEntity entity);
}
