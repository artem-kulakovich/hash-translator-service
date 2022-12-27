package by.bntu.fitr.hashtranslatorservice.exception;

public class PermissionDeniedException extends RuntimeException {

    public PermissionDeniedException(final String msg){
        super(msg);
    }
}
