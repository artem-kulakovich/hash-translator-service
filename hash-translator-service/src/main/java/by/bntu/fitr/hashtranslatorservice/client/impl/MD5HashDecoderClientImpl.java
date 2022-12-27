package by.bntu.fitr.hashtranslatorservice.client.impl;

import by.bntu.fitr.hashtranslatorservice.client.MD5HashDecoderClient;
import by.bntu.fitr.hashtranslatorservice.constant.ApiPathConstant;
import by.bntu.fitr.hashtranslatorservice.constant.CommonConstant;
import by.bntu.fitr.hashtranslatorservice.util.RestTemplateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class MD5HashDecoderClientImpl implements MD5HashDecoderClient {
    private RestTemplate restTemplate;
    private final RestTemplateUtil restTemplateUtil;

    public MD5HashDecoderClientImpl(final RestTemplateUtil restTemplateUtil) {
        this.restTemplateUtil = restTemplateUtil;
    }

    @Override
    public ResponseEntity<String> decodeMD5Hash(final String md5Hash) {
        String urlTemplate = restTemplateUtil.getUrlTemplate(ApiPathConstant.DECODE_MD5_HASH, Map.of(
                "type", "{type}", "hash", "{hash}", "uot", "{uot}"
        ));

        Map<String, ?> params = Map.of("type", CommonConstant.MD5_HASH_VALUE,
                "hash", md5Hash,
                "uot", CommonConstant.QOT_VALUE);

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                urlTemplate,
                String.class,
                params);

        return responseEntity;
    }

    @Autowired
    public void setRestTemplate(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
