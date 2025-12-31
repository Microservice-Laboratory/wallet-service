package leandrosilva.wallet.application.queries.getuserbydocument;

import leandrosilva.wallet.application.dto.UserDto;

public interface GetUserByDocumentHandler {
    UserDto handle(GetUserByDocumentQuery query);
}
