package leandrosilva.wallet.domain.model;

import lombok.Getter;
import java.time.Instant;
import java.util.UUID;

@Getter
public class User {
    private final UUID id;
    private final String fullName;
    private final String documentNumber;
    private final String email;
    private EUserStatus status;
    private final Instant createdAt;

    public User(UUID id, String fullName, String documentNumber, String email, EUserStatus status, Instant createdAt) {
        this.id = id;
        this.fullName = fullName;
        this.documentNumber = documentNumber;
        this.email = email;
        this.status = status;
        this.createdAt = createdAt;
    }

    public boolean canOpenAccount() {
        return this.status == EUserStatus.ACTIVE;
    }

    public void block() {
        this.status = EUserStatus.BLOCKED;
    }
}
