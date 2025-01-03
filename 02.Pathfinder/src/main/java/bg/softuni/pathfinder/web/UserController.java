package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.model.dto.UserLoginDTO;
import bg.softuni.pathfinder.model.dto.UserRegisterDTO;
import bg.softuni.pathfinder.model.enums.Level;
import bg.softuni.pathfinder.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping({"/users"})
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("allLevels")
    public Level[] allLevels() {
        return Level.values();
    }

    @GetMapping({"/register"})
    public String registerView(Model model) {
        if (!model.containsAttribute("registerData")) {
            model.addAttribute("registerData", new UserRegisterDTO());
        }

        return "register";
    }

    @PostMapping({"/register"})
    public String doRegister(@Valid UserRegisterDTO registerData,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("registerData", registerData);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerData", bindingResult);
            return "redirect:/users/register";
        } else {
            this.userService.register(registerData);
            return "redirect:/users/login";
        }
    }

    @GetMapping({"/login"})
    public String loginView() {
        return "login";
    }

    @PostMapping({"/login"})
    public String login(UserLoginDTO userLoginDTO) {
        this.userService.login(userLoginDTO);
        return "redirect:/";
    }

    @PostMapping({"/logout"})
    public String logout() {
        this.userService.logout();
        return "redirect:/";
    }

    @GetMapping({"/profile"})
    public String details(Model model) {
        model.addAttribute("userProfile", this.userService.getUserProfile());
        return "profile";
    }
}