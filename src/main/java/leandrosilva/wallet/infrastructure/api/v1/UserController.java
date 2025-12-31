package leandrosilva.wallet.infrastructure.api.v1;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;
import leandrosilva.wallet.application.commands.createuser.CreateUserHandler;
import leandrosilva.wallet.application.dto.UserDto;
import leandrosilva.wallet.application.queries.getuserbydocument.GetUserByDocumentHandler;
import leandrosilva.wallet.application.queries.getuserbydocument.GetUserByDocumentQuery;
import leandrosilva.wallet.infrastructure.api.dto.CreateUserRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final GetUserByDocumentHandler getUserByDocumentHandler;
    private final CreateUserHandler createUserUseCase;

    @GetMapping("/{documentNumber}")
    public ResponseEntity<UserDto> getByDocument(@PathVariable String documentNumber) {
        var query = new GetUserByDocumentQuery(documentNumber);
        var response = getUserByDocumentHandler.handle(query);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody @Valid CreateUserRequest request) {
        var command = request.toCommand();
        var response = createUserUseCase.handle(command);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{documentNumber}")
                .buildAndExpand(response.documentNumber())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }
}
