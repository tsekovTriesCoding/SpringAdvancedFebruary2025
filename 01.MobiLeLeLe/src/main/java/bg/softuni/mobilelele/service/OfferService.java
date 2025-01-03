package bg.softuni.mobilelele.service;

import bg.softuni.mobilelele.model.dto.AddOfferDTO;
import bg.softuni.mobilelele.model.dto.OfferDetailsDTO;
import bg.softuni.mobilelele.model.dto.OfferSummaryDTO;
import java.util.List;

public interface OfferService {
    void createOffer(AddOfferDTO addOfferDTO);

    OfferDetailsDTO getOfferDetails(Long id);

    List<OfferSummaryDTO> getAllOffersSummary();

    void deleteOffer(Long id);
}