package team5.Epic_Energy_Services.services;

import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import team5.Epic_Energy_Services.entities.Role;
import team5.Epic_Energy_Services.entities.User;
import team5.Epic_Energy_Services.entities.UserRole;
import team5.Epic_Energy_Services.exceptions.BadRequestException;
import team5.Epic_Energy_Services.exceptions.NotFoundException;
import team5.Epic_Energy_Services.payloads.UsersDTO;
import team5.Epic_Energy_Services.repositories.UsersRepository;

import java.util.UUID;

@Service
public class UsersService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder encoder;
    private final RoleService roleService;
    private final UserRoleService userRoleService;

    public UsersService(UsersRepository usersRepository,
                        PasswordEncoder encoder, RoleService roleService, UserRoleService userRoleService
    ) {
        this.usersRepository = usersRepository;
        this.encoder = encoder;
        this.roleService = roleService;
        this.userRoleService = userRoleService;
    }

    public User findByEmail(String email) {
        return this.usersRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("email user " + email + " not found"));
    }



    @Transactional
    public User findById(UUID userId) {
        return this.usersRepository.findById(userId).orElseThrow(() -> new NotFoundException("id not found"));
    }

    public User save(UsersDTO usersDTO) {
        if(this.usersRepository.existsByEmail(usersDTO.email())) throw new BadRequestException("email already used");
        if(this.usersRepository.existsByUsername(usersDTO.username())) throw new BadRequestException("username already used");
        User user = new User(usersDTO.username(), usersDTO.email()
                , this.encoder.encode(usersDTO.password()), usersDTO.name(), usersDTO.surname(), usersDTO.avatar());
        Role role = this.roleService.findByName(usersDTO.role().trim());
        UserRole userRole = new UserRole(user, role);
        User userCreated = this.usersRepository.save(user);
        this.userRoleService.save(userRole);
        return userCreated;
    }
    @Transactional
    public void deleteById(UUID id){
        this.userRoleService.deleteByUserId(id);
        this.usersRepository.deleteById(id);
    }
    public User modifyById(User user, UsersDTO usersDTO){
        user.setAvatar(usersDTO.avatar());
        user.setEmail(usersDTO.email());
        user.setName(usersDTO.name());
        user.setSurname(usersDTO.surname());
        user.setPassword(encoder.encode(usersDTO.password()));
        user.setUsername(usersDTO.username());
        Role newRole = this.roleService.findByName(usersDTO.role());
        UserRole userRole = this.userRoleService.findByUserId(user.getId());
        userRole.setRole(newRole);
        this.userRoleService.save(userRole);
        return  this.usersRepository.save(user);

    }
}
