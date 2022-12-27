package by.bntu.fitr.hashtranslatorservice.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "application")
public class Application implements Serializable {
    @Id
    private String id;

    @Field(value = "hashValues")
    private Map<String, String> hashValues;

    @Field(value = "status")
    private String status;

    @Field(value = "userId")
    private Long userId;

    @Field(value = "createAt")
    private Date createAt;

    public Application(final Map<String, String> hashValues) {
        this.hashValues = hashValues;
    }

    @Override
    public String toString() {
        return "Application{" +
                "id='" + id + '\'' +
                ", hashValues=" + hashValues +
                ", status='" + status + '\'' +
                ", userId=" + userId +
                ", createAt=" + createAt +
                '}';
    }
}
