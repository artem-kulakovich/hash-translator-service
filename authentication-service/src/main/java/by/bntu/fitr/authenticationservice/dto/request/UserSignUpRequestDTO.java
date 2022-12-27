package by.bntu.fitr.authenticationservice.dto.request;

import by.bntu.fitr.authenticationservice.constant.ValidationConstant;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSignUpRequestDTO {

    @Size(
            min = ValidationConstant.EMAIL_MIN_CONSTRAINT,
            max = ValidationConstant.EMAIL_MAX_CONSTRAINT,
            message = ValidationConstant.EMAIL_ERROR_WITH_LIMIT_MSG
    )
    @NotNull
    @JsonProperty(value = "email")
    private String email;

    @Size(
            min = ValidationConstant.PASSWORD_MIN_CONSTRAINT,
            max = ValidationConstant.PASSWORD_MAX_CONSTRAINT,
            message = ValidationConstant.PASSWORD_ERROR_WITH_LIMIT_MSG
    )
    @NotNull
    @JsonProperty(value = "password")
    private String password;

    @Size(
            min = ValidationConstant.PASSWORD_MIN_CONSTRAINT,
            max = ValidationConstant.PASSWORD_MAX_CONSTRAINT,
            message = ValidationConstant.PASSWORD_ERROR_WITH_LIMIT_MSG
    )
    @NotNull
    @JsonProperty(value = "repeatedPassword")
    private String repeatedPassword;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserSignUpRequestDTO that = (UserSignUpRequestDTO) o;

        if (!Objects.equals(email, that.email)) return false;
        if (!Objects.equals(password, that.password)) return false;
        return Objects.equals(repeatedPassword, that.repeatedPassword);
    }

    @Override
    public int hashCode() {
        int result = email != null ? email.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (repeatedPassword != null ? repeatedPassword.hashCode() : 0);
        return result;
    }
}
