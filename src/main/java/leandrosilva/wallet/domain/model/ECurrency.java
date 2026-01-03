package leandrosilva.wallet.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ECurrency {
    BRL("Real Brasileiro", "R$"),
    USD("Dólar Americano", "US$");

    private final String description;
    private final String symbol;
}
