package by.bntu.fitr.authenticationservice.excecption;

public class UserNotFoundException extends NotFoundException {

    public UserNotFoundException(final String msg) {
        super(msg);
    }
}
