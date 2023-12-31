package nvv.dev.programingcourses.services;

import lombok.RequiredArgsConstructor;
import nvv.dev.programingcourses.models.User;
import nvv.dev.programingcourses.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void save(User user) {
        userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public Boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    public Boolean existsByUsername(String userName){
        return userRepository.existsByUserName(userName);
    }

}
