package nvv.dev.programingcourses.controller;

import lombok.RequiredArgsConstructor;
import nvv.dev.programingcourses.request.RoleRequest;
import nvv.dev.programingcourses.response.MessageResponse;
import nvv.dev.programingcourses.services.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/docs")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @PostMapping("/roles")
    public ResponseEntity<MessageResponse> updatedRole(
            @RequestBody RoleRequest request
    ) {
        return ResponseEntity.ok(roleService.handleCrud(request));
    }
}
