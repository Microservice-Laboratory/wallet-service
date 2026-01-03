package leandrosilva.wallet.application.queries.getaccountbyid;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import leandrosilva.wallet.application.dto.AccountDto;
import leandrosilva.wallet.domain.exception.ResourceNotFoundException;
import leandrosilva.wallet.domain.repository.AccountRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetAccountByIdQueryHandler implements GetAccountByIdHandler {

    private final AccountRepository accountRepository;

    @Override
    public AccountDto handle(GetAccountByIdQuery query) {
        var account = accountRepository.findById(query.id())
                .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada com o id: " + query.id()));

        return AccountDto.from(account);
    }
}