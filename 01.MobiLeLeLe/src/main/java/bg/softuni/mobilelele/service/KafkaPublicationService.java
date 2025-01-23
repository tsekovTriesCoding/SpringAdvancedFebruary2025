package bg.softuni.mobilelele.service;

import bg.softuni.mobilelele.model.dto.ExRateDTO;

public interface KafkaPublicationService {

    void publishExRate(ExRateDTO exRateDTO);
}