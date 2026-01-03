package leandrosilva.wallet.application.commands.createaccount;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import leandrosilva.wallet.application.dto.AccountDto;
import leandrosilva.wallet.domain.exception.DomainException;
import leandrosilva.wallet.domain.exception.ResourceNotFoundException;
import leandrosilva.wallet.domain.model.Account;
import leandrosilva.wallet.domain.model.EAccountStatus;
import leandrosilva.wallet.domain.model.EUserStatus;
import leandrosilva.wallet.domain.model.User;
import leandrosilva.wallet.domain.repository.AccountRepository;
import leandrosilva.wallet.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateAccountCommandHandler implements CreateAccountHandler {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public AccountDto handle(CreateAccountCommand command) {
        this.checkUserIsActive(command);
        this.checkAccountAlreadyExist(command);

        Account account = new Account(
                UUID.randomUUID(), command.userId(), command.balance(), command.currency(),
                EAccountStatus.ACTIVE, Instant.now(), null);

        Account saved = accountRepository.save(account);

        return AccountDto.from(saved);
    }

    private void checkUserIsActive(CreateAccountCommand command) {
        User user = userRepository.findById(command.userId()).orElseThrow(
                () -> new ResourceNotFoundException("Usuário não encontrado com o id: " + command.userId()));

        if (user.getStatus() != EUserStatus.ACTIVE) {
            throw new DomainException("Status do usuário não permite criar uma conta");
        }
    }

    private void checkAccountAlreadyExist(CreateAccountCommand command) {
        List<Account> accounts = accountRepository.findAllByUserId(command.userId());

        boolean alreadyExists = accounts.stream().anyMatch(
                account -> account.getCurrency() == command.currency() && account.getStatus() == EAccountStatus.ACTIVE);

        if (alreadyExists) {
            throw new DomainException("Usuário já tem uma conta ativa para a moeda " + command.currency());
        }
    }
}
