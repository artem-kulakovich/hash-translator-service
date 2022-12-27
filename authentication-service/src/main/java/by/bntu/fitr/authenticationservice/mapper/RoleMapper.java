package by.bntu.fitr.authenticationservice.mapper;

import by.bntu.fitr.authenticationservice.entity.Role;
import by.bntu.fitr.authenticationservice.dto.response.RoleResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    public RoleResponseDTO toRoleResponseDTO(final Role role) {
        return new RoleResponseDTO(
                role.getId(),
                role.getName()
        );
    }
}
