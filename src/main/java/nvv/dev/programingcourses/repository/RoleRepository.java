package nvv.dev.programingcourses.repository;

import nvv.dev.programingcourses.enums.ERole;
import nvv.dev.programingcourses.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole eRole);
}
