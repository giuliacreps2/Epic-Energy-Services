package team5.Epic_Energy_Services.services;

import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import team5.Epic_Energy_Services.entities.Role;
import team5.Epic_Energy_Services.entities.User;
import team5.Epic_Energy_Services.entities.UserRole;
import team5.Epic_Energy_Services.exceptions.NotFoundException;
import team5.Epic_Energy_Services.payloads.UserDTO;
import team5.Epic_Energy_Services.repositories.UserRepository;

import java.util.UUID;

@Service
public class UsersService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final RoleService roleService;
    private final UserRoleService userRoleService;

    public UsersService(UserRepository userRepository,
                        PasswordEncoder encoder, RoleService roleService, UserRoleService userRoleService
    ) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.roleService = roleService;
        this.userRoleService = userRoleService;
    }

    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("email user " + email + " not found"));
    }

    @Transactional
    public User findById(UUID userId) {
        return this.userRepository.findById(userId).orElseThrow(() -> new NotFoundException("id not found"));
    }

    public User save(UserDTO userDTO) {
        User user = new User(userDTO.username(), userDTO.email()
                , this.encoder.encode(userDTO.password()), userDTO.name(), userDTO.surname(), userDTO.avatar());
        Role role = this.roleService.findByName(userDTO.role().trim());
        UserRole userRole = new UserRole(user, role);
        User userCreated = this.userRepository.save(user);
        this.userRoleService.save(userRole);
        return userCreated;
    }
}
