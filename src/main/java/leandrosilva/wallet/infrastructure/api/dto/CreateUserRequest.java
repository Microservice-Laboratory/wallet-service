package leandrosilva.wallet.infrastructure.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import leandrosilva.wallet.application.commands.createuser.CreateUserCommand;

public record CreateUserRequest(
        @NotBlank(message = "O nome é obrigatório") String fullName,

        @NotBlank String documentNumber,

        @NotBlank @Email(message = "Formato de e-mail inválido") String email) {
    public CreateUserCommand toCommand() {
        return new CreateUserCommand(fullName, documentNumber, email);
    }
}