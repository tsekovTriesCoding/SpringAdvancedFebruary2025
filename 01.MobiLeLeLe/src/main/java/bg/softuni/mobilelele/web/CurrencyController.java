package bg.softuni.mobilelele.web;

import bg.softuni.mobilelele.model.dto.ConversionResultDTO;
import bg.softuni.mobilelele.service.ExRateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyController {
    private final ExRateService exRateService;

    public CurrencyController(ExRateService exRateService) {
        this.exRateService = exRateService;
    }

    @GetMapping("/api/convert")
    public ResponseEntity<ConversionResultDTO> convert(
            @RequestParam("from") String from,
            @RequestParam("to") String to,
            @RequestParam("amount") BigDecimal amount) {

        BigDecimal result = this.exRateService.convert(from, to, amount);

        return ResponseEntity.ok(new ConversionResultDTO(from, to, amount, result));
    }
}
