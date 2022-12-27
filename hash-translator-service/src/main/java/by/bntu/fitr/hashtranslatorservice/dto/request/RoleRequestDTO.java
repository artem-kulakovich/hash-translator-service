package by.bntu.fitr.hashtranslatorservice.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleRequestDTO {
    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "name")
    private String name;
}
