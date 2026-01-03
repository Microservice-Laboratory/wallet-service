package leandrosilva.wallet.application.commands.createaccount;

import leandrosilva.wallet.application.dto.AccountDto;

public interface CreateAccountHandler {
    AccountDto handle(CreateAccountCommand command);
}
