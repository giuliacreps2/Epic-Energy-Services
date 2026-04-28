package team5.Epic_Energy_Services.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import team5.Epic_Energy_Services.entities.UserRole;
import team5.Epic_Energy_Services.repositories.UserRolesRepository;

import java.util.UUID;

@Service
public class UserRoleService {
    private final UserRolesRepository userRolesRepository;

    public UserRoleService(UserRolesRepository userRolesRepository) {
        this.userRolesRepository = userRolesRepository;
    }

    public UserRole save(UserRole userRole) {
        return this.userRolesRepository.save(userRole);
    }

    public Page<UserRole> findAll(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size);
        return this.userRolesRepository.findAll(pageable);
    }
    public void deleteByUserId(UUID id){
        this.userRolesRepository.deleteByUserId(id);
    }
    public UserRole findByUserId(UUID id){
        return  this.userRolesRepository.findByUserId(id);
    }
}
