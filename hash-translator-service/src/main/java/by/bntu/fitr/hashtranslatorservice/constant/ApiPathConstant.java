package by.bntu.fitr.hashtranslatorservice.constant;

public interface ApiPathConstant {
    String AUTHENTICATION_SERVICE_DOMAIN = System.getenv("AUTHENTICATION_SERVICE_DOMAIN");
    String MD5_HASH_TRANSLATOR_SERVICE_DOMAIN = "https://md5.opiums.eu";
    String VALIDATE_USER = AUTHENTICATION_SERVICE_DOMAIN + "/api/v1/authentication-service/authorized/validate-user";
    String DECODE_MD5_HASH = MD5_HASH_TRANSLATOR_SERVICE_DOMAIN + "/api.php";
}
