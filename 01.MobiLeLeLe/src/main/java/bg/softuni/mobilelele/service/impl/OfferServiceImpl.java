package bg.softuni.mobilelele.service.impl;

import bg.softuni.mobilelele.model.dto.AddOfferDTO;
import bg.softuni.mobilelele.model.dto.OfferDetailsDTO;
import bg.softuni.mobilelele.model.dto.OfferSummaryDTO;
import bg.softuni.mobilelele.model.entity.Offer;
import bg.softuni.mobilelele.repository.OfferRepository;
import bg.softuni.mobilelele.service.ExRateService;
import bg.softuni.mobilelele.service.OfferService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class OfferServiceImpl implements OfferService {
    private final Logger LOGGER = LoggerFactory.getLogger(OfferServiceImpl.class);
    private final RestClient offerRestClient;
    private final OfferRepository offerRepository;
    private final ExRateService exRateService;
    private final ModelMapper modelMapper;

    public OfferServiceImpl(@Qualifier("offersRestClient") RestClient offerRestClient,
                            OfferRepository offerRepository,
                            ExRateService exRateService,
                            ModelMapper modelMapper) {
        this.offerRestClient = offerRestClient;
        this.offerRepository = offerRepository;
        this.exRateService = exRateService;
        this.modelMapper = modelMapper;
    }

    public void createOffer(AddOfferDTO addOfferDTO) {
        this.LOGGER.info("Creating new offer...");

        this.offerRestClient
                .post()
                .uri("/offers")
                .body(addOfferDTO)
                .retrieve()
                .toBodilessEntity();
    }

    public OfferDetailsDTO getOfferDetails(Long id) {
        return this.offerRestClient
                .get()
                .uri("/offers/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(OfferDetailsDTO.class);
    }

    public List<OfferSummaryDTO> getAllOffersSummary() {
        this.LOGGER.info("Get all offers...");
        return this.offerRestClient
                .get()
                .uri("/offers")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }

    public void deleteOffer(Long id) {
        this.offerRepository.deleteById(id);
    }

    private OfferDetailsDTO toOfferDetailsDTO(Offer offer) {
        return new OfferDetailsDTO(offer.getId(),
                offer.getDescription(),
                offer.getMileage(),
                offer.getPrice(),
                offer.getEngine(),
                this.exRateService.allSupportedCurrencies(),
                offer.getPrice());
    }

    private OfferSummaryDTO toOfferSummaryDTO(Offer offer) {
        return new OfferSummaryDTO(offer.getId(), offer.getDescription(), offer.getMileage(), offer.getEngine(), offer.getPrice());
    }

    private static Offer map(AddOfferDTO addOfferDTO) {
        Offer offer = new Offer();
        offer.setEngine(addOfferDTO.engineType());
        offer.setDescription(addOfferDTO.description());
        offer.setMileage(addOfferDTO.mileage());
        offer.setPrice(addOfferDTO.price());
        return offer;
    }
}