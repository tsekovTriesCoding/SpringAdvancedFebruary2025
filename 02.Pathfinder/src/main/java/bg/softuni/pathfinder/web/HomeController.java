package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final RouteService routeService;

    @GetMapping({"/"})
    public String index(Model model) {
        model.addAttribute("mostCommentedRoute", this.routeService.getMostCommentedRoute());
        return "index";
    }

    @GetMapping({"/about"})
    public String about() {
        return "about";
    }
}