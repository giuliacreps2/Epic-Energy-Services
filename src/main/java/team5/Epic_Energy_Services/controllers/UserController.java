package team5.Epic_Energy_Services.controllers;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team5.Epic_Energy_Services.entities.User;
import team5.Epic_Energy_Services.entities.UserRole;
import team5.Epic_Energy_Services.exceptions.BadRequestException;
import team5.Epic_Energy_Services.payloads.UsersDTO;
import team5.Epic_Energy_Services.services.UserRoleService;
import team5.Epic_Energy_Services.services.UsersService;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/account")
public class UserController {
    private final UsersService usersService;
    private final UserRoleService userRoleService;

    public UserController(UsersService usersService, UserRoleService userRoleService) {
        this.usersService = usersService;
        this.userRoleService = userRoleService;
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('admin')")
    public Page<UserRole> findAll(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "3") int size,
                                  @RequestParam(defaultValue = "username") String sortBy) {
        return this.userRoleService.findAll(page, size, sortBy);

    }
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@AuthenticationPrincipal User user){
        this.usersService.deleteById(user.getId());
    }
    @PutMapping
    public User modifyById(@AuthenticationPrincipal User user, @RequestBody @Validated UsersDTO body, BindingResult validation){
        if (validation.hasErrors()) {
            String errors = validation.getAllErrors()
                    .stream()
                    .map(e -> e.getDefaultMessage())
                    .collect(Collectors.joining(", "));
            throw new BadRequestException("Dati non validi: " + errors);
        }
        return this.usersService.modifyById(user,body);
    }

    @GetMapping()
    public User getUser(@AuthenticationPrincipal User user) {
       return user;
    }

}
