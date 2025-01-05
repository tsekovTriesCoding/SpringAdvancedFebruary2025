package bg.softuni.mobilelele.web;

import bg.softuni.mobilelele.model.MobileleleUserDetails;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/"})
    public String home(@AuthenticationPrincipal UserDetails userDetails, Model model) {

        if (userDetails instanceof MobileleleUserDetails mobileleleUserDetails) {
            model.addAttribute("welcomeMessage", mobileleleUserDetails.getFullName());
        } else {
            model.addAttribute("welcomeMessage", "Guest");
        }

        return "index";
    }
}
