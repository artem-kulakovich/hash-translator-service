package by.bntu.fitr.authenticationservice.excecption;

public class PermissionDeniedException extends RuntimeException {

    public PermissionDeniedException(final String msg) {
        super(msg);
    }

}
