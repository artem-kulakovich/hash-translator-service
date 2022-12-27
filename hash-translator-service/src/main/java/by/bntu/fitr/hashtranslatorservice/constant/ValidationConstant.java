package by.bntu.fitr.hashtranslatorservice.constant;

public interface ValidationConstant {
    int MIN_COUNT_OF_HASHES_CONSTRAINT = 1;
    int MAX_COUNT_OF_HASHES_CONSTRAINT = 10;

    String INCORRECT_COUNT_OF_HASHES_EXCEPTION_MSG = "Count of hashes should be more than 0 and less than 10";
}
