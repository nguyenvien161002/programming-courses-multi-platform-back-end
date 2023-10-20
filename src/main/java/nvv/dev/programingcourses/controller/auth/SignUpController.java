package nvv.dev.programingcourses.controller.auth;

import lombok.RequiredArgsConstructor;
import nvv.dev.programingcourses.request.SignUpRequest;
import nvv.dev.programingcourses.response.AuthenticationResponse;
import nvv.dev.programingcourses.services.AuthService;
import nvv.dev.programingcourses.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class SignUpController {
    private final AuthService authService;
    private final UserService userService;
    @PostMapping("/signup")
    public ResponseEntity<AuthenticationResponse> signup(@RequestBody SignUpRequest request) {
        if(userService.existsByEmail(request.getEmail())) {
            AuthenticationResponse authResponse = AuthenticationResponse.builder()
                    .message("Email already exists.")
                    .token("Error by exists email.")
                    .build();
            return ResponseEntity.badRequest().body(authResponse);
        }
        return ResponseEntity.ok(authService.signup(request));
    }

}
