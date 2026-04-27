package team5.Epic_Energy_Services;

import org.springframework.stereotype.Service;

@Service
public class UserRoleService {
    private final UserRoleRepository userRoleRepository;
    public UserRoleService(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }
    public UserRole save(UserRole userRole){
         return this.userRoleRepository.save(userRole);
    }
}
