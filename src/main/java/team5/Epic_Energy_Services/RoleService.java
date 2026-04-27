package team5.Epic_Energy_Services;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    public RoleDTO save(RoleDTO roleDTO){
        Role role = new Role(roleDTO.roleName());
        this.roleRepository.save(role);
        return roleDTO;
    }
    public List<RoleDTO> findAll(){
        List<Role> roles = this.roleRepository.findAll();
        List<RoleDTO> rolesDto = roles.stream().map(r-> new RoleDTO(r.getRoleName())).toList();
        return rolesDto;
    }
    public void deleteById(UUID id){
        Role found = this.roleRepository.findById(id).orElseThrow(()->new NotFound("role not found"));
        this.roleRepository.delete(found);
    }
    public RoleDTO modifyById(RoleDTO roleDTO, UUID id){
        Role found = this.roleRepository.findById(id).orElseThrow(()->new NotFound("role not found"));
        found.setRoleName(roleDTO.roleName());
        this.roleRepository.save(found);
        return new RoleDTO(found.getRoleName());
    }
    public Role findByName(String name){
        return this.roleRepository.findByRoleName(name).orElseThrow(()-> new NotFound("role not found"));
    }
}
