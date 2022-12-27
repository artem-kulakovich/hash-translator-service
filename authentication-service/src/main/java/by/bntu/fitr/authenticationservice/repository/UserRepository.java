package by.bntu.fitr.authenticationservice.repository;

import by.bntu.fitr.authenticationservice.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findUserByEmail(final String email);

}
