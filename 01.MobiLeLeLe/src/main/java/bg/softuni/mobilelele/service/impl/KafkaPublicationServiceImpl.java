package bg.softuni.mobilelele.service.impl;

import bg.softuni.mobilelele.model.dto.ExRateDTO;
import bg.softuni.mobilelele.service.KafkaPublicationService;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static bg.softuni.mobilelele.config.KafkaConfig.EX_RATES_TOPIC;

@Service
public class KafkaPublicationServiceImpl implements KafkaPublicationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaPublicationServiceImpl.class);
    private final KafkaTemplate<String, ExRateDTO> kafkaTemplate;

    public KafkaPublicationServiceImpl(KafkaTemplate<String, ExRateDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publishExRate(ExRateDTO exRateDTO) {
        kafkaTemplate.
                send(EX_RATES_TOPIC, exRateDTO.currency(), exRateDTO).
                whenComplete(
                        (res, ex) -> {
                            if (ex == null) {
                                RecordMetadata recordMetadata = res.getRecordMetadata();
                                LOGGER.info(
                                        "Successfully send key {} to topic/partition/offset={}/{}/{}.",
                                        exRateDTO.currency(),
                                        recordMetadata.topic(),
                                        recordMetadata.partition(),
                                        recordMetadata.offset()
                                );
                            } else {
                                LOGGER.error("Error producing message in kafka with key {}.",
                                        exRateDTO.currency(),
                                        ex);
                            }
                        }
                );
    }
}
