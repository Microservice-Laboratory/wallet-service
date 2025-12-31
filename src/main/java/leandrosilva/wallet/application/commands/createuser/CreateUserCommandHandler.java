package leandrosilva.wallet.application.commands.createuser;

import java.time.Instant;
import java.util.UUID;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import leandrosilva.wallet.application.dto.UserDto;
import leandrosilva.wallet.domain.exception.DomainException;
import leandrosilva.wallet.domain.model.EUserStatus;
import leandrosilva.wallet.domain.repository.UserRepository;
import leandrosilva.wallet.domain.model.User;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateUserCommandHandler implements CreateUserHandler {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDto handle(CreateUserCommand command) {

        if (userRepository.existsByDocumentNumber(command.documentNumber())) {
            throw new DomainException("Usuário já cadastrado com este documento.");
        }
        if (userRepository.existsByEmail(command.email())) {
            throw new DomainException("E-mail já utilizado por outro usuário.");
        }

        var newUser = new User(
                UUID.randomUUID(),
                command.fullName(),
                command.documentNumber(),
                command.email(),
                EUserStatus.ACTIVE,
                Instant.now());

        var savedUser = userRepository.save(newUser);

        return UserDto.from(savedUser);
    }
}