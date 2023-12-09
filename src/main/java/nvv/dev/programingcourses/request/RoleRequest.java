package nvv.dev.programingcourses.request;

import lombok.*;
import nvv.dev.programingcourses.enums.ERole;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleRequest {
    private String requestSug;
    private Role role;
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    public static class Role {
        private Integer id;
        private ERole name;
        private String description;
    }
}
