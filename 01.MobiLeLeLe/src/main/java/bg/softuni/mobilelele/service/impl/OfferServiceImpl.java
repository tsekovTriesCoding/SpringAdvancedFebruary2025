package bg.softuni.mobilelele.service.impl;

import bg.softuni.mobilelele.model.dto.AddOfferDTO;
import bg.softuni.mobilelele.model.dto.OfferDetailsDTO;
import bg.softuni.mobilelele.model.dto.OfferSummaryDTO;
import bg.softuni.mobilelele.model.entity.Offer;
import bg.softuni.mobilelele.repository.OfferRepository;
import bg.softuni.mobilelele.service.OfferService;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;
    private final ModelMapper modelMapper;

    public OfferServiceImpl(OfferRepository offerRepository, ModelMapper modelMapper) {
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
    }

    public void createOffer(AddOfferDTO addOfferDTO) {
        Offer offer = (Offer)this.modelMapper.map(addOfferDTO, Offer.class);
        this.offerRepository.saveAndFlush(offer);
    }

    public OfferDetailsDTO getOfferDetails(Long id) {
        Offer offer = this.offerRepository.findById(id).orElse(null);
        return this.toOfferDetailsDTO(offer);
    }

    public List<OfferSummaryDTO> getAllOffersSummary() {
        return this.offerRepository.findAll().stream().map(this::toOfferSummaryDTO).toList();
    }

    public void deleteOffer(Long id) {
        this.offerRepository.deleteById(id);
    }

    private OfferDetailsDTO toOfferDetailsDTO(Offer offer) {
        return new OfferDetailsDTO(offer.getId(), offer.getDescription(), offer.getMileage(), offer.getPrice(), offer.getEngine(), null);
    }

    private OfferSummaryDTO toOfferSummaryDTO(Offer offer) {
        return new OfferSummaryDTO(offer.getId(), offer.getDescription(), offer.getMileage(), offer.getEngine());
    }
}