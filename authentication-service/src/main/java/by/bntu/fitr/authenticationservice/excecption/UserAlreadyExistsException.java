package by.bntu.fitr.authenticationservice.excecption;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(final String msg) {
        super(msg);
    }
}
