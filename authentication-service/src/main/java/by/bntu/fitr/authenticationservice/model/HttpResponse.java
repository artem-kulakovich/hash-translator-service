package by.bntu.fitr.authenticationservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HttpResponse {
    private HttpStatus httpStatus;
    private String msg;
    private int code;
}
