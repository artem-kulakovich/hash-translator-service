package by.bntu.fitr.hashtranslatorservice.service.impl;


import by.bntu.fitr.hashtranslatorservice.client.AuthenticationClient;
import by.bntu.fitr.hashtranslatorservice.client.MD5HashDecoderClient;
import by.bntu.fitr.hashtranslatorservice.constant.CommonConstant;
import by.bntu.fitr.hashtranslatorservice.constant.ExceptionConstant;
import by.bntu.fitr.hashtranslatorservice.dto.request.ApplicationRequestDTO;
import by.bntu.fitr.hashtranslatorservice.dto.request.UserRequestDTO;
import by.bntu.fitr.hashtranslatorservice.entity.Application;
import by.bntu.fitr.hashtranslatorservice.exception.ApplicationNotFoundException;
import by.bntu.fitr.hashtranslatorservice.exception.DuplicateHashesException;
import by.bntu.fitr.hashtranslatorservice.exception.RestTemplateClientException;
import by.bntu.fitr.hashtranslatorservice.mapper.ApplicationMapper;
import by.bntu.fitr.hashtranslatorservice.repository.ApplicationRepository;
import by.bntu.fitr.hashtranslatorservice.service.ApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ApplicationServiceImpl implements ApplicationService {
    private final ApplicationMapper applicationMapper;
    private final AuthenticationClient authenticationClient;
    private final MD5HashDecoderClient md5HashDecoderClient;
    private final ApplicationRepository applicationRepository;

    @Autowired
    public ApplicationServiceImpl(final ApplicationMapper applicationMapper,
                                  final AuthenticationClient authenticationClient,
                                  final MD5HashDecoderClient md5HashDecoderClient,
                                  final ApplicationRepository applicationRepository) {
        this.applicationMapper = applicationMapper;
        this.authenticationClient = authenticationClient;
        this.md5HashDecoderClient = md5HashDecoderClient;
        this.applicationRepository = applicationRepository;
    }

    @Async(value = "asyncTaskExecutor")
    @Override
    public void processApplication(final Application application) {
        /*
        try {
            Thread.sleep(30000l);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        */
        Map<String, String> hashValues = application.getHashValues();

        hashValues.forEach((key, value) -> {
            ResponseEntity<String> decodedHashResponseEntity = md5HashDecoderClient.decodeMD5Hash(key);
            if (decodedHashResponseEntity.getStatusCode().equals(HttpStatus.OK)) {
                hashValues.put(key, decodedHashResponseEntity.getBody() == null
                        ? CommonConstant.EMPTY
                        : decodedHashResponseEntity.getBody());
            }
        });

        application.setStatus(CommonConstant.APPLICATION_CONFIRMED_STATUS);
        log.info("Processed application: {}", application);
        applicationRepository.save(application);
    }

    @Override
    public Application createApplication(final ApplicationRequestDTO applicationRequestDTO, final String authToken) {
        UserRequestDTO userRequestDTO = getUserIfAuthTokenValid(authToken);
        List<String> hashes = applicationRequestDTO.getHashes();
        Map<String, String> hashValues = new HashMap<>();

        for (String hash : hashes) {
            hashValues.put(hash, null);
        }

        if (hashValues.keySet().size() != hashes.size()) {
            DuplicateHashesException duplicateHashesException = new DuplicateHashesException(ExceptionConstant.DUPLICATE_HASHES_IN_APPLICATION_REQUEST_MSG);
            log.error("An exception occurred! ", duplicateHashesException);
            throw duplicateHashesException;
        }

        Application application = new Application(hashValues);
        application.setStatus(CommonConstant.APPLICATION_NEW_STATUS);
        application.setUserId(userRequestDTO.getId());
        application.setCreateAt(new Date());
        return applicationRepository.save(application);
    }

    @Override
    public Application getApplicationById(final String id, final String authToken) {
        getUserIfAuthTokenValid(authToken);
        return applicationRepository.findById(id).orElseThrow(() -> new ApplicationNotFoundException("Application"));
    }

    private UserRequestDTO getUserIfAuthTokenValid(final String authToken) {
        ResponseEntity<UserRequestDTO> userRequestDTOResponseEntity = authenticationClient.validateUser(authToken);
        if (!userRequestDTOResponseEntity.getStatusCode().equals(HttpStatus.OK)) {
            RestTemplateClientException restTemplateClientException = new RestTemplateClientException(ExceptionConstant.VALIDATE_USER_REST_TEMPLATE_EXCEPTION_MSG);
            log.error("An exception occurred! ", restTemplateClientException);
            throw restTemplateClientException;
        }
        return userRequestDTOResponseEntity.getBody();

    }
}
