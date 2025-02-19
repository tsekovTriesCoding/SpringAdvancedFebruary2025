package bg.softuni.mobilelele.web;

import bg.softuni.mobilelele.service.OfferService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/offers"})
public class OffersController {
    private final OfferService offerService;

    public OffersController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping({"/all"})
    public String getAllOffers(Model model) {
        model.addAttribute("allOffers", this.offerService.getAllOffersSummary());
        return "offers";
    }
}
