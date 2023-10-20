package nvv.dev.programingcourses.controller.auth;

import lombok.RequiredArgsConstructor;
import nvv.dev.programingcourses.request.SignInRequest;
import nvv.dev.programingcourses.response.AuthenticationResponse;
import nvv.dev.programingcourses.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class SignInController {
    private final AuthService authService;
    @PostMapping("/signin")
    public ResponseEntity<AuthenticationResponse> signup(@RequestBody SignInRequest request) {
        return ResponseEntity.ok(authService.signin(request));
    }
}
