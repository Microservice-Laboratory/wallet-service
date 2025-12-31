package leandrosilva.wallet.infrastructure.persistence.mapper;

import org.mapstruct.Mapper;

import leandrosilva.wallet.domain.model.User;
import leandrosilva.wallet.infrastructure.persistence.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toDomain(UserEntity entity);

    UserEntity toEntity(User domain);
}
