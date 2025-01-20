package bg.softuni.mobileleleoffers.web;

import bg.softuni.mobileleleoffers.model.dto.AddOfferDTO;
import bg.softuni.mobileleleoffers.model.dto.OfferDTO;
import bg.softuni.mobileleleoffers.service.OfferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/offers")
public class OfferController {
    private final Logger LOGGER = LoggerFactory.getLogger(OfferController.class);
    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping
    public ResponseEntity<List<OfferDTO>> getAllOffers(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails != null) {
            System.out.println("Subject: " + userDetails.getUsername());
            System.out.println("Roles: " + userDetails.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(", ")));
        } else {
            System.out.println("No current user");
        }

        this.LOGGER.info("Get all offers...");
        return ResponseEntity.ok(offerService.getAllOffers());
    }

    @PostMapping()
    public ResponseEntity<OfferDTO> createOffer(@RequestBody AddOfferDTO addOfferDTO) {
        this.LOGGER.info("Going to create an offer {}", addOfferDTO);
        OfferDTO offerDTO = this.offerService.createOffer(addOfferDTO);

        return ResponseEntity.created(ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}").buildAndExpand(offerDTO.id())
                        .toUri())
                .body(offerDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfferDTO> findById(@PathVariable("id") Long id) {
        this.LOGGER.info("Going to get an offer with id {}", id);
        return ResponseEntity.ok(this.offerService.getOfferById(id));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<OfferDTO> deleteOffer(@PathVariable("id") Long id) {
        this.LOGGER.info("Going to delete an offer {}", id);
        this.offerService.deleteOffer(id);

        return ResponseEntity.noContent().build();
    }
}
