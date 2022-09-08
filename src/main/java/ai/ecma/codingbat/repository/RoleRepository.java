package ai.ecma.codingbat.repository;

import ai.ecma.codingbat.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
}
