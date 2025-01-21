package bg.softuni.mobileleleoffers.service.impl;

import bg.softuni.mobileleleoffers.model.dto.AddOfferDTO;
import bg.softuni.mobileleleoffers.model.dto.OfferDTO;
import bg.softuni.mobileleleoffers.model.entity.Offer;
import bg.softuni.mobileleleoffers.repository.OfferRepository;
import bg.softuni.mobileleleoffers.service.OfferService;
import bg.softuni.mobileleleoffers.service.exception.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.Period;
import java.util.List;

@Service
public class OfferServiceImpl implements OfferService {
    private final Logger LOGGER = LoggerFactory.getLogger(OfferServiceImpl.class);
    private final OfferRepository offerRepository;
    private final Period retentionPeriod;

    public OfferServiceImpl(OfferRepository offerRepository,
                            @Value("${offers.retention.period}") Period retentionPeriod) {
        this.offerRepository = offerRepository;
        this.retentionPeriod = retentionPeriod;
    }

    @Override
    public OfferDTO createOffer(AddOfferDTO addOfferDTO) {
        Offer offer = this.offerRepository.save(map(addOfferDTO));
        return map(offer);
    }

    @Override
    public List<OfferDTO> getAllOffers() {
        return this.offerRepository.findAll()
                .stream()
                .map(OfferServiceImpl::map)
                .toList();
    }

    @Override
    public void deleteOffer(Long offerId) {
        this.offerRepository.deleteById(offerId);
    }

    @Override
    public OfferDTO getOfferById(Long offerId) {
        return this.offerRepository.findById(offerId)
                .map(OfferServiceImpl::map)
                .orElseThrow(ObjectNotFoundException::new);
    }

    @Override
    public void cleanUpOldOffers() {
        Instant now = Instant.now();
        Instant deleteBefore = now.minus(retentionPeriod);

        LOGGER.info("Cleaning up old offers older than {}", deleteBefore);
        offerRepository.deleteOldOffers(deleteBefore);
        LOGGER.info("Old offers were removed");
    }

    private static OfferDTO map(Offer offer) {
        return new OfferDTO(
                offer.getId(),
                offer.getDescription(),
                offer.getMileage(),
                (int) offer.getPrice(),
                offer.getEngine());
    }

    private static Offer map(AddOfferDTO addOfferDTO) {
        return new Offer()
                .setDescription(addOfferDTO.description())
                .setEngine(addOfferDTO.engineType())
                .setMileage(addOfferDTO.mileage())
                .setPrice(addOfferDTO.price());
    }
}
