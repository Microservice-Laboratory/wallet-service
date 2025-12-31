package leandrosilva.wallet.application.dto;

import java.util.UUID;

import leandrosilva.wallet.domain.model.User;

public record UserDto(UUID id, String documentNumber, String fullName, String email, String status) {

    public static UserDto from(User user) {
        return new UserDto(user.getId(), user.getDocumentNumber(), user.getFullName(), user.getEmail(),
                user.getStatus().name());
    }
}
