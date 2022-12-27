package by.bntu.fitr.authenticationservice.service.impl;

import by.bntu.fitr.authenticationservice.repository.RoleRepository;
import by.bntu.fitr.authenticationservice.service.RoleService;
import by.bntu.fitr.authenticationservice.entity.Role;
import by.bntu.fitr.authenticationservice.excecption.RoleNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(final RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRoleByName(final String name) {
        return roleRepository.findRoleByName(name).orElseThrow(() -> new RoleNotFoundException("Role"));
    }
}
