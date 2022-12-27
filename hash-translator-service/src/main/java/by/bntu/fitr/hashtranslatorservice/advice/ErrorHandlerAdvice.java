package by.bntu.fitr.hashtranslatorservice.advice;

import by.bntu.fitr.hashtranslatorservice.exception.DuplicateHashesException;
import by.bntu.fitr.hashtranslatorservice.exception.NotFoundException;
import by.bntu.fitr.hashtranslatorservice.exception.PermissionDeniedException;
import by.bntu.fitr.hashtranslatorservice.model.HttpResponse;
import by.bntu.fitr.hashtranslatorservice.util.JsonMapperUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ErrorHandlerAdvice extends ResponseEntityExceptionHandler {
    private JsonMapperUtil<HttpResponse> httpResponseJsonMapperUtil;

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<?> handleAuthorizationException(final HttpClientErrorException e) {
        HttpResponse httpResponse = httpResponseJsonMapperUtil.getObjectFromString(e.getResponseBodyAsString(), HttpResponse.class);
        return new ResponseEntity<>(httpResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<HttpResponse> handleNotFoundException(final NotFoundException e) {
        HttpResponse httpResponse = new HttpResponse(HttpStatus.NOT_FOUND, e.getMessage(), 404);
        return new ResponseEntity<>(httpResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateHashesException.class)
    public ResponseEntity<HttpResponse> handleDuplicateHashesExceptionException(final DuplicateHashesException e) {
        HttpResponse httpResponse = new HttpResponse(HttpStatus.BAD_REQUEST, e.getMessage(), 404);
        return new ResponseEntity<>(httpResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(PermissionDeniedException.class)
    public ResponseEntity<HttpResponse> handlePermissionDeniedExceptionException(final PermissionDeniedException e) {
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

    @Autowired
    public void setHttpResponseJsonMapperUtil(final JsonMapperUtil<HttpResponse> httpResponseJsonMapperUtil) {
        this.httpResponseJsonMapperUtil = httpResponseJsonMapperUtil;
    }
}
