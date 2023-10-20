package nvv.dev.programingcourses.services;

import lombok.RequiredArgsConstructor;
import nvv.dev.programingcourses.enums.ERole;
import nvv.dev.programingcourses.models.Role;
import nvv.dev.programingcourses.models.User;
import nvv.dev.programingcourses.request.SignInRequest;
import nvv.dev.programingcourses.request.SignUpRequest;
import nvv.dev.programingcourses.response.AuthenticationResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse signup(SignUpRequest request) {
        String firstName = request.getFirstname();
        String lastName = request.getLastname();
        String email = request.getEmail();
        Set<String> jsonRoles = request.getListRoles();
        Set<Role> listRoles = new HashSet<>();
        if (jsonRoles == null) {
            Role userRole = roleService.findByName(ERole.USER);
            listRoles.add(userRole);
        } else {
            jsonRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleService.findByName(ERole.ADMIN);
                        listRoles.add(adminRole);
                    case "moderator":
                        Role moderatorRole = roleService.findByName(ERole.MODERATOR);
                        listRoles.add(moderatorRole);
                    default:
                        Role userrRole = roleService.findByName(ERole.USER);
                        listRoles.add(userrRole);
                }
            });
        }
        User user = User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .fullName(String.format("%s %s", firstName, lastName))
                .userName(generateUsername(firstName, lastName))
                .email(email)
                .password(passwordEncoder.encode(request.getPassword()))
                .updatedAt(new Date())
                .createdAt(new Date())
                .roles(listRoles)
                .build();
        userService.save(user);
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .message("Sign up successfully!")
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse signin(SignInRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = userService.findByEmail(request.getEmail());
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .message("Sign in successfully!")
                .token(jwtToken)
                .build();
    }

    public String generateUsername(String firstname, String lastname) {
        String username = normalizeName(firstname) + normalizeName(lastname);
        int suffix = 1;
        String originalUsername = username;
        while (userService.existsByUsername(username)) {
            username = originalUsername + suffix;
            suffix++;
        }
        return username;
    }

    private String normalizeName(String name) {
        return Normalizer.normalize(name.toLowerCase(), Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                .replaceAll("\\s", "");
    }


}
