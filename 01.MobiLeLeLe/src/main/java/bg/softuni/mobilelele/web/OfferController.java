package bg.softuni.mobilelele.web;

import bg.softuni.mobilelele.model.dto.AddOfferDTO;
import bg.softuni.mobilelele.model.enums.EngineTypeEnum;
import bg.softuni.mobilelele.service.OfferService;
import bg.softuni.mobilelele.service.exception.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping({"/offers"})
public class OfferController {
    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @ModelAttribute("allEngineTypes")
    public EngineTypeEnum[] allEngineTypes() {
        return EngineTypeEnum.values();
    }

    @GetMapping({"/add"})
    public String newOffer(Model model) {
        if (!model.containsAttribute("addOfferDTO")) {
            model.addAttribute("addOfferDTO", AddOfferDTO.empty());
        }

        return "offer-add";
    }

    @PostMapping({"/add"})
    public String createOffer(@Valid AddOfferDTO addOfferDTO, BindingResult bindingResult, RedirectAttributes rAtt) {
        if (bindingResult.hasErrors()) {
            rAtt.addFlashAttribute("addOfferDTO", addOfferDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.addOfferDTO", bindingResult);
            return "redirect:/offers/add";
        } else {
            this.offerService.createOffer(addOfferDTO);
            return "redirect:/offers/all";
        }
    }

    @GetMapping({"/{id}"})
    public String offerDetails(@PathVariable("id") Long id, Model model) {
        model.addAttribute("offerDetails", this.offerService.getOfferDetails(id));
        return "details";
    }

//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler(ObjectNotFoundException.class)
//    public ModelAndView handleObjectNotFound(ObjectNotFoundException e) {
//        ModelAndView mav = new ModelAndView("offer-not-found");
//        mav.addObject("offerId", e.getId());
//
//        return mav;
//    }


    @DeleteMapping({"/{id}"})
    public String deleteOffer(@PathVariable("id") Long id) {
        this.offerService.deleteOffer(id);
        return "redirect:/offers/all";
    }
}