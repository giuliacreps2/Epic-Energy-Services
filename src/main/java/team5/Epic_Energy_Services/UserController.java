package team5.Epic_Energy_Services;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class UserController {
    private  final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO save(@RequestBody @Validated UserDTO userDTO, BindingResult validated){
        if(validated.hasErrors()) throw new BadRequestException("errori");
        return new UserResponseDTO(this.userService.save(userDTO).getId());
    }

}
