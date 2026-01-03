package leandrosilva.wallet.application.commands.createaccount;

import java.time.Instant;
import java.util.UUID;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import leandrosilva.wallet.application.dto.AccountDto;
import leandrosilva.wallet.domain.model.Account;
import leandrosilva.wallet.domain.model.EAccountStatus;
import leandrosilva.wallet.domain.repository.AccountRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateAccountCommandHandler implements CreateAccountHandler {
    private final AccountRepository repository;

    @Override
    @Transactional
    public AccountDto handle(CreateAccountCommand command) {
        Account account = new Account(
                UUID.randomUUID(), command.userId(), command.balance(), command.currency(),
                EAccountStatus.ACTIVE, Instant.now(), null);

        Account saved = repository.save(account);

        return AccountDto.from(saved);
    }
}
