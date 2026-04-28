package team5.Epic_Energy_Services.controllers;

import org.springframework.data.domain.Page;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team5.Epic_Energy_Services.entities.User;
import team5.Epic_Energy_Services.entities.UserRole;
import team5.Epic_Energy_Services.services.UserRoleService;
import team5.Epic_Energy_Services.services.UsersService;

import java.util.Collection;

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
    public Page<UserRole> findAll(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "3") int size,
                                  @RequestParam(defaultValue = "username") String sortBy) {
        return this.userRoleService.findAll(page, size, sortBy);

    }

    @GetMapping("/me")
    public Collection<? extends GrantedAuthority> me(@AuthenticationPrincipal User user) {
        return user.getAuthorities();
    }

}
