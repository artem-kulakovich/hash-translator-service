package by.bntu.fitr.hashtranslatorservice.util;

import by.bntu.fitr.hashtranslatorservice.constant.ApiPathConstant;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

@Component
public class RestTemplateUtil {

    public String getUrlTemplate(final String path, final Map<String, String> paramValues) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(path);
        for (Map.Entry<String, String> entry : paramValues.entrySet()) {
            uriComponentsBuilder.queryParam(entry.getKey(), entry.getValue());
        }
        return uriComponentsBuilder.encode().toUriString();
    }

}
