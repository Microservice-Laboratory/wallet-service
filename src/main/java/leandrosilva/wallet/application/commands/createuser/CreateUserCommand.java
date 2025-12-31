package leandrosilva.wallet.application.commands.createuser;

public record CreateUserCommand(String fullName, String documentNumber, String email) {
}
