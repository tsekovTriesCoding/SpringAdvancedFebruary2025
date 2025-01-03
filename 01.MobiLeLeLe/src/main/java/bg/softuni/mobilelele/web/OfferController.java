package bg.softuni.mobilelele.web;

import bg.softuni.mobilelele.model.dto.AddOfferDTO;
import bg.softuni.mobilelele.model.enums.EngineTypeEnum;
import bg.softuni.mobilelele.service.OfferService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @DeleteMapping({"/{id}"})
    public String deleteOffer(@PathVariable("id") Long id) {
        this.offerService.deleteOffer(id);
        return "redirect:/offers/all";
    }
}