package by.bntu.fitr.authenticationservice.rest;

import by.bntu.fitr.authenticationservice.constant.CommonConstant;
import by.bntu.fitr.authenticationservice.constant.ExceptionConstant;
import by.bntu.fitr.authenticationservice.dto.request.UserCreateByAdminRequestDTO;
import by.bntu.fitr.authenticationservice.dto.request.UserSignUpRequestDTO;
import by.bntu.fitr.authenticationservice.dto.response.UserResponseDTO;
import by.bntu.fitr.authenticationservice.dto.response.UserSignUpResponseDTO;
import by.bntu.fitr.authenticationservice.entity.User;
import by.bntu.fitr.authenticationservice.excecption.PermissionDeniedException;
import by.bntu.fitr.authenticationservice.mapper.UserMapper;
import by.bntu.fitr.authenticationservice.service.UserService;
import by.bntu.fitr.authenticationservice.util.AuthenticationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/authentication-service")
public class UserRestController {
    private final UserMapper userMapper;
    private final UserService userService;
    private final AuthenticationUtil authenticationUtil;

    @Autowired
    public UserRestController(final UserMapper userMapper,
                              final UserService userService,
                              final AuthenticationUtil authenticationUtil) {
        this.userMapper = userMapper;
        this.userService = userService;
        this.authenticationUtil = authenticationUtil;
    }

    @PostMapping(value = "/sign-up")
    public ResponseEntity<UserSignUpResponseDTO> signUp(@RequestBody @Valid final UserSignUpRequestDTO userSignUpRequestDTO) {
        User user = userService.signUp(userSignUpRequestDTO);
        UserSignUpResponseDTO userSignUpResponseDTO = userMapper.mapToUserSignUpResponseDTO(user);
        log.info("New user sign up: {}", userSignUpResponseDTO);
        return new ResponseEntity<>(userSignUpResponseDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/authorized/validate-user")
    public ResponseEntity<UserResponseDTO> validateUser(@RequestHeader(value = "Authorization") final String authorizationParam) {
        User user = userService.getUserByAuthorizationToken(authorizationParam);
        UserResponseDTO userResponseDTO = userMapper.mapToUserResponseDTO(user);
        log.info("User validation success: {}", userResponseDTO);
        return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
    }


    @PostMapping(value = "/authorized/admin/create-user")
    public ResponseEntity<UserResponseDTO> createUser(@RequestHeader(value = "Authorization") final String authorizationParam,
                                                      @RequestBody @Valid final UserCreateByAdminRequestDTO userCreateByAdminRequestDTO) {
        User userByAuthorizationToken = userService.getUserByAuthorizationToken(authorizationParam);
        userService.checkIfUserHasPermissionToDoOperation(userByAuthorizationToken, CommonConstant.ADMIN_ROLE_NAME);
        User user = userService.createUser(userCreateByAdminRequestDTO);
        UserResponseDTO userResponseDTO = userMapper.mapToUserResponseDTO(user);
        log.info("New user created by admin: {}", userResponseDTO);
        return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/authorized/admin/delete-user/{id}")
    public ResponseEntity<Void> deleteUser(@RequestHeader(value = "Authorization") final String authorizationParam,
                                           @PathVariable("id") final Long id) {
        User userByAuthorizationToken = userService.getUserByAuthorizationToken(authorizationParam);
        userService.checkIfUserHasPermissionToDoOperation(userByAuthorizationToken, CommonConstant.ADMIN_ROLE_NAME);
        log.info("Request for deleting user with id = {}", id);
        if (userByAuthorizationToken.getId().equals(id)) {
            PermissionDeniedException permissionDeniedException = new PermissionDeniedException(ExceptionConstant.PERMISSION_DENIED_EXCEPTION_MSG);
            log.error("An exception occurred! ", permissionDeniedException);
            throw permissionDeniedException;
        }
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
