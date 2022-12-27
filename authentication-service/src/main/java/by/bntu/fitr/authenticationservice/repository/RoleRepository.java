package by.bntu.fitr.authenticationservice.repository;

import by.bntu.fitr.authenticationservice.entity.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Optional<Role> findRoleByName(final String name);
}
