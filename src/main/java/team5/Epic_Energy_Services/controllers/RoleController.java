package team5.Epic_Energy_Services.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team5.Epic_Energy_Services.exceptions.BadRequestException;
import team5.Epic_Energy_Services.payloads.RolesDTO;
import team5.Epic_Energy_Services.services.RoleService;

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
    public RolesDTO save(@RequestBody @Validated RolesDTO rolesDTO, BindingResult validation) {
        if (validation.hasErrors()) throw new BadRequestException("role is not valid");
        return this.roleService.save(rolesDTO);
    }

    @GetMapping
    public List<RolesDTO> findAll() {
        return this.roleService.findAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void DeleteById(@PathVariable UUID id) {
        this.roleService.deleteById(id);
    }

    @PutMapping("/{id}")
    public RolesDTO modifyById(@RequestBody @Validated RolesDTO rolesDTO, BindingResult validation, @PathVariable UUID id) {
        if (validation.hasErrors()) throw new BadRequestException("role is not valid");
        return this.roleService.modifyById(rolesDTO, id);
    }
}
