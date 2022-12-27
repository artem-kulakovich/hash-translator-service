package by.bntu.fitr.hashtranslatorservice.client;

import by.bntu.fitr.hashtranslatorservice.dto.request.UserRequestDTO;
import org.springframework.http.ResponseEntity;

public interface AuthenticationClient {

    ResponseEntity<UserRequestDTO> validateUser(final String basicToken);
}
