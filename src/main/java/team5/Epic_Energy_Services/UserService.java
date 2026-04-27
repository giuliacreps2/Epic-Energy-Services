package team5.Epic_Energy_Services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final RoleService roleService;
    private final UserRoleService userRoleService;

    public UserService(UserRepository userRepository,
                       PasswordEncoder encoder, RoleService roleService, UserRoleService userRoleService
    ) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.roleService = roleService;
        this.userRoleService = userRoleService;
    }

    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFound("email user " + email + " not found"));
    }
    public User findById(UUID userId) {
        return this.userRepository.findById(userId).orElseThrow(() -> new NotFound("id not found"));
    }
    public User save(UserDTO userDTO){
        User user = new User(userDTO.username(),userDTO.email()
                ,this.encoder.encode(userDTO.password()),userDTO.name(),userDTO.surname(), userDTO.avatar());
        Role role = this.roleService.findByName(userDTO.role().toLowerCase().trim());
        UserRole userRole = new UserRole(user,role);
        User userCreated = this.userRepository.save(user);
        this.userRoleService.save(userRole);
        return userCreated;
    }
}
