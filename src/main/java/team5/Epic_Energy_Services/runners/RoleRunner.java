package team5.Epic_Energy_Services.runners;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import team5.Epic_Energy_Services.payloads.RolesDTO;
import team5.Epic_Energy_Services.services.RoleService;

@Component
public class RoleRunner implements CommandLineRunner {
    private final RoleService roleService;

    public RoleRunner(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public void run(String... args) throws Exception {

        RolesDTO admin = new RolesDTO("admin");
        RolesDTO user = new RolesDTO("user");
        if(this.roleService.existByName(admin.roleName()) && this.roleService.existByName(user.roleName())){
            System.out.println("already in the db");
        }
        else {
            this.roleService.save(admin);
            this.roleService.save(user);
        }
    }
}
