package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.model.dto.AddRouteDTO;
import bg.softuni.pathfinder.model.dto.RouteShortInfoDTO;
import bg.softuni.pathfinder.model.enums.CategoryType;
import bg.softuni.pathfinder.model.enums.Level;
import bg.softuni.pathfinder.service.impl.RouteServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class RouteController {
    private final RouteServiceImpl routeService;

    @ModelAttribute
    public AddRouteDTO addRouteDTO() {
        return new AddRouteDTO();
    }

    @GetMapping("/routes")
    @PreAuthorize("hasRole('ADMIN')") //TODO make it work
    public String routes(Model model) {
        List<RouteShortInfoDTO> routes = this.routeService.getAll();
        model.addAttribute("allRoutes", routes);
        return "routes";
    }

    @GetMapping("/add-route")
    public String addRoute(Model model) {
        model.addAttribute("allLevels", Level.values());
        model.addAttribute("categories", CategoryType.values());
        return "/add-route";
    }

    @PostMapping("/add-route")
    public String addRoute(@Valid AddRouteDTO addRouteDTO,
                           @RequestParam("gpxCoordinates") MultipartFile file,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) throws IOException {

        routeService.add(addRouteDTO, file);
        return "redirect:/add-route";
    }

    @GetMapping("/route/{id}")
    public ModelAndView showRoute(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("route-details");

        modelAndView.addObject("route", this.routeService.getDetails(id));
        return modelAndView;
    }

    @GetMapping("/routes/{category}")
    public ModelAndView showRouteByCategory(@PathVariable CategoryType category) {
        String view = "";
        switch (category){
            case CAR -> view ="car";
            case BICYCLE -> view ="bicycle";
            case PEDESTRIAN -> view ="pedestrian";
            case MOTORCYCLE -> view ="motorcycle";
        }

        ModelAndView modelAndView = new ModelAndView(view);

        modelAndView.addObject("routes", routeService.getRouteByCategory(category));

        return modelAndView;
    }
}