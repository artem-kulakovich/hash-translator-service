package by.bntu.fitr.hashtranslatorservice.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Date;
import java.util.Map;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationResponseDTO {
    @JsonProperty(value = "id")
    private String id;

    @JsonProperty(value = "hashAndValues")
    private Map<String, String> hashValues;

    @JsonProperty(value = "status")
    private String status;

    @JsonProperty(value = "userId")
    private Long userId;

    @JsonProperty(value = "createAt")
    private Date createAt;
}
