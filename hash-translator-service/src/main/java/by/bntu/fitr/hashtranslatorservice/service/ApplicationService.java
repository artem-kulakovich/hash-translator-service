package by.bntu.fitr.hashtranslatorservice.service;

import by.bntu.fitr.hashtranslatorservice.dto.request.ApplicationRequestDTO;
import by.bntu.fitr.hashtranslatorservice.entity.Application;

public interface ApplicationService {

    void processApplication(final Application application);

    Application createApplication(final ApplicationRequestDTO applicationRequestDTO, final String authToken);

    Application getApplicationById(final String id, final String authToken);
}
