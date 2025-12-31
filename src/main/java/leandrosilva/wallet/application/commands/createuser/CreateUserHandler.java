package leandrosilva.wallet.application.commands.createuser;

import leandrosilva.wallet.application.dto.UserDto;

public interface CreateUserHandler {
    UserDto handle(CreateUserCommand command);
}
