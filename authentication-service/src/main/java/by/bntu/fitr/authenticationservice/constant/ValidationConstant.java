package by.bntu.fitr.authenticationservice.constant;

public interface ValidationConstant {
    int EMAIL_MIN_CONSTRAINT = 4;
    int EMAIL_MAX_CONSTRAINT = 300;
    int PASSWORD_MIN_CONSTRAINT = 5;
    int PASSWORD_MAX_CONSTRAINT = 20;

    String EMAIL_ERROR_WITH_LIMIT_MSG = "Email is to long or short. Please, check email again";
    String PASSWORD_ERROR_WITH_LIMIT_MSG = "Password is to long or short. Please, check password again";
}
