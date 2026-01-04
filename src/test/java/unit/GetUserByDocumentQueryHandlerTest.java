package unit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import leandrosilva.wallet.application.dto.UserDto;
import leandrosilva.wallet.application.queries.getuserbydocument.GetUserByDocumentQuery;
import leandrosilva.wallet.application.queries.getuserbydocument.GetUserByDocumentQueryHandler;
import leandrosilva.wallet.domain.exception.ResourceNotFoundException;
import leandrosilva.wallet.domain.model.EUserStatus;
import leandrosilva.wallet.domain.model.User;
import leandrosilva.wallet.domain.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class GetUserByDocumentQueryHandlerTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private GetUserByDocumentQueryHandler handler;

    @Test
    void shouldReturnUserWhenDocumentExists() {
        // Arrange
        String document = "00000000000000";
        User mockUser = new User(UUID.randomUUID(), "Teste", document, "teste@teste.com", EUserStatus.ACTIVE,
                Instant.now());

        when(userRepository.findByDocumentNumber(document)).thenReturn(Optional.of(mockUser));
        var query = new GetUserByDocumentQuery(document);

        // Act
        UserDto result = handler.handle(query);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.documentNumber()).isEqualTo(document);
        assertThat(result.fullName()).isEqualTo("Teste");

        verify(userRepository, times(1)).findByDocumentNumber(document);
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        // Arrange
        String document = "00000000000000";
        var query = new GetUserByDocumentQuery(document);
        when(userRepository.findByDocumentNumber(document)).thenReturn(Optional.empty());

        // Act
        var ast = assertThatThrownBy(() -> handler.handle(query));

        // Assert
        ast.isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Usuário não encontrado com o documento: " + document);

        verify(userRepository, times(1)).findByDocumentNumber(document);
    }
}
