package leandrosilva.wallet.application.queries.getuserbydocument;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import leandrosilva.wallet.application.dto.UserDto;
import leandrosilva.wallet.domain.exception.ResourceNotFoundException;
import leandrosilva.wallet.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetUserByDocumentQueryHandler implements GetUserByDocumentHandler {

    private final UserRepository userRepository;

    @Override
    public UserDto handle(GetUserByDocumentQuery query) {
        var user = userRepository.findByDocumentNumber(query.documentNumber())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Usuário não encontrado com o documento: " + query.documentNumber()));

        return UserDto.from(user);
    }
}
