package by.bntu.fitr.authenticationservice.excecption;

public class PasswordMissMatchesException extends RuntimeException {

    public PasswordMissMatchesException(final String msg) {
        super(msg);
    }
}
