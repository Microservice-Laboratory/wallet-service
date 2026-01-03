package leandrosilva.wallet.application.queries.getaccountbyid;

import leandrosilva.wallet.application.dto.AccountDto;

public interface GetAccountByIdHandler {
    AccountDto handle(GetAccountByIdQuery query);
}
