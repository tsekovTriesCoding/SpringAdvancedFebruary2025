package bg.softuni.mobilelele.web;

import bg.softuni.mobilelele.model.dto.UserRegistrationDTO;
import bg.softuni.mobilelele.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/users"})
public class RegisterController {
    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("registerDTO")
    public UserRegistrationDTO registerDTO() {
        return new UserRegistrationDTO();
    }

    @GetMapping({"/register"})
    public String register() {
        return "auth-register";
    }

    @PostMapping({"/register"})
    public String register(UserRegistrationDTO userRegistrationDTO) {
        this.userService.registerUser(userRegistrationDTO);
        return "index";
    }
}