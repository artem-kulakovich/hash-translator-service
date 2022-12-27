package by.bntu.fitr.authenticationservice.advice;



import by.bntu.fitr.authenticationservice.excecption.NotFoundException;
import by.bntu.fitr.authenticationservice.excecption.PasswordMissMatchesException;
import by.bntu.fitr.authenticationservice.excecption.PermissionDeniedException;
import by.bntu.fitr.authenticationservice.excecption.UserAlreadyExistsException;
import by.bntu.fitr.authenticationservice.model.HttpResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ErrorHandlerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PasswordMissMatchesException.class)
    public ResponseEntity<HttpResponse> handleAuthorizationException(final PasswordMissMatchesException e) {
        HttpResponse httpResponse = new HttpResponse(HttpStatus.BAD_REQUEST, e.getMessage(), 400);
        return new ResponseEntity<>(httpResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<HttpResponse> handleNotFoundException(final NotFoundException e) {
        HttpResponse httpResponse = new HttpResponse(HttpStatus.NOT_FOUND, e.getMessage(), 404);
        return new ResponseEntity<>(httpResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<HttpResponse> handleUserAlreadyExistsException(final UserAlreadyExistsException e) {
        HttpResponse httpResponse = new HttpResponse(HttpStatus.BAD_REQUEST, e.getMessage(), 400);
        return new ResponseEntity<>(httpResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PermissionDeniedException.class)
    public ResponseEntity<HttpResponse> handlePermissionDeniedException(final PermissionDeniedException e) {
        HttpResponse httpResponse = new HttpResponse(HttpStatus.BAD_REQUEST, e.getMessage(), 400);
        return new ResponseEntity<>(httpResponse, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(
                (error) -> {
                    String fieldName = ((FieldError) error).getField();
                    String message = error.getDefaultMessage();
                    errors.put(fieldName, message);
                });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
