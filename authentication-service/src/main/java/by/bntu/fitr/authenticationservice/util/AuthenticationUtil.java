package by.bntu.fitr.authenticationservice.util;

import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class AuthenticationUtil {

    public String getEmail(final String token) {
        return token.split(":")[0];
    }

    public String getPassword(final String token) {
        return token.split(":")[1];
    }

    public String getToken(final String authenticationHeaderParam) {
        return authenticationHeaderParam.split(" ")[1];
    }
}
