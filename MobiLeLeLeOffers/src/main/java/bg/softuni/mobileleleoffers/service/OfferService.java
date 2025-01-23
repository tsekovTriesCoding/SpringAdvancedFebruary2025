package bg.softuni.mobileleleoffers.service;

import bg.softuni.mobileleleoffers.model.dto.AddOfferDTO;
import bg.softuni.mobileleleoffers.model.dto.OfferDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface OfferService {

    OfferDTO createOffer(AddOfferDTO addOfferDTO);

    PagedModel<OfferDTO> getAllOffers(Pageable pageable);

    void deleteOffer(UserDetails userDetails, Long offerId);

    OfferDTO getOfferById(Long offerId);

    void cleanUpOldOffers();

    boolean isOwner(UserDetails userDetails, Long offerId);
}
