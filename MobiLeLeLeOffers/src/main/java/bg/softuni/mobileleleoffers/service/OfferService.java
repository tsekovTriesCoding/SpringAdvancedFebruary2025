package bg.softuni.mobileleleoffers.service;

import bg.softuni.mobileleleoffers.model.dto.AddOfferDTO;
import bg.softuni.mobileleleoffers.model.dto.OfferDTO;

import java.util.List;

public interface OfferService {
    void createOffer(AddOfferDTO addOfferDTO);

    List<OfferDTO> getAllOffers();

    void deleteOffer(Long offerId);

    OfferDTO getOfferById(Long offerId);
}
