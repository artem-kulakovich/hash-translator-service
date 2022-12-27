package by.bntu.fitr.hashtranslatorservice.util;

import by.bntu.fitr.hashtranslatorservice.model.HttpResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class JsonMapperUtil<T> {

    public T getObjectFromString(final String json, final Class<T> clazz) {
        try {
            return new ObjectMapper().readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
