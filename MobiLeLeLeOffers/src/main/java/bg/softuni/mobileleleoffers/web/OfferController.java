package bg.softuni.mobileleleoffers.web;

import bg.softuni.mobileleleoffers.model.dto.AddOfferDTO;
import bg.softuni.mobileleleoffers.model.dto.OfferDTO;
import bg.softuni.mobileleleoffers.service.MonitoringService;
import bg.softuni.mobileleleoffers.service.OfferService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
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
@Tag(
        name = "Offers",
        description = "The controller responsible for offer management."
)
public class OfferController {
    private final Logger LOGGER = LoggerFactory.getLogger(OfferController.class);
    private final OfferService offerService;
    private final MonitoringService monitoringService;

    public OfferController(OfferService offerService, MonitoringService monitoringService) {
        this.offerService = offerService;
        this.monitoringService = monitoringService;
    }

    @GetMapping
    public ResponseEntity<PagedModel<OfferDTO>> getAllOffers(
            @AuthenticationPrincipal UserDetails userDetails,
            @PageableDefault(
                    size = 3,
                    sort = "id",
                    direction = Sort.Direction.DESC
            ) Pageable pageable) {
        monitoringService.increaseOfferSearches();
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
        return ResponseEntity.ok(offerService.getAllOffers(pageable));
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

    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The offer details",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = OfferDTO.class)
                                    )
                            }
                    ),
                    @ApiResponse(responseCode = "404", description = "If the offer was not found",
                            content = {
                                    @Content(
                                            mediaType = "application/json"
                                    )
                            }
                    )
            }
    ) //for swagger
    @GetMapping("/{id}")
    public ResponseEntity<OfferDTO> findById(@PathVariable("id") Long id) {
        this.LOGGER.info("Going to get an offer with id {}", id);
        return ResponseEntity.ok(this.offerService.getOfferById(id));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<OfferDTO> deleteOffer(@PathVariable("id") Long id,
                                                @AuthenticationPrincipal UserDetails userDetails) {
        this.LOGGER.info("Going to delete an offer {}", id);
        this.offerService.deleteOffer(userDetails, id);

        return ResponseEntity.noContent().build();
    }
}
