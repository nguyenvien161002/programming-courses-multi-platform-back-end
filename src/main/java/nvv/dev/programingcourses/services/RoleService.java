package nvv.dev.programingcourses.services;

import nvv.dev.programingcourses.enums.ERole;
import nvv.dev.programingcourses.models.Role;
import nvv.dev.programingcourses.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void save(Role role) {
        roleRepository.save(role);
    }

    public Role findByName(ERole eRole) {
        return roleRepository.findByName(eRole)
                .orElseThrow(() -> new RuntimeException("Error: Role not found"));
    }

}
