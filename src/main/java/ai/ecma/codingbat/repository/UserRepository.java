package ai.ecma.codingbat.repository;

import ai.ecma.codingbat.entity.Role;
import ai.ecma.codingbat.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    List<User> findAllByRole(Role role);

    Optional<User> findByEmailEqualsIgnoreCase(String email);



}
