package team5.Epic_Energy_Services.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import team5.Epic_Energy_Services.entities.UserRole;
import team5.Epic_Energy_Services.repositories.UserRoleRepository;

@Service
public class UserRoleService {
    private final UserRoleRepository userRoleRepository;

    public UserRoleService(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    public UserRole save(UserRole userRole) {
        return this.userRoleRepository.save(userRole);
    }

    public Page<UserRole> findAll(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size);
        return this.userRoleRepository.findAll(pageable);
    }
}
