package by.bntu.fitr.authenticationservice.util;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class PasswordEncoder {

    public String encodeWithMD5(final String password) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("md5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] passwordInBytes = md5.digest(password.getBytes());
        StringBuilder passwordBuilder = new StringBuilder();

        for (byte b : passwordInBytes) {
            passwordBuilder.append(String.format("%02X", b));
        }
        System.out.println(passwordBuilder);
        return passwordBuilder.toString();
    }
}
