package leandrosilva.wallet.infrastructure.api.v1;

import java.net.URI;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;
import leandrosilva.wallet.application.commands.createaccount.CreateAccountCommand;
import leandrosilva.wallet.application.commands.createaccount.CreateAccountHandler;
import leandrosilva.wallet.application.dto.AccountDto;
import leandrosilva.wallet.application.queries.getaccountbyid.GetAccountByIdHandler;
import leandrosilva.wallet.application.queries.getaccountbyid.GetAccountByIdQuery;
import leandrosilva.wallet.infrastructure.api.dto.CreateAccountRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final CreateAccountHandler createAccountHandler;
    private final GetAccountByIdHandler getAccountByIdHandler;

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getByDocument(@PathVariable UUID id) {
        var query = new GetAccountByIdQuery(id);
        var response = getAccountByIdHandler.handle(query);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<AccountDto> create(@RequestBody @Valid CreateAccountRequest request) {
        var command = new CreateAccountCommand(
                request.userId(),
                request.currency(),
                request.balance());

        var response = createAccountHandler.handle(command);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }
}