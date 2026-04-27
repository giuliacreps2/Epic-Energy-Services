package team5.Epic_Energy_Services.services;

import org.springframework.stereotype.Service;
import team5.Epic_Energy_Services.entities.Role;
import team5.Epic_Energy_Services.exceptions.NotFoundException;
import team5.Epic_Energy_Services.payloads.RoleDTO;
import team5.Epic_Energy_Services.repositories.RoleRepository;

import java.util.List;
import java.util.UUID;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public RoleDTO save(RoleDTO roleDTO) {
        Role role = new Role(roleDTO.roleName());
        this.roleRepository.save(role);
        return roleDTO;
    }

    public List<RoleDTO> findAll() {
        List<Role> roles = this.roleRepository.findAll();
        List<RoleDTO> rolesDto = roles.stream().map(r -> new RoleDTO(r.getRoleName())).toList();
        return rolesDto;
    }

    public void deleteById(UUID id) {
        Role found = this.roleRepository.findById(id).orElseThrow(() -> new NotFoundException("role not found"));
        this.roleRepository.delete(found);
    }

    public RoleDTO modifyById(RoleDTO roleDTO, UUID id) {
        Role found = this.roleRepository.findById(id).orElseThrow(() -> new NotFoundException("role not found"));
        found.setRoleName(roleDTO.roleName());
        this.roleRepository.save(found);
        return new RoleDTO(found.getRoleName());
    }

    public Role findByName(String name) {
        return this.roleRepository.findByRoleName(name).orElseThrow(() -> new NotFoundException("role not found"));
    }
}
