package nvv.dev.programingcourses.controller;

import lombok.RequiredArgsConstructor;
import nvv.dev.programingcourses.response.ApiResponse;
import nvv.dev.programingcourses.services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/{username}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ApiResponse getUserByUsername(@PathVariable String username) {
        return ApiResponse.builder()
                .data(userService.findByUserName(username))
                .build();
    }
}