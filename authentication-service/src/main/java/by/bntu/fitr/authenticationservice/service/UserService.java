package by.bntu.fitr.authenticationservice.service;

import by.bntu.fitr.authenticationservice.dto.request.UserCreateByAdminRequestDTO;
import by.bntu.fitr.authenticationservice.dto.request.UserSignUpRequestDTO;
import by.bntu.fitr.authenticationservice.entity.User;

public interface UserService {

    void checkIfUserExists(final String email);

    User signUp(final UserSignUpRequestDTO userSignUpRequestDTO);

    User createUser(final UserCreateByAdminRequestDTO userCreateByAdminRequestDTO);

    User getUserByEmail(final String email);

    User getUserByAuthorizationToken(final String authorizationHeaderParam);

    void checkIfUserHasPermissionToDoOperation(final User user, final String requiredRoleName);

    User getUserById(final Long id);

    void deleteUser(final Long id);

}
