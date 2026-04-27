package team5.Epic_Energy_Services;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class UserController {
    private  final UserService userService;
    private final UserRoleService userRoleService;

    public UserController(UserService userService, UserRoleService userRoleService)
    {
        this.userService = userService;
        this.userRoleService = userRoleService;
    }

    @PutMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO save(@RequestBody @Validated UserDTO userDTO, BindingResult validated){
        if(validated.hasErrors()) throw new BadRequestException("errori");
        return new UserResponseDTO(this.userService.save(userDTO).getId());
    }
    @GetMapping("/all")
    public Page<UserRole> findAll(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "3") int size,
                                  @RequestParam(defaultValue = "username") String sortBy){
        return this.userRoleService.findAll(page,size,sortBy);

    }

}
