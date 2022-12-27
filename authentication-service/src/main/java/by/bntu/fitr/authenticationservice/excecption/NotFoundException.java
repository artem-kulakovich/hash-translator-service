package by.bntu.fitr.authenticationservice.excecption;

import by.bntu.fitr.authenticationservice.constant.ExceptionConstant;

public class NotFoundException extends RuntimeException {

    public NotFoundException(final String msg) {
        super(msg + " " + ExceptionConstant.NOT_FOUND_EXCEPTION_MSG);
    }
}
