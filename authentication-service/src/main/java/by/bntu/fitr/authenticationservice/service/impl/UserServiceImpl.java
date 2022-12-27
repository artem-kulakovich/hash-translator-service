package by.bntu.fitr.authenticationservice.service.impl;

import by.bntu.fitr.authenticationservice.repository.UserRepository;
import by.bntu.fitr.authenticationservice.constant.CommonConstant;
import by.bntu.fitr.authenticationservice.constant.ExceptionConstant;
import by.bntu.fitr.authenticationservice.dto.request.UserCreateByAdminRequestDTO;
import by.bntu.fitr.authenticationservice.dto.request.UserSignUpRequestDTO;
import by.bntu.fitr.authenticationservice.entity.User;
import by.bntu.fitr.authenticationservice.excecption.PasswordMissMatchesException;
import by.bntu.fitr.authenticationservice.excecption.PermissionDeniedException;
import by.bntu.fitr.authenticationservice.excecption.UserAlreadyExistsException;
import by.bntu.fitr.authenticationservice.excecption.UserNotFoundException;
import by.bntu.fitr.authenticationservice.mapper.UserMapper;
import by.bntu.fitr.authenticationservice.service.RoleService;
import by.bntu.fitr.authenticationservice.service.UserService;
import by.bntu.fitr.authenticationservice.util.AuthenticationUtil;
import by.bntu.fitr.authenticationservice.util.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;

@Service
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final AuthenticationUtil authenticationUtil;

    @Autowired
    public UserServiceImpl(final UserRepository userRepository,
                           final PasswordEncoder passwordEncoder,
                           final UserMapper userMapper,
                           final RoleService roleService,
                           final AuthenticationUtil authenticationUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.roleService = roleService;
        this.authenticationUtil = authenticationUtil;
    }

    @Override
    public void checkIfUserExists(final String email) {
        if (userRepository.findUserByEmail(email).isPresent()) {
            throw new UserAlreadyExistsException(ExceptionConstant.USER_ALREADY_EXISTS_EXCEPTION_MSG);
        }
    }

    @Transactional
    @Override
    public User signUp(final UserSignUpRequestDTO userSignUpRequestDTO) {
        if (!userSignUpRequestDTO.getPassword().equals(userSignUpRequestDTO.getRepeatedPassword())) {
            throw new PasswordMissMatchesException(ExceptionConstant.PASSWORD_MISS_MATCH_EXCEPTION_MSG);
        }

        checkIfUserExists(userSignUpRequestDTO.getEmail());

        User user = userMapper.mapToUser(userSignUpRequestDTO);
        user.setPassword(passwordEncoder.encodeWithMD5(user.getPassword()));
        user.setRole(roleService.getRoleByName(CommonConstant.USER_ROLE_NAME));
        return userRepository.save(user);
    }

    @Override
    public User createUser(final UserCreateByAdminRequestDTO userCreateByAdminRequestDTO) {
        checkIfUserExists(userCreateByAdminRequestDTO.getEmail());

        User user = userMapper.mapToUser(userCreateByAdminRequestDTO);
        user.setPassword(passwordEncoder.encodeWithMD5(user.getPassword()));
        user.setRole(roleService.getRoleByName(CommonConstant.USER_ROLE_NAME));
        return userRepository.save(user);
    }

    @Override
    public User getUserByEmail(final String email) {
        return userRepository.findUserByEmail(email).orElseThrow(() -> new UserNotFoundException("User"));
    }

    @Override
    public User getUserByAuthorizationToken(final String authorizationHeaderParam) {
        String basicToken = new String(Base64.getDecoder().decode(authenticationUtil.getToken(authorizationHeaderParam)));
        String email = authenticationUtil.getEmail(basicToken);
        User user = null;

        try {
            user = getUserByEmail(email);
        } catch (UserNotFoundException e) {
            throw new PermissionDeniedException(ExceptionConstant.PERMISSION_DENIED_EXCEPTION_MSG);
        }

        String password = authenticationUtil.getPassword(basicToken);
        if (!passwordEncoder.encodeWithMD5(password).equals(user.getPassword())) {
            throw new PermissionDeniedException(ExceptionConstant.PERMISSION_DENIED_EXCEPTION_MSG);
        }

        return user;
    }

    @Override
    public void checkIfUserHasPermissionToDoOperation(final User user, final String requiredRoleName) {
        if (!user.getRole().getName().equals(requiredRoleName)) {
            throw new PermissionDeniedException(ExceptionConstant.PERMISSION_DENIED_EXCEPTION_MSG);
        }
    }

    @Override
    public User getUserById(final Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User"));
    }


    @Override
    public void deleteUser(final Long id) {
        User user = getUserById(id);
        userRepository.delete(user);
    }
}
