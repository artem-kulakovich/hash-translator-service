package by.bntu.fitr.hashtranslatorservice.client.impl;

import by.bntu.fitr.hashtranslatorservice.client.AuthenticationClient;
import by.bntu.fitr.hashtranslatorservice.constant.ApiPathConstant;
import by.bntu.fitr.hashtranslatorservice.constant.CommonConstant;
import by.bntu.fitr.hashtranslatorservice.dto.request.UserRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class AuthenticationClientImpl implements AuthenticationClient {
    private RestTemplate restTemplate;

    @Override
    public ResponseEntity<UserRequestDTO> validateUser(final String basicToken) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(CommonConstant.CONTENT_TYPE_HEADER_NAME, MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.set(CommonConstant.AUTHORIZATION_HEADER_NAME, basicToken);

        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<UserRequestDTO> userRequestDTOResponseEntity = restTemplate.exchange(
                ApiPathConstant.VALIDATE_USER,
                HttpMethod.GET,
                httpEntity,
                UserRequestDTO.class
        );

        return userRequestDTOResponseEntity;
    }

    @Autowired
    public void setRestTemplate(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
