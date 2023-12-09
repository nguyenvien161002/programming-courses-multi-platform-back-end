package nvv.dev.programingcourses.services;

import lombok.RequiredArgsConstructor;
import nvv.dev.programingcourses.enums.ERole;
import nvv.dev.programingcourses.models.Role;
import nvv.dev.programingcourses.repository.RoleRepository;
import nvv.dev.programingcourses.request.RoleRequest;
import nvv.dev.programingcourses.response.MessageResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public void save(Role role) {
        roleRepository.save(role);
    }

    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    public Role findByName(ERole eRole) {
        return roleRepository.findByName(eRole)
                .orElseThrow(() -> new RuntimeException("Error: Role not found"));
    }

    public MessageResponse handleCrud(RoleRequest request) {
        String requestSug = request.getRequestSug();
        RoleRequest.Role roleJson = request.getRole();
        if(requestSug.equals("update") || requestSug.equals("delete")) {
            if (!roleRepository.existsById(roleJson.getId())) {
                return MessageResponse.builder()
                        .message("Role not found")
                        .build();
            }
        }
        if (requestSug.equals("update") || requestSug.equals("new")) {
            Role role = Role.builder()
                    .name(roleJson.getName())
                    .description(roleJson.getDescription())
                    .build();
            if(requestSug.equals("update")) {
                role.setId(roleJson.getId());
            }
            roleRepository.save(role);
        } else if (requestSug.equals("delete")) {
            roleRepository.deleteById(roleJson.getId());
        } else {
            return MessageResponse.builder()
                    .message("Role object crud request does not exist")
                    .build();
        }
        return MessageResponse.builder()
                .message("Successfully")
                .build();
    }
}
