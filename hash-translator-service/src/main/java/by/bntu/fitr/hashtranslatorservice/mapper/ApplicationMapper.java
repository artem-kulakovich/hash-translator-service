package by.bntu.fitr.hashtranslatorservice.mapper;

import by.bntu.fitr.hashtranslatorservice.dto.response.ApplicationResponseDTO;
import by.bntu.fitr.hashtranslatorservice.entity.Application;
import org.springframework.stereotype.Component;

@Component
public class ApplicationMapper {

    public ApplicationResponseDTO mapToApplicationResponseDTO(final Application application) {
        return new ApplicationResponseDTO(
                application.getId(),
                application.getHashValues(),
                application.getStatus(),
                application.getUserId(),
                application.getCreateAt()
        );
    }

}
