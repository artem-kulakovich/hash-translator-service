package by.bntu.fitr.hashtranslatorservice.rest;

import by.bntu.fitr.hashtranslatorservice.constant.ApiPathConstant;
import by.bntu.fitr.hashtranslatorservice.constant.CommonConstant;
import by.bntu.fitr.hashtranslatorservice.constant.ExceptionConstant;
import by.bntu.fitr.hashtranslatorservice.dto.request.ApplicationRequestDTO;
import by.bntu.fitr.hashtranslatorservice.dto.response.ApplicationResponseDTO;
import by.bntu.fitr.hashtranslatorservice.entity.Application;
import by.bntu.fitr.hashtranslatorservice.mapper.ApplicationMapper;
import by.bntu.fitr.hashtranslatorservice.service.ApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/applications")
public class ApplicationRestController {
    private final ApplicationService applicationService;
    private final ApplicationMapper applicationMapper;

    @Autowired
    public ApplicationRestController(final ApplicationService applicationService,
                                     final ApplicationMapper applicationMapper) {
        this.applicationService = applicationService;
        this.applicationMapper = applicationMapper;
    }

    @PostMapping("/")
    public ResponseEntity<String> processApplication(@RequestHeader(value = "Authorization") final String authorizationToken,
                                                     @RequestBody @Valid final ApplicationRequestDTO applicationRequestDTO) {
        Application application = applicationService.createApplication(applicationRequestDTO, authorizationToken);
        applicationService.processApplication(application);
        log.info("Processing application with id = {}", application.getId());
        return new ResponseEntity<>(application.getId(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicationResponseDTO> getApplicationById(@RequestHeader(value = "Authorization") final String authorizationToken,
                                                                     @PathVariable(value = "id") final String id) {
        Application application = applicationService.getApplicationById(id, authorizationToken);
        log.info("Application is found: {}", application);
        return new ResponseEntity<>(applicationMapper.mapToApplicationResponseDTO(application), HttpStatus.OK);
    }
}
