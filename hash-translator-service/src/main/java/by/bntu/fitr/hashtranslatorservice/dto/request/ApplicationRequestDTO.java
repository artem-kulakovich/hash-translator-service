package by.bntu.fitr.hashtranslatorservice.dto.request;

import by.bntu.fitr.hashtranslatorservice.constant.ValidationConstant;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationRequestDTO {

    @Size(
            min = ValidationConstant.MIN_COUNT_OF_HASHES_CONSTRAINT,
            max = ValidationConstant.MAX_COUNT_OF_HASHES_CONSTRAINT,
            message = ValidationConstant.INCORRECT_COUNT_OF_HASHES_EXCEPTION_MSG
    )
    @NotNull
    @JsonProperty(value = "hashes")
    private List<String> hashes;

    @Override
    public String toString() {
        return "ApplicationRequestDTO{" +
                "hashes=" + hashes +
                '}';
    }
}
