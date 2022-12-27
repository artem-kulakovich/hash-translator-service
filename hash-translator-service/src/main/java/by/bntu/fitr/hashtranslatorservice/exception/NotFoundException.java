package by.bntu.fitr.hashtranslatorservice.exception;

import by.bntu.fitr.hashtranslatorservice.constant.CommonConstant;
import by.bntu.fitr.hashtranslatorservice.constant.ExceptionConstant;

public class NotFoundException extends RuntimeException {

    public NotFoundException(final String msg) {
        super(msg + " " + ExceptionConstant.NOT_FOUND_EXCEPTION_MSG);
    }
}
