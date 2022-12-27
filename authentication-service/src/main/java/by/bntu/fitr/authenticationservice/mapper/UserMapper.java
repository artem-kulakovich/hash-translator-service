package by.bntu.fitr.authenticationservice.mapper;

import by.bntu.fitr.authenticationservice.entity.User;
import by.bntu.fitr.authenticationservice.dto.request.UserCreateByAdminRequestDTO;
import by.bntu.fitr.authenticationservice.dto.request.UserSignUpRequestDTO;
import by.bntu.fitr.authenticationservice.dto.response.UserResponseDTO;
import by.bntu.fitr.authenticationservice.dto.response.UserSignUpResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final RoleMapper roleMapper;

    @Autowired
    public UserMapper(final RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    public User mapToUser(final UserSignUpRequestDTO userSignUpRequestDTO) {
        return new User(
                userSignUpRequestDTO.getEmail(),
                userSignUpRequestDTO.getPassword()
        );
    }

    public UserSignUpResponseDTO mapToUserSignUpResponseDTO(final User user) {
        return new UserSignUpResponseDTO(
                user.getId(),
                user.getEmail()
        );
    }

    public UserResponseDTO mapToUserResponseDTO(final User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getEmail(),
                roleMapper.toRoleResponseDTO(user.getRole())
        );
    }

    public User mapToUser(final UserCreateByAdminRequestDTO userCreateByAdminRequestDTO) {
        return new User(
                userCreateByAdminRequestDTO.getEmail(),
                userCreateByAdminRequestDTO.getPassword()
        );
    }


}
