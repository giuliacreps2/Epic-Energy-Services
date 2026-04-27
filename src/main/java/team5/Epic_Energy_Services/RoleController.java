package team5.Epic_Energy_Services;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/roles")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoleDTO save(@RequestBody @Validated RoleDTO roleDTO, BindingResult validation){
        if(validation.hasErrors())throw new BadRequestException("role is not valid");
        return this.roleService.save(roleDTO);
    }
    @GetMapping
    public List<RoleDTO> findAll(){
        return  this.roleService.findAll();
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void DeleteById(@PathVariable UUID id){
        this.roleService.deleteById(id);
    }
    @PutMapping("/{id}")
    public RoleDTO modifyById(@RequestBody @Validated RoleDTO roleDTO, BindingResult validation,@PathVariable UUID id){
        if(validation.hasErrors())throw new BadRequestException("role is not valid");
        return this.roleService.modifyById(roleDTO,id);
    }
}
