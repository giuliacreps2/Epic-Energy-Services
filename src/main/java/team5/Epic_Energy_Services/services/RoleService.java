package team5.Epic_Energy_Services.services;

import org.springframework.stereotype.Service;
import team5.Epic_Energy_Services.entities.Role;
import team5.Epic_Energy_Services.exceptions.NotFoundException;
import team5.Epic_Energy_Services.payloads.RolesDTO;
import team5.Epic_Energy_Services.repositories.RolesRepository;

import java.util.List;
import java.util.UUID;

@Service
public class RoleService {
    private final RolesRepository rolesRepository;

    public RoleService(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    public RolesDTO save(RolesDTO rolesDTO) {
        Role role = new Role(rolesDTO.roleName());
        this.rolesRepository.save(role);
        return rolesDTO;
    }

    public List<RolesDTO> findAll() {
        List<Role> roles = this.rolesRepository.findAll();
        List<RolesDTO> rolesDto = roles.stream().map(r -> new RolesDTO(r.getRoleName())).toList();
        return rolesDto;
    }

    public void deleteById(UUID id) {
        Role found = this.rolesRepository.findById(id).orElseThrow(() -> new NotFoundException("role not found"));
        this.rolesRepository.delete(found);
    }

    public RolesDTO modifyById(RolesDTO rolesDTO, UUID id) {
        Role found = this.rolesRepository.findById(id).orElseThrow(() -> new NotFoundException("role not found"));
        found.setRoleName(rolesDTO.roleName());
        this.rolesRepository.save(found);
        return new RolesDTO(found.getRoleName());
    }

    public Role findByName(String name) {
        return this.rolesRepository.findByRoleName(name).orElseThrow(() -> new NotFoundException("role not found"));
    }

    public boolean existByName(String name) {
        return this.rolesRepository.existsByRoleName(name);
    }
}
